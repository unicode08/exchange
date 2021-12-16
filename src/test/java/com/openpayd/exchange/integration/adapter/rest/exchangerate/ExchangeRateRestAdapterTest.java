package com.openpayd.exchange.integration.adapter.rest.exchangerate;

import com.openpayd.exchange.domain.data.ExchangeRatePortOutputDTO;
import com.openpayd.exchange.integration.AbstractIT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class ExchangeRateRestAdapterTest extends AbstractIT {

    @Autowired
    ExchangeRateRestAdapter exchangeRateRestAdapter;

    @Test
    void whenValidInputGiven_getExchangeRate_thenReturnNoException() {

        String date = "2021-12-14";
        String baseCurrency = "USD";
        String targetCurrency = "TRY";
        ExchangeRatePortOutputDTO exchangeRatePortOutputDTO = exchangeRateRestAdapter.getExchangeRates(date, baseCurrency, targetCurrency);

        assertEquals(16.185493, exchangeRatePortOutputDTO.getBaseTargetCurrencyPair().get("TRY"));

    }
}