package com.openpayd.exchange.integration.common.mapper;

import com.openpayd.exchange.domain.data.ConvertAmountTransactionDTO;
import com.openpayd.exchange.integration.adapter.jpa.conversion.entity.ConvertAmountTransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ConvertAmountTransactionEntityMapper {
    ConvertAmountTransactionEntityMapper convertAmountTransactionEntityMapper = Mappers.getMapper(ConvertAmountTransactionEntityMapper.class);

    ConvertAmountTransactionDTO convertEntityToTransactionDTO(ConvertAmountTransactionEntity convertAmountTransactionEntity);

    ConvertAmountTransactionEntity convertTransactionDTOToEntity(ConvertAmountTransactionDTO convertAmountTransactionDTO);
}
