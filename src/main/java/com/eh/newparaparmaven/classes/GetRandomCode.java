package com.eh.newparaparmaven.classes;

import com.eh.newparaparmaven.dao.SelectQueryDao;
import com.eh.newparaparmaven.dbConnection.conRs;
import com.eh.newparaparmaven.model.CuSmsTable;
import java.sql.SQLException;

public class GetRandomCode {

    private static String columnName;
    private static String tableName;
    private static String whereCondition;
    private static conRs conrs;
    
    public static conRs getRandomCode(CuSmsTable cst) throws SQLException {

        columnName = " * ";
        tableName = " cu_sms_table ";
        whereCondition = " random_genarated_code = '"+cst.getRandom_genarated_code()+"'";
        try {
            conrs = SelectQueryDao.selectQueryWithWhereClause(columnName, tableName, whereCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
}
