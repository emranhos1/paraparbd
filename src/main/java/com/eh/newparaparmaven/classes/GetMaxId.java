package com.eh.newparaparmaven.classes;

import com.eh.newparaparmaven.dao.SelectQueryDao;
import com.eh.newparaparmaven.dbConnection.conRs;
import java.sql.SQLException;

public class GetMaxId {

    private static String columnName;
    private static String tableName;
    private static conRs conrs;
    
    public static conRs getMaxOwnerId() throws SQLException {

        columnName = " MAX(rent_a_car_owner_id) as rent_a_car_owner_id";
        tableName = " rent_a_car_owner ";

        try {
            conrs = SelectQueryDao.selectQueryWithOutWhereClause(columnName, tableName);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
    public static conRs getMaxCommonUserId() throws SQLException {

        columnName = " MAX(common_user_id) as common_user_id";
        tableName = " common_user ";

        try {
            conrs = SelectQueryDao.selectQueryWithOutWhereClause(columnName, tableName);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
    
    public static conRs getMaxDwpOwnerId() throws SQLException {

        columnName = " MAX(dwp_owner_id) as dwp_owner_id";
        tableName = " dwp_owner ";

        try {
            conrs = SelectQueryDao.selectQueryWithOutWhereClause(columnName, tableName);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
    
    public static conRs getMaxAllUserId() throws SQLException {

        columnName = " MAX(all_user_id) as all_user_id";
        tableName = " all_user ";

        try {
            conrs = SelectQueryDao.selectQueryWithOutWhereClause(columnName, tableName);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
    
    public static conRs getMaxCarCategoryId() throws SQLException {

        columnName = " MAX(car_category_id) as car_category_id";
        tableName = " car_category ";

        try {
            conrs = SelectQueryDao.selectQueryWithOutWhereClause(columnName, tableName);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
}
