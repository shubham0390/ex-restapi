package com.mmt.shubh.entity;

import lombok.Getter;
import lombok.Setter;
import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;
import org.pojomatic.annotations.PojomaticPolicy;
import org.pojomatic.annotations.Property;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by Subham Tyagi
 * On 6/10/2015.
 * <p>
 * TODO: Add class comments
 */
@Table(name = "MEMBER")
@Entity
@Getter
@Setter
@AutoProperty
@SequenceGenerator(name = "member_seq", sequenceName = "member_seq", initialValue = 1, allocationSize = 1)
public class MemberEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq")
    @Column(name = "ID")
    private long id;

    @Column(name = "member_name", nullable = false)
    private String memberName;

    @Column(name = "member_email", nullable = false, unique = true)
    private String memberEmail;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "display_name", nullable = false)
    private String displayName;

    @Column(name = "cover_photo_url")
    private String coverPhotoUrl;

    @Column(name = "profile_photo_url")
    private String profilePhotoUrl;

    @Column(name = "registered")
    private boolean isRegistered;

    @ManyToMany(mappedBy = "memberList", fetch = FetchType.LAZY)
    @Property(policy = PojomaticPolicy.NONE)
    private Collection<ExpenseBookEntity> expenseBook;

    @ManyToMany(mappedBy = "members", fetch = FetchType.LAZY)
    @Property(policy = PojomaticPolicy.NONE)
    private Collection<ExpenseEntity> expense;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member", cascade = {CascadeType.REFRESH, CascadeType.REMOVE, CascadeType.MERGE})
    @Property(policy = PojomaticPolicy.NONE)
    private Collection<DeviceEntity> deviceEntities;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner", cascade = {CascadeType.REFRESH, CascadeType.REMOVE, CascadeType.MERGE})
    @Property(policy = PojomaticPolicy.NONE)
    private Collection<CategoryEntity> categories;

    public MemberEntity() {
    }

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
