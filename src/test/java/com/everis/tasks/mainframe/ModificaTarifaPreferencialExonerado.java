package com.everis.tasks.mainframe;

import com.everis.bpi.actions.mainframe.*;
import com.everis.bpi.questions.mainframe.ValidacionCobros;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ModificaTarifaPreferencialExonerado implements Task {
    public final String numeroConcepto;
    public final String codigoUnico;
    public final String fechaInicio;
    public final String fechaFin;
    public final String referencia;
    public final String autorizador;

    public ModificaTarifaPreferencialExonerado(String numeroConcepto, String codigoUnico, String fechaInicio, String fechaFin, String referencia, String autorizador) {
        this.numeroConcepto = numeroConcepto;
        this.codigoUnico = codigoUnico;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.referencia = referencia;
        this.autorizador = autorizador;
    }

    public static Performable withData(String numeroConcepto, String codigoUnico, String fechaInicio, String fechaFin, String referencia, String autorizador) {
        return instrumented(ModificaTarifaPreferencialExonerado.class, numeroConcepto, codigoUnico, fechaInicio, fechaFin, referencia, autorizador);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Emulator.withPrints(Emulator.ActionsPrints.ENTER),
                WaitEmulator.withMiliseconds(2000),
                WriteEmulator.theValue("06"),
                Emulator.withPrints(Emulator.ActionsPrints.PRINT));

        /*Codigo para mostrar paso a paso en Mainframe*/
        Serenity.recordReportData().withTitle("Mainframe Evidence")
                .andContents(actor.asksFor(ValidacionCobros.GetStringByPosition(0, 0, 24, 79)));

        actor.attemptsTo(
                Emulator.withPrints(Emulator.ActionsPrints.ENTER),
                WaitEmulator.withMiliseconds(2000),
                WriteEmulator.theValue("2"),
                Emulator.withPrints(Emulator.ActionsPrints.TAB),
                WriteEmulator.theValue("tbe"),
                WriteEmulator.theValue("0001"),
                WriteEmulator.theValue("0001"),
                Emulator.withPrints(Emulator.ActionsPrints.PRINT));

        /*Codigo para mostrar paso a paso en Mainframe*/
        Serenity.recordReportData().withTitle("Mainframe Evidence")
                .andContents(actor.asksFor(ValidacionCobros.GetStringByPosition(0, 0, 24, 79)));

        actor.attemptsTo(
                Emulator.withPrints(Emulator.ActionsPrints.ENTER),
                Emulator.withPrints(Emulator.ActionsPrints.PRINT));

        /*Codigo para mostrar paso a paso en Mainframe*/
        Serenity.recordReportData().withTitle("Mainframe Evidence")
                .andContents(actor.asksFor(ValidacionCobros.GetStringByPosition(0, 0, 24, 79)));

        actor.attemptsTo(
                WaitEmulator.withMiliseconds(2000),
                KeyboardFunction.theValue(8),
                WaitEmulator.withMiliseconds(2000),
                WriteEmulator.theValue("001"),
                WriteEmulator.theValue("42"),
                WaitEmulator.withMiliseconds(2000),
                WriteEmulator.theValue(numeroConcepto),
                WaitEmulator.withMiliseconds(2000),
                WriteEmulator.theValue(codigoUnico),
                Emulator.withPrints(Emulator.ActionsPrints.PRINT));

        /*Codigo para mostrar paso a paso en Mainframe*/
        Serenity.recordReportData().withTitle("Mainframe Evidence")
                .andContents(actor.asksFor(ValidacionCobros.GetStringByPosition(0, 0, 24, 79)));

        actor.attemptsTo(
                Emulator.withPrints(Emulator.ActionsPrints.ENTER),
                Emulator.withPrints(Emulator.ActionsPrints.PRINT));

        /*Codigo para mostrar paso a paso en Mainframe*/
        Serenity.recordReportData().withTitle("Mainframe Evidence")
                .andContents(actor.asksFor(ValidacionCobros.GetStringByPosition(0, 0, 24, 79)));

        actor.attemptsTo(
                Emulator.withPrints(Emulator.ActionsPrints.TAB),
                Emulator.withPrints(Emulator.ActionsPrints.TAB),
                Emulator.withPrints(Emulator.ActionsPrints.TAB),
                Emulator.withPrints(Emulator.ActionsPrints.TAB),
                WriteEmulator.theValue("3"),
                WriteEmulator.theValue("2"),
                WriteEmulator.theValue("1"),//Con Exoneracion
                WriteEmulator.theValue("0"),
                WriteEmulator.theValue("0"),
                WaitEmulator.withMiliseconds(2000),
                WriteEmulator.theValue(fechaInicio.replace("/", "")),
                WaitEmulator.withMiliseconds(2000),
                WriteEmulator.theValue(fechaFin.replace("/", "")));

        for (int i = 0; i < 40; i++) {
            actor.attemptsTo(
                    WriteLineEmulator.theValue("Delete"));
        }

        actor.attemptsTo(
                WaitEmulator.withMiliseconds(2000),
                WriteEmulator.theValue(referencia),
                Emulator.withPrints(Emulator.ActionsPrints.TAB));

        for (int i = 0; i < 40; i++) {
            actor.attemptsTo(
                    WriteLineEmulator.theValue("Delete"));
        }

        actor.attemptsTo(
                WaitEmulator.withMiliseconds(2000),
                WriteEmulator.theValue(autorizador),
                Emulator.withPrints(Emulator.ActionsPrints.PRINT));

        /*Codigo para mostrar paso a paso en Mainframe*/
        Serenity.recordReportData().withTitle("Mainframe Evidence")
                .andContents(actor.asksFor(ValidacionCobros.GetStringByPosition(0, 0, 24, 79)));

        actor.attemptsTo(
                Emulator.withPrints(Emulator.ActionsPrints.ENTER),
                Emulator.withPrints(Emulator.ActionsPrints.PRINT));

        /*Codigo para mostrar paso a paso en Mainframe*/
        Serenity.recordReportData().withTitle("Mainframe Evidence")
                .andContents(actor.asksFor(ValidacionCobros.GetStringByPosition(0, 0, 24, 79)));

        actor.attemptsTo(
                WaitEmulator.withMiliseconds(2000),
                KeyboardFunction.theValue(8),
                Emulator.withPrints(Emulator.ActionsPrints.PRINT));

        /*Codigo para mostrar paso a paso en Mainframe*/
        Serenity.recordReportData().withTitle("Mainframe Evidence")
                .andContents(actor.asksFor(ValidacionCobros.GetStringByPosition(0, 0, 24, 79)));

        actor.attemptsTo(
                Emulator.withPrints(Emulator.ActionsPrints.ENTER),
                Emulator.withPrints(Emulator.ActionsPrints.PRINT));

        /*Codigo para mostrar paso a paso en Mainframe*/
        Serenity.recordReportData().withTitle("Mainframe Evidence")
                .andContents(actor.asksFor(ValidacionCobros.GetStringByPosition(0, 0, 24, 79)));

        actor.attemptsTo(
                WaitEmulator.withMiliseconds(2000),
                KeyboardFunction.theValue(2),
                Emulator.withPrints(Emulator.ActionsPrints.PRINT));

        /*Codigo para mostrar paso a paso en Mainframe*/
        Serenity.recordReportData().withTitle("Mainframe Evidence")
                .andContents(actor.asksFor(ValidacionCobros.GetStringByPosition(0, 0, 24, 79)));

    }
}