package com.eh.newparaparmaven.controller;

import com.eh.newparaparmaven.classes.SendSMSForConfirmBooking;
import com.eh.newparaparmaven.classes.UpdateRentACarBooking;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.eh.newparaparmaven.model.RentACarBooking;

public class ConfirmBookingServlet extends HttpServlet {

    RentACarBooking rentACarBooking = new RentACarBooking();

    private int rentacarbookingtableid;
    private int rentACarOwnerId;
    private boolean updateBooking;
    private String transactionNo;
    private int orderNo;
    private int commonuUserId;
    private String confirmBooking;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            rentacarbookingtableid = Integer.parseInt(request.getParameter("rentacarbookingtableid"));
            rentACarOwnerId = Integer.parseInt(request.getParameter("rentACarOwner"));
            transactionNo = request.getParameter("transactionNo");
            orderNo = Integer.parseInt(request.getParameter("orderNo"));
            commonuUserId = Integer.parseInt(request.getParameter("commonuUserId"));

            rentACarBooking.setRent_a_car_booking_table_id(rentacarbookingtableid);
            rentACarBooking.setRent_a_car_owner_id(rentACarOwnerId);

            System.out.println(rentacarbookingtableid);
            System.out.println(rentACarOwnerId);
            System.out.println(transactionNo);
            System.out.println(orderNo);
            System.out.println(commonuUserId);
            updateBooking = UpdateRentACarBooking.updateRentACarBooking(rentACarBooking);

            if (updateBooking == true) {
                confirmBooking = SendSMSForConfirmBooking.sendSMSConfirmBooking(commonuUserId, orderNo);
                request.getSession().setAttribute("messageTitle", "Success");
                request.getSession().setAttribute("messageText", "Booked Confirmed");
                request.getSession().setAttribute("typeIcon", "success");
                response.sendRedirect("admin/requestFromCommonUser.jsp");
            } else {
                request.getSession().setAttribute("messageTitle", "Error !");
                request.getSession().setAttribute("messageText", "Please Try Again");
                request.getSession().setAttribute("typeIcon", "warning");
                response.sendRedirect("admin/requestFromCommonUser.jsp");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConfirmBookingServlet.class.getName()).log(Level.SEVERE, null, ex);
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
