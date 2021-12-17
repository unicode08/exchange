package com.openpayd.exchange.integration.rest.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConvertAmountResponse {
    private String transactionId;
    private Double amount;

}
