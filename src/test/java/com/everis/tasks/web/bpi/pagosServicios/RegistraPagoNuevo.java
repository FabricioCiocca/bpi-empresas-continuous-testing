package com.everis.tasks.web.bpi.pagosServicios;

import com.everis.bpi.userinterface.web.bpi.LoginPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class RegistraPagoNuevo implements Task {


    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(LoginPage.BTN_NUEVO_PAGO, isVisible()).forNoMoreThan(150).seconds(),
                Click.on(LoginPage.BTN_NUEVO_PAGO)
        );

    }
}
