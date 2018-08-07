package com.eh.newparaparmaven.classes;

import com.eh.newparaparmaven.dao.UpdateQueryDao;
import com.eh.newparaparmaven.model.CuBkashTable;
import java.sql.SQLException;

public class UpdateBkashT {

    private static String tableName;
    private static String whereCondition;
    private static String columnNameANDcolumnValue;
    private static boolean updateBkash;
    public static boolean updateBkashT(CuBkashTable cuBkashTable) throws SQLException {

        tableName = " cu_bkash_table ";
        columnNameANDcolumnValue = " transaction_no = '"+cuBkashTable.getTransaction_no()+"', transaction_date = now(), panding_or_not = 1 ";
        whereCondition = " bkash_table_id = '"+cuBkashTable.getBkash_table_id()+"' ";

        try {
            updateBkash = UpdateQueryDao.updateQueryWithWhereClause(tableName, columnNameANDcolumnValue, whereCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateBkash;
    }
}
