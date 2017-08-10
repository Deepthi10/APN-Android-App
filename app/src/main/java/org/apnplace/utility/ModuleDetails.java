package org.apnplace.utility;

import java.io.Serializable;

/**
 * Created by dlakshmi on 10/27/2016.
 */

public class ModuleDetails implements Serializable {
    public String moduleid,modulename,unlock_date,moduledesc,filedetails,icon_img,available;

    public String getModuleid() {
        return moduleid;
    }

    public void setModuleid(String moduleid) {
        this.moduleid = moduleid;
    }

    public String getModulename() {
        return modulename;
    }

    public void setModulename(String modulename) {
        this.modulename = modulename;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getIcon_img() {
        return icon_img;
    }

    public void setIcon_img(String icon_img) {
        this.icon_img = icon_img;
    }

    public String getFiledetails() {
        return filedetails;
    }

    public void setFiledetails(String filedetails) {
        this.filedetails = filedetails;
    }

    public String getModuledesc() {
        return moduledesc;
    }

    public void setModuledesc(String moduledesc) {
        this.moduledesc = moduledesc;
    }

    public String getUnlock_date() {
        return unlock_date;
    }

    public void setUnlock_date(String unlock_date) {
        this.unlock_date = unlock_date;
    }
}
