package com.eh.newparaparmaven.classes;

import com.eh.newparaparmaven.dao.SelectQueryDao;
import com.eh.newparaparmaven.dbConnection.conRs;
import java.sql.SQLException;

public class GetBkashTable {

    private static String columnName;
    private static String tableName;
    private static String whereCondition;
    private static conRs conrs;
    private static String onCondition;
    
    public static conRs getBkashTable(int commonUserId) throws SQLException {

        columnName = " cbt.bkash_table_id, cbt.order_no, cbst.massage ";
        tableName = " cu_bkash_table cbt left join cu_bkash_sms_table cbst ";
        onCondition = " (cbt.cu_bkash_sms_table_id = cbst.cu_bkash_sms_table_id) ";
        whereCondition = " common_user_id = '"+commonUserId+"' and panding_or_not = '0' ";

        try {
            conrs = SelectQueryDao.selectQueryWithJoinWhere(columnName, tableName, onCondition, whereCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
}
