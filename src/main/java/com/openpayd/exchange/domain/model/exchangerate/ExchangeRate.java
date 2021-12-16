package com.openpayd.exchange.domain.model.exchangerate;

import com.openpayd.exchange.domain.data.ExchangeRateInputDTO;
import com.openpayd.exchange.domain.data.ExchangeRateOutputDTO;
import com.openpayd.exchange.domain.data.ExchangeRatePortOutputDTO;
import com.openpayd.exchange.domain.port.driven.ExchangeRateDrivenPort;

import java.util.Map;

public class ExchangeRate {


    private final ExchangeRateDrivenPort exchangeRateDrivenPort;

    public ExchangeRate(ExchangeRateDrivenPort exchangeRateDrivenPort) {
        this.exchangeRateDrivenPort = exchangeRateDrivenPort;
    }


    public ExchangeRateOutputDTO getExchangeRate(ExchangeRateInputDTO exchangeRateInputDTO) {

        ExchangeRatePortOutputDTO exchangeRatePortOutputDTO = exchangeRateDrivenPort.getExchangeRates(exchangeRateInputDTO.getDate(), exchangeRateInputDTO.getBaseCurrency(), exchangeRateInputDTO.getTargetCurrency());

        ExchangeRateOutputDTO exchangeRateOutputDTO = new ExchangeRateOutputDTO();
        exchangeRateOutputDTO.setBaseCurrency(exchangeRateInputDTO.getBaseCurrency());
        exchangeRateOutputDTO.setTargetCurrency(exchangeRateInputDTO.getTargetCurrency());
        exchangeRateOutputDTO.setDate(exchangeRateInputDTO.getDate());
        exchangeRateOutputDTO.setRate(calculateParity(exchangeRateInputDTO.getBaseCurrency(), exchangeRateInputDTO.getTargetCurrency(), exchangeRatePortOutputDTO.getBaseTargetCurrencyPair()));


        return exchangeRateOutputDTO;
    }

    private double calculateParity(String baseCurrency, String targetCurrency, Map<String, Double> baseTargetCurrencyPair) {
        Double baseCurrencyReferenceRate = baseTargetCurrencyPair.get(baseCurrency);
        Double targetCurrencyReferenceRate = baseTargetCurrencyPair.get(targetCurrency);
        return baseCurrencyReferenceRate / targetCurrencyReferenceRate;

    }


}
