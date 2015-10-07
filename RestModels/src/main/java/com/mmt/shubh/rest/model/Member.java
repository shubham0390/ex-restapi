package com.mmt.shubh.rest.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Subham Tyagi
 * On 6/10/2015.
 * <p>
 * TODO: Add class comments
 */
@XmlRootElement
@Getter
@Setter
public class Member {

    private long id;

    private String memberName;

    private String memberEmail;

    private String userPassword;

    private String displayName;

    private String coverPhotoUrl;

    private String profilePhotoUrl;
}
