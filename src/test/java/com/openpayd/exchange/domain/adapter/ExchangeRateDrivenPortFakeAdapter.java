package com.openpayd.exchange.domain.adapter;

import com.openpayd.exchange.domain.data.ExchangeRatePortOutputDTO;
import com.openpayd.exchange.domain.port.driven.ExchangeRateDrivenPort;

public class ExchangeRateDrivenPortFakeAdapter implements ExchangeRateDrivenPort {

    ExchangeRatePortOutputDTO exchangeRatePortOutputDTO;

    public ExchangeRateDrivenPortFakeAdapter(ExchangeRatePortOutputDTO exchangeRatePortOutputDTO) {
        this.exchangeRatePortOutputDTO = exchangeRatePortOutputDTO;
    }


    @Override
    public ExchangeRatePortOutputDTO getExchangeRates(String date, String baseCurrency, String targetCurrency) {
        return exchangeRatePortOutputDTO;
    }
}
