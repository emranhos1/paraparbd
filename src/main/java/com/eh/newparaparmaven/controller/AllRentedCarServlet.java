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
import com.eh.newparaparmaven.model.RentACarBooking;

public class AllRentedCarServlet extends HttpServlet {

    RentACarBooking rentACarBooking = new RentACarBooking();
    private int allUserId;
    private conRs getRentACarOwnerId;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pstm;
    private int rentACarOwnerId;
    private conRs allRentedCar;
    private Connection con1;
    private ResultSet rs1;
    private PreparedStatement pstm1;
    private int orgRow;
    private String[] CommonUserFirstName;
    private String[] CommonUserLastName;
    private String[] CommonUserPhoneNo;
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
    private String[] brandName;
    private String[] modelName;
    private String[] colorName;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            HttpSession session = request.getSession();
            allUserId = (int) session.getAttribute("idUser");
            getRentACarOwnerId = GetAllUser.getOwnerUserId(allUserId);

            con = getRentACarOwnerId.getCon();
            rs = getRentACarOwnerId.getRs();
            pstm = getRentACarOwnerId.getPstm();
            while (rs.next()) {
                rentACarOwnerId = rs.getInt("user_id");
            }
            System.out.println(rentACarOwnerId);
            rentACarBooking.setRent_a_car_owner_id(rentACarOwnerId);
            
            allRentedCar = GetRentACarBooking.getRentedCar(rentACarBooking);
            
            con1 = allRentedCar.getCon();
            rs1 = allRentedCar.getRs();
            pstm1 = allRentedCar.getPstm();
            
            rs1.last();
            orgRow = rs1.getRow();
            CommonUserFirstName = new String[orgRow];
            CommonUserLastName = new String[orgRow];
            CommonUserPhoneNo = new String[orgRow];
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
            dropAddress = new String[orgRow];
            rs1.beforeFirst();
            int i = 0;
            while (rs1.next()) {
                CommonUserFirstName[i] = rs1.getString("first_name");
                CommonUserLastName[i] = rs1.getString("last_name");
                CommonUserPhoneNo[i] = rs1.getString("phone_no");
                brandName[i] = rs1.getString("brand_name");
                modelName[i] = rs1.getString("model_name");
                colorName[i] = rs1.getString("color_name");
                origin[i] = rs1.getString("origin");
                destination[i] = rs1.getString("destination");
                totalFare[i] = rs1.getString("total_fare");
                discountId[i] = rs1.getString("discount_id");
                grossFare[i] = rs1.getString("gross_fare");
                paymentMethod[i] = rs1.getString("payment_method");
                travelDate[i] = rs1.getString("travel_date");
                orderDate[i] = rs1.getString("order_date");
                pickupAddress[i] = rs1.getString("pickup_address");
                dropAddress[i] = rs1.getString("drop_address");
                i++;
            }
            
            for (i = 0; i < orgRow; i++) {
                response.setContentType("text/plain");
                response.getWriter().write("<tr>"
                        + "<td>" + (i + 1) + "</td>"
                        + "<td>" + CommonUserFirstName[i] + " " + CommonUserLastName[i] + "</td>"
                        + "<td>" + CommonUserPhoneNo[i] + "</td>"
                        + "<td>" + brandName[i] + " " + modelName[i] + "("+colorName[i]+")</td>"
                        + "<td> From :" + origin[i] + "\n To :"+ destination[i] +"</td>"
                        + "<td> " + totalFare[i] + "</td>"
                        + "<td> Discount Id : " + discountId[i] + " \n Gross Fare :" + grossFare[i] +" \n Payment Method :" + paymentMethod[i] +"</td>"
                        + "<td> Teavel Date : " + travelDate[i] + " \n Order Date :" + orderDate[i] +"</td>"
                        + "<td>" + pickupAddress[i] +"</td>"
                        + "<td>" + dropAddress[i] +"</td>"
                        + "</tr>");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AllRentedCarServlet.class.getName()).log(Level.SEVERE, null, ex);
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