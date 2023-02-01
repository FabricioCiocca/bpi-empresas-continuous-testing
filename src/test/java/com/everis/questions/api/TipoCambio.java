package com.everis.questions.api;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Question;

public class TipoCambio {
    public static Question<String> Dolares(){

        return Question.about("Tipo de Cambio")
                .answeredBy(actor -> SerenityRest.lastResponse().jsonPath().getJsonObject("searchClass.purchasePrice"));
    }
}
