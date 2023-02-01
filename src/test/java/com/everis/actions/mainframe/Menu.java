package com.everis.actions.mainframe;

import mainframe.com.bdd.lib.EmulatorActions;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;

public class Menu extends EmulatorActions implements Interaction {

    @Override
    public <T extends Actor> void performAs(T actor) {
        getInstancia().menu();
    }
}
