package com.eh.newparaparmaven.controller;

import com.eh.newparaparmaven.classes.AddAllUser;
import com.eh.newparaparmaven.classes.AddLoginDetails;
import com.eh.newparaparmaven.classes.AddUserDetails;
import com.eh.newparaparmaven.classes.DeleteAllUserDetails;
import com.eh.newparaparmaven.classes.DeleteCommonUserDetails;
import com.eh.newparaparmaven.classes.FileUpload;
import com.eh.newparaparmaven.classes.GetMaxId;
import com.eh.newparaparmaven.classes.GetRandomCode;
import com.eh.newparaparmaven.dbConnection.conRs;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.eh.newparaparmaven.model.AllUser;
import com.eh.newparaparmaven.model.CommonUser;
import com.eh.newparaparmaven.model.CuSmsTable;
import com.eh.newparaparmaven.model.LoginTable;

/**
 *
 * @author Md. Emran Hossain
 */
@MultipartConfig(maxFileSize = 169999999)
public class CommonUserRegServlet extends HttpServlet {

    CommonUser cu = new CommonUser();
    AllUser au = new AllUser();
    LoginTable lt = new LoginTable();
    CuSmsTable cst = new CuSmsTable();

    private String firstName;
    private String lastName;
    private String email;
    private String cellNo;
    private Part commonUserPhoto;
    private String address;
    private String password;
    private String ownerPicName;
    private conRs getuserPhoneNo;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pstm;
    private boolean addCommonUserDetails;
    private InetAddress inetAddress;
    private String ip;
    private conRs getMaxCommonUserId;
    private int commonUserMaxId;
    private String userRole;
    private int activeStatus;
    private boolean addAllUser;
    private conRs getMaxAllUserId;
    private int AllUserMaxId;
    private boolean addLoginDetails;
    private String phoneCheck;
    private boolean deleteCommonUserDetails;
    private boolean deleteAllUserDetails;
    private String randomCode;
    private conRs getRandomCode;
    private String randomCodeCheck;
    private String gender;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            System.err.println(request.getContextPath()+"  at  commonUserRegServlet");
            inetAddress = InetAddress.getLocalHost();
            ip = inetAddress.toString();
            firstName = request.getParameter("firstName");
            lastName = request.getParameter("lastName");
            gender = request.getParameter("gender");
            email = request.getParameter("email");
            cellNo = request.getParameter("cellNo");
            commonUserPhoto = request.getPart("commonUserPhoto");
            ownerPicName = FileUpload.fileUpload(commonUserPhoto, request);
            address = request.getParameter("address");
            password = request.getParameter("pass");
            randomCode = request.getParameter("randomCode");

            System.out.println("commonUser ip : " + ip);
            System.out.println("commonUser firstName : " + firstName);
            System.out.println("commonUser lastName : " + lastName);
            System.out.println("commonUser gender : " + gender);
            System.out.println("commonUser email : " + email);
            System.out.println("commonUser cellNo : " + cellNo);
            System.out.println("commonUser ownerPicName part : " + commonUserPhoto);
            System.out.println("commonUser ownerPicName : " + ownerPicName);
            System.out.println("commonUser address : " + address);
            System.out.println("commonUser password : " + password);
            System.out.println("commonUser randomCode : " + randomCode);
            
            cu.setFirst_name(firstName);
            cu.setLast_name(lastName);
            cu.setGender(gender);
            cu.setEmail(email);
            cu.setPhone_no(cellNo);
            cu.setPhoto(ownerPicName);
            cu.setAddress(address);
            cu.setDevice_ime_no("null");
            cu.setDevice_location("null");
            cu.setDevice_ip(ip);

            cst.setRandom_genarated_code(randomCode);

            getRandomCode = GetRandomCode.getRandomCode(cst);
            con = getRandomCode.getCon();
            rs = getRandomCode.getRs();
            pstm = getRandomCode.getPstm();

