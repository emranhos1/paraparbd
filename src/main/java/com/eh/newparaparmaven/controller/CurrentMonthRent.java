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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.eh.newparaparmaven.model.RentACarBooking;

public class CurrentMonthRent extends HttpServlet {

    RentACarBooking rentACarBooking = new RentACarBooking();
    private conRs getCurrentMonnthRentACarBooking;
    private SimpleDateFormat dateFormat;
    private Date date;
    private String currentDate;
    private int currentMonth;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pstm;
    private int dataRow;
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
    private String[] pickupAddress;
    private String[] dropAddress;
    private int allUserId;
    private conRs getRentACarOwnerId;
    private int rentACarOwnerId;
    private String[] brandName;
    private String[] modelName;
    private String[] colorName;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            HttpSession session = request.getSession();
            allUserId = (int) session.getAttribute("idUser");
            try {
                getRentACarOwnerId = GetAllUser.getOwnerUserId(allUserId);
                con = getRentACarOwnerId.getCon();
                rs = getRentACarOwnerId.getRs();
                pstm = getRentACarOwnerId.getPstm();
                while (rs.next()) {
                    rentACarOwnerId = rs.getInt("user_id");
                }
            } catch (SQLException ex) {
                Logger.getLogger(CurrentMonthRent.class.getName()).log(Level.SEVERE, null, ex);
            } finally{
                try {
                    con.close();
                    rs.close();
                    pstm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CurrentMonthRent.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
            date = new Date();
            currentDate = dateFormat.format(date);
            currentMonth = date.getMonth() + 1;

            rentACarBooking.setTravel_month(currentMonth);
            rentACarBooking.setRent_a_car_owner_id(rentACarOwnerId);

            try {
                getCurrentMonnthRentACarBooking = GetRentACarBooking.getCurrentMonthRentACarBooking(rentACarBooking);

                con = getCurrentMonnthRentACarBooking.getCon();
                rs = getCurrentMonnthRentACarBooking.getRs();
                pstm = getCurrentMonnthRentACarBooking.getPstm();

                rs.last();
                dataRow = rs.getRow();
                firstName = new String[dataRow];
                lastName = new String[dataRow];
                email = new String[dataRow];
                phoneNo = new String[dataRow];
                brandName = new String[dataRow];
                modelName = new String[dataRow];
                colorName = new String[dataRow];
                origin = new String[dataRow];
                destination = new String[dataRow];
                totalFare = new String[dataRow];
                discountId = new String[dataRow];
                grossFare = new String[dataRow];
                paymentMethod = new String[dataRow];
                travelDate = new String[dataRow];
                orderDate = new String[dataRow];
                pickupAddress = new String[dataRow];
                dropAddress = new String[dataRow];
                rs.beforeFirst();
                int i = 0;
                while (rs.next()) {
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
                    pickupAddress[i] = rs.getString("pickup_address");
                    dropAddress[i] = rs.getString("drop_address");
                    i++;
                }
                for (i = 0; i < dataRow; i++) {
                    response.setContentType("text/plain");
                    response.getWriter().write("<tr>"
                            + "<td>" + (i + 1) + "</td>"
                            + "<td>" + firstName[i] + " " + lastName[i] + " \n" + email[i] + " \n" + phoneNo[i] + " \n"
                            + "</td>"
                            + "<td>" + brandName[i] + " " + modelName[i] + "(" + colorName[i] + ")</td>"
                            + "<td> " + origin[i] + "</td>"
                            + "<td> " + destination[i] + "</td>"
                            + "<td> Total Fare :" + totalFare[i] + " \n Discount :" + discountId[i] + " \n Gross Fare :" + grossFare[i] + " \n Payment Method :" + paymentMethod[i] + "</td>"
                            + "<td> Travel Date : " + travelDate[i] + "\n Order Date : " + orderDate[i] + "</td>"
                            + "<td> " + pickupAddress[i] + "</td>"
                            + "<td> " + dropAddress[i] + "</td>"
                            + "</tr>");
                }
            } catch (SQLException ex) {
                Logger.getLogger(CurrentMonthRent.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    con.close();
                    rs.close();
                    pstm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CurrentMonthRent.class.getName()).log(Level.SEVERE, null, ex);
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
