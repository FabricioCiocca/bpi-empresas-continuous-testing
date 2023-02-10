package com.everis.stepsdefinitions.mainframe;

import com.everis.questions.mainframe.Validacion;
import com.everis.tasks.mainframe.*;
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
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.CoreMatchers.equalTo;

public class MainframeStepsDef extends EmulatorActions {
    //private Scenario scenario;

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("^que soy un usuario e ingreso al CICS$")
    public void queSoyUnUsuarioEIngresoAlCICS() {

        UtilMainframe.getVariableOnSession(Constants.SCENARIO);
        getInstancia().connectServer(ManageEnvironment.getEnvironment());

        theActorInTheSpotlight().attemptsTo(
                new AccederMainframe(),
                new AgregarCredencialUno(),
                new LimpiarAcceso(),
                new AgregarCredencialUno(),
                IngresarAplicativo.withData("1"));

        String mensaje = Serenity.sessionVariableCalled("mensaje");
        if (mensaje.equals("5555")) {
            theActorInTheSpotlight().attemptsTo(
                    new ConsultaDirecta(),
                    new AgregarCredencialUno());
        }
    }

    @And("se ingresa al CICS con mis credenciales$")
    public void SeIngresaAlCICSConMisCredenciales() {

        UtilMainframe.getVariableOnSession(Constants.SCENARIO);
        getInstancia().connectServer(ManageEnvironment.getEnvironment());

        theActorInTheSpotlight().attemptsTo(
                new AccederMainframe(),
                new AgregarCredencialUno(),
                new LimpiarAcceso(),
                new AgregarCredencialUno(),
                IngresarAplicativo.withData("1"));

        String mensaje = Serenity.sessionVariableCalled("mensaje");
        if (mensaje.equals("5555")) {
            theActorInTheSpotlight().attemptsTo(
                    new ConsultaDirecta(),
                    new AgregarCredencialUno());
        }
    }

    @And("consulto tarifarios por nivel de servicio")
    public void consultoTarifariosPorNivelDeServicio() {

        String[] nivelConceptoArray = {"Negocio", "Empresa", "Corporativo", "Usuario Adicional"};
        String[] numeroConceptoArray = new String[nivelConceptoArray.length];
        String[] montoTarifaNivelArray = new String[nivelConceptoArray.length];

        ArrayList<String> nivelServicioArrays = Serenity.sessionVariableCalled("nivelServicioArrays");
        ArrayList<Integer> cantUsuarioActivoConfirmadoArrays = Serenity.sessionVariableCalled("cantUsuarioActivoConfirmadoArrays");

        for (int i = 0; i < nivelServicioArrays.size(); i++) {
            switch (nivelServicioArrays.get(i)) {
                case "Negocio":
                    numeroConceptoArray[0] = "20";
                    break;
                case "Empresa":
                    numeroConceptoArray[1] = "21";
                    break;
                case "Corporativo":
                    numeroConceptoArray[2] = "22";
                    break;
            }
            if (cantUsuarioActivoConfirmadoArrays.get(i) != null) {
                if (cantUsuarioActivoConfirmadoArrays.get(i) > 3) {
                    numeroConceptoArray[3] = "40";
                }
            }
        }

        theActorInTheSpotlight().attemptsTo(
                IngresarAplicativoAutorizado.withData("26"));

        String codigoUnico = Serenity.sessionVariableCalled("codigoUnico");
        String[] listaTarifaPreferencial = new String[nivelConceptoArray.length];

        for (int i = 0; i < numeroConceptoArray.length; i++) {
            if (numeroConceptoArray[i] != null) {
                theActorInTheSpotlight().attemptsTo(
                        ConsultaTarifaPreferencial.withData(numeroConceptoArray[i], codigoUnico));

                String mensajeCliente = Serenity.sessionVariableCalled("mensajeCliente");
                if (mensajeCliente.equals("Cliente no existe")) {
                    theActorInTheSpotlight().attemptsTo(
                            new VisualizarVentanaAnterior(),
                            ConsultaTarifaNormal.withData(numeroConceptoArray[i]),
                            ObtenerMontoTarifa.withData(i, montoTarifaNivelArray),
                            new VisualizarVentanaAnterior(),
                            new VisualizarVentanaAnterior(),
                            new VisualizarVentanaAnterior());
                } else {
                    theActorInTheSpotlight().attemptsTo(
                            ObtenerMontoTarifa.withData(i, montoTarifaNivelArray),
                            new VisualizarVentanaAnterior(),
                            new VisualizarVentanaAnterior(),
                            new VisualizarVentanaAnterior());
                    listaTarifaPreferencial[i] = nivelConceptoArray[i];
                }
            }
        }
        theActorInTheSpotlight().attemptsTo(
                new CerrarSesionMainframe()
        );
        Serenity.setSessionVariable("listaTarifaPreferencial").to(listaTarifaPreferencial);
        Serenity.setSessionVariable("nivelConceptoArray").to(nivelConceptoArray);
        Serenity.setSessionVariable("numeroConceptoArray").to(numeroConceptoArray);
    }

