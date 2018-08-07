package com.eh.newparaparmaven.classes;

import com.eh.newparaparmaven.dao.SelectQueryDao;
import com.eh.newparaparmaven.dbConnection.conRs;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GetLoginTable {

    private static String columnName;
    private static String tableName;
    private static String whereCondition;
    private static conRs conrs;
    public static conRs getPhone(String phone){

        columnName = " * ";
        tableName = " login_table ";
        whereCondition = "phone_no = '"+phone+"'";

        try {
            conrs = SelectQueryDao.selectQueryWithWhereClause(columnName, tableName, whereCondition);
        } catch (SQLException ex) {
            Logger.getLogger(GetLoginTable.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        return conrs;
    }
}
