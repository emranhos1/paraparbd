package com.eh.newparaparmaven.controller;

/**
 *
 * @author Md. Emran Hossain
 */
import com.eh.newparaparmaven.classes.AddAllUser;
import com.eh.newparaparmaven.classes.AddLoginDetails;
import com.eh.newparaparmaven.classes.AddUserDetails;
import com.eh.newparaparmaven.classes.DeleteAllUserDetails;
import com.eh.newparaparmaven.classes.DeleteOwnerDetails;
import com.eh.newparaparmaven.classes.FileUpload;
import com.eh.newparaparmaven.classes.GetMaxId;
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
import com.eh.newparaparmaven.model.LoginTable;
import com.eh.newparaparmaven.model.RentACarOwner;

@MultipartConfig(maxFileSize = 169999999)
public class RentECarRegServlet extends HttpServlet {

    RentACarOwner raco = new RentACarOwner();
    AllUser au = new AllUser();
    LoginTable lt = new LoginTable();

    private Part ownerPhoto;
    private boolean addOwnerDetails;
    private Part nidPhoto;
    private Part tinPhoto;
    private Part tradeLicensePhoto;
    private conRs getMaxOnwerId;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pstm;
    private int ownerMaxId;
    private String userRole;
    private int activeStatus;
    private boolean addAllUser;
    private conRs getMaxAllUserId;
    private int AllUserMaxId;
    private boolean addLoginDetails;
    private String ownerPicName;
    private String nidPicName;
    private String tinPicName;
    private String tradeLicensePicName;
    private String ip;
    private conRs getOwnerPhoneNo;
    private String phoneCheck;
    private boolean deleteOwnerDetails;
    private boolean deleteAllUserDetails;
    private String firstName;
    private String lastName;
    private String email;
    private String cellNo;
    private String gender;
    private int divisionNameDropDown;
    private int distictNameDropDown;
    private int policeNameDropDown;
    private String address;
    private String zipCode;
    private String nidNo;
    private String tinNo;
    private String tradeLicenseNo;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            firstName = request.getParameter("firstName");
            lastName = request.getParameter("lastName");
            email = request.getParameter("email");
            cellNo = request.getParameter("cellNo");
            gender = request.getParameter("gender");
            divisionNameDropDown = Integer.parseInt(request.getParameter("divisionNameDropDown"));
            distictNameDropDown = Integer.parseInt(request.getParameter("distictNameDropDown"));
            policeNameDropDown = Integer.parseInt(request.getParameter("policeNameDropDown"));
            address = request.getParameter("address");
            zipCode = request.getParameter("zipCode");
            nidNo = request.getParameter("nidNo");
            tinNo = request.getParameter("tinNo");
            tradeLicenseNo = request.getParameter("tradeLicenseNo");
            ip = InetAddress.getLocalHost().toString();
            ownerPhoto = request.getPart("ownerPhoto");
            ownerPicName = FileUpload.fileUpload(ownerPhoto, request);
            nidPhoto = request.getPart("nidPhoto");
            nidPicName = FileUpload.fileUpload(nidPhoto, request);
            tinPhoto = request.getPart("tinPhoto");
            tinPicName = FileUpload.fileUpload(tinPhoto, request);
            tradeLicensePhoto = request.getPart("tradeLicensePhoto");
            tradeLicensePicName = FileUpload.fileUpload(tradeLicensePhoto, request);
            
            raco.setFirst_name(firstName);
            raco.setLast_name(lastName);
            raco.setEmail(email);
            raco.setPhone_no(cellNo);
            raco.setGender(gender);
            raco.setOwner_photo(ownerPicName);
            raco.setNid_scan_copy(nidPicName);
            raco.setTin_scan_copy(tinPicName);
            raco.setTrades_scan_copy(tradeLicensePicName);
            raco.setDivision_id(divisionNameDropDown);
            raco.setDistrict_id(distictNameDropDown);
            raco.setThana_id(policeNameDropDown);
            raco.setAddress(address);
            raco.setZip_code(zipCode);
            raco.setNid_no(nidNo);
            raco.setTin_no(tinNo);
            raco.setTrade_lisence_no(tradeLicenseNo);
            raco.setRent_device_emi_no(ip);

