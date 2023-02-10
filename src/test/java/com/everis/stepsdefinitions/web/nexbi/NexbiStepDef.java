package com.everis.stepsdefinitions.web.nexbi;


import com.everis.questions.web.nexbi.NexbiQuestions;
import com.everis.tasks.mainframe.ObtenerTipoAccesoNuevo;
import com.everis.tasks.web.nexbi.login.AbrirNexbi;
import com.everis.tasks.web.nexbi.login.CredencialAccesoNexbi;
import com.everis.tasks.web.nexbi.query.*;
import com.everis.userinterfaces.web.nexbi.QueryPage;
import com.everis.utils.mainframe.ImprimirDetalleInformacion;
import com.everis.utils.mainframe.ImprimirDetalleInformacionNuevo;
import io.cucumber.datatable.DataTable;
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
import java.util.Map;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class NexbiStepDef {

    EnvironmentVariables environmentVariables;
    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("^que soy un usuario de Nexbi Intranet e ingreso en el ambiente (.*) con las credenciales: (.*), (.*)$")
    public void Ingresa_Credenciales_Nexbi_Intranet(String ambiente, String user, String password) {
        theActorCalled(user).attemptsTo(
                AbrirNexbi.loginNexbiPage());

        theActorInTheSpotlight().attemptsTo(
                CredencialAccesoNexbi.withData(user, password, ambiente));
    }

    @When("^consulto un cliente por (.*) y visualizo la Información General$")
    public void Consulta_Nexbi_Información_General(String codigoUnico) throws InterruptedException, IOException {

        ArrayList<String> numeroPuntoServicioArrays = new ArrayList<>();
        ArrayList<String> nombrePuntoServicioArrays = new ArrayList<>();
        ArrayList<String> tipoClienteArrays = new ArrayList<>();
        ArrayList<String> nivelServicioArrays = new ArrayList<>();
        ArrayList<String> cuentaCargoArrays = new ArrayList<>();
        ArrayList<String> monedaCuentaArrays = new ArrayList<>();
        ArrayList<String> tipoAccesoArrays = new ArrayList<>();
        ArrayList<Integer> cantUsuarioActivoArrays = new ArrayList<>();
        ArrayList<Integer> cantUsuarioArrays = new ArrayList<>();
        ArrayList<String> informacion = new ArrayList<>();
        List<WebElement> listaPuntoServicio = new ArrayList<>();
        String numeroPuntoServicio = "";
        numeroPuntoServicioArrays.add("");
        boolean condicion;
        int cantScroll=1;

        do{
            theActorInTheSpotlight().attemptsTo(ConsultaPorCodigo.withData(codigoUnico));

            condicion = false;
            for (int i = 0; i < cantScroll; i++) {
                if (i > 0) {
                    theActorInTheSpotlight().attemptsTo(Scroll.to(QueryPage.TABLE_PUNTOSERVICIO_RECORRIDO.of(String.valueOf(listaPuntoServicio.size()-3)).called(String.valueOf(listaPuntoServicio.size()-3))));
                }
                listaPuntoServicio = NexbiQuestions.listaPuntoServicio().answeredBy(theActorInTheSpotlight());
            }

            numeroPuntoServicio = TextContent.of(QueryPage.PUNTO_SERVICIO.of(String.valueOf(1)).called(String.valueOf(1))).answeredBy(theActorInTheSpotlight());

            for (int i = 0; i < numeroPuntoServicioArrays.size() ; i++) {
                if (numeroPuntoServicio.equals(numeroPuntoServicioArrays.get(i)) || numeroPuntoServicioArrays.size() == 1) {
                    for (int j = 1; j <= listaPuntoServicio.size(); j++) {
                        if(condicion) {
                            theActorInTheSpotlight().attemptsTo(ConsultaPorCodigo.withData(codigoUnico));
                            for (int h = 0; h < cantScroll; h++) {
                                if (h > 0) {
                                    theActorInTheSpotlight().attemptsTo(Scroll.to(QueryPage.TABLE_PUNTOSERVICIO_RECORRIDO.of(String.valueOf(listaPuntoServicio.size()-3)).called(String.valueOf(listaPuntoServicio.size()-3))));
                                }
                                listaPuntoServicio = NexbiQuestions.listaPuntoServicio().answeredBy(theActorInTheSpotlight());
                            }
                        }

                        numeroPuntoServicio = TextContent.of(QueryPage.PUNTO_SERVICIO.of(String.valueOf(j)).called(String.valueOf(j))).answeredBy(theActorInTheSpotlight());
                        try {
                            Assert.assertEquals(numeroPuntoServicio, numeroPuntoServicioArrays.get(i));
                        } catch (Throwable e) {
                            condicion = true;

                            numeroPuntoServicioArrays.add(TextContent.of(QueryPage.PUNTO_SERVICIO.of(String.valueOf(j)).called(String.valueOf(j))).answeredBy(theActorInTheSpotlight()));
                            nombrePuntoServicioArrays.add(TextContent.of(QueryPage.NOMBRE_PUNTO_SERVICIO.of(String.valueOf(j)).called(String.valueOf(j))).answeredBy(theActorInTheSpotlight()));

                            theActorInTheSpotlight().attemptsTo(
                                    ObtenerInformacionGeneralNuevo.withData(j, nombrePuntoServicioArrays, tipoClienteArrays, nivelServicioArrays, cuentaCargoArrays, monedaCuentaArrays),
                                    new VisualizarVentanaUsuarios());

                            if (QueryPage.INPUT_NOMBRE_USUARIO.isVisibleFor(theActorInTheSpotlight())) {
                                theActorInTheSpotlight().attemptsTo(
                                        ObtenerDetalleUsuario2Nuevo.withData(nombrePuntoServicioArrays, cantUsuarioActivoArrays, cantUsuarioArrays));
                                ObtenerTipoAccesoNuevo.ConUsuarios(nombrePuntoServicioArrays,tipoAccesoArrays);
                            }else{
                                cantUsuarioArrays.add(0);
                                cantUsuarioActivoArrays.add(0);
                                Serenity.setSessionVariable("cantUsuarioArrays").to(cantUsuarioArrays);
                                Serenity.setSessionVariable("cantUsuarioActivoArrays").to(cantUsuarioActivoArrays);
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
        }while(condicion);

        Serenity.setSessionVariable("numeroPuntoServicioArrays").to(numeroPuntoServicioArrays);
        Serenity.setSessionVariable("nombrePuntoServicioArrays").to(nombrePuntoServicioArrays);
        BrowseTheWeb.as(theActorInTheSpotlight()).getDriver().close();
        ImprimirDetalleInformacionNuevo.datosValidos(informacion);
     }

    @When("consulto un cliente por Punto de Servicio y visualizo la Información General sin Usuarios")
    public void consultoUnClientePorCodigoUnicoYVisualizoLaInformaciónGeneralSinUsuarios(DataTable dataTable) throws IOException {

        List<Map<String, String>> listaPuntoServicio = dataTable.asMaps(String.class, String.class);
        Serenity.setSessionVariable("listaPuntoServicio").to(listaPuntoServicio);
        String[] tipoClienteArrays = new String[listaPuntoServicio.size()];
        String[] nivelServicioArrays = new String[listaPuntoServicio.size()];
        String[] cuentaCargoArrays = new String[listaPuntoServicio.size()];
        String[] monedaCuentaArrays = new String[listaPuntoServicio.size()];
        String[] tipoAccesoArrays = new String[listaPuntoServicio.size()];
        int[] cantUsuarioActivoArrays = new int[listaPuntoServicio.size()];
        String[] nombrePuntoServicioArrays = new String[listaPuntoServicio.size()];
        String[] numeroPuntoServicioArrays = new String[listaPuntoServicio.size()];

        int cont = 0;
        for (Map<String, String> puntoServicio : listaPuntoServicio) {
            cont++;
            theActorInTheSpotlight().attemptsTo(
                    ConsultaPorPuntoServicio.withData(puntoServicio.get("punto")));
            theActorInTheSpotlight().attemptsTo(
                    ObtenerDatosLista.withData(cont, nombrePuntoServicioArrays, numeroPuntoServicioArrays, puntoServicio.size()));
            theActorInTheSpotlight().attemptsTo(
                    ObtenerInformacionGeneralSinUsuarios.withData(cont, tipoClienteArrays, nivelServicioArrays, cuentaCargoArrays, monedaCuentaArrays),
                    ObtenerMsjSinUsuario.withData(cont, tipoAccesoArrays, cantUsuarioActivoArrays));
        }
        BrowseTheWeb.as(theActorInTheSpotlight()).getDriver().close();
        ArrayList<String> informacion = new ArrayList<>();
        ImprimirDetalleInformacion.datosInválidos(listaPuntoServicio, informacion);
    }

    @When("^consulto un cliente por (.*) y visualizo$")
    public void consultoUnClientePorCodigoUnicoYVisualizo(String codigoUnico) throws InterruptedException, IOException {

        ArrayList<String> numeroPuntoServicioArrays = new ArrayList<>();
        ArrayList<String> nombrePuntoServicioArrays = new ArrayList<>();
        ArrayList<String> tipoClienteArrays = new ArrayList<>();
        ArrayList<String> nivelServicioArrays = new ArrayList<>();
        ArrayList<String> cuentaCargoArrays = new ArrayList<>();
        ArrayList<String> monedaCuentaArrays = new ArrayList<>();
        ArrayList<String> tipoAccesoArrays = new ArrayList<>();
        ArrayList<Integer> cantUsuarioActivoArrays = new ArrayList<>();
        ArrayList<Integer> cantUsuarioArrays = new ArrayList<>();
        ArrayList<String> informacion = new ArrayList<>();
        List<WebElement> listaPuntoServicio = new ArrayList<>();
        String numeroPuntoServicio = "";
        numeroPuntoServicioArrays.add("");
        boolean condicion;
        int cantScroll=1;

        do{
            theActorInTheSpotlight().attemptsTo(ConsultaPorCodigo.withData(codigoUnico));

            condicion = false;
            for (int i = 0; i < cantScroll; i++) {
                if (i > 0) {
                    theActorInTheSpotlight().attemptsTo(Scroll.to(QueryPage.TABLE_PUNTOSERVICIO_RECORRIDO.of(String.valueOf(listaPuntoServicio.size()-3)).called(String.valueOf(listaPuntoServicio.size()-3))));
                }
                listaPuntoServicio = NexbiQuestions.listaPuntoServicio().answeredBy(theActorInTheSpotlight());
            }

            numeroPuntoServicio = TextContent.of(QueryPage.PUNTO_SERVICIO.of(String.valueOf(1)).called(String.valueOf(1))).answeredBy(theActorInTheSpotlight());

            for (int i = 0; i < numeroPuntoServicioArrays.size() ; i++) {
                if (numeroPuntoServicio.equals(numeroPuntoServicioArrays.get(i)) || numeroPuntoServicioArrays.size() == 1) {
                    for (int j = 1; j <= listaPuntoServicio.size(); j++) {
                        if(condicion) {
                            theActorInTheSpotlight().attemptsTo(ConsultaPorCodigo.withData(codigoUnico));
                            for (int h = 0; h < cantScroll; h++) {
                                if (h > 0) {
                                    theActorInTheSpotlight().attemptsTo(Scroll.to(QueryPage.TABLE_PUNTOSERVICIO_RECORRIDO.of(String.valueOf(listaPuntoServicio.size()-3)).called(String.valueOf(listaPuntoServicio.size()-3))));
                                }
                                listaPuntoServicio = NexbiQuestions.listaPuntoServicio().answeredBy(theActorInTheSpotlight());
                            }
                        }

                        numeroPuntoServicio = TextContent.of(QueryPage.PUNTO_SERVICIO.of(String.valueOf(j)).called(String.valueOf(j))).answeredBy(theActorInTheSpotlight());
                        try {
                            Assert.assertEquals(numeroPuntoServicio, numeroPuntoServicioArrays.get(i));
                        } catch (Throwable e) {
                            condicion = true;

                            numeroPuntoServicioArrays.add(TextContent.of(QueryPage.PUNTO_SERVICIO.of(String.valueOf(j)).called(String.valueOf(j))).answeredBy(theActorInTheSpotlight()));
                            nombrePuntoServicioArrays.add(TextContent.of(QueryPage.NOMBRE_PUNTO_SERVICIO.of(String.valueOf(j)).called(String.valueOf(j))).answeredBy(theActorInTheSpotlight()));

                            theActorInTheSpotlight().attemptsTo(
                                    ObtenerInformacionGeneralNuevo.withData(j, nombrePuntoServicioArrays, tipoClienteArrays, nivelServicioArrays, cuentaCargoArrays, monedaCuentaArrays),
                                    new VisualizarVentanaUsuarios());

                            if (QueryPage.INPUT_NOMBRE_USUARIO.isVisibleFor(theActorInTheSpotlight())) {
                                theActorInTheSpotlight().attemptsTo(
                                        ObtenerDetalleUsuario2Nuevo.withData(nombrePuntoServicioArrays, cantUsuarioActivoArrays, cantUsuarioArrays));
                                ObtenerTipoAccesoNuevo.ConUsuarios(nombrePuntoServicioArrays,tipoAccesoArrays);
                            }else{
                                cantUsuarioArrays.add(0);
                                cantUsuarioActivoArrays.add(0);
                                Serenity.setSessionVariable("cantUsuarioArrays").to(cantUsuarioArrays);
                                Serenity.setSessionVariable("cantUsuarioActivoArrays").to(cantUsuarioActivoArrays);
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
        }while(condicion);

        Serenity.setSessionVariable("numeroPuntoServicioArrays").to(numeroPuntoServicioArrays);
        Serenity.setSessionVariable("nombrePuntoServicioArrays").to(nombrePuntoServicioArrays);
        BrowseTheWeb.as(theActorInTheSpotlight()).getDriver().close();
        ImprimirDetalleInformacionNuevo.datosValidos(informacion);
    }
}
