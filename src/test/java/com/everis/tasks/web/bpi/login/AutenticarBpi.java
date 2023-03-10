package com.everis.tasks.web.bpi.login;

import com.everis.bpi.userinterface.web.bpi.LoginPage;
import lombok.SneakyThrows;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

/**
 * @author Nilo Carrion
 */
public class AutenticarBpi implements Task {

    private final String dni;
    private final String password;

    public AutenticarBpi(String dni, String password) {
        this.dni = dni;
        this.password = password;
    }

    @SneakyThrows
    @Override
    @Step("{0} {0} Ingresamos datos de usuario")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                (WaitUntil.the(LoginPage.INP_CHOOSE_TIPO_ACCESO, isVisible()).forNoMoreThan(150).seconds()),
                Click.on(LoginPage.INP_CHOOSE_TIPO_ACCESO)
        );

        actor.attemptsTo(
                WaitUntil.the(LoginPage.INP_CHOOSE_TIPO_DOC, isVisible()).forNoMoreThan(150).seconds(),
                Click.on(LoginPage.INP_CHOOSE_TIPO_DOC)
        );

        actor.attemptsTo(
                Enter.theValue(dni).into(LoginPage.INP_DOCUMENTO),
                Enter.theValue(password).into(LoginPage.INP_PASSWORD),
                Click.on(LoginPage.BTN_INICIAR_SESION)
        );
    }

    public static Performable withData(String dni, String password) {
        return instrumented(AutenticarBpi.class, dni, password);
    }

}
