package com.everis.stepsdefinitions.web.bpi.pagoDeServicios.dataMaestra;

import com.everis.stepsdefinitions.web.bpi.login.LoginStepDefinitions;
import com.everis.tasks.web.bpi.pagosServicios.*;
import com.everis.tasks.web.bpi.validacion.ValidarMovimiento;
import com.everis.tasks.web.bpi.validacion.ValidarMovimientoDataMaestra;
import com.everis.tasks.web.bpi.validacion.ValidarSaldoDataMaestra;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;


public class PagoDeServiciosDataMaestra {
    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @When("^intenta realizar un pago de tipo Pagos - De servicios - A sola firma - data Maestra (.*), (.*), (.*), (.*), (.*)")
    public void realizaPagoServicioDataMaestraASolaFirma(String tipoCuenta, String cuenta, String empresa, String servicio, String dniPagador) throws InterruptedException {
        theActorInTheSpotlight().attemptsTo(new MenuBotonEntendido());
        Thread.sleep(1000);
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

    @And("^validarrr los Saldos y Movimientos (.*), (.*), (.*), (.*)$")
    public void validarrrLosSaldosYMovimientos(String monto, String cuentaOrigen, String tipoCuenta, String empresa) throws InterruptedException {
        theActorInTheSpotlight().attemptsTo(ValidarSaldoDataMaestra.withData(monto, cuentaOrigen, tipoCuenta));
        Thread.sleep(1000);
        theActorInTheSpotlight().attemptsTo(ValidarMovimiento.withData(monto, empresa));
        LoginStepDefinitions.pagosServiciosData.setTipoDeCuenta(tipoCuenta);
    }

    @And("^intenta realizar un nuevo pago de tipo Pagos - De servicios - A sola firma - data Maestra (.*)$")
    public void intentaRealizarUnNuevoPagoDeTipoPagosDeServiciosASolaFirmaSinDataTipoCuentaCuentaOrigen(String cuentaOrigen2) {
        LoginStepDefinitions.pagosServiciosData.setCuentaOrigen2SinData(cuentaOrigen2);
        theActorInTheSpotlight().attemptsTo(new RegistraPagoNuevo());
        theActorInTheSpotlight().attemptsTo(new SeleccionCtaEmpresaServicioCodDeudorNuevoPago());
    }

    @And("valida los Saldos y Movimientos")
    public void validaLosSaldosYMovimientos() throws InterruptedException {
        theActorInTheSpotlight().attemptsTo(ValidarSaldoDataMaestra.withData(LoginStepDefinitions.pagosServiciosData.getMonto(), LoginStepDefinitions.pagosServiciosData.getCuentaOrigen(), LoginStepDefinitions.pagosServiciosData.getTipoDeCuenta()));
        Thread.sleep(1000);
        theActorInTheSpotlight().attemptsTo(ValidarMovimientoDataMaestra.withData(LoginStepDefinitions.pagosServiciosData.getMonto(), LoginStepDefinitions.pagosServiciosData.getEmpresa()));
    }

    @And("^intenta realizar un nuevo pago  con el mismo servicio de tipo Pagos - De servicios - A sola firma - data Maestra (.*)$")
    public void intentaRealizarUnNuevoPagoConElMismoServicioDeTipoPagosDeServiciosASolaFirmaSinDataServicio(String Servicio2) {
        LoginStepDefinitions.pagosServiciosData.setServicio2SinData(Servicio2);
        theActorInTheSpotlight().attemptsTo(new RegistraPagoNuevo());
        theActorInTheSpotlight().attemptsTo(new NuevoServicio());
    }

    // PROBANDO
    @And("^intenta realizar un nuevo pago de tipo Pagos a una nueva empresa con nuevo servicio - De servicios - A sola firma - data Maestra (.*), (.*)$")
    public void intentaRealizarUnNuevoPagoDeTipoPagosAUnaNuevaEmpresaConNuevoServicioDeServiciosASolaFirmaDataMaestra(String empresa2, String servicio2) {
        LoginStepDefinitions.pagosServiciosData.setEmpresa2DataMaestra(empresa2);
        LoginStepDefinitions.pagosServiciosData.setServicio2DataMaestra(servicio2);
        theActorInTheSpotlight().attemptsTo(new RegistraPagoNuevo());
        theActorInTheSpotlight().attemptsTo(new SeleccionCtaEmpresaServicioCodDeudorNuevoPago());
    }
}
