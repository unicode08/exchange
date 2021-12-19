package com.openpayd.exchange.domain.model.exchangerate;

import com.openpayd.exchange.domain.constants.ExchangeRateExceptionDefinition;
import com.openpayd.exchange.domain.data.ExchangeRateInputDTO;
import com.openpayd.exchange.domain.data.ExchangeRateOutputDTO;
import com.openpayd.exchange.domain.data.ExchangeRatePortOutputDTO;
import com.openpayd.exchange.domain.exception.ExchangeRateException;
import com.openpayd.exchange.domain.port.driven.ExchangeRateDrivenPort;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ExchangeRate {


    private final ExchangeRateDrivenPort exchangeRateDrivenPort;

    public ExchangeRate(ExchangeRateDrivenPort exchangeRateDrivenPort) {
        this.exchangeRateDrivenPort = exchangeRateDrivenPort;
    }


    public ExchangeRateOutputDTO getExchangeRate(ExchangeRateInputDTO exchangeRateInputDTO) throws ExchangeRateException {

        validateInput(exchangeRateInputDTO);
        ExchangeRatePortOutputDTO exchangeRatePortOutputDTO = exchangeRateDrivenPort.getExchangeRates(exchangeRateInputDTO.getDate(), exchangeRateInputDTO.getBaseCurrency(), exchangeRateInputDTO.getTargetCurrency());

        ExchangeRateOutputDTO exchangeRateOutputDTO = new ExchangeRateOutputDTO();
        exchangeRateOutputDTO.setBaseCurrency(exchangeRateInputDTO.getBaseCurrency());
        exchangeRateOutputDTO.setTargetCurrency(exchangeRateInputDTO.getTargetCurrency());
        exchangeRateOutputDTO.setDate(exchangeRateInputDTO.getDate());
        exchangeRateOutputDTO.setRate(calculateParity(exchangeRateInputDTO.getBaseCurrency(), exchangeRateInputDTO.getTargetCurrency(), exchangeRatePortOutputDTO));


        return exchangeRateOutputDTO;
    }

    private void validateInput(ExchangeRateInputDTO exchangeRateInputDTO) throws ExchangeRateException {
        if (StringUtils.hasText(exchangeRateInputDTO.getDate())) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                sdf.setLenient(false);
                sdf.parse(exchangeRateInputDTO.getDate());
            } catch (ParseException ex) {
                throw new ExchangeRateException(ExchangeRateExceptionDefinition.EXCHANGE_RATE_INVALID_DATE_FORMAT);
            }
        }
    }

    private double calculateParity(String baseCurrency, String targetCurrency, ExchangeRatePortOutputDTO exchangeRatePortOutputDTO) throws ExchangeRateException {
        if (exchangeRatePortOutputDTO == null || exchangeRatePortOutputDTO.getBaseTargetCurrencyPair() == null) {
            throw new ExchangeRateException(ExchangeRateExceptionDefinition.EXCHANGE_RATE_INVALID_CURRENCY_INPUT);
        }

        if (!exchangeRatePortOutputDTO.getBaseTargetCurrencyPair().containsKey(baseCurrency)) {
            throw new ExchangeRateException(ExchangeRateExceptionDefinition.EXCHANGE_RATE_INVALID_BASE_CURRENCY_INPUT);
        }

        if (!exchangeRatePortOutputDTO.getBaseTargetCurrencyPair().containsKey(targetCurrency)) {
            throw new ExchangeRateException(ExchangeRateExceptionDefinition.EXCHANGE_RATE_INVALID_TARGET_CURRENCY_INPUT);
        }

        Double baseCurrencyReferenceRate = exchangeRatePortOutputDTO.getBaseTargetCurrencyPair().get(baseCurrency);
        Double targetCurrencyReferenceRate = exchangeRatePortOutputDTO.getBaseTargetCurrencyPair().get(targetCurrency);
        return baseCurrencyReferenceRate / targetCurrencyReferenceRate;

    }


}
