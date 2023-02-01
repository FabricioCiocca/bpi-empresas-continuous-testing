package com.everis.actions.mainframe;

import mainframe.com.bdd.lib.EmulatorActions;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.thucydides.core.annotations.Step;

public class Tab extends EmulatorActions implements Interaction {
    @Step("{0} key")
    @Override
    public <T extends Actor> void performAs(T actor) {
        getInstancia().tab();
    }
}
