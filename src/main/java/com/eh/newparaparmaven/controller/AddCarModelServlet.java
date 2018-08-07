package com.eh.newparaparmaven.controller;

import com.eh.newparaparmaven.classes.AddModel;
import com.eh.newparaparmaven.classes.GetModel;
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

public class AddCarModelServlet extends HttpServlet {

    private String carModelName;
    private conRs conrs;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pstm;
    private boolean addCarModel;
    private String carBrandNameDropDown;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            carBrandNameDropDown = new String(request.getParameter("carBrandNameDropDown").getBytes("ISO-8859-1"), "UTF-8");
            carModelName = new String(request.getParameter("carModelName").getBytes("ISO-8859-1"), "UTF-8").trim();

            conrs = GetModel.getCarModelNameForCheck(carModelName);

            con = conrs.getCon();
            rs = conrs.getRs();
            pstm = conrs.getPstm();

            if (rs.next()) {
                request.getSession().setAttribute("messageTitle", "Not Insert");
                request.getSession().setAttribute("messageText", "Model Name Already Added");
                request.getSession().setAttribute("typeIcon", "warning");
                response.sendRedirect("admin/AddCarPakege.jsp");
            } else {

                addCarModel = AddModel.addCarModel(carModelName, carBrandNameDropDown);

                if (addCarModel == true) {
                    request.getSession().setAttribute("messageTitle", "Success");
                    request.getSession().setAttribute("messageText", "Car Model Added");
                    request.getSession().setAttribute("typeIcon", "success");
                    response.sendRedirect("admin/AddCarPakege.jsp");
                } else {
                    request.getSession().setAttribute("messageTitle", "Not Insert");
                    request.getSession().setAttribute("messageText", "Please Try Again");
                    request.getSession().setAttribute("typeIcon", "warning");
                    response.sendRedirect("admin/AddCarPakege.jsp");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddCarModelServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                rs.close();
                pstm.close();
            } catch (SQLException ex) {
                Logger.getLogger(AddCarModelServlet.class.getName()).log(Level.SEVERE, null, ex);
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
