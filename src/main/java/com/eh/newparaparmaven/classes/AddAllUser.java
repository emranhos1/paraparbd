package com.eh.newparaparmaven.classes;

import com.eh.newparaparmaven.dao.InsertQueryDao;
import com.eh.newparaparmaven.model.AllUser;
import java.sql.SQLException;

public class AddAllUser {

    private static String tableName;
    private static String columnName;
    private static String values;
    private static boolean addAllUser;
    public static boolean addAllUser(AllUser allUser) throws SQLException {

        tableName = " all_user ";
        columnName = " user_id, user_role, active_status, active_date ";
        values = " '"+allUser.getUser_id()+"', '"+allUser.getUser_role()+"', '"+allUser.getActive_status()+"', now() ";

        try {
            addAllUser = InsertQueryDao.insertQueryWithOutWhereClause(tableName, columnName, values);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return addAllUser;
    }
}
