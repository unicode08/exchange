package com.openpayd.exchange.integration.rest.controller;

import com.openpayd.exchange.domain.data.ExchangeRateInputDTO;
import com.openpayd.exchange.domain.data.ExchangeRateOutputDTO;
import com.openpayd.exchange.domain.port.driving.ExchangeRateDirivingPort;
import com.openpayd.exchange.integration.adapter.rest.exchangerate.ExchangeRateRestAdapter;
import com.openpayd.exchange.integration.common.mapper.ExchangeRateResponseMapper;
import com.openpayd.exchange.integration.rest.response.ExchangeRateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExchangeRateController {

    @Autowired
    private ExchangeRateRestAdapter exchangeRateRestAdapter;

    @GetMapping("/api/getexchangerate")
    public ExchangeRateResponse getExchangeRate(@RequestParam(name = "base") String baseCurrency, @RequestParam(name = "target") String targetCurrency, @RequestParam(name = "date") String date) {
        ExchangeRateDirivingPort exchangeRateDirivingPort = new ExchangeRateDirivingPort(exchangeRateRestAdapter);
        ExchangeRateInputDTO exchangeRateInputDTO = new ExchangeRateInputDTO();
        exchangeRateInputDTO.setBaseCurrency(baseCurrency);
        exchangeRateInputDTO.setTargetCurrency(targetCurrency);
        exchangeRateInputDTO.setDate(date);
        ExchangeRateOutputDTO exchangeRateOutputDTO = exchangeRateDirivingPort.getExchangeRate(exchangeRateInputDTO);
        return ExchangeRateResponseMapper.exchangeRateResponseMapper.convertDomainModelToResponse(exchangeRateOutputDTO);
    }
}
