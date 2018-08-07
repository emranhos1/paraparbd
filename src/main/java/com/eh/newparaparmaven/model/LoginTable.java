package com.eh.newparaparmaven.model;

public class LoginTable {
    private int login_table_id;
    private int all_user_id;
    private String phone_no;
    private String password;

    public LoginTable() {
    }

    public int getLogin_table_id() {
        return login_table_id;
    }

    public void setLogin_table_id(int login_table_id) {
        this.login_table_id = login_table_id;
    }

    public int getAll_user_id() {
        return all_user_id;
    }

    public void setAll_user_id(int all_user_id) {
        this.all_user_id = all_user_id;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
