<%-- 
    Document   : adminDashboard
    Created on : Aug 5, 2018, 9:28:03 AM
    Author     : Shahjahan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Dashboard</title>
        <!--head-->
        <%@include file="head.jsp" %>
    </head>
    <body>

        <!--navbar-->
        <%@include file="navbar.jsp" %>
        <!--maincontent start-->
        <section id="admindashboard">
            <div class="container">
                <!--message div-->
                <div>
                    <input type="hidden" value="${messageTitle}" name="messageTitle" id="messageTitle"/>
                    <input type="hidden" value="${messageText}" name="messageText" id="messageText"/>
                    <input type="hidden" value="${typeIcon}" name="typeIcon" id="typeIcon"/>
                </div>
                <center>
                    <h3 class="display-6 text-success"><b>Admin Dashboard</b></h3>
                </center>
            </div>
        </section>
     <!--maincontent end-->
     
    <!--footer-->
    <%@include file="footer.jsp" %>
    <script>
        //            only For message show
            $(document).ready(function () {
                var messageTitle = document.getElementById('messageTitle').value;
                var messageText = document.getElementById('messageText').value;
                var typeIcon = document.getElementById('typeIcon').value;
            <%session.setAttribute("messageTitle", null);%>
            <%session.setAttribute("messageText", null);%>
            <%session.setAttribute("typeIcon", null);%>
                if (!(messageTitle === '' || messageTitle === null || messageTitle === 'undefined')) {
                    console.log(messageTitle);
                    swal({
                        title: messageTitle,
                        text: messageText,
                        type: typeIcon,
                        icon: typeIcon,
                        timer: 4000
                    });
                }
            });
    </script>
</body>
</html>
