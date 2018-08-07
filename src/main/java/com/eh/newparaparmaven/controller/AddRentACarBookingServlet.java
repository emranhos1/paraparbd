package com.eh.newparaparmaven.controller;

import com.eh.newparaparmaven.classes.AddCuSmsBookingRequest;
import com.eh.newparaparmaven.classes.AddRentACarBooking;
import com.eh.newparaparmaven.classes.GetAllUser;
import com.eh.newparaparmaven.classes.GetRentACarBooking;
import com.eh.newparaparmaven.classes.SendSMSForBooking;
import com.eh.newparaparmaven.dbConnection.conRs;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.eh.newparaparmaven.model.CuSmsBookingRequestTable;
import com.eh.newparaparmaven.model.RentACarBooking;

public class AddRentACarBookingServlet extends HttpServlet {

    RentACarBooking rentACarBooking = new RentACarBooking();
    CuSmsBookingRequestTable csbrt = new CuSmsBookingRequestTable();

    private String origin;
    private String destination;
    private double totalTake;
    private int carCategory;
    private int allUserId;
    private conRs getCommonUserId;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pstm;
    private int commonUserId;
    private boolean addRentACarBooking;
    private String travelDate;
    private String pickupAddress;
    private String DropAddress;
    private String travelD;
    private conRs getOrderNumber;
    private int orderNumber;
    private int orderNo;
    private conRs GetPhoneNo;
    private int phoneNo;
    private String sendSMSForBooking;
    private boolean insertCuSmsBookingRequest;
    private int originDivision;
    private int destinationDivision;
    private int originDistrict;
    private int destinationDistrict;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            travelD = request.getParameter("travelDate").trim();
            carCategory = Integer.parseInt(request.getParameter("carCategory"));
            pickupAddress = request.getParameter("pickupAddress").trim();
            DropAddress = request.getParameter("DropAddress").trim();
            totalTake = Double.parseDouble(request.getParameter("totalTake"));
            originDivision = Integer.parseInt(request.getParameter("divisionNameDropDownOrigin"));
            destinationDivision = Integer.parseInt(request.getParameter("divisionNameDropDownDestination"));
            originDistrict = Integer.parseInt(request.getParameter("distictNameDropDownOrigin"));
            destinationDistrict = Integer.parseInt(request.getParameter("distictNameDropDownDestination"));
            origin = request.getParameter("policeNameDropDownOrigin");
            destination = request.getParameter("policeNameDropDownDestination");

            try {
                getOrderNumber = GetRentACarBooking.getOrderNumberRentACarBooking();
                con = getOrderNumber.getCon();
                rs = getOrderNumber.getRs();
                pstm = getOrderNumber.getPstm();
                while (rs.next()) {
                    orderNumber = rs.getInt("order_number");
                }
            } catch (SQLException ex) {
                Logger.getLogger(AddRentACarBookingServlet.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    con.close();
                    rs.close();
                    pstm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AddRentACarBookingServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (orderNumber == 0) {
                orderNo = 1729;
            } else {
                orderNo = orderNumber + 1;
            }

            DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd KK:mm a");
            DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                travelDate = outputFormat.format(inputFormat.parse(travelD));
            } catch (ParseException ex) {
                Logger.getLogger(AddRentACarBookingServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println("order number : " + orderNumber);
            System.out.println("orderNO :" + orderNo);

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
                Logger.getLogger(AddRentACarBookingServlet.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    con.close();
                    rs.close();
                    pstm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AddRentACarBookingServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            int rentACarOwnerId = 0;
            int discountId = 0;
            double gross_fare = 0;
            String paymentMethod = "Cash";
            String authorized = "0";
            rentACarBooking.setCommon_user_id(commonUserId);
            rentACarBooking.setRent_a_car_owner_id(rentACarOwnerId);
            rentACarBooking.setCar_category_id(carCategory);
            rentACarBooking.setOrigin_division_id(originDivision);
            rentACarBooking.setOrigin_district_id(originDistrict);
            rentACarBooking.setDestination_division_id(destinationDivision);
            rentACarBooking.setDestination_district_id(destinationDistrict);
            rentACarBooking.setOrigin(origin);
            rentACarBooking.setDestination(destination);
            rentACarBooking.setTotal_fare(totalTake);
            rentACarBooking.setDiscount_id(discountId);
            rentACarBooking.setGross_fare(gross_fare);
            rentACarBooking.setPayment_method(paymentMethod);
            rentACarBooking.setTravel_date(travelDate);
            rentACarBooking.setAuthorized_or_not(authorized);
            rentACarBooking.setPickup_address(pickupAddress);
            rentACarBooking.setDrop_address(DropAddress);
            rentACarBooking.setOrder_number(orderNo);

            addRentACarBooking = AddRentACarBooking.addRentACarBookingTable(rentACarBooking);

            if (addRentACarBooking == true) {
                sendSMSForBooking = SendSMSForBooking.sendSMSBooking(commonUserId, orderNo);
                String[] output = sendSMSForBooking.split("~");
                csbrt.setCommon_user_id(commonUserId);
                csbrt.setText_body(output[0]);
                csbrt.setSms_status(output[1]);

                try {
                    insertCuSmsBookingRequest = AddCuSmsBookingRequest.addCuBookingSms(csbrt);
                } catch (SQLException ex) {
                    Logger.getLogger(AddRentACarBookingServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (insertCuSmsBookingRequest == true) {
                    request.getSession().setAttribute("messageTitle", "Success");
                    request.getSession().setAttribute("messageText", "Rent A Car Booking Request Sent");
                    request.getSession().setAttribute("typeIcon", "success");
                    response.sendRedirect("commonUser/rentACarBooking.jsp");
                } else {
                    request.getSession().setAttribute("messageTitle", "Faild !");
                    request.getSession().setAttribute("messageText", "Rent A Car Not Booked");
                    request.getSession().setAttribute("typeIcon", "warning");
                    response.sendRedirect("commonUser/rentACarBooking.jsp");
                }
            } else {
                request.getSession().setAttribute("messageTitle", "Faild !");
                request.getSession().setAttribute("messageText", "Rent A Car Not Booked");
                request.getSession().setAttribute("typeIcon", "warning");
                response.sendRedirect("commonUser/rentACarBooking.jsp");
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
