package com.everis.tasks.web.bpi.pagosServicios.dataPendiente;

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

public class IngresarCredencialesPagoPendiente implements Task {

    private final String contrasena;

    private final String token;

    public IngresarCredencialesPagoPendiente(String contrasena, String token) {
        this.contrasena = contrasena;
        this.token = token;
    }

    public static Performable withData(String contrasena, String token) {
        return instrumented(IngresarCredencialesPagoPendiente.class, contrasena, token);
    }

    @SneakyThrows
    @Override
    public <T extends Actor> void performAs(T actor) {

        Thread.sleep(2000);

        actor.attemptsTo(
                WaitUntil.the(LoginPage.INP_CONTRASENA, isVisible()).forNoMoreThan(150).seconds(),
                Enter.theValue(contrasena).into(LoginPage.INP_CONTRASENA));

        Thread.sleep(1500);

        actor.attemptsTo(
                WaitUntil.the(LoginPage.INP_TOKEN, isVisible()).forNoMoreThan(150).seconds(),
                Enter.theValue(token).into(LoginPage.INP_TOKEN));

        Thread.sleep(1500);

        actor.attemptsTo(
                WaitUntil.the(LoginPage.BTN_FINALIZAR, isVisible()).forNoMoreThan(150).seconds(),
                Click.on(LoginPage.BTN_FINALIZAR));

        Thread.sleep(4000);


    }

}