package com.km2labs.expenseview.rest.model;

import lombok.Getter;
import lombok.Setter;
import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Subham Tyagi
 * On 6/5/2015.
 * <p>
 * TODO: Add class comments
 */
@XmlRootElement
@AutoProperty
@Getter
@Setter
public class Device {

    private long id;

    private String deviceUUID;

    private String gcmToken;

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
