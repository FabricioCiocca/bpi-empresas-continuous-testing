package com.everis.stepsdefinitions.api;


import com.everis.questions.api.CodigoRespuesta;
import com.everis.questions.api.RespuestaJob;
import com.everis.questions.api.TipoCambio;
import com.everis.tasks.apis.GeneraJob;
import com.everis.tasks.apis.MuestraTipoCambio;
import com.everis.utils.DescargarArchivoSFTP;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.core.util.EnvironmentVariables;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.CoreMatchers.equalTo;

public class ApiStepDef {
    EnvironmentVariables environmentVariables;

    @And("^consulto el tipo de cambio de (.*) en el API$")
    public void consulto_el_tipo_de_cambio_de_uat_en_el_api(String actor) {

        theActorCalled(actor).whoCan(CallAnApi.at(
                    EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("api.manager")));

        theActorInTheSpotlight().attemptsTo(
                new MuestraTipoCambio());
    }

    @Then("valido el codigo respuesta")
    public void muestra_el_código_de_respuesta() {

        theActorInTheSpotlight().should(seeThat("El código de respuesta", new CodigoRespuesta(), equalTo(200)));

        Serenity.setSessionVariable("tipoCambioDolares").to(theActorInTheSpotlight().asksFor(TipoCambio.Dolares()));
    }

    @And("^ejecuto el servicio Job fee charge de (.*)$")
    public void ejecutoElJob(String actor) {

        theActorCalled(actor).whoCan(CallAnApi.at(EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("api.manager2")));

        theActorInTheSpotlight().attemptsTo(
                new GeneraJob());
    }

    @Then("valido la respuesta Data del Job")
    public void validoElCodigoRespuesta() {

        theActorInTheSpotlight().should(
                seeThat("El código de respuesta", new CodigoRespuesta(), equalTo(200)),
                seeThat("Respuesta data del Job", RespuestaJob.dataValida(), equalTo("true")));
    }

    @And("descargo el archivo TXT")
    public void descargoElArchivoTXT() {
        DescargarArchivoSFTP.imCobrosMensualTxt(environmentVariables);
        DescargarArchivoSFTP.stCobrosMensualTxt(environmentVariables);
    }
}
