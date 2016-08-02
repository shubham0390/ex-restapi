package com.mmt.shubh.rest.resources.user;

import com.mmt.shubh.rest.model.Device;
import com.mmt.shubh.rest.model.User;
import lombok.Getter;
import lombok.Setter;
import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by suze on 31/07/16.
 */
@XmlRootElement
@AutoProperty
@Getter
@Setter
public class SignupRequest {
    private User user;
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
