/*
 * Copyright (c) 2016. . The Km2Labs Project
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 */

package com.km2labs.expenseview.database.entity;

import lombok.Getter;
import lombok.Setter;
import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;
import org.pojomatic.annotations.PojomaticPolicy;
import org.pojomatic.annotations.Property;

import javax.persistence.*;
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
@Table(name = "EXPENSE")
public class ExpenseEntity extends AbstractEntity {

    @Column(nullable = false)
    private String amount;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @OneToOne(fetch = FetchType.LAZY,optional = false)
    @PrimaryKeyJoinColumn
    private UserEntity owner;

    @ManyToOne
    @JoinColumn(name = "expense_book_id", nullable = false)
    @Property(policy = PojomaticPolicy.NONE)
    private ExpenseBookEntity expenseBook;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @Property(policy = PojomaticPolicy.NONE)
    private CategoryEntity category;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "member_expense",
            joinColumns =
            @JoinColumn(name = "expense_id", referencedColumnName = "ID"),
            inverseJoinColumns =
            @JoinColumn(name = "member_id", referencedColumnName = "ID")
    )
    @Property(policy = PojomaticPolicy.NONE)
    private Collection<UserEntity> members;

    @OneToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.EAGER, orphanRemoval = true)
    @PrimaryKeyJoinColumn
    @Property(policy = PojomaticPolicy.NONE)
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
