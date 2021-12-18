package com.openpayd.exchange.domain.port.driving;

import com.openpayd.exchange.AbstractUT;
import com.openpayd.exchange.domain.adapter.ConvertAmountDataPortFakeAdapter;
import com.openpayd.exchange.domain.adapter.ExchangeRateDrivenPortFakeAdapter;
import com.openpayd.exchange.domain.data.ConvertAmountInputDTO;
import com.openpayd.exchange.domain.data.ConvertAmountOutputDTO;
import com.openpayd.exchange.domain.data.ExchangeRatePortOutputDTO;
import com.openpayd.exchange.domain.port.driven.ConvertAmountDataPort;
import com.openpayd.exchange.domain.port.driven.ExchangeRateDrivenPort;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class ConversionDrivingPortTest extends AbstractUT {

    ConversionDrivingPort conversionDrivingPort;
    ExchangeRateDrivenPort exchangeRateDrivenPort;
    ExchangeRatePortOutputDTO exchangeRatePortOutputDTO;

    @BeforeAll
    public void setUp()
    {
        ConvertAmountDataPort convertAmountDataPort = new ConvertAmountDataPortFakeAdapter();
        exchangeRatePortOutputDTO = new ExchangeRatePortOutputDTO();
        Map<String, Double> currencyRateMap = new HashMap<String, Double>();
        currencyRateMap.put("USD", 1.133793);
        currencyRateMap.put("TRY", 17.760031);
        exchangeRatePortOutputDTO.setBaseTargetCurrencyPair(currencyRateMap);
        exchangeRatePortOutputDTO.setDate("2021-16-12");
        exchangeRateDrivenPort = new ExchangeRateDrivenPortFakeAdapter(exchangeRatePortOutputDTO);
        conversionDrivingPort = new ConversionDrivingPort(convertAmountDataPort,exchangeRateDrivenPort);
    }


    @Test
    public void whenValidInputGiven_convertAmount_thenReturnNoException() {
        ConvertAmountInputDTO convertAmountInputDTO = new ConvertAmountInputDTO();
        convertAmountInputDTO.setFromCurrency("USD");
        convertAmountInputDTO.setToCurrency("TRY");
        convertAmountInputDTO.setConversionAmount(10D);
        ConvertAmountOutputDTO convertAmountOutputDTO = conversionDrivingPort.convertAmount(convertAmountInputDTO);
        assertEquals(156.64262347712503, convertAmountOutputDTO.getConvertedAmount());
    }

}