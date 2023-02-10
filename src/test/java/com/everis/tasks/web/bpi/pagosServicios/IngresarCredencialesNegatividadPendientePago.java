package com.everis.tasks.web.bpi.pagosServicios;

import com.everis.userinterfaces.web.bpi.LoginPage;
import lombok.SneakyThrows;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class IngresarCredencialesNegatividadPendientePago implements Task {

    private final String contrasena;

    private final String token;

    public IngresarCredencialesNegatividadPendientePago(String contrasena, String token) {
        this.contrasena = contrasena;
        this.token = token;
    }

    public static Performable withData(String contrasena, String token) {
        return instrumented(IngresarCredencialesNegatividadPendientePago.class, contrasena, token);
    }

    @SneakyThrows
    @Override
    public <T extends Actor> void performAs(T actor) {


        actor.attemptsTo(
                WaitUntil.the(LoginPage.BTN_CONINUE, isVisible()).forNoMoreThan(150).seconds(),
                Click.on(LoginPage.BTN_CONINUE));

        actor.attemptsTo(
                WaitUntil.the(LoginPage.INP_CONTRASENA, isVisible()).forNoMoreThan(150).seconds(),
                Enter.theValue(contrasena).into(LoginPage.INP_CONTRASENA));

    }

}
