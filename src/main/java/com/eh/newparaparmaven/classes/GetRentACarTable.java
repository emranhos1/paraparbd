package com.eh.newparaparmaven.classes;

import com.eh.newparaparmaven.dao.SelectQueryDao;
import com.eh.newparaparmaven.dbConnection.conRs;
import com.eh.newparaparmaven.model.RentACarOwner;
import java.sql.SQLException;

public class GetRentACarTable {

    private static String columnName;
    private static String tableName;
    private static conRs conrs;
    private static String onCondition;
    private static String whereCondition;
    
    public static conRs getRentACarId() throws SQLException {

        columnName = " raco.rent_a_car_owner_id, raco.first_name, raco.last_name, raco.phone_no, dt.division_name,dist.district_name,pst.police_station_name ";
        tableName = " rent_a_car_owner raco left join division_table dt ";
        onCondition = " (raco.division_id = dt.division_id) left join district_table dist on (raco.district_id = dist.district_id) left join police_station_table pst on(raco.thana_id = pst.police_station_id)";
        try {
            conrs = SelectQueryDao.selectQueryWithJoinClause(columnName, tableName, onCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
    
    public static conRs getRentACarIdInDist(RentACarOwner rentACarOwner) throws SQLException {

        columnName = " raco.first_name, raco.last_name, raco.email, raco.phone_no, raco.district_id, cc.car_category_id, cb.brand_name, cm.model_name, c.color_name ";
        tableName = " rent_a_car_owner raco left join car_category cc  ";
        onCondition = " (raco.rent_a_car_owner_id = cc.rent_a_car_owner_id) left join car_brand cb on (cc.brand_id = cb.car_brand_id) left join car_model cm on (cc.car_model_id = cm.car_model_id) left join color c on (cc.color_id = c.color_id) ";
        whereCondition = " raco.district_id = '"+rentACarOwner.getDistrict_id()+"' ";
        try {
            conrs = SelectQueryDao.selectQueryWithJoinWhere(columnName, tableName, onCondition, whereCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
    
    public static conRs getInactiveRentACar() throws SQLException {

        columnName = " au.all_user_id, au.user_id, au.user_role, rent_a_car_owner_id, raco.first_name, raco.last_name, raco.email, raco.phone_no, dt.division_name, dist.district_name, pst.police_station_name ";
        tableName = " all_user au left join rent_a_car_owner raco ";
        onCondition = " (au.user_id = raco.rent_a_car_owner_id) left join division_table dt on (raco.division_id = dt.division_id) left join district_table dist on (raco.district_id = dist.district_id) left join police_station_table pst on(raco.thana_id = pst.police_station_id)  ";
        whereCondition = " au.user_role = 'RentACar' and au.active_status = '0' ";
        try {
            conrs = SelectQueryDao.selectQueryWithJoinWhere(columnName, tableName, onCondition, whereCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
    public static conRs getActiveRentACar() throws SQLException {

        columnName = " au.all_user_id, au.user_id, au.user_role, rent_a_car_owner_id, raco.first_name, raco.last_name, raco.email, raco.phone_no, dt.division_name, dist.district_name, pst.police_station_name ";
        tableName = " all_user au left join rent_a_car_owner raco ";
        onCondition = " (au.user_id = raco.rent_a_car_owner_id) left join division_table dt on (raco.division_id = dt.division_id) left join district_table dist on (raco.district_id = dist.district_id) left join police_station_table pst on(raco.thana_id = pst.police_station_id)  ";
        whereCondition = " au.user_role = 'RentACar' and au.active_status = '1' ";
        try {
            conrs = SelectQueryDao.selectQueryWithJoinWhere(columnName, tableName, onCondition, whereCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
}
