package com.everis.tasks.web.bpi.validacion;

import com.everis.questions.web.bpi.SaldoDisponibleQuestions;
import com.everis.stepsdefinitions.web.bpi.login.LoginStepDefinitions;
import com.everis.userinterfaces.web.bpi.LoginPage;
import lombok.SneakyThrows;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class ValidarSaldoPendiente implements Task {
    private final String monto;
    private final String cuentaOrigen;
    private final String tipoCuenta;

    public ValidarSaldoPendiente(String monto, String cuentaOrigen, String tipoCuenta) {

        this.monto = monto;
        this.cuentaOrigen = cuentaOrigen;
        this.tipoCuenta = tipoCuenta;
    }

    public static Performable withData(String monto, String cuentaOrigen, String tipoCuenta) {

        return instrumented(ValidarSaldoPendiente.class, monto, cuentaOrigen, tipoCuenta);

    }
    @SneakyThrows
    @Override
    public <T extends Actor> void performAs(T actor) {

        Thread.sleep(2000);

        System.out.println("el saldo inicial es: " + LoginStepDefinitions.pagosServiciosData.getSaldoInicial());

        String[] saldoInicial = LoginStepDefinitions.pagosServiciosData.getSaldoInicial().trim().split(" ");

        System.out.println("saldo inical parte 1 " + saldoInicial[0]);
        System.out.println("saldo inical parte 1 " + saldoInicial[1]);

        LoginStepDefinitions.pagosServiciosData.setsaldoFinal(LoginStepDefinitions.pagosServiciosData.getSaldoInicial());

        System.out.println("el saldo final es: " + LoginStepDefinitions.pagosServiciosData.getsaldoFinal());

        //MENU CONSULTAS------------------------------------------------------------------------------------------------

        Thread.sleep(2000);

        actor.attemptsTo(
                WaitUntil.the(LoginPage.MENU_CONSULTAS, isVisible()).forNoMoreThan(150).seconds(),
                Click.on(LoginPage.MENU_CONSULTAS));

        //SALDOS -------------------------------------------------------------------------------------------------------

        Thread.sleep(6000);

        actor.attemptsTo(
                WaitUntil.the(LoginPage.MENU_SALDOS, isVisible()).forNoMoreThan(150).seconds(),
                Click.on(LoginPage.MENU_SALDOS));

        //INGRESAR CUENTA

        Thread.sleep(2500);

        actor.attemptsTo(
                WaitUntil.the(LoginPage.INP_BUSCAR_CUENTA, isVisible()).forNoMoreThan(150).seconds(),
                Enter.theValue(cuentaOrigen).into(LoginPage.INP_BUSCAR_CUENTA));

        //BUSCAR CUENTA

        Thread.sleep(2000);

        actor.attemptsTo(
                WaitUntil.the(LoginPage.BTN_BUSCAR_CUENTA, isVisible()).forNoMoreThan(150).seconds(),
                Click.on(LoginPage.BTN_BUSCAR_CUENTA));

        //TOTAL SOLES INICIO
        
        theActorInTheSpotlight().should(seeThat(
                SaldoDisponibleQuestions.saldoEmpresaTotal(), equalTo(LoginStepDefinitions.pagosServiciosData.getsaldoFinal())));

        //TIPO DE CUENTA - TABLA

        String[] tipoCuentaAux = tipoCuenta.split("\\s+");

        if (tipoCuentaAux[0].equals("Corriente")) {

            theActorInTheSpotlight().should(seeThat(
                    SaldoDisponibleQuestions.saldoTipoCuenta(), equalTo("Cuenta " + tipoCuentaAux[0])));

        } else {

            theActorInTheSpotlight().should(seeThat(
                    SaldoDisponibleQuestions.saldoTipoCuenta(), equalTo("Cuenta de " + tipoCuentaAux[0])));

        }

        //M - TABLA

        if (tipoCuentaAux[1].equals("Soles")) {

            theActorInTheSpotlight().should(seeThat(
                    SaldoDisponibleQuestions.saldoTipoMoneda(), equalTo("S/")));

        } else {

            theActorInTheSpotlight().should(seeThat(
                    SaldoDisponibleQuestions.saldoTipoMoneda(), equalTo("$")));

        }

        //NUMERO DE CUENTA - TABLA

        Thread.sleep(2000);

        theActorInTheSpotlight().should(seeThat(
                SaldoDisponibleQuestions.saldoNumeroCuenta(), containsString(cuentaOrigen)));

        //SALDO DISPONIBLE - TABLA

        theActorInTheSpotlight().should(seeThat(
                SaldoDisponibleQuestions.saldoDisponibleLinea(), equalTo(LoginStepDefinitions.pagosServiciosData.getsaldoFinal())));

        //TOTAL SOLES FINAL

        if(saldoInicial[0].equals("S/")) {
            theActorInTheSpotlight().should(seeThat(
                    SaldoDisponibleQuestions.saldoTotalFinal(), equalTo(LoginStepDefinitions.pagosServiciosData.getsaldoFinal())));
        }else {
            theActorInTheSpotlight().should(seeThat(
                    SaldoDisponibleQuestions.saldoTotalDolaresFinal(), equalTo(LoginStepDefinitions.pagosServiciosData.getsaldoFinal())));
        }

        //DETALLE DE LA FILA SALDOS ------------------------------------------------------------------------------------

        Thread.sleep(2000);

        actor.attemptsTo(
                WaitUntil.the(LoginPage.INFO_CUENTA_FILA, isVisible()).forNoMoreThan(150).seconds(),
                Click.on(LoginPage.INFO_CUENTA_FILA));

        //TIPO DE CUENTA Y MONEDA

        if (tipoCuentaAux[0].equals("Corriente")) {

            theActorInTheSpotlight().should(seeThat(
                    SaldoDisponibleQuestions.saldoTipoDeCuenta(), equalTo("Cuenta " + LoginStepDefinitions.pagosServiciosData.getTipoDeCuenta())));

        } else {

            theActorInTheSpotlight().should(seeThat(
                    SaldoDisponibleQuestions.saldoTipoDeCuenta(), equalTo("Cuenta de " + LoginStepDefinitions.pagosServiciosData.getTipoDeCuenta())));

        }

        //NUMERO DE CUENTA

        theActorInTheSpotlight().should(seeThat(
                SaldoDisponibleQuestions.cuentaOrigen(), equalTo(cuentaOrigen)));

        //SALDO DISPONIBLE

        theActorInTheSpotlight().should(seeThat(
                SaldoDisponibleQuestions.saldoDisponibleDetalle(), equalTo(LoginStepDefinitions.pagosServiciosData.getsaldoFinal())));

    }
}
