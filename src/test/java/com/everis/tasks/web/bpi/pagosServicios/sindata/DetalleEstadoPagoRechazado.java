package com.everis.tasks.web.bpi.pagosServicios.sindata;

import com.everis.questions.web.bpi.EstadoPagoQuestions;
import com.everis.stepsdefinitions.web.bpi.login.LoginStepDefinitions;
import com.everis.userinterfaces.web.bpi.LoginPage;
import lombok.SneakyThrows;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.By;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class DetalleEstadoPagoRechazado implements Task {
    private final String dniUsuario;
    private final String tipoCuenta;
    private final String cuentaOrigen;

    public DetalleEstadoPagoRechazado(String dniUsuario, String tipoCuenta, String cuentaOrigen) {
        this.dniUsuario = dniUsuario;
        this.tipoCuenta = tipoCuenta;
        this.cuentaOrigen = cuentaOrigen;
    }
    @SneakyThrows
    @Override
    public <T extends Actor> void performAs(T actor) {
        Target DETALLE_NUMERO_SOLICITUD_PROCESS = Target.the("Numero de Solicitud").located(By.xpath("//ibk-card-description[@data-test='lblRequestNumberValue']"));
        Target FECHA_HORA_PROCESS = Target.the("Fecha y hora de envío").located(By.xpath("//ibk-card-description[@data-test='lblDateSendValue']"));
        Target MONTO_PAGO_PROCESS = Target.the("Monto").located(By.xpath("(//*[@data-test='lblAmountValue'])"));
        LoginStepDefinitions.pagosServiciosData.setNroSolicitudProcess(String.valueOf(DETALLE_NUMERO_SOLICITUD_PROCESS.resolveFor(actor).getText()));
        LoginStepDefinitions.pagosServiciosData.setFechaHora(String.valueOf(FECHA_HORA_PROCESS.resolveFor(actor).getText()));
        LoginStepDefinitions.pagosServiciosData.setNombreusuario(dniUsuario);
        LoginStepDefinitions.pagosServiciosData.setMontoProcess(String.valueOf(MONTO_PAGO_PROCESS.resolveFor(actor).getText()));
        LoginStepDefinitions.pagosServiciosData.setDatosCargoDetalle(tipoCuenta + " " + cuentaOrigen);
        String[] fechaHoraAux = LoginStepDefinitions.pagosServiciosData.getFechaHora().split("-");
        actor.attemptsTo(WaitUntil.the(LoginPage.REVISA_DETALLE_ESTADO, isVisible()).forNoMoreThan(4).seconds(), Click.on(LoginPage.REVISA_DETALLE_ESTADO));
        Target DETALLE_ESTADO_SOLICITUD = Target.the("Estado de Servicio - Detalle").located(By.xpath("(//div[@data-test='txtEstado'])"));
        LoginStepDefinitions.pagosServiciosData.setEstadoSolicitudDetalle(String.valueOf(DETALLE_ESTADO_SOLICITUD.resolveFor(actor).getText()));

        if (LoginStepDefinitions.pagosServiciosData.getEstadoSolicitudDetalle().equals("En proceso")) {

            while (!LoginStepDefinitions.pagosServiciosData.getEstadoSolicitudDetalle().trim().equals("Rechazada")) {
                theActorInTheSpotlight().attemptsTo(WaitUntil.the(LoginPage.REGRESAR_DETALLE_ESTADO, isVisible()).forNoMoreThan(150).seconds(), Click.on(LoginPage.REGRESAR_DETALLE_ESTADO));
                theActorInTheSpotlight().attemptsTo(WaitUntil.the(LoginPage.REVISA_DETALLE_ESTADO, isVisible()).forNoMoreThan(150).seconds(), Click.on(LoginPage.REVISA_DETALLE_ESTADO));
                Target DETALLE_ESTADO_SOLICITUD2 = Target.the("Estado de Servicio - Detalle").located(By.xpath("(//div[@data-test='txtEstado'])"));
                LoginStepDefinitions.pagosServiciosData.setEstadoSolicitudDetalle(String.valueOf(DETALLE_ESTADO_SOLICITUD2.resolveFor(actor).getText()));
            }
        }
        theActorInTheSpotlight().should(seeThat(EstadoPagoQuestions.EstadoServicioDetalleRechazada(), equalTo(LoginStepDefinitions.pagosServiciosData.getEstadoSolicitudDetalle().substring(0,124))));
        theActorInTheSpotlight().should(seeThat(EstadoPagoQuestions.NumeroSolicitudDetalle(), equalTo(LoginStepDefinitions.pagosServiciosData.getNroSolicitudProcess())));
        theActorInTheSpotlight().should(seeThat(EstadoPagoQuestions.AutorizadorSolicitudDetalle(), containsString(LoginStepDefinitions.pagosServiciosData.getNombreusuario() + " - " + fechaHoraAux[0].trim())));
        theActorInTheSpotlight().should(seeThat(EstadoPagoQuestions.CreaciónSolititudDetalle(), containsString(LoginStepDefinitions.pagosServiciosData.getNombreusuario() + " - " + fechaHoraAux[0].trim())));
        String descripcion = LoginStepDefinitions.pagosServiciosData.getDescripcion();

        if (descripcion.length() > 1) {
            theActorInTheSpotlight().should(seeThat(EstadoPagoQuestions.DetalleDescripcion(), equalTo(LoginStepDefinitions.pagosServiciosData.getDescripcion())));
        }

        theActorInTheSpotlight().should(seeThat(EstadoPagoQuestions.MontoDetalle(), equalTo(LoginStepDefinitions.pagosServiciosData.getMontoProcess())));
        theActorInTheSpotlight().should(seeThat(EstadoPagoQuestions.DatosCargoDetalle(), containsString(LoginStepDefinitions.pagosServiciosData.getDatosCargoDetalle())));
        theActorInTheSpotlight().should(seeThat(EstadoPagoQuestions.PagoProcesadasDetalle(), equalTo("0")));
        theActorInTheSpotlight().should(seeThat(EstadoPagoQuestions.PagoRechazadasDetalle(), equalTo(LoginStepDefinitions.pagosServiciosData.getCuotasPagar())));
        theActorInTheSpotlight().should(seeThat(EstadoPagoQuestions.DetalleModopagoCuotas(), equalTo(LoginStepDefinitions.pagosServiciosData.getModoPago())));
        String montoingresado = LoginStepDefinitions.pagosServiciosData.getSimbolo() + " " + LoginStepDefinitions.pagosServiciosData.getMontoIncial() + ".00";
        if (montoingresado.length() > 9) {
            montoingresado = montoingresado.substring(0, montoingresado.length() - 6) + "," + montoingresado.substring(montoingresado.length() - 6);
        }
        if (montoingresado.startsWith("S/")) {
            if (montoingresado.length() > 13) {
                montoingresado = montoingresado.substring(0, montoingresado.length() - 10) + "," + montoingresado.substring(montoingresado.length() - 10);
            }
        } else {
            if (montoingresado.length() > 13 && montoingresado.startsWith("S/")) {
                montoingresado = montoingresado.substring(0, montoingresado.length() - 10) + "," + montoingresado.substring(montoingresado.length() - 10);
            }
            if (montoingresado.length() >= 13 && montoingresado.startsWith("$")) {
                montoingresado = montoingresado.substring(0, montoingresado.length() - 10) + "," + montoingresado.substring(montoingresado.length() - 10);
            }
        }

        theActorInTheSpotlight().should(seeThat(EstadoPagoQuestions.DetalleMontoPagadoCuotas(), equalTo(montoingresado)));
        theActorInTheSpotlight().should(seeThat(EstadoPagoQuestions.DetalleEstadoCuotas(), equalTo("Rechazado")));
        if (LoginStepDefinitions.pagosServiciosData.getSimbolo().equals("$")) {
            theActorInTheSpotlight().should(seeThat(EstadoPagoQuestions.DetalleTotalDolaresCuotas(), equalTo(montoingresado)));
        } else {
            theActorInTheSpotlight().should(seeThat(EstadoPagoQuestions.DetalleTotalSolesCuotas(), equalTo(montoingresado)));
        }
        actor.attemptsTo(WaitUntil.the(LoginPage.REGRESAR_DETALLE_ESTADO, isVisible()).forNoMoreThan(150).seconds(), Click.on(LoginPage.REGRESAR_DETALLE_ESTADO));


    }
    public static Performable withData(String dniUsuario, String cuentaOrigen, String tipoCuenta) {
        return instrumented(DetalleEstadoPagoRechazado.class, dniUsuario, cuentaOrigen, tipoCuenta);
    }
}
