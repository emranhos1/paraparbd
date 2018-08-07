package com.eh.newparaparmaven.model;

import java.sql.Date;

public class AllUser {
    private int all_user_id;
    private int user_id;
    private String user_role;
    private int active_status;
    private Date active_date;
    private Date inactive_date;

    public AllUser() {
    }

    public int getAll_user_id() {
        return all_user_id;
    }

    public void setAll_user_id(int all_user_id) {
        this.all_user_id = all_user_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }

    public int getActive_status() {
        return active_status;
    }

    public void setActive_status(int active_status) {
        this.active_status = active_status;
    }

    public Date getActive_date() {
        return active_date;
    }

    public void setActive_date(Date active_date) {
        this.active_date = active_date;
    }

    public Date getInactive_date() {
        return inactive_date;
    }

    public void setInactive_date(Date inactive_date) {
        this.inactive_date = inactive_date;
    }

    
}
