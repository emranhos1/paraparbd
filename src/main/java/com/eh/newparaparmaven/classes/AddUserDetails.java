package com.eh.newparaparmaven.classes;

import com.eh.newparaparmaven.dao.InsertQueryDao;
import com.eh.newparaparmaven.model.CommonUser;
import com.eh.newparaparmaven.model.DwpOwner;
import com.eh.newparaparmaven.model.RentACarOwner;
import java.sql.SQLException;

public class AddUserDetails {

    private static String tableName;
    private static String columnName;
    private static String values;
    private static boolean addOwnerDetails;
    private static boolean addDwpOwnerDetails;
    
    public static boolean addOwnerDetails(RentACarOwner rentACarOwner) throws SQLException {

        tableName = " rent_a_car_owner ";
        columnName = " first_name, last_name, email, phone_no, division_id, district_id, thana_id, address, zip_code, owner_photo, nid_no, nid_scan_copy, tin_no, tin_scan_copy, trade_lisence_no, trades_scan_copy, rent_device_emi_no, registration_date, gender ";
        values = "'" + rentACarOwner.getFirst_name() + "', '"+rentACarOwner.getLast_name()+"', '"+rentACarOwner.getEmail()+"', '"+rentACarOwner.getPhone_no()+"', '"+rentACarOwner.getDivision_id()+"', '"+rentACarOwner.getDistrict_id()+"', '"+rentACarOwner.getThana_id()+"', '"+rentACarOwner.getAddress()+"', '"+rentACarOwner.getZip_code()+"', '"+rentACarOwner.getOwner_photo()+"', '"+rentACarOwner.getNid_no()+"', '"+rentACarOwner.getNid_scan_copy()+"', '"+rentACarOwner.getTin_no()+"', '"+rentACarOwner.getTin_scan_copy()+"', '"+rentACarOwner.getTrade_lisence_no()+"', '"+rentACarOwner.getTrades_scan_copy()+"', '"+rentACarOwner.getRent_device_emi_no()+"', now(), '"+rentACarOwner.getGender()+"' ";

        try {
            addOwnerDetails = InsertQueryDao.insertQueryWithOutWhereClause(tableName, columnName, values);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return addOwnerDetails;
    }
    
    public static boolean addCommonUserDetails(CommonUser commonUser) throws SQLException {

        tableName = " common_user ";
        columnName = " first_name, last_name, email, phone_no, address, device_ime_no, device_location, device_ip, created_date, photo, gender ";
        values = "'" + commonUser.getFirst_name() + "', '"+commonUser.getLast_name()+"', '"+commonUser.getEmail()+"', '"+commonUser.getPhone_no()+"', '"+commonUser.getAddress()+"', '"+commonUser.getDevice_ime_no()+"', '"+commonUser.getDevice_location()+"', '"+commonUser.getDevice_ip()+"', now(), '"+commonUser.getPhoto()+"', '"+commonUser.getGender()+"' ";

        try {
            addOwnerDetails = InsertQueryDao.insertQueryWithOutWhereClause(tableName, columnName, values);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return addOwnerDetails;
    }
    
    public static boolean addDWPOwnerDetails(DwpOwner dwpOwner) throws SQLException {

        tableName = " dwp_owner ";
        columnName = " first_name, last_name, email, phone_no, division_id, district_id, police_station_id, address, gender, zip_code, owner_photo, nid_no, nid_scan_copy, tin_on, tin_scan_copy, trade_lisence_no, trade_lisence_scan_copy, registration_date ";
        values = "'" + dwpOwner.getFirst_name() + "', '"+dwpOwner.getLast_name()+"', '"+dwpOwner.getEmail()+"', '"+dwpOwner.getPhone_no()+"', '"+dwpOwner.getDivision_id()+"', '"+dwpOwner.getDistrict_id()+"', '"+dwpOwner.getPolice_station_id()+"', '"+dwpOwner.getAddress()+"', '"+dwpOwner.getGender()+"', '"+dwpOwner.getZip_code()+"', '"+dwpOwner.getOwner_photo()+"', '"+dwpOwner.getNid_no()+"', '"+dwpOwner.getNid_scan_copy()+"', '"+dwpOwner.getTin_on()+"', '"+dwpOwner.getTin_scan_copy()+"', '"+dwpOwner.getTrade_lisence_no()+"', '"+dwpOwner.getTrade_lisence_scan_copy()+"', now() ";

        try {
            addDwpOwnerDetails = InsertQueryDao.insertQueryWithOutWhereClause(tableName, columnName, values);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return addDwpOwnerDetails;
    }
}
