package com.everis.tasks.mainframe;

import com.everis.bpi.actions.mainframe.Emulator;
import com.everis.bpi.actions.mainframe.WaitEmulator;
import com.everis.bpi.actions.mainframe.WriteEmulator;
import com.everis.bpi.questions.mainframe.ValidacionCobros;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ConsultaTarifaPreferencial implements Task {
    public final String concepto;
    public final String codigoUnico;

    public ConsultaTarifaPreferencial(String concepto, String codigoUnico) {
        this.concepto = concepto;
        this.codigoUnico = codigoUnico;
    }

    public static Performable withData(String concepto, String codigoUnico) {
        return instrumented(ConsultaTarifaPreferencial.class, concepto, codigoUnico);
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
                .andContents(actor.asksFor(ValidacionCobros.GetStringByPosition(0, 0, 24, 79)).substring(0, 1942));

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
                WaitEmulator.withMiliseconds(2500),
                WriteEmulator.theValue(concepto),
                WaitEmulator.withMiliseconds(2500),
                WriteEmulator.theValue(codigoUnico),
                Emulator.withPrints(Emulator.ActionsPrints.PRINT));

        /*Codigo para mostrar paso a paso en Mainframe*/
        Serenity.recordReportData().withTitle("Mainframe Evidence")
                .andContents(actor.asksFor(ValidacionCobros.GetStringByPosition(0, 0, 24, 79)).substring(0, 1942));

        actor.attemptsTo(
                Emulator.withPrints(Emulator.ActionsPrints.ENTER),
                Emulator.withPrints(Emulator.ActionsPrints.PRINT));

        /*Codigo para mostrar paso a paso en Mainframe*/
        Serenity.recordReportData().withTitle("Mainframe Evidence")
                .andContents(actor.asksFor(ValidacionCobros.GetStringByPosition(0, 0, 24, 79)).substring(0, 1942));

        String mensajeCliente = actor.asksFor(ValidacionCobros.GetStringByPosition(21, 4, 1, 19)).substring(0, 19).trim();
        Serenity.setSessionVariable("mensajeCliente").to(mensajeCliente);

        String lineaModificacion = actor.asksFor(ValidacionCobros.GetStringByPosition(19, 0, 1, 79)).substring(0, 79).trim();
        Serenity.setSessionVariable("lineaModificacion").to(lineaModificacion);
    }
}
