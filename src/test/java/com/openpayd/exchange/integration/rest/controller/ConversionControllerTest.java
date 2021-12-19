package com.openpayd.exchange.integration.rest.controller;

import com.openpayd.exchange.domain.data.ExchangeRatePortOutputDTO;
import com.openpayd.exchange.integration.AbstractIT;
import com.openpayd.exchange.integration.rest.request.ConvertAmountRequest;
import com.openpayd.exchange.integration.rest.response.ConvertAmountResponse;
import com.openpayd.exchange.integration.rest.response.ConvertedAmountInfoResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

class ConversionControllerTest extends AbstractIT {


    @Test
    public void whenValidInputGiven_convertAmount_thenReturnNoException() {

        ExchangeRatePortOutputDTO exchangeRatePortOutputDTO = new ExchangeRatePortOutputDTO();
        Map<String, Double> currencyRateMap = new HashMap<String, Double>();
        currencyRateMap.put("USD", 1.133793);
        currencyRateMap.put("EUR", 1D);
        exchangeRatePortOutputDTO.setBaseTargetCurrencyPair(currencyRateMap);
        exchangeRatePortOutputDTO.setDate("DEF");
        Mockito.when(exchangeRateRestAdapter.getExchangeRates(null, "EUR", "USD")).thenReturn(exchangeRatePortOutputDTO);


        ConvertAmountRequest convertAmountRequest = new ConvertAmountRequest();
        convertAmountRequest.setFromCurrency("EUR");
        convertAmountRequest.setToCurrency("USD");
        convertAmountRequest.setConversionAmount(10D);

        ResponseEntity<ConvertAmountResponse> convertAmountResponseResponseEntity = testRestTemplate.postForEntity("/api/convertamount", convertAmountRequest, ConvertAmountResponse.class);

        assertAll(
                () -> assertEquals(HttpStatus.OK, convertAmountResponseResponseEntity.getStatusCode()),
                () -> assertEquals(11.33793, convertAmountResponseResponseEntity.getBody().getConvertedAmount())
        );
    }


    @Test
    public void whenValidInputGiven_getConvertedAmountInfoWithId_thenReturnNoException() {
        ResponseEntity<ConvertedAmountInfoResponse> convertedAmountInfoResponseResponseEntity = testRestTemplate.getForEntity("/api/getconvertedamountinfo?id=1&page=0&pageSize=10", ConvertedAmountInfoResponse.class);
        assertAll(
                () -> assertEquals(HttpStatus.OK, convertedAmountInfoResponseResponseEntity.getStatusCode()),
                () -> assertEquals("1", convertedAmountInfoResponseResponseEntity.getBody().getConvertAmountResponseList().get(0).getId())
        );

    }

    @Test
    public void whenValidInputGiven_getConvertedAmountInfoWithDate_thenReturnNoException() {
        ResponseEntity<ConvertedAmountInfoResponse> convertedAmountInfoResponseResponseEntity = testRestTemplate.getForEntity("/api/getconvertedamountinfo?date=2020-02-03&page=0&pageSize=10", ConvertedAmountInfoResponse.class);
        assertAll(
                () -> assertEquals(HttpStatus.OK, convertedAmountInfoResponseResponseEntity.getStatusCode()),
                () -> assertTrue(!convertedAmountInfoResponseResponseEntity.getBody().getConvertAmountResponseList().isEmpty())
        );

    }

}