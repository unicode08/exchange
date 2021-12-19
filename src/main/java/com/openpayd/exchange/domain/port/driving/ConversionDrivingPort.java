package com.openpayd.exchange.domain.port.driving;

import com.openpayd.exchange.domain.data.ConvertAmountInputDTO;
import com.openpayd.exchange.domain.data.ConvertAmountOutputDTO;
import com.openpayd.exchange.domain.data.ConvertAmountTransactionDTO;
import com.openpayd.exchange.domain.exception.ConvertAmountException;
import com.openpayd.exchange.domain.model.convertamount.ConvertAmount;
import com.openpayd.exchange.domain.model.convertamount.impl.ConvertAmountImpl;
import com.openpayd.exchange.domain.model.convertamount.impl.GetConvertedAmountInfoImpl;
import com.openpayd.exchange.domain.port.driven.ConvertAmountDataPort;
import com.openpayd.exchange.domain.port.driven.ExchangeRateDrivenPort;

import java.util.List;

public class ConversionDrivingPort {


    private ConvertAmountDataPort convertAmountDataPort;
    private ExchangeRateDrivenPort exchangeRateDrivenPort;

    public ConversionDrivingPort(ConvertAmountDataPort convertAmountDataPort, ExchangeRateDrivenPort exchangeRateDrivenPort) {
        this.convertAmountDataPort = convertAmountDataPort;
        this.exchangeRateDrivenPort = exchangeRateDrivenPort;

    }

    public ConvertAmountOutputDTO convertAmount(ConvertAmountInputDTO convertAmountInputDTO) throws ConvertAmountException {
        ConvertAmount convertAmount = new ConvertAmountImpl(convertAmountDataPort, exchangeRateDrivenPort);
        return convertAmount.perform(convertAmountInputDTO);
    }

    public List<ConvertAmountTransactionDTO> getConvertedAmountInfo(String transactionId, String transactionDate, Integer page, Integer pageSize) throws ConvertAmountException {
        ConvertAmount convertAmount = new GetConvertedAmountInfoImpl(convertAmountDataPort, exchangeRateDrivenPort);
        return convertAmount.perform(transactionId, transactionDate, page, pageSize);
    }
}
