package com.eh.newparaparmaven.classes;

import com.eh.newparaparmaven.dao.SelectQueryDao;
import com.eh.newparaparmaven.dbConnection.conRs;
import java.sql.SQLException;

public class GetDistrict {

    private static String columnName;
    private static String tableName;
    private static String whereCondition;
    private static conRs conrs;
    private static String onCondition;
    
    public static conRs getDistrictNameForCheck(String districtName) throws SQLException {

        columnName = " * ";
        tableName = " district_table ";
        whereCondition = " district_name = '"+districtName+"' ";

        try {
            conrs = SelectQueryDao.selectQueryWithWhereClause(columnName, tableName, whereCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
    
    public static conRs getDistrictTable() throws SQLException {

        columnName = " dist.district_id, dist.district_name,dist.created_date,divi.division_name ";
        tableName = " district_table dist join division_table divi ";
        onCondition = " (dist.division_id = divi.division_id) ";

        try {
            conrs = SelectQueryDao.selectQueryWithJoinClause(columnName, tableName, onCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
    public static conRs getDistrictForDivision(String divisionNameDropDown) throws SQLException {

        columnName = " * ";
        tableName = " district_table ";
        whereCondition = "division_id = '"+divisionNameDropDown+"' ";

        try {
            conrs = SelectQueryDao.selectQueryWithWhereClause(columnName, tableName, whereCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
}
