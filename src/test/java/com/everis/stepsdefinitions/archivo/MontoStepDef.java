package com.everis.stepsdefinitions.archivo;


import com.everis.questions.mainframe.ValidacionCobros;
import com.everis.tasks.mainframe.CalculaMonto2;
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
        ArrayList<Integer> cantUsuarioActivoArrays = Serenity.sessionVariableCalled("cantUsuarioActivoArrays");
        double montoTarifaUsuario = Double.parseDouble(Serenity.sessionVariableCalled("montoTarifaNivel40"));
        double tipoCambioDolares = Double.parseDouble(Serenity.sessionVariableCalled("tipoCambioDolares"));

        String[] montoCobrarArrays = new String[nivelServicioArrays.size()];

        for (int i = 0; i < nivelServicioArrays.size(); i++) {

            double montoTarifaNivel = 0;
            if (nivelServicioArrays.get(i).trim().equals("Negocio")) {
                montoTarifaNivel = Double.parseDouble(Serenity.sessionVariableCalled("montoTarifaNivel20"));
            } else if (nivelServicioArrays.get(i).trim().equals("Empresa")) {
                montoTarifaNivel = Double.parseDouble(Serenity.sessionVariableCalled("montoTarifaNivel21"));
            } else if (nivelServicioArrays.get(i).trim().equals("Corporativo")) {
                montoTarifaNivel = Double.parseDouble(Serenity.sessionVariableCalled("montoTarifaNivel22"));
            }

            if(cantUsuarioActivoArrays.get(i)==0){
                CalculaMonto2.SinUser(i, montoCobrarArrays);
            } else if (cantUsuarioActivoArrays.get(i) < 4) {
                CalculaMonto2.TipoCambio(i, monedaCuentaArrays, tipoCambioDolares, montoTarifaNivel, montoCobrarArrays);
            } else {
                CalculaMonto2.TipoCambio2(i, monedaCuentaArrays, cantUsuarioActivoArrays, montoTarifaUsuario, tipoCambioDolares, montoTarifaNivel, montoCobrarArrays);
            }
        }
    }

    @Then("^valido monto en el txt$")
    public void validoMontoEnElTxt() throws IOException {
        ArrayList<String> cuentaCargoArrays = Serenity.sessionVariableCalled("cuentaCargoArrays");
        ArrayList<Integer> cantUsuarioActivoArrays = Serenity.sessionVariableCalled("cantUsuarioActivoArrays");
        String[] montoCobrarArrays = Serenity.sessionVariableCalled("montoCobrarArrays");

        ArrayList<String> informacion = new ArrayList<>();
        String[] coherenciaCuentaMonto = new String[cuentaCargoArrays.size()];
        String[] incoherenciasCuentaMonto = new String[cuentaCargoArrays.size()];

        for (int i = cuentaCargoArrays.size(); i > 0; i--) {
            theActorInTheSpotlight().should(
                    seeThat(ValidacionCobros.valideText(i, environmentVariables, cuentaCargoArrays, cantUsuarioActivoArrays, montoCobrarArrays, coherenciaCuentaMonto, incoherenciasCuentaMonto, informacion), equalTo(true)));
        }
    }


    @Then("valido que el monto no exista en el txt")
    public void validoQueElMontoNoExistaEnElTxt() throws IOException {
        ArrayList<String> cuentaCargoArrays = Serenity.sessionVariableCalled("cuentaCargoArrays");
        ArrayList<Integer> cantUsuarioActivoArrays = Serenity.sessionVariableCalled("cantUsuarioActivoArrays");
        String[] montoCobrarArrays = Serenity.sessionVariableCalled("montoCobrarArrays");

        ArrayList<String> informacion = new ArrayList<>();
        String[] coherenciaCuentaMonto = new String[cuentaCargoArrays.size()];
        String[] incoherenciasCuentaMonto = new String[cuentaCargoArrays.size()];

        for (int i = cuentaCargoArrays.size(); i > 0; i--) {
            theActorInTheSpotlight().should(
                    seeThat(ValidacionCobros.valideText(i, environmentVariables, cuentaCargoArrays, cantUsuarioActivoArrays, montoCobrarArrays, coherenciaCuentaMonto, incoherenciasCuentaMonto, informacion), equalTo(true)));
        }
    }
}
