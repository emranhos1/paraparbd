package com.eh.newparaparmaven.controller;

import com.eh.newparaparmaven.classes.AddCuSms;
import com.eh.newparaparmaven.classes.SendSMS;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.eh.newparaparmaven.model.CuSmsTable;

public class SendSmsServlet extends HttpServlet {

    CuSmsTable cuSmsTable = new CuSmsTable();

    private String cellNo;
    private String sendSmsAllData;
    private boolean addCuSms;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            cellNo = request.getParameter("cellNo");

            sendSmsAllData = SendSMS.sendSMS(cellNo);

            String[] output = sendSmsAllData.split("~");
            cuSmsTable.setMobile_no(cellNo);
            cuSmsTable.setRandom_genarated_code(output[0]);
            cuSmsTable.setSms_status(output[1]);

            try {
                addCuSms = AddCuSms.addCuSms(cuSmsTable);
            } catch (SQLException ex) {
                Logger.getLogger(SendSmsServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (addCuSms == true) {
                request.getSession().setAttribute("messageTitle", "Success !");
                request.getSession().setAttribute("messageText", "1 sms sent to your mobile");
                request.getSession().setAttribute("typeIcon", "success");
            } else {
                request.getSession().setAttribute("messageTitle", "Error !");
                request.getSession().setAttribute("messageText", "Error to send sms");
                request.getSession().setAttribute("typeIcon", "warning");
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
