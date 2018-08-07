package com.eh.newparaparmaven.classes;

import com.eh.newparaparmaven.dao.DeleteQueryDao;
import java.sql.SQLException;

public class DeleteCommonUserDetails {

    private static String tableName;
    private static String whereCondition;
    private static boolean deleteCommonUser;
    
    public static boolean deleteCommonUserDetails(int maxId) {

        tableName = " common_user ";
        whereCondition = " Max(common_user_id) = '"+maxId+"' ";

        try {
            deleteCommonUser = DeleteQueryDao.deleteQueryWithWhereClause(tableName, whereCondition);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deleteCommonUser;
    }
}
