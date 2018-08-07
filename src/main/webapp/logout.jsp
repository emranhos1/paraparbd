<%-- 
    Document   : logout
    Created on : Sep 19, 2017, 1:15:30 PM
    Author     : Md. Emran Hossain
--%>
<%@page import="com.eh.newparaparmaven.classes.AddLoggingHistory"%>
<%
    String idUser = session.getAttribute("idUser").toString();
    System.out.println(idUser);
    AddLoggingHistory.updateLoginHistoryDetails(idUser);
    
    session.setAttribute("idUser", null);
    session.setAttribute("messageTitle", "Logout Successfull!");
    session.setAttribute("messageText", "Hope Come back Soon");
    session.setAttribute("typeIcon", "success");
    response.sendRedirect("index.jsp");
//    session.invalidate();
%>
