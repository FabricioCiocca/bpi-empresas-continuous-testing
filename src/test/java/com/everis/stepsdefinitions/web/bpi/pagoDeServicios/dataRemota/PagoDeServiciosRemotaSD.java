package com.everis.stepsdefinitions.web.bpi.pagoDeServicios.dataRemota;


import com.everis.bpi.stepsdefinitions.web.bpi.login.LoginStepDefinitions;
import com.everis.bpi.tasks.web.bpi.pagosServicios.IngresarCredencialesPago;
import com.everis.bpi.tasks.web.bpi.pagosServicios.MenuBotonEntendido;
import com.everis.bpi.tasks.web.bpi.pagosServicios.MenuPagoServicios;
import com.everis.bpi.tasks.web.bpi.pagosServicios.SeleccionCtaEmpresaServicioCodDeudor;
import com.everis.bpi.tasks.web.bpi.pagosServicios.dataPendiente.*;
import io.cucumber.java.Before;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;


public class PagoDeServiciosRemotaSD {

    @Before
    public void setTheStage() {

        OnStage.setTheStage(new OnlineCast());

    }

    @When("^intenta realizar un pago total de tipo Pagos - De servicios - Remota (.*); (.*); (.*); (.*); (.*); (.*)")
    public void realizaPagoTotalServicioRemota(String tipoCuenta, String cuenta, String empresa, String servicio, String codigoDeudor, String descripcion) throws InterruptedException {

        theActorInTheSpotlight().attemptsTo(
                new MenuBotonEntendido());

        theActorInTheSpotlight().attemptsTo(
                new MenuPagoServicios());

        String[] codigoDeudorArray = codigoDeudor.split("-");

        for (int i = 0; i < codigoDeudorArray.length; i++) {

            theActorInTheSpotlight().attemptsTo(
                    SeleccionCtaEmpresaServicioCodDeudor.withData(cuenta, empresa, servicio, codigoDeudorArray[i]));

        }

        LoginStepDefinitions.pagosServiciosData.setTipoDeCuenta(tipoCuenta);

        LoginStepDefinitions.pagosServiciosData.setCuentaOrigen(cuenta);

        LoginStepDefinitions.pagosServiciosData.setEmpresa(empresa);

        LoginStepDefinitions.pagosServiciosData.setServicio(servicio);

        LoginStepDefinitions.pagosServiciosData.setDniPagador(codigoDeudor);

        LoginStepDefinitions.pagosServiciosData.setDescripcion(descripcion);

        LoginStepDefinitions.pagosServiciosData.setCantdni(String.valueOf(codigoDeudorArray.length));

        theActorInTheSpotlight().attemptsTo(
                new SeleccionCuotaIngresaMontoTotal());

        theActorInTheSpotlight().attemptsTo(
                IngresarCredencialesPagoPendiente.withData(LoginStepDefinitions.pagosServiciosData.getPassword(), "111111"));

    }

    @When("^intenta realizar un pago parcial de tipo Pagos - De servicios - Remota (.*); (.*); (.*); (.*); (.*); (.*); (.*)$")
    public void realizaPagoParcialServicioRemota(String tipoCuenta, String cuenta, String empresa, String servicio, String codigoDeudor, String monto, String descripcion) throws InterruptedException {

        theActorInTheSpotlight().attemptsTo(
                new MenuBotonEntendido());

        theActorInTheSpotlight().attemptsTo(
                new MenuPagoServicios());

        String[] codigoDeudorArray = codigoDeudor.split("-");

        for (int i = 0; i < codigoDeudorArray.length; i++) {

            theActorInTheSpotlight().attemptsTo(
                    SeleccionCtaEmpresaServicioCodDeudor.withData(cuenta, empresa, servicio, codigoDeudorArray[i]));

        }

        LoginStepDefinitions.pagosServiciosData.setTipoDeCuenta(tipoCuenta);

        LoginStepDefinitions.pagosServiciosData.setCuentaOrigen(cuenta);

        LoginStepDefinitions.pagosServiciosData.setEmpresa(empresa);

        LoginStepDefinitions.pagosServiciosData.setServicio(servicio);

        LoginStepDefinitions.pagosServiciosData.setDniPagador(codigoDeudor);

        LoginStepDefinitions.pagosServiciosData.setMontoIncial(monto);

        LoginStepDefinitions.pagosServiciosData.setDescripcion(descripcion);

        LoginStepDefinitions.pagosServiciosData.setCantdni(String.valueOf(codigoDeudorArray.length));

        theActorInTheSpotlight().attemptsTo(
                new SeleccionCuotaIngresaMontoParcial());

        theActorInTheSpotlight().attemptsTo(
                IngresarCredencialesPagoPendiente.withData(LoginStepDefinitions.pagosServiciosData.getPassword(), "111111"));

    }

