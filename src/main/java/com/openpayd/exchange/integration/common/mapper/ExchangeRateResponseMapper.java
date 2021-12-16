package com.openpayd.exchange.integration.common.mapper;

import com.openpayd.exchange.domain.data.ExchangeRateOutputDTO;
import com.openpayd.exchange.integration.rest.response.ExchangeRateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ExchangeRateResponseMapper {
    ExchangeRateResponseMapper exchangeRateResponseMapper = Mappers.getMapper(ExchangeRateResponseMapper.class);


    @Named("convertDomainModelToResponse")
    @Mappings({
            @Mapping(target = "baseCurrency", source = "baseCurrency"),
            @Mapping(target = "targetCurrency", source = "targetCurrency"),
            @Mapping(target = "date", source = "date"),
            @Mapping(target = "rate", source = "rate")}
    )
    ExchangeRateResponse convertDomainModelToResponse(ExchangeRateOutputDTO exchangeRateOutputDTO);
}
