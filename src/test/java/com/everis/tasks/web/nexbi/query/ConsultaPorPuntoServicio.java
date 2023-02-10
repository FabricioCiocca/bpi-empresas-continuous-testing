package com.everis.tasks.web.nexbi.query;

import com.everis.bpi.userinterface.web.nexbi.QueryPage;
import lombok.SneakyThrows;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class ConsultaPorPuntoServicio implements Task {
    public final String puntoServicio;
    public final String codigoUnico;

    public ConsultaPorPuntoServicio(String puntoServicio, String codigoUnico) {
        this.puntoServicio = puntoServicio;
        this.codigoUnico = codigoUnico;
    }

    public static Performable withData(String puntoServicio, String codigoUnico) {
        return instrumented(ConsultaPorPuntoServicio.class, puntoServicio, codigoUnico);
    }

    @SneakyThrows
    @Step("{0} envia datos")
    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                WaitUntil.the(QueryPage.INP_SEARCH_COD, isVisible()).forNoMoreThan(20).seconds(),
                Enter.theValue(puntoServicio).into(QueryPage.INP_SEARCH_COD),
                WaitUntil.the(QueryPage.ICON_SEARCH, isVisible()).forNoMoreThan(20).seconds(),
                Click.on(QueryPage.ICON_SEARCH));

        Serenity.setSessionVariable("puntoServicio").to(puntoServicio);
        Serenity.setSessionVariable("codigoUnico").to(codigoUnico);
    }
}