    @When("^intenta realizar un pago de tipo Pagos - De servicios - Remota (.*); (.*); (.*); (.*); (.*); (.*); (.*)")
    public void realizaPagoOtroServicioRemota(String tipoCuenta, String cuenta, String empresa, String servicio, String dniAPagar, String monto, String descripcion) throws InterruptedException {

        theActorInTheSpotlight().attemptsTo(
                new MenuBotonEntendido());

        theActorInTheSpotlight().attemptsTo(
                new MenuPagoServicios());

        String[] dniAPagarArray = dniAPagar.split("-");

        for (int i = 0; i < dniAPagarArray.length; i++) {

            theActorInTheSpotlight().attemptsTo(
                    SeleccionCtaEmpresaServicioCodDeudor.withData(cuenta, empresa, servicio, dniAPagarArray[i]));

        }

        LoginStepDefinitions.pagosServiciosData.setTipoDeCuenta(tipoCuenta);

        LoginStepDefinitions.pagosServiciosData.setCuentaOrigen(cuenta);

        LoginStepDefinitions.pagosServiciosData.setEmpresa(empresa);

        LoginStepDefinitions.pagosServiciosData.setServicio(servicio);

        LoginStepDefinitions.pagosServiciosData.setDniPagador(dniAPagar);

        LoginStepDefinitions.pagosServiciosData.setCantdni(String.valueOf(dniAPagarArray.length));

        LoginStepDefinitions.pagosServiciosData.setMontoIncial(monto);

        LoginStepDefinitions.pagosServiciosData.setDescripcion(descripcion);

        theActorInTheSpotlight().attemptsTo(
                new SeleccionCuotaIngresaMontoOtro());

        theActorInTheSpotlight().attemptsTo(
                IngresarCredencialesPago.withData(LoginStepDefinitions.pagosServiciosData.getPassword(), "111111"));

    }

    @When("intenta introducir los servicios para realizar un pago de tipo Pagos - De servicios - Remota (.*); (.*); (.*); (.*); (.*)$")
    public void intentaIntroducirLosServiciosParaRealizarUnPagoDeTipoPagosDeServiciosRemota(String tipoCuenta, String cuenta, String empresa, String servicio, String codigoDeudor) throws InterruptedException {

        theActorInTheSpotlight().attemptsTo(
                new MenuBotonEntendido());

        theActorInTheSpotlight().attemptsTo(
                new MenuPagoServicios());

        String[] codigoDeudorArray = codigoDeudor.split("-");

        for (int i = 0; i < codigoDeudorArray.length; i++) {

            theActorInTheSpotlight().attemptsTo(
                    SeleccionCtaEmpresaServicioCodDeudor.withData(cuenta, empresa, servicio, codigoDeudorArray[i]));

        }
    }

