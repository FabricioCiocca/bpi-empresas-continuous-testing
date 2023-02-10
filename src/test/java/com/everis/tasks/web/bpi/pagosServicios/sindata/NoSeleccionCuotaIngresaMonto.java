package com.everis.tasks.web.bpi.pagosServicios.sindata;

import com.everis.stepsdefinitions.web.bpi.login.LoginStepDefinitions;
import com.everis.userinterfaces.web.bpi.LoginPage;
import lombok.SneakyThrows;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.By;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class NoSeleccionCuotaIngresaMonto implements Task {
    @SneakyThrows
    @Override
    public <T extends Actor> void performAs(T actor) {
        Target SIMBOLO_MONTO = Target.the("Simbolo").located(By.xpath("(//span[@class='mr-1'])[2]"));
        Target modoPago = Target.the("Modo Pago").located(By.xpath("(//div[@class='mat-select-value']/span)[4]"));
        actor.attemptsTo(WaitUntil.the(LoginPage.INP_DESCRIPCION, isVisible()).forNoMoreThan(150).seconds(), Enter.theValue(LoginStepDefinitions.pagosServiciosData.getDescripcion()).into(LoginPage.INP_DESCRIPCION));
        LoginStepDefinitions.pagosServiciosData.setModoPago(String.valueOf(modoPago.resolveFor(actor).getText()));
        LoginStepDefinitions.pagosServiciosData.setSimbolo(String.valueOf(SIMBOLO_MONTO.resolveFor(actor).getText()));
        LoginStepDefinitions.pagosServiciosData.setMonto(String.valueOf(Double.parseDouble(LoginStepDefinitions.pagosServiciosData.getMontoIncial()) * Integer.parseInt(LoginStepDefinitions.pagosServiciosData.getCantdni())));
        actor.attemptsTo(WaitUntil.the(LoginPage.BTN_CONINUE, isVisible()).forNoMoreThan(150).seconds(), Click.on(LoginPage.BTN_CONINUE));

    }
}
