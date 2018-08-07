package com.eh.newparaparmaven.model;

public class CarCategory {
    private int car_category_id;
    private int brand_id;
    private int car_model_id;
    private String car_sit;
    private int color_id;
    private String car_registration_no;
    private String tex_token_no;
    private String car_fitness_no;
    private String car_registration_scan_copy;
    private int rent_a_car_owner_id;

    public CarCategory() {
    }

    public String getTex_token_no() {
        return tex_token_no;
    }

    public void setTex_token_no(String tex_token_no) {
        this.tex_token_no = tex_token_no;
    }

    public String getCar_fitness_no() {
        return car_fitness_no;
    }

    public void setCar_fitness_no(String car_fitness_no) {
        this.car_fitness_no = car_fitness_no;
    }

    public String getCar_sit() {
        return car_sit;
    }

    public void setCar_sit(String car_sit) {
        this.car_sit = car_sit;
    }

    public int getCar_category_id() {
        return car_category_id;
    }

    public void setCar_category_id(int car_category_id) {
        this.car_category_id = car_category_id;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }

    public int getCar_model_id() {
        return car_model_id;
    }

    public void setCar_model_id(int car_model_id) {
        this.car_model_id = car_model_id;
    }

    public int getColor_id() {
        return color_id;
    }

    public void setColor_id(int color_id) {
        this.color_id = color_id;
    }

    public String getCar_registration_no() {
        return car_registration_no;
    }

    public void setCar_registration_no(String car_registration_no) {
        this.car_registration_no = car_registration_no;
    }

    public String getCar_registration_scan_copy() {
        return car_registration_scan_copy;
    }

    public void setCar_registration_scan_copy(String car_registration_scan_copy) {
        this.car_registration_scan_copy = car_registration_scan_copy;
    }

    public int getRent_a_car_owner_id() {
        return rent_a_car_owner_id;
    }

    public void setRent_a_car_owner_id(int rent_a_car_owner_id) {
        this.rent_a_car_owner_id = rent_a_car_owner_id;
    }
    
}
