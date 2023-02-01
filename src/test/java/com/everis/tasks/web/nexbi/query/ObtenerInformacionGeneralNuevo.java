package com.everis.tasks.web.nexbi.query;

import com.everis.questions.web.nexbi.NexbiQuestions;
import com.everis.userinterfaces.web.nexbi.QueryPage;
import lombok.SneakyThrows;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.questions.CurrentVisibility;

import java.util.ArrayList;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class ObtenerInformacionGeneralNuevo implements Task {
    public final int j;
    public final ArrayList<String> nombrePuntoServicioArrays;
    public final ArrayList<String> tipoClienteArrays;
    public final ArrayList<String> nivelServicioArrays;
    public final ArrayList<String> cuentaCargoArrays;
    public final ArrayList<String> monedaCuentaArrays;

    public ObtenerInformacionGeneralNuevo(int j, ArrayList<String> nombrePuntoServicioArrays, ArrayList<String> tipoClienteArrays, ArrayList<String> nivelServicioArrays, ArrayList<String> cuentaCargoArrays, ArrayList<String> monedaCuentaArrays) {
        this.j = j;
        this.nombrePuntoServicioArrays = nombrePuntoServicioArrays;
        this.tipoClienteArrays = tipoClienteArrays;
        this.nivelServicioArrays = nivelServicioArrays;
        this.cuentaCargoArrays = cuentaCargoArrays;
        this.monedaCuentaArrays = monedaCuentaArrays;
    }

    public static Performable withData(int j, ArrayList<String> nombrePuntoServicioArrays, ArrayList<String> tipoClienteArrays, ArrayList<String> nivelServicioArrays, ArrayList<String> cuentaCargoArrays, ArrayList<String> monedaCuentaArrays) {
        return instrumented(ObtenerInformacionGeneralNuevo.class, j, nombrePuntoServicioArrays, tipoClienteArrays, nivelServicioArrays, cuentaCargoArrays, monedaCuentaArrays);
    }

    @SneakyThrows
    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Click.on(QueryPage.TABLE_PUNTOSERVICIO_RECORRIDO.of(String.valueOf(j)).called(String.valueOf(j))));

        /*Obtenemos la Moneda de la Cuenta desde el Intranet (Bucle que ayuda a la aparecion de la moneda)*/
        int cont=0;
        do {
            if(cont>0) {
                monedaCuentaArrays.remove(nombrePuntoServicioArrays.size()-1);
            }
            monedaCuentaArrays.add(NexbiQuestions.monedaCuenta().answeredBy(theActorInTheSpotlight()));
            cont++;
        } while (monedaCuentaArrays.get(nombrePuntoServicioArrays.size()-1).equals(" - ") || monedaCuentaArrays.get(nombrePuntoServicioArrays.size()-1).equals(""));

        /*Obtenemos el Check de Autogestión desde el Intranet*/
        boolean isEnabled = actor.asksFor(CurrentVisibility.of(QueryPage.CHECKBOX_TIPO_ACCESO));

        /*Obtenemos el tipo de Cliente desde el Intranet */
        tipoClienteArrays.add(NexbiQuestions.tipoCliente().answeredBy(theActorInTheSpotlight()));

        /*Obtenemos el Nivel de Servicio desde el Intranet */
        nivelServicioArrays.add(NexbiQuestions.nivelServicio().answeredBy(theActorInTheSpotlight()));

        /*Obtenemos la Cuenta de Cargo desde el Intranet */
        cuentaCargoArrays.add(NexbiQuestions.cuentaCargo().answeredBy(theActorInTheSpotlight()));

        Serenity.setSessionVariable("checkBoxTipoAcceso").to(isEnabled);
        Serenity.setSessionVariable("tipoClienteArrays").to(tipoClienteArrays);
        Serenity.setSessionVariable("nivelServicioArrays").to(nivelServicioArrays);
        Serenity.setSessionVariable("cuentaCargoArrays").to(cuentaCargoArrays);
        Serenity.setSessionVariable("monedaCuentaArrays").to(monedaCuentaArrays);
    }
}
