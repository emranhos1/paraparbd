package com.eh.newparaparmaven.controller;

import com.eh.newparaparmaven.classes.AddBrand;
import com.eh.newparaparmaven.classes.GetBrand;
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

public class AddBikeBrandServlet extends HttpServlet {

    private String bikeBrandName;
    private boolean addBikeBrand;
    private conRs conrs;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pstm;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            bikeBrandName = new String(request.getParameter("bikeBrandName").getBytes("ISO-8859-1"), "UTF-8").trim();

            try {
                conrs = GetBrand.getBikeBrandNameForCheck(bikeBrandName);
                con = conrs.getCon();
                rs = conrs.getRs();
                pstm = conrs.getPstm();

                if (rs.next()) {
                    request.getSession().setAttribute("messageTitle", "Not Insert");
                    request.getSession().setAttribute("messageText", "Brand Name Already Added");
                    request.getSession().setAttribute("typeIcon", "warning");
                    response.sendRedirect("admin/addBikePackage.jsp");
                } else {

                    addBikeBrand = AddBrand.addBikeBrand(bikeBrandName);

                    if (addBikeBrand == true) {
                        request.getSession().setAttribute("messageTitle", "Success");
                        request.getSession().setAttribute("messageText", "Bike Brand Added");
                        request.getSession().setAttribute("typeIcon", "success");
                        response.sendRedirect("admin/addBikePackage.jsp");
                    } else {
                        request.getSession().setAttribute("messageTitle", "Not Insert");
                        request.getSession().setAttribute("messageText", "Please Try Again");
                        request.getSession().setAttribute("typeIcon", "warning");
                        response.sendRedirect("admin/addBikePackage.jsp");
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(AddBikeBrandServlet.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    con.close();
                    rs.close();
                    pstm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AddBikeBrandServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
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
