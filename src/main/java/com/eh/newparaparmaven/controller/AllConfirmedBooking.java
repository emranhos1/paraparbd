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

public class AllConfirmedBooking extends HttpServlet {

    private int allUserId;
    private conRs getCommonUserId;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pstm;
    private int commonUserId;
    private conRs getRentACarBookingTable;
    private int orgRow;
    private int[] rentACarBookingTableId;
    private String[] firstName;
    private String[] lastName;
    private String[] brandName;
    private String[] modelName;
    private String[] colorName;
    private String[] origin;
    private String[] destination;
    private String[] totalFare;
    private String[] discountId;
    private String[] grossFare;
    private String[] paymentMethod;
    private String[] travelDate;
    private String[] orderDate;
    private String[] pickupAddress;
    private String[] dropDddress;
    private String[] orderNumber;

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
                Logger.getLogger(AllConfirmedBooking.class.getName()).log(Level.SEVERE, null, ex);
            }  finally {
                try {
                    con.close();
                    rs.close();
                    pstm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AllConfirmedBooking.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            try {
                getRentACarBookingTable = GetRentACarBooking.getConfirmBooked(commonUserId);
                
                con = getRentACarBookingTable.getCon();
                rs = getRentACarBookingTable.getRs();
                pstm = getRentACarBookingTable.getPstm();
                rs.last();
//                cracbt.rent_a_car_booking_table_id, raco.first_name, raco.last_name, cb.brand_name, cm.model_name, c.color_name, cracbt.origin, cracbt.destination, cracbt.total_fare, cracbt.discount_id, cracbt.gross_fare, cracbt.payment_method, cracbt.travel_date, cracbt.order_date, cracbt.authorized_or_not, cracbt.pickup_address, cracbt.drop_address, cracbt.order_number
                orgRow = rs.getRow();
                rentACarBookingTableId = new int[orgRow];
                firstName = new String[orgRow];
                lastName = new String[orgRow];
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
                pickupAddress = new String[orgRow];
                dropDddress = new String[orgRow];
                orderNumber = new String[orgRow];
                rs.beforeFirst();
                int i = 0;
                while (rs.next()) {
                    rentACarBookingTableId[i] = rs.getInt("rent_a_car_booking_table_id");
                    firstName[i] = rs.getString("first_name");
                    lastName[i] = rs.getString("last_name");
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
                    pickupAddress[i] = rs.getString("pickup_address");
                    dropDddress[i] = rs.getString("drop_address");
                    orderNumber[i] = rs.getString("order_number");
                    i++;
                }

                for (i = 0; i < orgRow; i++) {
                    response.setContentType("text/plain");
                    response.getWriter().write("<tr>"
                            + "<td>" + (i + 1) + "</td>"
                            + "<td>" + orderNumber[i] + "</td>"
                            + "<td>" + firstName[i]+ " "+ lastName[i] + "</td>"
                            + "<td>" + travelDate[i] + "</td>"
                            + "<td>" + pickupAddress[i] + "</td>"
                            + "</tr>");
                }
            } catch (SQLException ex) {
                Logger.getLogger(AllConfirmedBooking.class.getName()).log(Level.SEVERE, null, ex);
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
