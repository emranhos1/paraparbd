package com.eh.newparaparmaven.classes;

import com.eh.newparaparmaven.dbConnection.conRs;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SendSMSForBooking {

    private static conRs GetPhoneNo;
    private static Connection con;
    private static ResultSet rs;
    private static PreparedStatement pstm;
    private static String phoneNo;
    private static String all;
    private static String data;

    public static String sendSMSBooking(int commonUserId, int orderNo) {
        try {
            GetPhoneNo = GetCommonUserPhoneNo.getPhoneNo(commonUserId);
            con = GetPhoneNo.getCon();
            rs = GetPhoneNo.getRs();
            pstm = GetPhoneNo.getPstm();
            while (rs.next()) {
                phoneNo = rs.getString("phone_no");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SendSMSForBooking.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                rs.close();
                pstm.close();
            } catch (SQLException ex) {
                Logger.getLogger(SendSMSForBooking.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        data = sendSMS(phoneNo, orderNo);
        return data;
    }

    public static String sendSMS(String PHONE, int orderNo) {
        HttpURLConnection connection = null;
        try {
            String API = "75762";
            String TEXT = "TEXT";
            String textBody = "Your Order No " + orderNo + ",Your Booking Request Sent Successfully Please Wait For Call. \n parapar  \n Hot Line: 09617 111 999";
            System.out.println("phone :" + PHONE);
            URL url = new URL(getOneToOne(API, TEXT, PHONE, textBody, "", ""));
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setUseCaches(false);
            connection.setDoOutput(true);
//            Send request
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.close();

            //Get Response  
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            all = textBody + "~" + response;
            System.out.println(all);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return all;
    }

    public static String getOneToOne(String apiKey, String type, String mobile, String smsText, String maskName, String campaignName) {

        try {
            String oneByone = "https://api2.onnorokomsms.com/HttpSendSms.ashx?op=NumberSms&apiKey=";
            String smsType = "&type=";
            String reciverNumber = "&mobile=";
            String smsBody = "&smsText=";
            String smsMask = "&maskName=";
            String smscampaign = "&campaignName=";
            return oneByone + apiKey + smsType + type + reciverNumber + mobile + smsBody + URLEncoder.encode(smsText, "UTF-8").replace("\\+", "%20") + smsMask + maskName + smscampaign + campaignName;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(SendSMS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String getGetBalance(String apiKey) {
        String balance = "https://api2.onnorokomsms.com/HttpSendSms.ashx?op=GetCurrentBalance&apiKey=";
        return balance + apiKey;
    }
}
