package com.eh.newparaparmaven.controller;

import com.eh.newparaparmaven.classes.GetDistrict;
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

public class AllDistictServlet extends HttpServlet {

    private conRs conrs;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pstm;
    private int orgRow;
    private int[] districtId;
    private String[] divisionName;
    private String[] districtName;
    private String[] createdDate;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            conrs = GetDistrict.getDistrictTable();
            
            con = conrs.getCon();
            rs = conrs.getRs();
            pstm = conrs.getPstm();
            
            rs.last();
            orgRow = rs.getRow();
            districtId = new int[orgRow];
            divisionName = new String[orgRow];
            districtName = new String[orgRow];
            createdDate = new String[orgRow];
            rs.beforeFirst();
            int i = 0;
            while (rs.next()) {
                districtId[i] = rs.getInt("district_id");
                divisionName[i] = rs.getString("division_name");
                districtName[i] = rs.getString("district_name");
                createdDate[i] = rs.getString("created_date");
                i++;
            }
            
            for (i = 0; i < orgRow; i++) {
                response.setContentType("text/plain");
                response.getWriter().write("<tr>"
                        + "<td>" + districtId[i] + "</td>"
                        + "<td>" + divisionName[i] + "</td>"
                        + "<td>" + districtName[i] + "</td>"
                        + "<td>" + createdDate[i] + "</td>"
                        + "</tr>");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AllDistictServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
                rs.close();
                pstm.close();
            } catch (SQLException ex) {
                Logger.getLogger(AllDistictServlet.class.getName()).log(Level.SEVERE, null, ex);
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
