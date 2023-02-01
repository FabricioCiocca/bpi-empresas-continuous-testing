package com.everis.tasks.web.bpi.pagosServicios.sindata;

import com.everis.stepsdefinitions.web.bpi.login.LoginStepDefinitions;
import com.everis.tasks.web.bpi.validacion.ValidarMovimientoPendiente;
import com.everis.userinterfaces.web.bpi.LoginPage;
import lombok.SneakyThrows;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.By;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class ValidarMovimientosFC implements Task {
    private final String empresa;
    private final String monto;

    public ValidarMovimientosFC(String empresa, String monto) {
        this.empresa = empresa;
        this.monto = monto;
    }

    public static Performable withData(String monto, String empresa) {
        return instrumented(ValidarMovimientoPendiente.class, monto, empresa);
    }

    @SneakyThrows
    @Override
    public <T extends Actor> void performAs(T actor) {
        Thread.sleep(2000);
        actor.attemptsTo(WaitUntil.the(LoginPage.MENU_CONSULTAS, isVisible()).forNoMoreThan(150).seconds(), Click.on(LoginPage.MENU_CONSULTAS));
        Thread.sleep(3000);
        actor.attemptsTo(WaitUntil.the(LoginPage.MENU_MOVIMIENTOS, isVisible()).forNoMoreThan(150).seconds(), Click.on(LoginPage.MENU_MOVIMIENTOS));
        Thread.sleep(2000);
        actor.attemptsTo(WaitUntil.the(LoginPage.CMB_ELEGIR_CUENTA, isVisible()).forNoMoreThan(150).seconds(), Click.on(LoginPage.CMB_ELEGIR_CUENTA));
        Thread.sleep(2000);
        Serenity.getDriver().findElement(By.xpath("//div[contains(text(),'" + LoginStepDefinitions.pagosServiciosData.getCuentaOrigen() + "')]")).click();
        Thread.sleep(2000);
        actor.attemptsTo(WaitUntil.the(LoginPage.BTN_BUSCAR_MOVIMIENTOS, isVisible()).forNoMoreThan(150).seconds(), Click.on(LoginPage.BTN_BUSCAR_MOVIMIENTOS));
        String[] saldoInicial = LoginStepDefinitions.pagosServiciosData.getSaldoInicial().trim().split(" ");
        if (Double.parseDouble(LoginStepDefinitions.pagosServiciosData.getmontoTipoCambio()) > 0) {
            if (LoginStepDefinitions.pagosServiciosData.getmontoPagadoTipoCambio().length() > 6) {
                LoginStepDefinitions.pagosServiciosData.setmontosPagados(saldoInicial[0] + " -" + LoginStepDefinitions.pagosServiciosData.getmontoPagadoTipoCambio().substring(0, LoginStepDefinitions.pagosServiciosData.getmontoPagadoTipoCambio().length() - 6) + "," + LoginStepDefinitions.pagosServiciosData.getmontoPagadoTipoCambio().substring(LoginStepDefinitions.pagosServiciosData.getmontoPagadoTipoCambio().length() - 6));
            } else {
                LoginStepDefinitions.pagosServiciosData.setmontosPagados(saldoInicial[0] + " -" + LoginStepDefinitions.pagosServiciosData.getmontoPagadoTipoCambio());
            }
        } else {
            LoginStepDefinitions.pagosServiciosData.setmontosPagados(LoginStepDefinitions.pagosServiciosData.getmontosPagados().replace(LoginStepDefinitions.pagosServiciosData.getSimbolo() + " ", "").replace(",", ""));
            if (LoginStepDefinitions.pagosServiciosData.getmontosPagados().length() > 6) {
                LoginStepDefinitions.pagosServiciosData.setmontosPagados(LoginStepDefinitions.pagosServiciosData.getSimbolo() + " -" + LoginStepDefinitions.pagosServiciosData.getmontosPagados().substring(0, LoginStepDefinitions.pagosServiciosData.getmontosPagados().length() - 6) + "," + LoginStepDefinitions.pagosServiciosData.getmontosPagados().substring(LoginStepDefinitions.pagosServiciosData.getmontosPagados().length() - 6));
            } else {
                LoginStepDefinitions.pagosServiciosData.setmontosPagados(LoginStepDefinitions.pagosServiciosData.getSimbolo() + " -" + LoginStepDefinitions.pagosServiciosData.getmontosPagados());
            }
        }
        int i = Integer.parseInt(LoginStepDefinitions.pagosServiciosData.getCantdni()) + 1;//30
        while (!LoginStepDefinitions.pagosServiciosData.getmontosPagados().equals(LoginStepDefinitions.pagosServiciosData.getmontoPagado())) {
            i--;
            Target IMPORTE = Target.the("Importe").located(By.xpath("(//*[@class='ibk-table-row ng-star-inserted'][" + i + "]//span)[2]"));
            LoginStepDefinitions.pagosServiciosData.setmontoPagado(String.valueOf(IMPORTE.resolveFor(actor).getText()));
        }
        String[] fechaHoraAux = LoginStepDefinitions.pagosServiciosData.getFechaHora().split("-");
        Target FECHA_OPERACION = Target.the("Fecha Operacion").located(By.xpath("(//*[@class='ibk-table-row ng-star-inserted'][" + i + "]//div)[1]"));
        LoginStepDefinitions.pagosServiciosData.setfechaOperacionM(String.valueOf(FECHA_OPERACION.resolveFor(actor).getText()));
        LoginStepDefinitions.pagosServiciosData.getfechaOperacionM().equals(fechaHoraAux[0].trim());
        Target MOVIMIENTO = Target.the("Movimiento").located(By.xpath("(//*[@class='ibk-table-row ng-star-inserted'][" + i + "]//div)[4]"));
        LoginStepDefinitions.pagosServiciosData.setmovimientoM(String.valueOf(MOVIMIENTO.resolveFor(actor).getText()));
        LoginStepDefinitions.pagosServiciosData.getmovimientoM().equals("PAGO DE SERVICIOS");
        Target DESCRIPCION = Target.the("Descripcion").located(By.xpath("(//*[@class='ibk-table-row ng-star-inserted'][" + i + "]//div)[5]"));
        LoginStepDefinitions.pagosServiciosData.setdescricionM(String.valueOf(DESCRIPCION.resolveFor(actor).getText()));
        LoginStepDefinitions.pagosServiciosData.getdescricionM().contains(LoginStepDefinitions.pagosServiciosData.getEmpresa());
    }
}
