package com.eh.newparaparmaven.classes;

import com.eh.newparaparmaven.dao.SelectQueryDao;
import com.eh.newparaparmaven.dbConnection.conRs;
import java.sql.SQLException;

public class GetPoliceStation {

    private static String columnName;
    private static String tableName;
    private static String whereCondition;
    private static conRs conrs;
    private static String onCondition;
    
    public static conRs getPoliceStationNameForCheck(String PoliceStationName) throws SQLException {

        columnName = " * ";
        tableName = " police_station_table ";
        whereCondition = " police_station_name = '"+PoliceStationName+"'";
        try {
            conrs = SelectQueryDao.selectQueryWithWhereClause(columnName, tableName, whereCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
    public static conRs getPoliceStationNameForDistrict(String districtNameDropDown) throws SQLException {

        columnName = " * ";
        tableName = " police_station_table ";
        whereCondition = " district_id = '"+districtNameDropDown+"'";
        try {
            conrs = SelectQueryDao.selectQueryWithWhereClause(columnName, tableName, whereCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
    public static conRs getPoliceStationTable() throws SQLException {

        columnName = " tt.police_station_id, tt.police_station_name, tt.created_date, dist.district_name, divi.division_name ";
        tableName = " police_station_table tt left join district_table dist ";
        onCondition = " (tt.district_id = dist.district_id) left join division_table divi on (tt.division_id = divi.division_id) ";

        try {
            conrs = SelectQueryDao.selectQueryWithJoinClause(columnName, tableName, onCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
    public static conRs getPoliceStationTableAll(String divisionNameDropDownOrigin, String districtNameDropDownOrigin) throws SQLException {

        columnName = " tt.police_station_id, tt.police_station_name, concat(tt.police_station_name,' &#44; ',dist.district_name,' #44; ',divi.division_name) as origin ";
        tableName = " police_station_table tt left join district_table dist ";
        onCondition = " (tt.district_id = dist.district_id) left join division_table divi on (tt.division_id = divi.division_id) ";
        whereCondition = " divi.division_id = '"+divisionNameDropDownOrigin+"' and dist.district_id = '"+districtNameDropDownOrigin+"' ";

        try {
            conrs = SelectQueryDao.selectQueryWithJoinWhere(columnName, tableName, onCondition, whereCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
}
