package com.eh.newparaparmaven.model;

public class RentACarTable {
    
    private int rent_e_car_table_id;
    private int car_category_id;
    private int rent_a_car_owner_id;
    private String created_date;
    private String active_or_not;

    public RentACarTable() {
    }

    public String getActive_or_not() {
        return active_or_not;
    }

    public void setActive_or_not(String active_or_not) {
        this.active_or_not = active_or_not;
    }

    public int getRent_e_car_table_id() {
        return rent_e_car_table_id;
    }

    public void setRent_e_car_table_id(int rent_e_car_table_id) {
        this.rent_e_car_table_id = rent_e_car_table_id;
    }

    public int getCar_category_id() {
        return car_category_id;
    }

    public void setCar_category_id(int car_category_id) {
        this.car_category_id = car_category_id;
    }

    public int getRent_a_car_owner_id() {
        return rent_a_car_owner_id;
    }

    public void setRent_a_car_owner_id(int rent_a_car_owner_id) {
        this.rent_a_car_owner_id = rent_a_car_owner_id;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }
}
