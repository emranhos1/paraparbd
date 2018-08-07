package com.eh.newparaparmaven.controller;

import com.eh.newparaparmaven.classes.GetAllFareRate;
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

public class AllFareRate extends HttpServlet {

    private conRs getFareRate;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pstm;
    private int cuFareRateTableId;
    private String parKmRate;
    private String bodyLess200km;
    private String bodyBig200km;
    private String driverCost;
    private String otherCost;
    private JSONObject jsonObject;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            try {
                getFareRate = GetAllFareRate.getAllFare();

                con = getFareRate.getCon();
                rs = getFareRate.getRs();
                pstm = getFareRate.getPstm();
                while (rs.next()) {
                    jsonObject = new JSONObject();
                    jsonObject.put("cuFareRateTableId", rs.getInt("cu_fare_rate_table_id"));
                    jsonObject.put("parKmRate", rs.getString("par_km_rate"));
                    jsonObject.put("bodyLess200km", rs.getString("bodyless200km"));
                    jsonObject.put("bodyBig200km", rs.getString("bodybig200km"));
                    jsonObject.put("driverCost", rs.getString("driver_cost"));
                    jsonObject.put("otherCost", rs.getString("other_cost"));
                }

                String json = new Gson().toJson(jsonObject);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);

            } catch (SQLException | JSONException ex) {
                Logger.getLogger(AllFareRate.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    con.close();
                    rs.close();
                    pstm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AllFareRate.class.getName()).log(Level.SEVERE, null, ex);
                }
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
