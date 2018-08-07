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

public class DWPInfoServlet extends HttpServlet {

    private int allUserId;
    private conRs getDwpOwnerId;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pstm;
    private int dwpOwnerId;
    private conRs getUserInfo;
    private JSONObject jsonObject;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            HttpSession session = request.getSession();
            allUserId = (int) session.getAttribute("idUser");
            try {
                getDwpOwnerId = GetAllUser.getOwnerUserId(allUserId);
                con = getDwpOwnerId.getCon();
                rs = getDwpOwnerId.getRs();
                pstm = getDwpOwnerId.getPstm();
                while (rs.next()) {
                    dwpOwnerId = rs.getInt("user_id");
                }
            } catch (SQLException ex) {
                Logger.getLogger(DWPInfoServlet.class.getName()).log(Level.SEVERE, null, ex);
            } finally{
                try {
                    con.close();
                    rs.close();
                    pstm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DWPInfoServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 

            try {
                getUserInfo = GetUserInfo.getDwpOwnerInfo(dwpOwnerId);
                con = getUserInfo.getCon();
                pstm = getUserInfo.getPstm();
                rs = getUserInfo.getRs();
                
                while (rs.next()) {
                jsonObject = new JSONObject();
                jsonObject.put("dwp_owner_id", rs.getString("dwp_owner_id"));
                jsonObject.put("firstName", rs.getString("first_name"));
                jsonObject.put("lastName", rs.getString("last_name"));
                jsonObject.put("email", rs.getString("email"));
                jsonObject.put("phoneNo", rs.getString("phone_no"));
                jsonObject.put("divisionName", rs.getString("division_name"));
                jsonObject.put("districtName", rs.getString("district_name"));
                jsonObject.put("policeStationName", rs.getString("police_station_name"));
                jsonObject.put("address", rs.getString("address"));
                jsonObject.put("gender", rs.getString("gender"));
                jsonObject.put("zipCode", rs.getString("zip_code"));
                jsonObject.put("nidNo", rs.getString("nid_no"));
                jsonObject.put("tinNo", rs.getString("tin_on"));
                jsonObject.put("tradeLisenceNo", rs.getString("trade_lisence_no"));
                jsonObject.put("registrationDate", rs.getString("registration_date"));
                jsonObject.put("photo", rs.getString("owner_photo"));
            }
            } catch (SQLException | JSONException ex) {
                Logger.getLogger(DWPInfoServlet.class.getName()).log(Level.SEVERE, null, ex);
            } finally{
                try {
                    con.close();
                    rs.close();
                    pstm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DWPInfoServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            String json = new Gson().toJson(jsonObject);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
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
