package com.mmt.shubh.rest.model;

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
public class Member {

    private long id;

    private String memberName;

    private String memberEmail;

    private String userPassword;

    private String displayName;

    private String phoneNumber;

    private String coverPhotoUrl;

    private String profilePhotoUrl;

    private boolean isRegistered;

    private boolean isActive;

    private List<DeviceDetails> deviceDetailsList;

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
