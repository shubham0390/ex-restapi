package com.km2labs.expenseview.rest.model;

import lombok.Getter;
import lombok.Setter;
import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by subhamtyagi on 2/18/16.
 */
@Getter
@Setter
@AutoProperty
@XmlRootElement
public class Account {

    private String accountName;
    private int accountBalance;
    private String accountType;
    private String member;

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
