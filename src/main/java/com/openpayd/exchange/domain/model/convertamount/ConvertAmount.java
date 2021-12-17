package com.openpayd.exchange.domain.model.convertamount;
import java.util.Date;

import com.openpayd.exchange.domain.data.ConvertAmountInputDTO;
import com.openpayd.exchange.domain.data.ConvertAmountOutputDTO;
import com.openpayd.exchange.domain.data.ConvertAmountTransactionDTO;
import com.openpayd.exchange.domain.data.ExchangeRatePortOutputDTO;
import com.openpayd.exchange.domain.port.driven.ConvertAmountDataPort;
import com.openpayd.exchange.domain.port.driven.ExchangeRateDrivenPort;

import java.util.UUID;

public class ConvertAmount {
    private final ConvertAmountDataPort convertAmountDataPort;
    private final ExchangeRateDrivenPort exchangeRateDrivenPort;

    public ConvertAmount(ConvertAmountDataPort convertAmountDataPort, ExchangeRateDrivenPort exchangeRateDrivenPort) {
        this.convertAmountDataPort = convertAmountDataPort;
        this.exchangeRateDrivenPort = exchangeRateDrivenPort;
    }

    public ConvertAmountOutputDTO convertAmount(ConvertAmountInputDTO convertAmountInputDTO) {
        Double convertedAmount = calculateConvertedAmount(convertAmountInputDTO);
        String transactionGuid = createTransactionGuid();
        save(convertAmountInputDTO, convertedAmount,transactionGuid);
        return createConvertAmountOutput(convertedAmount, transactionGuid);
    }

    private ConvertAmountOutputDTO createConvertAmountOutput(Double amount, String transactionGuid) {
        ConvertAmountOutputDTO convertAmountOutputDTO = new ConvertAmountOutputDTO();
        convertAmountOutputDTO.setTransactionId(transactionGuid);
        convertAmountOutputDTO.setAmount(amount);
        return convertAmountOutputDTO;
    }

    private void save(ConvertAmountInputDTO convertAmountInputDTO, Double convertedAmount, String transactionGuid) {
        ConvertAmountTransactionDTO convertAmountTransactionDTO = new ConvertAmountTransactionDTO();
        convertAmountTransactionDTO.setId(transactionGuid);
        convertAmountTransactionDTO.setFromCurrency(convertAmountInputDTO.getFromCurrency());
        convertAmountTransactionDTO.setToCurrency(convertAmountInputDTO.getToCurrency());
        convertAmountTransactionDTO.setConversionAmount(convertAmountInputDTO.getConversionAmount());
        convertAmountTransactionDTO.setConvertedAmount(convertedAmount);
        convertAmountTransactionDTO.setTransactionDate(new Date());
        convertAmountDataPort.save(convertAmountTransactionDTO);
    }

    private String createTransactionGuid() {
        return UUID.randomUUID().toString();
    }

    private Double calculateConvertedAmount(ConvertAmountInputDTO convertAmountInputDTO) {
        ExchangeRatePortOutputDTO exchangeRatePortOutputDTO = exchangeRateDrivenPort.getExchangeRates(null, convertAmountInputDTO.getFromCurrency(), convertAmountInputDTO.getToCurrency());
        return (exchangeRatePortOutputDTO.getBaseTargetCurrencyPair().get(convertAmountInputDTO.getToCurrency()) / exchangeRatePortOutputDTO.getBaseTargetCurrencyPair().get(convertAmountInputDTO.getFromCurrency()))* convertAmountInputDTO.getConversionAmount();
    }
}
