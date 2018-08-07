package com.eh.newparaparmaven.classes;

import com.eh.newparaparmaven.dao.UpdateQueryDao;
import com.eh.newparaparmaven.model.AllUser;
import java.sql.SQLException;

public class UpdateRentECar {

    private static String tableName;
    private static String columnNameANDcolumnValue;
    private static String whereCondition;
    private static boolean updateRentACar;
    
    public static boolean activeRentACar(AllUser allUser) throws SQLException {

        tableName = " all_user ";
        columnNameANDcolumnValue = " active_status = '1', active_date =  now() ";
        whereCondition = " all_user_id = '"+allUser.getAll_user_id()+"' ";

        try {
            updateRentACar = UpdateQueryDao.updateQueryWithWhereClause(tableName, columnNameANDcolumnValue, whereCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateRentACar;
    }
    
    public static boolean inActiveRentACar(AllUser allUser) throws SQLException {

        tableName = " all_user ";
        columnNameANDcolumnValue = " active_status = '0', inactive_date =  now() ";
        whereCondition = " all_user_id = '"+allUser.getAll_user_id()+"' ";

        try {
            updateRentACar = UpdateQueryDao.updateQueryWithWhereClause(tableName, columnNameANDcolumnValue, whereCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateRentACar;
    }
}
