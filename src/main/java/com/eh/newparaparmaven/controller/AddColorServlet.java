package com.eh.newparaparmaven.controller;

import com.eh.newparaparmaven.classes.AddColor;
import com.eh.newparaparmaven.classes.GetColor;
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

public class AddColorServlet extends HttpServlet {

    private String colorName;
    private boolean addColor;
    private conRs conrs;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pstm;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            colorName = new String(request.getParameter("colorName").getBytes("ISO-8859-1"), "UTF-8").trim();

            conrs = GetColor.getColorNameForCheck(colorName);

            con = conrs.getCon();
            rs = conrs.getRs();
            pstm = conrs.getPstm();

            if (rs.next()) {
                request.getSession().setAttribute("messageTitle", "Error!");
                request.getSession().setAttribute("messageText", "Color Name Already Added");
                request.getSession().setAttribute("typeIcon", "success");
                response.sendRedirect("admin/AddColor.jsp");
            } else {

                addColor = AddColor.addColor(colorName);

                if (addColor == true) {
                    request.getSession().setAttribute("messageTitle", "Success");
                    request.getSession().setAttribute("messageText", "Color Added");
                    request.getSession().setAttribute("typeIcon", "success");
                    response.sendRedirect("admin/AddColor.jsp");
                } else {
                    request.getSession().setAttribute("messageTitle", "Error !");
                    request.getSession().setAttribute("messageText", "Please Try Again");
                    request.getSession().setAttribute("typeIcon", "warning");
                    response.sendRedirect("admin/AddColor.jsp");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddColorServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                rs.close();
                pstm.close();
            } catch (SQLException ex) {
                Logger.getLogger(AddColorServlet.class.getName()).log(Level.SEVERE, null, ex);
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
