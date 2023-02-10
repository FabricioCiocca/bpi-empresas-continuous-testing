package com.everis.tasks.mainframe;

import com.everis.bpi.questions.web.nexbi.NexbiQuestions;
import com.everis.bpi.tasks.web.nexbi.query.ConsultaPorCodigo;
import environment.ManageEnvironment;
import io.cucumber.java.Before;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import mainframe.com.bdd.generic.Constants;
import mainframe.com.bdd.lib.EmulatorActions;
import mainframe.com.bdd.util.UtilMainframe;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class PruebaSD extends EmulatorActions {

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @When("^consulto un cliente por (.*) y visualizo$")
    public void consultoUnClientePorCodigoUnicoYVisualizo(String codigoUnico) throws InterruptedException, IOException {

        theActorInTheSpotlight().attemptsTo(ConsultaPorCodigo.withData(codigoUnico));

        Serenity.setSessionVariable("listaPuntoServicio").to(theActorInTheSpotlight().asksFor(NexbiQuestions.listaPuntoServicio()));
        /*List<WebElement> listaPuntoServicio = Serenity.sessionVariableCalled("listaPuntoServicio");

        String[] nombrePuntoServicioArrays = new String[listaPuntoServicio.size()];
        String[] numeroPuntoServicioArrays = new String[listaPuntoServicio.size()];

        for (int i = 1; i <= listaPuntoServicio.size(); i++) {
            theActorInTheSpotlight().attemptsTo(ObtenerDatosLista.withData(i, nombrePuntoServicioArrays, numeroPuntoServicioArrays));
        }

        String[] tipoClienteArrays = new String[listaPuntoServicio.size()];
        String[] nivelServicioArrays = new String[listaPuntoServicio.size()];
        String[] cuentaCargoArrays = new String[listaPuntoServicio.size()];
        String[] monedaCuentaArrays = new String[listaPuntoServicio.size()];
        String[] tipoAccesoArrays = new String[listaPuntoServicio.size()];
        int[] cantUsuarioActivoConfirmadoArrays = new int[listaPuntoServicio.size()];
        int[] cantUsuarioActivoArrays = new int[listaPuntoServicio.size()];
        ArrayList<String> informacion = new ArrayList<>();
        for (int i = 1; i <= listaPuntoServicio.size(); i++) {

            if (i > 1) {
                theActorInTheSpotlight().attemptsTo(ConsultaPorCodigo.withData(codigoUnico));
            }
            theActorInTheSpotlight().attemptsTo(
                    ObtenerInformacionGeneral.withData(i, tipoClienteArrays, nivelServicioArrays, cuentaCargoArrays, monedaCuentaArrays),
                    new VisualizarVentanaUsuarios());

            if (QueryPage.INPUT_NOMBRE_USUARIO.isVisibleFor(theActorInTheSpotlight())) {
                theActorInTheSpotlight().attemptsTo(
                        ObtenerDetalleUsuario2.withData(i, cantUsuarioActivoConfirmadoArrays, cantUsuarioActivoArrays) );
                ObtenerTipoAcceso.ConUsuarios(i,tipoAccesoArrays);
            }else{
                cantUsuarioActivoArrays[i-1]=0;
                cantUsuarioActivoConfirmadoArrays[i-1]=0;
                Serenity.setSessionVariable("cantUsuarioActivoArrays").to(cantUsuarioActivoArrays);
                Serenity.setSessionVariable("cantUsuarioActivoConfirmadoArrays").to(cantUsuarioActivoConfirmadoArrays);
                ObtenerTipoAcceso.SinUsuarios(i,tipoAccesoArrays);
            }
            theActorInTheSpotlight().attemptsTo(
                    new VisualizarIsvNexbi());
        }*/
        BrowseTheWeb.as(theActorInTheSpotlight()).getDriver().close();
        //ImprimirDetalleInformacion.datosValidos(listaPuntoServicio,informacion);
    }

    @Given("^que soy un usuario e ingreso al CICS con (.*) credencial$")
    public void queSoyUnUsuarioEIngresoAlCICSConCredencial(String cantCredencial) {
        UtilMainframe.getVariableOnSession(Constants.SCENARIO);
        getInstancia().connectServer(ManageEnvironment.getEnvironment());

        theActorCalled("User").attemptsTo(
                new AccederMainframe());

        if (cantCredencial.equals("1")) {
            theActorInTheSpotlight().attemptsTo(
                    new AgregarCredencialUno(),
                    new ConsultaDirecta());
        } else if (cantCredencial.equals("2")) {
            theActorInTheSpotlight().attemptsTo(
                    new AgregarCredencialDos(),
                    new LimpiarAcceso(),
                    new AgregarCredencialDos(),
                    new ConsultaIndirecta(),
                    new AgregarCredencialUno());
        } else {
            throw new PendingException("Máximo 2 Usuarios");
        }
    }

    @And("se ingresa al CICS con (.*) credencial$")
    public void ingresoAlCICSConCredencial(String cantCredencial) {

        UtilMainframe.getVariableOnSession(Constants.SCENARIO);
        getInstancia().connectServer(ManageEnvironment.getEnvironment());

        theActorCalled("User").attemptsTo(
                new AccederMainframe());

        if (cantCredencial.equals("1")) {
            theActorInTheSpotlight().attemptsTo(
                    new AgregarCredencialUno(),
                    new ConsultaDirecta());
        } else if (cantCredencial.equals("2")) {
            theActorInTheSpotlight().attemptsTo(
                    new AgregarCredencialDos(),
                    new LimpiarAcceso(),
                    new AgregarCredencialDos(),
                    new ConsultaIndirecta(),
                    new AgregarCredencialUno());
        } else {
            throw new PendingException("Máximo 2 Usuarios");
        }
    }

    @When("^consulto la tarifa de: (.*), (.*)$")
    public void consultoLaTarifade(String nivelConcepto, String codigoUnico) {

        String[] nivelConceptoArray = {"Negocio", "Empresa", "Corporativo", "Usuario Adicional"};
        String[] numeroConceptoArray = {"20", "21", "22", "40"};
        String[] montoTarifaNivelArray = new String[nivelConceptoArray.length];

        theActorInTheSpotlight().attemptsTo(
                IngresarAplicativoAutorizado.withData("26"));

        for (int i = 0; i < nivelConceptoArray.length; i++) {
            if (nivelConceptoArray[i].equalsIgnoreCase(nivelConcepto)) {
                theActorInTheSpotlight().attemptsTo(
                        ConsultaTarifaPreferencial.withData(numeroConceptoArray[i], codigoUnico));

                String mensajeCliente = Serenity.sessionVariableCalled("mensajeCliente");
                if (!mensajeCliente.equals("Cliente no existe")) {
                    theActorInTheSpotlight().attemptsTo(
                            ObtenerMontoTarifa.withData(i, montoTarifaNivelArray));
                    montoTarifaNivelArray = Serenity.sessionVariableCalled("montoTarifaNivelArray");
                    throw new PendingException(
                            "El Codigo Unico " + codigoUnico + " ya cuenta con Tarifa Preferencial en " + nivelConcepto + " con un monto de S/ " + montoTarifaNivelArray[i]);
                }
            }
        }
        Serenity.setSessionVariable("codigoUnico").to(codigoUnico);
        Serenity.setSessionVariable("nivelConcepto").to(nivelConcepto);
        Serenity.setSessionVariable("nivelConceptoArray").to(nivelConceptoArray);
        Serenity.setSessionVariable("numeroConceptoArray").to(numeroConceptoArray);
    }

    @And("^se ingresa una tarifa preferencial: (.*); desde el (.*) hasta el (.*), (.*), (.*)$")
    public void seIngresaUnaTarifaPreferecial(String monto, String fechaInicio, String fechaFin, String referencia, String autorizador) throws ParseException {

        String codigoUnico = Serenity.sessionVariableCalled("codigoUnico");
        String nivelConcepto = Serenity.sessionVariableCalled("nivelConcepto");
        String[] nivelConceptoArray = Serenity.sessionVariableCalled("nivelConceptoArray");
        String[] numeroConceptoArray = Serenity.sessionVariableCalled("numeroConceptoArray");
        boolean condicion = false;

        for (int i = 0; i < nivelConceptoArray.length; i++) {
            if (nivelConceptoArray[i].equalsIgnoreCase(nivelConcepto)) {
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                Date fechadeHoy = formato.parse(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                Date fechadeInicio = formato.parse(fechaInicio);
                Date fechadeFin = formato.parse(fechaFin);
                condicion = true;

                if (fechadeInicio.equals(fechadeHoy) || fechadeInicio.after(fechadeHoy)) {
                    if (fechadeInicio.before(fechadeFin)) {
                        if (monto.toLowerCase().contains("exo")) {
                            theActorInTheSpotlight().attemptsTo(
                                    IngresaTarifaPreferencialExonerado.withData(numeroConceptoArray[i], codigoUnico, fechaInicio, fechaFin, referencia, autorizador));
                        } else {
                            theActorInTheSpotlight().attemptsTo(
                                    IngresaTarifaPreferencialMonto.withData(numeroConceptoArray[i], codigoUnico, fechaInicio, fechaFin, referencia, autorizador, monto));
                        }
                        theActorInTheSpotlight().attemptsTo(
                                new VisualizarVentanaAnterior(),
                                new VisualizarVentanaAnterior(),
                                new VisualizarVentanaAnterior());
                    } else {
                        throw new PendingException("La Fecha de Fin debe ser mayor que la Fecha de Inicio");
                    }
                } else {
                    throw new PendingException("La Fecha de Inicio debe ser mayor o igual que la Fecha de Hoy");
                }
            }
        }
        if (!condicion) {
            throw new PendingException("El Dato en el 'nivel de servicio' es Incorrecto");
        }
        Serenity.setSessionVariable("codigoUnico").to(codigoUnico);
        Serenity.setSessionVariable("nivelServicio").to(nivelConcepto);
        Serenity.setSessionVariable("nivelConceptoArray").to(nivelConceptoArray);
        Serenity.setSessionVariable("numeroConceptoArray").to(numeroConceptoArray);
    }

    @Then("^consulto tarifarios por nivel de servicio CONSULTA")
    public void consultoTarifariosPorNivelDeServicioConsulta() {
        theActorInTheSpotlight().attemptsTo(
                IngresarAplicativoAutorizado.withData("26"));

        String codigoUnico = Serenity.sessionVariableCalled("codigoUnico");
        ArrayList<String> listaTarifaPreferencial = new ArrayList<>();

        String[] numeroConceptoArray = {"20", "21", "22", "40"};

        for (int i = 0; i < numeroConceptoArray.length; i++) {
            theActorInTheSpotlight().attemptsTo(
                    ConsultaTarifaNormal.withData(numeroConceptoArray[i]),
                    //ObtenerMontoTarifa.withData(numeroConceptoArray[i]),
                    new VisualizarVentanaAnterior(),
                    new VisualizarVentanaAnterior(),
                    new VisualizarVentanaAnterior(),
                    ConsultaTarifaPreferencial.withData(numeroConceptoArray[i], codigoUnico));

            String mensajeCliente = Serenity.sessionVariableCalled("mensajeCliente");
            if (mensajeCliente.equals("Cliente no existe")) {
                theActorInTheSpotlight().attemptsTo(
                        new VisualizarVentanaAnterior());
            } else {
                theActorInTheSpotlight().attemptsTo(
                        //ObtenerMontoTarifa.withData(numeroConceptoArray[i]),
                        new VisualizarVentanaAnterior(),
                        new VisualizarVentanaAnterior(),
                        new VisualizarVentanaAnterior());

                if (numeroConceptoArray[i].equals("20")) {
                    listaTarifaPreferencial.add("Nivel Negocio:      Tarifa Preferecial");
                } else if (numeroConceptoArray[i].equals("21")) {
                    listaTarifaPreferencial.add("Nivel Empresa:      Tarifa Preferecial");
                } else if (numeroConceptoArray[i].equals("22")) {
                    listaTarifaPreferencial.add("Nivel Corporativo:  Tarifa Preferecial");
                } else if (numeroConceptoArray[i].equals("40")) {
                    listaTarifaPreferencial.add("Usuario Adicional:  Tarifa Preferecial");
                }
            }
        }
        Serenity.setSessionVariable("listaTarifaPreferencial").to(listaTarifaPreferencial);
    }
}
