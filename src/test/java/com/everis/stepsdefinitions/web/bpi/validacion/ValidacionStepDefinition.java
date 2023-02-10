package com.everis.stepsdefinitions.web.bpi.validacion;

import com.everis.bpi.stepsdefinitions.web.bpi.login.LoginStepDefinitions;
import com.everis.bpi.tasks.web.bpi.pagosServicios.DetalleEstadoPago;
import com.everis.bpi.tasks.web.bpi.pagosServicios.IngresarCredencialesPago;
import com.everis.bpi.tasks.web.bpi.pagosServicios.SeleccionCuotaIngresaMonto;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class ValidacionStepDefinition {

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Then("^el pago se realiza satisfactoriamente (.*), (.*), (.*)$")
    public void validaSaldoSinDataASolaFirma(String monto, String descripcion, String pass) {
        LoginStepDefinitions.pagosServiciosData.setMontoIncial(monto);
        LoginStepDefinitions.pagosServiciosData.setDescripcion(descripcion);
        LoginStepDefinitions.pagosServiciosData.setPassword(pass);
        theActorInTheSpotlight().attemptsTo(new SeleccionCuotaIngresaMonto());
        theActorInTheSpotlight().attemptsTo(IngresarCredencialesPago.withData(pass, "111111"));
        theActorInTheSpotlight().attemptsTo(DetalleEstadoPago.withData(LoginStepDefinitions.pagosServiciosData.getUsuario(), LoginStepDefinitions.pagosServiciosData.getTipoDeCuenta(), LoginStepDefinitions.pagosServiciosData.getCuentaOrigen()));
    }


}
