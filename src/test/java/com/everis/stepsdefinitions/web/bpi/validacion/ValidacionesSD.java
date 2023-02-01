package com.everis.stepsdefinitions.web.bpi.validacion;

import com.everis.stepsdefinitions.web.bpi.login.LoginStepDefinitions;
import com.everis.tasks.web.bpi.validacion.ValidarMovimientoPendiente;
import com.everis.tasks.web.bpi.validacion.ValidarNuevoSaldoPendiente;
import com.everis.tasks.web.bpi.validacion.ValidarSaldoPendiente;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;


public class ValidacionesSD {

    @Before
    public void setTheStage() {

        OnStage.setTheStage(new OnlineCast());

    }

    @And("valida el Saldo y Movimiento")
    public void validaElSaldoYMovimiento() throws InterruptedException {
        theActorInTheSpotlight().attemptsTo(
                ValidarNuevoSaldoPendiente.withData(LoginStepDefinitions.pagosServiciosData.getMonto(), LoginStepDefinitions.pagosServiciosData.getCuentaOrigen(), LoginStepDefinitions.pagosServiciosData.getTipoDeCuenta()));

        Thread.sleep(1000);

        theActorInTheSpotlight().attemptsTo(
                ValidarMovimientoPendiente.withData(LoginStepDefinitions.pagosServiciosData.getMonto(), LoginStepDefinitions.pagosServiciosData.getEmpresa()));

        Thread.sleep(3000);

    }

    @And("valida el saldo")
    public void validaElSaldo() {

        theActorInTheSpotlight().attemptsTo(
                ValidarSaldoPendiente.withData(LoginStepDefinitions.pagosServiciosData.getMonto(), LoginStepDefinitions.pagosServiciosData.getCuentaOrigen(), LoginStepDefinitions.pagosServiciosData.getTipoDeCuenta()));

    }
}
