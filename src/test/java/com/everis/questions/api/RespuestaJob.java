package com.everis.questions.api;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Question;

public class RespuestaJob {
    public static Question<String> dataValida(){
        return Question.about("Respuesta data del Job")
                .answeredBy(actor -> SerenityRest.lastResponse().jsonPath().getJsonObject("data").toString());
    }
}
