package com.eh.newparaparmaven.controller;

import com.eh.newparaparmaven.classes.GetAllUser;
import com.eh.newparaparmaven.classes.GetDWPOwnerTable;
import com.eh.newparaparmaven.classes.GetRentACarBooking;
import com.google.gson.Gson;
import com.eh.newparaparmaven.dbConnection.conRs;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.eh.newparaparmaven.model.RentACarBooking;
import org.json.JSONException;
import org.json.JSONObject;

public class CurrentMonthRentForDWP extends HttpServlet {

    RentACarBooking rentACarBooking = new RentACarBooking();
    private int allUserId;
    private conRs getDWPOwnerId;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pstm;
    private int DWPOwnerId;
    private SimpleDateFormat dateFormat;
    private Date date;
    private String currentDate;
    private int currentMonth;
    private conRs getDWPOwnerDistrictId;
    private int DWPOwnerDistId;
    private conRs getCurentMonthRentForDwp;
    private int dataRow;
    private String[] cuFistName;
    private String[] cuLastName;
    private String[] rentFirstName;
    private String[] rentLastName;
    private String[] brandName;
    private String[] modelName;
    private String[] colorName;
    private String[] origin;
    private String[] destination;
    private String[] totalFare;
    private String[] discountId;
    private String[] grossFare;
    private String[] travelDate;
    private String[] orderDate;
    private conRs getCommission;
    private JSONObject jsonObject;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            HttpSession session = request.getSession();
            allUserId = (int) session.getAttribute("idUser");
            try {
                getDWPOwnerId = GetAllUser.getOwnerUserId(allUserId);
                con = getDWPOwnerId.getCon();
                rs = getDWPOwnerId.getRs();
                pstm = getDWPOwnerId.getPstm();
                while (rs.next()) {
                    DWPOwnerId = rs.getInt("user_id");
                }
                System.out.println(DWPOwnerId);
            } catch (SQLException ex) {
                Logger.getLogger(CurrentMonthRentForDWP.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    con.close();
                    rs.close();
                    pstm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CurrentMonthRentForDWP.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            try {
                getDWPOwnerDistrictId = GetDWPOwnerTable.getDWPOwnerDistId(DWPOwnerId);
                con = getDWPOwnerDistrictId.getCon();
                rs = getDWPOwnerDistrictId.getRs();
                pstm = getDWPOwnerDistrictId.getPstm();
                while (rs.next()) {
                    DWPOwnerDistId = rs.getInt("district_id");
                }
                System.out.println(DWPOwnerDistId);
            } catch (SQLException ex) {
                Logger.getLogger(CurrentMonthRentForDWP.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    con.close();
                    rs.close();
                    pstm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CurrentMonthRentForDWP.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
            date = new Date();
            currentDate = dateFormat.format(date);
            currentMonth = date.getMonth() + 1;

            rentACarBooking.setTravel_month(currentMonth);
            rentACarBooking.setOrigin_district_id(DWPOwnerDistId);

            try {
                getCurentMonthRentForDwp = GetRentACarBooking.getCurrentMonthDWPBooking(rentACarBooking);
                con = getCurentMonthRentForDwp.getCon();
                rs = getCurentMonthRentForDwp.getRs();
                pstm = getCurentMonthRentForDwp.getPstm();

                rs.last();
                dataRow = rs.getRow();
                cuFistName = new String[dataRow];
                cuLastName = new String[dataRow];
                rentFirstName = new String[dataRow];
                rentLastName = new String[dataRow];
                brandName = new String[dataRow];
                modelName = new String[dataRow];
                colorName = new String[dataRow];
                origin = new String[dataRow];
                destination = new String[dataRow];
                totalFare = new String[dataRow];
                discountId = new String[dataRow];
                grossFare = new String[dataRow];
                travelDate = new String[dataRow];
                orderDate = new String[dataRow];
                rs.beforeFirst();
                int i = 0;
                while (rs.next()) {
                    cuFistName[i] = rs.getString("cuFistName");
                    cuLastName[i] = rs.getString("cuLastName");
                    rentFirstName[i] = rs.getString("rentFirstName");
                    rentLastName[i] = rs.getString("rentLastName");
                    brandName[i] = rs.getString("brand_name");
                    modelName[i] = rs.getString("model_name");
                    colorName[i] = rs.getString("color_name");
                    origin[i] = rs.getString("origin");
                    destination[i] = rs.getString("destination");
                    totalFare[i] = rs.getString("total_fare");
                    discountId[i] = rs.getString("discount_id");
                    grossFare[i] = rs.getString("gross_fare");
                    travelDate[i] = rs.getString("travel_date");
                    orderDate[i] = rs.getString("order_date");
                    i++;
                }
                for (i = 0; i < dataRow; i++) {
                    response.setContentType("text/plain");
                    response.getWriter().write("<tr>"
                            + "<td>" + (i + 1) + "</td>"
                            + "<td>" + cuFistName[i] + " " + cuLastName[i] + "</td>"
                            + "<td>" + rentFirstName[i] + " " + rentLastName[i] + "</td>"
                            + "<td>" + brandName[i] + " (" + modelName[i] + ")</td>"
                            + "<td> " + origin[i] + "</td>"
                            + "<td> " + destination[i] + "</td>"
                            + "<td> Total Fare :" + totalFare[i] + " \n Discount :" + discountId[i] + " \n Gross Fare :" + grossFare[i] + "</td>"
                            + "<td> Travel Date : " + travelDate[i] + "\n Order Date : " + orderDate[i] + "</td>"
                            + "</tr>");
                }
            } catch (SQLException ex) {
                Logger.getLogger(CurrentMonthRentForDWP.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    con.close();
                    pstm.close();
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CurrentMonthRentForDWP.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

//            change hobe
//            try {
//                
//                getCommission = GetRentACarBooking.getCurrentMonthCommissionDWP(rentACarBooking);
//
//                con = getCommission.getCon();
//                pstm = getCommission.getPstm();
//                rs = getCommission.getRs();
//
//                while (rs.next()) {
//                    jsonObject = new JSONObject();
//                    jsonObject.put("commission", rs.getString("dd_commission"));
//                }
//            } catch (SQLException | JSONException ex) {
//                Logger.getLogger(CurrentMonthRentForDWP.class.getName()).log(Level.SEVERE, null, ex);
//            } finally {
//                try {
//                    con.close();
//                    pstm.close();
//                    rs.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(CurrentMonthRentForDWP.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//
//            String json = new Gson().toJson(jsonObject);
//            response.setContentType("application/json");
//            response.setCharacterEncoding("UTF-8");
//            response.getWriter().write(json);
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
