package com.km2labs.expenseview.rest.resources.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.pojomatic.annotations.AutoProperty;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Subham on 18/05/16.
 */
@XmlRootElement
@AutoProperty
@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {
    protected long deviceId;
}
