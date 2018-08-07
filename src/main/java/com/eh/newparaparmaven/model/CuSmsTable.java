package com.eh.newparaparmaven.model;

public class CuSmsTable {
    private int cu_sms_table_id;
    private int all_user_id;
    private String sms_status;
    private String mobile_no;
    private String response_code;
    private String random_genarated_code;

    public CuSmsTable() {
    }

    public int getCu_sms_table_id() {
        return cu_sms_table_id;
    }

    public void setCu_sms_table_id(int cu_sms_table_id) {
        this.cu_sms_table_id = cu_sms_table_id;
    }

    public int getAll_user_id() {
        return all_user_id;
    }

    public void setAll_user_id(int all_user_id) {
        this.all_user_id = all_user_id;
    }

    public String getSms_status() {
        return sms_status;
    }

    public void setSms_status(String sms_status) {
        this.sms_status = sms_status;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getResponse_code() {
        return response_code;
    }

    public void setResponse_code(String response_code) {
        this.response_code = response_code;
    }

    public String getRandom_genarated_code() {
        return random_genarated_code;
    }

    public void setRandom_genarated_code(String random_genarated_code) {
        this.random_genarated_code = random_genarated_code;
    }
}
