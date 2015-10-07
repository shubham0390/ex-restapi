package com.mmt.shubh.entity;

import lombok.Getter;
import lombok.Setter;
import org.pojomatic.Pojomatic;

import javax.persistence.*;
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
public class MemberEntity extends AbstractEntity{


    @Column(name = "member_name", nullable = false)
    private String memberName;

    @Column(name = "member_email", nullable = false)
    private String memberEmail;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "display_name", nullable = false)
    private String displayName;

    @Column(name = "cover_photo_url")
    private String coverPhotoUrl;

    @Column(name = "profile_photo_url")
    private String profilePhotoUrl;

    @ManyToMany(mappedBy = "memberList", fetch = FetchType.LAZY)
    private Set<ExpenseBookEntity> expenseBook;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ExpenseEntity> expense;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "member")
    private List<DeviceEntity> deviceEntities;

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
