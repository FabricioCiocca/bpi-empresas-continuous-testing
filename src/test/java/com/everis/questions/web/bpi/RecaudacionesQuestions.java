package com.everis.questions.web.bpi;

import com.everis.userinterfaces.web.bpi.LoginPage;
import net.serenitybdd.screenplay.Question;

public class RecaudacionesQuestions {

    public static Question<String> empresaRecaudacion() {
        return actor -> LoginPage.EMPRESA_RECAUDACION.resolveFor(actor).waitUntilVisible().getText();
    }
    public static Question<String> monto() {
        return actor -> LoginPage.MONTO_RECAUDACION.resolveFor(actor).waitUntilVisible().getText();
    }
    public static Question<String> numerooperacion() {
        return actor -> LoginPage.NUMERO_OPERACION_RECAUDACIONES.resolveFor(actor).waitUntilVisible().getText();
    }  public static Question<String> fecha() {
        return actor -> LoginPage.FECHA_RECAUDACION.resolveFor(actor).waitUntilVisible().getText();
    }  public static Question<String> servicio() {
        return actor -> LoginPage.SERVICIO_RECAUDACION.resolveFor(actor).waitUntilVisible().getText();
    }  public static Question<String> codigo() {
        return actor -> LoginPage.CODIGO_RECAUDACION.resolveFor(actor).waitUntilVisible().getText();
    }




}
