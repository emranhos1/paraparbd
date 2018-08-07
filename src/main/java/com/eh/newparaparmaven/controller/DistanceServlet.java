package com.eh.newparaparmaven.controller;

import com.eh.newparaparmaven.classes.GetFareRate;
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

public class DistanceServlet extends HttpServlet {

    private double miles;
    private double forKM;
    private double km;
    private long totalTake;
    private JSONObject jsonObject;
    private conRs getFareRate;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pstm;
    private int orgRow;
    private double parKMRate;
    private double bodySmall;
    private double bodyBig;
    private double driverCost;
    private double otherCost;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            miles = Double.parseDouble(request.getParameter("miles"));
            forKM = 1.61;
            km = miles * forKM + 1;
            System.out.println(km);
            getFareRate = GetFareRate.getFareRateTable();
            con = getFareRate.getCon();
            rs = getFareRate.getRs();
            pstm = getFareRate.getPstm();

            while (rs.next()) {
                parKMRate = rs.getDouble("par_km_rate");
                bodySmall = rs.getDouble("bodyless200km");
                bodyBig = rs.getDouble("bodybig200km");
                driverCost = rs.getDouble("driver_cost");
                otherCost = rs.getDouble("other_cost");
            }

            if (km > 200.0) {
                System.out.println("Big " + km);
                System.out.println("bodyBig " + bodyBig);
                totalTake = Math.round((km * parKMRate) +bodyBig + driverCost + otherCost);
            } else {
                System.out.println("Small " + km);
                System.out.println("bodySmall " + bodySmall);
                totalTake = Math.round((km * parKMRate) +bodySmall + driverCost);
            }

            jsonObject = new JSONObject();
            jsonObject.put("totalTake", totalTake);
            String json = new Gson().toJson(jsonObject);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);

        } catch (JSONException ex) {
            Logger.getLogger(DistanceServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DistanceServlet.class.getName()).log(Level.SEVERE, null, ex);
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