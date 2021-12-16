package com.openpayd.exchange.domain.port.driven;

import com.openpayd.exchange.domain.data.ExchangeRatePortOutputDTO;

public interface ExchangeRateDrivenPort {
    ExchangeRatePortOutputDTO getExchangeRates(String date, String baseCurrency, String targetCurrency);
}
