package com.openpayd.exchange.integration.rest.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConvertAmountRequest {
    private String fromCurrency;
    private String toCurrency;
    private Double conversionAmount;
}
