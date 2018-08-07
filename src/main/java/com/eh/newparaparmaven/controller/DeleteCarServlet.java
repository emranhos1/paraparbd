package com.eh.newparaparmaven.controller;

/**
 *
 * @author Md. Emran Hossain
 */
import com.eh.newparaparmaven.classes.DeleteCar;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteCarServlet extends HttpServlet {

    private int carCategoryId;
    private boolean deleteCarCategory;
    private boolean deleteRentACarTable;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            carCategoryId = Integer.parseInt(request.getParameter("carCategoryId"));
            deleteCarCategory = DeleteCar.deleteCarCategory(carCategoryId);
            deleteRentACarTable = DeleteCar.deleteRentACarTable(carCategoryId);
            if (deleteCarCategory == true) {
                if (deleteRentACarTable == true) {
                    request.getSession().setAttribute("messageTitle", "Deleted");
                    request.getSession().setAttribute("messageText", "Delete Complete");
                    request.getSession().setAttribute("typeIcon", "success");
                    response.sendRedirect("rentACar/allCar.jsp");
                } else {
                    request.getSession().setAttribute("messageTitle", "Not Delete !");
                    request.getSession().setAttribute("typeIcon", "warning");
                    response.sendRedirect("rentACar/allCar.jsp");
                }
            } else {
                request.getSession().setAttribute("messageTitle", "Not Delete !");
                request.getSession().setAttribute("typeIcon", "warning");
                response.sendRedirect("rentACar/allCar.jsp");
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