    @When("^se ingresa la tarifa preferencial: (.*), (.*), (.*); desde el (.*) hasta el (.*), (.*), (.*)$")
    public void seIngresaLaTarifaPreferencial(String nivelConcepto, String codigoUnico, String monto, String fechaInicio, String fechaFin, String referencia, String autorizador) throws ParseException {

        String[] nivelConceptoArray = {"Negocio", "Empresa", "Corporativo", "Usuario Adicional"};
        String[] numeroConceptoArray = {"20", "21", "22", "40"};
        String[] montoTarifaNivelArray = new String[nivelConceptoArray.length];
        boolean condicion = false;

        theActorInTheSpotlight().attemptsTo(
                IngresarAplicativoAutorizado.withData("26"));

        for (int i = 0; i < nivelConceptoArray.length; i++) {
            if (nivelConceptoArray[i].equalsIgnoreCase(nivelConcepto)) {
                theActorInTheSpotlight().attemptsTo(
                        ConsultaTarifaPreferencial.withData(numeroConceptoArray[i], codigoUnico));

                String mensajeCliente = Serenity.sessionVariableCalled("mensajeCliente");
                if (mensajeCliente.equals("Cliente no existe")) {
                    theActorInTheSpotlight().attemptsTo(
                            new VisualizarVentanaAnterior());

                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                    Date fechadeHoy = formato.parse(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    Date fechadeInicio = formato.parse(fechaInicio);
                    Date fechadeFin = formato.parse(fechaFin);
                    condicion = true;

                    if (fechadeInicio.equals(fechadeHoy)) {
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
                        throw new PendingException("La Fecha de Inicio debe igual a la Fecha de Hoy");//antes "La Fecha de Inicio debe ser mayor o igual que la Fecha de Hoy"
                    }

                } else {
                    theActorInTheSpotlight().attemptsTo(
                            ObtenerMontoTarifa.withData(i, montoTarifaNivelArray));
                    montoTarifaNivelArray = Serenity.sessionVariableCalled("montoTarifaNivelArray");
                    throw new PendingException(
                            "El Codigo Unico " + codigoUnico + " ya cuenta con Tarifa Preferencial en " + nivelConcepto + " con un monto de S/ " + montoTarifaNivelArray[i]);
                }
            }
        }
        if (!condicion) {
            throw new PendingException("El Concepto '" + nivelConcepto + "' es Incorrecto");
        }
        Serenity.setSessionVariable("monto").to(monto);
        Serenity.setSessionVariable("codigoUnico").to(codigoUnico);
        Serenity.setSessionVariable("nivelServicio").to(nivelConcepto);
        Serenity.setSessionVariable("nivelConceptoArray").to(nivelConceptoArray);
        Serenity.setSessionVariable("numeroConceptoArray").to(numeroConceptoArray);
        Serenity.setSessionVariable("montoTarifaNivelArray").to(montoTarifaNivelArray);
    }

    @Then("^valido que la tarifa preferencial se haya ingresado correctamente$")
    public void validoQueLaTarifaPreferencialSeHayaIngresadoCorrectamente() {

        String monto = Serenity.sessionVariableCalled("monto");
        String codigoUnico = Serenity.sessionVariableCalled("codigoUnico");
        String nivelServicio = Serenity.sessionVariableCalled("nivelServicio");
        String[] nivelConceptoArray = Serenity.sessionVariableCalled("nivelConceptoArray");
        String[] numeroConceptoArray = Serenity.sessionVariableCalled("numeroConceptoArray");
        String[] montoTarifaNivelArray = Serenity.sessionVariableCalled("montoTarifaNivelArray");
        ;

        for (int i = 0; i < nivelConceptoArray.length; i++) {
            if (nivelConceptoArray[i].equalsIgnoreCase(nivelServicio)) {
                theActorInTheSpotlight().attemptsTo(
                        ConsultaTarifaPreferencial.withData(numeroConceptoArray[i], codigoUnico));

                String mensajeCliente = Serenity.sessionVariableCalled("mensajeCliente");
                if (mensajeCliente.equals("Cliente no existe")) {
                    theActorInTheSpotlight().attemptsTo(
                            new CerrarSesionMainframe());
                    throw new PendingException("No se Encontro Tarifa Preferencial");
                } else {
                    theActorInTheSpotlight().attemptsTo(
                            ObtenerMontoTarifa.withData(i, montoTarifaNivelArray));
                    theActorInTheSpotlight().attemptsTo(
                            new CerrarSesionMainframe());
                    theActorInTheSpotlight().should(
                            seeThat(Validacion.ingresoTarifa(i, monto, montoTarifaNivelArray), equalTo(true)));
                }
            }
        }
    }

    @When("^se modifica la tarifa preferencial: (.*), (.*), (.*); desde el (.*) hasta el (.*), (.*), (.*)$")
    public void seModificaLaTarifaPreferencial(String nivelConcepto, String codigoUnico, String monto, String fechaInicio, String fechaFin, String referencia, String autorizador) throws ParseException {

        String[] nivelConceptoArray = {"Negocio", "Empresa", "Corporativo", "Usuario Adicional"};
        String[] numeroConceptoArray = {"20", "21", "22", "40"};
        boolean condicion = false;

        theActorInTheSpotlight().attemptsTo(
                IngresarAplicativoAutorizado.withData("26"));

        for (int i = 0; i < nivelConceptoArray.length; i++) {
            if (nivelConceptoArray[i].equalsIgnoreCase(nivelConcepto)) {
                theActorInTheSpotlight().attemptsTo(
                        ConsultaTarifaPreferencial.withData(numeroConceptoArray[i], codigoUnico));

                String mensajeCliente = Serenity.sessionVariableCalled("mensajeCliente");
                String lineaModificacion = Serenity.sessionVariableCalled("lineaModificacion");

                if (mensajeCliente.equals("Cliente no existe")) {
                    throw new PendingException("El Codigo Unico " + codigoUnico + " no cuenta con Tarifa Preferencial en " + nivelConcepto);

                } else {
                    if (lineaModificacion.equals("")) {
                        theActorInTheSpotlight().attemptsTo(
                                new VisualizarVentanaAnterior(),
                                new VisualizarVentanaAnterior());

                        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechadeHoy = formato.parse(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                        Date fechadeInicio = formato.parse(fechaInicio);
                        Date fechadeFin = formato.parse(fechaFin);
                        condicion = true;

                        if (fechadeInicio.equals(fechadeHoy) || fechadeInicio.after(fechadeHoy)) {
                            if (fechadeInicio.before(fechadeFin)) {
                                if (monto.toLowerCase().contains("exo")) {
                                    theActorInTheSpotlight().attemptsTo(
                                            ModificaTarifaPreferencialExonerado.withData(numeroConceptoArray[i], codigoUnico, fechaInicio, fechaFin, referencia, autorizador));
                                } else {
                                    theActorInTheSpotlight().attemptsTo(
                                            ModificaTarifaPreferencialMonto.withData(numeroConceptoArray[i], codigoUnico, fechaInicio, fechaFin, referencia, autorizador, monto));
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
                    } else {
                        throw new PendingException("El Codigo Unico " + codigoUnico + " en " + nivelConcepto.toUpperCase().charAt(0) +
                                nivelConcepto.substring(1, nivelConcepto.length()).toLowerCase() + " alcanzo el maximo de moficiaciones futuras");
                    }
                }
            }
        }
        if (!condicion) {
            throw new PendingException("El Dato en el 'nivel de servicio' es Incorrecto");
        }
        Serenity.setSessionVariable("monto").to(monto);
        Serenity.setSessionVariable("codigoUnico").to(codigoUnico);
        Serenity.setSessionVariable("fechaInicio").to(fechaInicio);
        Serenity.setSessionVariable("nivelServicio").to(nivelConcepto);
        Serenity.setSessionVariable("nivelConceptoArray").to(nivelConceptoArray);
        Serenity.setSessionVariable("numeroConceptoArray").to(numeroConceptoArray);
    }

    @Then("^valido que la tarifa preferencial se haya modificado correctamente$")
    public void validoQueLaTarifaPreferencialSeHayaModificadoCorrectamente() throws ParseException {

        String monto = Serenity.sessionVariableCalled("monto");
        String codigoUnico = Serenity.sessionVariableCalled("codigoUnico");
        String nivelServicio = Serenity.sessionVariableCalled("nivelServicio");
        String fechaInicio = Serenity.sessionVariableCalled("fechaInicio");
        String[] nivelConceptoArray = Serenity.sessionVariableCalled("nivelConceptoArray");
        String[] numeroConceptoArray = Serenity.sessionVariableCalled("numeroConceptoArray");
        String[] montoTarifaNivelArray = new String[nivelConceptoArray.length];

        for (int i = 0; i < nivelConceptoArray.length; i++) {
            if (nivelConceptoArray[i].equalsIgnoreCase(nivelServicio)) {
                theActorInTheSpotlight().attemptsTo(
                        ConsultaTarifaPreferencial.withData(numeroConceptoArray[i], codigoUnico));

                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                Date fechadeHoy = formato.parse(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                Date fechadeInicio = formato.parse(fechaInicio);

                if (fechadeInicio.equals(fechadeHoy)) {
                    theActorInTheSpotlight().attemptsTo(
                            ObtenerMontoTarifa.withData(i, montoTarifaNivelArray));
                } else {
                    theActorInTheSpotlight().attemptsTo(
                            ObtenerMontoTarifaModificado.withData(i, montoTarifaNivelArray));
                }
                theActorInTheSpotlight().attemptsTo(
                        new CerrarSesionMainframe());
                theActorInTheSpotlight().should(
                        seeThat(Validacion.modificoTarifa(i, monto, montoTarifaNivelArray), equalTo(true)));
            }
        }
    }
}
