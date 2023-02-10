package com.everis.stepsdefinitions.web.bpi.validacion;

import com.everis.questions.web.bpi.RecaudacionesQuestions;
import com.everis.stepsdefinitions.web.bpi.login.LoginStepDefinitions;
import com.everis.tasks.web.bpi.cerrar.CerrarSesionPendiente;
import com.everis.tasks.web.bpi.login.AutenticarBpi;
import com.everis.tasks.web.bpi.validacion.ValidarRecaudaciones;
import com.everis.tdm.Do;
import com.everis.tdm.model.bpi.Empresa;
import com.everis.userinterfaces.web.bpi.LoginPage;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class ValidacionRecaudacionesStepDefinition {

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @And("^valido recaudaciones (.*), (.*)$")
    public void validarRecaudaciones(String Service, String buscarcod) throws InterruptedException {
        theActorInTheSpotlight().attemptsTo(new CerrarSesionPendiente());
        String ambiente = LoginStepDefinitions.pagosServiciosData.getAmbiente();
        Empresa company = Do.getEmpresaPorAmbiente(LoginStepDefinitions.pagosServiciosData.getEmpresa(), ambiente);
        String docempresa = company.getDocEmpresa();
        String passwordEmpresa = company.getPasswordEmpresa();
        theActorInTheSpotlight().attemptsTo(AutenticarBpi.withData(docempresa, passwordEmpresa));
      //  theActorInTheSpotlight().attemptsTo(new MenuBotonEntendido());
        theActorInTheSpotlight().attemptsTo(ValidarRecaudaciones.withData(Service, buscarcod));
        theActorInTheSpotlight().should(seeThat(RecaudacionesQuestions.empresaRecaudacion(), equalTo(LoginStepDefinitions.pagosServiciosData.getEmpresa())));
        theActorInTheSpotlight().should(seeThat(RecaudacionesQuestions.servicio(), equalTo(Service)));
        theActorInTheSpotlight().should(seeThat(RecaudacionesQuestions.codigo(), equalTo(LoginStepDefinitions.pagosServiciosData.getDniPagador())));
        theActorInTheSpotlight().should(seeThat(RecaudacionesQuestions.fecha(), containsString(LoginStepDefinitions.pagosServiciosData.getFechaRecaudacion())));
        theActorInTheSpotlight().should(seeThat(RecaudacionesQuestions.numerooperacion(), equalTo(LoginStepDefinitions.pagosServiciosData.getNumeroOperacion())));
        theActorInTheSpotlight().should(seeThat(RecaudacionesQuestions.monto(), equalTo(LoginStepDefinitions.pagosServiciosData.getMonto())));
        theActorInTheSpotlight().attemptsTo(Click.on(LoginPage.REGRESAR_DETALLE_ESTADO));
    }


}
