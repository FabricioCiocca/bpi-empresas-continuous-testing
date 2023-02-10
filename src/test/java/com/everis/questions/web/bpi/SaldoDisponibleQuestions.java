package com.everis.questions.web.bpi;

import com.everis.bpi.userinterface.web.bpi.LoginPage;
import net.serenitybdd.screenplay.Question;

public class SaldoDisponibleQuestions {

    public static Question<String> saldoTipoCuenta() {
        return actor -> LoginPage.SALDO_TIPO_CUENTA.resolveFor(actor).waitUntilVisible().getText();
    }

    public static Question<String> saldoTipoMoneda() {
        return actor -> LoginPage.SALDO_TIPO_MONEDA.resolveFor(actor).waitUntilVisible().getText();
    }

    public static Question<String> saldoNumeroCuenta() {
        return actor -> LoginPage.SALDO_NUMERO_CUENTA.resolveFor(actor).waitUntilVisible().getText();
    }

    public static Question<String> cuentaOrigen() {
        return actor -> LoginPage.SALDO_DISPONIBLE.resolveFor(actor).waitUntilVisible().getText();
    }

    public static Question<String> saldoEmpresaTotal() {
        return actor -> LoginPage.SALDO_EMPRESA_TOTAL.resolveFor(actor).waitUntilVisible().getText();
    }

    public static Question<String> saldoContable() {
        return actor -> LoginPage.SALDO_CONTABLE.resolveFor(actor).waitUntilVisible().getText();
    }

    public static Question<String> saldoDisponibleLinea() {
        return actor -> LoginPage.SALDO_DISPONIBLE_LINEA.resolveFor(actor).waitUntilVisible().getText();
    }

    public static Question<String> saldoTotalFinal() {
        return actor -> LoginPage.SALDO_FINAL_TOTAL.resolveFor(actor).waitUntilVisible().getText();
    }

    public static Question<String> saldoTipoDeCuenta() {
        return actor -> LoginPage.SALDO_TIPO_DE_CUENTA.resolveFor(actor).waitUntilVisible().getText();
    }

    public static Question<String> saldoDisponibleDetalle() {
        return actor -> LoginPage.SALDO_SALDO_DISPONIBLE.resolveFor(actor).waitUntilVisible().getText();
    }


    public static Question<String> saldoTotalDolaresFinal() {
        return actor -> LoginPage.SALDO_DOLARES_FINAL_TOTAL.resolveFor(actor).waitUntilVisible().getText();
    }
}