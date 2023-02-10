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

public class MenuUsuarios implements Task {
    public final int i;
    public final int[] cantUsuarioActivoArrays;

    public MenuUsuarios(int i, int[] cantUsuarioActivoArrays) {
        this.i = i;
        this.cantUsuarioActivoArrays = cantUsuarioActivoArrays;
    }
    public static Performable withData(int i, int[] cantUsuarioActivoArrays){
        return instrumented(ObtenerDetalleUsuario.class,i,cantUsuarioActivoArrays);
    }

    @SneakyThrows
    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                WaitUntil.the(QueryPage.MENU_USUARIOS, isVisible()).forNoMoreThan(30).seconds(),
                Click.on(QueryPage.MENU_USUARIOS),
                WaitUntil.the(QueryPage.BTN_ACTIVO, isVisible()).forNoMoreThan(30).seconds(),
                Click.on(QueryPage.BTN_ACTIVO),
                WaitUntil.the(QueryPage.SELEC_ALL, isVisible()).forNoMoreThan(30).seconds(),
                Click.on(QueryPage.SELEC_ALL)
        );


        /*Obitene el mensaje de cuantos usuarios existen*/
        String mensajeUsuarios = NexbiQuestions.cantUsuarios().answeredBy(theActorInTheSpotlight());
        /*Divide mensaje para obtener la cantidad de uarios que existen*/
        String[] mensajeUsuariosArray = mensajeUsuarios.split(" ");

        /*Bucle para ver cuantos usuarios "activos" existen*/
        int cantUsuarioActivo = 0, cantiUsuarioNuevo= 0, cantUsuarioMigrado=0;
            for (int i = 1; i <= Integer.parseInt(mensajeUsuariosArray[4]); i++) {
                actor.attemptsTo(
                        Click.on(QueryPage.BTN_SEARCH.of(String.valueOf(i)).called(String.valueOf(i)))
                );

                /*Declaramos si tiene TIE o no*/
                boolean seccionTieUsuario = false;
                /*Declaramos si tiene Numero de TIE o no*/
                boolean numeroTieUsuario = false;

                /*Verifica si el usuario tiene TIE para que sea un usuario activo*/
                if (QueryPage.SECCION_TIE.isVisibleFor(actor)) {
                    seccionTieUsuario = true;
                    System.out.println("------------------------------------------------");
                    System.out.println("¿El usuario tiene la sección TIE?: " + true);

                    /*Verifica si el usuario tiene numero de TIE para que sea un usuario activo*/
                    if (!NexbiQuestions.numeroTie().answeredBy(theActorInTheSpotlight()).equals(" - ")) {
                        numeroTieUsuario = true;
                        cantUsuarioActivo++;
                        System.out.println("¿El usuario tiene numero de TIE?: " + true);
                        System.out.println("------------------------------------------------");
                    }
                }else{
                    System.out.println("------------------------------------------------");
                    System.out.println("¿El usuario tiene la sección TIE?: " + false);
                    System.out.println("¿El usuario tiene numero de TIE?: " + false);
                    System.out.println("------------------------------------------------");
                }

                boolean isEnabled = Serenity.sessionVariableCalled("checkBox");

                if (!isEnabled) {
                    System.out.println("El tipo de acceso es :  Stock");
                }else {
                    if (!seccionTieUsuario) {
                        cantiUsuarioNuevo++;
                        }
                    else if (numeroTieUsuario) {
                        cantUsuarioMigrado++;
                        }
                    else {
                        cantUsuarioMigrado++;
                    }
                    actor.attemptsTo(
                            WaitUntil.the(QueryPage.BTN_REGRESAR, isVisible()).forNoMoreThan(20).seconds(),
                            Click.on(QueryPage.BTN_REGRESAR),
                            WaitUntil.the(QueryPage.BTN_ACTIVO, isVisible()).forNoMoreThan(20).seconds(),
                            Click.on(QueryPage.BTN_ACTIVO));
                }
            }

        if (cantUsuarioMigrado==0){
            System.out.println("El tipo de acceso :  Nuevo");
        }else if (cantiUsuarioNuevo==0){
            System.out.println("El tipo de acceso :  Migrado");
        }else {
            System.out.println("El tipo de acceso :  Migrado");
        }
        actor.attemptsTo(
                WaitUntil.the(QueryPage.SELECT_ICON_ISV, isVisible()).forNoMoreThan(20).seconds(),
                Click.on(QueryPage.SELECT_ICON_ISV));

        System.out.println("La cantidad de Usuarios Activos:  " + cantUsuarioActivo + "\n"+"\n"+"\n"+"\n");
        Serenity.setSessionVariable("cantUsuarioActivo").to(cantUsuarioActivo);
        cantUsuarioActivoArrays[i-1]=cantUsuarioActivo;
        Serenity.setSessionVariable("cantUsuarioActivoArrays").to(cantUsuarioActivoArrays);
    }
}
