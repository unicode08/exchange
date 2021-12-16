package com.openpayd.exchange.integration.rest.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExchangeRateResponse {
    private String baseCurrency;
    private String targetCurrency;
    private String date;
    private double rate;
}
