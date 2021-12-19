package com.openpayd.exchange.domain.constants;

public interface ExchangeRateExceptionDefinition {

     String EXCHANGE_RATE_INVALID_DATE_FORMAT = "Given exchange rate date format is not valid, format must be suitable 'yyyy-MM-dd' format!";
     String EXCHANGE_RATE_INVALID_CURRENCY_INPUT = "Given both currency input is not valid please control input currencies.";
     String EXCHANGE_RATE_INVALID_BASE_CURRENCY_INPUT = "Given base currency input is not valid please control base currency input.";
     String EXCHANGE_RATE_INVALID_TARGET_CURRENCY_INPUT = "Given target currency input is not valid please control target currency input.";
}
