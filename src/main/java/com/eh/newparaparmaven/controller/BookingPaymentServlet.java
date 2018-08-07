package com.eh.newparaparmaven.controller;

import com.eh.newparaparmaven.classes.AddCuBkashTable;
import com.eh.newparaparmaven.classes.GetRentACarBooking;
import com.eh.newparaparmaven.classes.SendSMSBookingPayment;
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
import com.eh.newparaparmaven.model.CuBkashTable;

public class BookingPaymentServlet extends HttpServlet {

    CuBkashTable cuBkashTable = new CuBkashTable();
    private int commonUserId;
    private int rentacarbookingtableid;
    private conRs getOrderNumber;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pstm;
    private int orderNumber;
    private String paidOrNot;
    private boolean addCuBkashTable;
    private String cuBkashsmsTableId;
    private int totalFare;
    private int advancePay;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            commonUserId = Integer.parseInt(request.getParameter("commonUserId"));
            rentacarbookingtableid = Integer.parseInt(request.getParameter("rentacarbookingtableid"));
            totalFare = Integer.parseInt(request.getParameter("totalFare"));
            System.out.println("common user Id :"+commonUserId);
            System.out.println("total fare :"+totalFare);
            System.out.println("rent a car booking table id :"+rentacarbookingtableid);

            advancePay = totalFare * 10/100 ;
            System.out.println("advancePay : "+advancePay);
            try {
                getOrderNumber = GetRentACarBooking.getOrderNumberRentACarBooking(rentacarbookingtableid);
                con = getOrderNumber.getCon();
                rs = getOrderNumber.getRs();
                pstm = getOrderNumber.getPstm();
                while (rs.next()) {
                    orderNumber = Integer.parseInt(rs.getString("order_number"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(BookingPaymentServlet.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    con.close();
                    rs.close();
                    pstm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BookingPaymentServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            System.out.println(orderNumber);
            String sendSMSForBookingPayment = SendSMSBookingPayment.sendSMSPayment(commonUserId, orderNumber, advancePay);
            String[] output = sendSMSForBookingPayment.split("~");
            String textBody = output[0];
            String smsStatus = output[1];
            System.out.println(sendSMSForBookingPayment);
            paidOrNot = "0";
            cuBkashsmsTableId = "1";
            cuBkashTable.setCommon_user_id(commonUserId);
            cuBkashTable.setPanding_or_not(paidOrNot);
            cuBkashTable.setOrder_no(orderNumber);
            cuBkashTable.setCu_bkash_sms_table_id(cuBkashsmsTableId);
//            if (!sendSMSForBookingPayment.equals("")) {
                try {
                    addCuBkashTable = AddCuBkashTable.addCuBkashTable(cuBkashTable);
                } catch (SQLException ex) {
                    Logger.getLogger(BookingPaymentServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (addCuBkashTable == true) {
                    request.getSession().setAttribute("messageTitle", "Success");
                    request.getSession().setAttribute("messageText", "SMS Sent to User For Bkash");
                    request.getSession().setAttribute("typeIcon", "success");
                    response.sendRedirect("admin/requestFromCommonUser.jsp");
                } else {
                    request.getSession().setAttribute("messageTitle", "Faild !");
                    request.getSession().setAttribute("messageText", "Please Try Again");
                    request.getSession().setAttribute("typeIcon", "warning");
                    response.sendRedirect("admin/requestFromCommonUser.jsp");
                }
//            } else {
//                request.getSession().setAttribute("messageTitle", "Faild !");
//                request.getSession().setAttribute("messageText", "Please Try Again");
//                request.getSession().setAttribute("typeIcon", "warning");
//                response.sendRedirect("admin/requestFromCommonUser.jsp");
//            }
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
