package com.everis.tasks.mainframe;

import com.everis.bpi.actions.mainframe.KeyboardFunction;
import com.everis.bpi.actions.mainframe.WaitEmulator;
import mainframe.com.bdd.lib.EmulatorActions;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

public class VisualizarVentanaAnterior extends EmulatorActions implements Task {
    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                WaitEmulator.withMiliseconds(2500),
                KeyboardFunction.theValue(3));
    }
}
