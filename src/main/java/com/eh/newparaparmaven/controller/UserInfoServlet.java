package com.eh.newparaparmaven.controller;

import com.eh.newparaparmaven.classes.GetAllUser;
import com.eh.newparaparmaven.classes.GetUserInfo;
import com.eh.newparaparmaven.dbConnection.conRs;
import com.google.gson.Gson;
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

public class UserInfoServlet extends HttpServlet {

    private int allUserId;
    private conRs getCommonUserId;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pstm;
    private int commonUserId;
    private conRs getUserInfo;
    private Connection con1;
    private PreparedStatement pstm1;
    private ResultSet rs1;
    private JSONObject jsonObject;
    private String photo;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            HttpSession session = request.getSession();
            allUserId = (int) session.getAttribute("idUser");
            getCommonUserId = GetAllUser.getOwnerUserId(allUserId);

            con = getCommonUserId.getCon();
            rs = getCommonUserId.getRs();
            pstm = getCommonUserId.getPstm();
            while (rs.next()) {
                commonUserId = rs.getInt("user_id");
            }

            getUserInfo = GetUserInfo.getCommonUserInfo(commonUserId);

            con1 = getUserInfo.getCon();
            pstm1 = getUserInfo.getPstm();
            rs1 = getUserInfo.getRs();

            while (rs1.next()) {
                photo = rs1.getString("photo");
                jsonObject = new JSONObject();
                jsonObject.put("commonUserId", rs1.getString("common_user_id"));
                jsonObject.put("firstName", rs1.getString("first_name"));
                jsonObject.put("lastName", rs1.getString("last_name"));
                jsonObject.put("email", rs1.getString("email"));
                jsonObject.put("phoneNo", rs1.getString("phone_no"));
                jsonObject.put("address", rs1.getString("address"));
                jsonObject.put("deviceImeNo", rs1.getString("device_ime_no"));
                jsonObject.put("deviceIp", rs1.getString("device_ip"));
                jsonObject.put("createdDate", rs1.getString("created_date"));
                jsonObject.put("photo", rs1.getString("photo"));
            }
            String json = new Gson().toJson(jsonObject);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);

        } catch (SQLException | JSONException ex) {
            Logger.getLogger(UserInfoServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                con1.close();
                rs.close();
                rs1.close();
                pstm.close();
                pstm1.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserInfoServlet.class.getName()).log(Level.SEVERE, null, ex);
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
