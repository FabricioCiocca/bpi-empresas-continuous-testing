package com.everis.tasks.web.bpi.pagosServicios.sindata;

import com.everis.questions.web.bpi.SaldoDisponibleQuestions;
import com.everis.stepsdefinitions.web.bpi.login.LoginStepDefinitions;
import com.everis.tasks.web.bpi.validacion.ValidarNuevoSaldoPendiente;
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

public class ValidarSaldoFC implements Task {
    private final String monto;
    private final String cuentaOrigen;
    private final String tipoCuenta;

    public ValidarSaldoFC(String monto, String cuentaOrigen, String tipoCuenta) {

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

        String[] saldoInicial = LoginStepDefinitions.pagosServiciosData.getSaldoInicial().trim().split(" ");
        double mil = 0;
        if (Double.parseDouble(LoginStepDefinitions.pagosServiciosData.getmontoTipoCambio()) > 0) {
            if (LoginStepDefinitions.pagosServiciosData.getmontoTipoCambio().length() > 6) {
                mil = Double.parseDouble(LoginStepDefinitions.pagosServiciosData.getmontoTipoCambio().substring(0, LoginStepDefinitions.pagosServiciosData.getmontoTipoCambio().length() - 6));
            }
            LoginStepDefinitions.pagosServiciosData.setsaldoFinal(String.valueOf(String.valueOf(String.format("%.2f", Double.parseDouble(LoginStepDefinitions.pagosServiciosData.getSaldoInicial().replace(saldoInicial[0] + " ", "").replace(",", "")) - Double.parseDouble(LoginStepDefinitions.pagosServiciosData.getmontoTipoCambio()) - (mil * 0.05)))));
        } else {
            if (LoginStepDefinitions.pagosServiciosData.getMonto().replace(LoginStepDefinitions.pagosServiciosData.getSimbolo() + " ", "").replace(",", "").length() > 6) {
                mil = Double.parseDouble(LoginStepDefinitions.pagosServiciosData.getMonto().replace(LoginStepDefinitions.pagosServiciosData.getSimbolo() + " ", "").replace(",", "").substring(0, LoginStepDefinitions.pagosServiciosData.getMonto().replace(LoginStepDefinitions.pagosServiciosData.getSimbolo() + " ", "").replace(",", "").length() - 6));
            }
            LoginStepDefinitions.pagosServiciosData.setsaldoFinal(String.valueOf(String.valueOf(String.format("%.2f", Double.parseDouble(LoginStepDefinitions.pagosServiciosData.getSaldoInicial().replace(saldoInicial[0] + " ", "").replace(",", "")) - Double.parseDouble(LoginStepDefinitions.pagosServiciosData.getMonto().replace(LoginStepDefinitions.pagosServiciosData.getSimbolo() + " ", "").replace(",", "")) - (mil * 0.05)))));
        }
        if (LoginStepDefinitions.pagosServiciosData.getsaldoFinal().length() > 9) {
            LoginStepDefinitions.pagosServiciosData.setsaldoFinal(saldoInicial[0] + " " + LoginStepDefinitions.pagosServiciosData.getsaldoFinal().substring(0, LoginStepDefinitions.pagosServiciosData.getsaldoFinal().length() - 9) + "," + LoginStepDefinitions.pagosServiciosData.getsaldoFinal().substring(LoginStepDefinitions.pagosServiciosData.getsaldoFinal().length() - 9, LoginStepDefinitions.pagosServiciosData.getsaldoFinal().length() - 6) + "," + LoginStepDefinitions.pagosServiciosData.getsaldoFinal().substring(LoginStepDefinitions.pagosServiciosData.getsaldoFinal().length() - 6));
        } else if (LoginStepDefinitions.pagosServiciosData.getsaldoFinal().length() > 6) {
            LoginStepDefinitions.pagosServiciosData.setsaldoFinal(saldoInicial[0] + " " + LoginStepDefinitions.pagosServiciosData.getsaldoFinal().substring(0, LoginStepDefinitions.pagosServiciosData.getsaldoFinal().length() - 6) + "," + LoginStepDefinitions.pagosServiciosData.getsaldoFinal().substring(LoginStepDefinitions.pagosServiciosData.getsaldoFinal().length() - 6));
        } else {
            LoginStepDefinitions.pagosServiciosData.setsaldoFinal(saldoInicial[0] + " " + LoginStepDefinitions.pagosServiciosData.getsaldoFinal());
        }
        actor.attemptsTo(WaitUntil.the(LoginPage.MENU_CONSULTAS, isVisible()).forNoMoreThan(150).seconds(), Click.on(LoginPage.MENU_CONSULTAS));
        actor.attemptsTo(WaitUntil.the(LoginPage.MENU_SALDOS, isVisible()).forNoMoreThan(150).seconds(), Click.on(LoginPage.MENU_SALDOS));
        actor.attemptsTo(WaitUntil.the(LoginPage.INP_BUSCAR_CUENTA, isVisible()).forNoMoreThan(150).seconds(), Enter.theValue(cuentaOrigen).into(LoginPage.INP_BUSCAR_CUENTA));
        actor.attemptsTo(WaitUntil.the(LoginPage.BTN_BUSCAR_CUENTA, isVisible()).forNoMoreThan(150).seconds(), Click.on(LoginPage.BTN_BUSCAR_CUENTA));
        theActorInTheSpotlight().should(seeThat(SaldoDisponibleQuestions.saldoEmpresaTotal(), equalTo(LoginStepDefinitions.pagosServiciosData.getsaldoFinal())));
        String[] tipoCuentaAux = tipoCuenta.split("\\s+");
        if (tipoCuentaAux[0].equals("Corriente")) {
            theActorInTheSpotlight().should(seeThat(SaldoDisponibleQuestions.saldoTipoCuenta(), equalTo("Cuenta " + tipoCuentaAux[0])));
        } else {
            theActorInTheSpotlight().should(seeThat(SaldoDisponibleQuestions.saldoTipoCuenta(), equalTo("Cuenta de " + tipoCuentaAux[0])));
        }
        if (tipoCuentaAux[1].equals("Soles")) {
            theActorInTheSpotlight().should(seeThat(SaldoDisponibleQuestions.saldoTipoMoneda(), equalTo("S/")));
        } else {
            theActorInTheSpotlight().should(seeThat(SaldoDisponibleQuestions.saldoTipoMoneda(), equalTo("$")));
        }

        theActorInTheSpotlight().should(seeThat(SaldoDisponibleQuestions.saldoNumeroCuenta(), containsString(cuentaOrigen)));
        theActorInTheSpotlight().should(seeThat(SaldoDisponibleQuestions.saldoDisponibleLinea(), equalTo(LoginStepDefinitions.pagosServiciosData.getsaldoFinal())));

        if (saldoInicial[0].equals("S/")) {
            theActorInTheSpotlight().should(seeThat(SaldoDisponibleQuestions.saldoTotalFinal(), equalTo(LoginStepDefinitions.pagosServiciosData.getsaldoFinal())));
        } else {
            theActorInTheSpotlight().should(seeThat(SaldoDisponibleQuestions.saldoTotalDolaresFinal(), equalTo(LoginStepDefinitions.pagosServiciosData.getsaldoFinal())));
        }
        actor.attemptsTo(WaitUntil.the(LoginPage.INFO_CUENTA_FILA, isVisible()).forNoMoreThan(150).seconds(), Click.on(LoginPage.INFO_CUENTA_FILA));
        if (tipoCuentaAux[0].equals("Corriente")) {
            theActorInTheSpotlight().should(seeThat(SaldoDisponibleQuestions.saldoTipoDeCuenta(), equalTo("Cuenta " + LoginStepDefinitions.pagosServiciosData.getTipoDeCuenta())));
        } else {
            theActorInTheSpotlight().should(seeThat(SaldoDisponibleQuestions.saldoTipoDeCuenta(), equalTo("Cuenta de " + LoginStepDefinitions.pagosServiciosData.getTipoDeCuenta())));
        }

        theActorInTheSpotlight().should(seeThat(SaldoDisponibleQuestions.cuentaOrigen(), equalTo(cuentaOrigen)));
        theActorInTheSpotlight().should(seeThat(SaldoDisponibleQuestions.saldoDisponibleDetalle(), equalTo(LoginStepDefinitions.pagosServiciosData.getsaldoFinal())));
    }

