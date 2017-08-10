package org.apnplace.utility;

import java.io.Serializable;

/**
 * Created by dlakshmi on 10/25/2016.
 */

public class SessionDet implements Serializable {
    String id;
    String device;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }
}
