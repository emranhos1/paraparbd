package com.eh.newparaparmaven.model;

public class CuSmsBookingRequestTable {
    
    private int cu_booking_request_table_id;
    private String text_body;
    private int common_user_id;
    private String sms_status;
    private String request_date;

    public int getCu_booking_request_table_id() {
        return cu_booking_request_table_id;
    }

    public void setCu_booking_request_table_id(int cu_booking_request_table_id) {
        this.cu_booking_request_table_id = cu_booking_request_table_id;
    }

    public String getText_body() {
        return text_body;
    }

    public void setText_body(String text_body) {
        this.text_body = text_body;
    }

    public int getCommon_user_id() {
        return common_user_id;
    }

    public void setCommon_user_id(int common_user_id) {
        this.common_user_id = common_user_id;
    }

    public String getSms_status() {
        return sms_status;
    }

    public void setSms_status(String sms_status) {
        this.sms_status = sms_status;
    }

    public String getRequest_date() {
        return request_date;
    }

    public void setRequest_date(String request_date) {
        this.request_date = request_date;
    }
    
    
}
