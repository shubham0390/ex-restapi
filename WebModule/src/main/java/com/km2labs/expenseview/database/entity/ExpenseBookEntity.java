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
