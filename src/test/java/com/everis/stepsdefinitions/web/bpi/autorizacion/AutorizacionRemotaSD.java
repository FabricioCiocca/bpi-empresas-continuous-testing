package com.everis.stepsdefinitions.web.bpi.autorizacion;

import com.everis.bpi.models.bpi.PagosServiciosData;
import com.everis.bpi.stepsdefinitions.web.bpi.login.LoginStepDefinitions;
import com.everis.bpi.tasks.web.bpi.autorizar.MenuAutorizacionesPendienteFirmaP;
import com.everis.bpi.tasks.web.bpi.cerrar.CerrarSesionPendiente;
import com.everis.bpi.tasks.web.bpi.login.AutenticarNuevoBpi;
import com.everis.bpi.tasks.web.bpi.pagosServicios.MenuBotonEntendido;
import com.everis.tdm.Do;
import com.everis.tdm.model.bpi.Usuarios;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.sikuli.script.FindFailed;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class AutorizacionRemotaSD {

    public static PagosServiciosData pagosServiciosData = new PagosServiciosData();

    @Before
    public void setTheStage() {

        OnStage.setTheStage(new OnlineCast());

    }

    @And("^se autoriza el pago a Firma Conjunta - Remota (.*)$")
    public void SeAutorizaElPagoAFirmaConjuntaRemota(String user) throws InterruptedException, FindFailed {

        theActorInTheSpotlight().attemptsTo(
                new CerrarSesionPendiente());

        String ambiente = LoginStepDefinitions.pagosServiciosData.getAmbiente();

        Usuarios usuario = Do.getUsuarioPorNombre(user, ambiente);

        String docusuario = usuario.getDocUser();

        String password = usuario.getPassword();

        theActorInTheSpotlight().attemptsTo(
                AutenticarNuevoBpi.withData(docusuario, password));

        theActorInTheSpotlight().attemptsTo(
                new MenuBotonEntendido());

        LoginStepDefinitions.pagosServiciosData.setcredencialIncorrecto("NO");

        theActorInTheSpotlight().attemptsTo(
                new MenuAutorizacionesPendienteFirmaP());

        pagosServiciosData.setTipoDeCambioVenta("4.174");

        pagosServiciosData.setTipoDeCambioCompra("3.6775");

        pagosServiciosData.setAmbiente(ambiente);

        LoginStepDefinitions.pagosServiciosData.setUsuario(LoginStepDefinitions.pagosServiciosData.getUsuario() + " - " + user);

        LoginStepDefinitions.pagosServiciosData.setFechaHora2(LoginStepDefinitions.pagosServiciosData.getFechaHora2() + " - " + LoginStepDefinitions.pagosServiciosData.getFechaHora());

    }

    @And("^se introduce la contraseña para autorizar el pago a Firma Conjunta - Remota (.*); (.*)$")
    public void seIntroduceLaContraseñaParaAutorizarElPagoAFirmaConjuntaRemota(String user, String passwordErroneo) throws InterruptedException {

        theActorInTheSpotlight().attemptsTo(
                new CerrarSesionPendiente());

        String ambiente = LoginStepDefinitions.pagosServiciosData.getAmbiente();

        Usuarios usuario = Do.getUsuarioPorNombre(user, ambiente);

        String docusuario = usuario.getDocUser();

        String password = usuario.getPassword();

        theActorInTheSpotlight().attemptsTo(
                AutenticarNuevoBpi.withData(docusuario, password));

        theActorInTheSpotlight().attemptsTo(
                new MenuBotonEntendido());

        LoginStepDefinitions.pagosServiciosData.setPassword(passwordErroneo);

        LoginStepDefinitions.pagosServiciosData.setcredencialIncorrecto("SI");

        theActorInTheSpotlight().attemptsTo(
                new MenuAutorizacionesPendienteFirmaP());

    }
}