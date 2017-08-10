package org.apnplace.utility;

import java.io.Serializable;

/**
 * Created by dlakshmi on 10/25/2016.
 */

public class ResponseDet implements Serializable {
    Stat stat;
    Udetails udetails;
    SessionDet sessiondetails;
    DeviceDet devicedetails;

    public Stat getStat() {
        return stat;
    }

    public void setStat(Stat stat) {
        this.stat = stat;
    }

    public Udetails getUdetails() {
        return udetails;
    }

    public void setUdetails(Udetails udetails) {
        this.udetails = udetails;
    }

    public SessionDet getSessiondetails() {
        return sessiondetails;
    }

    public void setSessiondetails(SessionDet sessiondetails) {
        this.sessiondetails = sessiondetails;
    }

    public DeviceDet getDevicedetails() {
        return devicedetails;
    }

    public void setDevicedetails(DeviceDet devicedetails) {
        this.devicedetails = devicedetails;
    }
}
