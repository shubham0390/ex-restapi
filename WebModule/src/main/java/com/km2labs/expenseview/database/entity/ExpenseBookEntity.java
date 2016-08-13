package com.km2labs.expenseview.database.entity;

import lombok.Getter;
import lombok.Setter;
import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;
import org.pojomatic.annotations.PojomaticPolicy;
import org.pojomatic.annotations.Property;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * Created by Subham Tyagi
 * On 6/10/2015.
 * <p>
 * TODO: Add class comments
 */
@Entity
@Getter
@Setter
@AutoProperty
@Table(name = "EXPENSE_BOOK")
public class ExpenseBookEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private long id;

    @Column(nullable = false)
    private String clientId;

    @Column(nullable = false)
    private String name;

    @Column
    private String profileImagePath;

    @Column
    private String description;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String ownerEmailId;

    @Column(name = "created_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;

    @Column(name = "active")
    private boolean isActive;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "expensebook_membber",
            joinColumns =
            @JoinColumn(name = "expense_book_id", referencedColumnName = "ID"),
            inverseJoinColumns =
            @JoinColumn(name = "member_id", referencedColumnName = "ID")
    )
    @Property(policy = PojomaticPolicy.NONE)
    private Collection<MemberEntity> memberList;

    @OneToMany(mappedBy = "expenseBook", fetch = FetchType.LAZY)
    @Property(policy = PojomaticPolicy.NONE)
    private Collection<ExpenseEntity> expenses;

    public ExpenseBookEntity() {
    }

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
