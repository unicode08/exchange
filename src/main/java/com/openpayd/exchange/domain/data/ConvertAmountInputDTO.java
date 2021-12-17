package com.openpayd.exchange.domain.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConvertAmountInputDTO {
    private String fromCurrency;
    private String toCurrency;
    private Double conversionAmount;
}
