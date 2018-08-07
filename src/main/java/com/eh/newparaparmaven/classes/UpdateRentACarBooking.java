package com.eh.newparaparmaven.classes;

import com.eh.newparaparmaven.dao.UpdateQueryDao;
import com.eh.newparaparmaven.model.RentACarBooking;
import java.sql.SQLException;

public class UpdateRentACarBooking {

    private static String tableName;
    private static String columnNameANDcolumnValue;
    private static String whereCondition;
    private static boolean updateRentACarBookingTable;
    
    public static boolean updateRentACarBooking(RentACarBooking rentACarBooking) throws SQLException {

        tableName = " cu_rent_a_car_booking_table ";
        columnNameANDcolumnValue = " rent_a_car_owner_id = '"+rentACarBooking.getRent_a_car_owner_id()+"', authorized_or_not = '1' ";
        whereCondition = " rent_a_car_booking_table_id = '"+rentACarBooking.getRent_a_car_booking_table_id()+"' ";

        try {
            updateRentACarBookingTable = UpdateQueryDao.updateQueryWithWhereClause(tableName, columnNameANDcolumnValue, whereCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateRentACarBookingTable;
    }
}