            try {
                addOwnerDetails = AddUserDetails.addOwnerDetails(raco);
            } catch (SQLException ex) {
                Logger.getLogger(RentECarRegServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (addOwnerDetails == true) {
                try {
                    getMaxOnwerId = GetMaxId.getMaxOwnerId();
                    con = getMaxOnwerId.getCon();
                    rs = getMaxOnwerId.getRs();
                    pstm = getMaxOnwerId.getPstm();
                    while (rs.next()) {
                        ownerMaxId = rs.getInt("rent_a_car_owner_id");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(RentECarRegServlet.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        con.close();
                        rs.close();
                        pstm.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(RentECarRegServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                userRole = "RentACar";
                activeStatus = 0;
                au.setUser_id(ownerMaxId);
                au.setUser_role(userRole);
                au.setActive_status(activeStatus);

                try {
                    addAllUser = AddAllUser.addAllUser(au);
                } catch (SQLException ex) {
                    Logger.getLogger(RentECarRegServlet.class.getName()).log(Level.SEVERE, null, ex);
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
                        Logger.getLogger(RentECarRegServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    lt.setAll_user_id(AllUserMaxId);
                    lt.setPhone_no(request.getParameter("cellNo").trim());
                    lt.setPassword(request.getParameter("pass").trim());

                    addLoginDetails = AddLoginDetails.addLoginDetails(lt);
                    if (addLoginDetails == true) {
                        request.getSession().setAttribute("messageTitle", "Success");
                        request.getSession().setAttribute("messageText", "Reistration Successfull We will Contuct You Vary Soon");
                        request.getSession().setAttribute("typeIcon", "success");
                        response.sendRedirect("rentACarOwnerRegistration.jsp");
                    } else {
                        System.out.println("1st ownerId :" + ownerMaxId);
                        System.out.println("1st allUser :" + AllUserMaxId);
                        deleteOwnerDetails = DeleteOwnerDetails.deleteOwnerDetails(ownerMaxId);
                        deleteAllUserDetails = DeleteAllUserDetails.deleteAllUserDetails(AllUserMaxId);
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(RentECarRegServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        boolean deleteOwnerPic = FileUpload.fileDelete(ownerPicName, request);
                        boolean deleteNidPic = FileUpload.fileDelete(nidPicName, request);
                        boolean deleteTinPicName = FileUpload.fileDelete(tinPicName, request);
                        boolean deletetradeLicensePic = FileUpload.fileDelete(tradeLicensePicName, request);
                        request.getSession().setAttribute("messageTitle", "Registration Faild !");
                        request.getSession().setAttribute("messageText", "Please Try Again");
                        request.getSession().setAttribute("typeIcon", "warning");
                        response.sendRedirect("rentACarOwnerRegistration.jsp");
                    }
                } else {
                    System.out.println("1st ownerId :" + ownerMaxId);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(RentECarRegServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    deleteOwnerDetails = DeleteOwnerDetails.deleteOwnerDetails(ownerMaxId);
                    boolean deleteOwnerPic = FileUpload.fileDelete(ownerPicName, request);
                    boolean deleteNidPic = FileUpload.fileDelete(nidPicName, request);
                    boolean deleteTinPicName = FileUpload.fileDelete(tinPicName, request);
                    boolean deletetradeLicensePic = FileUpload.fileDelete(tradeLicensePicName, request);

                    request.getSession().setAttribute("messageTitle", "Registration Faild !");
                    request.getSession().setAttribute("messageText", "Please Try Again");
                    request.getSession().setAttribute("typeIcon", "warning");
                    response.sendRedirect("rentACarOwnerRegistration.jsp");
                }
            } else {
                System.out.println("1st Block");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(RentECarRegServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                boolean deleteOwnerPic = FileUpload.fileDelete(ownerPicName, request);
                boolean deleteNidPic = FileUpload.fileDelete(nidPicName, request);
                boolean deleteTinPicName = FileUpload.fileDelete(tinPicName, request);
                boolean deletetradeLicensePic = FileUpload.fileDelete(tradeLicensePicName, request);
                request.getSession().setAttribute("messageTitle", "Registration Faild !");
                request.getSession().setAttribute("messageText", "Please Try Again");
                request.getSession().setAttribute("typeIcon", "warning");
                response.sendRedirect("rentACarOwnerRegistration.jsp");
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
