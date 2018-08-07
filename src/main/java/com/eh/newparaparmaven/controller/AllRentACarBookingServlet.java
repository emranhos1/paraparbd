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

public class AllRentACarBookingServlet extends HttpServlet {

    private conRs getRentACarBooking;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pstm;
    private int orgRow;
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
    private int[] rentACarBookingTableId;
    private String[] pickup_address;
    private String[] drop_address;
    private String[] transactionNo;
    private String[] orderNo;
    private String[] brandName;
    private String[] modelName;
    private String[] colorName;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            getRentACarBooking = GetRentACarBooking.getRentACarBookingTable();

            con = getRentACarBooking.getCon();
            rs = getRentACarBooking.getRs();
            pstm = getRentACarBooking.getPstm();

            rs.last();
            orgRow = rs.getRow();
            transactionNo = new String[orgRow];
            orderNo = new String[orgRow];
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
                transactionNo[i] = rs.getString("transaction_no");
                orderNo[i] = rs.getString("order_number");
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
                System.out.println(orderNo[i]);
                response.setContentType("text/plain");
                response.getWriter().write("<tr>"
                        + "<td>" + (i + 1) + "</td>"
                        + "<td>" + orderNo[i] + " (" +transactionNo[i] + ")</td>"
                        + "<td>" + firstName[i] + " " + lastName[i] + " \n" + email[i] + " \n" + phoneNo[i] + " \n"
                        + "<input type='hidden' id='commonUserId' name='commonUserId' value='" + commonUserId[i] + "'/>"
                        + "<input type='hidden' id='rentACarBookingTableId' name='rentACarBookingTableId' value='" + rentACarBookingTableId[i] + "'/>"
                        + "</td>"
                        + "<td>" + brandName[i] +" ( "+ modelName[i] + ")</td>"
                        + "<td> " + origin[i] + " - " + destination[i] + "</td>"
                        + "<td> Total Fare :" + totalFare[i] + " \n Discount :" + discountId[i] + " \n Gross Fare :" + grossFare[i] + " \n Payment Method :" + paymentMethod[i] + "</td>"
                        + "<td> Travel Date : " + travelDate[i] + "\n Order Date : " + orderDate[i] + "</td>"
                        + "<td> " + pickup_address[i] + "</td>"
                        + "<td> " + drop_address[i] + "</td>"
                        + "<td>"
                        + "<button class='btn btn-secondary' type='button' data-dismiss='modal'>"
                        + "<a data-toggle='modal' data-commonuserid='" + commonUserId[i] + "' data-rentacarbookingtableid='" + rentACarBookingTableId[i] + "' data-totalfare = '"+totalFare[i]+"' class='btn btn-primary open-spceDialog-bookingPayment' href='#addSpecBookingPayment' >Send Payment Request</a>"
                        + "</button> <br><br>       "
                        + "<button class='btn btn-secondary' type='button' data-dismiss='modal'>"
                        + "<a data-toggle='modal' data-commonuserid='" + commonUserId[i] + "' data-rentacarbookingtableid='" + rentACarBookingTableId[i] + "' data-transactionno='" + transactionNo[i] + "' data-orderno='" + orderNo[i] + "' class='btn btn-primary open-spceDialog-carSend' href='#addSpecCarSend' >Authorize</a>"
                        + "</button>        "
                        + "</td>"
                        + "</tr>");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AllRentACarBookingServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                rs.close();
                pstm.close();
            } catch (SQLException ex) {
                Logger.getLogger(AllRentACarBookingServlet.class.getName()).log(Level.SEVERE, null, ex);
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
