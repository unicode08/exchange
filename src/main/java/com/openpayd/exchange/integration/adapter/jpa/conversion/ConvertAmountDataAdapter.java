package com.openpayd.exchange.integration.adapter.jpa.conversion;

import com.openpayd.exchange.domain.data.ConvertAmountTransactionDTO;
import com.openpayd.exchange.domain.port.driven.ConvertAmountDataPort;
import com.openpayd.exchange.integration.adapter.jpa.conversion.entity.ConvertAmountTransactionEntity;
import com.openpayd.exchange.integration.adapter.jpa.conversion.repository.ConvertAmountTransactionRepository;
import com.openpayd.exchange.integration.common.mapper.ConvertAmountTransactionEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ConvertAmountDataAdapter implements ConvertAmountDataPort {

    @Autowired
    ConvertAmountTransactionRepository convertAmountTransactionRepository;

    @Override
    public void save(ConvertAmountTransactionDTO convertAmountTransactionDTO) {
        convertAmountTransactionRepository.save(ConvertAmountTransactionEntityMapper.convertAmountTransactionEntityMapper.convertTransactionDTOToEntity(convertAmountTransactionDTO));
    }

    public ConvertAmountTransactionDTO getConvertAmountEntity(String transactionId) {
        Optional<ConvertAmountTransactionEntity> convertAmountTransactionEntity = convertAmountTransactionRepository.findById(transactionId);
        return ConvertAmountTransactionEntityMapper.convertAmountTransactionEntityMapper.convertEntityToTransactionDTO(convertAmountTransactionEntity.get());
    }
}
