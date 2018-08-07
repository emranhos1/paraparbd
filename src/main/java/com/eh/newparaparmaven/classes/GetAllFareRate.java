package com.eh.newparaparmaven.classes;

import com.eh.newparaparmaven.dao.SelectQueryDao;
import com.eh.newparaparmaven.dbConnection.conRs;
import java.sql.SQLException;

public class GetAllFareRate {

    private static String columnName;
    private static String tableName;
    private static conRs conrs;
    
    public static conRs getAllFare() throws SQLException {

        columnName = " * ";
        tableName = " cu_fare_rate_table ";

        try {
            conrs = SelectQueryDao.selectQueryWithOutWhereClause(columnName, tableName);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
}
