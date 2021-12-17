package com.openpayd.exchange.integration.adapter.jpa.conversion.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "CONVERT_AMOUNT_TRANSACTION")
@Getter
@Setter
public class ConvertAmountTransactionEntity implements Serializable {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "FROM_CURRENCY")
    private String fromCurrency;

    @Column(name = "TO_CURRENCY")
    private String toCurrency;

    @Column(name = "CONVERSION_AMOUNT")
    private Double conversionAmount;

    @Column(name = "CONVERTED_AMOUNT")
    private Double convertedAmount;

    @Column(name = "TRANSACTION_DATE")
    private Date transactionDate;


}
