package com.mmt.shubh.database.entity;

import lombok.Getter;
import lombok.Setter;
import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;
import org.pojomatic.annotations.PojomaticPolicy;
import org.pojomatic.annotations.Property;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Table(name = "user")
@Entity
@Getter
@Setter
@AutoProperty
@SequenceGenerator(name = "user_seq", sequenceName = "user_seq", initialValue = 100, allocationSize = 1)
public class UserEntity implements Serializable {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @Column(name = "id")
    @Id
    private long id;

    @Column(name = "user_name", nullable = false)
    private String name;

    @Column(name = "user_email", nullable = false, unique = true)
    private String email;

    @Column(name = "cover_photo_url")
    private String coverPhotoUrl;

    @Column(name = "profile_photo_url")
    private String profilePhotoUrl;

    @Column(name = "status")
    private String status;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = {CascadeType.REFRESH, CascadeType.REMOVE, CascadeType.MERGE})
    @Property(policy = PojomaticPolicy.NONE)
    private Set<DeviceEntity> deviceEntities;

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
