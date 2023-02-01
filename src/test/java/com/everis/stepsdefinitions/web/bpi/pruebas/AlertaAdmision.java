package com.everis.stepsdefinitions.web.bpi.pruebas;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Switch;

public class AlertaAdmision implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Switch.toAlert()
        );
    }
}
