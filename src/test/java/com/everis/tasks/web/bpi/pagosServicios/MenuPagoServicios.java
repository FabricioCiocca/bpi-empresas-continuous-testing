package com.everis.tasks.web.bpi.pagosServicios;

import com.everis.userinterfaces.web.bpi.LoginPage;
import lombok.SneakyThrows;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class MenuPagoServicios implements Task {


    @SneakyThrows
    @Override
    public <T extends Actor> void performAs(T actor) {

      Thread.sleep(20000);
        actor.attemptsTo(WaitUntil.the(LoginPage.MENU_PAGOS_Y_TRANSFERENCIAS, isVisible()).forNoMoreThan(50).seconds(),
                Click.on(LoginPage.MENU_PAGOS_Y_TRANSFERENCIAS));

        actor.attemptsTo(WaitUntil.the(LoginPage.MENU_DE_SERVICIOS, isVisible()).forNoMoreThan(50).seconds(),
                Click.on(LoginPage.MENU_DE_SERVICIOS));
    }

}
