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

public class ValidarNuevoSaldoPendiente implements Task {
    private final String monto;
    private final String cuentaOrigen;
    private final String tipoCuenta;

    public ValidarNuevoSaldoPendiente(String monto, String cuentaOrigen, String tipoCuenta) {

        this.monto = monto;
        this.cuentaOrigen = cuentaOrigen;
        this.tipoCuenta = tipoCuenta;
    }

    public static Performable withData(String monto, String cuentaOrigen, String tipoCuenta) {

        return instrumented(ValidarNuevoSaldoPendiente.class, monto, cuentaOrigen, tipoCuenta);

    }
    @SneakyThrows
    @Override
    public <T extends Actor> void performAs(T actor) {

        System.out.println("el saldo inicial es: " + LoginStepDefinitions.pagosServiciosData.getSaldoInicial());
        System.out.println("el monto tipo cambio  es: " + LoginStepDefinitions.pagosServiciosData.getmontoTipoCambio());

        String[] saldoInicial = LoginStepDefinitions.pagosServiciosData.getSaldoInicial().trim().split(" ");

        System.out.println("saldo inical parte 1 " + saldoInicial[0]);
        System.out.println("saldo inical parte 1 " + saldoInicial[1]);
        System.out.println("saldo inical parte 1 " + LoginStepDefinitions.pagosServiciosData.getMonto());

        double mil=0;
        if(Double.parseDouble(LoginStepDefinitions.pagosServiciosData.getmontoTipoCambio())>0){

            if(LoginStepDefinitions.pagosServiciosData.getmontoTipoCambio().length()>6){

                mil = Double.parseDouble(LoginStepDefinitions.pagosServiciosData.getmontoTipoCambio()
                        .substring(0,LoginStepDefinitions.pagosServiciosData.getmontoTipoCambio().length()-6));

                System.out.println("el monto mil es: " + mil);
            }

            LoginStepDefinitions.pagosServiciosData.setsaldoFinal(String.valueOf(String.valueOf(String.format("%.2f",
                    Double.parseDouble(LoginStepDefinitions.pagosServiciosData.getSaldoInicial().replace(saldoInicial[0]+" ", "").replace(",", "")) -
                            Double.parseDouble(LoginStepDefinitions.pagosServiciosData.getmontoTipoCambio()) - (mil*0.05) ))));
        }else {

            if(LoginStepDefinitions.pagosServiciosData.getMonto().replace(LoginStepDefinitions.pagosServiciosData.getSimbolo()+" ", "").replace(",", "").length()>6){

                mil = Double.parseDouble(LoginStepDefinitions.pagosServiciosData.getMonto().replace(LoginStepDefinitions.pagosServiciosData.getSimbolo()+" ", "").replace(",", "")
                        .substring(0,LoginStepDefinitions.pagosServiciosData.getMonto().replace(LoginStepDefinitions.pagosServiciosData.getSimbolo()+" ", "").replace(",", "").length()-6));

                System.out.println("el monto mil es: " + mil);
            }

            LoginStepDefinitions.pagosServiciosData.setsaldoFinal(String.valueOf(String.valueOf(String.format("%.2f",
                    Double.parseDouble(LoginStepDefinitions.pagosServiciosData.getSaldoInicial().replace(saldoInicial[0]+" ", "").replace(",", "")) -
                            Double.parseDouble(LoginStepDefinitions.pagosServiciosData.getMonto().replace(LoginStepDefinitions.pagosServiciosData.getSimbolo()+" ", "").replace(",", "")) - (mil*0.05)))));

        }

        System.out.println("el saldo final es: " + LoginStepDefinitions.pagosServiciosData.getsaldoFinal());


        if(LoginStepDefinitions.pagosServiciosData.getsaldoFinal().length()>9){

            LoginStepDefinitions.pagosServiciosData.setsaldoFinal(saldoInicial[0]+" "+
                    LoginStepDefinitions.pagosServiciosData.getsaldoFinal().substring(0, LoginStepDefinitions.pagosServiciosData.getsaldoFinal().length() - 9) + "," +
                    LoginStepDefinitions.pagosServiciosData.getsaldoFinal().substring(LoginStepDefinitions.pagosServiciosData.getsaldoFinal().length() - 9,LoginStepDefinitions.pagosServiciosData.getsaldoFinal().length() - 6)+ "," +
                    LoginStepDefinitions.pagosServiciosData.getsaldoFinal().substring(LoginStepDefinitions.pagosServiciosData.getsaldoFinal().length() - 6));

        }
        else if(LoginStepDefinitions.pagosServiciosData.getsaldoFinal().length()>6){

            LoginStepDefinitions.pagosServiciosData.setsaldoFinal(saldoInicial[0]+" "+
                    LoginStepDefinitions.pagosServiciosData.getsaldoFinal().substring(0, LoginStepDefinitions.pagosServiciosData.getsaldoFinal().length() - 6) + "," +
                            LoginStepDefinitions.pagosServiciosData.getsaldoFinal().substring(LoginStepDefinitions.pagosServiciosData.getsaldoFinal().length() - 6));

        } else {
            LoginStepDefinitions.pagosServiciosData.setsaldoFinal(saldoInicial[0]+" "+LoginStepDefinitions.pagosServiciosData.getsaldoFinal());
        }


        System.out.println("el saldo final es: " + LoginStepDefinitions.pagosServiciosData.getsaldoFinal());
        //--------------------------------------------------------------------------------------------------------

        //CONSULTAS ----------------------------------------------------------------------------------------------------------------------------------

        actor.attemptsTo(
                WaitUntil.the(LoginPage.MENU_CONSULTAS, isVisible()).forNoMoreThan(150).seconds(),
                Click.on(LoginPage.MENU_CONSULTAS));

        //SALDOS ------------------------------------------------------

        actor.attemptsTo(
                WaitUntil.the(LoginPage.MENU_SALDOS, isVisible()).forNoMoreThan(150).seconds(),
                Click.on(LoginPage.MENU_SALDOS));

        //INGRESAR CUENTA

        actor.attemptsTo(
                WaitUntil.the(LoginPage.INP_BUSCAR_CUENTA, isVisible()).forNoMoreThan(150).seconds(),
                Enter.theValue(cuentaOrigen).into(LoginPage.INP_BUSCAR_CUENTA));

        //BUSCAR CUENTA

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

        //DETALLE DE LA FILA SALDOS --------------------------------

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
