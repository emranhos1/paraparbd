package com.eh.newparaparmaven.controller;

import com.eh.newparaparmaven.classes.GetAllUser;
import com.eh.newparaparmaven.classes.UpdateLoginUser;
import com.eh.newparaparmaven.classes.UpdateUserDetails;
import com.eh.newparaparmaven.dbConnection.conRs;
import com.eh.newparaparmaven.model.CommonUser;
import com.eh.newparaparmaven.model.LoginTable;
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
import javax.servlet.http.HttpSession;

public class UpdateUserInfoServlet extends HttpServlet {

    CommonUser cu = new CommonUser();
    LoginTable lt = new LoginTable();

    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String password;
    private int allUserId;
    private conRs getCommonUserId;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pstm;
    private int commonUserId;
    private boolean updateCommonUserDetails;
    private boolean updateLoginTable;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            firstName = request.getParameter("firstName");
            lastName = request.getParameter("lastName");
            email = request.getParameter("email");
            address = request.getParameter("address");
            password = request.getParameter("password");

            HttpSession session = request.getSession();
            allUserId = (int) session.getAttribute("idUser");
            getCommonUserId = GetAllUser.getOwnerUserId(allUserId);

            con = getCommonUserId.getCon();
            rs = getCommonUserId.getRs();
            pstm = getCommonUserId.getPstm();
            while (rs.next()) {
                commonUserId = rs.getInt("user_id");
            }

            cu.setCommon_user_id(commonUserId);
            cu.setFirst_name(firstName);
            cu.setLast_name(lastName);
            cu.setEmail(email);
            cu.setAddress(address);

            updateCommonUserDetails = UpdateUserDetails.updateCommonUserDetails(cu);

            lt.setAll_user_id(allUserId);
            lt.setPassword(password);

            updateLoginTable = UpdateLoginUser.updateLoginDetails(lt);

            if (updateCommonUserDetails == true) {
                if (updateLoginTable == true) {
                    request.getSession().setAttribute("messageTitle", "Success");
                    request.getSession().setAttribute("messageText", "Update Successfull");
                    request.getSession().setAttribute("typeIcon", "success");
                    response.sendRedirect("commonUser/userProfile.jsp");
                } else {
                    request.getSession().setAttribute("messageTitle", "Error !");
                    request.getSession().setAttribute("messageText", "Please Try Again");
                    request.getSession().setAttribute("typeIcon", "warning");
                    response.sendRedirect("commonUser/userProfile.jsp");
                }
            } else {
                request.getSession().setAttribute("messageTitle", "Error !");
                request.getSession().setAttribute("messageText", "Please Try Again");
                request.getSession().setAttribute("typeIcon", "warning");
                response.sendRedirect("commonUser/userProfile.jsp");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UpdateUserInfoServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                rs.close();
                pstm.close();
            } catch (SQLException ex) {
                Logger.getLogger(UpdateUserInfoServlet.class.getName()).log(Level.SEVERE, null, ex);
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
