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

public class IngresarAplicativo implements Task {

    public final String numeroAplicativo;

    public IngresarAplicativo(String numeroAplicativo) {
        this.numeroAplicativo = numeroAplicativo;
    }

    public static Performable withData(String numeroAplicativo) {
        return instrumented(IngresarAplicativo.class, numeroAplicativo);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Emulator.withPrints(Emulator.ActionsPrints.WAITINPUTDATA),
                WaitEmulator.withMiliseconds(2500),
                WriteEmulator.theValue(numeroAplicativo),
                Emulator.withPrints(Emulator.ActionsPrints.PRINT));

        /*Codigo para mostrar paso a paso en Mainframe*/
        Serenity.recordReportData().withTitle("Mainframe Evidence")
                .andContents(actor.asksFor(ValidacionCobros.GetStringByPosition(0, 0, 24, 79)).substring(0, 1942));


        actor.attemptsTo(
                Emulator.withPrints(Emulator.ActionsPrints.ENTER),
                Emulator.withPrints(Emulator.ActionsPrints.PRINT));

        String mensaje = actor.asksFor(ValidacionCobros.GetStringByPosition(3, 69, 1, 4)).substring(0, 4).trim();
        Serenity.setSessionVariable("mensaje").to(mensaje);
    }
}
