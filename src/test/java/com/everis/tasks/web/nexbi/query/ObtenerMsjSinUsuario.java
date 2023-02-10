package com.everis.tasks.web.nexbi.query;

import com.everis.bpi.questions.web.nexbi.NexbiQuestions;
import com.everis.bpi.userinterface.web.nexbi.QueryPage;
import lombok.SneakyThrows;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class ObtenerMsjSinUsuario implements Task {
    public final int i;
    public final String[] tipoAccesoArrays;
    public final int[] cantUsuarioActivoConfirmadoArrays;
    public final int[] cantUsuarioActivoArrays;


    public ObtenerMsjSinUsuario(int i, String[] tipoAccesoArrays, int[] cantUsuarioActivoConfirmadoArrays, int[] cantUsuarioActivoArrays) {

        this.i = i;
        this.tipoAccesoArrays = tipoAccesoArrays;
        this.cantUsuarioActivoConfirmadoArrays = cantUsuarioActivoConfirmadoArrays;
        this.cantUsuarioActivoArrays = cantUsuarioActivoArrays;

    }

    public static Performable withData(int i, String[] tipoAccesoArrays, int[] cantUsuarioActivoConfirmadoArrays, int[] cantUsuarioActivoArrays) {
        return instrumented(ObtenerMsjSinUsuario.class, i, tipoAccesoArrays, cantUsuarioActivoConfirmadoArrays, cantUsuarioActivoArrays);
    }


    @SneakyThrows
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(QueryPage.MENU_USUARIOS, isVisible()).forNoMoreThan(30).seconds(),
                Click.on(QueryPage.MENU_USUARIOS));

        /*Obitene el mensaje de cuantos usuarios existen*/
        String mensajeUsuarios = NexbiQuestions.sinUsuario().answeredBy(theActorInTheSpotlight());

        boolean isEnabled = Serenity.sessionVariableCalled("checkBoxTipoAcceso");
        if (!isEnabled) {
            tipoAccesoArrays[i - 1] = "Stock";
        } else if (isEnabled) {
            tipoAccesoArrays[i - 1] = "Nuevo";
        }

        actor.attemptsTo(
                WaitUntil.the(QueryPage.SELECT_ICON_ISV, isVisible()).forNoMoreThan(20).seconds(),
                Click.on(QueryPage.SELECT_ICON_ISV));
        cantUsuarioActivoConfirmadoArrays[i - 1] = 0;

        Serenity.setSessionVariable("mensajeUsuarios").to(mensajeUsuarios);
        Serenity.setSessionVariable("tipoAccesoArrays").to(tipoAccesoArrays);
        Serenity.setSessionVariable("cantUsuarioActivoConfirmadoArrays").to(cantUsuarioActivoConfirmadoArrays);
    }
}
