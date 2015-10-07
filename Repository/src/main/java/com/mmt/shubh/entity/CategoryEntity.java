package com.mmt.shubh.entity;


import lombok.Getter;
import lombok.Setter;
import org.pojomatic.Pojomatic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Subham Tyagi
 * On 6/10/2015.
 * <p>
 * TODO: Add class comments
 */
@Table(name = "CATEGORY")
@Getter
@Entity
@Setter
public class CategoryEntity extends AbstractEntity {

    @Column(name = "name", nullable = false)
    private String name;

  /*  @OneToMany(mappedBy = "category")
    private Set<ExpenseEntity> expenses;*/

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
