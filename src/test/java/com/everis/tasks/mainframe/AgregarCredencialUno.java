package com.everis.tasks.mainframe;

import com.everis.bpi.actions.mainframe.Emulator;
import com.everis.bpi.actions.mainframe.WaitEmulator;
import com.everis.bpi.actions.mainframe.WriteEmulator;
import com.everis.bpi.questions.mainframe.ValidacionCobros;
import environment.ManageEnvironment;
import mainframe.com.bdd.generic.MainframeHelperProperties;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

public class AgregarCredencialUno implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                WaitEmulator.withMiliseconds(2000),
                Emulator.withPrints(Emulator.ActionsPrints.WAITINPUTDATA),
                WriteEmulator.theValue(helpCredencials().getMainframeUser()),
                Emulator.withPrints(Emulator.ActionsPrints.ENTER),
                WaitEmulator.withMiliseconds(2000),
                Emulator.withPrints(Emulator.ActionsPrints.WAITINPUTDATA),
                WriteEmulator.theValue(helpCredencials().getMainframePassword()),
                Emulator.withPrints(Emulator.ActionsPrints.PRINT));

        /*Codigo para mostrar paso a paso en Mainframe*/
        Serenity.recordReportData().withTitle("Mainframe Evidence")
                .andContents(actor.asksFor(ValidacionCobros.GetStringByPosition(0, 0, 24, 79)).substring(0, 1942));

        actor.attemptsTo(
                Emulator.withPrints(Emulator.ActionsPrints.ENTER));
    }

    private MainframeHelperProperties helpCredencials() {
        return new MainframeHelperProperties(ManageEnvironment.getEnvironment());
    }
}
