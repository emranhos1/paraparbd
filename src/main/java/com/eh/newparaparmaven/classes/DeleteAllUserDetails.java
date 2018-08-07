package com.eh.newparaparmaven.classes;

import com.eh.newparaparmaven.dao.DeleteQueryDao;
import java.sql.SQLException;

public class DeleteAllUserDetails {

    private static String tableName;
    private static String whereCondition;
    private static boolean deleteAllUser;
    
    public static boolean deleteAllUserDetails(int maxId) {

        tableName = " all_user ";
        whereCondition = " all_user_id = '"+maxId+"' ";

        try {
            deleteAllUser = DeleteQueryDao.deleteQueryWithWhereClause(tableName, whereCondition);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deleteAllUser;
    }
}
