/*
 * Copyright (c) 2016. . The Km2Labs Project
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 */

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
