package com.everis.stepsdefinitions.web.bpi.pagoDeServicios.dataPendiente;


import com.everis.stepsdefinitions.web.bpi.login.LoginStepDefinitions;
import com.everis.tasks.web.bpi.pagosServicios.dataPendiente.SeleccionCtaEmpresaServicioPendiente;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;


public class PagoNuevoDeServiciosPendienteSD {

    @Before
    public void setTheStage() {

        OnStage.setTheStage(new OnlineCast());

    }

    @And("^intenta realizar un nuevo pago de tipo Pagos - De servicios - A sola firma - Pendiente (.*), (.*), (.*), (.*)")
    public void realizaNuevoPagoServicioPendienteASolaFirma(String tipoCuenta, String cuenta, String empresa, String servicio) throws InterruptedException {

        Thread.sleep(2000);

        theActorInTheSpotlight().attemptsTo(
                SeleccionCtaEmpresaServicioPendiente.withData(cuenta, empresa, servicio));

    }

    @And("^intenta realizar un nuevo pago de servicios con la misma cuenta al mismo servicio - A sola firma - Pendiente")
    public void realizaNuevoPagoServiciosMismaCuentaMismoServicioPendienteASolaFirma() throws InterruptedException {

        Thread.sleep(2000);

        theActorInTheSpotlight().attemptsTo(
                SeleccionCtaEmpresaServicioPendiente.withData(LoginStepDefinitions.pagosServiciosData.getCuentaOrigen(), LoginStepDefinitions.pagosServiciosData.getEmpresa(), LoginStepDefinitions.pagosServiciosData.getServicio()));

    }

    @And("^intenta realizar un nuevo pago de servicios con diferente cuenta a diferente empresa - A sola firma - Pendiente (.*), (.*), (.*), (.*)")
    public void realizaNuevoPagoServiciosDiferenteCuentaDiferenteEmpresaPendienteASolaFirma(String tipoCuenta, String cuenta, String empresa, String servicio) throws InterruptedException {

        Thread.sleep(2000);

        theActorInTheSpotlight().attemptsTo(
                SeleccionCtaEmpresaServicioPendiente.withData(cuenta, empresa, servicio));

    }

    @And("^intenta realizar un nuevo pago de servicios con diferente cuenta a diferente servicio - A sola firma - Pendiente (.*), (.*), (.*)")
    public void realizaNuevoPagoServiciosDiferenteCuentaDiferenteServicioPendienteASolaFirma(String tipoCuenta, String cuenta, String servicio) throws InterruptedException {

        Thread.sleep(2000);

        theActorInTheSpotlight().attemptsTo(
                SeleccionCtaEmpresaServicioPendiente.withData(cuenta, LoginStepDefinitions.pagosServiciosData.getEmpresa(), servicio));

    }

    @And("^intenta realizar un nuevo pago de servicios con la misma cuenta a diferente servicio - A sola firma - Pendiente (.*)")
    public void realizaNuevoPagoServiciosMismaCuentaDiferenteServicioPendienteASolaFirma(String servicio) throws InterruptedException {

        Thread.sleep(2000);

        theActorInTheSpotlight().attemptsTo(
                SeleccionCtaEmpresaServicioPendiente.withData(LoginStepDefinitions.pagosServiciosData.getCuentaOrigen(), LoginStepDefinitions.pagosServiciosData.getEmpresa(), servicio));

    }

    @And("^intenta realizar un nuevo pago de servicios con diferente cuenta al mismo servicio - A sola firma - Pendiente (.*), (.*)")
    public void realizaNuevoPagoServiciosDiferenteCuentaMismoServicioPendienteASolaFirma(String tipoCuenta, String cuenta) throws InterruptedException {

        Thread.sleep(2000);

        theActorInTheSpotlight().attemptsTo(
                SeleccionCtaEmpresaServicioPendiente.withData(cuenta, LoginStepDefinitions.pagosServiciosData.getEmpresa(), LoginStepDefinitions.pagosServiciosData.getServicio()));

    }
}
