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

    @Column(name = "user_email", unique = true)
    private String email;

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "cover_photo_url")
    private String coverPhotoUrl;

    @Column(name = "profile_photo_url")
    private String profilePhotoUrl;

    @Column(name = "status")
    private String status;


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
