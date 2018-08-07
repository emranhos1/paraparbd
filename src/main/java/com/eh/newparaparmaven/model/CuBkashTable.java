package com.eh.newparaparmaven.model;

public class CuBkashTable {
    private int bkash_table_id;
    private int common_user_id;
    private String transaction_no;
    private String transaction_date;
    private String panding_or_not;
    private int order_no;
    private String cu_bkash_sms_table_id;

    public String getCu_bkash_sms_table_id() {
        return cu_bkash_sms_table_id;
    }

    public void setCu_bkash_sms_table_id(String cu_bkash_sms_table_id) {
        this.cu_bkash_sms_table_id = cu_bkash_sms_table_id;
    }

    public int getOrder_no() {
        return order_no;
    }

    public void setOrder_no(int order_no) {
        this.order_no = order_no;
    }

    public String getPanding_or_not() {
        return panding_or_not;
    }

    public void setPanding_or_not(String panding_or_not) {
        this.panding_or_not = panding_or_not;
    }

    public int getBkash_table_id() {
        return bkash_table_id;
    }

    public void setBkash_table_id(int bkash_table_id) {
        this.bkash_table_id = bkash_table_id;
    }

    public int getCommon_user_id() {
        return common_user_id;
    }

    public void setCommon_user_id(int common_user_id) {
        this.common_user_id = common_user_id;
    }

    public String getTransaction_no() {
        return transaction_no;
    }

    public void setTransaction_no(String transaction_no) {
        this.transaction_no = transaction_no;
    }

    public String getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }
    
}
