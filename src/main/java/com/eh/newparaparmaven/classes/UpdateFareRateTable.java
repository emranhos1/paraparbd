package com.eh.newparaparmaven.classes;

import com.eh.newparaparmaven.dao.UpdateQueryDao;
import com.eh.newparaparmaven.model.CuFareRateTable;
import java.sql.SQLException;

public class UpdateFareRateTable {

    private static String tableName;
    private static String columnNameANDcolumnValue;
    private static String whereCondition;
    private static boolean updateFareRate;
    
    public static boolean updateFareRate(CuFareRateTable cuFareRateTable) throws SQLException {

        tableName = " cu_fare_rate_table ";
//        cu_fare_rate_table_id, par_km_rate, bodyless200km, bodybig200km, driver_cost, other_cost, update_date, active_status
        columnNameANDcolumnValue = " par_km_rate = '"+cuFareRateTable.getPar_km_rate()+"', bodyless200km = '"+cuFareRateTable.getBodyLess200km()+"', bodybig200km= '"+cuFareRateTable.getBodyBig200km()+"', driver_cost = '"+cuFareRateTable.getDriver_cost()+"', other_cost = '"+cuFareRateTable.getOther_cost()+"', update_date = now() ";
        whereCondition = " cu_fare_rate_table_id = '"+cuFareRateTable.getCu_fare_rate_table_id()+"' ";

        try {
            updateFareRate = UpdateQueryDao.updateQueryWithWhereClause(tableName, columnNameANDcolumnValue, whereCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateFareRate;
    }
}
