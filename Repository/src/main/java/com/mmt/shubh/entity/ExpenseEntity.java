package com.mmt.shubh.entity;

import lombok.Getter;
import lombok.Setter;
import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

import javax.persistence.*;

/**
 * Created by Subham Tyagi
 * On 6/10/2015.
 * <p>
 * TODO: Add class comments
 */
@Table(name = "EXPENSE")
@Entity
@Getter
@Setter
@AutoProperty
public class ExpenseEntity extends AbstractEntity {


    @Column(nullable = false)
    private String amount;

    @Column(nullable = false)
    private long date;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long syncKey;

    @ManyToOne
    @JoinColumn(name = "EXPENSE_BOOK_ID", nullable = false)
    private ExpenseBookEntity expenseBook;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    private CategoryEntity category;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID", nullable = false)
    private MemberEntity member;

    @OneToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "EXPENSE_ID", unique = true, nullable = true, insertable = true, updatable = true)
    private AccountTransactionEntity accountTransaction;

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
