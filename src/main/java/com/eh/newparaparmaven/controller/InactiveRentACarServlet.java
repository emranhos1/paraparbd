package com.eh.newparaparmaven.controller;

import com.eh.newparaparmaven.classes.UpdateRentECar;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.eh.newparaparmaven.model.AllUser;

public class InactiveRentACarServlet extends HttpServlet {

    AllUser allUser = new AllUser();
    private int allUserId;
    private boolean inActiveRentACar;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            allUserId = Integer.parseInt(request.getParameter("allUserId"));
            allUser.setAll_user_id(allUserId);
            inActiveRentACar = UpdateRentECar.inActiveRentACar(allUser);

            if (inActiveRentACar == true) {
                request.getSession().setAttribute("messageTitle", "Success");
                request.getSession().setAttribute("messageText", "This Rent A Car is Inactive Now");
                request.getSession().setAttribute("typeIcon", "success");
                response.sendRedirect("admin/activeRentACar.jsp");
            } else {
                request.getSession().setAttribute("messageTitle", "Error !");
                request.getSession().setAttribute("messageText", "Please Try Again");
                request.getSession().setAttribute("typeIcon", "warning");
                response.sendRedirect("admin/activeRentACar.jsp");
            }
        } catch (SQLException ex) {
            Logger.getLogger(InactiveRentACarServlet.class.getName()).log(Level.SEVERE, null, ex);
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
