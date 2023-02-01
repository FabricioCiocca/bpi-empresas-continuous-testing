package com.everis.tasks.web.nexbi.query;

import com.everis.userinterfaces.web.nexbi.QueryPage;
import lombok.SneakyThrows;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class VisualizarVentanaUsuarios implements Task {

    @SneakyThrows
    @Override
    public <T extends Actor> void performAs(T actor) {
        Thread.sleep(10000);
        actor.attemptsTo(
                WaitUntil.the(QueryPage.MENU_USUARIOS, isVisible()).forNoMoreThan(30).seconds(),
                Click.on(QueryPage.MENU_USUARIOS));
    }
}
