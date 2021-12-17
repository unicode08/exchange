package com.openpayd.exchange.integration.rest.controller;

import com.openpayd.exchange.domain.data.ConvertAmountInputDTO;
import com.openpayd.exchange.domain.port.driving.ConversionDrivingPort;
import com.openpayd.exchange.integration.adapter.jpa.conversion.ConvertAmountDataAdapter;
import com.openpayd.exchange.integration.adapter.rest.exchangerate.ExchangeRateRestAdapter;
import com.openpayd.exchange.integration.common.mapper.ConvertAmountRequestMapper;
import com.openpayd.exchange.integration.common.mapper.ConvertAmountResponseMapper;
import com.openpayd.exchange.integration.rest.request.ConvertAmountRequest;
import com.openpayd.exchange.integration.rest.response.ConvertAmountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConversionController {

    @Autowired
    private ExchangeRateRestAdapter exchangeRateRestAdapter;

    @Autowired
    private ConvertAmountDataAdapter convertAmountDataAdapter;


    @PostMapping("/api/convertamount")
    public ConvertAmountResponse convertAmount(@RequestBody ConvertAmountRequest convertAmountRequest) {
        ConvertAmountInputDTO convertAmountInputDTO = ConvertAmountRequestMapper.convertAmountRequestMapper.convertRequestToDomainModel(convertAmountRequest);
        ConversionDrivingPort conversionDrivingPort = new ConversionDrivingPort(convertAmountDataAdapter, exchangeRateRestAdapter);
        return ConvertAmountResponseMapper.convertAmountResponseMapper.convertDomainModelToResponse(conversionDrivingPort.convertAmount(convertAmountInputDTO));
    }


}
