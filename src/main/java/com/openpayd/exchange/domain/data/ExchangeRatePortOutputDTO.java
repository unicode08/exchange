package com.openpayd.exchange.domain.data;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ExchangeRatePortOutputDTO {
    private Map<String,Double> baseTargetCurrencyPair;
    private String date;
}
