package com.openpayd.exchange.domain.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConvertAmountOutputDTO {
    private String transactionId;
    private Double amount;
}
