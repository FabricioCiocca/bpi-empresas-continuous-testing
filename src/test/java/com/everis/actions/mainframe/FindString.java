package com.everis.actions.mainframe;

import mainframe.com.bdd.lib.EmulatorActions;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Performable;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class FindString extends EmulatorActions implements Interaction {
    public final String text;

    public FindString(String text) {
        this.text = text;
    }
    public static Performable theValue(String text){
        return instrumented(FindString.class,text);
    }

    @Step("{0} write")
    @Override
    public <T extends Actor> void performAs(T actor) {
        getInstancia().buscarCadena(text);
    }
}
