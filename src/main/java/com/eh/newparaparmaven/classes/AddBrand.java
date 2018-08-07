package com.eh.newparaparmaven.classes;

import com.eh.newparaparmaven.dao.InsertQueryDao;
import java.sql.SQLException;

public class AddBrand {

    private static String tableName;
    private static String columnName;
    private static String values;
    private static boolean addBikeBrand;
    
    public static boolean addBikeBrand(String bikeBrandName) throws SQLException {

        tableName = " bike_brand_table ";
        columnName = " brand_name, created_date ";
        values = "'" + bikeBrandName + "', now() ";

        try {
            addBikeBrand = InsertQueryDao.insertQueryWithOutWhereClause(tableName, columnName, values);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return addBikeBrand;
    }
    
    public static boolean addCarBrand(String carBrandName) throws SQLException {

        tableName = " car_brand ";
        columnName = " brand_name, created_date ";
        values = "'" + carBrandName + "', now() ";

        try {
            addBikeBrand = InsertQueryDao.insertQueryWithOutWhereClause(tableName, columnName, values);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return addBikeBrand;
    }
}
