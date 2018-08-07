package com.eh.newparaparmaven.controller;

import com.eh.newparaparmaven.classes.UpdateLoginUser;
import com.eh.newparaparmaven.model.LoginTable;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdatePassword extends HttpServlet {

    LoginTable loginTable = new LoginTable();
    private String loginTableId;
    private String newPass;
    private boolean updateCommonUserDetails;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
          newPass = request.getParameter("newPass"); 
          loginTableId = request.getParameter("loginTableId");
            System.out.println(loginTableId);
            System.out.println(newPass);
          loginTable.setLogin_table_id(Integer.parseInt(loginTableId));
          loginTable.setPassword(newPass);
          
            try {
                updateCommonUserDetails = UpdateLoginUser.updateLoginPass(loginTable);
            } catch (SQLException ex) {
                Logger.getLogger(UpdatePassword.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (updateCommonUserDetails == true) {
                request.getSession().setAttribute("messageTitle", "Success");
                request.getSession().setAttribute("messageText", "Update Successfull");
                request.getSession().setAttribute("typeIcon", "success");
                response.sendRedirect("index.jsp");
            } else {
                request.getSession().setAttribute("messageTitle", "Error !");
                request.getSession().setAttribute("messageText", "Please Try Again");
                request.getSession().setAttribute("typeIcon", "warning");
                response.sendRedirect("forgetPassword.jsp");
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
