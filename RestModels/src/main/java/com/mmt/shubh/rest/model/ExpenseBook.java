package com.mmt.shubh.rest.model;

import java.util.List;

/**
 * Created by Subham Tyagi
 * On 6/10/2015.
 * <p>
 * TODO: Add class comments
 */
public class ExpenseBook {
    public long id;
    public String name;
    public String profileImagePath;
    public String description;
    public String type;
    public long accountId;
    public List<Member> memberList;
}
