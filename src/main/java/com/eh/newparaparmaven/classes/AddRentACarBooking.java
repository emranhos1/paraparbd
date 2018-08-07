package com.eh.newparaparmaven.classes;

import com.eh.newparaparmaven.dao.InsertQueryDao;
import com.eh.newparaparmaven.model.RentACarBooking;
import java.sql.SQLException;

public class AddRentACarBooking {

    private static String tableName;
    private static String columnName;
    private static String values;
    private static boolean addLoginDetails;
    
    public static boolean addRentACarBookingTable(RentACarBooking rentACarBooking) {

        tableName = " cu_rent_a_car_booking_table ";
        columnName = " common_user_id, rent_a_car_owner_id, car_category_id, origin_division_id, origin_district_id, destination_division_id, destination_district_id, origin, destination, total_fare, discount_id, gross_fare, payment_method, travel_date, order_date, authorized_or_not, pickup_address, drop_address, order_number ";
        values = " '"+rentACarBooking.getCommon_user_id()+"', '"+rentACarBooking.getRent_a_car_owner_id()+"', '"+rentACarBooking.getCar_category_id()+"', '"+rentACarBooking.getOrigin_division_id()+"', '"+rentACarBooking.getOrigin_district_id()+"', '"+rentACarBooking.getDestination_division_id()+"', '"+rentACarBooking.getDestination_district_id()+"', '"+rentACarBooking.getOrigin()+"','"+rentACarBooking.getDestination()+"','"+rentACarBooking.getTotal_fare()+"','"+rentACarBooking.getDiscount_id()+"','"+rentACarBooking.getGross_fare()+"','"+rentACarBooking.getPayment_method()+"', '"+rentACarBooking.getTravel_date()+"', now(), '"+rentACarBooking.getAuthorized_or_not()+"', '"+rentACarBooking.getPickup_address()+"', '"+rentACarBooking.getDrop_address()+"', '"+rentACarBooking.getOrder_number()+"' ";

        try {
            addLoginDetails = InsertQueryDao.insertQueryWithOutWhereClause(tableName, columnName, values);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addLoginDetails;
    }
}
