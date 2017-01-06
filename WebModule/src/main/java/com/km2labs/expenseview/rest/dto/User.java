package com.km2labs.expenseview.rest.dto;

import com.km2labs.expenseview.common.RegistrationType;
import lombok.Getter;
import lombok.Setter;
import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Subham Tyagi
 * On 6/10/2015.
 * <p>
 * TODO: Add class comments
 */
@XmlRootElement
@AutoProperty
@Getter
@Setter
public class User {

    private long id;

    private String name;

    private String email;

    private String phoneNumber;

    private String profilePhotoUrl;

    private RegistrationType registrationType;

    private boolean isActive;

    private List<Device> deviceDetailsList;

    private List<ExpenseBook> expenseBooks;

    private List<Account> accounts;

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
