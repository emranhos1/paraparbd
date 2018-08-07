package com.eh.newparaparmaven.controller;

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

public class AllAuthorizedRequestServlet extends HttpServlet {

    private conRs getAuthorizedRequest;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pstm;
    private int orgRow;
    private int[] rentACarBookingTableId;
    private int[] commonUserId;
    private String[] firstName;
    private String[] lastName;
    private String[] email;
    private String[] phoneNo;
    private String[] origin;
    private String[] destination;
    private String[] totalFare;
    private String[] discountId;
    private String[] grossFare;
    private String[] paymentMethod;
    private String[] travelDate;
    private String[] orderDate;
    private String[] pickup_address;
    private String[] drop_address;
    private String[] brandName;
    private String[] modelName;
    private String[] colorName;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            getAuthorizedRequest = GetRentACarBooking.getAuthorizedRequest();

            con = getAuthorizedRequest.getCon();
            rs = getAuthorizedRequest.getRs();
            pstm = getAuthorizedRequest.getPstm();

            rs.last();
            orgRow = rs.getRow();
            rentACarBookingTableId = new int[orgRow];
            commonUserId = new int[orgRow];
            firstName = new String[orgRow];
            lastName = new String[orgRow];
            email = new String[orgRow];
            phoneNo = new String[orgRow];
            brandName = new String[orgRow];
            modelName = new String[orgRow];
            colorName = new String[orgRow];
            origin = new String[orgRow];
            destination = new String[orgRow];
            totalFare = new String[orgRow];
            discountId = new String[orgRow];
            grossFare = new String[orgRow];
            paymentMethod = new String[orgRow];
            travelDate = new String[orgRow];
            orderDate = new String[orgRow];
            pickup_address = new String[orgRow];
            drop_address = new String[orgRow];
            rs.beforeFirst();
            int i = 0;
            while (rs.next()) {
                rentACarBookingTableId[i] = rs.getInt("rent_a_car_booking_table_id");
                commonUserId[i] = rs.getInt("common_user_id");
                firstName[i] = rs.getString("first_name");
                lastName[i] = rs.getString("last_name");
                email[i] = rs.getString("email");
                phoneNo[i] = rs.getString("phone_no");
                brandName[i] = rs.getString("brand_name");
                modelName[i] = rs.getString("model_name");
                colorName[i] = rs.getString("color_name");
                origin[i] = rs.getString("origin");
                destination[i] = rs.getString("destination");
                totalFare[i] = rs.getString("total_fare");
                discountId[i] = rs.getString("discount_id");
                grossFare[i] = rs.getString("gross_fare");
                paymentMethod[i] = rs.getString("payment_method");
                travelDate[i] = rs.getString("travel_date");
                orderDate[i] = rs.getString("order_date");
                pickup_address[i] = rs.getString("pickup_address");
                drop_address[i] = rs.getString("drop_address");
                i++;
            }

            for (i = 0; i < orgRow; i++) {
                response.setContentType("text/plain");
                response.getWriter().write("<tr>"
                        + "<td>" + (i + 1) + "</td>"
                        + "<td>" + firstName[i] + " " + lastName[i] + " \n" + email[i] + " \n" + phoneNo[i] + " \n"
                        + "<input type='hidden' id='commonUserId' name='commonUserId' value='" + commonUserId[i] + "'/>"
                        + "</td>"
                        + "<td>" + brandName[i] + " (" + modelName[i] + ")</td>"
                        + "<td> " + origin[i] + "</td>"
                        + "<td> " + destination[i] + "</td>"
                        + "<td> Total Fare :" + totalFare[i] + " \n Discount :" + discountId[i] + " \n Gross Fare :" + grossFare[i] + " \n Payment Method :" + paymentMethod[i] + "</td>"
                        + "<td> Travel Date : " + travelDate[i] + "\n Order Date : " + orderDate[i] + "</td>"
                        + "<td> " + pickup_address[i] + "</td>"
                        + "<td> " + drop_address[i] + "</td>"
                        + "</tr>");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AllAuthorizedRequestServlet.class.getName()).log(Level.SEVERE, null, ex);
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
