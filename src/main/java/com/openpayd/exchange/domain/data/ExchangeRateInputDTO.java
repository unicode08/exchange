package com.openpayd.exchange.domain.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExchangeRateInputDTO {
    private String baseCurrency;
    private String targetCurrency;
    private String date;
}
