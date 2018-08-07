package com.eh.newparaparmaven.controller;

import com.eh.newparaparmaven.classes.GetAllUser;
import com.eh.newparaparmaven.classes.GetUserInfo;
import com.google.gson.Gson;
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
import org.json.JSONException;
import org.json.JSONObject;

public class RentACarInfoServlet extends HttpServlet {

    private int allUserId;
    private conRs getRentACarOwnerId;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pstm;
    private int rentACarOwnerId;
    private conRs getUserInfo;
    private Connection con1;
    private PreparedStatement pstm1;
    private ResultSet rs1;
    private JSONObject jsonObject;

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
            
            getUserInfo = GetUserInfo.getRentACarOwnerInfo(rentACarOwnerId);
            
            con1 = getUserInfo.getCon();
            pstm1 = getUserInfo.getPstm();
            rs1 = getUserInfo.getRs();
            
            while (rs1.next()) {
                jsonObject = new JSONObject();
                jsonObject.put("rentACarOwnerId", rs1.getString("rent_a_car_owner_id"));
                jsonObject.put("firstName", rs1.getString("first_name"));
                jsonObject.put("lastName", rs1.getString("last_name"));
                jsonObject.put("email", rs1.getString("email"));
                jsonObject.put("phoneNo", rs1.getString("phone_no"));
                jsonObject.put("address", rs1.getString("address"));
                jsonObject.put("zipCode", rs1.getString("zip_code"));
                jsonObject.put("divisionName", rs1.getString("division_name"));
                jsonObject.put("districtName", rs1.getString("district_name"));
                jsonObject.put("policeStationName", rs1.getString("police_station_name"));
                jsonObject.put("nidNo", rs1.getString("nid_no"));
                jsonObject.put("tinNo", rs1.getString("tin_no"));
                jsonObject.put("tradeLisenceNo", rs1.getString("trade_lisence_no"));
                jsonObject.put("rentDeviceEmiNo", rs1.getString("rent_device_emi_no"));
                jsonObject.put("registrationDate", rs1.getString("registration_date"));
                jsonObject.put("photo", rs1.getString("owner_photo"));
            }
            
            String json = new Gson().toJson(jsonObject);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
            
        } catch (SQLException | JSONException ex) {
            Logger.getLogger(RentACarInfoServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                con1.close();
                rs.close();
                rs1.close();
                pstm.close();
                pstm1.close();
            } catch (SQLException ex) {
                Logger.getLogger(RentACarInfoServlet.class.getName()).log(Level.SEVERE, null, ex);
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