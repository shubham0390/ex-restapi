package com.mmt.shubh.entity;

import lombok.Getter;
import lombok.Setter;
import org.pojomatic.Pojomatic;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Subham Tyagi
 * On 6/10/2015.
 * <p>
 * TODO: Add class comments
 */
@Entity
@Table(name = "EXPENSE_BOOK")
@Getter
@Setter
public class ExpenseBookEntity extends AbstractEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String profileImagePath;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String type;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "EXPENSEBOOK_MEMBER",
            joinColumns =
            @JoinColumn(name = "EXPENSEBOOK_ID", referencedColumnName = "ID"),
            inverseJoinColumns =
            @JoinColumn(name = "MEMBER_ID", referencedColumnName = "ID")
    )
    private Set<MemberEntity> memberList;

    @OneToMany(mappedBy = "expenseBook", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ExpenseEntity> expenses;

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

    public ExpenseBookEntity() {
    }
}
