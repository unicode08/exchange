package com.openpayd.exchange.domain.model.convertamount.impl;

import com.openpayd.exchange.domain.constants.ConvertAmountExceptionDefinition;
import com.openpayd.exchange.domain.data.ConvertAmountInputDTO;
import com.openpayd.exchange.domain.data.ConvertAmountOutputDTO;
import com.openpayd.exchange.domain.data.ConvertAmountTransactionDTO;
import com.openpayd.exchange.domain.data.ExchangeRatePortOutputDTO;
import com.openpayd.exchange.domain.exception.ConvertAmountException;
import com.openpayd.exchange.domain.model.convertamount.ConvertAmount;
import com.openpayd.exchange.domain.port.driven.ConvertAmountDataPort;
import com.openpayd.exchange.domain.port.driven.ExchangeRateDrivenPort;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

public class ConvertAmountImpl extends ConvertAmount {

    public ConvertAmountImpl(ConvertAmountDataPort convertAmountDataPort, ExchangeRateDrivenPort exchangeRateDrivenPort) {
        super(convertAmountDataPort, exchangeRateDrivenPort);
    }

    @Override
    protected ConvertAmountOutputDTO performImpl(ConvertAmountInputDTO convertAmountInputDTO) throws ConvertAmountException {
        Double convertedAmount = calculateConvertedAmount(convertAmountInputDTO);
        String transactionGuid = createTransactionGuid();
        save(convertAmountInputDTO, convertedAmount, transactionGuid);
        return createConvertAmountOutput(convertAmountInputDTO, convertedAmount, transactionGuid);
    }

    @Override
    protected void validateInput(ConvertAmountInputDTO convertAmountInputDTO) throws ConvertAmountException {
        if(!StringUtils.hasText(convertAmountInputDTO.getFromCurrency()))
        {
            throw new ConvertAmountException(ConvertAmountExceptionDefinition.CONVERT_AMOUNT_NULL_FROM_CURRENCY);
        }
        if(!StringUtils.hasText(convertAmountInputDTO.getToCurrency()))
        {
            throw new ConvertAmountException(ConvertAmountExceptionDefinition.CONVERT_AMOUNT_NULL_TO_CURRENCY);
        }
        if(convertAmountInputDTO.getConversionAmount() == null || convertAmountInputDTO.getConversionAmount() < 1)
        {
            throw new ConvertAmountException(ConvertAmountExceptionDefinition.CONVERT_AMOUNT_INVALID_AMOUNT);
        }

    }


    private ConvertAmountOutputDTO createConvertAmountOutput(ConvertAmountInputDTO convertAmountInputDTO, Double amount, String transactionGuid) {
        ConvertAmountOutputDTO convertAmountOutputDTO = new ConvertAmountOutputDTO();
        convertAmountOutputDTO.setId(transactionGuid);
        convertAmountOutputDTO.setFromCurrency(convertAmountInputDTO.getFromCurrency());
        convertAmountOutputDTO.setToCurrency(convertAmountInputDTO.getToCurrency());
        convertAmountOutputDTO.setConversionAmount(convertAmountInputDTO.getConversionAmount());
        convertAmountOutputDTO.setConvertedAmount(amount);
        convertAmountOutputDTO.setTransactionDate(transactionDate);
        return convertAmountOutputDTO;
    }

    private void save(ConvertAmountInputDTO convertAmountInputDTO, Double convertedAmount, String transactionGuid) {
        ConvertAmountTransactionDTO convertAmountTransactionDTO = new ConvertAmountTransactionDTO();
        convertAmountTransactionDTO.setId(transactionGuid);
        convertAmountTransactionDTO.setFromCurrency(convertAmountInputDTO.getFromCurrency());
        convertAmountTransactionDTO.setToCurrency(convertAmountInputDTO.getToCurrency());
        convertAmountTransactionDTO.setConversionAmount(convertAmountInputDTO.getConversionAmount());
        convertAmountTransactionDTO.setConvertedAmount(convertedAmount);
        convertAmountTransactionDTO.setTransactionDate(transactionDate);
        convertAmountDataPort.save(convertAmountTransactionDTO);
    }

    private String createTransactionGuid() {
        return UUID.randomUUID().toString();
    }

    private Double calculateConvertedAmount(ConvertAmountInputDTO convertAmountInputDTO) throws ConvertAmountException {
        ExchangeRatePortOutputDTO exchangeRatePortOutputDTO = exchangeRateDrivenPort.getExchangeRates(null, convertAmountInputDTO.getFromCurrency(), convertAmountInputDTO.getToCurrency());

        if (!exchangeRatePortOutputDTO.getBaseTargetCurrencyPair().containsKey(convertAmountInputDTO.getToCurrency()) && !exchangeRatePortOutputDTO.getBaseTargetCurrencyPair().containsKey(convertAmountInputDTO.getFromCurrency())) {
            throw new ConvertAmountException(ConvertAmountExceptionDefinition.CONVERT_AMOUNT_INVALID_CURRENCY_INPUT);
        }
        if (!exchangeRatePortOutputDTO.getBaseTargetCurrencyPair().containsKey(convertAmountInputDTO.getToCurrency())) {
            throw new ConvertAmountException(ConvertAmountExceptionDefinition.CONVERT_AMOUNT_INVALID_TO_CURRENCY_INPUT);
        }
        if (!exchangeRatePortOutputDTO.getBaseTargetCurrencyPair().containsKey(convertAmountInputDTO.getFromCurrency())) {
            throw new ConvertAmountException(ConvertAmountExceptionDefinition.CONVERT_AMOUNT_INVALID_FROM_CURRENCY_INPUT);
        }

        return (exchangeRatePortOutputDTO.getBaseTargetCurrencyPair().get(convertAmountInputDTO.getToCurrency()) / exchangeRatePortOutputDTO.getBaseTargetCurrencyPair().get(convertAmountInputDTO.getFromCurrency())) * convertAmountInputDTO.getConversionAmount();
    }

    @Override
    protected List<ConvertAmountTransactionDTO> performImpl(String transactionId, String transactionDate, Integer page, Integer pageSize) throws ConvertAmountException {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void validateInput(String transactionId, String transactionDate, Integer page, Integer pageSize) throws ConvertAmountException  {
        throw new UnsupportedOperationException();
    }


}
