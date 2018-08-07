package com.eh.newparaparmaven.classes;

import com.eh.newparaparmaven.dao.SelectQueryDao;
import com.eh.newparaparmaven.dbConnection.conRs;
import java.sql.SQLException;

public class GetDWPOwnerTable {

    private static String columnName;
    private static String tableName;
    private static String whereCondition;
    private static conRs conrs;
    
    public static conRs getDWPOwnerDistId(int dwpOwnerId) throws SQLException {

        columnName = " * ";
        tableName = " dwp_owner ";
        whereCondition = " dwp_owner_id = '"+dwpOwnerId+"' ";

        try {
            conrs = SelectQueryDao.selectQueryWithWhereClause(columnName, tableName, whereCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
}
