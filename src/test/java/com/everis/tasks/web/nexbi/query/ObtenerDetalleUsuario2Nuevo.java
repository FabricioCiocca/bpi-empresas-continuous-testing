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
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class ObtenerDetalleUsuario2Nuevo implements Task {
    public final ArrayList<String> nombrePuntoServicioArrays;
    public final ArrayList<Integer> cantUsuarioActivoArrays;
    public final ArrayList<Integer> cantUsuarioArrays;

    public ObtenerDetalleUsuario2Nuevo(ArrayList<String> nombrePuntoServicioArrays, ArrayList<Integer> cantUsuarioActivoArrays, ArrayList<Integer> cantUsuarioArrays) {
        this.nombrePuntoServicioArrays = nombrePuntoServicioArrays;
        this.cantUsuarioActivoArrays = cantUsuarioActivoArrays;
        this.cantUsuarioArrays = cantUsuarioArrays;
    }

    public static Performable withData(ArrayList<String> nombrePuntoServicioArrays, ArrayList<Integer> cantUsuarioActivoArrays, ArrayList<Integer> cantUsuarioArrays){
        return instrumented(ObtenerDetalleUsuario2Nuevo.class,nombrePuntoServicioArrays,cantUsuarioActivoArrays,cantUsuarioArrays);
    }

    @SneakyThrows
    @Override
    public <T extends Actor> void performAs(T actor) {

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

            String nivelServicioUsuario;
            do {
                nivelServicioUsuario = NexbiQuestions.nivelServicioUsuario().answeredBy(theActorInTheSpotlight());
            } while (nivelServicioUsuario.equals(" - ") || nivelServicioUsuario.equals(""));

            List<WebElement> seccionIntranetUsuario = NexbiQuestions.seccionIntranetUsuario().answeredBy(theActorInTheSpotlight());

            /*Verifica si el usuario tiene TIE para que sea un usuario activo*/
            if (seccionIntranetUsuario.size()==6) {
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

            actor.attemptsTo(
                    WaitUntil.the(QueryPage.BTN_REGRESAR, isVisible()).forNoMoreThan(40).seconds(),
                    Click.on(QueryPage.BTN_REGRESAR),
                    WaitUntil.the(QueryPage.BTN_ACTIVO, isVisible()).forNoMoreThan(40).seconds(),
                    Click.on(QueryPage.BTN_ACTIVO));
        }

        cantUsuarioArrays.add(cantUsuario);
        cantUsuarioActivoArrays.add(cantUsuarioActivo);

        Serenity.setSessionVariable("seccionNumeroTieArrays"+(nombrePuntoServicioArrays.size()-1)).to(seccionNumeroTieArrays);
        Serenity.setSessionVariable("cantUsuarioArrays").to(cantUsuarioArrays);
        Serenity.setSessionVariable("cantUsuarioActivoArrays").to(cantUsuarioActivoArrays);
        Serenity.setSessionVariable("cantUsuarioStock").to(cantUsuarioStock);
        Serenity.setSessionVariable("cantiUsuarioNuevo").to(cantiUsuarioNuevo);
    }
}
