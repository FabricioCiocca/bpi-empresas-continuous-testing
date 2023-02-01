package com.everis.tasks.web.bpi.cerrar;

import com.everis.userinterfaces.web.bpi.LoginPage;
import lombok.SneakyThrows;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

/**
 * @author Nilo Carrion
 */
public class CerrarSesionPendiente implements Task {

    @SneakyThrows
    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                (WaitUntil.the(LoginPage.BUSCAR_CERRAR_SESION, isVisible()).forNoMoreThan(150).seconds()),
                Click.on(LoginPage.BUSCAR_CERRAR_SESION));

        actor.attemptsTo(
                WaitUntil.the(LoginPage.BTN_CERRAR_SESION, isVisible()).forNoMoreThan(150).seconds(),
                Click.on(LoginPage.BTN_CERRAR_SESION));

        actor.attemptsTo(
                WaitUntil.the(LoginPage.BTN_CONT_CERRAR_SESION, isVisible()).forNoMoreThan(150).seconds(),
                Click.on(LoginPage.BTN_CONT_CERRAR_SESION));

    }
}
