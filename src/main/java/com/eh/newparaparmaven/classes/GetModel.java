package com.eh.newparaparmaven.classes;

import com.eh.newparaparmaven.dao.SelectQueryDao;
import com.eh.newparaparmaven.dbConnection.conRs;
import java.sql.SQLException;

public class GetModel {

    private static String columnName;
    private static String tableName;
    private static String whereCondition;
    private static conRs conrs;
    private static String onCondition;
    public static conRs getModel(String brandNameDropDown) throws SQLException {

        columnName = " * ";
        tableName = " car_model ";
        whereCondition = "car_brand_id = '"+brandNameDropDown+"'";

        try {
            conrs = SelectQueryDao.selectQueryWithWhereClause(columnName, tableName, whereCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
    
    public static conRs getBikeModelNameForCheck(String modelName) throws SQLException {

        columnName = " * ";
        tableName = " bike_model ";
        whereCondition = " model_name = '"+modelName+"' ";

        try {
            conrs = SelectQueryDao.selectQueryWithWhereClause(columnName, tableName, whereCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
    
    public static conRs getBikeModelTable() throws SQLException {

        columnName = " bm.bike_model_id, bm.model_name, bm.created_date,bbt.brand_name ";
        tableName = " bike_model bm left join bike_brand_table bbt ";
        onCondition = " (bm.bike_brand_table_id = bbt.bike_brand_table_id) ";

        try {
            conrs = SelectQueryDao.selectQueryWithJoinClause(columnName, tableName, onCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
    
    public static conRs getCarModelNameForCheck(String modelName) throws SQLException {

        columnName = " * ";
        tableName = " car_model ";
        whereCondition = " model_name = '"+modelName+"' ";

        try {
            conrs = SelectQueryDao.selectQueryWithWhereClause(columnName, tableName, whereCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
    
    public static conRs getCarModelTable() throws SQLException {

        columnName = " cm.car_model_id, cm.model_name, cm.created_date, cb.brand_name ";
        tableName = " car_model cm left join car_brand cb ";
        onCondition = " (cm.car_brand_id = cb.car_brand_id) ";
        try {
            conrs = SelectQueryDao.selectQueryWithJoinClause(columnName, tableName, onCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
}
