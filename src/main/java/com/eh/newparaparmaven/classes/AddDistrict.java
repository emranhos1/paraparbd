package com.eh.newparaparmaven.classes;

import com.eh.newparaparmaven.dao.InsertQueryDao;
import java.sql.SQLException;

public class AddDistrict {

    private static String tableName;
    private static String columnName;
    private static String values;
    private static boolean addDistrict;
    public static boolean addDistrict(String divisionNameDropDown, String districtName) throws SQLException {

        tableName = " district_table ";
        columnName = " division_id, district_name, created_date ";
        values = "'" + divisionNameDropDown + "', '" + districtName + "', now() ";

        try {
            addDistrict = InsertQueryDao.insertQueryWithOutWhereClause(tableName, columnName, values);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return addDistrict;
    }
}
