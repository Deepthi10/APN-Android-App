package org.apnplace.utility;

import java.io.Serializable;

/**
 * Created by dlakshmi on 10/25/2016.
 */

public class Stat  implements Serializable {
    String status;
    String msg;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
