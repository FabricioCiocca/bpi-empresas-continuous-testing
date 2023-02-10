package com.everis.tasks.web.nexbi.query;

import com.everis.userinterfaces.web.nexbi.QueryPage;
import lombok.SneakyThrows;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class ObtenerDetalleSinUsuario implements Task {
    public final int i;
    public final int[] cantUsuarioActivoArrays;
    public final int[] cantUsuarioArrays;

    public ObtenerDetalleSinUsuario(int i, int[] cantUsuarioActivoArrays, int[] cantUsuarioArrays) {
        this.i = i;
        this.cantUsuarioActivoArrays = cantUsuarioActivoArrays;
        this.cantUsuarioArrays = cantUsuarioArrays;
    }

    public static Performable withData(int i, int[] cantUsuarioActivoArrays, int[] cantUsuarioArrays){
        return instrumented(ObtenerDetalleSinUsuario.class,i,cantUsuarioActivoArrays,cantUsuarioArrays);
    }

    @SneakyThrows
    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                WaitUntil.the(QueryPage.MENU_USUARIOS, isVisible()).forNoMoreThan(30).seconds(),
                Click.on(QueryPage.MENU_USUARIOS));

        cantUsuarioArrays[i-1]=0;
        cantUsuarioActivoArrays[i-1]=0;

        Serenity.setSessionVariable("cantUsuarioArrays").to(cantUsuarioArrays);
        Serenity.setSessionVariable("cantUsuarioActivoArrays").to(cantUsuarioActivoArrays);
    }
}
