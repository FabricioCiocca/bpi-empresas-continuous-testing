package com.everis.actions.mainframe;

import mainframe.com.bdd.lib.EmulatorActions;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Performable;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class WaitEmulator extends EmulatorActions implements Interaction {

    private final int miliseconds;

    public WaitEmulator(int miliseconds) {
        this.miliseconds = miliseconds;
    }

    public static Performable withMiliseconds(int miliseconds){
        return instrumented(WaitEmulator.class, miliseconds);
    }

    @Step("{0} waits")
    @Override
    public <T extends Actor> void performAs(T actor) {
        getInstancia().esperar(miliseconds);
    }
}
