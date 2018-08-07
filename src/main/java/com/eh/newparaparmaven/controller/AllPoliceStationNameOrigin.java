package com.eh.newparaparmaven.controller;

import com.eh.newparaparmaven.classes.GetPoliceStation;
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

public class AllPoliceStationNameOrigin extends HttpServlet {

    private String districtNameDropDownOrigin;
    private conRs conrs;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pstm;
    private int orgRow;
    private int[] policeStationId;
    private String[] policeStationName;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            districtNameDropDownOrigin = new String(request.getParameter("districtNameDropDownOrigin").getBytes("ISO-8859-1"), "UTF-8");
            conrs = GetPoliceStation.getPoliceStationNameForDistrict(districtNameDropDownOrigin);
            
            con = conrs.getCon();
            rs = conrs.getRs();
            pstm = conrs.getPstm();

            rs.last();
            orgRow = rs.getRow();
            policeStationId = new int[orgRow];
            policeStationName = new String[orgRow];
            rs.beforeFirst();
            int i = 0;
            while (rs.next()) {
                policeStationId[i] = rs.getInt("police_station_id");
                policeStationName[i] = rs.getString("police_station_name");
                i++;
            }
            response.getWriter().write("<option value=''>Select One</option>");
            for (i = 0; i < orgRow; i++) {
                response.setContentType("text/plain");
                response.getWriter().write("<option value=" + policeStationName[i] + ">" + policeStationName[i] + "</option>");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AllPoliceStationNameOrigin.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
                rs.close();
                pstm.close();
            } catch (SQLException ex) {
                Logger.getLogger(AllPoliceStationNameOrigin.class.getName()).log(Level.SEVERE, null, ex);
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
