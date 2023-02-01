package com.everis.tasks.web.nexbi.query;

import com.everis.userinterfaces.web.nexbi.QueryPage;
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

public class ConsultaPorCodigo implements Task {
    public final String codigoUnico;

    public ConsultaPorCodigo(String codigoUnico) {
        this.codigoUnico = codigoUnico;
    }

    public static Performable withData(String codigoUnico) {
        return instrumented(ConsultaPorCodigo.class, codigoUnico);
    }

    @SneakyThrows
    @Step("{0} envia datos")
    @Override
    public <T extends Actor> void performAs(T actor) {


        Thread.sleep(3000);
        actor.attemptsTo(
                WaitUntil.the(QueryPage.SELECT_ARROW, isVisible()).forNoMoreThan(20).seconds(),
                Click.on(QueryPage.SELECT_ARROW),
                WaitUntil.the(QueryPage.CHOOSE_SEARCH, isVisible()).forNoMoreThan(20).seconds(),
                Click.on(QueryPage.CHOOSE_SEARCH),
                WaitUntil.the(QueryPage.INP_SEARCH_COD, isVisible()).forNoMoreThan(20).seconds(),
                Enter.theValue(codigoUnico).into(QueryPage.INP_SEARCH_COD),
                WaitUntil.the(QueryPage.ICON_SEARCH, isVisible()).forNoMoreThan(20).seconds(),
                Click.on(QueryPage.ICON_SEARCH));

        Serenity.setSessionVariable("codigoUnico").to(codigoUnico);
    }
}
