package com.openpayd.exchange.integration.adapter.jpa.conversion;

import com.openpayd.exchange.domain.data.ConvertAmountTransactionDTO;
import com.openpayd.exchange.integration.AbstractIT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class ConvertAmountDataAdapterTest extends AbstractIT {

    @Autowired
    ConvertAmountDataAdapter convertAmountDataAdapter;

    @Test
    public void whenValidInputGiven_getConvertAmountEntity_thenReturnNoException()
    {
        ConvertAmountTransactionDTO convertAmountTransactionDTO = convertAmountDataAdapter.getConvertAmountEntity("1");
        assertEquals("USD",convertAmountTransactionDTO.getFromCurrency());
    }
}