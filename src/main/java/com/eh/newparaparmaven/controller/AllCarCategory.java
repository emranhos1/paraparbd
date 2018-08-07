package com.eh.newparaparmaven.controller;

import com.eh.newparaparmaven.classes.GetCategory;
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

public class AllCarCategory extends HttpServlet {

    private conRs conrs;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pstm;
    private int orgRow;
    private int[] carCategoryId;
    private String[] brandName;
    private String districtNameDropDownOrigin;
    private String[] modelName;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            districtNameDropDownOrigin = request.getParameter("districtNameDropDownOrigin");
            
            conrs = GetCategory.getCategoryNamesForCU(districtNameDropDownOrigin);
            
            con = conrs.getCon();
            rs = conrs.getRs();
            pstm = conrs.getPstm();

            rs.last();
            orgRow = rs.getRow();
            carCategoryId = new int[orgRow];
            brandName = new String[orgRow];
            modelName = new String[orgRow];
            rs.beforeFirst();
            int i = 0;
            while (rs.next()) {
                carCategoryId[i] = rs.getInt("car_category_id");
                brandName[i] = rs.getString("brand_name");
                modelName[i] = rs.getString("model_name");
                i++;
            }
//            if(rs.next()){
//                carCategoryId[i] = rs.getInt("car_category_id");
//                brandName[i] = rs.getString("brand_name");
//                i++;
//            } else{
//                request.getSession().setAttribute("messageTitle", "Car Not Avalable !");
//                request.getSession().setAttribute("messageText", "This Service is not avalabe here");
//                request.getSession().setAttribute("typeIcon", "warning");
//            }
            response.getWriter().write("<option value=''>Select Car</option>");
            for (i = 0; i < orgRow; i++) {
                System.out.println("test car category ");
                System.out.println(carCategoryId[i]+"--"+brandName[i]+"--"+modelName[i]);
                response.setContentType("text/plain");
                response.getWriter().write("<option value=" + carCategoryId[i] + ">" + brandName[i]+"( "+modelName[i] + " )</option>");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AllCarCategory.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
                rs.close();
                pstm.close();
            } catch (SQLException ex) {
                Logger.getLogger(AllCarCategory.class.getName()).log(Level.SEVERE, null, ex);
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