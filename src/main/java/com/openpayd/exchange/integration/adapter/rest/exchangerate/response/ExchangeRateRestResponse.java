package com.openpayd.exchange.integration.adapter.rest.exchangerate.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ExchangeRateRestResponse {
    private boolean success;
    private int timestamp;
    private String base;
    private String date;
    private Map<String, Double> rates;
}
