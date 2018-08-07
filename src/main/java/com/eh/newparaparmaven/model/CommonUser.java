package com.eh.newparaparmaven.model;

public class CommonUser {
    private int common_user_id;
    private String first_name;
    private String last_name;
    private String gender;
    private String email;
    private String phone_no;
    private String address;
    private String device_ime_no;
    private String device_location;
    private String device_ip;
    private String created_date;
    private String photo;

    public CommonUser() {
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getCommon_user_id() {
        return common_user_id;
    }

    public void setCommon_user_id(int common_user_id) {
        this.common_user_id = common_user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDevice_ime_no() {
        return device_ime_no;
    }

    public void setDevice_ime_no(String device_ime_no) {
        this.device_ime_no = device_ime_no;
    }

    public String getDevice_location() {
        return device_location;
    }

    public void setDevice_location(String device_location) {
        this.device_location = device_location;
    }

    public String getDevice_ip() {
        return device_ip;
    }

    public void setDevice_ip(String device_ip) {
        this.device_ip = device_ip;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    
}
