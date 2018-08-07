package com.eh.newparaparmaven.controller;

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
import com.eh.newparaparmaven.model.DwpOwner;
import com.eh.newparaparmaven.model.LoginTable;

@MultipartConfig(maxFileSize = 169999999)
public class DWPRegServlet extends HttpServlet {

    DwpOwner dwpOwner = new DwpOwner();
    AllUser au = new AllUser();
    LoginTable lt = new LoginTable();

    private String firstName;
    private String lastName;
    private String email;
    private String cellNo;
    private String divisionNameDropDown;
    private String distictNameDropDown;
    private String policeNameDropDown;
    private String address;
    private String zipCode;
    private String gender;
    private String nidNo;
    private String tinNo;
    private String tradeLicenseNo;
    private String pass;
    private Part nidPhoto;
    private Part tinPhoto;
    private Part tradeLicensePhoto;
    private String nidPicName;
    private String tinPicName;
    private String tradeLicensePicName;
    private boolean adddwpOwnerDetails;
    private conRs getMaxDwpOnwerId;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pstm;
    private int dwpOwnerMaxId;
    private String userRole;
    private int activeStatus;
    private boolean addAllUser;
    private conRs getMaxAllUserId;
    private int AllUserMaxId;
    private boolean addLoginDetails;
    private boolean deleteDwpOwnerDetails;
    private boolean deleteAllUserDetails;
    private Part ownerPhoto;
    private String ownerPicName;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            firstName = request.getParameter("firstName");
            lastName = request.getParameter("lastName");
            email = request.getParameter("email");
            cellNo = request.getParameter("cellNo");
            divisionNameDropDown = request.getParameter("divisionNameDropDown");
            distictNameDropDown = request.getParameter("distictNameDropDown");
            policeNameDropDown = request.getParameter("policeNameDropDown");
            address = request.getParameter("address");
            zipCode = request.getParameter("zipCode");
            gender = request.getParameter("gender");
            nidNo = request.getParameter("nidNo");
            tinNo = request.getParameter("tinNo");
            tradeLicenseNo = request.getParameter("tradeLicenseNo");
            pass = request.getParameter("pass");

            ownerPhoto = request.getPart("ownerPhoto");
            ownerPicName = FileUpload.fileUpload(ownerPhoto, request);
            nidPhoto = request.getPart("nidPhoto");
            nidPicName = FileUpload.fileUpload(nidPhoto, request);
            tinPhoto = request.getPart("tinPhoto");
            tinPicName = FileUpload.fileUpload(tinPhoto, request);
            tradeLicensePhoto = request.getPart("tradeLicensePhoto");
            tradeLicensePicName = FileUpload.fileUpload(tradeLicensePhoto, request);
            
            System.err.println(" firstName: "+firstName);
            System.err.println(" lastName: "+lastName);
            System.err.println(" email: "+email);
            System.err.println(" cellNo: "+cellNo);
            System.err.println(" divisionNameDropDown: "+divisionNameDropDown);
            System.err.println(" distictNameDropDown: "+distictNameDropDown);
            System.err.println(" policeNameDropDown: "+policeNameDropDown);
            System.err.println(" address: "+address);
            System.err.println(" zipCode: "+zipCode);
            System.err.println(" gender: "+gender);
            System.err.println(" nidNo: "+nidNo);
            System.err.println(" tinNo: "+tinNo);
            System.err.println(" tradeLicenseNo: "+tradeLicenseNo);
            System.err.println(" pass: "+pass);
            System.err.println(" ownerPicName: "+ownerPicName);
            System.err.println(" nidPicName: "+nidPicName);
            System.err.println(" tinPicName: "+tinPicName);
            System.err.println(" tradeLicensePicName: "+tradeLicensePicName);

