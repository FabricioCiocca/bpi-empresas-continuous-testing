package com.everis.stepsdefinitions.web.bpi.pagoDeServicios.sinData;


import com.everis.questions.web.bpi.NegatividadQuestions;
import com.everis.stepsdefinitions.web.bpi.login.LoginStepDefinitions;
import com.everis.tasks.web.bpi.pagosServicios.*;
import com.everis.tasks.web.bpi.pagosServicios.sindata.DetalleEstadoPagoRechazado;
import com.everis.tasks.web.bpi.pagosServicios.sindata.NoSeleccionCuotaIngresaMonto;
import com.everis.tasks.web.bpi.validacion.ValidarMovimiento;
import com.everis.tasks.web.bpi.validacion.ValidarSaldo;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.equalTo;


public class SinDataSolaFirmaStepDefinitions {
    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @When("^intenta realizar un pago de tipo Pagos - De servicios - A sola firma - Sin data (.*), (.*), (.*), (.*), (.*)$")
    public void intenta_realizar_un_pago_de_tipo_pagos_de_servicios_a_sola_firma_sin_data_corriente_soles_fe_y_alegría_donaciones(String tipoCuenta, String cuenta, String empresa, String servicio, String dniPagador) throws InterruptedException{
        //theActorInTheSpotlight().attemptsTo(new MenuBotonEntendido());

        theActorInTheSpotlight().attemptsTo(new MenuPagoServicios());
        String[] dniPagadorArray = dniPagador.split("-");
        for (int i = 0; i < dniPagadorArray.length; i++) {
            theActorInTheSpotlight().attemptsTo(SeleccionCtaEmpresaServicioCodDeudor.withData(cuenta, empresa, servicio, dniPagadorArray[i]));
        }
        LoginStepDefinitions.pagosServiciosData.setTipoDeCuenta(tipoCuenta);
        LoginStepDefinitions.pagosServiciosData.setCuentaOrigen(cuenta);
        LoginStepDefinitions.pagosServiciosData.setEmpresa(empresa);
        LoginStepDefinitions.pagosServiciosData.setServicio(servicio);
        LoginStepDefinitions.pagosServiciosData.setDniPagador(dniPagador);
        LoginStepDefinitions.pagosServiciosData.setCantdni(String.valueOf(dniPagadorArray.length));
    }

    @And("^valida los Saldos y Movimientos (.*), (.*), (.*), (.*)$")
    public void validarSaldosYMovimientos(String monto, String cuentaOrigen, String tipoCuenta, String empresa) throws InterruptedException {
        theActorInTheSpotlight().attemptsTo(ValidarSaldo.withData(monto, cuentaOrigen, tipoCuenta));

        theActorInTheSpotlight().attemptsTo(ValidarMovimiento.withData(monto, empresa));
        LoginStepDefinitions.pagosServiciosData.setTipoDeCuenta(tipoCuenta);
    }

    @And("^intenta realizar un nuevo pago de tipo Pagos - De servicios - A sola firma - Sin data (.*)$")
    public void intentaRealizarUnNuevoPagoDeTipoPagosDeServiciosASolaFirmaSinDataTipoCuentaCuentaOrigen(String cuentaOrigen2) {
        LoginStepDefinitions.pagosServiciosData.setCuentaOrigen2SinData(cuentaOrigen2);
        theActorInTheSpotlight().attemptsTo(new RegistraPagoNuevo());
        theActorInTheSpotlight().attemptsTo(new SeleccionCtaEmpresaServicioCodDeudorNuevoPago());
    }

    @And("validos los Saldos y Movimientos")
    public void validos_los_saldos_y_movimientos() throws InterruptedException {
        theActorInTheSpotlight().attemptsTo(ValidarSaldo.withData(LoginStepDefinitions.pagosServiciosData.getMonto(), LoginStepDefinitions.pagosServiciosData.getCuentaOrigen(), LoginStepDefinitions.pagosServiciosData.getTipoDeCuenta()));
        theActorInTheSpotlight().attemptsTo(ValidarMovimiento.withData(LoginStepDefinitions.pagosServiciosData.getMonto(), LoginStepDefinitions.pagosServiciosData.getEmpresa()));
    }
    @And("^intenta realizar un nuevo pago  con el mismo servicio de tipo Pagos - De servicios - A sola firma - Sin data (.*)$")
    public void intentaRealizarUnNuevoPagoConElMismoServicioDeTipoPagosDeServiciosASolaFirmaSinDataServicio(String Servicio2) {
        LoginStepDefinitions.pagosServiciosData.setServicio2SinData(Servicio2);
        theActorInTheSpotlight().attemptsTo(new RegistraPagoNuevo());
        theActorInTheSpotlight().attemptsTo(new NuevoServicio());
    }

