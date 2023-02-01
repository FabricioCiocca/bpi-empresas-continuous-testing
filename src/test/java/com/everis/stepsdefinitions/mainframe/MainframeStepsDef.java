package com.everis.stepsdefinitions.mainframe;

import com.everis.tasks.mainframe.*;
import environment.ManageEnvironment;
import io.cucumber.java.Before;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import mainframe.com.bdd.generic.Constants;
import mainframe.com.bdd.lib.EmulatorActions;
import mainframe.com.bdd.util.UtilMainframe;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import java.util.ArrayList;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class MainframeStepsDef extends EmulatorActions {
    //private Scenario scenario;



    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @And("se ingresa al CICS con (.*) credencial$")
    public void ingresoAlCICSConCredencial(String cantCredencial) {

        // scenario =
        UtilMainframe.getVariableOnSession(Constants.SCENARIO);
        getInstancia().connectServer(ManageEnvironment.getEnvironment());

        theActorCalled("User").attemptsTo(
                new AccederMainframe());

        if (cantCredencial.equals("1")) {
            theActorInTheSpotlight().attemptsTo(
                    new AgregarCredencialUno(),
                    new ConsultaDirecta());
        } else if (cantCredencial.equals("2")) {
            theActorInTheSpotlight().attemptsTo(
                    new AgregarCredencialDos(),
                    new LimpiarAcceso(),
                    new AgregarCredencialDos(),
                    new ConsultaIndirecta(),
                    new AgregarCredencialUno());
        } else {
            throw new PendingException("Máximo 2 Usuarios");
        }
    }

    @And("consulto tarifarios por nivel de servicio")
    public void consultoTarifariosPorNivelDeServicio() {

        theActorInTheSpotlight().attemptsTo(
                IngresarAplicativo.withData("26"));

        String codigoUnico= Serenity.sessionVariableCalled("codigoUnico");
        ArrayList<String> listaTarifaPreferencial = new ArrayList<>();

        String[] numeroConceptoArray={"20","21","22","40"};

        for(int i=0;i<numeroConceptoArray.length;i++) {
            theActorInTheSpotlight().attemptsTo(
                    ConsultaTarifaNormal.withData(numeroConceptoArray[i]),
                    ObtenerMontoTarifa.withData(numeroConceptoArray[i]),
                    new VisualizarVentanaAnterior(),
                    new VisualizarVentanaAnterior(),
                    new VisualizarVentanaAnterior(),
                    ConsultaTarifaPreferencial.withData(numeroConceptoArray[i], codigoUnico));

            String mensajeCliente = Serenity.sessionVariableCalled("mensajeCliente");
            if (mensajeCliente.equals("Cliente no existe")) {
                theActorInTheSpotlight().attemptsTo(
                        new VisualizarVentanaAnterior());
            }else {
                theActorInTheSpotlight().attemptsTo(
                        ObtenerMontoTarifa.withData(numeroConceptoArray[i]),
                        new VisualizarVentanaAnterior(),
                        new VisualizarVentanaAnterior(),
                        new VisualizarVentanaAnterior());

                if (numeroConceptoArray[i].equals("20")) {
                    listaTarifaPreferencial.add("Nivel Negocio:      Tarifa Preferecial");
                } else if (numeroConceptoArray[i].equals("21")) {
                    listaTarifaPreferencial.add("Nivel Empresa:      Tarifa Preferecial");
                } else if (numeroConceptoArray[i].equals("22")) {
                    listaTarifaPreferencial.add("Nivel Corporativo:  Tarifa Preferecial");
                } else if (numeroConceptoArray[i].equals("40")) {
                    listaTarifaPreferencial.add("Usuario Adicional:  Tarifa Preferecial");
                }
            }
        }
        theActorInTheSpotlight().attemptsTo(
                new CerrarSesionMainframe()
        );
        Serenity.setSessionVariable("listaTarifaPreferencial").to(listaTarifaPreferencial);
    }
}
