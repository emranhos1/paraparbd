package com.eh.newparaparmaven.classes;

import com.eh.newparaparmaven.dao.DeleteQueryDao;
import java.sql.SQLException;

public class DeleteCar {

    private static String tableName;
    private static String whereCondition;
    private static boolean deleteCarCategory;
    public static boolean deleteCarCategory(int carCategoryId) {

        tableName = " car_category ";
        whereCondition = " car_category_id = '"+carCategoryId+"' ";

        try {
            deleteCarCategory = DeleteQueryDao.deleteQueryWithWhereClause(tableName, whereCondition);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deleteCarCategory;
    }
    public static boolean deleteRentACarTable(int carCategoryId) {

        tableName = " rent_a_car_table ";
        whereCondition = " car_category_id = '"+carCategoryId+"' ";

        try {
            deleteCarCategory = DeleteQueryDao.deleteQueryWithWhereClause(tableName, whereCondition);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deleteCarCategory;
    }
}
