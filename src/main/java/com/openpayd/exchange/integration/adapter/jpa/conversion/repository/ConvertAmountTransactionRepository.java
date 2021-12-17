package com.openpayd.exchange.integration.adapter.jpa.conversion.repository;

import com.openpayd.exchange.integration.adapter.jpa.conversion.entity.ConvertAmountTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ConvertAmountTransactionRepository extends JpaRepository<ConvertAmountTransactionEntity, String>, JpaSpecificationExecutor<ConvertAmountTransactionEntity> {
}
