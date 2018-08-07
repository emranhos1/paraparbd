package com.eh.newparaparmaven.classes;

import com.eh.newparaparmaven.dao.InsertQueryDao;
import com.eh.newparaparmaven.model.CuBkashTable;
import java.sql.SQLException;

public class AddCuBkashTable {

    private static String tableName;
    private static String columnName;
    private static String values;
    private static boolean addCuBkash;
    
    public static boolean addCuBkashTable(CuBkashTable cuBkashTable) throws SQLException {

        tableName = " cu_bkash_table ";
        columnName = " common_user_id, panding_or_not, order_no, cu_bkash_sms_table_id ";
        values = "'" + cuBkashTable.getCommon_user_id()+ "', '"+cuBkashTable.getPanding_or_not()+"', '"+cuBkashTable.getOrder_no()+"', '"+cuBkashTable.getCu_bkash_sms_table_id()+"' ";

        try {
            addCuBkash = InsertQueryDao.insertQueryWithOutWhereClause(tableName, columnName, values);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return addCuBkash;
    }
}
