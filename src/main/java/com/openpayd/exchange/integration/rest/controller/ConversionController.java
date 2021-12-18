package com.openpayd.exchange.integration.rest.controller;

import com.openpayd.exchange.domain.data.ConvertAmountInputDTO;
import com.openpayd.exchange.domain.port.driving.ConversionDrivingPort;
import com.openpayd.exchange.integration.adapter.jpa.conversion.ConvertAmountDataAdapter;
import com.openpayd.exchange.integration.adapter.rest.exchangerate.ExchangeRateRestAdapter;
import com.openpayd.exchange.integration.common.mapper.ConvertAmountRequestMapper;
import com.openpayd.exchange.integration.common.mapper.ConvertAmountResponseMapper;
import com.openpayd.exchange.integration.rest.request.ConvertAmountRequest;
import com.openpayd.exchange.integration.rest.response.ConvertAmountResponse;
import com.openpayd.exchange.integration.rest.response.ConvertedAmountInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/api/getconvertedamountinfo")
    public ConvertedAmountInfoResponse getConvertedAmountInfo(@RequestParam(name = "id", required = false) String transactionId, @RequestParam(name = "date" ,required = false) String transactionDate, @RequestParam(name = "page", required = false) Integer page, @RequestParam(name = "pageSize", required = false) Integer pageSize) {
        ConversionDrivingPort conversionDrivingPort = new ConversionDrivingPort(convertAmountDataAdapter, exchangeRateRestAdapter);
        ConvertedAmountInfoResponse convertedAmountInfoResponse = new ConvertedAmountInfoResponse();
        convertedAmountInfoResponse.setConvertAmountResponseList(ConvertAmountResponseMapper.convertAmountResponseMapper.convertDomainModelToResponseList(conversionDrivingPort.getConvertedAmountInfo(transactionId, transactionDate, page, pageSize)));
        return convertedAmountInfoResponse;

    }


}
