package com.eh.newparaparmaven.classes;

import com.eh.newparaparmaven.dao.SelectQueryDao;
import com.eh.newparaparmaven.dbConnection.conRs;
import java.sql.SQLException;

public class GetBrand {

    private static String columnName;
    private static String tableName;
    private static conRs conrs;
    private static String whereCondition;
    
    public static conRs getBikeBrandNameForCheck(String brandName) throws SQLException {

        columnName = " * ";
        tableName = " bike_brand_table ";
        whereCondition = " brand_name = '"+brandName+"' ";

        try {
            conrs = SelectQueryDao.selectQueryWithWhereClause(columnName, tableName, whereCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
    public static conRs getBikeBrand() throws SQLException {

        columnName = " * ";
        tableName = " bike_brand_table ";

        try {
            conrs = SelectQueryDao.selectQueryWithOutWhereClause(columnName, tableName);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
    
    public static conRs getCarBrandNameForCheck(String brandName) throws SQLException {

        columnName = " * ";
        tableName = " car_brand ";
        whereCondition = " brand_name = '"+brandName+"' ";

        try {
            conrs = SelectQueryDao.selectQueryWithWhereClause(columnName, tableName, whereCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
    public static conRs getCarBrandTable() throws SQLException {

        columnName = " * ";
        tableName = " car_brand ";

        try {
            conrs = SelectQueryDao.selectQueryWithOutWhereClause(columnName, tableName);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
}
