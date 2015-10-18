package com.mmt.shubh.rest.model;

import lombok.Getter;
import lombok.Setter;
import org.pojomatic.annotations.AutoProperty;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

/**
 * Created by Subham Tyagi
 * On 6/10/2015.
 * <p>
 * TODO: Add class comments
 */
@Getter
@Setter
@AutoProperty
@XmlRootElement
public class ExpenseBook {
    private long id;
    private String clientId;
    private String name;
    private String ownerEmailId;
    private String profileImagePath;
    private String description;
    private String type;
    private long accountId;
    private List<Member> memberList;
    private Date creationDate;

}
