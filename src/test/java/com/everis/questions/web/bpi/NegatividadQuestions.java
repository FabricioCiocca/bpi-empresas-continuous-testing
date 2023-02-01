package com.everis.questions.web.bpi;

import com.everis.userinterfaces.web.bpi.LoginPage;
import net.serenitybdd.screenplay.Question;


public class NegatividadQuestions {

    public static Question<String> LimiteServicio() {
        return actor -> LoginPage.LIMITE_SERVICIO.resolveFor(actor).waitUntilVisible().getText();
    }

    public static Question<String> ServicioYaAgregado() {
        return actor -> LoginPage.SERVICIO_AGREGADO.resolveFor(actor).waitUntilVisible().getText();
    }
    public static Question<String> ParametrosSeguridad() {
        return actor -> LoginPage.PARAMETROS_SEGURIDAD.resolveFor(actor).waitUntilVisible().getText();
    }
    public static Question<String> NoSeEncontraronCuotas() {
        return actor -> LoginPage.NO_CUOTAS.resolveFor(actor).waitUntilVisible().getText();
    }

    public static Question<String> DebesIngresarUnMontoValido() {
        return actor -> LoginPage.MONTO_INVALIDO.resolveFor(actor).waitUntilVisible().getText();
    }
    public static Question<String> MontoSuperaLimite() {
        return actor -> LoginPage.MONTO_INVALIDO_SUPERADO.resolveFor(actor).waitUntilVisible().getText();
    }

    public static Question<String> DebesSelecciomarCuota() {
        return actor -> LoginPage.NINGUNA_CUOTA.resolveFor(actor).waitUntilVisible().getText();
    }

    public static Question<String> MontoIngresadoSuperaLimites() {
        return actor -> LoginPage.MONTO_SUPERA.resolveFor(actor).waitUntilVisible().getText();
    }
}
