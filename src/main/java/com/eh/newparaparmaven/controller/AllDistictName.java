package com.eh.newparaparmaven.controller;
/**
 *
 * @author Md. Emran Hossain
 */
import com.eh.newparaparmaven.classes.GetDistrict;
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

public class AllDistictName extends HttpServlet {

    private String divisionNameDropDown;
    private conRs conrs;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pstm;
    private int orgRow;
    private int[] districtId;
    private String[] districtName;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            divisionNameDropDown = new String(request.getParameter("divisionNameDropDown").getBytes("ISO-8859-1"), "UTF-8");
            conrs = GetDistrict.getDistrictForDivision(divisionNameDropDown);
            
            con = conrs.getCon();
            rs = conrs.getRs();
            pstm = conrs.getPstm();

            rs.last();
            orgRow = rs.getRow();
            districtId = new int[orgRow];
            districtName = new String[orgRow];
            rs.beforeFirst();
            int i = 0;
            while (rs.next()) {
                districtId[i] = rs.getInt("district_id");
                districtName[i] = rs.getString("district_name");
                i++;
            }
            response.getWriter().write("<option value=''>Select District</option>");
            for (i = 0; i < orgRow; i++) {
                response.setContentType("text/plain");
                response.getWriter().write("<option value=" + districtId[i] + ">" + districtName[i] + "</option>");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AllDistictName.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
                rs.close();
                pstm.close();
            } catch (SQLException ex) {
                Logger.getLogger(AllDistictName.class.getName()).log(Level.SEVERE, null, ex);
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