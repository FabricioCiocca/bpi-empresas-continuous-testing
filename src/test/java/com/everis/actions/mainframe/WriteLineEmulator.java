package com.everis.actions.mainframe;

import mainframe.com.bdd.lib.EmulatorActions;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Performable;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class WriteLineEmulator extends EmulatorActions implements Interaction {
    private final String text;

    public WriteLineEmulator(String text) {
        this.text = text;
    }
    public static Performable theValue(String text){
        return instrumented(WriteLineEmulator.class,text);
    }
    @Step("{0} write")
    @Override
    public <T extends Actor> void performAs(T actor) {
        getInstancia().escribirLinea(text);
    }
}
