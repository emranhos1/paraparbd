package com.eh.newparaparmaven.controller;

import com.eh.newparaparmaven.classes.GetAllUser;
import com.eh.newparaparmaven.classes.GetCategory;
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
import javax.servlet.http.HttpSession;

public class AllCarCategoryServlet extends HttpServlet {

    private conRs conrs;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pstm;
    private int orgRow;
    private int[] carCategoryId;
    private String[] brandName;
    private String[] modelName;
    private String[] colorName;
    private int allUserId;
    private conRs getRentACarId;
    private int rentACarId;
    private Connection con1;
    private ResultSet rs1;
    private PreparedStatement pstm1;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            HttpSession session = request.getSession();
            allUserId = (int) session.getAttribute("idUser");
            getRentACarId = GetAllUser.getOwnerUserId(allUserId);

            con = getRentACarId.getCon();
            rs = getRentACarId.getRs();
            pstm = getRentACarId.getPstm();
            while (rs.next()) {
                rentACarId = rs.getInt("user_id");
            }
            
            conrs = GetCategory.getCategoryNames(rentACarId);
            
            con1 = conrs.getCon();
            rs1 = conrs.getRs();
            pstm1 = conrs.getPstm();

            rs1.last();
            orgRow = rs1.getRow();
            carCategoryId = new int[orgRow];
            brandName = new String[orgRow];
            modelName = new String[orgRow];
            colorName = new String[orgRow];
            rs1.beforeFirst();
            int i = 0;
            while (rs1.next()) {
                carCategoryId[i] = rs1.getInt("car_category_id");
                brandName[i] = rs1.getString("brand_name");
                modelName[i] = rs1.getString("model_name");
                colorName[i] = rs1.getString("color_name");
                i++;
            }
            for (i = 0; i < orgRow; i++) {
                response.setContentType("text/plain");
                response.getWriter().write("<tr>"
                        + "<td>" + carCategoryId[i] + "</td>"
                        + "<td>" + brandName[i] + "</td>"
                        + "<td>" + modelName[i] + "</td>"
                        + "<td>" + colorName[i] + "</td>"
                        + "</tr>");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AllCarCategoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
                con1.close();
                rs.close();
                rs1.close();
                pstm.close();
                pstm1.close();
            } catch (SQLException ex) {
                Logger.getLogger(AllCarCategoryServlet.class.getName()).log(Level.SEVERE, null, ex);
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
