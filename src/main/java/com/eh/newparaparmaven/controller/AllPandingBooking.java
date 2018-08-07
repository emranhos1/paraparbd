package com.eh.newparaparmaven.controller;

import com.eh.newparaparmaven.classes.GetAllUser;
import com.eh.newparaparmaven.classes.GetBkashTable;
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

public class AllPandingBooking extends HttpServlet {

    private int allUserId;
    private conRs getCommonUserId;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pstm;
    private int commonUserId;
    private int orgRow;
    private conRs getBkashT;
    private int[] bkashTableId;
    private String[] orderNo;
    private conRs getBkashSms;
    private int cuBkashSMSTableId;
    private String[] massage;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            HttpSession session = request.getSession();
            allUserId = (int) session.getAttribute("idUser");
            try {
                getCommonUserId = GetAllUser.getOwnerUserId(allUserId);
                con = getCommonUserId.getCon();
                rs = getCommonUserId.getRs();
                pstm = getCommonUserId.getPstm();
                while (rs.next()) {
                    commonUserId = rs.getInt("user_id");
                }
            } catch (SQLException ex) {
                Logger.getLogger(AllPandingBooking.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    con.close();
                    rs.close();
                    pstm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AllPandingBooking.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            try {
                getBkashT = GetBkashTable.getBkashTable(commonUserId);
                con = getBkashT.getCon();
                rs = getBkashT.getRs();
                pstm = getBkashT.getPstm();
                rs.last();
                orgRow = rs.getRow();
                bkashTableId = new int[orgRow];
                orderNo = new String[orgRow];
                massage = new String[orgRow];
                rs.beforeFirst();
                int i = 0;
                while (rs.next()) {
                    bkashTableId[i] = rs.getInt("bkash_table_id");
                    orderNo[i] = rs.getString("order_no");
                    massage[i] = rs.getString("massage");
                    i++;
                }

                for (i = 0; i < orgRow; i++) {
                    response.setContentType("text/plain");
                    response.getWriter().write("<tr>"
                            + "<td>" + (i + 1) + "</td>"
                            + "<td>" + orderNo[i] + "</td>"
                            + "<td>" + massage[i] + "</td>"
                            + "<td>"
                            + "<button class='btn btn-secondary' type='button' data-dismiss='modal'>"
                            + "<a data-toggle='modal' data-bkashtableid='" + bkashTableId[i] + "' class='btn btn-primary open-spceDialog-TransectionCode' href='#addSpecTransectionCode' >Give Transection Code</a>"
                            + "</button>        "
                            + "</td>"
                            + "</tr>");
                }
            } catch (SQLException ex) {
                Logger.getLogger(AllPandingBooking.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    con.close();
                    rs.close();
                    pstm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AllPandingBooking.class.getName()).log(Level.SEVERE, null, ex);
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
