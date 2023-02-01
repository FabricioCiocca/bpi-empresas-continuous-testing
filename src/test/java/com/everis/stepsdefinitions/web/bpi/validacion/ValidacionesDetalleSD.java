package com.everis.stepsdefinitions.web.bpi.validacion;

import com.everis.stepsdefinitions.web.bpi.login.LoginStepDefinitions;
import com.everis.tasks.web.bpi.pagosServicios.dataPendiente.DetalleEstadoPagoPendienteFC;
import com.everis.tasks.web.bpi.pagosServicios.dataPendiente.DetalleEstadoPagoPendienteGF;
import com.everis.tasks.web.bpi.pagosServicios.dataPendiente.DetalleEstadoPagoPendienteSF;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;


public class ValidacionesDetalleSD {

    @Before
    public void setTheStage() {

        OnStage.setTheStage(new OnlineCast());

    }

    @Then("el pago a sola firma se realiza con exito$")
    public void elPagoSolaFirmaSeRealizaConExito() {

        theActorInTheSpotlight().attemptsTo(
                DetalleEstadoPagoPendienteSF.withData(LoginStepDefinitions.pagosServiciosData.getUsuario(), LoginStepDefinitions.pagosServiciosData.getTipoDeCuenta(), LoginStepDefinitions.pagosServiciosData.getCuentaOrigen()));

    }

    @Then("el pago a firma conjunta se realiza con exito$")
    public void elPagoFirmaConjuntaSeRealizaConExito() {

        theActorInTheSpotlight().attemptsTo(
                DetalleEstadoPagoPendienteFC.withData(LoginStepDefinitions.pagosServiciosData.getUsuario(), LoginStepDefinitions.pagosServiciosData.getTipoDeCuenta(), LoginStepDefinitions.pagosServiciosData.getCuentaOrigen()));

    }

    @Then("el pago a firma combinada se realiza con exito$")
    public void elPagoFirmaCombinadaSeRealizaConExito() {

        theActorInTheSpotlight().attemptsTo(
                DetalleEstadoPagoPendienteGF.withData(LoginStepDefinitions.pagosServiciosData.getUsuario(), LoginStepDefinitions.pagosServiciosData.getTipoDeCuenta(), LoginStepDefinitions.pagosServiciosData.getCuentaOrigen()));

    }
}
