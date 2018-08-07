package com.eh.newparaparmaven.controller;

import com.eh.newparaparmaven.classes.UpdateFareRateTable;
import com.eh.newparaparmaven.model.CuFareRateTable;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateFareRate extends HttpServlet {

    CuFareRateTable cfrt = new CuFareRateTable();

    private int cuFareRateTableId;
    private String parKmRate;
    private String bodyLass200km;
    private String bodyBig200km;
    private String driverCost;
    private String otherCost;
    private boolean updateFareRate;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            cuFareRateTableId = Integer.parseInt(request.getParameter("cuFareRateTableId"));
            parKmRate = request.getParameter("parKmRate");
            bodyLass200km = request.getParameter("bodyLass200km");
            bodyBig200km = request.getParameter("bodyBig200km");
            driverCost = request.getParameter("driverCost");
            otherCost = request.getParameter("otherCost");

            System.out.println("driver :" +driverCost);
            System.out.println("other :" +otherCost);
            cfrt.setCu_fare_rate_table_id(cuFareRateTableId);
            cfrt.setPar_km_rate(parKmRate);
            cfrt.setBodyLess200km(bodyLass200km);
            cfrt.setBodyBig200km(bodyBig200km);
            cfrt.setDriver_cost(driverCost);
            cfrt.setOther_cost(otherCost);

            try {
                updateFareRate = UpdateFareRateTable.updateFareRate(cfrt);
            } catch (SQLException ex) {
                Logger.getLogger(UpdateFareRate.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (updateFareRate == true) {
                request.getSession().setAttribute("messageTitle", "Success");
                request.getSession().setAttribute("messageText", "Fare Rent Update Successfull");
                request.getSession().setAttribute("typeIcon", "success");
                response.sendRedirect("admin/farePanel.jsp");
            } else {
                request.getSession().setAttribute("messageTitle", "Error !");
                request.getSession().setAttribute("messageText", "Please Try Again");
                request.getSession().setAttribute("typeIcon", "warning");
                response.sendRedirect("admin/farePanel.jsp");
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
