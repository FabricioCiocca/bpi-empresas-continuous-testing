package com.everis.tasks.apis;

import com.everis.bpi.utils.data.EndpointServicioWeb;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

public class MuestraTipoCambio implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Get.resource(EndpointServicioWeb.PAYMENT_ORDER.getPath()).with(
                        requestSpecification -> requestSpecification
                                .relaxedHTTPSValidation()
                                .contentType("application/json")
                                .header("x-requested-with", "XMLHttpRequest")
                                .header("X-INT-Consumer-Id", "BIE")
                                .header("X-INT-Country-Id", "PE")
                                .header("X-INT-Device-Id", "-w9d2p")
                                .header("X-INT-Message-Id", "8b5844b156831800")
                                .header("X-INT-Net-Id", "BI")
                                .header("X-INT-Server-Id", "10.244.5.9")
                                .header("X-INT-Service-Id", "BIE")
                                .header("X-INT-Supervisor-Id", "BIEB0000")
                                .header("X-INT-Timestamp", "2022-11-17T17:26:49.800322")
                                .header("X-INT-User-Id", "BIEB0000")
                                .header("Authorization", "Basic dUJzZUJpZUFDb246SWJrdWJzYWNvbjE3")
                                .header("x-ibm-client-id", "9a7fe400-0ec5-44d9-bbb6-c8a84dfd468e")
                                .header("X-INT-Branch-Id", "898")
                                .param("officeCode", "100")
                                .param("typePrice", "13")
                                .param("channelCode", "TR")));
    }
}
