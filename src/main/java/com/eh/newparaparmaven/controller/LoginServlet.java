package com.eh.newparaparmaven.controller;

/**
 *
 * @author Md. Emran Hossain
 */
import com.eh.newparaparmaven.classes.AddLoggingHistory;
import com.eh.newparaparmaven.classes.Login;
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
import javax.servlet.http.HttpSession;
import com.eh.newparaparmaven.model.LogginHistoryTable;

public class LoginServlet extends HttpServlet {

    LogginHistoryTable lht = new LogginHistoryTable();
    private String phoneNo;
    private String password;
    private conRs conrs;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pstm;
    private int allUserId;
    private String userRole;
    private boolean addLogginHistoryDetails;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            phoneNo = request.getParameter("phoneNo");
            password = request.getParameter("password");
            
            System.out.println(phoneNo);
            System.out.println(password);

            conrs = Login.getLoginDetails(phoneNo, password);

            con = conrs.getCon();
            rs = conrs.getRs();
            pstm = conrs.getPstm();

            if (rs.next()) {
                allUserId = rs.getInt("all_user_id");
                userRole = rs.getString("user_role");

                switch (userRole) {
                    case "RentACar":
                        request.getSession().setAttribute("messageTitle", "Login Successfull");
                        request.getSession().setAttribute("messageText", "Welcome");
                        request.getSession().setAttribute("typeIcon", "success");
                        response.sendRedirect("rentACar/rentACarDashboard.jsp");
                        break;
                    case "CommonUser":
                        request.getSession().setAttribute("messageTitle", "Login Successfull");
                        request.getSession().setAttribute("messageText", "Welcome");
                        request.getSession().setAttribute("typeIcon", "success");
                        response.sendRedirect("commonUser/commonUserDashboard.jsp");
                        break;
                    case "Biker":
                        request.getSession().setAttribute("messageTitle", "Login Successfull");
                        request.getSession().setAttribute("messageText", "Welcome");
                        request.getSession().setAttribute("typeIcon", "success");
                        response.sendRedirect("biker/bikerDashboard.jsp");
                        break;
                    case "Admin":
                        request.getSession().setAttribute("messageTitle", "Login Successfull");
                        request.getSession().setAttribute("messageText", "Welcome");
                        request.getSession().setAttribute("typeIcon", "success");
                        response.sendRedirect("admin/adminDashboard.jsp");
                        break;
                    case "DWP":
                        request.getSession().setAttribute("messageTitle", "Login Successfull");
                        request.getSession().setAttribute("messageText", "Welcome");
                        request.getSession().setAttribute("typeIcon", "success");
                        response.sendRedirect("dwpAdmin/adminDashboard.jsp");
                        break;
                    default:
                        break;
                }
                lht.setAll_user_id(allUserId);
                lht.setUser_device_mac("null");
                lht.setUser_device_ip("null");
                addLogginHistoryDetails = AddLoggingHistory.addLoginHistoryDetails(lht);
            } else {
                request.getSession().setAttribute("messageTitle", "Login Faild !");
                request.getSession().setAttribute("messageText", "Username or password incorrect");
                request.getSession().setAttribute("typeIcon", "warning");
                response.sendRedirect("index.jsp");
            }

            HttpSession session = request.getSession();
            session.setAttribute("idUser", allUserId);

        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
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
