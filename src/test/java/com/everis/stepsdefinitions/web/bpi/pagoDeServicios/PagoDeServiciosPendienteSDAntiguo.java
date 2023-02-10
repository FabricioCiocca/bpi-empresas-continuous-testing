package com.everis.stepsdefinitions.web.bpi.pagoDeServicios;


import com.everis.bpi.stepsdefinitions.web.bpi.login.LoginStepDefinitions;
import com.everis.bpi.tasks.web.bpi.pagosServicios.IngresarCredencialesPago;
import com.everis.bpi.tasks.web.bpi.pagosServicios.MenuPagoServicios;
import com.everis.bpi.tasks.web.bpi.pagosServicios.SeleccionCtaEmpresaServicioCodDeudor;
import com.everis.bpi.tasks.web.bpi.pagosServicios.dataPendiente.DetalleEstadoPagoPendienteFC;
import com.everis.bpi.tasks.web.bpi.pagosServicios.dataPendiente.DetalleEstadoPagoPendienteGF;
import com.everis.bpi.tasks.web.bpi.pagosServicios.dataPendiente.DetalleEstadoPagoPendienteSF;
import com.everis.bpi.tasks.web.bpi.pagosServicios.dataPendiente.SeleccionCuotaIngresaMontoPendienteAntiguo;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;


public class PagoDeServiciosPendienteSDAntiguo {
    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @When("^intenta realizar un pago (.*) de tipo Pagos - De servicios (.*), (.*), (.*), (.*), (.*)")
    public void realizaPagoServicioPendiente(String tipoPago, String tipoCuenta, String cuenta, String empresa, String servicio, String dniPagador) throws InterruptedException {
        // theActorInTheSpotlight().attemptsTo(new MenuBotonEntendido());
        theActorInTheSpotlight().attemptsTo(
                new MenuPagoServicios());

        String[] dniPagadorArray = dniPagador.split("-");

        for (int i = 0; i < dniPagadorArray.length; i++) {

            theActorInTheSpotlight().attemptsTo(
                    SeleccionCtaEmpresaServicioCodDeudor.withData(cuenta, empresa, servicio, dniPagadorArray[i]));

        }

        LoginStepDefinitions.pagosServiciosData.setTipoDePago(tipoPago);
        LoginStepDefinitions.pagosServiciosData.setTipoDeCuenta(tipoCuenta);
        LoginStepDefinitions.pagosServiciosData.setCuentaOrigen(cuenta);
        LoginStepDefinitions.pagosServiciosData.setEmpresa(empresa);
        LoginStepDefinitions.pagosServiciosData.setServicio(servicio);
        LoginStepDefinitions.pagosServiciosData.setDniPagador(dniPagador);
        LoginStepDefinitions.pagosServiciosData.setCantdni(String.valueOf(dniPagadorArray.length));
    }

    @Then("el pago se realiza con exito (.*), (.*), (.*)$")
    public void elPagoSeRealizaConExito(String monto, String descripcion, String pass) {

        LoginStepDefinitions.pagosServiciosData.setMontoIncial(monto);
        LoginStepDefinitions.pagosServiciosData.setDescripcion(descripcion);
        LoginStepDefinitions.pagosServiciosData.setPassword(pass);

        theActorInTheSpotlight().attemptsTo(
                new SeleccionCuotaIngresaMontoPendienteAntiguo());

        theActorInTheSpotlight().attemptsTo(
                IngresarCredencialesPago.withData(pass, "111111"));


        double montoOriginal;

        if (Double.parseDouble(LoginStepDefinitions.pagosServiciosData.getmontoTipoCambio()) > 0) {

            montoOriginal = Double.parseDouble(LoginStepDefinitions.pagosServiciosData.getmontoTipoCambio());

        } else {

            montoOriginal = Double.parseDouble(LoginStepDefinitions.pagosServiciosData.getMonto().replace(LoginStepDefinitions.pagosServiciosData.getSimbolo() + " ", "").replace(",", ""));

        }

        System.out.println("el monto original es: " + montoOriginal);

        System.out.println("el monto sola firma es: " + LoginStepDefinitions.pagosServiciosData.getMontoSolaFirma());
        System.out.println("el monto firma conjunta es: " + LoginStepDefinitions.pagosServiciosData.getMontoFirmaConjunta());

        // ELIGE QUE DETALLE SALDRA SEGUN LA FIRMA

        if (montoOriginal <= Double.parseDouble(LoginStepDefinitions.pagosServiciosData.getMontoSolaFirma())) {

            theActorInTheSpotlight().attemptsTo(
                    DetalleEstadoPagoPendienteSF.withData(LoginStepDefinitions.pagosServiciosData.getUsuario(), LoginStepDefinitions.pagosServiciosData.getTipoDeCuenta(), LoginStepDefinitions.pagosServiciosData.getCuentaOrigen()));

        } else if (montoOriginal <= Double.parseDouble(LoginStepDefinitions.pagosServiciosData.getMontoFirmaConjunta())) {

            theActorInTheSpotlight().attemptsTo(
                    DetalleEstadoPagoPendienteFC.withData(LoginStepDefinitions.pagosServiciosData.getUsuario(), LoginStepDefinitions.pagosServiciosData.getTipoDeCuenta(), LoginStepDefinitions.pagosServiciosData.getCuentaOrigen()));

        } else {

            theActorInTheSpotlight().attemptsTo(
                    DetalleEstadoPagoPendienteGF.withData(LoginStepDefinitions.pagosServiciosData.getUsuario(), LoginStepDefinitions.pagosServiciosData.getTipoDeCuenta(), LoginStepDefinitions.pagosServiciosData.getCuentaOrigen()));

        }
    }
}
