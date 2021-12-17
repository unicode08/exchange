package com.openpayd.exchange.domain.port.driving;

import com.openpayd.exchange.domain.data.ConvertAmountInputDTO;
import com.openpayd.exchange.domain.data.ConvertAmountOutputDTO;
import com.openpayd.exchange.domain.model.convertamount.ConvertAmount;
import com.openpayd.exchange.domain.port.driven.ConvertAmountDataPort;
import com.openpayd.exchange.domain.port.driven.ExchangeRateDrivenPort;

public class ConversionDrivingPort {

    ConvertAmount convertAmount;

    public ConversionDrivingPort(ConvertAmountDataPort convertAmountDataPort, ExchangeRateDrivenPort exchangeRateDrivenPort) {
        convertAmount = new ConvertAmount(convertAmountDataPort, exchangeRateDrivenPort);
    }

    public ConvertAmountOutputDTO convertAmount(ConvertAmountInputDTO convertAmountInputDTO) {
        return convertAmount.convertAmount(convertAmountInputDTO);
    }
}
