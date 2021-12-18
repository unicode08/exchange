package com.openpayd.exchange.integration.adapter.jpa.conversion;

import com.openpayd.exchange.domain.data.ConvertAmountTransactionDTO;
import com.openpayd.exchange.integration.AbstractIT;
import org.assertj.core.util.DateUtil;
import org.h2.util.DateTimeUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

class ConvertAmountDataAdapterTest extends AbstractIT {

    @Autowired
    ConvertAmountDataAdapter convertAmountDataAdapter;

    @Test
    public void whenValidInputGiven_getConvertAmountEntity_thenReturnNoException() {
        ConvertAmountTransactionDTO convertAmountTransactionDTO = convertAmountDataAdapter.getConvertAmountEntity("1");
        assertEquals("USD", convertAmountTransactionDTO.getFromCurrency());
    }

    @Test
    public void whenValidInputGiven_getConvertedAmountInfoById_thenReturnNoException() {
        List<ConvertAmountTransactionDTO> convertAmountTransactionDTOList = convertAmountDataAdapter.getConvertedAmountTransactionDTOListByTransactionIdOrTransactionDate("1", null,0,10);
        assertTrue(convertAmountTransactionDTOList != null && !convertAmountTransactionDTOList.isEmpty());
    }

    @Test
    public void whenValidInputGiven_getConvertedAmountInfoByDate_thenReturnNoException() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse("2020-02-03");
        DateUtil.truncateTime(date);
        List<ConvertAmountTransactionDTO> convertAmountTransactionDTOList = convertAmountDataAdapter.getConvertedAmountTransactionDTOListByTransactionIdOrTransactionDate(null, date,0,10);
        assertTrue(convertAmountTransactionDTOList != null && !convertAmountTransactionDTOList.isEmpty());
    }
}