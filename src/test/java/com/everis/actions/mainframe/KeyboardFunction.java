package com.everis.actions.mainframe;

import mainframe.com.bdd.lib.EmulatorActions;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Performable;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;


public class KeyboardFunction extends EmulatorActions implements Interaction {
    public final int key;

    public KeyboardFunction(int key) {
        this.key = key;
    }


    public static Performable theValue(int key) {
        return instrumented(KeyboardFunction.class, key);
    }

    @Step("{0} write")
    @Override
    public <T extends Actor> void performAs(T actor) {
        getInstancia().teclaFuncion(key);
    }
}
