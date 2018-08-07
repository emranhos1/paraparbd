package com.eh.newparaparmaven.controller;

import com.eh.newparaparmaven.classes.GetLoginTable;
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
import org.json.JSONException;
import org.json.JSONObject;

public class CheckCellNo extends HttpServlet {

    private String cellNo;
    private conRs getuserPhoneNo;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pstm;
    private String phoneCheck;
    private JSONObject jsonObject;
    private String loginTableId;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            cellNo = request.getParameter("cellNo");

            getuserPhoneNo = GetLoginTable.getPhone(cellNo);
            con = getuserPhoneNo.getCon();
            rs = getuserPhoneNo.getRs();
            pstm = getuserPhoneNo.getPstm();
            try {
                if (rs.next()) {
                    phoneCheck = rs.getString("phone_no");
                    loginTableId = rs.getString("login_table_id");
                } else {
                    phoneCheck = null;
                }
            } catch (SQLException ex) {
                Logger.getLogger(CheckCellNo.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    con.close();
                    rs.close();
                    pstm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CheckCellNo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            System.out.println(phoneCheck);

            try {
                jsonObject = new JSONObject();
                jsonObject.put("cellNo", phoneCheck);
                jsonObject.put("loginTableId", loginTableId);

                String json = new Gson().toJson(jsonObject);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
            } catch (JSONException ex) {
                Logger.getLogger(CheckCellNo.class.getName()).log(Level.SEVERE, null, ex);
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