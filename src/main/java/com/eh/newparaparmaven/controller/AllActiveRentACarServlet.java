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

public class AllActiveRentACarServlet extends HttpServlet {

    private conRs getActiveRentACar;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pstm;
    private int orgRow;
    private int[] allUserId;
    private String[] firstName;
    private String[] lastName;
    private String[] email;
    private String[] phoneNo;
    private String[] divisionName;
    private String[] districtName;
    private String[] thanaName;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            getActiveRentACar = GetRentACarTable.getActiveRentACar();

            con = getActiveRentACar.getCon();
            rs = getActiveRentACar.getRs();
            pstm = getActiveRentACar.getPstm();

            rs.last();
            orgRow = rs.getRow();
            allUserId = new int[orgRow];
            firstName = new String[orgRow];
            lastName = new String[orgRow];
            email = new String[orgRow];
            phoneNo = new String[orgRow];
            divisionName = new String[orgRow];
            districtName = new String[orgRow];
            thanaName = new String[orgRow];
            rs.beforeFirst();
            int i = 0;
            while (rs.next()) {
                allUserId[i] = rs.getInt("all_user_id");
                firstName[i] = rs.getString("first_name");
                lastName[i] = rs.getString("last_name");
                email[i] = rs.getString("email");
                phoneNo[i] = rs.getString("phone_no");
                divisionName[i] = rs.getString("division_name");
                districtName[i] = rs.getString("district_name");
                thanaName[i] = rs.getString("police_station_name");
                i++;
            }
            for (i = 0; i < orgRow; i++) {
                response.setContentType("text/plain");
                response.getWriter().write("<tr>"
                        + "<td>" + (i + 1) + "</td>"
                        + "<td>" + firstName[i] + " " + lastName[i] + "</td>"
                        + "<td>" + email[i] + "</td>"
                        + "<td> " + phoneNo[i] + "</td>"
                        + "<td> " + divisionName[i] + "</td>"
                        + "<td> " + districtName[i] + "</td>"
                        + "<td> " + thanaName[i] + "</td>"
                        + "<td>"
                        + "<button class='btn btn-secondary' type='button' data-dismiss='modal'>"
                        + "<a data-toggle='modal' data-alluserid='" + allUserId[i] + "' class='btn btn-primary open-spceDialog-inActive' href='#addSpecInactive' >Inactive</a>"
                        + "</button>        "
                        + "</td>"
                        + "</tr>");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AllActiveRentACarServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                rs.close();
                pstm.close();
            } catch (SQLException ex) {
                Logger.getLogger(AllActiveRentACarServlet.class.getName()).log(Level.SEVERE, null, ex);
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
