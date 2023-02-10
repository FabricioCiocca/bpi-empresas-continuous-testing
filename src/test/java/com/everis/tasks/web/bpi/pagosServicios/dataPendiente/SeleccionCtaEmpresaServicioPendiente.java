package com.everis.tasks.web.bpi.pagosServicios.dataPendiente;

import com.everis.userinterfaces.web.bpi.LoginPage;
import lombok.SneakyThrows;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class SeleccionCtaEmpresaServicioPendiente implements Task {
    private final String cuenta;
    private final String empresa;
    private final String servicio;

    public SeleccionCtaEmpresaServicioPendiente(String cuenta, String empresa, String servicio) {
        this.cuenta = cuenta;
        this.empresa = empresa;
        this.servicio = servicio;
    }

    public static Performable withData(String cuenta, String empresa, String servicio) {
        return instrumented(SeleccionCtaEmpresaServicioPendiente.class, cuenta, empresa, servicio);
    }

    @SneakyThrows
    @Override
    @Step("{0} {0} Ingresamos datos de Empresa")
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                WaitUntil.the(LoginPage.BTN_NUEVO_PAGO, isClickable()).forNoMoreThan(150).seconds(),
                Click.on(LoginPage.BTN_NUEVO_PAGO));

        actor.attemptsTo(
                WaitUntil.the(LoginPage.INP_CHOOSE_ACCOUNT, isClickable()).forNoMoreThan(150).seconds(),
                Click.on(LoginPage.INP_CHOOSE_ACCOUNT));

        Serenity.getDriver().findElement(By.xpath("//div[contains(text(),'" + cuenta + "')]")).click();

        actor.attemptsTo(
                WaitUntil.the(LoginPage.INP_TYPE_COMPANY, isVisible()).forNoMoreThan(150).seconds(),
                Enter.theValue(empresa).into(LoginPage.INP_TYPE_COMPANY));

        actor.attemptsTo(
                WaitUntil.the(LoginPage.INP_CHOOSE_COMPANY, isVisible()).forNoMoreThan(150).seconds(),
                Click.on(LoginPage.INP_CHOOSE_COMPANY));

        actor.attemptsTo(
                WaitUntil.the(LoginPage.CMB_CHOOSE_SERVICE, isVisible()).forNoMoreThan(150).seconds(),
                Click.on(LoginPage.CMB_CHOOSE_SERVICE));

        Serenity.getDriver().findElement(By.xpath("//span[contains(text(),'" + servicio + "') and @class='mat-option-text']")).click();

    }

}
