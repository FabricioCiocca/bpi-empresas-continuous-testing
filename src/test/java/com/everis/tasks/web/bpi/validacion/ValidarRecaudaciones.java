package com.everis.tasks.web.bpi.validacion;

import com.everis.bpi.stepsdefinitions.web.bpi.login.LoginStepDefinitions;
import com.everis.bpi.userinterface.web.bpi.LoginPage;
import com.everis.bpi.userinterface.web.bpi.SaldosYMovimientosPage;
import lombok.SneakyThrows;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class ValidarRecaudaciones implements Task {
    public final String service;
    public final String buscarcod;

    public ValidarRecaudaciones(String service, String buscarcod) {
        this.service = service;
        this.buscarcod = buscarcod;
    }

    public static Performable withData(String service, String buscarcod) {
        return instrumented(ValidarRecaudaciones.class, service, buscarcod);
    }

    @SneakyThrows
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(WaitUntil.the(SaldosYMovimientosPage.MENU_CONSULTAS, isVisible()).forNoMoreThan(50).seconds(),
                Click.on(SaldosYMovimientosPage.MENU_CONSULTAS));
        actor.attemptsTo(WaitUntil.the(LoginPage.RECAUDACIONES, isVisible()).forNoMoreThan(50).seconds(),
                Click.on(LoginPage.RECAUDACIONES));
        actor.attemptsTo(WaitUntil.the(LoginPage.CHOOSE_SERVICE, isVisible()).forNoMoreThan(50).seconds(),
                Click.on(LoginPage.CHOOSE_SERVICE));
        Serenity.getDriver().findElement(By.xpath("//mat-option[@role='option']//span[text()='" + service + "']")).click();
        actor.attemptsTo(WaitUntil.the(LoginPage.CHOOSE_COD_DEUDOR, isVisible()).forNoMoreThan(50).seconds(),
                Click.on(LoginPage.CHOOSE_COD_DEUDOR));
        Serenity.getDriver().findElement(By.xpath("//mat-option[@role='option']//span[text()='" + buscarcod + "']")).click();
        if (buscarcod.equalsIgnoreCase("Código de deudor")) {
            actor.attemptsTo(WaitUntil.the(LoginPage.INP_BUSCAR_COD_DEUDOR, isVisible()).forNoMoreThan(50).seconds(),
                    Click.on(LoginPage.INP_BUSCAR_COD_DEUDOR), Enter.theValue(LoginStepDefinitions.pagosServiciosData.getDniPagador()).into(LoginPage.INP_BUSCAR_COD_DEUDOR));
            actor.attemptsTo(WaitUntil.the(LoginPage.BTN_SEARCH, isVisible()).forNoMoreThan(50).seconds(),
                    Click.on(LoginPage.BTN_SEARCH));

        } else {
            actor.attemptsTo(WaitUntil.the(LoginPage.BTN_SEARCH, isVisible()).forNoMoreThan(50).seconds(), Click.on(LoginPage.BTN_SEARCH));
            JavascriptExecutor js = (JavascriptExecutor) Serenity.getDriver();
            js.executeScript("arguments[0].scrollIntoView();", Serenity.getDriver().findElement(By.xpath("//div[@data-test='lnkSeePay']//span[text()='Ver pagos']")));
            actor.attemptsTo(WaitUntil.the(LoginPage.VER_PAGO, isVisible()).forNoMoreThan(50).seconds(), Click.on(LoginPage.VER_PAGO));

        }

        LoginStepDefinitions.pagosServiciosData.setcodigo("");
        int cont = 0;
        while (!LoginStepDefinitions.pagosServiciosData.getcodigo().contains(LoginStepDefinitions.pagosServiciosData.getDniPagador())) {
            cont++;

            if (cont > 8) {
                JavascriptExecutor js = (JavascriptExecutor) Serenity.getDriver();
                js.executeScript("arguments[0].scrollIntoView();", Serenity.getDriver().findElement(By.xpath("(//*[@class='ibk-table-row ng-star-inserted'][" + 9 + "]//ibk-table-cell-content)[3]")));
            }
            Target CODIGO = Target.the("Codigo").located(By.xpath("(//*[@class='ibk-table-row ng-star-inserted'][" + cont + "]//ibk-table-cell-content)[3]"));
            LoginStepDefinitions.pagosServiciosData.setcodigo(String.valueOf(CODIGO.resolveFor(actor).getText()));

        }
        Target Fecha_Operacion = Target.the("Fecha Operacion").located(By.xpath("(//*[@ng-reflect-key='payDate'])[" + cont + "]"));
        LoginStepDefinitions.pagosServiciosData.setFechaRecaudacion(String.valueOf(Fecha_Operacion.resolveFor(actor).getText()));
        String[] fechaHoraAux = LoginStepDefinitions.pagosServiciosData.getFechaRecaudacion().split("-");
        LoginStepDefinitions.pagosServiciosData.setFechaRecaudacion(fechaHoraAux[0]);
        Target Numero_Operación = Target.the("Nr Operacion").located(By.xpath("(//*[@ng-reflect-key='operationNumber'])[" + cont + "]"));
        LoginStepDefinitions.pagosServiciosData.setNumeroOperacion(String.valueOf(Numero_Operación.resolveFor(actor).getText()));
//        Target DETAIL = Target.the("Detalle de Recaudación").located(By.xpath("(//*[@ng-reflect-key='detail']//ibk-icon)["+cont+"]"));
//        LoginStepDefinitions.pagosServiciosData.setDetallerecaudacion(String.valueOf(DETAIL.resolveFor(actor).getText()));
        Serenity.getDriver().findElement(By.xpath("(//*[@ng-reflect-key='detail']//ibk-icon)[" + cont + "]")).click();

        //       Click.on(LoginStepDefinitions.pagosServiciosData.getDetallerecaudacion());
    }
}