    @Then("^el pago no se realiza satisfactoriamente (.*); (.*); (.*)$")
    public void elPagoNoSeRealizaSatisfactoriamenteMontoDescripcionPassword(String monto, String descripcion, String pass) {
        LoginStepDefinitions.pagosServiciosData.setMontoIncial(monto);
        LoginStepDefinitions.pagosServiciosData.setDescripcion(descripcion);
        LoginStepDefinitions.pagosServiciosData.setPassword(pass);
        theActorInTheSpotlight().attemptsTo(new SeleccionCuotaIngresaMonto());
        theActorInTheSpotlight().attemptsTo(IngresarCredencialesPago.withData(pass, "111111"));
        theActorInTheSpotlight().attemptsTo(DetalleEstadoPagoRechazado.withData(LoginStepDefinitions.pagosServiciosData.getUsuario(), LoginStepDefinitions.pagosServiciosData.getTipoDeCuenta(), LoginStepDefinitions.pagosServiciosData.getCuentaOrigen()));

    }

    @Then("valida que el servicio ya existe")
    public void validaQueElServicioYaExiste() {
        theActorInTheSpotlight().should(seeThat(NegatividadQuestions.ServicioYaAgregado(), equalTo("SERVICIO YA AGREGADO")));
    }

    @Then("^ingreso contraseña incorrecta (.*); (.*); (.*)$")
    public void validaQueLaContraseñaEsIncorrectaMontoDescripcionPassword(String monto, String descripcion, String pass) {
        LoginStepDefinitions.pagosServiciosData.setMontoIncial(monto);
        LoginStepDefinitions.pagosServiciosData.setDescripcion(descripcion);
        LoginStepDefinitions.pagosServiciosData.setPassword(pass);
        theActorInTheSpotlight().attemptsTo(new SeleccionCuotaIngresaMonto());
        theActorInTheSpotlight().attemptsTo(IngresarCredencialesPago.withData(pass, "111111"));
    }

    @And("valido que la contraseña es incorrecta")
    public void validoQueLaContraseñaEsIncorrecta() {
        theActorInTheSpotlight().should(seeThat(NegatividadQuestions.ParametrosSeguridad(), equalTo("No cumple con los parámetros de seguridad")));

    }


    @Then("valida que no se agregue mas de 10 servicios")
    public void validaQueNoSeAgregueMasDeServicios() {
        theActorInTheSpotlight().should(seeThat(NegatividadQuestions.LimiteServicio(), equalTo("LÍMITE DE SERVICIOS")));
    }

    @Then("^no seleccionamos la cuota para pagar (.*), (.*)$")
    public void noSeleccionamosLaCuotaParaPagar(String monto, String descripcion) {
        LoginStepDefinitions.pagosServiciosData.setMontoIncial(monto);
        LoginStepDefinitions.pagosServiciosData.setDescripcion(descripcion);
        theActorInTheSpotlight().attemptsTo(new NoSeleccionCuotaIngresaMonto());

    }

    @And("valida que debes seleccionar la cuota del servicio")
    public void validaQueDebesSeleccionarLaCuotaDelServicio() {
        theActorInTheSpotlight().should(seeThat(NegatividadQuestions.DebesSelecciomarCuota(), equalTo("Debes seleccionar al menos una cuota para poder realizar el pago correspondiente.")));
    }


    @Then("^el pago supera a la mayor combinación de firma (.*)$")
    public void elPagoSuperaALaMayorCombinaciónDeFirmaMontoDescripcionPassword(String monto) {
        LoginStepDefinitions.pagosServiciosData.setMontoIncial(monto);
        theActorInTheSpotlight().attemptsTo(new SeleccionCuotaIngresaMonto());
    }

    @And("valida que el monto supera los limites")
    public void validaQueElMontoSuperaLosLimites() {
        theActorInTheSpotlight().should(seeThat(NegatividadQuestions.MontoSuperaLimite(), equalTo("MONTO INGRESADO SUPERA LÍMITES")));
    }

    @And("valida que el monto sea mayor a 0")
    public void validaQueElMontoSeaMayorA() {
        theActorInTheSpotlight().should(seeThat(NegatividadQuestions.DebesIngresarUnMontoValido(), equalTo("Debes ingresar un monto válido")));
    }





}