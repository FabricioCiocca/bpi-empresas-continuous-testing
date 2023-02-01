package com.everis.tasks.web.nexbi.query;

import com.everis.questions.web.nexbi.NexbiQuestions;
import com.everis.userinterfaces.web.nexbi.QueryPage;
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

public class ObtenerDetalleUsuario2 implements Task {
    public final int i;
    public final int[] cantUsuarioActivoArrays;
    public final int[] cantUsuarioArrays;

    public ObtenerDetalleUsuario2(int i, int[] cantUsuarioActivoArrays, int[] cantUsuarioArrays) {
        this.i = i;
        this.cantUsuarioActivoArrays = cantUsuarioActivoArrays;
        this.cantUsuarioArrays = cantUsuarioArrays;
    }

    public static Performable withData(int i, int[] cantUsuarioActivoArrays, int[] cantUsuarioArrays){
        return instrumented(ObtenerDetalleUsuario2.class,i,cantUsuarioActivoArrays,cantUsuarioArrays);
    }

    @SneakyThrows
    @Override
    public <T extends Actor> void performAs(T actor) {

        Thread.sleep(5500);
        actor.attemptsTo(
                WaitUntil.the(QueryPage.BTN_ACTIVO, isVisible()).forNoMoreThan(30).seconds(),
                Click.on(QueryPage.BTN_ACTIVO),
                WaitUntil.the(QueryPage.SELEC_ALL, isVisible()).forNoMoreThan(30).seconds(),
                Click.on(QueryPage.SELEC_ALL));

        /*Obitene el mensaje de cuantos usuarios existen*/
        String mensajeUsuarios = NexbiQuestions.cantUsuarios().answeredBy(theActorInTheSpotlight());
        /*Divide mensaje para obtener la cantidad de uarios que existen*/
        String[] mensajeUsuariosArray = mensajeUsuarios.split(" ");

        /*Bucle para ver cuantos usuarios "activos" existen*/
        int cantUsuarioActivo = 0, cantiUsuarioNuevo= 0, cantUsuarioStock=0, cantUsuario = 0;
        String[][] seccionNumeroTieArrays = new String[Integer.parseInt(mensajeUsuariosArray[4])][2];

        for (int j = 1; j <= Integer.parseInt(mensajeUsuariosArray[4]); j++) {
            actor.attemptsTo(
                    Click.on(QueryPage.BTN_SEARCH.of(String.valueOf(j)).called(String.valueOf(j))));

            /*Verifica si el usuario tiene TIE para que sea un usuario activo*/
            if (QueryPage.SECCION_TIE.isVisibleFor(actor)) {
                seccionNumeroTieArrays[j-1][0]="true";

                /*Verifica si el usuario tiene numero de TIE para que sea un usuario activo*/
                if (!NexbiQuestions.numeroTie().answeredBy(theActorInTheSpotlight()).equals(" - ")) {
                    cantUsuarioStock++;
                    cantUsuarioActivo++;
                    cantUsuario++;
                    seccionNumeroTieArrays[j-1][1]="true";
                }else{
                    seccionNumeroTieArrays[j-1][1]="false";
                    cantUsuario++;
                }
            } else {
                seccionNumeroTieArrays[j-1][0]="false";
                seccionNumeroTieArrays[j-1][1]="false";
                cantiUsuarioNuevo++;
                cantUsuarioActivo++;
                cantUsuario++;
            }

            Thread.sleep(5500);
            actor.attemptsTo(
                    WaitUntil.the(QueryPage.BTN_REGRESAR, isVisible()).forNoMoreThan(40).seconds(),
                    Click.on(QueryPage.BTN_REGRESAR),
                    WaitUntil.the(QueryPage.BTN_ACTIVO, isVisible()).forNoMoreThan(40).seconds(),
                    Click.on(QueryPage.BTN_ACTIVO));
        }

        cantUsuarioArrays[i-1]=cantUsuario;
        cantUsuarioActivoArrays[i-1]=cantUsuarioActivo;

        Serenity.setSessionVariable("seccionNumeroTieArrays"+(i-1)).to(seccionNumeroTieArrays);
        Serenity.setSessionVariable("cantUsuarioArrays").to(cantUsuarioArrays);
        Serenity.setSessionVariable("cantUsuarioActivoArrays").to(cantUsuarioActivoArrays);
        Serenity.setSessionVariable("cantUsuarioStock").to(cantUsuarioStock);
        Serenity.setSessionVariable("cantiUsuarioNuevo").to(cantiUsuarioNuevo);
    }
}
