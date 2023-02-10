package com.everis.tasks.web.nexbi.query;

import com.everis.bpi.userinterface.web.nexbi.QueryPage;
import lombok.SneakyThrows;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

public class VisualizarIsvNexbi implements Task {

    @SneakyThrows
    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                //WaitUntil.the(QueryPage.SELECT_ICON_ISV, isVisible()).forNoMoreThan(20).seconds(),
                Click.on(QueryPage.SELECT_ICON_ISV).afterWaitingUntilPresent());
    }
}
