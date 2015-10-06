package com.mmt.shubh.rest.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Subham Tyagi
 * On 3/24/2015.
 * <p>
 * TODO: Add class comments
 */

@Getter
@Setter
@Data
@XmlRootElement
public abstract class ServiceResponse {
    protected boolean sucsess;
    protected int status;
    protected Object payload;

    public boolean isSucsess() {
        return sucsess;
    }

    public void setSucsess(boolean sucsess) {
        this.sucsess = sucsess;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }
}
