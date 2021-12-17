package com.openpayd.exchange.integration.common.mapper;

import com.openpayd.exchange.domain.data.ExchangeRateOutputDTO;
import com.openpayd.exchange.integration.rest.response.ExchangeRateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ExchangeRateResponseMapper {
    ExchangeRateResponseMapper exchangeRateResponseMapper = Mappers.getMapper(ExchangeRateResponseMapper.class);

    ExchangeRateResponse convertDomainModelToResponse(ExchangeRateOutputDTO exchangeRateOutputDTO);
}
