package com.openpayd.exchange.integration.adapter.rest.exchangerate;

import com.openpayd.exchange.domain.data.ExchangeRatePortOutputDTO;
import com.openpayd.exchange.domain.port.driven.ExchangeRateDrivenPort;
import com.openpayd.exchange.integration.adapter.rest.exchangerate.response.ExchangeRateRestResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ExchangeRateRestAdapter implements ExchangeRateDrivenPort {


    @Value("${url.exchangerate}")
    String baseUrl;

    @Value("${url.accesskey}")
    String accessKey;


    public ExchangeRatePortOutputDTO getExchangeRates(String date, String baseCurrency, String targetCurrency) {


        ExchangeRatePortOutputDTO exchangeRatePortOutputDTO = new ExchangeRatePortOutputDTO();


        RestTemplate restTemplate = new RestTemplate();
        String resourceExcahngeRateUrl = baseUrl + "/" + date + "?" + accessKey + "&symbols=" + baseCurrency + "," + targetCurrency;

        ResponseEntity<ExchangeRateRestResponse> exchangeRateRestResponseEntity = restTemplate.getForEntity(resourceExcahngeRateUrl, ExchangeRateRestResponse.class);

        exchangeRatePortOutputDTO.setBaseTargetCurrencyPair(exchangeRateRestResponseEntity.getBody().getRates());
        exchangeRatePortOutputDTO.setDate(date);

        return exchangeRatePortOutputDTO;
    }
}
