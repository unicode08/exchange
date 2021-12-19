package com.openpayd.exchange.domain.port.driving;

import com.openpayd.exchange.AbstractUT;
import com.openpayd.exchange.domain.adapter.ConvertAmountDataPortFakeAdapter;
import com.openpayd.exchange.domain.adapter.ExchangeRateDrivenPortFakeAdapter;
import com.openpayd.exchange.domain.data.ConvertAmountInputDTO;
import com.openpayd.exchange.domain.data.ConvertAmountOutputDTO;
import com.openpayd.exchange.domain.data.ConvertAmountTransactionDTO;
import com.openpayd.exchange.domain.data.ExchangeRatePortOutputDTO;
import com.openpayd.exchange.domain.exception.ConvertAmountException;
import com.openpayd.exchange.domain.port.driven.ConvertAmountDataPort;
import com.openpayd.exchange.domain.port.driven.ExchangeRateDrivenPort;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ConversionDrivingPortTest extends AbstractUT {

    ConversionDrivingPort conversionDrivingPort;
    ExchangeRateDrivenPort exchangeRateDrivenPort;
    ExchangeRatePortOutputDTO exchangeRatePortOutputDTO;
    String transactionId;
    String transactionDate;
    Integer page;
    Integer pageSize;

    @BeforeAll
    public void setUp() {
        ConvertAmountDataPort convertAmountDataPort = new ConvertAmountDataPortFakeAdapter();
        exchangeRatePortOutputDTO = new ExchangeRatePortOutputDTO();
        Map<String, Double> currencyRateMap = new HashMap<String, Double>();
        currencyRateMap.put("USD", 1.133793);
        currencyRateMap.put("EUR", 17.760031);
        exchangeRatePortOutputDTO.setBaseTargetCurrencyPair(currencyRateMap);
        exchangeRatePortOutputDTO.setDate("2021-16-12");
        exchangeRateDrivenPort = new ExchangeRateDrivenPortFakeAdapter(exchangeRatePortOutputDTO);
        conversionDrivingPort = new ConversionDrivingPort(convertAmountDataPort, exchangeRateDrivenPort);

        transactionId = "1";
        transactionDate = "2021-16-12";
        page = 1;
        pageSize = 2;
    }


    @Test
    public void whenValidInputGiven_convertAmount_thenReturnNoException() throws ConvertAmountException {
        ConvertAmountInputDTO convertAmountInputDTO = new ConvertAmountInputDTO();
        convertAmountInputDTO.setFromCurrency("USD");
        convertAmountInputDTO.setToCurrency("EUR");
        convertAmountInputDTO.setConversionAmount(10D);
        ConvertAmountOutputDTO convertAmountOutputDTO = conversionDrivingPort.convertAmount(convertAmountInputDTO);
        assertEquals(156.64262347712503, convertAmountOutputDTO.getConvertedAmount());
    }

    @Test
    public void whenInValidInputGiven_convertAmountWithWrongCurrencies_thenThrowsException() {
        ConvertAmountInputDTO convertAmountInputDTO = new ConvertAmountInputDTO();
        convertAmountInputDTO.setFromCurrency("USDD");
        convertAmountInputDTO.setToCurrency("EURR");
        convertAmountInputDTO.setConversionAmount(10D);
        assertThrows(ConvertAmountException.class, () -> conversionDrivingPort.convertAmount(convertAmountInputDTO));
    }

    @Test
    public void whenInValidInputGiven_convertAmountWithWrongFromCurrency_thenThrowsException() {
        ConvertAmountInputDTO convertAmountInputDTO = new ConvertAmountInputDTO();
        convertAmountInputDTO.setFromCurrency("USDD");
        convertAmountInputDTO.setToCurrency("EUR");
        convertAmountInputDTO.setConversionAmount(10D);
        assertThrows(ConvertAmountException.class, () -> conversionDrivingPort.convertAmount(convertAmountInputDTO));
        convertAmountInputDTO.setFromCurrency("");
        assertThrows(ConvertAmountException.class, () -> conversionDrivingPort.convertAmount(convertAmountInputDTO));
        convertAmountInputDTO.setFromCurrency(null);
        assertThrows(ConvertAmountException.class, () -> conversionDrivingPort.convertAmount(convertAmountInputDTO));
    }

    @Test
    public void whenInValidInputGiven_convertAmountWithWrongToCurrency_thenThrowsException() {
        ConvertAmountInputDTO convertAmountInputDTO = new ConvertAmountInputDTO();
        convertAmountInputDTO.setFromCurrency("USD");
        convertAmountInputDTO.setToCurrency("EURR");
        convertAmountInputDTO.setConversionAmount(10D);
        assertThrows(ConvertAmountException.class, () -> conversionDrivingPort.convertAmount(convertAmountInputDTO));
        convertAmountInputDTO.setToCurrency("");
        assertThrows(ConvertAmountException.class, () -> conversionDrivingPort.convertAmount(convertAmountInputDTO));
        convertAmountInputDTO.setToCurrency(null);
        assertThrows(ConvertAmountException.class, () -> conversionDrivingPort.convertAmount(convertAmountInputDTO));
    }

    @Test
    public void whenInValidInputGiven_convertAmountWithWrongAmount_thenThrowsException() {
        ConvertAmountInputDTO convertAmountInputDTO = new ConvertAmountInputDTO();
        convertAmountInputDTO.setFromCurrency("USD");
        convertAmountInputDTO.setToCurrency("EUR");
        convertAmountInputDTO.setConversionAmount(-1d);
        assertThrows(ConvertAmountException.class, () -> conversionDrivingPort.convertAmount(convertAmountInputDTO));
        convertAmountInputDTO.setConversionAmount(0d);
        assertThrows(ConvertAmountException.class, () -> conversionDrivingPort.convertAmount(convertAmountInputDTO));
        convertAmountInputDTO.setConversionAmount(1d);
        assertDoesNotThrow(() -> conversionDrivingPort.convertAmount(convertAmountInputDTO));
    }


    @Test
    public void whenValidInputGiven_getConvertedAmountInfo_thenReturnNoException() throws ConvertAmountException {
        List<ConvertAmountTransactionDTO> convertAmountTransactionDTOList = conversionDrivingPort.getConvertedAmountInfo(transactionId, transactionDate, page, pageSize);
        assertTrue(!convertAmountTransactionDTOList.isEmpty());
    }

    @Test
    public void whenInValidInputGiven_getConvertedAmountInfo_thenThrowsException() throws ConvertAmountException {
        assertThrows(ConvertAmountException.class, () -> conversionDrivingPort.getConvertedAmountInfo(transactionId, "232132", page, pageSize));
    }

}