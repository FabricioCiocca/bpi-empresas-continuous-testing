package com.everis.tasks.apis;

import com.everis.utils.data.EndpointServicioWeb;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

public class GeneraJob implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Get.resource(EndpointServicioWeb.PAYMENT_ORDER1.getPath()).with(
                        requestSpecification -> requestSpecification
                                .relaxedHTTPSValidation()
                                .contentType("application/json")
                                .header("x-requested-with", "XMLHttpRequest")
                                .auth().basic("nexbi", "nexbiP@ssw0rd")));
    }
}
