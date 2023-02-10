package com.everis.tasks.mainframe;

import com.everis.bpi.actions.mainframe.*;
import com.everis.bpi.questions.mainframe.ValidacionCobros;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

public class CerrarSesionMainframe implements Task {
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitEmulator.withMiliseconds(2500),
                Emulator.withPrints(Emulator.ActionsPrints.WAITINPUTDATA),
                KeyboardFunction.theValue(3),
                WaitEmulator.withMiliseconds(2500),
                Emulator.withPrints(Emulator.ActionsPrints.WAITINPUTDATA),
                KeyboardFunction.theValue(3),
                WaitEmulator.withMiliseconds(2500),
                Emulator.withPrints(Emulator.ActionsPrints.WAITINPUTDATA),
                KeyboardPA.theValue(1),
                Emulator.withPrints(Emulator.ActionsPrints.PRINT),
                WaitEmulator.withMiliseconds(2500),
                KeyboardFunction.theValue(3),
                WaitEmulator.withMiliseconds(2500),
                Emulator.withPrints(Emulator.ActionsPrints.WAITINPUTDATA),
                WriteEmulator.theValue("logoff"),
                Emulator.withPrints(Emulator.ActionsPrints.PRINT)
        );

        /*Codigo para mostrar paso a paso en Mainframe*/
        Serenity.recordReportData().withTitle("Mainframe Evidence")
                .andContents(actor.asksFor(ValidacionCobros.GetStringByPosition(0, 0, 24, 79)).substring(0, 1942));

        actor.attemptsTo(
                Emulator.withPrints(Emulator.ActionsPrints.ENTER),
                Emulator.withPrints(Emulator.ActionsPrints.PRINT),
                KeyboardFunction.theValue(12)
        );
    }
}
