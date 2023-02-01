package com.everis.stepsdefinitions.web.bpi.bloqueo;

import com.everis.models.bpi.PagosServiciosData;
import com.everis.stepsdefinitions.web.bpi.login.LoginStepDefinitions;
import com.everis.tasks.web.bpi.bloqueo.MenuBloqueosEnviadasPorMiP;
import com.everis.tasks.web.bpi.bloqueo.MenuBloqueosPendienteFirmaP;
import com.everis.tasks.web.bpi.cerrar.CerrarSesionPendiente;
import com.everis.tasks.web.bpi.login.AutenticarNuevoBpi;
import com.everis.tasks.web.bpi.pagosServicios.MenuBotonEntendido;
import com.everis.tdm.Do;
import com.everis.tdm.model.bpi.Usuarios;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.sikuli.script.FindFailed;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class BloqueoPendienteSD {

    public static PagosServiciosData pagosServiciosData = new PagosServiciosData();

    @Before
    public void setTheStage() {

        OnStage.setTheStage(new OnlineCast());

    }

    @And("^se bloquea el pago a firma conjunta - Pendiente (.*)$")
    public void SeBloqueaElPagoAFirmaConjuntaPendiente(String user) throws InterruptedException, FindFailed {

        Thread.sleep(3000);

        theActorInTheSpotlight().attemptsTo(
                new CerrarSesionPendiente());

        String ambiente = LoginStepDefinitions.pagosServiciosData.getAmbiente();

        Usuarios usuario = Do.getUsuarioPorNombre(user, ambiente);

        String docusuario = usuario.getDocUser();

        String password = usuario.getPassword();

        Thread.sleep(3000);

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

    @And("^bloquea el pago a fima conjunta - Pendiente")
    public void BloqueaElPagoAFirmaConjuntaPendiente() throws InterruptedException, FindFailed {

        Thread.sleep(3000);

        theActorInTheSpotlight().attemptsTo(
                new MenuBloqueosEnviadasPorMiP());

    }

    @And("^se introduce la contraseña para bloquear el pago a Firma Conjunta - Pendiente (.*); (.*)$")
    public void seIntroduceLaContraseñaParaBloquearElPagoAFirmaConjuntaPendiente(String user, String passwordErroneo)throws InterruptedException {

        Thread.sleep(3000);

        theActorInTheSpotlight().attemptsTo(
                new CerrarSesionPendiente());

        String ambiente = LoginStepDefinitions.pagosServiciosData.getAmbiente();

        Usuarios usuario = Do.getUsuarioPorNombre(user, ambiente);

        String docusuario = usuario.getDocUser();

        String password = usuario.getPassword();

        Thread.sleep(3000);

        theActorInTheSpotlight().attemptsTo(
                AutenticarNuevoBpi.withData(docusuario, password));

        theActorInTheSpotlight().attemptsTo(
                new MenuBotonEntendido());

        LoginStepDefinitions.pagosServiciosData.setPassword(passwordErroneo);

        theActorInTheSpotlight().attemptsTo(
                new MenuBloqueosPendienteFirmaP());

    }

    @And("^introduce la contraseña para bloquear el pago a Firma Conjunta - Pendiente (.*)$")
    public void introduceLaContraseñaParaBloquearElPagoAFirmaConjuntaPendiente(String passwordErroneo)throws InterruptedException {

        LoginStepDefinitions.pagosServiciosData.setPassword(passwordErroneo);

        theActorInTheSpotlight().attemptsTo(
                new MenuBloqueosEnviadasPorMiP());

    }
}