package com.openpayd.exchange.integration.common.mapper;

import com.openpayd.exchange.domain.data.ConvertAmountOutputDTO;
import com.openpayd.exchange.domain.data.ConvertAmountTransactionDTO;
import com.openpayd.exchange.integration.rest.response.ConvertAmountResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ConvertAmountResponseMapper {
    ConvertAmountResponseMapper convertAmountResponseMapper = Mappers.getMapper(ConvertAmountResponseMapper.class);

    ConvertAmountResponse convertDomainModelToResponse(ConvertAmountOutputDTO convertAmountOutputDTO);

    List<ConvertAmountResponse> convertDomainModelToResponseList(List<ConvertAmountTransactionDTO> convertAmountTransactionDTOList);
}
