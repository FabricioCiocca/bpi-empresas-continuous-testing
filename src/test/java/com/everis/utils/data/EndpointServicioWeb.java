package com.everis.utils.data;

public enum EndpointServicioWeb {
    //GENERATE_TOKEN("/security/v1/oauth/token"),
    PAYMENT_ORDER("/api/currency/v1/10/reference-exchange?officeCode=100&typePrice=13&channelCode=TR"),
    //officeCode=100&typePrice=13&channelCode=TR
    PAYMENT_ORDER1("/api/external/fee-charge/service-level/execute");

    private final String path;

    EndpointServicioWeb(String path) {
        this.path = path;
    }

    public String getPath(){
        return path;
    }
}
