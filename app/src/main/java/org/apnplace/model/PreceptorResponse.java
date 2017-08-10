package org.apnplace.model;

import org.apnplace.utility.Details;

/**
 * Created by dlakshmi on 10/25/2016.
 */

public class PreceptorResponse {

     String status;
    Details userdetails;

    public PreceptorResponse() {

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Details getUserdetails() {
        return userdetails;
    }

    public void setUserdetails(Details userdetails) {
        this.userdetails = userdetails;
    }
}
