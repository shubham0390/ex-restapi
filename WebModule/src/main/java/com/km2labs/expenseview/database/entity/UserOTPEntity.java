package com.km2labs.expenseview.database.entity;

import lombok.Getter;
import lombok.Setter;
import org.pojomatic.annotations.AutoProperty;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "member_otp")
@Getter
@Setter
@AutoProperty
@SequenceGenerator(name = "otp_seq", sequenceName = "otp_seq", initialValue = 100, allocationSize = 1)
public class UserOTPEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "otp_seq")
    @Column(name = "id")
    private long id;

    @Column(name = "otp")
    private int otp;
    @Column(name = "generated_time")
    private long generatedTime;
    @Column(name = "expiration_time")
    private long expirationTime;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private UserEntity userEntity;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private DeviceEntity deviceEntity;
}
