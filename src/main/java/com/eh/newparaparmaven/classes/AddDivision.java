package com.eh.newparaparmaven.classes;

import com.eh.newparaparmaven.dao.InsertQueryDao;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddDivision {

    private static SimpleDateFormat dateFormat;
    private static Date date;
    private static String currentDate;
    private static String tableName;
    private static String columnName;
    private static String values;
    private static boolean conrs;
    
    public static boolean addDivision(String divisionName) throws SQLException {
        
        tableName = " division_table ";
        columnName = " division_name, created_date ";
        values = "'" + divisionName + "', now() ";

        try {
            conrs = InsertQueryDao.insertQueryWithOutWhereClause(tableName, columnName, values);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
}
