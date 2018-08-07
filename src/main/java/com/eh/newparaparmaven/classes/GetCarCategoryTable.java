package com.eh.newparaparmaven.classes;

import com.eh.newparaparmaven.dao.SelectQueryDao;
import com.eh.newparaparmaven.dbConnection.conRs;
import java.sql.SQLException;

public class GetCarCategoryTable {

    private static String columnName;
    private static String tableName;
    private static String whereCondition;
    private static conRs conrs;
    private static String onCondition;
    public static conRs getRegistrationNoForCheck(String registrationNo) throws SQLException {

        columnName = " * ";
        tableName = " car_category ";
        whereCondition = " car_registration_no = '"+registrationNo+"' ";

        try {
            conrs = SelectQueryDao.selectQueryWithWhereClause(columnName, tableName, whereCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
    
    public static conRs getCarCategory(int rentECarOnwerId) throws SQLException {

        columnName = " cc.car_category_id, cc.car_registration_no, cc.car_registration_scan_copy, cb.brand_name, cm.model_name, c.color_name ";
        tableName = " car_category cc left join car_brand cb ";
        onCondition = " (cc.brand_id = cb.car_brand_id) left join car_model cm on (cc.car_model_id = cm.car_model_id) left join color c on (cc.color_id = c.color_id) ";
        whereCondition = " rent_a_car_owner_id = '"+rentECarOnwerId+"' ";

        try {
            conrs = SelectQueryDao.selectQueryWithJoinWhere(columnName, tableName, onCondition, whereCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
}
