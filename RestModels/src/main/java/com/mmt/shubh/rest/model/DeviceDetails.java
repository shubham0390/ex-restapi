package com.mmt.shubh.rest.model;

import lombok.Getter;
import lombok.Setter;
import org.pojomatic.annotations.AutoProperty;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Subham Tyagi
 * On 6/5/2015.
 * <p>
 * TODO: Add class comments
 */
@XmlRootElement
@AutoProperty
@Getter
public class DeviceDetails {

    private long id;

    private String deviceUUID;

    private String gcmToken;

    public Builder buildUpon() {
        return new Builder(this);
    }

    public static class Builder {

        private DeviceDetails deviceDetails;

        public Builder() {
            this.deviceDetails = new DeviceDetails();
        }

        private Builder(DeviceDetails deviceDetails) {
            this.deviceDetails = deviceDetails;
        }

        public void setDeviceUUID(String deviceUUID) {
            deviceDetails.deviceUUID = deviceUUID;
        }

        public void setGcmToken(String gcmToken) {
            deviceDetails.gcmToken = gcmToken;
        }

        public void setId(long id) {
            deviceDetails.id = id;
        }

        public DeviceDetails build() {

            return deviceDetails;
        }
    }
}
