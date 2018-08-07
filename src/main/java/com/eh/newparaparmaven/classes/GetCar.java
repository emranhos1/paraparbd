package com.eh.newparaparmaven.classes;

import com.eh.newparaparmaven.dao.SelectQueryDao;
import com.eh.newparaparmaven.dbConnection.conRs;
import java.sql.SQLException;

public class GetCar {

    private static String columnName;
    private static String tableName;
    private static conRs conrs;
    private static String onCondition;
    private static String whereCondition;
    
    public static conRs getInactiveCar() throws SQLException {

        columnName = " ract.rent_e_car_table_id, cc.car_category_id, cb.brand_name, cm.model_name, c.color_name, cc.car_registration_no, cc.car_registration_scan_copy, cc.tex_token_no, cc.car_fitness_no, raco.first_name, raco.last_name, raco.email, raco.phone_no ";
        tableName = " car_category cc left join rent_a_car_table ract ";
        onCondition = "(cc.car_category_id = ract.car_category_id) left join car_brand cb on (cc.brand_id = cb.car_brand_id) left join car_model cm on (cc.car_model_id = cm.car_model_id) left join color c on (cc.color_id = c.color_id) left join rent_a_car_owner raco on (cc.rent_a_car_owner_id = raco.rent_a_car_owner_id)";
        whereCondition = "ract.active_or_not = '0'";

        try {
            conrs = SelectQueryDao.selectQueryWithJoinWhere(columnName, tableName, onCondition, whereCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
    
    public static conRs getActiveCar() throws SQLException {

        columnName = " ract.rent_e_car_table_id, cc.car_category_id, cb.brand_name, cm.model_name, c.color_name, cc.car_registration_no, cc.car_registration_scan_copy, cc.tex_token_no, cc.car_fitness_no, raco.first_name, raco.last_name, raco.email, raco.phone_no ";
        tableName = " car_category cc left join rent_a_car_table ract ";
        onCondition = "(cc.car_category_id = ract.car_category_id) left join car_brand cb on (cc.brand_id = cb.car_brand_id) left join car_model cm on (cc.car_model_id = cm.car_model_id) left join color c on (cc.color_id = c.color_id) left join rent_a_car_owner raco on (cc.rent_a_car_owner_id = raco.rent_a_car_owner_id)";
        whereCondition = "ract.active_or_not = '1'";

        try {
            conrs = SelectQueryDao.selectQueryWithJoinWhere(columnName, tableName, onCondition, whereCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
}
