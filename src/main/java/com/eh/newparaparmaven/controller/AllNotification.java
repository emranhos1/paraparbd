package com.eh.newparaparmaven.controller;

import com.eh.newparaparmaven.classes.GetAllUser;
import com.eh.newparaparmaven.classes.GetRentACarBooking;
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

public class AllNotification extends HttpServlet {

    private int allUserId;
    private conRs getCommonUserId;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pstm;
    private int commonUserId;
    private conRs getRentACarBooking;
    private int orgRow;
    private String[] RACWFirstName;
    private String[] RACWLastName;
    private String[] RACWPhoneNo;
    private String[] origin;
    private String[] destination;
    private String[] totalFare;
    private String[] paymentMethod;
    private String[] travelDate;
    private String[] orderDate;
    private String[] discountId;
    private String[] grossFare;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            HttpSession session = request.getSession();
            allUserId = (int) session.getAttribute("idUser");
            try {
                getCommonUserId = GetAllUser.getOwnerUserId(allUserId);
                con = getCommonUserId.getCon();
                rs = getCommonUserId.getRs();
                pstm = getCommonUserId.getPstm();
                while (rs.next()) {
                    commonUserId = rs.getInt("user_id");
                }
            } catch (SQLException ex) {
                Logger.getLogger(AllNotification.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    con.close();
                    rs.close();
                    pstm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AllNotification.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            try {
                getRentACarBooking = GetRentACarBooking.getCommonUserHistory(commonUserId);
                con = getRentACarBooking.getCon();
                rs = getRentACarBooking.getRs();
                pstm = getRentACarBooking.getPstm();
                rs.last();
                orgRow = rs.getRow();
                RACWFirstName = new String[orgRow];
                RACWLastName = new String[orgRow];
                RACWPhoneNo = new String[orgRow];
                origin = new String[orgRow];
                destination = new String[orgRow];
                totalFare = new String[orgRow];
                paymentMethod = new String[orgRow];
                travelDate = new String[orgRow];
                orderDate = new String[orgRow];
                discountId = new String[orgRow];
                grossFare = new String[orgRow];
                rs.beforeFirst();
                int i = 0;
                while (rs.next()) {
                    RACWFirstName[i] = rs.getString("first_name");
                    RACWLastName[i] = rs.getString("last_name");
                    RACWPhoneNo[i] = rs.getString("phone_no");
                    origin[i] = rs.getString("origin");
                    destination[i] = rs.getString("destination");
                    totalFare[i] = rs.getString("total_fare");
                    paymentMethod[i] = rs.getString("payment_method");
                    travelDate[i] = rs.getString("travel_date");
                    orderDate[i] = rs.getString("order_date");
                    discountId[i] = rs.getString("discount_id");
                    grossFare[i] = rs.getString("gross_fare");
                    i++;
                }

                for (i = 0; i < orgRow; i++) {
                    response.setContentType("text/plain");
                    response.getWriter().write("<tr>"
                            + "<td>" + (i + 1) + "</td>"
                            + "<td>" + RACWFirstName[i] + " " + RACWLastName[i] + "<br> Phone No : " + RACWPhoneNo[i] + "</td>"
                            + "<td> From : " + origin[i] + "<br> To : " + destination[i] + "</td>"
                            + "<td> Total Fare : " + totalFare[i] + "<br> Payment Method : " + paymentMethod[i] + "</td>"
                            + "<td> Travel Date : " + travelDate[i] + "<br> Order Date : " + orderDate[i] + "</td>"
                            + "<td> Discount Id : " + discountId[i] + "<br> Gross Fare : " + grossFare[i] + "</td>"
                            + "</tr>");
                }
            } catch (SQLException ex) {
                Logger.getLogger(AllNotification.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    con.close();
                    rs.close();
                    pstm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AllNotification.class.getName()).log(Level.SEVERE, null, ex);
                }
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
