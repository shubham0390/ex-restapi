package com.mmt.shubh.entity;

import lombok.Getter;
import org.pojomatic.Pojomatic;

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
public class DeviceEntity extends AbstractEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "device_uuid", nullable = false)
    private String deviceUUID;

    @Column(name = "registration_token", nullable = false)
    private String registrationToken;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private MemberEntity member;

    public static class Builder {
        private DeviceEntity deviceEntity;

        public Builder() {
            deviceEntity = new DeviceEntity();
        }

        public Builder setDeviceUUID(String deviceUUID) {
            deviceEntity.deviceUUID = deviceUUID;
            return this;
        }

        public Builder setId(long id) {
            deviceEntity.id = id;
            return this;
        }

        public Builder setRegistrationToken(String registrationToken) {
            deviceEntity.registrationToken = registrationToken;
            return this;
        }

        public DeviceEntity build() {
            return deviceEntity;
        }

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
