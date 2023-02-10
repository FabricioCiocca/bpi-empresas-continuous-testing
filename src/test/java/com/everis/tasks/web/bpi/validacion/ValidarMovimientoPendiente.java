package com.everis.tasks.web.bpi.validacion;

import com.everis.bpi.stepsdefinitions.web.bpi.login.LoginStepDefinitions;
import com.everis.bpi.userinterface.web.bpi.SaldosYMovimientosPage;
import lombok.SneakyThrows;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.junit.Assert;
import org.openqa.selenium.By;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.hamcrest.Matchers.containsString;

public class ValidarMovimientoPendiente implements Task {
    private final String empresa;
    private final String monto;

    public ValidarMovimientoPendiente(String empresa, String monto) {
        this.empresa = empresa;
        this.monto = monto;
    }

    public static Performable withData(String monto, String empresa) {
        return instrumented(ValidarMovimientoPendiente.class, monto, empresa);
    }

    @SneakyThrows
    @Override
    public <T extends Actor> void performAs(T actor) {

        //CONSULTAS ----------------------------------------------------------------------------------------------------------------------------------

        actor.attemptsTo(
                WaitUntil.the(SaldosYMovimientosPage.MENU_CONSULTAS, isVisible()).forNoMoreThan(150).seconds(), Click.on(SaldosYMovimientosPage.MENU_CONSULTAS));

        //MOVIMIENTOS ------------------------------------------------------

        actor.attemptsTo(
                WaitUntil.the(SaldosYMovimientosPage.MENU_MOVIMIENTOS, isVisible()).forNoMoreThan(150).seconds(),
                Click.on(SaldosYMovimientosPage.MENU_MOVIMIENTOS));

        //BUSCAR CUENTA

        actor.attemptsTo(
                WaitUntil.the(SaldosYMovimientosPage.CMB_ELEGIR_CUENTA, isVisible()).forNoMoreThan(150).seconds(),
                Click.on(SaldosYMovimientosPage.CMB_ELEGIR_CUENTA));

        //ELEGIR CUENTA

        Serenity.getDriver().findElement(By.xpath("//div[contains(text(),'" + LoginStepDefinitions.pagosServiciosData.getCuentaOrigen() + "')]")).click();

        //BOTON BUSCAR

        actor.attemptsTo(
                WaitUntil.the(SaldosYMovimientosPage.BTN_BUSCAR_MOVIMIENTOS, isVisible()).forNoMoreThan(150).seconds(),
                Click.on(SaldosYMovimientosPage.BTN_BUSCAR_MOVIMIENTOS));

        System.out.println("MONTO PAGADO: " + LoginStepDefinitions.pagosServiciosData.getmontosPagados());

        String[] saldoInicial = LoginStepDefinitions.pagosServiciosData.getSaldoInicial().trim().split(" ");

        if (Double.parseDouble(LoginStepDefinitions.pagosServiciosData.getmontoTipoCambio()) > 0) {

            if (LoginStepDefinitions.pagosServiciosData.getmontoPagadoTipoCambio().length() > 6) {

                LoginStepDefinitions.pagosServiciosData.setmontosPagados(saldoInicial[0] + " -" +
                        LoginStepDefinitions.pagosServiciosData.getmontoPagadoTipoCambio().substring(0, LoginStepDefinitions.pagosServiciosData.getmontoPagadoTipoCambio().length() - 6) + "," +
                        LoginStepDefinitions.pagosServiciosData.getmontoPagadoTipoCambio().substring(LoginStepDefinitions.pagosServiciosData.getmontoPagadoTipoCambio().length() - 6));

            } else {

                LoginStepDefinitions.pagosServiciosData.setmontosPagados(saldoInicial[0] + " -" + LoginStepDefinitions.pagosServiciosData.getmontoPagadoTipoCambio());

            }
            System.out.println("MONTO PAGADO-----------: " + LoginStepDefinitions.pagosServiciosData.getmontosPagados());

        } else {

            LoginStepDefinitions.pagosServiciosData.setmontosPagados(LoginStepDefinitions.pagosServiciosData.getmontosPagados().replace(LoginStepDefinitions.pagosServiciosData.getSimbolo() + " ", "").replace(",", ""));

            if (LoginStepDefinitions.pagosServiciosData.getmontosPagados().length() > 6) {

                LoginStepDefinitions.pagosServiciosData.setmontosPagados(LoginStepDefinitions.pagosServiciosData.getSimbolo() + " -" +
                        LoginStepDefinitions.pagosServiciosData.getmontosPagados().substring(0, LoginStepDefinitions.pagosServiciosData.getmontosPagados().length() - 6) + "," +
                        LoginStepDefinitions.pagosServiciosData.getmontosPagados().substring(LoginStepDefinitions.pagosServiciosData.getmontosPagados().length() - 6));

            } else {

                LoginStepDefinitions.pagosServiciosData.setmontosPagados(LoginStepDefinitions.pagosServiciosData.getSimbolo() + " -" + LoginStepDefinitions.pagosServiciosData.getmontosPagados());
            }

        }
        System.out.println("MONTO PAGADO-----------: " + LoginStepDefinitions.pagosServiciosData.getmontosPagados());
        int i = Integer.parseInt(LoginStepDefinitions.pagosServiciosData.getCantdni()) + 1;//30

        while (!LoginStepDefinitions.pagosServiciosData.getmontosPagados().equals(LoginStepDefinitions.pagosServiciosData.getmontoPagado())) {

            i--;

            //IMPORTE

            Target IMPORTE = Target.the("Importe").located(By.xpath("(//*[@class='ibk-table-row ng-star-inserted'][" + i + "]//span)[2]"));
            LoginStepDefinitions.pagosServiciosData.setmontoPagado(String.valueOf(IMPORTE.resolveFor(actor).getText()));

            System.out.println(LoginStepDefinitions.pagosServiciosData.getmontoPagado());

            assertEquals(
                    LoginStepDefinitions.pagosServiciosData.getmontoPagado(), LoginStepDefinitions.pagosServiciosData.getmontosPagados());

        }

        //FECHA DE OPERACION

        String[] fechaHoraAux = LoginStepDefinitions.pagosServiciosData.getFechaHora().split("-");

        Target FECHA_OPERACION = Target.the("Fecha Operacion").located(By.xpath("(//*[@class='ibk-table-row ng-star-inserted'][" + i + "]//div)[1]"));
        LoginStepDefinitions.pagosServiciosData.setfechaOperacionM(String.valueOf(FECHA_OPERACION.resolveFor(actor).getText()));

        assertEquals(
                LoginStepDefinitions.pagosServiciosData.getfechaOperacionM(), fechaHoraAux[0].trim());

        //MOVIMIENTO Y DESCRIPCION

        Target MOVIMIENTO = Target.the("Movimiento").located(By.xpath("(//*[@class='ibk-table-row ng-star-inserted'][" + i + "]//div)[4]"));
        LoginStepDefinitions.pagosServiciosData.setmovimientoM(String.valueOf(MOVIMIENTO.resolveFor(actor).getText()));

        Target DESCRIPCION = Target.the("Descripcion").located(By.xpath("(//*[@class='ibk-table-row ng-star-inserted'][" + i + "]//div)[5]"));
        LoginStepDefinitions.pagosServiciosData.setdescricionM(String.valueOf(DESCRIPCION.resolveFor(actor).getText()));

        String[] tipoCuentaAux = LoginStepDefinitions.pagosServiciosData.getTipoDeCuenta().split("\\s+");

        if (tipoCuentaAux[0].equals("Corriente")) {

            if (LoginStepDefinitions.pagosServiciosData.getEmpresa().length() > 13) {

                Assert.assertThat(
                        LoginStepDefinitions.pagosServiciosData.getdescricionM(), containsString(LoginStepDefinitions.pagosServiciosData.getEmpresa().substring(0, 13)));

            } else {

                Assert.assertThat(
                        LoginStepDefinitions.pagosServiciosData.getdescricionM(), containsString(LoginStepDefinitions.pagosServiciosData.getEmpresa()));

            }

            assertEquals(
                    LoginStepDefinitions.pagosServiciosData.getmovimientoM(), "PAGO DE SERVICIOS");

        } else {

            if (LoginStepDefinitions.pagosServiciosData.getEmpresa().length() > 13) {

                Assert.assertThat(
                        LoginStepDefinitions.pagosServiciosData.getmovimientoM(), containsString(LoginStepDefinitions.pagosServiciosData.getEmpresa().substring(0, 13)));

            } else {

                Assert.assertThat(
                        LoginStepDefinitions.pagosServiciosData.getmovimientoM(), containsString(LoginStepDefinitions.pagosServiciosData.getEmpresa()));

            }

        }
    }
}
