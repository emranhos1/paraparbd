package com.eh.newparaparmaven.model;

public class CuFareRateTable {
    private int cu_fare_rate_table_id;
    private String par_km_rate;
    private String bodyLess200km;
    private String bodyBig200km;
    private String driver_cost;
    private String other_cost;
    private String update_date;
    private String active_status;

    public CuFareRateTable() {
    }

    public int getCu_fare_rate_table_id() {
        return cu_fare_rate_table_id;
    }

    public void setCu_fare_rate_table_id(int cu_fare_rate_table_id) {
        this.cu_fare_rate_table_id = cu_fare_rate_table_id;
    }

    public String getPar_km_rate() {
        return par_km_rate;
    }

    public void setPar_km_rate(String par_km_rate) {
        this.par_km_rate = par_km_rate;
    }

    public String getBodyLess200km() {
        return bodyLess200km;
    }

    public void setBodyLess200km(String bodyLess200km) {
        this.bodyLess200km = bodyLess200km;
    }

    public String getBodyBig200km() {
        return bodyBig200km;
    }

    public void setBodyBig200km(String bodyBig200km) {
        this.bodyBig200km = bodyBig200km;
    }

    public String getDriver_cost() {
        return driver_cost;
    }

    public void setDriver_cost(String driver_cost) {
        this.driver_cost = driver_cost;
    }

    public String getOther_cost() {
        return other_cost;
    }

    public void setOther_cost(String other_cost) {
        this.other_cost = other_cost;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }

    public String getActive_status() {
        return active_status;
    }

    public void setActive_status(String active_status) {
        this.active_status = active_status;
    }
    
}
