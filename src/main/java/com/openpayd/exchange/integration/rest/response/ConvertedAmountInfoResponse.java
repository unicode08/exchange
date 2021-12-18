package com.openpayd.exchange.integration.rest.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ConvertedAmountInfoResponse {
    List<ConvertAmountResponse> convertAmountResponseList;
}
