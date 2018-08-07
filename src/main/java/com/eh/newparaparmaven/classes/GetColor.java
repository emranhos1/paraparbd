package com.eh.newparaparmaven.classes;

import com.eh.newparaparmaven.dao.SelectQueryDao;
import com.eh.newparaparmaven.dbConnection.conRs;
import java.sql.SQLException;

public class GetColor {

    private static String columnName;
    private static String tableName;
    private static conRs conrs;
    private static String whereCondition;
    
    public static conRs getColorNameForCheck(String colorName) throws SQLException {

        columnName = " * ";
        tableName = " color ";
        whereCondition = " color_name = '"+colorName+"' ";

        try {
            conrs = SelectQueryDao.selectQueryWithWhereClause(columnName, tableName, whereCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
    public static conRs getColorTable() throws SQLException {

        columnName = " * ";
        tableName = " color ";

        try {
            conrs = SelectQueryDao.selectQueryWithOutWhereClause(columnName, tableName);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
}
