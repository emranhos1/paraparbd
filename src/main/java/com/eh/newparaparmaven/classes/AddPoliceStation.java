package com.eh.newparaparmaven.classes;

import com.eh.newparaparmaven.dao.InsertQueryDao;
import java.sql.SQLException;

public class AddPoliceStation {
    private static String tableName;
    private static String columnName;
    private static String values;
    private static boolean addPolice;
    public static boolean addPoliceStation(String divisionNameDropDown, String distictNameDropDown, String PoliceStationName) throws SQLException {

        tableName = " police_station_table ";
        columnName = " division_id, district_id, police_station_name, created_date ";
        values = "'"+divisionNameDropDown+"', '" + distictNameDropDown + "', '" + PoliceStationName + "', now() ";

        try {
            addPolice = InsertQueryDao.insertQueryWithOutWhereClause(tableName, columnName, values);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return addPolice;
    }
}
