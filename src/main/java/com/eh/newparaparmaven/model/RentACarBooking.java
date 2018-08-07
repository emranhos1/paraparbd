package com.eh.newparaparmaven.model;

public class RentACarBooking {
    
    private int rent_a_car_booking_table_id, common_user_id, rent_a_car_owner_id, car_category_id, origin_division_id, origin_district_id, destination_division_id, destination_district_id, discount_id, order_number;
    private String origin, destination, payment_method, travel_date, order_date, authorized_or_not, pickup_address, drop_address;
    private double total_fare, gross_fare;
    private int travel_month;

    public RentACarBooking() {
    }

    public int getTravel_month() {
        return travel_month;
    }

    public void setTravel_month(int travel_month) {
        this.travel_month = travel_month;
    }

    public int getRent_a_car_booking_table_id() {
        return rent_a_car_booking_table_id;
    }

    public void setRent_a_car_booking_table_id(int rent_a_car_booking_table_id) {
        this.rent_a_car_booking_table_id = rent_a_car_booking_table_id;
    }

    public int getCommon_user_id() {
        return common_user_id;
    }

    public void setCommon_user_id(int common_user_id) {
        this.common_user_id = common_user_id;
    }

    public int getRent_a_car_owner_id() {
        return rent_a_car_owner_id;
    }

    public void setRent_a_car_owner_id(int rent_a_car_owner_id) {
        this.rent_a_car_owner_id = rent_a_car_owner_id;
    }

    public int getCar_category_id() {
        return car_category_id;
    }

    public void setCar_category_id(int car_category_id) {
        this.car_category_id = car_category_id;
    }

    public int getOrigin_division_id() {
        return origin_division_id;
    }

    public void setOrigin_division_id(int origin_division_id) {
        this.origin_division_id = origin_division_id;
    }

    public int getOrigin_district_id() {
        return origin_district_id;
    }

    public void setOrigin_district_id(int origin_district_id) {
        this.origin_district_id = origin_district_id;
    }

    public int getDestination_division_id() {
        return destination_division_id;
    }

    public void setDestination_division_id(int destination_division_id) {
        this.destination_division_id = destination_division_id;
    }

    public int getDestination_district_id() {
        return destination_district_id;
    }

    public void setDestination_district_id(int destination_district_id) {
        this.destination_district_id = destination_district_id;
    }

    public int getDiscount_id() {
        return discount_id;
    }

    public void setDiscount_id(int discount_id) {
        this.discount_id = discount_id;
    }

    public int getOrder_number() {
        return order_number;
    }

    public void setOrder_number(int order_number) {
        this.order_number = order_number;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getTravel_date() {
        return travel_date;
    }

    public void setTravel_date(String travel_date) {
        this.travel_date = travel_date;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getAuthorized_or_not() {
        return authorized_or_not;
    }

    public void setAuthorized_or_not(String authorized_or_not) {
        this.authorized_or_not = authorized_or_not;
    }

    public String getPickup_address() {
        return pickup_address;
    }

    public void setPickup_address(String pickup_address) {
        this.pickup_address = pickup_address;
    }

    public String getDrop_address() {
        return drop_address;
    }

    public void setDrop_address(String drop_address) {
        this.drop_address = drop_address;
    }

    public double getTotal_fare() {
        return total_fare;
    }

    public void setTotal_fare(double total_fare) {
        this.total_fare = total_fare;
    }

    public double getGross_fare() {
        return gross_fare;
    }

    public void setGross_fare(double gross_fare) {
        this.gross_fare = gross_fare;
    }
    
}
