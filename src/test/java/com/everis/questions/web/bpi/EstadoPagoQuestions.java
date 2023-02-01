package com.everis.questions.web.bpi;


import com.everis.userinterfaces.web.bpi.LoginPage;
import net.serenitybdd.screenplay.Question;

public class EstadoPagoQuestions {


    public static Question<String> EstadoServicioDetalleProcesada() {
        return actor -> "Procesada";
    }

    public static Question<String> NumeroSolicitudDetalle() {
        return actor -> LoginPage.DETALLE_NUMERO_SOLICITUD.resolveFor(actor).waitUntilVisible().getText();
    }

    public static Question<String> CreaciónSolititudDetalle() {
        return actor -> LoginPage.DETALLE_CREACION_SOLICITUD.resolveFor(actor).waitUntilVisible().getText();
    }

    public static Question<String> AutorizadorSolicitudDetalle() {
        return actor -> LoginPage.DETALLE_AUTORIZADOR_SOLICITUD.resolveFor(actor).waitUntilVisible().getText();
    }

    public static Question<String> MontoDetalle() {
        return actor -> LoginPage.DETALLE_MONTO_PAGO.resolveFor(actor).waitUntilVisible().getText();
    }

    public static Question<String> DatosCargoDetalle() {
        return actor -> LoginPage.DETALLE_DATOS_CARGO.resolveFor(actor).waitUntilVisible().getText();
    }

    public static Question<String> CuotasProcesadas() {
        return actor -> LoginPage.CUOTAS_PAGAR.resolveFor(actor).waitUntilVisible().getText();
    }

    public static Question<String> PagoProcesadasDetalle() {
        return actor -> LoginPage.DETALLE_PROCESADOS_PAGO.resolveFor(actor).waitUntilVisible().getText();
    }

    public static Question<String> PagoRechazadasDetalle() {
        return actor -> LoginPage.DETALLE_RECHAZADOS_PAGO.resolveFor(actor).waitUntilVisible().getText();
    }

    public static Question<String> DetalleDescripcion() {
        return actor -> LoginPage.DETALLE_DESCRIPCION.resolveFor(actor).waitUntilVisible().getText();
    }

    public static Question<String> DetalleModopagoCuotas() {
        return actor -> LoginPage.DETALLE_MODO_PAGO_CUOTAS.resolveFor(actor).waitUntilVisible().getText();
    }

    public static Question<String> DetalleMontoPagadoCuotas() {
        return actor -> LoginPage.DETALLE_MONTO_PAGADO_CUOTAS.resolveFor(actor).waitUntilVisible().getText();
    }
    public static Question<String> DetalleMontoPagadoCuotasPendiente() {
        return actor -> LoginPage.DETALLE_MONTO_PAGADO_CUOTAS_P.resolveFor(actor).waitUntilVisible().getText();//CRISTIAN
    }

    public static Question<String> DetalleEstadoCuotas() {
        return actor -> LoginPage.DETALLE_ESTADO_CUOTAS.resolveFor(actor).waitUntilVisible().getText();
    }

    public static Question<String> DetalleTotalSolesCuotas() {
        return actor -> LoginPage.DETALLE_TOTAL_SOLES_CUOTAS.resolveFor(actor).waitUntilVisible().getText();
    }
    public static Question<String> DetalleTotalDolaresCuotas() {
        return actor -> LoginPage.DETALLE_TOTAL_DOLARES_CUOTAS.resolveFor(actor).waitUntilVisible().getText();
    }

    public static Question<String> DetalleTotalDolaresCuotasPendiente() {
        return actor -> LoginPage.DETALLE_TOTAL_DOLARES_CUOTAS_P.resolveFor(actor).waitUntilVisible().getText();//CRISTIAN
    }
    public static Question<String> EstadoServicioDetallePendiente() {
        return actor -> "Pendiente";
    }
    public static Question<String> EstadoServicioDetalleRechazada() {
        return actor -> "Rechazada - La cuenta no tiene los fondos suficientes para realizar la operación. Por favor, verifica y vuelve a intentarlo.";
    }
    public static Question<String> AutorizadorSolicitudDetalle2() {
        return actor -> LoginPage.DETALLE_AUTORIZADOR_SOLICITUD2.resolveFor(actor).waitUntilVisible().getText();
    }
    public static Question<String> AutorizadorSolicitudDetalle3() {
        return actor -> LoginPage.DETALLE_AUTORIZADOR_SOLICITUD3.resolveFor(actor).waitUntilVisible().getText();
    }

}
