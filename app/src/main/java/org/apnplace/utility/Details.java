package org.apnplace.utility;

import java.io.Serializable;

/**
 * Created by dlakshmi on 10/24/2016.
 */

public class Details implements Serializable {
 String user_name,role,firstname,lastname,middlename,email_id,edulevel,address,certification,zipsetting,psetting,currentjob,contact;

    public Details() {

    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getEdulevel() {
        return edulevel;
    }

    public void setEdulevel(String edulevel) {
        this.edulevel = edulevel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public String getZipsetting() {
        return zipsetting;
    }

    public void setZipsetting(String zipsetting) {
        this.zipsetting = zipsetting;
    }

    public String getPsetting() {
        return psetting;
    }

    public void setPsetting(String psetting) {
        this.psetting = psetting;
    }

    public String getCurrentjob() {
        return currentjob;
    }

    public void setCurrentjob(String currentjob) {
        this.currentjob = currentjob;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
