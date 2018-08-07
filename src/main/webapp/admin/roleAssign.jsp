<%-- 
    Document   : roleAssign
    Created on : Aug 5, 2018, 9:33:19 AM
    Author     : Shahjahan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Parapar - Role Assign</title>
        <!--head-->
        <%@include file="head.jsp" %>
    </head>
    <body>
        <%
            if ((session.getAttribute("idUser") == null) || (session.getAttribute("idUser") == "")) {
                response.sendRedirect("../index.jsp");
            } else {%>
        <!--navbar-->
        <%@include file="navbar.jsp" %>
        <!--maincontent start-->
        <section id="admindashboard">
            <div class="container">

                <a style="float: right; font-weight: bold; color:black; text-decoration: none;" href="index.jsp">&#10006;</a>
                <center>
                    <h3 class="display-6 text-success"><b>Role Assign</b></h3>
                </center>
            </div>
        </section>
        <!--maincontent end-->
        <!--footer-->
        <%@include file="footer.jsp" %>
        <%}%>
    </body>
</html>
