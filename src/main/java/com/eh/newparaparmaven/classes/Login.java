package com.eh.newparaparmaven.classes;

import com.eh.newparaparmaven.dao.SelectQueryDao;
import com.eh.newparaparmaven.dbConnection.conRs;
import java.sql.SQLException;

public class Login {

    private static String columnName;
    private static String tableName;
    private static String onCondition;
    private static conRs conrs;
    private static String whereCondition;
    public static conRs getLoginDetails(String phoneNo, String password) throws SQLException {

        columnName = " lt.all_user_id as all_user_id, lt.phone_no as phone_no, lt.password as password, au.user_role as user_role ";
        tableName = " login_table lt left join all_user au ";
        onCondition = " (lt.all_user_id = au.all_user_id) ";
        whereCondition = " phone_no = '"+phoneNo+"' and password = '"+password+"' and active_status = 1 ";

        try {
            conrs = SelectQueryDao.selectQueryWithJoinWhere(columnName, tableName, onCondition, whereCondition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conrs;
    }
}
