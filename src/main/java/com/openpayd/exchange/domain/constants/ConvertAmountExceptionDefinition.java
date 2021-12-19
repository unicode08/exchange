package com.openpayd.exchange.domain.constants;

public interface ConvertAmountExceptionDefinition {
    String CONVERT_AMOUNT_INVALID_CURRENCY_INPUT = "Given both currency input is not valid please control input currencies.";
    String CONVERT_AMOUNT_INVALID_DATE_FORMAT = "Given date format is not valid, format must be suitable 'yyyy-MM-dd' format.";
    String CONVERT_AMOUNT_INVALID_FROM_CURRENCY_INPUT = "Given from currency input is not valid please control base currency input.";
    String CONVERT_AMOUNT_INVALID_TO_CURRENCY_INPUT = "Given to currency input is not valid please control target currency input.";
    String GET_CONVERT_AMOUNT_INVALID_DATE_ID_INPUT = "Id or Date input must given.";
    String CONVERT_AMOUNT_NULL_FROM_CURRENCY = "From currency cannot became null.";
    String CONVERT_AMOUNT_NULL_TO_CURRENCY = "To currency cannot became null.";
    String CONVERT_AMOUNT_INVALID_AMOUNT = "Amount field must bigger than zero.";
}
