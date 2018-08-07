package com.eh.newparaparmaven.classes;

import com.eh.newparaparmaven.dao.InsertQueryDao;
import com.eh.newparaparmaven.model.RentACarTable;
import java.sql.SQLException;

public class AddRentACarTable {

    private static String tableName;
    private static String columnName;
    private static String values;
    private static boolean addRentACarTable;
    public static boolean addRentACarTable(RentACarTable rentACarTable) {

        tableName = " rent_a_car_table ";
        columnName = " car_category_id, rent_a_car_owner_id, created_date, active_or_not ";
        values = " '"+rentACarTable.getCar_category_id()+"', '"+rentACarTable.getRent_a_car_owner_id()+"', now(), '"+rentACarTable.getActive_or_not()+"' ";

        try {
            addRentACarTable = InsertQueryDao.insertQueryWithOutWhereClause(tableName, columnName, values);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addRentACarTable;
    }
}
