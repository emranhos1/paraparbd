package com.eh.newparaparmaven.controller;

import com.eh.newparaparmaven.classes.UpdateBkashT;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.eh.newparaparmaven.model.CuBkashTable;

public class UpdateBkashTable extends HttpServlet {

    CuBkashTable cuBkashTable = new CuBkashTable();
    private int bkashTableId;
    private String transectionCode;
    private boolean updateBkash;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            bkashTableId = Integer.parseInt(request.getParameter("bkashTableId").trim());
            transectionCode = request.getParameter("transectionCode").trim();

            cuBkashTable.setBkash_table_id(bkashTableId);
            cuBkashTable.setTransaction_no(transectionCode);

            try {
                updateBkash = UpdateBkashT.updateBkashT(cuBkashTable);
            } catch (SQLException ex) {
                Logger.getLogger(UpdateBkashTable.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (updateBkash == true) {
                request.getSession().setAttribute("messageTitle", "Success");
                request.getSession().setAttribute("messageText", "Transection Code sent Successfully Please Wait For Confirmation SMS");
                request.getSession().setAttribute("typeIcon", "success");
                response.sendRedirect("commonUser/pendingBooking.jsp");
            } else {
                request.getSession().setAttribute("messageTitle", "Error !");
                request.getSession().setAttribute("messageText", "Transection Code not sent Please Try Again");
                request.getSession().setAttribute("typeIcon", "warning");
                response.sendRedirect("commonUser/pendingBooking.jsp");
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
