package com.eh.newparaparmaven.classes;

import com.eh.newparaparmaven.dao.InsertQueryDao;
import com.eh.newparaparmaven.dao.UpdateQueryDao;
import com.eh.newparaparmaven.model.LogginHistoryTable;
import java.sql.SQLException;

public class AddLoggingHistory {

    private static String tableName;
    private static String columnName;
    private static String values;
    private static boolean addLoginHistoryDetails;
    private static String columnNameANDcolumnValue;
    private static String whereCondition;
    public static boolean addLoginHistoryDetails(LogginHistoryTable logginHistoryTable) {

        tableName = " loggin_history_table ";
        columnName = " all_user_id, user_device_mac, user_device_ip, logging_date_time ";
        values = " '"+logginHistoryTable.getAll_user_id()+"', '"+logginHistoryTable.getUser_device_mac()+"', '"+logginHistoryTable.getUser_device_ip()+"', now() ";

        try {
            addLoginHistoryDetails = InsertQueryDao.insertQueryWithOutWhereClause(tableName, columnName, values);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addLoginHistoryDetails;
    }
    public static boolean updateLoginHistoryDetails(String allUserId) {
        
        tableName = " loggin_history_table ";
        columnNameANDcolumnValue = " logout_date_time = now() ";
        whereCondition = " all_user_id = '"+allUserId+"' ";
        try {
            addLoginHistoryDetails = UpdateQueryDao.updateQueryWithWhereClause(tableName, columnNameANDcolumnValue, whereCondition);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addLoginHistoryDetails;
    }
}
