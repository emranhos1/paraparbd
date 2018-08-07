package com.eh.newparaparmaven.model;

public class LogginHistoryTable {
    private int loggin_history_table_id;
    private int all_user_id;
    private String user_device_mac;
    private String user_device_ip;
    private String logging_date_time;
    private String logout_date_time;

    public LogginHistoryTable() {
    }

    public int getLoggin_history_table_id() {
        return loggin_history_table_id;
    }

    public void setLoggin_history_table_id(int loggin_history_table_id) {
        this.loggin_history_table_id = loggin_history_table_id;
    }

    public int getAll_user_id() {
        return all_user_id;
    }

    public void setAll_user_id(int all_user_id) {
        this.all_user_id = all_user_id;
    }

    public String getUser_device_mac() {
        return user_device_mac;
    }

    public void setUser_device_mac(String user_device_mac) {
        this.user_device_mac = user_device_mac;
    }

    public String getUser_device_ip() {
        return user_device_ip;
    }

    public void setUser_device_ip(String user_device_ip) {
        this.user_device_ip = user_device_ip;
    }

    public String getLogging_date_time() {
        return logging_date_time;
    }

    public void setLogging_date_time(String logging_date_time) {
        this.logging_date_time = logging_date_time;
    }

    public String getLogout_date_time() {
        return logout_date_time;
    }

    public void setLogout_date_time(String logout_date_time) {
        this.logout_date_time = logout_date_time;
    }
    
}