    @When("intenta introducir la contraseña para realizar un pago total de tipo Pagos - De servicios - Remota (.*); (.*); (.*); (.*); (.*); (.*); (.*)$")
    public void intentaIntroducirLaContraseñaParaRealizarUnPagoTotalDeTipoPagosDeServiciosRemota(String tipoCuenta, String cuenta, String empresa, String servicio, String codigoDeudor, String descripcion, String password) throws InterruptedException {

        theActorInTheSpotlight().attemptsTo(
                new MenuBotonEntendido());

        theActorInTheSpotlight().attemptsTo(
                new MenuPagoServicios());

        String[] codigoDeudorArray = codigoDeudor.split("-");

        for (int i = 0; i < codigoDeudorArray.length; i++) {

            theActorInTheSpotlight().attemptsTo(
                    SeleccionCtaEmpresaServicioCodDeudor.withData(cuenta, empresa, servicio, codigoDeudorArray[i]));

        }

        LoginStepDefinitions.pagosServiciosData.setTipoDeCuenta(tipoCuenta);

        LoginStepDefinitions.pagosServiciosData.setCuentaOrigen(cuenta);

        LoginStepDefinitions.pagosServiciosData.setEmpresa(empresa);

        LoginStepDefinitions.pagosServiciosData.setServicio(servicio);

        LoginStepDefinitions.pagosServiciosData.setDniPagador(codigoDeudor);

        LoginStepDefinitions.pagosServiciosData.setDescripcion(descripcion);

        LoginStepDefinitions.pagosServiciosData.setPassword(password);

        LoginStepDefinitions.pagosServiciosData.setCantdni(String.valueOf(codigoDeudorArray.length));

        theActorInTheSpotlight().attemptsTo(
                new SeleccionCuotaIngresaMontoTotal());

        theActorInTheSpotlight().attemptsTo(
                IngresarCredencialesPagoPendiente.withData(password, "111111"));

    }

    @When("intenta introducir el monto de pago para realizar un pago parcial de tipo Pagos - De servicios - Remota (.*); (.*); (.*); (.*); (.*); (.*); (.*)$")
    public void intentaIntroducirElMontoDePagoParaRealizarUnPagoParcialDeTipoPagosDeServiciosRemota(String tipoCuenta, String cuenta, String empresa, String servicio, String codigoDeudor, String monto, String descripcion) throws InterruptedException {

        theActorInTheSpotlight().attemptsTo(
                new MenuBotonEntendido());

        theActorInTheSpotlight().attemptsTo(
                new MenuPagoServicios());

        String[] codigoDeudorArray = codigoDeudor.split("-");

        for (int i = 0; i < codigoDeudorArray.length; i++) {

            theActorInTheSpotlight().attemptsTo(
                    SeleccionCtaEmpresaServicioCodDeudor.withData(cuenta, empresa, servicio, codigoDeudorArray[i]));

        }

        LoginStepDefinitions.pagosServiciosData.setTipoDeCuenta(tipoCuenta);

        LoginStepDefinitions.pagosServiciosData.setCuentaOrigen(cuenta);

        LoginStepDefinitions.pagosServiciosData.setEmpresa(empresa);

        LoginStepDefinitions.pagosServiciosData.setServicio(servicio);

        LoginStepDefinitions.pagosServiciosData.setDniPagador(codigoDeudor);

        LoginStepDefinitions.pagosServiciosData.setMontoIncial(monto);

        LoginStepDefinitions.pagosServiciosData.setDescripcion(descripcion);

        LoginStepDefinitions.pagosServiciosData.setCantdni(String.valueOf(codigoDeudorArray.length));

        theActorInTheSpotlight().attemptsTo(
                new SeleccionCuotaIngresaMontoParcial());

    }

    @When("intenta elegir las cuotas de pago para realizar un pago parcial de tipo Pagos - De servicios - Remota (.*); (.*); (.*); (.*); (.*); (.*); (.*)$")
    public void intentaElegirLasCuotasDePagoParaRealizarUnPagoDeTipoPagosDeServiciosRemota(String tipoCuenta, String cuenta, String empresa, String servicio, String codigoDeudor, String monto, String descripcion) throws InterruptedException {

        theActorInTheSpotlight().attemptsTo(
                new MenuBotonEntendido());

        theActorInTheSpotlight().attemptsTo(
                new MenuPagoServicios());

        String[] codigoDeudorArray = codigoDeudor.split("-");

        for (int i = 0; i < codigoDeudorArray.length; i++) {

            theActorInTheSpotlight().attemptsTo(
                    SeleccionCtaEmpresaServicioCodDeudor.withData(cuenta, empresa, servicio, codigoDeudorArray[i]));

        }

        LoginStepDefinitions.pagosServiciosData.setTipoDeCuenta(tipoCuenta);

        LoginStepDefinitions.pagosServiciosData.setCuentaOrigen(cuenta);

        LoginStepDefinitions.pagosServiciosData.setEmpresa(empresa);

        LoginStepDefinitions.pagosServiciosData.setServicio(servicio);

        LoginStepDefinitions.pagosServiciosData.setDniPagador(codigoDeudor);

        LoginStepDefinitions.pagosServiciosData.setMontoIncial(monto);

        LoginStepDefinitions.pagosServiciosData.setDescripcion(descripcion);

        LoginStepDefinitions.pagosServiciosData.setCantdni(String.valueOf(codigoDeudorArray.length));

        theActorInTheSpotlight().attemptsTo(
                new SeleccionCuotaIngresaMontoParcialSinCuotas());

    }

