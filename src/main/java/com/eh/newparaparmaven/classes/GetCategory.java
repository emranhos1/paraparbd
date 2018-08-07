package com.eh.newparaparmaven.classes;

import com.eh.newparaparmaven.dao.SelectQueryDao;
import com.eh.newparaparmaven.dbConnection.conRs;
import java.sql.SQLException;

public class GetCategory {

    private static String columnName;
    private static String tableName;
    private static String whereCondition;
    private static conRs conrs;
    private static String onCondition;
    
    public static conRs getCategoryNames(int userId) throws SQLException {

        columnName = " * ";
        tableName = " car_category cc left join car_brand cb ";
        onCondition = " (cc.brand_id = cb.car_brand_id) left join car_model cm on (cc.car_model_id = cm.car_model_id) left join color c on (cc.color_id = c.color_id) ";
        whereCondition = "rent_a_car_owner_id = '"+userId+"'";

        try {
            conrs = SelectQueryDao.selectQueryWithJoinWhere(columnName, tableName, onCondition, whereCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
    
    public static conRs getCategoryNamesForCU(String districtNameDropDownOrigin) throws SQLException {

        columnName = " raco.rent_a_car_owner_id, raco.division_id, raco.district_id, raco.thana_id, ract.rent_e_car_table_id, cc.car_category_id, cm.model_name, cb.brand_name, ract.rent_a_car_owner_id, ract.active_or_not ";
        tableName = " rent_a_car_owner raco left join rent_a_car_table ract  ";
        onCondition = " (raco.rent_a_car_owner_id = ract.rent_a_car_owner_id) left join car_category cc on (ract.car_category_id = cc.car_category_id) left join car_brand cb on (cc.brand_id = cb.car_brand_id) left join car_model cm on (cc.car_model_id = cm.car_model_id) ";
        whereCondition = "ract.active_or_not = '1' and raco.district_id ='"+districtNameDropDownOrigin+"'";
        
        try {
            conrs = SelectQueryDao.selectQueryWithJoinWhere(columnName, tableName, onCondition, whereCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
}
