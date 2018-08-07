package com.eh.newparaparmaven.classes;

import com.eh.newparaparmaven.dao.SelectQueryDao;
import com.eh.newparaparmaven.dbConnection.conRs;
import java.sql.SQLException;

public class GetUserInfo {

    private static String columnName;
    private static String tableName;
    private static String whereCondition;
    private static conRs conrs;
    private static String onCondition;

    public static conRs getCommonUserInfo(int CommonUserId) throws SQLException {

        columnName = " * ";
        tableName = " common_user ";
        whereCondition = "common_user_id = '"+CommonUserId+"'";

        try {
            conrs = SelectQueryDao.selectQueryWithWhereClause(columnName, tableName, whereCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
    
    public static conRs getRentACarOwnerInfo(int rentACarOwnerId) throws SQLException {

        columnName = " dt.division_name, dist.district_name, pst.police_station_name, raco.rent_a_car_owner_id, raco.first_name, raco.last_name, raco.email, raco.phone_no, raco.address, raco.zip_code, raco.owner_photo, raco.nid_no, raco.tin_no, raco.trade_lisence_no, raco.rent_device_emi_no, raco.registration_date ";
        tableName = " rent_a_car_owner raco left join division_table dt ";
        onCondition = " (raco.division_id = dt.division_id) left join district_table dist on (raco.district_id= dist.district_id) left join police_station_table pst on (raco.thana_id = pst.police_station_id) ";
        whereCondition = "rent_a_car_owner_id = '"+rentACarOwnerId+"'";

        try {
            conrs = SelectQueryDao.selectQueryWithJoinWhere(columnName, tableName, onCondition, whereCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
    
    public static conRs getDwpOwnerInfo(int dwpOwnerId) throws SQLException {

        columnName = " dwo.dwp_owner_id, dwo.first_name, dwo.last_name, dwo.email, dwo.phone_no, dt.division_name, dist.district_name, pst.police_station_name, dwo.address, dwo.gender, dwo.zip_code, dwo.owner_photo, dwo.nid_no, dwo.nid_scan_copy, dwo.tin_on, dwo.tin_scan_copy, dwo.trade_lisence_no, dwo.trade_lisence_scan_copy, dwo.registration_date, dwo.update_date ";
        tableName = " dwp_owner dwo left join division_table dt ";
        onCondition = " (dwo.division_id = dt.division_id) left join district_table dist on (dwo.district_id= dist.district_id) left join police_station_table pst on (dwo.police_station_id = pst.police_station_id) ";
        whereCondition = " dwo.dwp_owner_id  = '"+dwpOwnerId+"' ";

        try {
            conrs = SelectQueryDao.selectQueryWithJoinWhere(columnName, tableName, onCondition, whereCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
}
