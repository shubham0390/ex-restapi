package com.mmt.shubh.rest.response;

import com.mmt.shubh.rest.model.Member;
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
public abstract class ServiceResponse<T> extends Member {

    protected boolean success;

    protected int status;

    protected T payload;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }
}
