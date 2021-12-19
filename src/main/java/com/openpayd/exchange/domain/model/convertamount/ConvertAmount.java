package com.openpayd.exchange.domain.model.convertamount;

import com.openpayd.exchange.domain.data.ConvertAmountInputDTO;
import com.openpayd.exchange.domain.data.ConvertAmountOutputDTO;
import com.openpayd.exchange.domain.data.ConvertAmountTransactionDTO;
import com.openpayd.exchange.domain.exception.ConvertAmountException;
import com.openpayd.exchange.domain.port.driven.ConvertAmountDataPort;
import com.openpayd.exchange.domain.port.driven.ExchangeRateDrivenPort;

import java.util.Date;
import java.util.List;

public abstract class ConvertAmount {
    protected final ConvertAmountDataPort convertAmountDataPort;
    protected final ExchangeRateDrivenPort exchangeRateDrivenPort;
    protected final Date transactionDate;

    public ConvertAmount(ConvertAmountDataPort convertAmountDataPort, ExchangeRateDrivenPort exchangeRateDrivenPort) {
        this.convertAmountDataPort = convertAmountDataPort;
        this.exchangeRateDrivenPort = exchangeRateDrivenPort;
        this.transactionDate = new Date();
    }


    public ConvertAmountOutputDTO perform(ConvertAmountInputDTO convertAmountInputDTO) throws ConvertAmountException {
        validateInput(convertAmountInputDTO);
        return performImpl(convertAmountInputDTO);
    }

    protected abstract ConvertAmountOutputDTO performImpl(ConvertAmountInputDTO convertAmountInputDTO) throws ConvertAmountException;

    protected abstract void validateInput(ConvertAmountInputDTO convertAmountInputDTO) throws ConvertAmountException ;


    public List<ConvertAmountTransactionDTO> perform(String transactionId, String transactionDate, Integer page, Integer pageSize) throws ConvertAmountException {
        validateInput(transactionId, transactionDate, page, pageSize);
        return performImpl(transactionId, transactionDate, page, pageSize);

    }

    protected abstract List<ConvertAmountTransactionDTO> performImpl(String transactionId, String transactionDate, Integer page, Integer pageSize) throws ConvertAmountException;


    protected abstract void validateInput(String transactionId, String transactionDate, Integer page, Integer pageSize) throws ConvertAmountException ;


}