    @When("intenta introducir la contraseña para realizar un pago parcial de tipo Pagos - De servicios - Remota (.*); (.*); (.*); (.*); (.*); (.*); (.*); (.*)$")
    public void intentaIntroducirLaContraseñaParaRealizarUnPagoParcialDeTipoPagosDeServiciosRemota(String tipoCuenta, String cuenta, String empresa, String servicio, String codigoDeudor, String monto, String descripcion, String password) throws InterruptedException {

        theActorInTheSpotlight().attemptsTo(
                new MenuBotonEntendido());

        theActorInTheSpotlight().attemptsTo(
                new MenuPagoServicios());

        String[] codigoDeudorArray = codigoDeudor.split("-");

        for (int i = 0; i < codigoDeudorArray.length; i++) {

            theActorInTheSpotlight().attemptsTo(
                    SeleccionCtaEmpresaServicioCodDeudor.withData(cuenta, empresa, servicio, codigoDeudorArray[i]));

        }

        LoginStepDefinitions.pagosServiciosData.setTipoDeCuenta(tipoCuenta);

        LoginStepDefinitions.pagosServiciosData.setCuentaOrigen(cuenta);

        LoginStepDefinitions.pagosServiciosData.setEmpresa(empresa);

        LoginStepDefinitions.pagosServiciosData.setServicio(servicio);

        LoginStepDefinitions.pagosServiciosData.setDniPagador(codigoDeudor);

        LoginStepDefinitions.pagosServiciosData.setMontoIncial(monto);

        LoginStepDefinitions.pagosServiciosData.setDescripcion(descripcion);

        LoginStepDefinitions.pagosServiciosData.setPassword(password);

        LoginStepDefinitions.pagosServiciosData.setCantdni(String.valueOf(codigoDeudorArray.length));

        theActorInTheSpotlight().attemptsTo(
                new SeleccionCuotaIngresaMontoParcial());

        theActorInTheSpotlight().attemptsTo(
                IngresarCredencialesPagoPendiente.withData(password, "111111"));

    }

    @When("intenta efectuar un pago total de tipo Pagos - De servicios - Remota (.*); (.*); (.*); (.*); (.*); (.*)$")
    public void intentaEfectuarUnPagoTotalDeTipoPagosDeServiciosRemota(String tipoCuenta, String cuenta, String empresa, String servicio, String codigoDeudor, String descripcion) throws InterruptedException {

        theActorInTheSpotlight().attemptsTo(
                new MenuBotonEntendido());

        theActorInTheSpotlight().attemptsTo(
                new MenuPagoServicios());

        String[] codigoDeudorArray = codigoDeudor.split("-");

        for (int i = 0; i < codigoDeudorArray.length; i++) {

            theActorInTheSpotlight().attemptsTo(
                    SeleccionCtaEmpresaServicioCodDeudor.withData(cuenta, empresa, servicio, codigoDeudorArray[i]));

        }

        LoginStepDefinitions.pagosServiciosData.setTipoDeCuenta(tipoCuenta);

        LoginStepDefinitions.pagosServiciosData.setCuentaOrigen(cuenta);

        LoginStepDefinitions.pagosServiciosData.setEmpresa(empresa);

        LoginStepDefinitions.pagosServiciosData.setServicio(servicio);

        LoginStepDefinitions.pagosServiciosData.setDniPagador(codigoDeudor);

        LoginStepDefinitions.pagosServiciosData.setDescripcion(descripcion);

        LoginStepDefinitions.pagosServiciosData.setCantdni(String.valueOf(codigoDeudorArray.length));

        theActorInTheSpotlight().attemptsTo(
                new SeleccionCuotaIngresaMontoTotal());

    }
}
