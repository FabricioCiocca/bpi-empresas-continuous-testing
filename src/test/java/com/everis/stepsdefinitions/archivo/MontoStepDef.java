package com.everis.stepsdefinitions.archivo;

import com.everis.questions.mainframe.ValidacionCobros;
import com.everis.tasks.mainframe.CalculaMonto;
import com.everis.tasks.mainframe.EliminarLinea;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.core.util.EnvironmentVariables;

import java.io.IOException;
import java.util.ArrayList;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.CoreMatchers.equalTo;

public class MontoStepDef {
    EnvironmentVariables environmentVariables;

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @And("calculo el monto a cobrar")
    public void calculoElMontoACobrar() {

        ArrayList<String> nivelServicioArrays = Serenity.sessionVariableCalled("nivelServicioArrays");
        ArrayList<String> monedaCuentaArrays = Serenity.sessionVariableCalled("monedaCuentaArrays");
        ArrayList<Integer> cantUsuarioActivoConfirmadoArrays = Serenity.sessionVariableCalled("cantUsuarioActivoConfirmadoArrays");
        String[] montoTarifaNivelArray = Serenity.sessionVariableCalled("montoTarifaNivelArray");
        String[] nivelConceptoArray = Serenity.sessionVariableCalled("nivelConceptoArray");
        double tipoCambioDolares = Double.parseDouble(Serenity.sessionVariableCalled("tipoCambioDolares"));

        String[] montoCobrarArrays = new String[nivelServicioArrays.size()];

        for (int i = 0; i < nivelServicioArrays.size(); i++) {
            for (int j = 0; j < nivelConceptoArray.length - 1; j++) {
                if (nivelServicioArrays.get(i).equals(nivelConceptoArray[j])) {
                    if (cantUsuarioActivoConfirmadoArrays.get(i) == null) {
                        CalculaMonto.SinUsuario(i, montoCobrarArrays);
                    } else if (cantUsuarioActivoConfirmadoArrays.get(i) == 0) {
                        CalculaMonto.SinUsuario(i, montoCobrarArrays);
                    } else if (cantUsuarioActivoConfirmadoArrays.get(i) < 4) {
                        CalculaMonto.TipoCambio(i, monedaCuentaArrays, tipoCambioDolares, Double.parseDouble(montoTarifaNivelArray[j]), montoCobrarArrays);
                    } else {
                        CalculaMonto.TipoCambio2(i, monedaCuentaArrays, cantUsuarioActivoConfirmadoArrays, Double.parseDouble(montoTarifaNivelArray[3]), tipoCambioDolares, Double.parseDouble(montoTarifaNivelArray[j]), montoCobrarArrays);
                    }
                }
            }
        }
    }

    @Then("^valido monto en el txt$")
    public void validoMontoEnElTxt() throws IOException {

        ArrayList<String> cuentaCargoArrays = Serenity.sessionVariableCalled("cuentaCargoArrays");
        ArrayList<Integer> cantUsuarioActivoConfirmadoArrays = Serenity.sessionVariableCalled("cantUsuarioActivoConfirmadoArrays");
        String[] montoCobrarArrays = Serenity.sessionVariableCalled("montoCobrarArrays");

        ArrayList<String> informacion = new ArrayList<>();
        ArrayList<String> coherenciaCuentaMonto = new ArrayList<>();
        ArrayList<String> incoherenciasCuentaMonto = new ArrayList<>();

        for (int i = cuentaCargoArrays.size(); i > 0; i--) {
            EliminarLinea.Txt(i, environmentVariables, cuentaCargoArrays, cantUsuarioActivoConfirmadoArrays, montoCobrarArrays, coherenciaCuentaMonto);
        }

        for (int i = cuentaCargoArrays.size(); i > 0; i--) {
            theActorInTheSpotlight().should(
                    seeThat(ValidacionCobros.DetectorIncoherencias(i, environmentVariables, cuentaCargoArrays, incoherenciasCuentaMonto, informacion), equalTo(true)));
        }
    }
}