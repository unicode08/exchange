package com.openpayd.exchange.domain.port.driven;

import com.openpayd.exchange.domain.data.ConvertAmountTransactionDTO;

public interface ConvertAmountDataPort {
    void save(ConvertAmountTransactionDTO convertAmountTransactionDTO);
}
