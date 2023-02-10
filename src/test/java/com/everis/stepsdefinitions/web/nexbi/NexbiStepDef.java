package com.everis.stepsdefinitions.web.nexbi;

import com.everis.questions.web.nexbi.NexbiQuestions;
import com.everis.tasks.mainframe.ObtenerTipoAccesoNuevo;
import com.everis.tasks.web.nexbi.login.AbrirNexbi;
import com.everis.tasks.web.nexbi.login.CredencialAccesoNexbi;
import com.everis.tasks.web.nexbi.query.*;
import com.everis.userinterfaces.web.nexbi.QueryPage;
import com.everis.utils.mainframe.ImprimirDetalleInformacionNuevo;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.questions.TextContent;
import net.thucydides.core.util.EnvironmentVariables;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class NexbiStepDef {

    EnvironmentVariables environmentVariables;

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("^que soy un (.*) de Nexbi Intranet e ingreso con las credenciales: (.*), (.*)$")
    public void Ingresa_Credenciales_Nexbi_Intranet(String usuario, String user, String password) {

        theActorCalled(usuario).attemptsTo(
                AbrirNexbi.loginNexbiPage());

        theActorInTheSpotlight().attemptsTo(
                CredencialAccesoNexbi.withData(user, password));
    }

    @When("^consulto un cliente por (.*) y visualizo la Información General$")
    public void ConsultoUnClientePorCodigoUnico(String codigoUnico) throws InterruptedException, IOException {

        ArrayList<String> numeroPuntoServicioArrays = new ArrayList<>();
        ArrayList<String> nombrePuntoServicioArrays = new ArrayList<>();
        ArrayList<String> tipoAccesoArrays = new ArrayList<>();
        ArrayList<String> tipoClienteArrays = new ArrayList<>();
        ArrayList<String> nivelServicioArrays = new ArrayList<>();
        ArrayList<String> cuentaCargoArrays = new ArrayList<>();
        ArrayList<String> monedaCuentaArrays = new ArrayList<>();
        ArrayList<Integer> cantUsuarioActivoConfirmadoArrays = new ArrayList<>();
        ArrayList<Integer> cantUsuarioActivoArrays = new ArrayList<>();
        ArrayList<Integer> cantUsuarioArrays = new ArrayList<>();

        List<WebElement> listaPuntoServicio = new ArrayList<>();
        ArrayList<String> informacion = new ArrayList<>();

        String numeroPuntoServicio = "";
        numeroPuntoServicioArrays.add("");
        boolean condicion;
        int cantScroll = 1;

        do {
            theActorInTheSpotlight().attemptsTo(ConsultaPorCodigo.withData(codigoUnico));

            condicion = false;
            for (int i = 0; i < cantScroll; i++) {
                if (i > 0 && listaPuntoServicio.size() > 3) {
                    theActorInTheSpotlight().attemptsTo(Scroll.to(QueryPage.TABLE_PUNTOSERVICIO_RECORRIDO.of(String.valueOf(listaPuntoServicio.size() - 3)).called(String.valueOf(listaPuntoServicio.size() - 3))));
                }
                listaPuntoServicio = NexbiQuestions.listaPuntoServicio().answeredBy(theActorInTheSpotlight());
            }

            numeroPuntoServicio = TextContent.of(QueryPage.PUNTO_SERVICIO.of(String.valueOf(1)).called(String.valueOf(1))).answeredBy(theActorInTheSpotlight()).trim();

            for (int i = 0; i < numeroPuntoServicioArrays.size(); i++) {
                if (numeroPuntoServicio.equals(numeroPuntoServicioArrays.get(i)) || numeroPuntoServicioArrays.size() == 1) {
                    for (int j = 1; j <= listaPuntoServicio.size(); j++) {
                        if (condicion) {
                            theActorInTheSpotlight().attemptsTo(ConsultaPorCodigo.withData(codigoUnico));
                            for (int h = 0; h < cantScroll; h++) {
                                if (h > 0) {
                                    theActorInTheSpotlight().attemptsTo(Scroll.to(QueryPage.TABLE_PUNTOSERVICIO_RECORRIDO.of(String.valueOf(listaPuntoServicio.size() - 3)).called(String.valueOf(listaPuntoServicio.size() - 3))));
                                }
                                listaPuntoServicio = NexbiQuestions.listaPuntoServicio().answeredBy(theActorInTheSpotlight());
                            }
                        }

                        numeroPuntoServicio = TextContent.of(QueryPage.PUNTO_SERVICIO.of(String.valueOf(j)).called(String.valueOf(j))).answeredBy(theActorInTheSpotlight()).trim();
                        try {
                            Assert.assertEquals(numeroPuntoServicio, numeroPuntoServicioArrays.get(i));
                        } catch (Throwable e) {
                            condicion = true;

                            numeroPuntoServicioArrays.add(TextContent.of(QueryPage.PUNTO_SERVICIO.of(String.valueOf(j)).called(String.valueOf(j))).answeredBy(theActorInTheSpotlight()).trim());
                            nombrePuntoServicioArrays.add(TextContent.of(QueryPage.NOMBRE_PUNTO_SERVICIO.of(String.valueOf(j)).called(String.valueOf(j))).answeredBy(theActorInTheSpotlight()).trim());

                            theActorInTheSpotlight().attemptsTo(
                                    ObtenerInformacionGeneral.withData(j, nombrePuntoServicioArrays, tipoClienteArrays, nivelServicioArrays, cuentaCargoArrays, monedaCuentaArrays),
                                    new VisualizarVentanaUsuarios());

                            if (QueryPage.INPUT_NOMBRE_USUARIO.isVisibleFor(theActorInTheSpotlight())) {
                                theActorInTheSpotlight().attemptsTo(
                                        ObtenerDetalleUsuario.withData(nombrePuntoServicioArrays, cantUsuarioActivoConfirmadoArrays, cantUsuarioActivoArrays, cantUsuarioArrays));
                                ObtenerTipoAccesoNuevo.ConUsuarios(nombrePuntoServicioArrays,tipoAccesoArrays);
                            }else{//preguntar si se puede guardar las lineas del 118 al 124 en una clase "obtenerDetalleSinUsuario"
                                cantUsuarioArrays.add(null);
                                cantUsuarioActivoArrays.add(null);
                                cantUsuarioActivoConfirmadoArrays.add(null);
                                Serenity.setSessionVariable("cantUsuarioArrays").to(cantUsuarioArrays);
                                Serenity.setSessionVariable("cantUsuarioActivoArrays").to(cantUsuarioActivoArrays);
                                Serenity.setSessionVariable("cantUsuarioActivoConfirmadoArrays").to(cantUsuarioActivoConfirmadoArrays);
                                ObtenerTipoAccesoNuevo.SinUsuarios(tipoAccesoArrays);
                            }
                            theActorInTheSpotlight().attemptsTo(new VisualizarIsvNexbi());
                        }
                        i++;
                    }
                    break;
                }
            }
            cantScroll++;
        } while (condicion);

        Serenity.setSessionVariable("numeroPuntoServicioArrays").to(numeroPuntoServicioArrays);
        Serenity.setSessionVariable("nombrePuntoServicioArrays").to(nombrePuntoServicioArrays);
        BrowseTheWeb.as(theActorInTheSpotlight()).getDriver().close();
        ImprimirDetalleInformacionNuevo.datosValidos(informacion);
    }
}

