package com.eh.newparaparmaven.classes;

import com.eh.newparaparmaven.dao.SelectQueryDao;
import com.eh.newparaparmaven.dbConnection.conRs;
import java.sql.SQLException;


public class GetDivision {

    private static String columnName;
    private static String tableName;
    private static conRs conrs;
    private static String whereCondition;
    
    public static conRs getDivisionNameForCheck(String divisionName) throws SQLException {

        columnName = " * ";
        tableName = " division_table ";
        whereCondition = " division_name = '"+divisionName+"'";
        try {
            conrs = SelectQueryDao.selectQueryWithWhereClause(columnName, tableName, whereCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
    public static conRs getAllDivisionName() throws SQLException {

        columnName = " * ";
        tableName = " division_table ";
        try {
            conrs = SelectQueryDao.selectQueryWithOutWhereClause(columnName, tableName);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
    
    public static conRs getDivisionTable() throws SQLException {

        columnName = " * ";
        tableName = " division_table ";

        try {
            conrs = SelectQueryDao.selectQueryWithOutWhereClause(columnName, tableName);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
}
