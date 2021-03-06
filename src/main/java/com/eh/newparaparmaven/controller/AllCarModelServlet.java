package com.eh.newparaparmaven.controller;

import com.eh.newparaparmaven.classes.GetModel;
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

public class AllCarModelServlet extends HttpServlet {

    private conRs conrs;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pstm;
    private int orgRow;
    private int[] carModelId;
    private String[] modelName;
    private String[] createdDate;
    private String[] brandName;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            conrs = GetModel.getCarModelTable();

            con = conrs.getCon();
            rs = conrs.getRs();
            pstm = conrs.getPstm();

            rs.last();
            orgRow = rs.getRow();
            carModelId = new int[orgRow];
            modelName = new String[orgRow];
            brandName = new String[orgRow];
            createdDate = new String[orgRow];
            rs.beforeFirst();
            int i = 0;
            while (rs.next()) {
                carModelId[i] = rs.getInt("car_model_id");
                modelName[i] = rs.getString("model_name");
                brandName[i] = rs.getString("brand_name");
                createdDate[i] = rs.getString("created_date");
                i++;
            }

            for (i = 0; i < orgRow; i++) {
                response.setContentType("text/plain");
                response.getWriter().write("<tr>"
                        + "<td>" + carModelId[i] + "</td>"
                        + "<td>" + modelName[i] + "</td>"
                        + "<td>" + brandName[i] + "</td>"
                        + "<td>" + createdDate[i] + "</td>"
                        + "</tr>");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AllCarModelServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                rs.close();
                pstm.close();
            } catch (SQLException ex) {
                Logger.getLogger(AllCarModelServlet.class.getName()).log(Level.SEVERE, null, ex);
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
