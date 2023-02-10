package com.everis.tasks.mainframe;

import com.everis.bpi.actions.mainframe.Emulator;
import com.everis.bpi.actions.mainframe.WaitEmulator;
import com.everis.bpi.actions.mainframe.WriteLineEmulator;
import com.everis.bpi.questions.mainframe.ValidacionCobros;
import lombok.SneakyThrows;
import mainframe.com.bdd.lib.EmulatorActions;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ObtenerMontoTarifa extends EmulatorActions implements Task {
    public final int i;
    public final String[] montoTarifaNivelArray;

    public ObtenerMontoTarifa(int i, String[] montoTarifaNivelArray) {
        this.i = i;
        this.montoTarifaNivelArray = montoTarifaNivelArray;
    }

    public static Performable withData(int i, String[] montoTarifaNivelArray) {
        return instrumented(ObtenerMontoTarifa.class, i, montoTarifaNivelArray);
    }

    @SneakyThrows
    @Override
    public <T extends Actor> void performAs(T actor) {


        actor.attemptsTo(
                WaitEmulator.withMiliseconds(2500),
                Emulator.withPrints(Emulator.ActionsPrints.TAB),
                WaitEmulator.withMiliseconds(2000),
                Emulator.withPrints(Emulator.ActionsPrints.WAITINPUTDATA),
                WriteLineEmulator.theValue("Right"),
                WaitEmulator.withMiliseconds(2000),
                Emulator.withPrints(Emulator.ActionsPrints.WAITINPUTDATA),
                WriteLineEmulator.theValue("Down"),
                Emulator.withPrints(Emulator.ActionsPrints.ENTER),
                Emulator.withPrints(Emulator.ActionsPrints.PRINT));


        montoTarifaNivelArray[i] = actor.asksFor(ValidacionCobros.GetStringByPosition(12, 35, 1, 10)).substring(0, 10).trim();
        Serenity.setSessionVariable("montoTarifaNivelArray").to(montoTarifaNivelArray);


        /*Codigo para mostrar paso a paso en Mainframe*/
        Serenity.recordReportData().withTitle("Mainframe Evidence")
                .andContents(actor.asksFor(ValidacionCobros.GetStringByPosition(0, 0, 24, 79)).substring(0, 1942));
    }
}