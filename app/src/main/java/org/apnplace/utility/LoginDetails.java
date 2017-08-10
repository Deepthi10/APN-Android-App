package org.apnplace.utility;

import java.io.Serializable;


public class LoginDetails implements Serializable {


        public static class Stat {
            public String status;
            public String msg;

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

        public static class Udetails {
            public String user_id;
            public String firstname;
            public String user_name;
            public String role;
            public String faculty_flag;

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

        public static class Sessiondetails {
            public String id;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }

        public static class Devicedetails {
            public String dtype;
            public String did;

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

    }