    public String saldoRestado(String saldoInicial, Double montoResta) {
        String moneda = LoginStepDefinitions.pagosServiciosData.getSimbolo();
        Double Count = Double.valueOf(LoginStepDefinitions.pagosServiciosData.getCantdni());
        String[] tipoCuenta = LoginStepDefinitions.pagosServiciosData.getTipoDeCuenta().trim().split(" ");
        if (tipoCuenta[1].equals("Soles") && LoginStepDefinitions.pagosServiciosData.getSimbolo().equals("$")) {
            LoginStepDefinitions.pagosServiciosData.setmontoTipoCambio(String.valueOf(String.format("%.2f", Double.parseDouble(LoginStepDefinitions.pagosServiciosData.getMonto().replace(LoginStepDefinitions.pagosServiciosData.getSimbolo() + " ", "").replace(",", "")) * Double.parseDouble(LoginStepDefinitions.pagosServiciosData.getTipoDeCambioVenta()))));
        } else if (tipoCuenta[1].equals("Dólares") && LoginStepDefinitions.pagosServiciosData.getSimbolo().equals("S/")) {
            LoginStepDefinitions.pagosServiciosData.setmontoTipoCambio(String.valueOf(String.format("%.2f", Double.parseDouble(LoginStepDefinitions.pagosServiciosData.getMonto().replace(LoginStepDefinitions.pagosServiciosData.getSimbolo() + " ", "").replace(",", "")) / Double.parseDouble(LoginStepDefinitions.pagosServiciosData.getTipoDeCambioCompra()))));
        } else {
            LoginStepDefinitions.pagosServiciosData.setmontoTipoCambio(LoginStepDefinitions.pagosServiciosData.getMonto().replace(LoginStepDefinitions.pagosServiciosData.getSimbolo() + " ", "").replace(",", ""));
        }
        String saldoDisponibleAntiguo = "prueba";
        String saldoDisponibleActual = "prueba 2";
        int amount = 5;
        if (saldoDisponibleAntiguo.startsWith("S/")) {
            saldoDisponibleActual = "S/ " + String.format("%.2f", Double.parseDouble(saldoDisponibleAntiguo.replace("S/ ", "").replace(",", "")) - amount);
            if (saldoDisponibleActual.length() > 9) {
                saldoDisponibleActual = saldoDisponibleActual.substring(0, saldoDisponibleActual.length() - 6) + "," + saldoDisponibleActual.substring(saldoDisponibleActual.length() - 6);
            }
            if (saldoDisponibleActual.length() > 13) {
                saldoDisponibleActual = saldoDisponibleActual.substring(0, saldoDisponibleActual.length() - 10) + "," + saldoDisponibleActual.substring(saldoDisponibleActual.length() - 10);
                System.out.println("saldo ACTUAL: " + saldoDisponibleActual);
            }

        } else {
            saldoDisponibleActual = "$ " + String.format("%.2f", Double.parseDouble(saldoDisponibleAntiguo.replace("$ ", "").replace(",", "")) - amount);
            if (saldoDisponibleActual.length() > 9) {
                saldoDisponibleActual = saldoDisponibleActual.substring(0, saldoDisponibleActual.length() - 6) + "," + saldoDisponibleActual.substring(saldoDisponibleActual.length() - 6);
            }
            if (saldoDisponibleActual.length() > 13 && saldoDisponibleActual.startsWith("S/")) {
                saldoDisponibleActual = saldoDisponibleActual.substring(0, saldoDisponibleActual.length() - 10) + "," + saldoDisponibleActual.substring(saldoDisponibleActual.length() - 10);
            }
            if (saldoDisponibleActual.length() >= 13 && saldoDisponibleActual.startsWith("$")) {
                saldoDisponibleActual = saldoDisponibleActual.substring(0, saldoDisponibleActual.length() - 10) + "," + saldoDisponibleActual.substring(saldoDisponibleActual.length() - 10);
            }
            LoginStepDefinitions.pagosServiciosData.setMonto(String.valueOf(amount));
        }
        return saldoDisponibleActual;
    }
}
