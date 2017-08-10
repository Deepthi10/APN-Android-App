package org.apnplace.utility;

import java.io.Serializable;

/**
 * Created by dlakshmi on 10/25/2016.
 */

public class Udetails  implements Serializable {

    String user_id;
    String firstname;
    String user_name;
    String role;
    String faculty_flag;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
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

    public String getFaculty_flag() {
        return faculty_flag;
    }

    public void setFaculty_flag(String faculty_flag) {
        this.faculty_flag = faculty_flag;
    }
}
