package com.km2labs.expenseview.rest.dto;

import lombok.Getter;
import lombok.Setter;
import org.pojomatic.Pojomatic;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Subham Tyagi
 * On 6/10/2015.
 * <p>
 * TODO: Add class comments
 */
@XmlRootElement
@Getter
@Setter
public class Category {
    private long id;
    private String name;
    private String type;

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
