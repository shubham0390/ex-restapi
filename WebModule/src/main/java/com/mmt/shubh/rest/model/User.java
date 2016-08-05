package com.mmt.shubh.rest.model;


import lombok.Getter;
import lombok.Setter;
import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement
@AutoProperty
@Getter
@Setter
public class User {

    private String name;
    private String email;
    private String phoneNumber;
    private String profileImageUrl;
    private String coverImageUrl;
    private Device device;
    private Set<Device> otherDevices;

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
