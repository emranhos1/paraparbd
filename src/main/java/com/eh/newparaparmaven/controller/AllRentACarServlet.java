package com.eh.newparaparmaven.controller;

import com.eh.newparaparmaven.classes.GetRentACarTable;
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

public class AllRentACarServlet extends HttpServlet {

    private conRs getAllRentACar;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pstm;
    private int orgRow;
    private int[] rentACarOwnerId;
    private String[] firstName;
    private String[] lastName;
    private String[] phoneNo;
    private String[] divisionName;
    private String[] districtName;
    private String[] thanaName;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            getAllRentACar = GetRentACarTable.getRentACarId();
            con = getAllRentACar.getCon();
            rs = getAllRentACar.getRs();
            pstm = getAllRentACar.getPstm();

            rs.last();
            orgRow = rs.getRow();
            rentACarOwnerId = new int[orgRow];
            firstName = new String[orgRow];
            lastName = new String[orgRow];
            phoneNo = new String[orgRow];
            divisionName = new String[orgRow];
            districtName = new String[orgRow];
            thanaName = new String[orgRow];
            rs.beforeFirst();
            int i = 0;
            while (rs.next()) {
                rentACarOwnerId[i] = rs.getInt("rent_a_car_owner_id");
                firstName[i] = rs.getString("first_name");
                lastName[i] = rs.getString("last_name");
                phoneNo[i] = rs.getString("phone_no");
                divisionName[i] = rs.getString("division_name");
                districtName[i] = rs.getString("district_name");
                thanaName[i] = rs.getString("police_station_name");
                i++;
            }
            response.getWriter().write("<option value=''>Select Rent A Car</option>");
            for (i = 0; i < orgRow; i++) {
                response.setContentType("text/plain");
                response.getWriter().write("<option value=" + rentACarOwnerId[i] + ">" + firstName[i]+"("+phoneNo[i]+")--"+ districtName[i] + "</option>");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AllRentACarServlet.class.getName()).log(Level.SEVERE, null, ex);
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