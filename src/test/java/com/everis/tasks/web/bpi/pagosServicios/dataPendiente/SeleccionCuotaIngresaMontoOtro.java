package com.everis.tasks.web.bpi.pagosServicios.dataPendiente;

import com.everis.bpi.questions.web.bpi.EstadoPagoQuestions;
import com.everis.bpi.stepsdefinitions.web.bpi.login.LoginStepDefinitions;
import com.everis.bpi.userinterface.web.bpi.PagoRealizadoPage;
import lombok.SneakyThrows;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.By;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.hamcrest.Matchers.equalTo;

public class SeleccionCuotaIngresaMontoOtro implements Task {


    @SneakyThrows
    @Override
    public <T extends Actor> void performAs(T actor) {

        String[] dniPagadorArray = LoginStepDefinitions.pagosServiciosData.getDniPagador().split("-");

        LoginStepDefinitions.pagosServiciosData.setCuotasPagar(String.valueOf(dniPagadorArray.length));

        LoginStepDefinitions.pagosServiciosData.setMonto("0");

        LoginStepDefinitions.pagosServiciosData.setmontoTipoCambio("0");

        //FILA DE SERVICIOS---------------------------------------------------------------------------------------------

        Target SIMBOLO_MONTO = Target.the("Simbolo").located(By.xpath("(//span[@class='mr-1'])[2]"));
        LoginStepDefinitions.pagosServiciosData.setSimbolo(String.valueOf(SIMBOLO_MONTO.resolveFor(actor).getText()));

        Target modoPago = Target.the("Modo Pago").located(By.xpath("(//div[@class='mat-select-value']/span)[4]"));
        LoginStepDefinitions.pagosServiciosData.setModoPago(String.valueOf(modoPago.resolveFor(actor).getText()));

        for (int i = 1; dniPagadorArray.length >= i; i++) {

            Serenity.getDriver().findElement(By.xpath("(//ibk-card[" + i + "]//ibk-table-cell[1]/ibk-checkbox/mat-checkbox)[2]")).click();

            //MONTO INICIAL

            Serenity.getDriver().findElement(By.xpath("//ibk-card[" + i + "]//ibk-table//ibk-input//input")).
                    sendKeys(LoginStepDefinitions.pagosServiciosData.getMontoIncial());

            System.out.println("el monto inicial es: " + LoginStepDefinitions.pagosServiciosData.getMontoIncial());

            //----------------------------------------------------------------------------------------------------------

            String[] tipoCuenta = LoginStepDefinitions.pagosServiciosData.getTipoDeCuenta().trim().split(" ");

            if (tipoCuenta[1].equals("Soles") && LoginStepDefinitions.pagosServiciosData.getSimbolo().equals("$")) {

                LoginStepDefinitions.pagosServiciosData.setmontoPagadoTipoCambio(String.valueOf(String.format("%.2f",
                        Double.parseDouble(LoginStepDefinitions.pagosServiciosData.getMontoIncial()) *
                                Double.parseDouble(LoginStepDefinitions.pagosServiciosData.getTipoDeCambioVenta()))));

                LoginStepDefinitions.pagosServiciosData.setmontoTipoCambio(String.valueOf(String.format("%.2f",
                        Double.parseDouble(LoginStepDefinitions.pagosServiciosData.getmontoPagadoTipoCambio()) *
                                Double.parseDouble(LoginStepDefinitions.pagosServiciosData.getCantdni()))));

            } else if (tipoCuenta[1].equals("D??lares") && LoginStepDefinitions.pagosServiciosData.getSimbolo().equals("S/")) {

                LoginStepDefinitions.pagosServiciosData.setmontoPagadoTipoCambio(String.valueOf(String.format("%.2f",
                        Double.parseDouble(LoginStepDefinitions.pagosServiciosData.getMontoIncial()) /
                                Double.parseDouble(LoginStepDefinitions.pagosServiciosData.getTipoDeCambioCompra()))));

                LoginStepDefinitions.pagosServiciosData.setmontoTipoCambio(String.valueOf(String.format("%.2f",
                        Double.parseDouble(LoginStepDefinitions.pagosServiciosData.getmontoPagadoTipoCambio()) *
                                Double.parseDouble(LoginStepDefinitions.pagosServiciosData.getCantdni()))));

            }

            //----------------------------------------------------------------------------------------------------------

            //MONTOS PAGADOS

            LoginStepDefinitions.pagosServiciosData.setmontosPagados(String.valueOf(String.format("%.2f",
                    Double.parseDouble(LoginStepDefinitions.pagosServiciosData.getMontoIncial()))));

            //MONTO TOTAL

            LoginStepDefinitions.pagosServiciosData.setMonto(String.valueOf(String.format("%.2f",
                    Double.parseDouble(LoginStepDefinitions.pagosServiciosData.getmontosPagados()) *
                            Double.parseDouble(LoginStepDefinitions.pagosServiciosData.getCantdni()))));

            System.out.println("el monto pagado tipo de cambio es: " + LoginStepDefinitions.pagosServiciosData.getmontoPagadoTipoCambio());
            System.out.println("el monto tipo de cambio es: " + LoginStepDefinitions.pagosServiciosData.getmontoTipoCambio());
            System.out.println("el monto pagado es: " + LoginStepDefinitions.pagosServiciosData.getmontosPagados());
            System.out.println("el monto es: " + LoginStepDefinitions.pagosServiciosData.getMonto());

        }

        if (LoginStepDefinitions.pagosServiciosData.getmontosPagados().length() > 6) {

            LoginStepDefinitions.pagosServiciosData.setmontosPagados(LoginStepDefinitions.pagosServiciosData.getSimbolo() + " " + LoginStepDefinitions.pagosServiciosData.getmontosPagados().substring(0, LoginStepDefinitions.pagosServiciosData.getmontosPagados().length() - 6) + "," + LoginStepDefinitions.pagosServiciosData.getmontosPagados().substring(LoginStepDefinitions.pagosServiciosData.getmontosPagados().length() - 6));

            System.out.println("el monto pagado final en moneda es: " + LoginStepDefinitions.pagosServiciosData.getmontosPagados());

        } else if (LoginStepDefinitions.pagosServiciosData.getmontosPagados().length() <= 6) {

            LoginStepDefinitions.pagosServiciosData.setmontosPagados(LoginStepDefinitions.pagosServiciosData.getSimbolo() + " " + LoginStepDefinitions.pagosServiciosData.getmontosPagados());

            System.out.println("el monto pagado final en moneda es: " + LoginStepDefinitions.pagosServiciosData.getmontosPagados());

        }

        if (LoginStepDefinitions.pagosServiciosData.getMonto().length() > 6) {

            LoginStepDefinitions.pagosServiciosData.setMonto(LoginStepDefinitions.pagosServiciosData.getSimbolo() + " " + LoginStepDefinitions.pagosServiciosData.getMonto().substring(0, LoginStepDefinitions.pagosServiciosData.getMonto().length() - 6) + "," + LoginStepDefinitions.pagosServiciosData.getMonto().substring(LoginStepDefinitions.pagosServiciosData.getMonto().length() - 6));

            System.out.println("el monto total en moneda es: " + LoginStepDefinitions.pagosServiciosData.getMonto());

        } else if (LoginStepDefinitions.pagosServiciosData.getMonto().length() <= 6) {

            LoginStepDefinitions.pagosServiciosData.setMonto(LoginStepDefinitions.pagosServiciosData.getSimbolo() + " " + LoginStepDefinitions.pagosServiciosData.getMonto());

            System.out.println("el monto total en moneda es: " + LoginStepDefinitions.pagosServiciosData.getMonto());

        }

        actor.attemptsTo(
                WaitUntil.the(PagoRealizadoPage.INP_DESCRIPCION, isVisible()).forNoMoreThan(150).seconds(),
                Enter.theValue(LoginStepDefinitions.pagosServiciosData.getDescripcion()).into(PagoRealizadoPage.INP_DESCRIPCION));

        theActorInTheSpotlight().should(seeThat(
                EstadoPagoQuestions.CuotasProcesadas(), equalTo(LoginStepDefinitions.pagosServiciosData.getCuotasPagar())));

        actor.attemptsTo(
                WaitUntil.the(PagoRealizadoPage.BTN_CONINUE, isVisible()).forNoMoreThan(150).seconds(),
                Click.on(PagoRealizadoPage.BTN_CONINUE));

    }
}
