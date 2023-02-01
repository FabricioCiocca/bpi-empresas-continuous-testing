package com.everis.tasks.web.bpi.pagosServicios;

import com.everis.stepsdefinitions.web.bpi.login.LoginStepDefinitions;
import com.everis.userinterfaces.web.bpi.LoginPage;
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

public class NuevoServicio implements Task {
    @SneakyThrows
    @Override
    public <T extends Actor> void performAs(T actor) {
        Thread.sleep(3000);
        actor.attemptsTo(WaitUntil.the(LoginPage.INP_CHOOSE_ACCOUNT, isClickable()).forNoMoreThan(150).seconds(), Click.on(LoginPage.INP_CHOOSE_ACCOUNT));
        Thread.sleep(1000);
        Serenity.getDriver().findElement(By.xpath("//div[contains(text(),'" + LoginStepDefinitions.pagosServiciosData.getCuentaOrigen() + "')]")).click();
        Thread.sleep(3000);
        actor.attemptsTo(WaitUntil.the(LoginPage.INP_TYPE_COMPANY, isVisible()).forNoMoreThan(150).seconds(), Enter.theValue(LoginStepDefinitions.pagosServiciosData.getEmpresa()).into(LoginPage.INP_TYPE_COMPANY));
        Thread.sleep(1500);
        actor.attemptsTo(WaitUntil.the(LoginPage.INP_CHOOSE_COMPANY, isVisible()).forNoMoreThan(150).seconds(), Click.on(LoginPage.INP_CHOOSE_COMPANY));
        Thread.sleep(1500);
        actor.attemptsTo(WaitUntil.the(LoginPage.CMB_CHOOSE_SERVICE, isVisible()).forNoMoreThan(150).seconds(), Click.on(LoginPage.CMB_CHOOSE_SERVICE));
        Thread.sleep(2000);
        Serenity.getDriver().findElement(By.xpath("//span[contains(text(),'" + LoginStepDefinitions.pagosServiciosData.getServicio2SinData() + "') and @class='mat-option-text']")).click();
        actor.attemptsTo(WaitUntil.the(LoginPage.INP_CODDEUDOR, isVisible()).forNoMoreThan(150).seconds(), Enter.theValue(LoginStepDefinitions.pagosServiciosData.getDniPagador()).into(LoginPage.INP_CODDEUDOR));
    }
}
