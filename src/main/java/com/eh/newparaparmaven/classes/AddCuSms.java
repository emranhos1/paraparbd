package com.eh.newparaparmaven.classes;

import com.eh.newparaparmaven.dao.InsertQueryDao;
import com.eh.newparaparmaven.model.CuSmsTable;
import java.sql.SQLException;

public class AddCuSms {

    private static String tableName;
    private static String columnName;
    private static String values;
    private static boolean addCuSmsDetails;
    
    public static boolean addCuSms(CuSmsTable cuSmsTable) throws SQLException {

        tableName = " cu_sms_table ";
        columnName = " sms_status, mobile_no, random_genarated_code ";
        values = "'" + cuSmsTable.getSms_status()+ "', '"+cuSmsTable.getMobile_no()+"', '"+cuSmsTable.getRandom_genarated_code()+"' ";

        try {
            addCuSmsDetails = InsertQueryDao.insertQueryWithOutWhereClause(tableName, columnName, values);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return addCuSmsDetails;
    }
}
