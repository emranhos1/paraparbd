package com.eh.newparaparmaven.classes;

import com.eh.newparaparmaven.dao.SelectQueryDao;
import com.eh.newparaparmaven.dbConnection.conRs;
import com.eh.newparaparmaven.model.RentACarBooking;
import java.sql.SQLException;

public class GetRentACarBooking {

    private static String columnName;
    private static String tableName;
    private static conRs conrs;
    private static String onCondition;
    private static String whereCondition;
    
    public static conRs getRentACarBookingTable() throws SQLException {

        columnName = " cbt.transaction_no, cracbt.order_number, cracbt.rent_a_car_booking_table_id, cu.common_user_id, cu.first_name, cu.last_name, cu.email, cu.phone_no, cracbt.origin, cb.brand_name, cm.model_name, c.color_name, cracbt.destination, cracbt.pickup_address, cracbt.drop_address, cracbt.total_fare, cracbt.discount_id, cracbt.gross_fare, cracbt.payment_method, cracbt.travel_date, cracbt.order_date ";
        tableName = " cu_rent_a_car_booking_table cracbt left Join common_user cu ";
        onCondition = " (cracbt.common_user_id = cu.common_user_id) left join cu_bkash_table cbt on (cracbt.order_number = cbt.order_no) left join car_category cc on(cracbt.car_category_id = cc.car_category_id) left join car_brand cb on(cc.brand_id = cb.car_brand_id) left join car_model cm on (cc.car_model_id = cm.car_model_id) left join color c on (cc.color_id = c.color_id) ";
        whereCondition = " cracbt.authorized_or_not = '0' ";

        try {
            conrs = SelectQueryDao.selectQueryWithJoinWhere(columnName, tableName, onCondition, whereCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
    public static conRs getAuthorizedRequest() throws SQLException {

        columnName = " cracbt.rent_a_car_booking_table_id, cu.common_user_id, cu.first_name, cu.last_name, cu.email, cu.phone_no, cracbt.origin, cb.brand_name, cm.model_name, c.color_name, cracbt.destination, cracbt.pickup_address, cracbt.drop_address, cracbt.total_fare, cracbt.discount_id, cracbt.gross_fare, cracbt.payment_method, cracbt.travel_date, cracbt.order_date ";
        tableName = " cu_rent_a_car_booking_table cracbt left Join common_user cu ";
        onCondition = " (cracbt.common_user_id = cu.common_user_id) left join car_category cc on (cc.car_category_id = cracbt.car_category_id) left join car_brand cb on (cc.brand_id = cb.car_brand_id) left join car_model cm on (cc.car_model_id = cm.car_model_id) left join color c on (cc.color_id = c.color_id) ";
        whereCondition = " authorized_or_not = '1' ";

        try {
            conrs = SelectQueryDao.selectQueryWithJoinWhere(columnName, tableName, onCondition, whereCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
//    
    public static conRs getRentedCar(RentACarBooking rentACarBooking) throws SQLException {

        columnName = " cracbt.origin, cracbt.destination, cracbt.total_fare, cracbt.discount_id, cracbt.gross_fare, cracbt.payment_method, cracbt.travel_date, cracbt.order_date, cb.brand_name, cm.model_name, c.color_name, cracbt.pickup_address, cracbt.drop_address, cu.first_name, cu.last_name,cu.phone_no ";
        tableName = " cu_rent_a_car_booking_table cracbt left join common_user cu ";
        onCondition = " (cracbt.common_user_id = cu.common_user_id) left join car_category cc on (cc.car_category_id = cracbt.car_category_id) left join car_brand cb on (cc.brand_id = cb.car_brand_id) left join car_model cm on (cc.car_model_id = cm.car_model_id) left join color c on (cc.color_id = c.color_id) ";
        whereCondition = " cracbt.rent_a_car_owner_id ='"+rentACarBooking.getRent_a_car_owner_id()+"' ";

        try {
            conrs = SelectQueryDao.selectQueryWithJoinWhere(columnName, tableName, onCondition, whereCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
//    
    public static conRs getCommonUserHistory(int commonUserId) throws SQLException {

        columnName = " cracbt.common_user_id, raco.first_name, raco.last_name, raco.phone_no, cracbt.origin, cracbt.destination, cracbt.total_fare, cracbt.payment_method, cracbt.travel_date, cracbt.order_date, cracbt.discount_id, cracbt.gross_fare ";
        tableName = " cu_rent_a_car_booking_table cracbt left join rent_a_car_owner raco ";
        onCondition = " (cracbt.rent_a_car_owner_id = raco.rent_a_car_owner_id) ";
        whereCondition = " not cracbt.rent_a_car_owner_id = '0' and cracbt.common_user_id ='"+commonUserId+"' ";

        try {
            conrs = SelectQueryDao.selectQueryWithJoinWhere(columnName, tableName, onCondition, whereCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
    
    public static conRs getCurrentMonthRentACarBooking(RentACarBooking rentACarBooking) throws SQLException {

        columnName = " cracbt.rent_a_car_booking_table_id, cu.common_user_id, cu.first_name, cu.last_name, cu.email, cu.phone_no, cb.brand_name, cm.model_name, c.color_name, cracbt.origin, cracbt.destination, cracbt.total_fare, cracbt.discount_id, cracbt.gross_fare, cracbt.payment_method, cracbt.travel_date, cracbt.order_date, cracbt.pickup_address, cracbt.drop_address ";
        tableName = " cu_rent_a_car_booking_table cracbt left Join common_user cu ";
        onCondition = " (cracbt.common_user_id = cu.common_user_id) left join car_category cc on (cc.car_category_id = cracbt.car_category_id) left join car_brand cb on (cc.brand_id = cb.car_brand_id) left join car_model cm on (cc.car_model_id = cm.car_model_id) left join color c on (cc.color_id = c.color_id) ";
        whereCondition = " EXTRACT(MONTH FROM cracbt.travel_date) = '"+rentACarBooking.getTravel_month()+"' and cracbt.rent_a_car_owner_id = '"+rentACarBooking.getRent_a_car_owner_id()+"' ";
        try {
            conrs = SelectQueryDao.selectQueryWithJoinWhere(columnName, tableName, onCondition, whereCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
    
    public static conRs getCurrentMonthDWPBooking(RentACarBooking rentACarBooking) throws SQLException {

        columnName = " cu.first_name cuFistName, cu.last_name cuLastName, raco.first_name rentFirstName, raco.last_name rentLastName, cb.brand_name, cm.model_name, c.color_name, cracbt.origin_division_id, cracbt.origin_district_id, cracbt.destination_division_id, cracbt.destination_district_id, cracbt.origin, cracbt.destination, cracbt.total_fare, cracbt.discount_id, cracbt.gross_fare, cracbt.payment_method, cracbt.travel_date, cracbt.order_date, cracbt.authorized_or_not, cracbt.pickup_address, cracbt.drop_address, cracbt.order_number ";
        tableName = " cu_rent_a_car_booking_table cracbt left Join common_user cu ";
        onCondition = " (cracbt.common_user_id = cu.common_user_id) left join rent_a_car_owner raco on (cracbt.rent_a_car_owner_id = raco.rent_a_car_owner_id) left join car_category cc on (cc.car_category_id = cracbt.car_category_id) left join car_brand cb on (cc.brand_id = cb.car_brand_id) left join car_model cm on (cc.car_model_id = cm.car_model_id) left join color c on (cc.color_id = c.color_id) ";
        whereCondition = " EXTRACT(MONTH FROM cracbt.travel_date) = '"+rentACarBooking.getTravel_month()+"' and cracbt.origin_district_id = '"+rentACarBooking.getOrigin_district_id()+"' ";
        try {
            conrs = SelectQueryDao.selectQueryWithJoinWhere(columnName, tableName, onCondition, whereCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
    
    public static conRs getCurrentMonthCommissionDWP(RentACarBooking rentACarBooking) throws SQLException {

        columnName = " cu.first_name cuFistName, cu.last_name cuLastName, raco.first_name rentFirstName, raco.last_name rentLastName, cb.brand_name, cm.model_name, c.color_name, cracbt.origin_division_id, cracbt.origin_district_id, cracbt.destination_division_id, cracbt.destination_district_id, cracbt.origin, cracbt.destination, cracbt.total_fare, cracbt.discount_id, cracbt.gross_fare, cracbt.payment_method, cracbt.travel_date, cracbt.order_date, cracbt.authorized_or_not, cracbt.pickup_address, cracbt.drop_address, cracbt.order_number ";
        tableName = " cu_rent_a_car_booking_table cracbt left Join common_user cu ";
        onCondition = " (cracbt.common_user_id = cu.common_user_id) left join rent_a_car_owner raco on (cracbt.rent_a_car_owner_id = raco.rent_a_car_owner_id) left join car_category cc on (cc.car_category_id = cracbt.car_category_id) left join car_brand cb on (cc.brand_id = cb.car_brand_id) left join car_model cm on (cc.car_model_id = cm.car_model_id) left join color c on (cc.color_id = c.color_id) ";
        whereCondition = " EXTRACT(MONTH FROM cracbt.travel_date) = '"+rentACarBooking.getTravel_month()+"' and cracbt.origin_district_id = '"+rentACarBooking.getOrigin_district_id()+"' ";
        try {
            conrs = SelectQueryDao.selectQueryWithJoinWhere(columnName, tableName, onCondition, whereCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
    
    public static conRs getOrderNumberRentACarBooking() throws SQLException {

        columnName = " order_number ";
        tableName = " cu_rent_a_car_booking_table ";
        try {
            conrs = SelectQueryDao.selectQueryWithOutWhereClause(columnName, tableName);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
    
    public static conRs getOrderNumberRentACarBooking(int rentacarbookingtableid) throws SQLException {

        columnName = " order_number ";
        tableName = " cu_rent_a_car_booking_table ";
        whereCondition = "rent_a_car_booking_table_id = '"+rentacarbookingtableid+"'";
        try {
            conrs = SelectQueryDao.selectQueryWithWhereClause(columnName, tableName, whereCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
    
    public static conRs getConfirmBooked(int commonUserId) throws SQLException {

        columnName = " cracbt.rent_a_car_booking_table_id, cracbt.common_user_id, raco.first_name, raco.last_name, cb.brand_name, cm.model_name, c.color_name, cracbt.origin, cracbt.destination, cracbt.total_fare, cracbt.discount_id, cracbt.gross_fare, cracbt.payment_method, cracbt.travel_date, cracbt.order_date, cracbt.authorized_or_not, cracbt.pickup_address, cracbt.drop_address, cracbt.order_number ";
        tableName = " cu_rent_a_car_booking_table cracbt left join rent_a_car_owner raco ";
        onCondition = " (cracbt.rent_a_car_owner_id = raco.rent_a_car_owner_id) left join car_category cc on (cc.car_category_id = cracbt.car_category_id) left join car_brand cb on (cc.brand_id = cb.car_brand_id) left join car_model cm on (cc.car_model_id = cm.car_model_id) left join color c on (cc.color_id = c.color_id) ";
        whereCondition = "cracbt.common_user_id = '"+commonUserId+"' and cracbt.rent_a_car_owner_id != 0";
        try {
            conrs = SelectQueryDao.selectQueryWithJoinWhere(columnName, tableName, onCondition, whereCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
}
