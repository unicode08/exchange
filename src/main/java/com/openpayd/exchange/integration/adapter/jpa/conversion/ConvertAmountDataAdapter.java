package com.openpayd.exchange.integration.adapter.jpa.conversion;

import com.openpayd.exchange.domain.data.ConvertAmountTransactionDTO;
import com.openpayd.exchange.domain.port.driven.ConvertAmountDataPort;
import com.openpayd.exchange.integration.adapter.jpa.conversion.entity.ConvertAmountTransactionEntity;
import com.openpayd.exchange.integration.adapter.jpa.conversion.repository.ConvertAmountTransactionRepository;
import com.openpayd.exchange.integration.common.mapper.ConvertAmountTransactionEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ConvertAmountDataAdapter implements ConvertAmountDataPort {

    @Autowired
    ConvertAmountTransactionRepository convertAmountTransactionRepository;

    @Override
    public void save(ConvertAmountTransactionDTO convertAmountTransactionDTO) {
        convertAmountTransactionRepository.save(ConvertAmountTransactionEntityMapper.convertAmountTransactionEntityMapper.convertTransactionDTOToEntity(convertAmountTransactionDTO));
    }

    @Override
    public List<ConvertAmountTransactionDTO> getConvertedAmountTransactionDTOListByTransactionIdOrTransactionDate(String transactionId, Date transactionDate, Integer page, Integer pageSize) {

        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").descending());

        Specification<ConvertAmountTransactionEntity> convertAmountTransactionEntitySpecification = (root, query, criteriaBuilder) ->
        {
            List<Predicate> predicateList = new ArrayList<>();
            if (StringUtils.hasText(transactionId)) {
                predicateList.add(criteriaBuilder.equal(root.get("id"), transactionId));
            }
            if (!StringUtils.hasText(transactionId) && transactionDate != null) {
                predicateList.add(criteriaBuilder.equal(root.get("transactionDate"), transactionDate));
            }
            return criteriaBuilder.and(predicateList.toArray(Predicate[]::new));
        };

        Page<ConvertAmountTransactionEntity> convertAmountTransactionEntityPage = convertAmountTransactionRepository.findAll(convertAmountTransactionEntitySpecification, pageable);
        List<ConvertAmountTransactionDTO> convertAmountTransactionDTOList = convertAmountTransactionEntityPage.stream().map(convertAmountTransactionEntity -> ConvertAmountTransactionEntityMapper.convertAmountTransactionEntityMapper.convertEntityToTransactionDTO(convertAmountTransactionEntity)).collect(Collectors.toList());
        return convertAmountTransactionDTOList;
    }

    public ConvertAmountTransactionDTO getConvertAmountEntity(String transactionId) {
        Optional<ConvertAmountTransactionEntity> convertAmountTransactionEntity = convertAmountTransactionRepository.findById(transactionId);
        return ConvertAmountTransactionEntityMapper.convertAmountTransactionEntityMapper.convertEntityToTransactionDTO(convertAmountTransactionEntity.get());
    }
}
