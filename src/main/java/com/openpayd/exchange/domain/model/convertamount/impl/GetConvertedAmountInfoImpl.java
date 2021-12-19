package com.openpayd.exchange.domain.model.convertamount.impl;

import com.openpayd.exchange.domain.constants.ConvertAmountExceptionDefinition;
import com.openpayd.exchange.domain.data.ConvertAmountInputDTO;
import com.openpayd.exchange.domain.data.ConvertAmountOutputDTO;
import com.openpayd.exchange.domain.data.ConvertAmountTransactionDTO;
import com.openpayd.exchange.domain.exception.ConvertAmountException;
import com.openpayd.exchange.domain.model.convertamount.ConvertAmount;
import com.openpayd.exchange.domain.port.driven.ConvertAmountDataPort;
import com.openpayd.exchange.domain.port.driven.ExchangeRateDrivenPort;
import com.openpayd.exchange.domain.util.DateUtil;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class GetConvertedAmountInfoImpl extends ConvertAmount {

    public GetConvertedAmountInfoImpl(ConvertAmountDataPort convertAmountDataPort, ExchangeRateDrivenPort exchangeRateDrivenPort) {
        super(convertAmountDataPort, exchangeRateDrivenPort);
    }


    @Override
    protected List<ConvertAmountTransactionDTO> performImpl(String transactionId, String transactionDate, Integer page, Integer pageSize) throws ConvertAmountException {
        Date transaction = null;

        if (StringUtils.hasText(transactionDate)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                transaction = DateUtil.truncate(sdf.parse(transactionDate));
            } catch (ParseException e) {
                throw new ConvertAmountException(ConvertAmountExceptionDefinition.CONVERT_AMOUNT_INVALID_DATE_FORMAT);
            }
        }

        if (page == null || page < 1) {
            page = 1;
        }

        if (pageSize == null || pageSize < 1) {
            pageSize = 10;
        }

        return convertAmountDataPort.getConvertedAmountTransactionDTOListByTransactionIdOrTransactionDate(transactionId, transaction, page, pageSize);
    }

    @Override
    protected void validateInput(String transactionId, String transactionDate, Integer page, Integer pageSize) throws ConvertAmountException {
        if (!StringUtils.hasText(transactionId) && !StringUtils.hasText(transactionDate)) {
            throw new ConvertAmountException(ConvertAmountExceptionDefinition.GET_CONVERT_AMOUNT_INVALID_DATE_ID_INPUT);
        }

    }


    @Override
    protected ConvertAmountOutputDTO performImpl(ConvertAmountInputDTO convertAmountInputDTO) throws ConvertAmountException {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void validateInput(ConvertAmountInputDTO convertAmountInputDTO) {
        throw new UnsupportedOperationException();
    }

}