            dwpOwner.setFirst_name(firstName);
            dwpOwner.setLast_name(lastName);
            dwpOwner.setEmail(email);
            dwpOwner.setPhone_no(cellNo);
            dwpOwner.setOwner_photo(ownerPicName);
            dwpOwner.setDivision_id(divisionNameDropDown);
            dwpOwner.setDistrict_id(distictNameDropDown);
            dwpOwner.setPolice_station_id(policeNameDropDown);
            dwpOwner.setAddress(address);
            dwpOwner.setZip_code(zipCode);
            dwpOwner.setGender(gender);
            dwpOwner.setNid_no(nidNo);
            dwpOwner.setTin_on(tinNo);
            dwpOwner.setTrade_lisence_no(tradeLicenseNo);
            dwpOwner.setNid_scan_copy(nidPicName);
            dwpOwner.setTin_scan_copy(tinPicName);
            dwpOwner.setTrade_lisence_scan_copy(tradeLicensePicName);

            try {
                adddwpOwnerDetails = AddUserDetails.addDWPOwnerDetails(dwpOwner);
            } catch (SQLException ex) {
                Logger.getLogger(DWPRegServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (adddwpOwnerDetails == true) {
                try {
                    getMaxDwpOnwerId = GetMaxId.getMaxDwpOwnerId();
                    con = getMaxDwpOnwerId.getCon();
                    rs = getMaxDwpOnwerId.getRs();
                    pstm = getMaxDwpOnwerId.getPstm();
                    while (rs.next()) {
                        dwpOwnerMaxId = rs.getInt("dwp_owner_id");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DWPRegServlet.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        con.close();
                        rs.close();
                        pstm.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(DWPRegServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                userRole = "DWP";
                activeStatus = 1;
                au.setUser_id(dwpOwnerMaxId);
                au.setUser_role(userRole);
                au.setActive_status(activeStatus);

                try {
                    addAllUser = AddAllUser.addAllUser(au);
                } catch (SQLException ex) {
                    Logger.getLogger(DWPRegServlet.class.getName()).log(Level.SEVERE, null, ex);
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
                        Logger.getLogger(DWPRegServlet.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        try {
                            con.close();
                            rs.close();
                            pstm.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(DWPRegServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    lt.setAll_user_id(AllUserMaxId);
                    lt.setPhone_no(cellNo);
                    lt.setPassword(pass);
                    
                    addLoginDetails = AddLoginDetails.addLoginDetails(lt);
                    
                    if (addLoginDetails == true) {
                        request.getSession().setAttribute("messageTitle", "Success");
                        request.getSession().setAttribute("messageText", "Reistration Successfull We will Contuct You Vary Soon");
                        request.getSession().setAttribute("typeIcon", "success");
                        response.sendRedirect("admin/dwpRegistration.jsp");
                    } else {
                        System.out.println("1st dwpOwnerId :" + dwpOwnerMaxId);
                        System.out.println("1st allUser :" + AllUserMaxId);
                        deleteDwpOwnerDetails = DeleteOwnerDetails.deleteDwpOwnerDetails(dwpOwnerMaxId);
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
                        response.sendRedirect("admin/dwpRegistration.jsp");
                    }
                } else{
                    System.out.println("1st dwpOwnerId :" + dwpOwnerMaxId);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(RentECarRegServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    deleteDwpOwnerDetails = DeleteOwnerDetails.deleteOwnerDetails(dwpOwnerMaxId);
                    boolean deleteOwnerPic = FileUpload.fileDelete(ownerPicName, request);
                    boolean deleteNidPic = FileUpload.fileDelete(nidPicName, request);
                    boolean deleteTinPicName = FileUpload.fileDelete(tinPicName, request);
                    boolean deletetradeLicensePic = FileUpload.fileDelete(tradeLicensePicName, request);

                    request.getSession().setAttribute("messageTitle", "Registration Faild !");
                    request.getSession().setAttribute("messageText", "Please Try Again");
                    request.getSession().setAttribute("typeIcon", "warning");
                    response.sendRedirect("admin/dwpRegistration.jsp");
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
                response.sendRedirect("admin/dwpRegistration.jsp");
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
