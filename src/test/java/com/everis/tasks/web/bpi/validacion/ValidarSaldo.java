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

public class ValidarSaldo implements Task {
    private final String monto;
    private final String cuentaOrigen;
    private final String tipoCuenta;

    public ValidarSaldo(String monto, String cuentaOrigen, String tipoCuenta) {

        this.monto = monto;
        this.cuentaOrigen = cuentaOrigen;
        this.tipoCuenta = tipoCuenta;
    }

    public static Performable withData(String monto, String cuentaOrigen, String tipoCuenta) {

        return instrumented(ValidarSaldo.class, monto, cuentaOrigen, tipoCuenta);

    }
    @SneakyThrows
    @Override
    public <T extends Actor> void performAs(T actor) {
        Double valorMontoResta = Double.parseDouble(LoginStepDefinitions.pagosServiciosData.getMonto());
        Double itf = 0.005 * valorMontoResta / 100;
        LoginStepDefinitions.pagosServiciosData.setItf(String.valueOf(itf));
        String saldoActualizado = saldoRestado(LoginStepDefinitions.pagosServiciosData.getSaldoInicial(), Double.valueOf(Double.parseDouble(LoginStepDefinitions.pagosServiciosData.getItf()) + valorMontoResta));
        LoginStepDefinitions.pagosServiciosData.setSaldoActualizado(saldoActualizado);
        Thread.sleep(2000);
        actor.attemptsTo(WaitUntil.the(LoginPage.MENU_CONSULTAS, isVisible()).forNoMoreThan(150).seconds(), Click.on(LoginPage.MENU_CONSULTAS));
        Thread.sleep(2500);
        actor.attemptsTo(WaitUntil.the(LoginPage.MENU_SALDOS, isVisible()).forNoMoreThan(150).seconds(), Click.on(LoginPage.MENU_SALDOS));
        Thread.sleep(2500);
        actor.attemptsTo(WaitUntil.the(LoginPage.INP_BUSCAR_CUENTA, isVisible()).forNoMoreThan(150).seconds(), Enter.theValue(cuentaOrigen).into(LoginPage.INP_BUSCAR_CUENTA));
        Thread.sleep(2000);
        actor.attemptsTo(WaitUntil.the(LoginPage.BTN_BUSCAR_CUENTA, isVisible()).forNoMoreThan(150).seconds(), Click.on(LoginPage.BTN_BUSCAR_CUENTA));
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
        Thread.sleep(2000);
        theActorInTheSpotlight().should(seeThat(SaldoDisponibleQuestions.saldoNumeroCuenta(), containsString(cuentaOrigen)));
        theActorInTheSpotlight().should(seeThat(SaldoDisponibleQuestions.saldoEmpresaTotal(), equalTo(LoginStepDefinitions.pagosServiciosData.getSaldoActualizado())));
        theActorInTheSpotlight().should(seeThat(SaldoDisponibleQuestions.saldoDisponibleLinea(), equalTo(LoginStepDefinitions.pagosServiciosData.getSaldoActualizado())));
        theActorInTheSpotlight().should(seeThat(SaldoDisponibleQuestions.saldoTotalFinal(), equalTo(LoginStepDefinitions.pagosServiciosData.getSaldoActualizado())));
        Thread.sleep(2000);
        actor.attemptsTo(WaitUntil.the(LoginPage.INFO_CUENTA_FILA, isVisible()).forNoMoreThan(150).seconds(), Click.on(LoginPage.INFO_CUENTA_FILA));
        theActorInTheSpotlight().should(seeThat(SaldoDisponibleQuestions.cuentaOrigen(), equalTo(cuentaOrigen)));
        if (tipoCuentaAux[0].equals("Corriente")) {
            theActorInTheSpotlight().should(seeThat(SaldoDisponibleQuestions.saldoTipoDeCuenta(), equalTo("Cuenta " + LoginStepDefinitions.pagosServiciosData.getTipoDeCuenta())));
        } else {
            theActorInTheSpotlight().should(seeThat(SaldoDisponibleQuestions.saldoTipoDeCuenta(), equalTo("Cuenta de " + LoginStepDefinitions.pagosServiciosData.getTipoDeCuenta())));
        }
        theActorInTheSpotlight().should(seeThat(SaldoDisponibleQuestions.saldoDisponibleDetalle(), equalTo(LoginStepDefinitions.pagosServiciosData.getSaldoActualizado())));
    }

    public String saldoRestado(String saldoInicial, Double montoResta) {
        double tipoDeCambioVenta = Double.parseDouble(LoginStepDefinitions.pagosServiciosData.getTipoDeCambioVenta());
        double tipoDeCambioCompra = Double.parseDouble(LoginStepDefinitions.pagosServiciosData.getTipoDeCambioCompra());
        double amount;
        String saldoDisponibleAntiguo = saldoInicial;
        String saldoDisponibleActual;
        String[] tipoCuenta = LoginStepDefinitions.pagosServiciosData.getTipoDeCuenta().trim().split(" ");
        String moneda = LoginStepDefinitions.pagosServiciosData.getSimbolo();
        Double Count = Double.valueOf(LoginStepDefinitions.pagosServiciosData.getCantdni());
        if (tipoCuenta[1].equals("Soles") && moneda.equals("$")) {
            amount = Double.parseDouble(String.format("%.2f", montoResta * tipoDeCambioVenta));
        } else if (tipoCuenta[1].equals("Dólares") && moneda.equals("S/")) {
            double itf = Double.parseDouble(LoginStepDefinitions.pagosServiciosData.getItf());
            montoResta = montoResta - itf;
            if (Count > 1) {
                amount = Double.parseDouble(String.format("%.1f", montoResta / tipoDeCambioCompra));
            } else {
                amount = Double.parseDouble(String.valueOf(montoResta / tipoDeCambioCompra));
            }
            if (amount > 1000) {
                for (int i = 1000; i<=amount;i+=1000){
                    amount = Double.parseDouble(String.valueOf(amount + 0.05));
                }
            }
        } else {
            amount = Double.parseDouble(String.format("%.1f", montoResta));
        }

        if (saldoDisponibleAntiguo.startsWith("S/")) {
            saldoDisponibleActual = "S/ " + String.format("%.2f", Double.parseDouble(saldoDisponibleAntiguo.replace("S/ ", "").replace(",", "")) - amount);
            System.out.println("Count:" + saldoDisponibleActual.length());
            if (saldoDisponibleActual.length() > 9) {
                saldoDisponibleActual = saldoDisponibleActual.substring(0, saldoDisponibleActual.length() - 6) + "," + saldoDisponibleActual.substring(saldoDisponibleActual.length() - 6);
            }
            if (saldoDisponibleActual.length() > 13) {
                saldoDisponibleActual = saldoDisponibleActual.substring(0, saldoDisponibleActual.length() - 10) + "," + saldoDisponibleActual.substring(saldoDisponibleActual.length() - 10);
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
