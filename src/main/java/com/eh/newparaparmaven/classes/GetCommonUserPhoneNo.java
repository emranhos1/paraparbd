package com.eh.newparaparmaven.classes;

import com.eh.newparaparmaven.dao.SelectQueryDao;
import com.eh.newparaparmaven.dbConnection.conRs;
import java.sql.SQLException;

public class GetCommonUserPhoneNo {

    private static String columnName;
    private static String tableName;
    private static String whereCondition;
    private static conRs conrs;
    
    public static conRs getPhoneNo(int commonUserId) throws SQLException {

        columnName = " phone_no ";
        tableName = " common_user ";
        whereCondition = " common_user_id = '"+commonUserId+"' ";

        try {
            conrs = SelectQueryDao.selectQueryWithWhereClause(columnName, tableName, whereCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
}
