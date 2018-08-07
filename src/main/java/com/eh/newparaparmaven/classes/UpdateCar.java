package com.eh.newparaparmaven.classes;

import com.eh.newparaparmaven.dao.UpdateQueryDao;
import com.eh.newparaparmaven.model.RentACarOwner;
import java.sql.SQLException;

public class UpdateCar {

    private static String tableName;
    private static String columnNameANDcolumnValue;
    private static String whereCondition;
    private static boolean updateCar;
    
    public static boolean activeCar(RentACarOwner rentACarOwner) throws SQLException {

        tableName = " rent_a_car_table ";
        columnNameANDcolumnValue = " active_or_not = '1', active_date =  now() ";
        whereCondition = " rent_e_car_table_id = '"+rentACarOwner.getRent_a_car_owner_id()+"' ";

        try {
            updateCar = UpdateQueryDao.updateQueryWithWhereClause(tableName, columnNameANDcolumnValue, whereCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateCar;
    }
    
    public static boolean inActiveCar(RentACarOwner rentACarOwner) throws SQLException {

        tableName = " rent_a_car_table ";
        columnNameANDcolumnValue = " active_or_not = '0', active_date =  now() ";
        whereCondition = " rent_e_car_table_id = '"+rentACarOwner.getRent_a_car_owner_id()+"' ";

        try {
            updateCar = UpdateQueryDao.updateQueryWithWhereClause(tableName, columnNameANDcolumnValue, whereCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateCar;
    }
}
