package com.eh.newparaparmaven.controller;

import com.eh.newparaparmaven.classes.AddDistrict;
import com.eh.newparaparmaven.classes.GetDistrict;
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

public class AddDistrictServlet extends HttpServlet {

    private String divisionNameDropDown;
    private String districtName;
    private boolean addDistrict;
    private conRs conrs;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pstm;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            divisionNameDropDown = new String(request.getParameter("divisionNameDropDown").getBytes("ISO-8859-1"), "UTF-8");
            districtName = new String(request.getParameter("districtName").getBytes("ISO-8859-1"), "UTF-8").trim();

            conrs = GetDistrict.getDistrictNameForCheck(districtName);

            con = conrs.getCon();
            rs = conrs.getRs();
            pstm = conrs.getPstm();

            if (rs.next()) {
                request.getSession().setAttribute("messageTitle", "Error !");
                request.getSession().setAttribute("messageText", "Data Already Stored Try Again");
                request.getSession().setAttribute("typeIcon", "warning");
                response.sendRedirect("admin/AddDistrict.jsp");
            } else {

                addDistrict = AddDistrict.addDistrict(divisionNameDropDown, districtName);

                if (addDistrict == true) {
                    request.getSession().setAttribute("messageTitle", "Success");
                    request.getSession().setAttribute("messageText", "District Added");
                    request.getSession().setAttribute("typeIcon", "success");
                    response.sendRedirect("admin/AddDistrict.jsp");
                } else {
                    request.getSession().setAttribute("messageTitle", "Error !");
                    request.getSession().setAttribute("messageText", "Please Try Again");
                    request.getSession().setAttribute("typeIcon", "warning");
                    response.sendRedirect("admin/AddDistrict.jsp");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddDistrictServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
                rs.close();
                pstm.close();
            } catch (SQLException ex) {
                Logger.getLogger(AddDistrictServlet.class.getName()).log(Level.SEVERE, null, ex);
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
