package com.everis.stepsdefinitions.web.bpi.pagoDeServicios.sinData;

import com.everis.models.bpi.PagosServiciosData;
import com.everis.stepsdefinitions.web.bpi.login.LoginStepDefinitions;
import com.everis.tasks.web.bpi.autorizar.MenuAutorizacionesPendienteFirmaP;
import com.everis.tasks.web.bpi.cerrar.CerrarSesionPendiente;
import com.everis.tasks.web.bpi.login.AutenticarBpi;
import com.everis.tasks.web.bpi.pagosServicios.IngresarCredencialesPago;
import com.everis.tasks.web.bpi.pagosServicios.MenuBotonEntendido;
import com.everis.tasks.web.bpi.pagosServicios.MenuPagoServicios;
import com.everis.tasks.web.bpi.pagosServicios.SeleccionCtaEmpresaServicioCodDeudor;
import com.everis.tasks.web.bpi.pagosServicios.sindata.*;
import com.everis.tdm.Do;
import com.everis.tdm.model.bpi.Usuarios;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.SneakyThrows;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;


public class SinDataFirmaConjuntaStepDefinitions {
    public static PagosServiciosData pagosServiciosData = new PagosServiciosData();

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }


    @SneakyThrows
    @When("^intenta realizar un pago de estado pendiente (.*), (.*), (.*),(.*),(.*)$")
    public void intentaRealizarUnPagoDeEstadoPendienteTipoCuentaCuentaOrigenEmpresaServicioCodigoDeudor(String tipoCuenta, String cuenta, String empresa, String servicio, String dniPagador) {
        theActorInTheSpotlight().attemptsTo(new MenuBotonEntendido());
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

    @Then("^el pago se realiza de manera exitosa (.*), (.*), (.*)$")
    public void elPagoSeRealizaDeManeraExitosaMontoDescripcionPassword(String monto, String descripcion, String pass) {
        LoginStepDefinitions.pagosServiciosData.setMontoIncial(monto);
        LoginStepDefinitions.pagosServiciosData.setDescripcion(descripcion);
        LoginStepDefinitions.pagosServiciosData.setPassword(pass);
        theActorInTheSpotlight().attemptsTo(new SeleccionCuotaIngresaMontoFirmaConjunta());
        theActorInTheSpotlight().attemptsTo(IngresarCredencialesPago.withData(pass, "111111"));
        double montoOriginal;
        if (Double.parseDouble(LoginStepDefinitions.pagosServiciosData.getmontoTipoCambio()) > 0) {
            montoOriginal = Double.parseDouble(LoginStepDefinitions.pagosServiciosData.getmontoTipoCambio());
        } else {
            montoOriginal = Double.parseDouble(LoginStepDefinitions.pagosServiciosData.getMonto().replace(LoginStepDefinitions.pagosServiciosData.getSimbolo() + " ", "").replace(",", ""));
        }
        if (montoOriginal <= Double.parseDouble(LoginStepDefinitions.pagosServiciosData.getMontoFirmaConjunta())) {
            theActorInTheSpotlight().attemptsTo(DetalleEstadoPagoSinDataFC.withData(LoginStepDefinitions.pagosServiciosData.getUsuario(), LoginStepDefinitions.pagosServiciosData.getTipoDeCuenta(), LoginStepDefinitions.pagosServiciosData.getCuentaOrigen()));
        } else {
            theActorInTheSpotlight().attemptsTo(DetalleEstadoPagoSinDataGF.withData(LoginStepDefinitions.pagosServiciosData.getUsuario(), LoginStepDefinitions.pagosServiciosData.getTipoDeCuenta(), LoginStepDefinitions.pagosServiciosData.getCuentaOrigen()));
        }





    }

    @And("^se autoriza el pago a Firma Conjunta - Sin data (.*)$")
    public void seAutorizaElPagoAFirmaConjuntaSinDataFirmante(String user) throws InterruptedException {
        theActorInTheSpotlight().attemptsTo(
                new CerrarSesionPendiente());
        String ambiente = LoginStepDefinitions.pagosServiciosData.getAmbiente();
        Usuarios usuario = Do.getUsuarioPorNombre(user, ambiente);
        String docusuario = usuario.getDocUser();
        String password = usuario.getPassword();
        theActorInTheSpotlight().attemptsTo(
                AutenticarBpi.withData(docusuario, password)
        );
        theActorInTheSpotlight().attemptsTo(
                new MenuBotonEntendido());
        theActorInTheSpotlight().attemptsTo(
                new MenuAutorizacionesPendienteFirmaP());
        pagosServiciosData.setTipoDeCambioVenta("4.174");
        pagosServiciosData.setTipoDeCambioCompra("3.6775");
        pagosServiciosData.setAmbiente(ambiente);
        pagosServiciosData.setUsuario(user);

    }

    @SneakyThrows
    @And("validamos el Saldo y Movimiento")
    public void validamosElSaldoYMovimiento() {
        theActorInTheSpotlight().attemptsTo(
                ValidarSaldoFC.withData(LoginStepDefinitions.pagosServiciosData.getMonto(), LoginStepDefinitions.pagosServiciosData.getCuentaOrigen(), LoginStepDefinitions.pagosServiciosData.getTipoDeCuenta()));
        theActorInTheSpotlight().attemptsTo(
                ValidarMovimientosFC.withData(LoginStepDefinitions.pagosServiciosData.getMonto(), LoginStepDefinitions.pagosServiciosData.getEmpresa()));
    }
}
