package com.eh.newparaparmaven.controller;

import com.eh.newparaparmaven.classes.UpdateCar;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.eh.newparaparmaven.model.RentACarOwner;

public class ActiveCarServlet extends HttpServlet {

    RentACarOwner rentACarOwner = new RentACarOwner();
    private int rentACarTableId;
    private boolean activeCar;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            rentACarTableId = Integer.parseInt(request.getParameter("rentACarTableId"));
            
            rentACarOwner.setRent_a_car_owner_id(rentACarTableId);
            try {
                activeCar = UpdateCar.activeCar(rentACarOwner);
            } catch (SQLException ex) {
                Logger.getLogger(ActiveCarServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (activeCar == true) {
                request.getSession().setAttribute("messageTitle", "Success");
                request.getSession().setAttribute("messageText", "This Car is Active Now");
                request.getSession().setAttribute("typeIcon", "success");
                response.sendRedirect("admin/inActiveCar.jsp");
            } else {
                request.getSession().setAttribute("messageTitle", "Error !");
                request.getSession().setAttribute("messageText", "Please Try Again");
                request.getSession().setAttribute("typeIcon", "warning");
                response.sendRedirect("admin/inActiveCar.jsp");
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
