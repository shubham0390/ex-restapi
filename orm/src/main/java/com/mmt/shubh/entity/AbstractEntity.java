package com.mmt.shubh.entity;

import lombok.Getter;
import lombok.Setter;
import org.pojomatic.Pojomatic;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Subham Tyagi
 * On 6/12/2015.
 * <p>
 * TODO: Add class comments
 */
@MappedSuperclass
@Getter
public class AbstractEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private long id;


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

    public AbstractEntity setId(long id) {
        this.id = id;
        return this;
    }
}
