package com.openpayd.exchange.domain.adapter;

import com.openpayd.exchange.domain.data.ConvertAmountTransactionDTO;
import com.openpayd.exchange.domain.port.driven.ConvertAmountDataPort;

public class ConvertAmountDataPortFakeAdapter implements ConvertAmountDataPort {

    @Override
    public void save(ConvertAmountTransactionDTO convertAmountTransactionDTO) {
    }
}
