package com.openpayd.exchange.domain.adapter;

import com.openpayd.exchange.domain.data.ConvertAmountTransactionDTO;
import com.openpayd.exchange.domain.port.driven.ConvertAmountDataPort;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConvertAmountDataPortFakeAdapter implements ConvertAmountDataPort {

    @Override
    public void save(ConvertAmountTransactionDTO convertAmountTransactionDTO) {
    }

    @Override
    public List<ConvertAmountTransactionDTO> getConvertedAmountTransactionDTOListByTransactionIdOrTransactionDate(String transactionId, Date transactionDate,Integer page,Integer pageSize) {
        List<ConvertAmountTransactionDTO> convertAmountTransactionDTOList = new ArrayList<>();
        ConvertAmountTransactionDTO convertAmountTransactionDTO = new ConvertAmountTransactionDTO();
        convertAmountTransactionDTO.setId("1");
        convertAmountTransactionDTO.setFromCurrency("EUR");
        convertAmountTransactionDTO.setToCurrency("USD");
        convertAmountTransactionDTO.setConversionAmount(10D);
        convertAmountTransactionDTO.setConvertedAmount(12D);
        convertAmountTransactionDTO.setTransactionDate(new Date());
        convertAmountTransactionDTOList.add(convertAmountTransactionDTO);
        return convertAmountTransactionDTOList;
    }
}