            if (rs.next()) {
                try {
                    addCommonUserDetails = AddUserDetails.addCommonUserDetails(cu);
                } catch (SQLException ex) {
                    Logger.getLogger(CommonUserRegServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (addCommonUserDetails == true) {
                    try {
                        getMaxCommonUserId = GetMaxId.getMaxCommonUserId();
                        con = getMaxCommonUserId.getCon();
                        rs = getMaxCommonUserId.getRs();
                        pstm = getMaxCommonUserId.getPstm();
                        while (rs.next()) {
                            commonUserMaxId = rs.getInt("common_user_id");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(CommonUserRegServlet.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        try {
                            con.close();
                            rs.close();
                            pstm.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(CommonUserRegServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    userRole = "CommonUser";
                    activeStatus = 1;
                    au.setUser_id(commonUserMaxId);
                    au.setUser_role(userRole);
                    au.setActive_status(activeStatus);
                    try {
                        addAllUser = AddAllUser.addAllUser(au);
                    } catch (SQLException ex) {
                        Logger.getLogger(CommonUserRegServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (addAllUser == true) {
                        try {
                            getMaxAllUserId = GetMaxId.getMaxAllUserId();
                            con = getMaxAllUserId.getCon();
                            rs = getMaxAllUserId.getRs();
                            pstm = getMaxAllUserId.getPstm();
                            while (rs.next()) {
                                AllUserMaxId = rs.getInt("all_user_id");
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(CommonUserRegServlet.class.getName()).log(Level.SEVERE, null, ex);
                        } finally {
                            try {
                                con.close();
                                rs.close();
                                pstm.close();
                            } catch (SQLException ex) {
                                Logger.getLogger(CommonUserRegServlet.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        lt.setAll_user_id(AllUserMaxId);
                        lt.setPhone_no(cellNo);
                        lt.setPassword(password);

                        addLoginDetails = AddLoginDetails.addLoginDetails(lt);
                        if (addLoginDetails == true) {
                            request.getSession().setAttribute("messageTitle", "Reistration Successfull");
                            request.getSession().setAttribute("messageText", "Welcome to our family");
                            request.getSession().setAttribute("typeIcon", "success");
                            response.sendRedirect("index.jsp");
                        } else {
                            System.out.println("2st CommonUser :" + commonUserMaxId);
                            System.out.println("1st allUser :" + AllUserMaxId);
                            deleteCommonUserDetails = DeleteCommonUserDetails.deleteCommonUserDetails(commonUserMaxId);
                            deleteAllUserDetails = DeleteAllUserDetails.deleteAllUserDetails(AllUserMaxId);
                            request.getSession().setAttribute("messageTitle", "Reistration Faild !");
                            request.getSession().setAttribute("messageText", "Please Try Again");
                            request.getSession().setAttribute("typeIcon", "warning");
                            response.sendRedirect("commonUserRegistration.jsp");
                        }

                    } else {
                        System.out.println("1st CommonUser :" + commonUserMaxId);
                        deleteCommonUserDetails = DeleteCommonUserDetails.deleteCommonUserDetails(commonUserMaxId);
                        request.getSession().setAttribute("messageTitle", "Reistration Faild !");
                        request.getSession().setAttribute("messageText", "Please Try Again");
                        request.getSession().setAttribute("typeIcon", "warning");
                        response.sendRedirect("commonUserRegistration.jsp");
                    }

                } else {
                    request.getSession().setAttribute("messageTitle", "Reistration Faild !");
                    request.getSession().setAttribute("messageText", "Please Try Again");
                    request.getSession().setAttribute("typeIcon", "warning");
                    response.sendRedirect("commonUserRegistration.jsp");
                }
            } else {
                request.getSession().setAttribute("messageTitle", "Reistration Faild !");
                request.getSession().setAttribute("messageText", "Code Not Match Please Try Again");
                request.getSession().setAttribute("typeIcon", "warning");
                response.sendRedirect("commonUserRegistration.jsp");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommonUserRegServlet.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
////                con.close();
////                rs.close();
////                pstm.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(CommonUserRegServlet.class.getName()).log(Level.SEVERE, null, ex);
//            }
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
