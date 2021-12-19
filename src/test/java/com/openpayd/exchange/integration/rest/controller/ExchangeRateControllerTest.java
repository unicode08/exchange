package com.openpayd.exchange.integration.rest.controller;

import com.openpayd.exchange.domain.constants.ExchangeRateExceptionDefinition;
import com.openpayd.exchange.domain.data.ExchangeRatePortOutputDTO;
import com.openpayd.exchange.integration.AbstractIT;
import com.openpayd.exchange.integration.rest.response.ExceptionResponse;
import com.openpayd.exchange.integration.rest.response.ExchangeRateResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ExchangeRateControllerTest extends AbstractIT {

    @Test
    public void whenValidInputGiven_getExchangeRate_thenReturnNoException() {


        ExchangeRatePortOutputDTO exchangeRatePortOutputDTO = new ExchangeRatePortOutputDTO();
        Map<String, Double> currencyRateMap = new HashMap<String, Double>();
        currencyRateMap.put("USD", 1.133793);
        currencyRateMap.put("EUR", 1D);
        exchangeRatePortOutputDTO.setBaseTargetCurrencyPair(currencyRateMap);
        exchangeRatePortOutputDTO.setDate("DEF");
        Mockito.when(exchangeRateRestAdapter.getExchangeRates(null, "EUR", "USD")).thenReturn(exchangeRatePortOutputDTO);


        ResponseEntity<ExchangeRateResponse> exchangeRateResponseResponseEntity = testRestTemplate.getForEntity("/api/getexchangerate?base=EUR&target=USD", ExchangeRateResponse.class);


        assertAll(
                () -> assertEquals(HttpStatus.OK, exchangeRateResponseResponseEntity.getStatusCode()),
                () -> assertEquals(0.8819952142939672, exchangeRateResponseResponseEntity.getBody().getRate())
        );

    }


    @Test
    public void whenInValidInputGiven_getExchangeRateWithBadCurrenyInput_thenReturnHttpConflict() {

        ExchangeRatePortOutputDTO exchangeRatePortOutputDTO = new ExchangeRatePortOutputDTO();
        exchangeRatePortOutputDTO.setBaseTargetCurrencyPair(null);
        exchangeRatePortOutputDTO.setDate("DEF");
        Mockito.when(exchangeRateRestAdapter.getExchangeRates(null, "EURK", "USDK")).thenReturn(exchangeRatePortOutputDTO);

        ResponseEntity<ExceptionResponse> exchangeRateResponseResponseEntity = testRestTemplate.getForEntity("/api/getexchangerate?base=EURK&target=USDK", ExceptionResponse.class);


        assertAll(
                () -> assertEquals(HttpStatus.BAD_REQUEST, exchangeRateResponseResponseEntity.getStatusCode()),
                () -> assertEquals(ExchangeRateExceptionDefinition.EXCHANGE_RATE_INVALID_CURRENCY_INPUT, exchangeRateResponseResponseEntity.getBody().getExceptionMessage())
        );

    }

    @Test
    public void whenInValidInputGiven_getExchangeRate_thenReturnHttpConflict() {

        ExchangeRatePortOutputDTO exchangeRatePortOutputDTO = new ExchangeRatePortOutputDTO();
        Map<String, Double> currencyRateMap = new HashMap<String, Double>();
        currencyRateMap.put("EUR", 1D);
        exchangeRatePortOutputDTO.setBaseTargetCurrencyPair(currencyRateMap);
        exchangeRatePortOutputDTO.setDate("DEF");
        Mockito.when(exchangeRateRestAdapter.getExchangeRates(null, "EUR", "USDK")).thenReturn(exchangeRatePortOutputDTO);

        ResponseEntity<ExceptionResponse> exchangeRateResponseResponseEntity = testRestTemplate.getForEntity("/api/getexchangerate?base=EUR&target=USDK", ExceptionResponse.class);


        assertAll(
                () -> assertEquals(HttpStatus.BAD_REQUEST, exchangeRateResponseResponseEntity.getStatusCode()),
                () -> assertEquals(ExchangeRateExceptionDefinition.EXCHANGE_RATE_INVALID_TARGET_CURRENCY_INPUT, exchangeRateResponseResponseEntity.getBody().getExceptionMessage())
        );

    }
}
