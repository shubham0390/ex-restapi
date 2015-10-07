package com.mmt.shubh.entity;

import lombok.Getter;
import lombok.Setter;
import org.pojomatic.Pojomatic;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Subham Tyagi
 * On 7/24/2015.
 * <p>
 * TODO: Add class comments
 */
@Entity
@Table(name = "account")
@Getter
@Setter
public class AccountEntity extends AbstractEntity {

    @Column(name = "account_name", nullable = false)
    private String accountName;
    @Column(name = "account_balance", nullable = false)
    private int accountBalance;
    @Column(name = "member_name", nullable = false)
    private MemberEntity member;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<AccountTransactionEntity> expense;
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
