package org.apnplace.utility;

import java.io.Serializable;

/**
 * Created by dlakshmi on 10/25/2016.
 */

public class DeviceDet implements Serializable {
    String dtype;
    String did;

    public String getDtype() {
        return dtype;
    }

    public void setDtype(String dtype) {
        this.dtype = dtype;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }
}
