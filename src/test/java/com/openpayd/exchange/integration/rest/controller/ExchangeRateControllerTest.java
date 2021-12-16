package com.openpayd.exchange.integration.rest.controller;

import com.openpayd.exchange.integration.AbstractIT;
import com.openpayd.exchange.integration.rest.response.ExchangeRateResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ExchangeRateControllerTest extends AbstractIT {

    @Test
    public void whenValidInputGiven_getExchangeRate_thenReturnNoException() {


        ResponseEntity<ExchangeRateResponse> exchangeRateResponseResponseEntity = testRestTemplate.getForEntity("/api/getexchangerate?base=TRY&target=USD&date=2021-12-14", ExchangeRateResponse.class);


        assertAll(
                () -> assertEquals(HttpStatus.OK, exchangeRateResponseResponseEntity.getStatusCode()),
                () -> assertEquals(14.377494332677179, exchangeRateResponseResponseEntity.getBody().getRate())
        );

    }
}
