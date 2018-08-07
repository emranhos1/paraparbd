package com.eh.newparaparmaven.classes;

import com.eh.newparaparmaven.dao.SelectQueryDao;
import com.eh.newparaparmaven.dbConnection.conRs;
import java.sql.SQLException;

public class GetFareRate {

    private static String columnName;
    private static String tableName;
    private static String whereCondition;
    private static conRs conrs;
    public static conRs getFareRateTable() throws SQLException {

        columnName = " * ";
        tableName = " cu_fare_rate_table ";
        whereCondition = " active_status = '1' ";
        try {
            conrs = SelectQueryDao.selectQueryWithWhereClause(columnName, tableName, whereCondition);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conrs;
    }
}
