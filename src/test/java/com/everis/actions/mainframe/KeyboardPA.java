package com.everis.actions.mainframe;

import mainframe.com.bdd.lib.EmulatorActions;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Performable;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class KeyboardPA extends EmulatorActions implements Interaction {
    public final int key;

    public KeyboardPA(int key) {
        this.key = key;
    }

    public static Performable theValue(int key) {
        return instrumented(KeyboardPA.class, key);
    }

    @Step("{0} write")
    @Override
    public <T extends Actor> void performAs(T actor) {
        getInstancia().teclaPA(key);
    }
}
