package com.everis.stepsdefinitions.web.bpi.bloqueo;

import com.everis.bpi.models.bpi.PagosServiciosData;
import com.everis.bpi.stepsdefinitions.web.bpi.login.LoginStepDefinitions;
import com.everis.bpi.tasks.web.bpi.bloqueo.MenuBloqueosEnviadasPorMiP;
import com.everis.bpi.tasks.web.bpi.bloqueo.MenuBloqueosPendienteFirmaP;
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

public class BloqueoRemotaSD {

    public static PagosServiciosData pagosServiciosData = new PagosServiciosData();

    @Before
    public void setTheStage() {

        OnStage.setTheStage(new OnlineCast());

    }

    @And("^se bloquea el pago a firma conjunta - Remota (.*)$")
    public void SeBloqueaElPagoAFirmaConjuntaRemota(String user) throws InterruptedException, FindFailed {

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

        theActorInTheSpotlight().attemptsTo(
                new MenuBloqueosPendienteFirmaP());

        pagosServiciosData.setTipoDeCambioVenta("4.174");

        pagosServiciosData.setTipoDeCambioCompra("3.6775");

        pagosServiciosData.setAmbiente(ambiente);

        pagosServiciosData.setUsuario(user);

    }

    @And("^bloquea el pago a fima conjunta - Remota")
    public void BloqueaElPagoAFirmaConjuntaRemota() throws InterruptedException, FindFailed {

        theActorInTheSpotlight().attemptsTo(
                new MenuBloqueosEnviadasPorMiP());

    }

    @And("^se introduce la contraseña para bloquear el pago a Firma Conjunta - Remota (.*); (.*)$")
    public void seIntroduceLaContraseñaParaBloquearElPagoAFirmaConjuntaRemota(String user, String passwordErroneo) throws InterruptedException {

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

        theActorInTheSpotlight().attemptsTo(
                new MenuBloqueosPendienteFirmaP());

    }

    @And("^introduce la contraseña para bloquear el pago a Firma Conjunta - Remota (.*)$")
    public void introduceLaContraseñaParaBloquearElPagoAFirmaConjuntaRemota(String passwordErroneo) throws InterruptedException {

        LoginStepDefinitions.pagosServiciosData.setPassword(passwordErroneo);

        theActorInTheSpotlight().attemptsTo(
                new MenuBloqueosEnviadasPorMiP());

    }
}