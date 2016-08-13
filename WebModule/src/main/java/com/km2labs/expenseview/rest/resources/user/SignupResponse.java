package com.km2labs.expenseview.rest.resources.user;

import com.km2labs.expenseview.rest.model.User;
import lombok.Getter;
import lombok.Setter;
import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@AutoProperty
@Getter
@Setter
public class SignupResponse {
    private User user;
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
