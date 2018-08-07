package com.eh.newparaparmaven.classes;

import com.eh.newparaparmaven.dao.InsertQueryDao;
import java.sql.SQLException;

public class AddModel {

    private static String tableName;
    private static String columnName;
    private static String values;
    private static boolean addBikeModel;

    public static boolean addBikeModel(String brandNameDropDown, String modelName) throws SQLException {

        tableName = " bike_model ";
        columnName = " model_name, bike_brand_table_id, created_date ";
        values = "'" + modelName + "', '" + brandNameDropDown + "', now() ";

        try {
            addBikeModel = InsertQueryDao.insertQueryWithOutWhereClause(tableName, columnName, values);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return addBikeModel;
    }

    public static boolean addCarModel(String modelName, String carBrandNameDropDown) throws SQLException {

        tableName = " car_model ";
        columnName = " model_name, car_brand_id, created_date ";
        values = "'" + modelName + "', '" + carBrandNameDropDown + "', now() ";

        try {
            addBikeModel = InsertQueryDao.insertQueryWithOutWhereClause(tableName, columnName, values);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return addBikeModel;
    }
}
