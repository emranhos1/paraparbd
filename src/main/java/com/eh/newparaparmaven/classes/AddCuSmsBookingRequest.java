package com.eh.newparaparmaven.classes;

import com.eh.newparaparmaven.dao.InsertQueryDao;
import com.eh.newparaparmaven.model.CuSmsBookingRequestTable;
import java.sql.SQLException;

public class AddCuSmsBookingRequest {

    private static String tableName;
    private static String columnName;
    private static String values;
    private static boolean addCsbrt;
    
    public static boolean addCuBookingSms(CuSmsBookingRequestTable csbrt) throws SQLException {

        tableName = " cu_sms_booking_request_table ";
        columnName = " text_body, common_user_id, sms_status, request_date ";
        values = "'" + csbrt.getText_body()+ "', '"+csbrt.getCommon_user_id()+"', '"+csbrt.getSms_status()+"', now() ";

        try {
            addCsbrt = InsertQueryDao.insertQueryWithOutWhereClause(tableName, columnName, values);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return addCsbrt;
    }
}
