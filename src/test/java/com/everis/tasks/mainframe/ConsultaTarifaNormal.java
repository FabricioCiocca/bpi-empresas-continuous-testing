package com.everis.tasks.mainframe;

import com.everis.actions.mainframe.Emulator;
import com.everis.actions.mainframe.WaitEmulator;
import com.everis.actions.mainframe.WriteEmulator;
import com.everis.questions.mainframe.ValidacionCobros;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ConsultaTarifaNormal implements Task {
    public final String concepto;

    public ConsultaTarifaNormal(String concepto) {
        this.concepto = concepto;
    }

    public static Performable withData(String concepto){
        return instrumented(ConsultaTarifaNormal.class,concepto);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Emulator.withPrints(Emulator.ActionsPrints.ENTER),
                Emulator.withPrints(Emulator.ActionsPrints.WAITINPUTDATA),
                WaitEmulator.withMiliseconds(2500),
                WriteEmulator.theValue("03"),
                Emulator.withPrints(Emulator.ActionsPrints.PRINT));

        /*Codigo para mostrar paso a paso en Mainframe*/
        Serenity.recordReportData().withTitle("Mainframe Evidence")
                .andContents(actor.asksFor(ValidacionCobros.GetStringByPosition(0,0,24,79)).substring(0,1942));

        actor.attemptsTo(
                Emulator.withPrints(Emulator.ActionsPrints.ENTER),
                WaitEmulator.withMiliseconds(2500),
                Emulator.withPrints(Emulator.ActionsPrints.WAITINPUTDATA),
                WriteEmulator.theValue("tbe"),
                Emulator.withPrints(Emulator.ActionsPrints.WAITINPUTDATA),
                WriteEmulator.theValue("0001"),
                Emulator.withPrints(Emulator.ActionsPrints.WAITINPUTDATA),
                WriteEmulator.theValue("0001"),
                Emulator.withPrints(Emulator.ActionsPrints.WAITINPUTDATA),
                WriteEmulator.theValue("001"),
                Emulator.withPrints(Emulator.ActionsPrints.WAITINPUTDATA),
                WriteEmulator.theValue("42"),
                Emulator.withPrints(Emulator.ActionsPrints.WAITINPUTDATA),
                WaitEmulator.withMiliseconds(2500),
                WriteEmulator.theValue(concepto),
                Emulator.withPrints(Emulator.ActionsPrints.PRINT));

        /*Codigo para mostrar paso a paso en Mainframe*/
        Serenity.recordReportData().withTitle("Mainframe Evidence")
                .andContents(actor.asksFor(ValidacionCobros.GetStringByPosition(0,0,24,79)).substring(0,1942));

        actor.attemptsTo(
                Emulator.withPrints(Emulator.ActionsPrints.ENTER),
                Emulator.withPrints(Emulator.ActionsPrints.PRINT));

        /*Codigo para mostrar paso a paso en Mainframe*/
        Serenity.recordReportData().withTitle("Mainframe Evidence")
                .andContents(actor.asksFor(ValidacionCobros.GetStringByPosition(0,0,24,79)).substring(0,1942));
    }
}
