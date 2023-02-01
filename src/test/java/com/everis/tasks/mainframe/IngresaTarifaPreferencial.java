package com.everis.tasks.mainframe;

import com.everis.actions.mainframe.Emulator;
import com.everis.actions.mainframe.KeyboardFunction;
import com.everis.actions.mainframe.WaitEmulator;
import com.everis.actions.mainframe.WriteEmulator;
import com.everis.questions.mainframe.ValidacionCobros;
import io.cucumber.java.PendingException;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class IngresaTarifaPreferencial implements Task {
    public final String tarifaPreferencial;
    public final String codigoUnico;
    public final String fechaVencimiento;
    public final String monto;

    public IngresaTarifaPreferencial(String tarifaPreferencial, String codigoUnico, String fechaVencimiento, String monto) {
        this.tarifaPreferencial = tarifaPreferencial;
        this.codigoUnico = codigoUnico;
        this.fechaVencimiento = fechaVencimiento;
        this.monto = monto;
    }

    public static Performable withData(String tarifaPreferencial, String codigoUnico, String fechaVencimiento, String monto){
        return instrumented(IngresaTarifaPreferencial.class,tarifaPreferencial,codigoUnico,fechaVencimiento,monto);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        String numeroConcepto="";
        String exonerar="0";

        if (tarifaPreferencial.equals("Negocio")) {
            numeroConcepto="20";
        } else if (tarifaPreferencial.equals("Empresa")) {
            numeroConcepto="21";
        } else if (tarifaPreferencial.equals("Corporativo")) {
            numeroConcepto="22";
        } else if (tarifaPreferencial.equals("Usuario Adicional")) {
            numeroConcepto="40";
        }else{
            throw new PendingException("Dato en 'tarifaPreferencial' incorrecto");
        }

        if(monto.toLowerCase().contains("exo")){
            exonerar="1";
        }

        actor.attemptsTo(
                Emulator.withPrints(Emulator.ActionsPrints.ENTER),
                WaitEmulator.withMiliseconds(2000),
                WriteEmulator.theValue("01"),
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
                WriteEmulator.theValue("0001"));

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

        Serenity.recordReportData().withTitle("Mainframe Evidence")
                .andContents(actor.asksFor(ValidacionCobros.GetStringByPosition(0, 0, 24, 79)));

        actor.attemptsTo(
                WriteEmulator.theValue("3"),
                WriteEmulator.theValue("2"),
                WaitEmulator.withMiliseconds(2000),
                WriteEmulator.theValue(exonerar),
                WriteEmulator.theValue("0"),
                WriteEmulator.theValue("0"),
                Emulator.withPrints(Emulator.ActionsPrints.TAB),
                Emulator.withPrints(Emulator.ActionsPrints.TAB),
                Emulator.withPrints(Emulator.ActionsPrints.TAB),
                WaitEmulator.withMiliseconds(2000),
                WriteEmulator.theValue(fechaVencimiento.replace("/","")),
                WaitEmulator.withMiliseconds(2000),
                WriteEmulator.theValue("bpi"),//preguntar si estos espacios iran en el example
                Emulator.withPrints(Emulator.ActionsPrints.TAB),
                WaitEmulator.withMiliseconds(2000),
                WriteEmulator.theValue("nexbi"),//preguntar si estos espacios iran en el example
                Emulator.withPrints(Emulator.ActionsPrints.PRINT));

        Serenity.recordReportData().withTitle("Mainframe Evidence")
                .andContents(actor.asksFor(ValidacionCobros.GetStringByPosition(0, 0, 24, 79)));

        actor.attemptsTo(
                Emulator.withPrints(Emulator.ActionsPrints.ENTER),
                Emulator.withPrints(Emulator.ActionsPrints.PRINT));

        Serenity.recordReportData().withTitle("Mainframe Evidence")
                .andContents(actor.asksFor(ValidacionCobros.GetStringByPosition(0, 0, 24, 79)));

        actor.attemptsTo(
                WaitEmulator.withMiliseconds(2000),
                KeyboardFunction.theValue(8));

        if(exonerar.equals("0")){
            actor.attemptsTo(
                    WaitEmulator.withMiliseconds(2000),
                    WriteEmulator.theValue(monto));
        }

        actor.attemptsTo(
                Emulator.withPrints(Emulator.ActionsPrints.PRINT));

        Serenity.recordReportData().withTitle("Mainframe Evidence")
                .andContents(actor.asksFor(ValidacionCobros.GetStringByPosition(0, 0, 24, 79)));

        actor.attemptsTo(
                Emulator.withPrints(Emulator.ActionsPrints.ENTER),
                Emulator.withPrints(Emulator.ActionsPrints.PRINT));

        Serenity.recordReportData().withTitle("Mainframe Evidence")
                .andContents(actor.asksFor(ValidacionCobros.GetStringByPosition(0, 0, 24, 79)));

        actor.attemptsTo(
                WaitEmulator.withMiliseconds(2000),
                KeyboardFunction.theValue(2),
                Emulator.withPrints(Emulator.ActionsPrints.PRINT));

        Serenity.recordReportData().withTitle("Mainframe Evidence")
                .andContents(actor.asksFor(ValidacionCobros.GetStringByPosition(0, 0, 24, 79)));

        actor.attemptsTo(
                WaitEmulator.withMiliseconds(2000),
                KeyboardFunction.theValue(3),
                WaitEmulator.withMiliseconds(2000),
                KeyboardFunction.theValue(3),
                WaitEmulator.withMiliseconds(2000),
                KeyboardFunction.theValue(3));

        Serenity.setSessionVariable("numeroConcepto").to(numeroConcepto);
    }
}
