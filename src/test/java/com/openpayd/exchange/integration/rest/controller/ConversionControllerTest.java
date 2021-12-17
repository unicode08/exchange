package com.openpayd.exchange.integration.rest.controller;
import com.google.common.collect.Maps;

import com.openpayd.exchange.domain.data.ExchangeRatePortOutputDTO;
import com.openpayd.exchange.integration.AbstractIT;
import com.openpayd.exchange.integration.adapter.rest.exchangerate.ExchangeRateRestAdapter;
import com.openpayd.exchange.integration.rest.request.ConvertAmountRequest;
import com.openpayd.exchange.integration.rest.response.ConvertAmountResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

class ConversionControllerTest extends AbstractIT {


    @MockBean
    ExchangeRateRestAdapter exchangeRateRestAdapter;

    @Test
    public void whenValidInputGiven_convertAmount_thenReturnNoException() {

        ExchangeRatePortOutputDTO exchangeRatePortOutputDTO = new ExchangeRatePortOutputDTO();
        Map<String, Double> currencyRateMap = new HashMap<String, Double>();
        currencyRateMap.put("USD", 1.133793);
        currencyRateMap.put("EUR", 1D);
        exchangeRatePortOutputDTO.setBaseTargetCurrencyPair(currencyRateMap);
        exchangeRatePortOutputDTO.setDate("DEF");

        Mockito.when(exchangeRateRestAdapter.getExchangeRates(null,"EUR","USD")).thenReturn(exchangeRatePortOutputDTO);
        ConvertAmountRequest convertAmountRequest = new ConvertAmountRequest();
        convertAmountRequest.setFromCurrency("EUR");
        convertAmountRequest.setToCurrency("USD");
        convertAmountRequest.setConversionAmount(10D);

        ResponseEntity<ConvertAmountResponse> convertAmountResponseResponseEntity = testRestTemplate.postForEntity("/api/convertamount", convertAmountRequest, ConvertAmountResponse.class);

        assertAll(
                () -> assertEquals(HttpStatus.OK, convertAmountResponseResponseEntity.getStatusCode()),
                () -> assertEquals(11.33793, convertAmountResponseResponseEntity.getBody().getAmount())
        );
    }

}