package com.mmt.shubh.entity;

import lombok.Getter;
import lombok.Setter;
import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Subham Tyagi
 * On 7/24/2015.
 * <p>
 * TODO: Add class comments
 */
@Entity
@Table(name = "account_transaction")
@Getter
@Setter
@AutoProperty
public class AccountTransactionEntity extends AbstractEntity {
    @Column(name = "transaction_name", nullable = false)
    private String transactionName;
    @Column(name = "credit_amount")
    private int creditAmount;
    @Column(name = "debit_amount")
    private int debitAmount;
    @Column(name = "transaction_date", nullable = false)
    private Date transactionDate;
    @Column(name = "balance_amount", nullable = false)
    private int balanceAmount;
    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    private AccountEntity account;


    @Override
    public boolean equals(Object o) {
        return Pojomatic.equals(this, o);

    }

    @Override
    public int hashCode() {
        return Pojomatic.hashCode(this);
    }

    @Override
    public String toString() {
        return Pojomatic.toString(this);

    }
}
