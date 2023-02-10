package com.everis.tasks.web.bpi.pagosServicios;

import com.everis.userinterfaces.web.bpi.LoginPage;
import lombok.SneakyThrows;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

public class MenuBotonEntendido implements Task {

    @SneakyThrows
    @Override
    public <T extends Actor> void performAs(T actor) {
        if (LoginPage.POPUP_BTN_ENTENDIDO.resolveFor(actor).isEnabled()) {
            actor.attemptsTo(Click.on(LoginPage.POPUP_BTN_ENTENDIDO));
        }else {

        }


    }
}
