package com.eh.newparaparmaven.controller;
/**
 *
 * @author Md. Emran Hossain
 */
import com.eh.newparaparmaven.classes.GetAllUser;
import com.eh.newparaparmaven.classes.GetCarCategoryTable;
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

public class AllCarServlet extends HttpServlet {

    private int allUserId;
    private conRs getRentACarOwnerId;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pstm;
    private int rentACarOwnerId;
    private conRs conrs;
    private Connection con1;
    private ResultSet rs1;
    private PreparedStatement pstm1;
    private int dataRow;
    private String[] carRegistrationNo;
    private String[] carRegistrationScanCopy;
    private String[] brandName;
    private String[] modelName;
    private String[] colorName;
    private int[] carCategoryId;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            HttpSession session = request.getSession();
            allUserId = (int) session.getAttribute("idUser");
            getRentACarOwnerId = GetAllUser.getOwnerUserId(allUserId);

            con = getRentACarOwnerId.getCon();
            rs = getRentACarOwnerId.getRs();
            pstm = getRentACarOwnerId.getPstm();
            while (rs.next()) {
                rentACarOwnerId = rs.getInt("user_id");
            }

            System.out.println("all User :" + allUserId);
            System.out.println("owner :" + rentACarOwnerId);
            conrs = GetCarCategoryTable.getCarCategory(rentACarOwnerId);

            con1 = conrs.getCon();
            rs1 = conrs.getRs();
            pstm1 = conrs.getPstm();
            rs1.last();
            dataRow = rs1.getRow();
            carCategoryId = new int[dataRow];
            carRegistrationNo = new String[dataRow];
            carRegistrationScanCopy = new String[dataRow];
            brandName = new String[dataRow];
            modelName = new String[dataRow];
            colorName = new String[dataRow];
            rs1.beforeFirst();
            int i = 0;
            while (rs1.next()) {
                carCategoryId[i] = rs1.getInt("car_category_id");
                carRegistrationNo[i] = rs1.getString("car_registration_no");
                carRegistrationScanCopy[i] = rs1.getString("car_registration_scan_copy");
                brandName[i] = rs1.getString("brand_name");
                modelName[i] = rs1.getString("model_name");
                colorName[i] = rs1.getString("color_name");
                i++;
            }

            for (i = 0; i < dataRow; i++) {
                response.setContentType("text/plain");
                response.getWriter().write("<tr>"
                        + "<td>" + (i + 1) + "</td>"
                        + "<td>" + carRegistrationNo[i] + "<input type='hidden' id='carCategoryId' name='carCategoryId' value='" + carCategoryId[i] + "'/></td>"
                        + "<td>" + brandName[i] + "</td>"
                        + "<td>" + modelName[i] + "</td>"
                        + "<td>" + colorName[i] + "</td>"
                        + "<td><img src='../allImage/" + carRegistrationScanCopy[i] + "' alt='এই ফাইলটি লোড করা যাচ্ছেনা' height='100px' width='100px'/></td>"
                        + "<td>"
                        + "<button class='btn btn-secondary' type='button' data-dismiss='modal'>"
                        + "<a data-toggle='modal' data-carcategoryid='" + carCategoryId[i] + "' class='btn btn-primary open-spceDialog-delete' href='#addSpecDelete' >Delete</a>"
                        + "</button>        "
                        + "</td>"
                        + "</tr>");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AllCarServlet.class.getName()).log(Level.SEVERE, null, ex);
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