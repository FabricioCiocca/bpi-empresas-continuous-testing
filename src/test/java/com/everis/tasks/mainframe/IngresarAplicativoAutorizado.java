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

public class IngresarAplicativoAutorizado implements Task {
    public final String numeroAplicativo;

    public IngresarAplicativoAutorizado(String numeroAplicativo) {
        this.numeroAplicativo = numeroAplicativo;
    }

    public static Performable withData(String numeroAplicativo) {
        return instrumented(IngresarAplicativoAutorizado.class, numeroAplicativo);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                WaitEmulator.withMiliseconds(2000),
                WriteEmulator.theValue(numeroAplicativo),
                Emulator.withPrints(Emulator.ActionsPrints.PRINT));

        /*Codigo para mostrar paso a paso en Mainframe*/
        Serenity.recordReportData().withTitle("Mainframe Evidence")
                .andContents(actor.asksFor(ValidacionCobros.GetStringByPosition(0, 0, 24, 79)));

        actor.attemptsTo(
                Emulator.withPrints(Emulator.ActionsPrints.ENTER));
    }
}
