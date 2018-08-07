package com.eh.newparaparmaven.controller;

import com.eh.newparaparmaven.classes.GetCar;
import com.eh.newparaparmaven.dbConnection.conRs;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AllInactiveCar extends HttpServlet {

    private conRs getInactiveCar;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pstm;
    private int orgRow;
    private int[] carCategoryId;
    private String[] brandName;
    private String[] modelName;
    private String[] colorName;
    private String[] carRegistrationNo;
    private String[] carRegistrationScanCopy;
    private String[] firstName;
    private String[] lastName;
    private String[] email;
    private String[] phoneNo;
    private int[] rentACarTableId;
    private String[] texTokenNo;
    private String[] carFitnessNo;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            try {
                getInactiveCar = GetCar.getInactiveCar();
                con = getInactiveCar.getCon();
                rs = getInactiveCar.getRs();
                pstm = getInactiveCar.getPstm();

                rs.last();
                orgRow = rs.getRow();
                rentACarTableId = new int[orgRow];
                carCategoryId = new int[orgRow];
                brandName = new String[orgRow];
                modelName = new String[orgRow];
                colorName = new String[orgRow];
                carRegistrationNo = new String[orgRow];
                texTokenNo = new String[orgRow];
                carFitnessNo = new String[orgRow];
                carRegistrationScanCopy = new String[orgRow];
                firstName = new String[orgRow];
                lastName = new String[orgRow];
                email = new String[orgRow];
                phoneNo = new String[orgRow];
                rs.beforeFirst();
                int i = 0;
                while (rs.next()) {
                    rentACarTableId[i] = rs.getInt("rent_e_car_table_id");
                    carCategoryId[i] = rs.getInt("car_category_id");
                    brandName[i] = rs.getString("brand_name");
                    modelName[i] = rs.getString("model_name");
                    colorName[i] = rs.getString("color_name");
                    carRegistrationNo[i] = rs.getString("car_registration_no");
                    texTokenNo[i] = rs.getString("tex_token_no");
                    carFitnessNo[i] = rs.getString("car_fitness_no");
                    carRegistrationScanCopy[i] = rs.getString("car_registration_scan_copy");
                    firstName[i] = rs.getString("first_name");
                    lastName[i] = rs.getString("last_name");
                    email[i] = rs.getString("email");
                    phoneNo[i] = rs.getString("phone_no");
                    i++;
                }

                for (i = 0; i < orgRow; i++) {
                    response.setContentType("text/plain");
                    response.getWriter().write("<tr>"
                            + "<td>" + (i + 1) + "</td>"
                            + "<td>" + firstName[i] + " " + lastName[i] + "<br> Phone No : " + phoneNo[i] + "<br> Email : " + email[i] + "</td>"
                            + "<td>" + brandName[i] + " -- " + modelName[i] + " (" + colorName[i] + ")</td>"
                            + "<td>" + carRegistrationNo[i] + "</td>"
                            + "<td>" + texTokenNo[i] + "</td>"
                            + "<td>" + carFitnessNo[i] + "</td>"
                            + "<td><img src='../allImage/" + carRegistrationScanCopy[i] + "' alt='File Not Found' height='100px' width='100px'/></td>"
                            + "<td>"
                            + "<button class='btn btn-secondary' type='button' data-dismiss='modal'>"
                            + "<a data-toggle='modal' data-rentacartableid='" + rentACarTableId[i] + "' class='btn btn-primary open-spceDialog-active' href='#addSpecActive' >Active</a>"
                            + "</button>        "
                            + "</td>"
                            + "</tr>");
                }
            } catch (SQLException ex) {
                Logger.getLogger(AllInactiveCar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
