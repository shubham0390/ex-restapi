package com.km2labs.expenseview.rest.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Getter
@Setter
public class LoginStatus {
    int type;
    String accessToken;
    String loginStatus;

}
