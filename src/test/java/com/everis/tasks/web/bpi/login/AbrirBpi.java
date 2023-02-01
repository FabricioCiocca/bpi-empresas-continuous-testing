package com.everis.tasks.web.bpi.login;

import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.util.EnvironmentVariables;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * @author Nilo Carrion
 */
public class AbrirBpi implements Task {

    private final String url;
    EnvironmentVariables environmentVariables;

    public AbrirBpi(String url) {
        this.url = url;
    }

    public static Task loginBpiPage() {
        String url = "bpi.page";
        return instrumented(AbrirBpi.class, url);
    }

    @Override
    @Step("{0} Inicia la página #url")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.url(EnvironmentSpecificConfiguration.from(environmentVariables).getProperty(url))
        );
    }

}
