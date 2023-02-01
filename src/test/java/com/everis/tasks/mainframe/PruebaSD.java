package com.everis.tasks.mainframe;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.serenitybdd.core.Serenity;

import java.util.ArrayList;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class PruebaSD {


    @And("^ingreso una (.*), (.*) y (.*)$")
    public void ingresoUnaTarifaPreferecial(String tarifaPreferencial, String monto, String fechaVencimiento) {

        ArrayList<String> listaTarifaPreferencial = Serenity.sessionVariableCalled("listaTarifaPreferencial");
        String codigoUnico = Serenity.sessionVariableCalled("codigoUnico");

        for (int i = 0; i < listaTarifaPreferencial.size(); i++) {
            if (listaTarifaPreferencial.get(i).contains(tarifaPreferencial)) {
                throw new PendingException("El Codigo Unico " + codigoUnico + " ya tiene Tarifa Preferencial");
            }
        }
        theActorInTheSpotlight().attemptsTo(
                IngresaTarifaPreferencial.withData(tarifaPreferencial, codigoUnico, fechaVencimiento, monto));
    }

    @Then("consulto tarifa preferecial ingresado por nivel de servicio")
    public void consultoTarifaPreferecialIngresadoPorNivelDeServicio() {

        String codigoUnico = Serenity.sessionVariableCalled("codigoUnico");
        String numeroConcepto = Serenity.sessionVariableCalled("numeroConcepto");

        theActorInTheSpotlight().attemptsTo(
                ConsultaTarifaPreferencial.withData(numeroConcepto, codigoUnico));

        String mensajeCliente = Serenity.sessionVariableCalled("mensajeCliente");
        if (mensajeCliente.equals("Cliente no existe")) {
            throw new PendingException("No se Ingreso Correctamente la Tarifa Preferencial");
        } else {
            theActorInTheSpotlight().attemptsTo(
                    ObtenerMontoTarifa.withData(numeroConcepto),
                    new VisualizarVentanaAnterior(),
                    new VisualizarVentanaAnterior(),
                    new VisualizarVentanaAnterior());
        }
    }
}
