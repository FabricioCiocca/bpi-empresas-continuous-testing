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

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class ObtenerInformacionGeneral implements Task {
    public final int i;
    public final String[] tipoClienteArrays;
    public final String[] nivelServicioArrays;
    public final String[] cuentaCargoArrays;
    public final String[] monedaCuentaArrays;

    public ObtenerInformacionGeneral(int i, String[] tipoClienteArrays, String[] nivelServicioArrays, String[] cuentaCargoArrays, String[] monedaCuentaArrays) {
        this.i = i;
        this.tipoClienteArrays = tipoClienteArrays;
        this.nivelServicioArrays = nivelServicioArrays;
        this.cuentaCargoArrays = cuentaCargoArrays;
        this.monedaCuentaArrays = monedaCuentaArrays;
    }

    public static Performable withData(int i, String[] tipoClienteArrays, String[] nivelServicioArrays, String[] cuentaCargoArrays, String[] monedaCuentaArrays) {
        return instrumented(ObtenerInformacionGeneral.class, i, tipoClienteArrays, nivelServicioArrays, cuentaCargoArrays, monedaCuentaArrays);
    }

    @SneakyThrows
    @Override
    public <T extends Actor> void performAs(T actor) {
        Thread.sleep(3500);
        actor.attemptsTo(
                Click.on(QueryPage.TABLE_PUNTOSERVICIO_RECORRIDO.of(String.valueOf(i)).called(String.valueOf(i))));

        /*Obtenemos la Moneda de la Cuenta desde el Intranet (Bucle que ayuda a la aparecion de la moneda)*/
        do {
            monedaCuentaArrays[i - 1] = NexbiQuestions.monedaCuenta().answeredBy(theActorInTheSpotlight());
        } while (monedaCuentaArrays[i - 1].equals(" - ") || monedaCuentaArrays[i - 1].equals(""));

        /*Obtenemos el Check de Autogestión desde el Intranet*/
        boolean isEnabled = actor.asksFor(CurrentVisibility.of(QueryPage.CHECKBOX_TIPO_ACCESO));

        /*Obtenemos el tipo de Cliente desde el Intranet */
        tipoClienteArrays[i - 1] = NexbiQuestions.tipoCliente().answeredBy(theActorInTheSpotlight());

        /*Obtenemos el Nivel de Servicio desde el Intranet */
        nivelServicioArrays[i - 1] = NexbiQuestions.nivelServicio().answeredBy(theActorInTheSpotlight());

        /*Obtenemos la Cuenta de Cargo desde el Intranet */
        cuentaCargoArrays[i - 1] = NexbiQuestions.cuentaCargo().answeredBy(theActorInTheSpotlight());

        Serenity.setSessionVariable("checkBoxTipoAcceso").to(isEnabled);
        Serenity.setSessionVariable("tipoClienteArrays").to(tipoClienteArrays);
        Serenity.setSessionVariable("nivelServicioArrays").to(nivelServicioArrays);
        Serenity.setSessionVariable("cuentaCargoArrays").to(cuentaCargoArrays);
        Serenity.setSessionVariable("monedaCuentaArrays").to(monedaCuentaArrays);
    }
}
