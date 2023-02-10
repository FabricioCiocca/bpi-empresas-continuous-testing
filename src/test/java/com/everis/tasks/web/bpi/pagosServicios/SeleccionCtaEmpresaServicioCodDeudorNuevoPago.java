package com.everis.tasks.web.bpi.pagosServicios;

import com.everis.bpi.stepsdefinitions.web.bpi.login.LoginStepDefinitions;
import com.everis.bpi.userinterface.web.bpi.RealizaPagoPage;
import lombok.SneakyThrows;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.By;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class SeleccionCtaEmpresaServicioCodDeudorNuevoPago implements Task {


    @SneakyThrows
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(WaitUntil.the(RealizaPagoPage.INP_CHOOSE_ACCOUNT, isClickable()).forNoMoreThan(150).seconds(), Click.on(RealizaPagoPage.INP_CHOOSE_ACCOUNT));
        Serenity.getDriver().findElement(By.xpath("//div[contains(text(),'" + LoginStepDefinitions.pagosServiciosData.getCuentaOrigen2SinData() + "')]")).click();
        actor.attemptsTo(WaitUntil.the(RealizaPagoPage.INP_TYPE_COMPANY, isVisible()).forNoMoreThan(150).seconds(), Enter.theValue(LoginStepDefinitions.pagosServiciosData.getEmpresa()).into(RealizaPagoPage.INP_TYPE_COMPANY));
        actor.attemptsTo(WaitUntil.the(RealizaPagoPage.INP_CHOOSE_COMPANY, isVisible()).forNoMoreThan(150).seconds(), Click.on(RealizaPagoPage.INP_CHOOSE_COMPANY));
        actor.attemptsTo(WaitUntil.the(RealizaPagoPage.CMB_CHOOSE_SERVICE, isVisible()).forNoMoreThan(150).seconds(), Click.on(RealizaPagoPage.CMB_CHOOSE_SERVICE));
        Serenity.getDriver().findElement(By.xpath("//span[contains(text(),'" + LoginStepDefinitions.pagosServiciosData.getServicio() + "') and @class='mat-option-text']")).click();
        actor.attemptsTo(WaitUntil.the(RealizaPagoPage.INP_CODDEUDOR, isVisible()).forNoMoreThan(150).seconds(), Enter.theValue(LoginStepDefinitions.pagosServiciosData.getDniPagador()).into(RealizaPagoPage.INP_CODDEUDOR));
    }

}
