package com.mmt.shubh.rest;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by subhamtyagi on 4/2/16.
 */
@XmlRootElement
@Getter
@Setter
public class ApiResult<T> {

    boolean status;
    T data;

}
