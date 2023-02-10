package com.everis.tasks.web.bpi.pagosServicios.sindata;

import com.everis.bpi.questions.web.bpi.EstadoPagoQuestions;
import com.everis.bpi.stepsdefinitions.web.bpi.login.LoginStepDefinitions;
import com.everis.bpi.userinterface.web.bpi.PagoRealizadoPage;
import lombok.SneakyThrows;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.By;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class DetalleEstadoPagoSinDataFC implements Task {
    private final String dniUsuario;
    private final String tipoCuenta;
    private final String cuentaOrigen;

    public DetalleEstadoPagoSinDataFC(String dniUsuario, String tipoCuenta, String cuentaOrigen) {
        this.dniUsuario = dniUsuario;
        this.tipoCuenta = tipoCuenta;
        this.cuentaOrigen = cuentaOrigen;
    }

    @SneakyThrows
    @Override
    public <T extends Actor> void performAs(T actor) {
        Target DETALLE_NUMERO_SOLICITUD_PROCESS = Target.the("Numero de Solicitud").located(By.xpath("//ibk-card-description[@data-test='lblRequestNumberValue']"));
        LoginStepDefinitions.pagosServiciosData.setNroSolicitudProcess(String.valueOf(DETALLE_NUMERO_SOLICITUD_PROCESS.resolveFor(actor).getText()));
        Target FECHA_HORA_PROCESS = Target.the("Fecha y hora de envío").located(By.xpath("//ibk-card-description[@data-test='lblDateSendValue']"));
        LoginStepDefinitions.pagosServiciosData.setFechaHora(String.valueOf(FECHA_HORA_PROCESS.resolveFor(actor).getText()));
        Target MONTO_PAGO_PROCESS = Target.the("Monto").located(By.xpath("(//*[@data-test='lblAmountValue'])"));
        LoginStepDefinitions.pagosServiciosData.setMontoProcess(String.valueOf(MONTO_PAGO_PROCESS.resolveFor(actor).getText()));
        LoginStepDefinitions.pagosServiciosData.setNombreusuario(dniUsuario);
        LoginStepDefinitions.pagosServiciosData.setDatosCargoDetalle(tipoCuenta + " " + cuentaOrigen);
        String[] fechaHoraAux = LoginStepDefinitions.pagosServiciosData.getFechaHora().split("-"); //DIVIDIMOS HORA
        actor.attemptsTo(WaitUntil.the(PagoRealizadoPage.REVISA_DETALLE_ESTADO, isVisible()).forNoMoreThan(4).seconds(), Click.on(PagoRealizadoPage.REVISA_DETALLE_ESTADO));
        Target DETALLE_ESTADO_SOLICITUD = Target.the("Estado de Servicio - Detalle").located(By.xpath("(//div[@data-test='txtEstado'])"));
        LoginStepDefinitions.pagosServiciosData.setEstadoSolicitudDetalle(String.valueOf(DETALLE_ESTADO_SOLICITUD.resolveFor(actor).getText()));
        if (LoginStepDefinitions.pagosServiciosData.getEstadoSolicitudDetalle().equals("En proceso")) {
            theActorInTheSpotlight().attemptsTo(WaitUntil.the(PagoRealizadoPage.REGRESAR_DETALLE_ESTADO, isVisible()).forNoMoreThan(150).seconds(), Click.on(PagoRealizadoPage.REGRESAR_DETALLE_ESTADO));
            theActorInTheSpotlight().attemptsTo(WaitUntil.the(PagoRealizadoPage.REVISA_DETALLE_ESTADO, isVisible()).forNoMoreThan(150).seconds(), Click.on(PagoRealizadoPage.REVISA_DETALLE_ESTADO));
            Target DETALLE_ESTADO_SOLICITUD2 = Target.the("Estado de Servicio - Detalle").located(By.xpath("(//div[@data-test='txtEstado'])"));
            LoginStepDefinitions.pagosServiciosData.setEstadoSolicitudDetalle(String.valueOf(DETALLE_ESTADO_SOLICITUD2.resolveFor(actor).getText()));
            System.out.println("confirmacion - Estado 2: " + LoginStepDefinitions.pagosServiciosData.getEstadoSolicitudDetalle());
            theActorInTheSpotlight().should(seeThat(EstadoPagoQuestions.EstadoServicioDetallePendiente(), equalTo(LoginStepDefinitions.pagosServiciosData.getEstadoSolicitudDetalle())));
        } else {
            theActorInTheSpotlight().should(seeThat(EstadoPagoQuestions.EstadoServicioDetallePendiente(), equalTo(LoginStepDefinitions.pagosServiciosData.getEstadoSolicitudDetalle())));
        }
        theActorInTheSpotlight().should(seeThat(EstadoPagoQuestions.CreaciónSolititudDetalle(), containsString(LoginStepDefinitions.pagosServiciosData.getNombreusuario() + " - " + fechaHoraAux[0].trim())));
        theActorInTheSpotlight().should(seeThat(EstadoPagoQuestions.AutorizadorSolicitudDetalle(), containsString(LoginStepDefinitions.pagosServiciosData.getNombreusuario() + " - " + fechaHoraAux[0].trim())));
        theActorInTheSpotlight().should(seeThat(EstadoPagoQuestions.NumeroSolicitudDetalle(), equalTo(LoginStepDefinitions.pagosServiciosData.getNroSolicitudProcess())));
        if (!LoginStepDefinitions.pagosServiciosData.getDescripcion().equals("")) {
            System.out.println("entre por que la descripcion esta llena");
            theActorInTheSpotlight().should(seeThat(EstadoPagoQuestions.DetalleDescripcion(), equalTo(LoginStepDefinitions.pagosServiciosData.getDescripcion())));
        }
        theActorInTheSpotlight().should(seeThat(EstadoPagoQuestions.DatosCargoDetalle(), containsString(LoginStepDefinitions.pagosServiciosData.getDatosCargoDetalle())));
        System.out.println("confirmacion - DATOS CARGO: " + LoginStepDefinitions.pagosServiciosData.getDatosCargoDetalle());
        theActorInTheSpotlight().should(seeThat(EstadoPagoQuestions.MontoDetalle(), equalTo(LoginStepDefinitions.pagosServiciosData.getMontoProcess())));
        System.out.println("MONTO: " + LoginStepDefinitions.pagosServiciosData.getMontoProcess());
        theActorInTheSpotlight().should(seeThat(EstadoPagoQuestions.PagoProcesadasDetalle(), equalTo("-")));
        System.out.println("PROCESADOS: " + LoginStepDefinitions.pagosServiciosData.getCuotasPagar());
        theActorInTheSpotlight().should(seeThat(EstadoPagoQuestions.PagoRechazadasDetalle(), equalTo("-")));
        int i = Integer.parseInt(LoginStepDefinitions.pagosServiciosData.getCantdni()) + 1;//30
        while (!LoginStepDefinitions.pagosServiciosData.getmontosPagados().equals(LoginStepDefinitions.pagosServiciosData.getmontoPagado())) {
            i--;
            Target MONTO_PAGADO = Target.the("Monto Pagado").located(By.xpath("(//*[@class='height-16 cursor-default hover-none ibk-table-row ng-star-inserted'])[" + i + "]//ibk-table-cell[7]"));
            LoginStepDefinitions.pagosServiciosData.setmontoPagado(String.valueOf(MONTO_PAGADO.resolveFor(actor).getText()));
        }
        Target MODO_DE_PAGO = Target.the("Modo de Pago").located(By.xpath("(//*[@class='height-16 cursor-default hover-none ibk-table-row ng-star-inserted'])[" + i + "]//ibk-table-cell[6]"));
        LoginStepDefinitions.pagosServiciosData.setmodoDePago(String.valueOf(MODO_DE_PAGO.resolveFor(actor).getText()));
        LoginStepDefinitions.pagosServiciosData.getmodoDePago().equals(LoginStepDefinitions.pagosServiciosData.getModoPago());
        Target ESTADO = Target.the("Estado").located(By.xpath("(//*[@class='height-16 cursor-default hover-none ibk-table-row ng-star-inserted'])[" + i + "]//ibk-table-cell[8]"));
        LoginStepDefinitions.pagosServiciosData.setEstado(String.valueOf(ESTADO.resolveFor(actor).getText()));
        LoginStepDefinitions.pagosServiciosData.getEstado().equals("Pendiente");
        if (LoginStepDefinitions.pagosServiciosData.getSimbolo().equals("S/")) {
            Target TOTAL_SOLES = Target.the("Total Soles").located(By.xpath("(//span[@data-test='lblTotalPenValue'])[" + i + "]"));
            LoginStepDefinitions.pagosServiciosData.setmontoPagado(String.valueOf(TOTAL_SOLES.resolveFor(actor).getText()));
            LoginStepDefinitions.pagosServiciosData.getmontoPagado().equals(LoginStepDefinitions.pagosServiciosData.getmontosPagados());
        } else if (LoginStepDefinitions.pagosServiciosData.getSimbolo().equals("$")) {
            Target TOTAL_DOLARES = Target.the("Total Dolares").located(By.xpath("(//span[@data-test='lblTotalDollarValue'])[" + i + "]"));
            LoginStepDefinitions.pagosServiciosData.setmontoPagado(String.valueOf(TOTAL_DOLARES.resolveFor(actor).getText()));
            LoginStepDefinitions.pagosServiciosData.getmontoPagado().equals(LoginStepDefinitions.pagosServiciosData.getmontosPagados());
        }
        actor.attemptsTo(WaitUntil.the(PagoRealizadoPage.REGRESAR_DETALLE_ESTADO, isVisible()).forNoMoreThan(150).seconds(), Click.on(PagoRealizadoPage.REGRESAR_DETALLE_ESTADO));
    }

    public static Performable withData(String dniUsuario, String cuentaOrigen, String tipoCuenta) {
        return instrumented(DetalleEstadoPagoSinDataFC.class, dniUsuario, cuentaOrigen, tipoCuenta);
    }
}
