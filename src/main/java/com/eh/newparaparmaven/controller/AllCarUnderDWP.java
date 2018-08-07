package com.eh.newparaparmaven.controller;

import com.eh.newparaparmaven.classes.GetAllUser;
import com.eh.newparaparmaven.classes.GetDWPOwnerTable;
import com.eh.newparaparmaven.classes.GetRentACarTable;
import com.eh.newparaparmaven.dbConnection.conRs;
import com.eh.newparaparmaven.model.RentACarOwner;
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

public class AllCarUnderDWP extends HttpServlet {

    RentACarOwner rentACarOwner = new RentACarOwner();
    private int allUserId;
    private conRs getDWPOwnerId;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pstm;
    private int DWPOwnerId;
    private conRs getDWPOwnerDistrictId;
    private int DWPOwnerDistId;
    private conRs getRentACarOwnerId;
    private int orgRow;
    private int[] carCategoryId;
    private String[] brandName;
    private String[] modelName;
    private String[] colorName;
    private String[] rentACarOwnerFirstName;
    private String[] rentACarOwnerLastName;
    private String[] rentACarOwnerEmail;
    private String[] rentACarOwnerPhoneNo;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            HttpSession session = request.getSession();
            allUserId = (int) session.getAttribute("idUser");
            try {
                getDWPOwnerId = GetAllUser.getOwnerUserId(allUserId);
                con = getDWPOwnerId.getCon();
                rs = getDWPOwnerId.getRs();
                pstm = getDWPOwnerId.getPstm();
                while (rs.next()) {
                    DWPOwnerId = rs.getInt("user_id");
                }
                System.out.println(DWPOwnerId);
            } catch (SQLException ex) {
                Logger.getLogger(CurrentMonthRentForDWP.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    con.close();
                    rs.close();
                    pstm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CurrentMonthRentForDWP.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            try {
                getDWPOwnerDistrictId = GetDWPOwnerTable.getDWPOwnerDistId(DWPOwnerId);
                con = getDWPOwnerDistrictId.getCon();
                rs = getDWPOwnerDistrictId.getRs();
                pstm = getDWPOwnerDistrictId.getPstm();
                while (rs.next()) {
                    DWPOwnerDistId = rs.getInt("district_id");
                }
                System.out.println(DWPOwnerDistId);
            } catch (SQLException ex) {
                Logger.getLogger(CurrentMonthRentForDWP.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    con.close();
                    rs.close();
                    pstm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CurrentMonthRentForDWP.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            rentACarOwner.setDistrict_id(DWPOwnerDistId);

            try {
                getRentACarOwnerId = GetRentACarTable.getRentACarIdInDist(rentACarOwner);
                con = getRentACarOwnerId.getCon();
                rs = getRentACarOwnerId.getRs();
                pstm = getRentACarOwnerId.getPstm();
                
                rs.last();
                orgRow = rs.getRow();
                rentACarOwnerFirstName = new String[orgRow];
                rentACarOwnerLastName = new String[orgRow];
                rentACarOwnerEmail = new String[orgRow];
                rentACarOwnerPhoneNo = new String[orgRow];
                carCategoryId = new int[orgRow];
                brandName = new String[orgRow];
                modelName = new String[orgRow];
                colorName = new String[orgRow];
                rs.beforeFirst();
                int i = 0;
                while (rs.next()) {
                    rentACarOwnerFirstName[i] = rs.getString("first_name");
                    rentACarOwnerLastName[i] = rs.getString("last_name");
                    rentACarOwnerEmail[i] = rs.getString("email");
                    rentACarOwnerPhoneNo[i] = rs.getString("phone_no");
                    carCategoryId[i] = rs.getInt("car_category_id");
                    brandName[i] = rs.getString("brand_name");
                    modelName[i] = rs.getString("model_name");
                    colorName[i] = rs.getString("color_name");
                    i++;
                }
                
                for (i = 0; i < orgRow; i++) {
                response.setContentType("text/plain");
                response.getWriter().write("<tr>"
                        + "<td>" + (i + 1) + "</td>"
                        + "<td>" + rentACarOwnerFirstName[i] + " " + rentACarOwnerLastName[i] + "<br> Phone No : " + rentACarOwnerPhoneNo[i] + "<br> Email : " + rentACarOwnerEmail[i] + "</td>"
                        + "<td>" + brandName[i] + "</td>"
                        + "<td>" + modelName[i] + "</td>"
                        + "<td>" + colorName[i] + "</td>"
                        + "</tr>");
            }
                
            } catch (SQLException ex) {
                Logger.getLogger(AllCarUnderDWP.class.getName()).log(Level.SEVERE, null, ex);
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
    }// </editor-fold>

}
