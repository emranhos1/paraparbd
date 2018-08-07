package com.eh.newparaparmaven.classes;

import com.eh.newparaparmaven.dao.UpdateQueryDao;
import com.eh.newparaparmaven.model.LoginTable;
import java.sql.SQLException;

public class UpdateLoginUser {

    private static String tableName;
    private static String columnNameANDcolumnValue;
    private static String whereCondition;
    private static boolean updateLoginDetils;
    public static boolean updateLoginDetails(LoginTable loginTable) throws SQLException {

        tableName = " login_table ";
        columnNameANDcolumnValue = " password = '"+loginTable.getPassword()+"', updated_date = now() ";
        whereCondition = " all_user_id = '"+loginTable.getAll_user_id()+"' ";

        try {
            updateLoginDetils = UpdateQueryDao.updateQueryWithWhereClause(tableName, columnNameANDcolumnValue, whereCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateLoginDetils;
    }
    
    public static boolean updateLoginPass(LoginTable loginTable) throws SQLException {

        tableName = " login_table ";
        columnNameANDcolumnValue = " password = '"+loginTable.getPassword()+"', updated_date = now() ";
        whereCondition = " login_table_id = '"+loginTable.getLogin_table_id()+"' ";

        try {
            updateLoginDetils = UpdateQueryDao.updateQueryWithWhereClause(tableName, columnNameANDcolumnValue, whereCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateLoginDetils;
    }
}
