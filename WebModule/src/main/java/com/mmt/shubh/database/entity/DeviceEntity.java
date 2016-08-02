package com.mmt.shubh.database.entity;

import lombok.Getter;
import lombok.Setter;
import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

import javax.persistence.*;

/**
 * Created by Subham Tyagi
 * On 6/2/2015.
 * <p>
 * TODO: Add class comments
 */
@Table(name = "DEVICE")
@Entity
@Getter
@Setter
@AutoProperty
public class DeviceEntity extends AbstractEntity {

    @Column(name = "device_uuid", nullable = false)
    private String deviceUUID;

    @Column(name = "registration_token", nullable = false)
    private String registrationToken;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private UserEntity user;

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
