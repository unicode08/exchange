package com.openpayd.exchange.domain.port.driving;

import com.openpayd.exchange.domain.data.ExchangeRateInputDTO;
import com.openpayd.exchange.domain.data.ExchangeRateOutputDTO;
import com.openpayd.exchange.domain.model.exchangerate.ExchangeRate;
import com.openpayd.exchange.domain.port.driven.ExchangeRateDrivenPort;

public class ExchangeRateDirivingPort {
    private final ExchangeRateDrivenPort exchangeRateDrivenPort;
    private ExchangeRate exchangeRate;

    public ExchangeRateDirivingPort(ExchangeRateDrivenPort exchangeRateDrivenPort) {
        this.exchangeRateDrivenPort = exchangeRateDrivenPort;
        exchangeRate = new ExchangeRate(exchangeRateDrivenPort);
    }

    public ExchangeRateOutputDTO getExchangeRate(ExchangeRateInputDTO exchangeRateInputDTO) {
        return exchangeRate.getExchangeRate(exchangeRateInputDTO);
    }
}
