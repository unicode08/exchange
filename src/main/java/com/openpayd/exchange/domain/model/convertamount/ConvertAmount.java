package com.openpayd.exchange.domain.model.convertamount;

import com.openpayd.exchange.domain.data.ConvertAmountInputDTO;
import com.openpayd.exchange.domain.data.ConvertAmountOutputDTO;
import com.openpayd.exchange.domain.data.ConvertAmountTransactionDTO;
import com.openpayd.exchange.domain.data.ExchangeRatePortOutputDTO;
import com.openpayd.exchange.domain.port.driven.ConvertAmountDataPort;
import com.openpayd.exchange.domain.port.driven.ExchangeRateDrivenPort;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ConvertAmount {
    private final ConvertAmountDataPort convertAmountDataPort;
    private final ExchangeRateDrivenPort exchangeRateDrivenPort;
    private final Date transactionDate;

    public ConvertAmount(ConvertAmountDataPort convertAmountDataPort, ExchangeRateDrivenPort exchangeRateDrivenPort) {
        this.convertAmountDataPort = convertAmountDataPort;
        this.exchangeRateDrivenPort = exchangeRateDrivenPort;
        this.transactionDate = new Date();
    }

    public ConvertAmountOutputDTO convertAmount(ConvertAmountInputDTO convertAmountInputDTO) {
        Double convertedAmount = calculateConvertedAmount(convertAmountInputDTO);
        String transactionGuid = createTransactionGuid();
        save(convertAmountInputDTO, convertedAmount, transactionGuid);
        return createConvertAmountOutput(convertAmountInputDTO, convertedAmount, transactionGuid);
    }

    public List<ConvertAmountTransactionDTO> getConvertedAmountInfo(String transactionId, String transactionDate, Integer page, Integer pageSize) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-mm-dd");
        return convertAmountDataPort.getConvertedAmountTransactionDTOListByTransactionIdOrTransactionDate(transactionId, sdf.parse(transactionDate), page, pageSize);
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

    private Double calculateConvertedAmount(ConvertAmountInputDTO convertAmountInputDTO) {
        ExchangeRatePortOutputDTO exchangeRatePortOutputDTO = exchangeRateDrivenPort.getExchangeRates(null, convertAmountInputDTO.getFromCurrency(), convertAmountInputDTO.getToCurrency());
        return (exchangeRatePortOutputDTO.getBaseTargetCurrencyPair().get(convertAmountInputDTO.getToCurrency()) / exchangeRatePortOutputDTO.getBaseTargetCurrencyPair().get(convertAmountInputDTO.getFromCurrency())) * convertAmountInputDTO.getConversionAmount();
    }
}
