package com.eh.newparaparmaven.classes;

import com.eh.newparaparmaven.dao.InsertQueryDao;
import java.sql.SQLException;

public class AddColor {

    private static String tableName;
    private static String columnName;
    private static String values;
    private static boolean addBikeBrand;
    
    public static boolean addColor(String colorName) throws SQLException {

        tableName = " color ";
        columnName = " color_name, created_date ";
        values = "'" + colorName + "', now() ";

        try {
            addBikeBrand = InsertQueryDao.insertQueryWithOutWhereClause(tableName, columnName, values);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return addBikeBrand;
    }
}
