package com.eh.newparaparmaven.classes;

import com.eh.newparaparmaven.dao.DeleteQueryDao;
import java.sql.SQLException;

public class DeleteOwnerDetails {

    private static String tableName;
    private static String whereCondition;
    private static boolean deleteOwner;
    
    public static boolean deleteOwnerDetails(int maxId) {

        tableName = " rent_a_car_owner ";
        whereCondition = " Max(rent_a_car_owner_id) = '"+maxId+"' ";

        try {
            deleteOwner = DeleteQueryDao.deleteQueryWithWhereClause(tableName, whereCondition);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deleteOwner;
    }
    
    public static boolean deleteDwpOwnerDetails(int maxId) {

        tableName = " dwp_owner ";
        whereCondition = " dwp_owner_id = '"+maxId+"' ";

        try {
            deleteOwner = DeleteQueryDao.deleteQueryWithWhereClause(tableName, whereCondition);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deleteOwner;
    }
}
