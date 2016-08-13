package com.km2labs.expenseview.rest.resources.user;

import com.km2labs.expenseview.rest.model.Device;
import lombok.Getter;
import lombok.Setter;
import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by suze on 11/08/16.
 */
@XmlRootElement
@AutoProperty
@Getter
@Setter
public class LoginRequest {

    private String loginType;
    private String authenticationToken;
    private String mobileNo;
    private Device device;

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
