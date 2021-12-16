package com.openpayd.exchange.domain.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExchangeRateOutputDTO {
    private String baseCurrency;
    private String targetCurrency;
    private String date;
    private double rate;
}
