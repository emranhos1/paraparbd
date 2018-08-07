package com.eh.newparaparmaven.classes;

import com.eh.newparaparmaven.dao.InsertQueryDao;
import com.eh.newparaparmaven.model.LoginTable;
import java.sql.SQLException;

public class AddLoginDetails {

    private static String tableName;
    private static String columnName;
    private static String values;
    private static boolean addLoginDetails;
    
    public static boolean addLoginDetails(LoginTable loginTable) {

        tableName = " login_table ";
        columnName = " all_user_id, phone_no, password, created_date ";
        values = " '"+loginTable.getAll_user_id()+"', '"+loginTable.getPhone_no()+"', '"+loginTable.getPassword()+"', now() ";

        try {
            addLoginDetails = InsertQueryDao.insertQueryWithOutWhereClause(tableName, columnName, values);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addLoginDetails;
    }
}
