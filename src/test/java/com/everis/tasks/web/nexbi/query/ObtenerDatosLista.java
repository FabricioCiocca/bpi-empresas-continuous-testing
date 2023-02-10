package com.everis.tasks.web.nexbi.query;

import com.everis.bpi.userinterface.web.nexbi.QueryPage;
import lombok.SneakyThrows;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.questions.TextContent;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ObtenerDatosLista implements Task {
    public final int i;
    public final String[] nombrePuntoServicioArrays;
    public final String[] puntoServicioArrays;

    public ObtenerDatosLista(int i, String[] nombrePuntoServicioArrays, String[] puntoServicioArrays) {
        this.i = i;
        this.nombrePuntoServicioArrays = nombrePuntoServicioArrays;
        this.puntoServicioArrays = puntoServicioArrays;
    }

    public static Performable withData(int i, String[] nombrePuntoServicioArrays, String[] puntoServicioArrays) {
        return instrumented(ObtenerDatosLista.class, i, nombrePuntoServicioArrays, puntoServicioArrays);
    }

    @SneakyThrows
    @Override
    public <T extends Actor> void performAs(T actor) {

        nombrePuntoServicioArrays[i - 1] = TextContent.of(QueryPage.NOMBRE_PUNTO_SERVICIO.of(String.valueOf(i)).called(String.valueOf(i))).answeredBy(actor);
        puntoServicioArrays[i - 1] = TextContent.of(QueryPage.PUNTO_SERVICIO.of(String.valueOf(i)).called(String.valueOf(i))).answeredBy(actor);

        Serenity.setSessionVariable("nombrePuntoServicioArrays").to(nombrePuntoServicioArrays);
        Serenity.setSessionVariable("puntoServicioArrays").to(puntoServicioArrays);
    }
}