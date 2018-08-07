package com.eh.newparaparmaven.controller;

import com.eh.newparaparmaven.classes.AddCarCategory;
import com.eh.newparaparmaven.classes.AddRentACarTable;
import com.eh.newparaparmaven.classes.FileUpload;
import com.eh.newparaparmaven.classes.GetAllUser;
import com.eh.newparaparmaven.classes.GetCarCategoryTable;
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
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import com.eh.newparaparmaven.model.CarCategory;
import com.eh.newparaparmaven.model.RentACarTable;

@MultipartConfig(maxFileSize = 169999999)
public class AddRentACarServlet extends HttpServlet {

    CarCategory cc = new CarCategory();
    RentACarTable ract = new RentACarTable();

    private int allUserId;
    private conRs getCarOwnerId;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pstm;
    private int carOwnerId;
    private int brandNameDropDown;
    private int ModelNameDropDown;
    private int colorDropDown;
    private String carRegNo;
    private Part carRegScanCopy;
    private String carRegScanCopyName;
    private boolean addCarCategory;
    private conRs getMaxCategoryId;
    private Connection con1;
    private ResultSet rs1;
    private PreparedStatement pstm1;
    private int carCategoryId;
    private boolean addRentACarTable;
    private conRs getCarRegNo;
    private Connection con2;
    private ResultSet rs2;
    private PreparedStatement pstm2;
    private String active;
    private String texTokenNo;
    private String carFitnessNo;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            HttpSession session = request.getSession();
            allUserId = (int) session.getAttribute("idUser");
            getCarOwnerId = GetAllUser.getOwnerUserId(allUserId);

            con = getCarOwnerId.getCon();
            rs = getCarOwnerId.getRs();
            pstm = getCarOwnerId.getPstm();
            while (rs.next()) {
                carOwnerId = rs.getInt("user_id");
            }

            brandNameDropDown = Integer.parseInt(request.getParameter("brandNameDropDown"));
            ModelNameDropDown = Integer.parseInt(request.getParameter("ModelNameDropDown"));
            colorDropDown = Integer.parseInt(request.getParameter("colorDropDown"));
            carRegNo = request.getParameter("carRegNo");
            texTokenNo = request.getParameter("texTokenNo");
            carFitnessNo = request.getParameter("carFitnessNo");
            carRegScanCopy = request.getPart("carRegScanCopy");
            
            System.out.println(carRegScanCopy);
            System.out.println(brandNameDropDown);
            System.out.println(ModelNameDropDown);
            System.out.println(colorDropDown);
            System.out.println(carRegNo);
            System.out.println(texTokenNo);
            System.out.println(carFitnessNo);
            getCarRegNo = GetCarCategoryTable.getRegistrationNoForCheck(carRegNo);
            con2 = getCarRegNo.getCon();
            rs2 = getCarRegNo.getRs();
            pstm2 = getCarRegNo.getPstm();

            if (rs2.next()) {
                request.getSession().setAttribute("messageTitle", "Error !");
                request.getSession().setAttribute("messageText", "Registration No Already Used");
                request.getSession().setAttribute("typeIcon", "warning");
                response.sendRedirect("rentACar/addNewCar.jsp");
            } else {
                carRegScanCopyName = FileUpload.fileUpload(carRegScanCopy, request);
                cc.setBrand_id(brandNameDropDown);
                cc.setCar_model_id(ModelNameDropDown);
                cc.setColor_id(colorDropDown);
                cc.setCar_registration_no(carRegNo);
                cc.setTex_token_no(texTokenNo);
                cc.setCar_fitness_no(carFitnessNo);
                cc.setCar_registration_scan_copy(carRegScanCopyName);
                cc.setRent_a_car_owner_id(carOwnerId);
                addCarCategory = AddCarCategory.addCarCategory(cc);
                getMaxCategoryId = GetMaxId.getMaxCarCategoryId();
                con1 = getMaxCategoryId.getCon();
                rs1 = getMaxCategoryId.getRs();
                pstm1 = getMaxCategoryId.getPstm();

                while (rs1.next()) {
                    carCategoryId = rs1.getInt("car_category_id");
                }
                active = "0";
                ract.setCar_category_id(carCategoryId);
                ract.setRent_a_car_owner_id(carOwnerId);
                ract.setActive_or_not(active);

                addRentACarTable = AddRentACarTable.addRentACarTable(ract);
                if (addCarCategory == true) {
                    if (addRentACarTable == true) {
                        request.getSession().setAttribute("messageTitle", "Success");
                        request.getSession().setAttribute("messageText", "Registration Complete");
                        request.getSession().setAttribute("typeIcon", "success");
                        response.sendRedirect("rentACar/addNewCar.jsp");
                    } else {
                        request.getSession().setAttribute("messageTitle", "Error !");
                        request.getSession().setAttribute("messageText", "Registration Not Complete");
                        request.getSession().setAttribute("typeIcon", "warning");
                        response.sendRedirect("rentACar/addNewCar.jsp");
                    }
                } else {
                    request.getSession().setAttribute("messageTitle", "Error !");
                    request.getSession().setAttribute("messageText", "Registration Not Complete");
                    request.getSession().setAttribute("typeIcon", "warning");
                    response.sendRedirect("rentACar/addNewCar.jsp");
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(AddRentACarServlet.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                con.close();
//                con1.close();
//                con2.close();
//                rs.close();
//                rs1.close();
//                rs2.close();
//                pstm.close();
//                pstm1.close();
//                pstm2.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(AddRentACarServlet.class.getName()).log(Level.SEVERE, null, ex);
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
