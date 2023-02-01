package com.everis.tasks.mainframe;

import com.everis.actions.mainframe.Emulator;
import com.everis.actions.mainframe.WaitEmulator;
import com.everis.actions.mainframe.WriteEmulator;
import com.everis.questions.mainframe.ValidacionCobros;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

public class LimpiarAcceso implements Task {
    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                WaitEmulator.withMiliseconds(2000),
                WriteEmulator.theValue("logoff"),
                Emulator.withPrints(Emulator.ActionsPrints.PRINT),
                Emulator.withPrints(Emulator.ActionsPrints.ENTER),
                Emulator.withPrints(Emulator.ActionsPrints.PRINT)
        );

        /*Codigo para mostrar paso a paso en Mainframe*/
        Serenity.recordReportData().withTitle("Mainframe Evidence")
                .andContents(actor.asksFor(ValidacionCobros.GetStringByPosition(0,0,24,79)).substring(0,1942));

        actor.attemptsTo(
                Emulator.withPrints(Emulator.ActionsPrints.ENTER));
    }
}
