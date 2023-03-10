package com.everis.stepsdefinitions.web.bpi.pagoDeServicios;


import com.everis.bpi.models.bpi.PagosServiciosData;
import com.everis.bpi.questions.web.bpi.NegatividadQuestions;
import com.everis.bpi.stepsdefinitions.web.bpi.login.LoginStepDefinitions;
import com.everis.bpi.tasks.web.bpi.pagosServicios.dataPendiente.DetalleEstadoPagoPendienteRechazadoFC1;
import com.everis.bpi.tasks.web.bpi.pagosServicios.dataPendiente.DetalleEstadoPagoPendienteRechazadoGF1;
import com.everis.bpi.tasks.web.bpi.pagosServicios.dataPendiente.DetalleEstadoPagoPendienteRechazadoSF1;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.equalTo;


public class NegativoSD {

    public static PagosServiciosData pagosServiciosData = new PagosServiciosData();

    @Before
    public void setTheStage() {

        OnStage.setTheStage(new OnlineCast());

    }

    @Then("el pago a sola firma se realiza sin exito$")
    public void elPagoSolaFirmaSeRealizaSinExito() {

        theActorInTheSpotlight().attemptsTo(
                DetalleEstadoPagoPendienteRechazadoSF1.withData(LoginStepDefinitions.pagosServiciosData.getUsuario(), LoginStepDefinitions.pagosServiciosData.getTipoDeCuenta(), LoginStepDefinitions.pagosServiciosData.getCuentaOrigen()));


    }

    @Then("el pago a firma conjunta se realiza sin exito$")
    public void elPagoFirmaConjuntaSeRealizaSinExito() {

        theActorInTheSpotlight().attemptsTo(
                DetalleEstadoPagoPendienteRechazadoFC1.withData(LoginStepDefinitions.pagosServiciosData.getUsuario(), LoginStepDefinitions.pagosServiciosData.getTipoDeCuenta(), LoginStepDefinitions.pagosServiciosData.getCuentaOrigen()));

    }

    @Then("el pago a firma combinada se realiza sin exito$")
    public void elPagoFirmaCombinadaSeRealizaSinExito() {

        theActorInTheSpotlight().attemptsTo(
                DetalleEstadoPagoPendienteRechazadoGF1.withData(LoginStepDefinitions.pagosServiciosData.getUsuario(), LoginStepDefinitions.pagosServiciosData.getTipoDeCuenta(), LoginStepDefinitions.pagosServiciosData.getCuentaOrigen()));

    }

    @Then("se muestra el mensaje de error 'Limite de Servicios'")
    public void seMuestraElMensajeDeErrorLimite() {

        theActorInTheSpotlight().should(seeThat(
                NegatividadQuestions.LimiteServicio(), equalTo("L??MITE DE SERVICIOS")));

    }

    @Then("se muestra el mensaje de error 'Servicio ya Agregado'")
    public void seMuestraElMensajeDeErrorServicioYaAgregado() {
        theActorInTheSpotlight().should(seeThat(
                NegatividadQuestions.ServicioYaAgregado(), equalTo("SERVICIO YA AGREGADO")));

    }

    @Then("se muestra el mensaje de error 'No cumple con los par??metros de seguridad'")
    public void seMuestraElMensajeDeErrorNoCumpleConLosPar??metrosDeSeguridad() {

        theActorInTheSpotlight().should(seeThat(
                NegatividadQuestions.ParametrosSeguridad(), equalTo("No cumple con los par??metros de seguridad")));

    }

    @Then("se muestra el mensaje de error 'No se encontraron cuotas'")
    public void seMuestraElMensajeDeErrorNoSeEncontraronCuotas() {

        theActorInTheSpotlight().should(seeThat(
                NegatividadQuestions.NoSeEncontraronCuotas(), equalTo("NO SE ENCONTRARON CUOTAS")));

    }

    @Then("se muestra el mensaje de error 'Debes ingresar un monto v??lido'")
    public void seMuestraElMensajeDeErrorDebesIngresarUnMontoValido() {

        theActorInTheSpotlight().should(seeThat(
                NegatividadQuestions.DebesIngresarUnMontoValido(), equalTo("Debes ingresar un monto v??lido")));

    }

    @Then("se muestra el mensaje de error 'Debes seleccionar al menos una cuota para poder realizar el pago correspondiente.'")
    public void seMuestraElMensajeDeErrorDebesSeleccionarAlMenosUnaCuotaParaPoderRealizarElPagoCorrespondiente() {

        theActorInTheSpotlight().should(seeThat(
                NegatividadQuestions.DebesSelecciomarCuota(), equalTo("Debes seleccionar al menos una cuota para poder realizar el pago correspondiente.")));

    }

    @Then("se muestra el mensaje de error 'Monto ingresado supera l??mites'")
    public void seMuestraElMensajeDeErrorMONTOINGRESADOSUPERALIMITES() {

        theActorInTheSpotlight().should(seeThat(
                NegatividadQuestions.MontoIngresadoSuperaLimites(), equalTo("MONTO INGRESADO SUPERA L??MITES")));

    }
}
