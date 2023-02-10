package com.everis.tasks.web.nexbi.query;

import com.everis.bpi.questions.web.nexbi.NexbiQuestions;
import com.everis.bpi.userinterface.web.nexbi.QueryPage;
import lombok.SneakyThrows;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.util.ArrayList;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class ObtenerDetalleUsuario implements Task {
    public final ArrayList<String> nombrePuntoServicioArrays;
    public final ArrayList<Integer> cantUsuarioActivoConfirmadoArrays;
    public final ArrayList<Integer> cantUsuarioActivoArrays;
    public final ArrayList<Integer> cantUsuarioArrays;

    public ObtenerDetalleUsuario(ArrayList<String> nombrePuntoServicioArrays, ArrayList<Integer> cantUsuarioActivoConfirmadoArrays, ArrayList<Integer> cantUsuarioActivoArrays, ArrayList<Integer> cantUsuarioArrays) {
        this.nombrePuntoServicioArrays = nombrePuntoServicioArrays;
        this.cantUsuarioActivoConfirmadoArrays = cantUsuarioActivoConfirmadoArrays;
        this.cantUsuarioActivoArrays = cantUsuarioActivoArrays;
        this.cantUsuarioArrays = cantUsuarioArrays;
    }

    public static Performable withData(ArrayList<String> nombrePuntoServicioArrays, ArrayList<Integer> cantUsuarioActivoConfirmadoArrays, ArrayList<Integer> cantUsuarioActivoArrays, ArrayList<Integer> cantUsuarioArrays){
        return instrumented(ObtenerDetalleUsuario.class,nombrePuntoServicioArrays,cantUsuarioActivoConfirmadoArrays,cantUsuarioActivoArrays,cantUsuarioArrays);
    }

    @SneakyThrows
    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                WaitUntil.the(QueryPage.SELEC_ALL, isVisible()).forNoMoreThan(30).seconds(),
                Click.on(QueryPage.SELEC_ALL));

        String mensajeTotalUsuario = NexbiQuestions.cantTotalUsuarios().answeredBy(theActorInTheSpotlight()).trim();
        String[] mensajeTotalUsuarioArray = mensajeTotalUsuario.split(" ");

        actor.attemptsTo(
                WaitUntil.the(QueryPage.BTN_ACTIVO, isVisible()).forNoMoreThan(30).seconds(),
                Click.on(QueryPage.BTN_ACTIVO));


        if (QueryPage.SELEC_ALL.isVisibleFor(theActorInTheSpotlight())) {

            actor.attemptsTo(
                    WaitUntil.the(QueryPage.SELEC_ALL, isVisible()).forNoMoreThan(30).seconds(),
                    Click.on(QueryPage.SELEC_ALL));

            String mensajeNumeroPagina = NexbiQuestions.numeroPagina().answeredBy(theActorInTheSpotlight()).trim();
            String[] mensajeNumeroPaginaArray = mensajeNumeroPagina.split(" ");

            int cantUsuarioActivo=0;
            for (int m = 1; m < Integer.parseInt(mensajeNumeroPaginaArray[3]); m++) {
                actor.attemptsTo(
                        WaitUntil.the(QueryPage.SIGUIENTE_PAGINA, isVisible()).forNoMoreThan(30).seconds(),
                        Click.on(QueryPage.SIGUIENTE_PAGINA),
                        Scroll.to(QueryPage.SIGUIENTE_PAGINA));
                actor.attemptsTo(
                        WaitUntil.the(QueryPage.SUBIR_PAGINA, isVisible()).forNoMoreThan(30).seconds(),
                        Click.on(QueryPage.SUBIR_PAGINA));
                actor.attemptsTo(
                        WaitUntil.the(QueryPage.SELEC_ALL, isVisible()).forNoMoreThan(30).seconds(),
                        Click.on(QueryPage.SELEC_ALL));
            }

            String mensajeTotalUsuarioActivo = NexbiQuestions.cantUsuarios().answeredBy(theActorInTheSpotlight()).trim();
            String[] mensajeTotalUsuarioActivoArray = mensajeTotalUsuarioActivo.split(" ");

            cantUsuarioActivo=Integer.parseInt(mensajeTotalUsuarioActivoArray[3]);

            actor.attemptsTo(
                    WaitUntil.the(QueryPage.BTN_ACTIVO, isVisible()).forNoMoreThan(30).seconds(),
                    Click.on(QueryPage.BTN_ACTIVO));

            int cantUsuarioActivoConfirmado = 0, cantiUsuarioNuevo = 0, cantUsuarioStock = 0, n = 0;
            String[][] seccionNumeroTieArrays = new String[cantUsuarioActivo][2];

            for (int j = 1; j <= cantUsuarioActivo; j++) {
                if(j>40){
                    for (int k = 1; k <= (j-1)/40; k++) {
                        actor.attemptsTo(
                                WaitUntil.the(QueryPage.SIGUIENTE_PAGINA, isVisible()).forNoMoreThan(30).seconds(),
                                Click.on(QueryPage.SIGUIENTE_PAGINA),
                                Scroll.to(QueryPage.SIGUIENTE_PAGINA));
                        actor.attemptsTo(
                                WaitUntil.the(QueryPage.SUBIR_PAGINA, isVisible()).forNoMoreThan(50).seconds(),
                                Click.on(QueryPage.SUBIR_PAGINA));
                            n=(j-1)-(40*k);
                    }
                }

                n++;
                actor.attemptsTo(
                        Click.on(QueryPage.BTN_SEARCH.of(String.valueOf(n)).called(String.valueOf(n))));

                String nivelServicioUsuario;
                do {
                    nivelServicioUsuario = NexbiQuestions.nivelServicioUsuario().answeredBy(theActorInTheSpotlight()).trim();
                } while (nivelServicioUsuario.equals("-") || nivelServicioUsuario.equals(""));

                /*Verifica si el usuario tiene TIE para que sea un usuario activo*/
                if (QueryPage.SECCION_TIE.isVisibleFor(actor)) {
                    seccionNumeroTieArrays[j - 1][0] = "true";

                    /*Verifica si el usuario tiene numero de TIE para que sea un usuario activo*/
                    if (!NexbiQuestions.numeroTie().answeredBy(theActorInTheSpotlight()).equals(" - ")) {
                        cantUsuarioStock++;
                        cantUsuarioActivoConfirmado++;
                        seccionNumeroTieArrays[j - 1][1] = "true";
                    } else {
                        seccionNumeroTieArrays[j - 1][1] = "false";
                    }
                } else {
                    seccionNumeroTieArrays[j - 1][0] = "false";
                    seccionNumeroTieArrays[j - 1][1] = "false";
                    cantiUsuarioNuevo++;
                    cantUsuarioActivoConfirmado++;
                }

                actor.attemptsTo(
                        //WaitUntil.the(QueryPage.BTN_REGRESAR, isVisible()).forNoMoreThan(40).seconds(),
                        //Click.on(QueryPage.BTN_REGRESAR).afterWaitingUntilEnabled(),
                        WaitUntil.the(QueryPage.MENU_USUARIOS, isVisible()).forNoMoreThan(50).seconds(),
                        Click.on(QueryPage.MENU_USUARIOS),
                        WaitUntil.the(QueryPage.BTN_ACTIVO, isVisible()).forNoMoreThan(40).seconds(),
                        Click.on(QueryPage.BTN_ACTIVO));
            }
            cantUsuarioArrays.add(Integer.parseInt(mensajeTotalUsuarioArray[mensajeTotalUsuarioArray.length-5]));
            cantUsuarioActivoArrays.add(cantUsuarioActivo);
            cantUsuarioActivoConfirmadoArrays.add(cantUsuarioActivoConfirmado);

            Serenity.setSessionVariable("seccionNumeroTieArrays"+(nombrePuntoServicioArrays.size()-1)).to(seccionNumeroTieArrays);
            Serenity.setSessionVariable("cantUsuarioStock").to(cantUsuarioStock);
            Serenity.setSessionVariable("cantiUsuarioNuevo").to(cantiUsuarioNuevo);
        }else{
            cantUsuarioArrays.add(Integer.parseInt(mensajeTotalUsuarioArray[mensajeTotalUsuarioArray.length-5]));
            cantUsuarioActivoArrays.add(0);
            cantUsuarioActivoConfirmadoArrays.add(0);
        }

        Serenity.setSessionVariable("cantUsuarioArrays").to(cantUsuarioArrays);
        Serenity.setSessionVariable("cantUsuarioActivoArrays").to(cantUsuarioActivoArrays);
        Serenity.setSessionVariable("cantUsuarioActivoConfirmadoArrays").to(cantUsuarioActivoConfirmadoArrays);
    }
}
