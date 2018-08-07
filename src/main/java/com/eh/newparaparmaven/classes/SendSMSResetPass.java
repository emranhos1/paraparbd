package com.eh.newparaparmaven.classes;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SendSMSResetPass {

    private static String all;

//    private static final String API = "75762";
//    private static final String TEXT = "TEXT";
//    private static final String PHONE = "01670932273";
//    private static final Random random = new Random();
//    private static final int count = random.nextInt(99999);
//    private static final String COUNT = Integer.toString(count);
    
//    String balanceInquery = getGetBalance(API);
//    String oneSms = getOneToOne(API, TEXT, PHONE, COUNT, "", "");

//    public static void main(String[] args) {
    public static String sendSMS(String PHONE) {
        SendSMSResetPass sendSms = new SendSMSResetPass();
        HttpURLConnection connection = null;
        try {
            String API = "75762";
            String TEXT = "TEXT";
            Random random = new Random();
            int count = random.nextInt(99999);
            String COUNT = Integer.toString(count);
            String textBody = "Your Reset Password varify code is " + COUNT + ".\n paraparbd";
            System.out.println("phone :"+PHONE);
            URL url = new URL(sendSms.getOneToOne(API, TEXT, PHONE, textBody, "", ""));
//            URL url = new URL(sendSms.getGetBalance(API));
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setUseCaches(false);
            connection.setDoOutput(true);
//Send request
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
            all = COUNT+"~"+response;
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
            Logger.getLogger(SendSMSResetPass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
	
    public static String getGetBalance(String apiKey) {
        String balance = "https://api2.onnorokomsms.com/HttpSendSms.ashx?op=GetCurrentBalance&apiKey=";
        return balance + apiKey;
    }
}
