package com.openpayd.exchange.domain.port.driven;

import com.openpayd.exchange.domain.data.ConvertAmountTransactionDTO;

import java.util.Date;
import java.util.List;

public interface ConvertAmountDataPort {
    void save(ConvertAmountTransactionDTO convertAmountTransactionDTO);
    List<ConvertAmountTransactionDTO> getConvertedAmountTransactionDTOListByTransactionIdOrTransactionDate(String transactionId, Date transactionDate, Integer page, Integer pageSize);
}
