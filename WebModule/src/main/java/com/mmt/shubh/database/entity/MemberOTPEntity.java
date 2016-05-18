package com.mmt.shubh.database.entity;

import lombok.Getter;
import lombok.Setter;
import org.pojomatic.annotations.AutoProperty;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Subham on 18/05/16.
 */
@Entity
@Table(name = "member_otp")
@Getter
@Setter
@AutoProperty
public class MemberOTPEntity {
    private long memberEntity;
    private long deviceId;
    private int otp;
    private long generatedTime;
    private long expirationTime;
}
