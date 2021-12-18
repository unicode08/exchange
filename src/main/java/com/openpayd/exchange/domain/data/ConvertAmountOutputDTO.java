package com.openpayd.exchange.domain.data;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ConvertAmountOutputDTO {
    private String id;
    private String fromCurrency;
    private String toCurrency;
    private Double conversionAmount;
    private Double convertedAmount;
    private Date transactionDate;
}
