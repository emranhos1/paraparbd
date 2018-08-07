package com.eh.newparaparmaven.classes;

import com.eh.newparaparmaven.dao.SelectQueryDao;
import com.eh.newparaparmaven.dbConnection.conRs;
import java.sql.SQLException;

public class GetAllUser {

    private static String columnName;
    private static String tableName;
    private static String whereCondition;
    private static conRs conrs;
    public static conRs getOwnerUserId(int allUserId) throws SQLException {

        columnName = " user_id ";
        tableName = " all_user ";
        whereCondition = " all_user_id = '"+allUserId+"' ";

        try {
            conrs = SelectQueryDao.selectQueryWithWhereClause(columnName, tableName, whereCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
}
