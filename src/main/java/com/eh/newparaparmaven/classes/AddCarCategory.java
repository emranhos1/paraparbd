package com.eh.newparaparmaven.classes;

import com.eh.newparaparmaven.dao.InsertQueryDao;
import com.eh.newparaparmaven.model.CarCategory;
import java.sql.SQLException;

public class AddCarCategory {

    private static String tableName;
    private static String columnName;
    private static String values;
    private static boolean addCarCategory;
    public static boolean addCarCategory(CarCategory carCategory) throws SQLException {

        tableName = " car_category ";
        columnName = " brand_id, car_model_id, color_id, car_registration_no, car_registration_scan_copy, rent_a_car_owner_id, tex_token_no, car_fitness_no ";
        values = "'" + carCategory.getBrand_id()+ "', '"+carCategory.getCar_model_id()+"', '"+carCategory.getColor_id()+"', '"+carCategory.getCar_registration_no()+"', '"+carCategory.getCar_registration_scan_copy()+"', '"+carCategory.getRent_a_car_owner_id()+"', '"+carCategory.getTex_token_no()+"', '"+carCategory.getCar_fitness_no()+"' ";

        try {
            addCarCategory = InsertQueryDao.insertQueryWithOutWhereClause(tableName, columnName, values);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return addCarCategory;
    }
}
