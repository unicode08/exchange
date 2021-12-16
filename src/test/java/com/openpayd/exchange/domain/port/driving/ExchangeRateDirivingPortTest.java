package com.openpayd.exchange.domain.port.driving;

import com.openpayd.exchange.AbstractUT;
import com.openpayd.exchange.domain.adapter.ExchangeRateDrivenPortFakeAdapter;
import com.openpayd.exchange.domain.data.ExchangeRateInputDTO;
import com.openpayd.exchange.domain.data.ExchangeRateOutputDTO;
import com.openpayd.exchange.domain.data.ExchangeRatePortOutputDTO;
import com.openpayd.exchange.domain.port.driven.ExchangeRateDrivenPort;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class ExchangeRateDirivingPortTest extends AbstractUT {

    ExchangeRateDirivingPort exchangeRateDirivingPort;
    ExchangeRatePortOutputDTO exchangeRatePortOutputDTO;
    ExchangeRateDrivenPort exchangeRateDrivenPort;

    @BeforeAll
    public void setUp() {
        exchangeRatePortOutputDTO = new ExchangeRatePortOutputDTO();
        Map<String, Double> currencyRateMap = new HashMap<String, Double>();
        currencyRateMap.put("USD", 1.133793);
        currencyRateMap.put("TRY", 17.760031);
        exchangeRatePortOutputDTO.setBaseTargetCurrencyPair(currencyRateMap);
        exchangeRatePortOutputDTO.setDate("2021-16-12");
        exchangeRateDrivenPort = new ExchangeRateDrivenPortFakeAdapter(exchangeRatePortOutputDTO);
        exchangeRateDirivingPort = new ExchangeRateDirivingPort(exchangeRateDrivenPort);
    }

    @Test
    public void whenValidInputGiven_getExchangeRate_thenReturnNoException() {

        ExchangeRateInputDTO exchangeRateInputDTO = new ExchangeRateInputDTO();
        exchangeRateInputDTO.setBaseCurrency("TRY");
        exchangeRateInputDTO.setTargetCurrency("USD");
        exchangeRateInputDTO.setDate("2021-16-12");

        ExchangeRateOutputDTO exchangeRateOutputDTO = exchangeRateDirivingPort.getExchangeRate(exchangeRateInputDTO);
        assertAll(
                () -> assertEquals(15.664262347712501, exchangeRateOutputDTO.getRate())
        );
    }

}