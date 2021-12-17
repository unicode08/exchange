package com.openpayd.exchange.integration.common.mapper;

import com.openpayd.exchange.domain.data.ConvertAmountInputDTO;
import com.openpayd.exchange.integration.rest.request.ConvertAmountRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ConvertAmountRequestMapper {
    ConvertAmountRequestMapper convertAmountRequestMapper = Mappers.getMapper(ConvertAmountRequestMapper.class);

    ConvertAmountInputDTO convertRequestToDomainModel(ConvertAmountRequest convertAmountRequest);

}
