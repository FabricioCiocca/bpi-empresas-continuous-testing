package com.everis.stepsdefinitions.web.bpi.validacion;

import com.everis.bpi.models.bpi.PagosServiciosData;
import com.everis.bpi.stepsdefinitions.web.bpi.login.LoginStepDefinitions;
import com.everis.bpi.tasks.web.bpi.cerrar.CerrarSesionPendiente;
import com.everis.bpi.tasks.web.bpi.login.AutenticarNuevoBpi;
import com.everis.bpi.tasks.web.bpi.pagosServicios.MenuBotonEntendido;
import com.everis.bpi.tasks.web.bpi.validacion.ValidaNoVisualizacionPendiente;
import com.everis.tdm.Do;
import com.everis.tdm.model.bpi.Usuarios;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.sikuli.script.FindFailed;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class ValidacionNoVisualizacionSD {

    public static PagosServiciosData pagosServiciosData = new PagosServiciosData();

    @Before
    public void setTheStage() {

        OnStage.setTheStage(new OnlineCast());

    }

    @And("^se valida que no se visualice el pago a firma conjunta (.*)$")
    public void SeValidaQueNoSeVisualiceElPagoAFirmaConjunta(String user) throws InterruptedException, FindFailed {

        theActorInTheSpotlight().attemptsTo(
                new CerrarSesionPendiente());

        String ambiente = LoginStepDefinitions.pagosServiciosData.getAmbiente();

        Usuarios usuario = Do.getUsuarioPorNombre(user, ambiente);

        String docusuario = usuario.getDocUser();

        String password = usuario.getPassword();

        System.out.println(user + docusuario);

        theActorInTheSpotlight().attemptsTo(
                AutenticarNuevoBpi.withData(docusuario, password));

        theActorInTheSpotlight().attemptsTo(
                new MenuBotonEntendido());

        theActorInTheSpotlight().attemptsTo(
                new ValidaNoVisualizacionPendiente());

        pagosServiciosData.setTipoDeCambioVenta("4.174");

        pagosServiciosData.setTipoDeCambioCompra("3.6775");

        pagosServiciosData.setAmbiente(ambiente);

        pagosServiciosData.setUsuario(user);

    }
}