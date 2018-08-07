<%-- 
    Document   : commonUserDashboard
    Created on : Aug 3, 2018, 6:43:45 PM
    Author     : Shahjahan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Parapar - User Dashboard</title>
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

        <!-- Page Content -->
        <div class="container">
            <center>
                <!--message div-->
                <div>
                    <input type="hidden" value="${messageTitle}" name="messageTitle" id="messageTitle"/>
                    <input type="hidden" value="${messageText}" name="messageText" id="messageText"/>
                    <input type="hidden" value="${typeIcon}" name="typeIcon" id="typeIcon"/>
                </div>
                <div class="mybody">
                    <div class="myclass">
                        <a href="#" onclick="rideSharing()">
                            <div class="btN">
                                <div class="btn-imgsec">
                                    <img class="btn-image" src="../assets/img/bike-color.png">
                                </div>
                                <div class="btn-text">
                                    <span>Ride Sharing</span>
                                </div>
                            </div>
                        </a>               
                    </div>

                    <div class="myclass">
                        <a href="rentACarBooking.jsp">
                            <div class="btN">
                                <div class="btn-imgsec">
                                    <img class="btn-image" src="../assets/img/car-color.png">
                                </div>
                                <div class="btn-text">
                                    <span>Rent A Car</span>
                                </div>
                            </div>
                        </a>               
                    </div>

                    <div class="myclass">
                        <a href="#" onclick="ebusticket()()">
                            <div class="btN">
                                <div class="btn-imgsec">
                                    <img class="btn-image" src="../assets/img/bus-color.png">
                                </div>
                                <div class="btn-text">
                                    <span>E-Bus Ticketing</span>
                                </div>
                            </div>
                        </a>               
                    </div>
                </div>
            </center>
        </div>
        <!-- /.container -->
        <!--footer-->
        <%@include file="footer.jsp" %>
        <%}%>
        <script>
            
            function rideSharing() {
    //            only For message show
                $(document).ready(function () {
                    var messageTitle = 'Comming Soon';
                    var messageText = 'Service Not Available Right Now!';
                    var typeIcon = 'warning';
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
            }

            function ebusticket() {
                //            only For message show
                $(document).ready(function () {
                    var messageTitle = 'Comming Soon';
                    var messageText = 'Service Not Available Right Now!';
                    var typeIcon = 'warning';
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
            }
            
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