package com.everis.tasks.web.bpi.validacion;

import com.everis.bpi.questions.web.bpi.MovimientosDisponibleQuestions;
import com.everis.bpi.stepsdefinitions.web.bpi.login.LoginStepDefinitions;
import com.everis.bpi.userinterface.web.bpi.SaldosYMovimientosPage;
import lombok.SneakyThrows;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.By;

import java.text.DecimalFormat;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.apache.commons.lang3.StringUtils.stripAccents;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class ValidarMovimientoDataMaestra implements Task {

    public static Performable withData(String monto, String empresa) {
        return instrumented(ValidarMovimientoDataMaestra.class, monto, empresa);
    }

    @SneakyThrows
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(WaitUntil.the(SaldosYMovimientosPage.MENU_CONSULTAS, isVisible()).forNoMoreThan(150).seconds(), Click.on(SaldosYMovimientosPage.MENU_CONSULTAS));
        actor.attemptsTo(WaitUntil.the(SaldosYMovimientosPage.MENU_MOVIMIENTOS, isVisible()).forNoMoreThan(150).seconds(), Click.on(SaldosYMovimientosPage.MENU_MOVIMIENTOS));
        actor.attemptsTo(WaitUntil.the(SaldosYMovimientosPage.CMB_ELEGIR_CUENTA, isVisible()).forNoMoreThan(150).seconds(), Click.on(SaldosYMovimientosPage.CMB_ELEGIR_CUENTA));
        Serenity.getDriver().findElement(By.xpath("//div[contains(text(),'" + LoginStepDefinitions.pagosServiciosData.getCuentaOrigen() + "')]")).click();
        actor.attemptsTo(WaitUntil.the(SaldosYMovimientosPage.BTN_BUSCAR_MOVIMIENTOS, isVisible()).forNoMoreThan(150).seconds(), Click.on(SaldosYMovimientosPage.BTN_BUSCAR_MOVIMIENTOS));
        String[] fechaHoraAux = LoginStepDefinitions.pagosServiciosData.getFechaHora().split("-");
        theActorInTheSpotlight().should(seeThat(MovimientosDisponibleQuestions.fechaOperacion(), equalTo(fechaHoraAux[0].trim())));

        //LocalDate fechaProcesadaAux = LocalDate.now().plusDays(1);
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy ");
        //String fechaProcesada = fechaProcesadaAux.format(formatter);
        //fechaHoraAux[0] = fechaProcesada;

        theActorInTheSpotlight().should(seeThat(MovimientosDisponibleQuestions.fechaProceso(), equalTo(fechaHoraAux[0].trim())));

        String[] tipoCuenta = LoginStepDefinitions.pagosServiciosData.getTipoDeCuenta().trim().split(" ");

        if (tipoCuenta[0].equals("Corriente")) {
            theActorInTheSpotlight().should(seeThat(MovimientosDisponibleQuestions.movimiento(), equalTo("PAGO DE SERVICIOS")));
        } else {
            if (LoginStepDefinitions.pagosServiciosData.getEmpresa().length() > 13) {
                if (MovimientosDisponibleQuestions.descripcion().toString() == "CASUARINAS SA-P") {
                    theActorInTheSpotlight().should(seeThat(MovimientosDisponibleQuestions.descripcion(), containsString("CASUARINAS SA-P")));
                } else {
                    System.out.println("1)" + LoginStepDefinitions.pagosServiciosData.getEmpresa());
                    theActorInTheSpotlight().should(seeThat(MovimientosDisponibleQuestions.descripcion(), containsString(stripAccents(LoginStepDefinitions.pagosServiciosData.getEmpresa().substring(0, 13)))));
                    System.out.println("2)" + LoginStepDefinitions.pagosServiciosData.getEmpresa().substring(0, 13));
                }
            }
            if (MovimientosDisponibleQuestions.movimiento().toString().trim().equals("-")) {
                theActorInTheSpotlight().should(seeThat(MovimientosDisponibleQuestions.movimiento(), containsString(stripAccents("-"))));
                System.out.println("3)" + LoginStepDefinitions.pagosServiciosData.getEmpresa());
            } else {
                theActorInTheSpotlight().should(seeThat(MovimientosDisponibleQuestions.movimiento(), containsString(stripAccents(LoginStepDefinitions.pagosServiciosData.getEmpresa()))));
                System.out.println("4)" + LoginStepDefinitions.pagosServiciosData.getEmpresa());
            }
        }


        if (LoginStepDefinitions.pagosServiciosData.getEmpresa().length() > 13) {
            theActorInTheSpotlight().should(seeThat(MovimientosDisponibleQuestions.descripcion(), containsString(stripAccents(LoginStepDefinitions.pagosServiciosData.getEmpresa().substring(0, 13)))));
            System.out.println("5)" + LoginStepDefinitions.pagosServiciosData.getEmpresa().substring(0, 13));
        } else {
            theActorInTheSpotlight().should(seeThat(MovimientosDisponibleQuestions.descripcion(), containsString(stripAccents(LoginStepDefinitions.pagosServiciosData.getEmpresa()))));
            System.out.println("6)" + LoginStepDefinitions.pagosServiciosData.getEmpresa());
        }

        double Count = Double.parseDouble(LoginStepDefinitions.pagosServiciosData.getCantdni());
        double monto = Double.parseDouble(LoginStepDefinitions.pagosServiciosData.getMonto());
        if (monto > 1000) {
            for (int i = 1000; i <= monto; i += 1000) {
                monto = Double.parseDouble(String.valueOf(monto - 0.05));
            }
        }
        String decim = new DecimalFormat("0.00").format(monto);
        double amount = Double.parseDouble(decim);
        if (Count > 1) {
            double montofinalizado = amount / Count;
            String decim2 = new DecimalFormat("0.00").format(montofinalizado);
            if (decim2.length() > 5) {
                decim2 = decim2.substring(0, decim2.length() - 6) + "," + decim2.substring(decim2.length() - 6);
            } else if (decim2.length() > 7) {
                decim2 = decim2.substring(0, decim2.length() - 7) + "," + decim2.substring(decim2.length() - 7);
            }
            if (tipoCuenta[1].equals("Dólares") && LoginStepDefinitions.pagosServiciosData.getSimbolo().equals("S/")) {
                theActorInTheSpotlight().should(seeThat(MovimientosDisponibleQuestions.importe(), equalTo("$ " + "-" + decim2)));
            } else if (tipoCuenta[1].equals("Soles") && LoginStepDefinitions.pagosServiciosData.getSimbolo().startsWith("$")) {
                theActorInTheSpotlight().should(seeThat(MovimientosDisponibleQuestions.importe(), equalTo("S/ " + "-" + decim2)));
            } else {
                theActorInTheSpotlight().should(seeThat(MovimientosDisponibleQuestions.importe(), equalTo(LoginStepDefinitions.pagosServiciosData.getSimbolo() + " -" + decim2)));
            }

        } else {
            if (decim.length() >= 7) {
                decim = decim.substring(0, decim.length() - 6) + "," + decim.substring(decim.length() - 6);
            }
            if (decim.length() > 8) {
                decim = decim.substring(0, decim.length() - 7) + "," + decim.substring(decim.length() - 7);
            }
            if (LoginStepDefinitions.pagosServiciosData.getSimbolo().equals("S/") && tipoCuenta[1].equals("Dólares")) {
                theActorInTheSpotlight().should(seeThat(MovimientosDisponibleQuestions.importe(), equalTo("$ " + "-" + decim)));
            } else if (LoginStepDefinitions.pagosServiciosData.getSimbolo().startsWith("$") && tipoCuenta[1].equals("Soles")) {
                double tipocambioventa = Double.parseDouble(LoginStepDefinitions.pagosServiciosData.getTipoDeCambioVenta());
                double montofinalizado = amount * tipocambioventa;
                String decim2 = new DecimalFormat("0.00").format(montofinalizado);
                if (decim2.length() >= 7) {
                    decim2 = decim2.substring(0, decim2.length() - 6) + "," + decim2.substring(decim2.length() - 6);
                }
                if (decim2.length() > 8) {
                    decim2 = decim2.substring(0, decim2.length() - 7) + "," + decim2.substring(decim2.length() - 7);
                }
                theActorInTheSpotlight().should(seeThat(MovimientosDisponibleQuestions.importe(), equalTo("S/ " + "-" + decim2)));
            } else {
                theActorInTheSpotlight().should(seeThat(MovimientosDisponibleQuestions.importe(), equalTo(LoginStepDefinitions.pagosServiciosData.getSimbolo() + " -" + decim)));
            }
        }
    }

}
