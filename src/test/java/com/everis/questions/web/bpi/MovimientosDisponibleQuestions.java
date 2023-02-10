package com.everis.questions.web.bpi;

import com.everis.bpi.userinterface.web.bpi.LoginPage;
import net.serenitybdd.screenplay.Question;

public class MovimientosDisponibleQuestions {

    public static Question<String> fechaOperacion() {
        return actor -> LoginPage.MOVIMIENTOS_FECHA_OPERACION.resolveFor(actor).waitUntilVisible().getText();
    }

    public static Question<String> fechaProceso() {
        return actor -> LoginPage.MOVIMIENTOS_FECHA_PROCESO.resolveFor(actor).waitUntilVisible().getText();
    }

    public static Question<String> numeroOperacion() {
        return actor -> LoginPage.MOVIMIENTOS_NUMERO_OPERACION.resolveFor(actor).waitUntilVisible().getText();
    }

    public static Question<String> movimiento() {
        return actor -> LoginPage.MOVIMIENTOS_MOVIMIENTOS.resolveFor(actor).waitUntilVisible().getText();
    }

    public static Question<String> descripcion() {
        return actor -> LoginPage.MOVIMIENTOS_DESCRIPCION.resolveFor(actor).waitUntilVisible().getText();
    }

    public static Question<String> canal() {
        return actor -> LoginPage.MOVIMIENTOS_CANAL.resolveFor(actor).waitUntilVisible().getText();
    }

    public static Question<String> importe() {
        return actor -> LoginPage.MOVIMIENTOS_IMPORTE.resolveFor(actor).waitUntilVisible().getText();
    }

    public static Question<String> saldoContable() {
        return actor -> LoginPage.MOVIMIENTOS_SALDO_CONTABLE.resolveFor(actor).waitUntilVisible().getText();
    }

    public static Question<String> validaMonto(String fila) {
        return actor -> LoginPage.IMPORTE.called(fila).resolveFor(actor).waitUntilVisible().getText();
    }
}
