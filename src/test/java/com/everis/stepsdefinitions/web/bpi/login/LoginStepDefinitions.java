package com.everis.stepsdefinitions.web.bpi.login;

import com.everis.models.bpi.PagosServiciosData;
import com.everis.tasks.web.bpi.login.AbrirBpi;
import com.everis.tasks.web.bpi.login.AutenticarBpi;
import com.everis.tdm.Do;
import com.everis.tdm.model.bpi.Usuarios;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.core.util.EnvironmentVariables;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class LoginStepDefinitions {

    public static PagosServiciosData pagosServiciosData = new PagosServiciosData();
    EnvironmentVariables environmentVariables;

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("^que el usuario (.*) accede a la aplicacion BPI (.*) (.*)$")
    public void validaUsuarioAccedeAplicacionBpi(String user, String ambiente, String credencialesOK) throws InterruptedException, FindFailed {
        theActorCalled(user).attemptsTo(AbrirBpi.loginBpiPage());
        if (ambiente.trim().equals("DEV")) {

            Screen s = new Screen();
            s.click(EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("ruta.imagcredencial"));
        }
        String password = "";
        Usuarios usuario = Do.getUsuarioPorNombre(user, ambiente);
        if (credencialesOK.equalsIgnoreCase("SI")) {
            password = usuario.getPassword();
        } else {
            password = usuario.getPassword() + "xxx";
        }
        String docusuario = usuario.getDocUser();
        double limitepermitidoSF = usuario.getMontolimitepermitido(), montomaximoFM = usuario.getMontomax();
        theActorInTheSpotlight().attemptsTo(AutenticarBpi.withData(docusuario, password));
        pagosServiciosData.setMontoSolaFirma(String.valueOf(limitepermitidoSF));
        pagosServiciosData.setMontoFirmaConjunta(String.valueOf(montomaximoFM));
        pagosServiciosData.setTipoDeCambioVenta("4.1640");
        pagosServiciosData.setTipoDeCambioCompra("3.6355");
        pagosServiciosData.setAmbiente(ambiente);
        pagosServiciosData.setUsuario(user);
        pagosServiciosData.setPassword(password);
        pagosServiciosData.setCredencialOk(credencialesOK);
    }


    @Given("^que la (.*)accede a la aplicacion BPI (.*)$")
    public void queLaFirmanteAccedeALaAplicacionBPIAmbienteCredencialesOK(String empresa, String ambiente) throws InterruptedException, FindFailed {
/*        theActorCalled(empresa).attemptsTo(AbrirBpi.loginBpiPage());
        if (ambiente.trim().equals("DEV")) {
            Screen s = new Screen();
            s.click("C://Users//Usuario//Proyectos//bpi-ct//bpi-empresas-continuous-testing//src//test//resources//Aceptar.png");
        }
        String password = "";
        Empresa company = Do.getEmpresaPorAmbiente(empresa, ambiente);
        if (pagosServiciosData.getCredencialOk().equalsIgnoreCase("SI")) {
            password = company.getPasswordEmpresa();
        } else {
            password = company.getPasswordEmpresa() + "xxx";
        }
        String docempresa = company.getDocEmpresa();
        theActorInTheSpotlight().attemptsTo(AutenticarBpi.withData(docempresa, password));

        pagosServiciosData.setAmbiente(ambiente);
        pagosServiciosData.setCompany(empresa);
        pagosServiciosData.setPassword(password);*/

    }
}