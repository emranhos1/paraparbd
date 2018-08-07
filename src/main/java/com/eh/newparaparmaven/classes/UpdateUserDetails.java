package com.eh.newparaparmaven.classes;

import com.eh.newparaparmaven.dao.UpdateQueryDao;
import com.eh.newparaparmaven.model.CommonUser;
import java.sql.SQLException;

public class UpdateUserDetails {

    private static String tableName;
    private static String columnNameANDcolumnValue;
    private static String whereCondition;
    private static boolean updateCommonUser;
    
    public static boolean updateCommonUserDetails(CommonUser commonUser) throws SQLException {

        tableName = " common_user ";
        columnNameANDcolumnValue = " first_name = '"+commonUser.getFirst_name()+"', last_name = '"+commonUser.getLast_name()+"', email = '"+commonUser.getEmail()+"', address = '"+commonUser.getAddress()+"', updated_date = now() ";
        whereCondition = " common_user_id = '"+commonUser.getCommon_user_id()+"' ";

        try {
            updateCommonUser = UpdateQueryDao.updateQueryWithWhereClause(tableName, columnNameANDcolumnValue, whereCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateCommonUser;
    }
}
