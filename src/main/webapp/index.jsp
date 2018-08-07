<%-- 
    Document   : index
    Created on : Jul 28, 2018, 2:34:58 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <!--head-->
        <%@include file="head.jsp" %>
        <meta name="viewport" content="width=device-width, initial-scale=1">
    </head>

    <body>
        <!--navbar-->
        <%@include file="navbar.jsp" %>

        <!-- Page Content -->
        <div class="container">
            
            <!--message div-->
            <div>
                <input type="hidden" value="${messageTitle}" name="messageTitle" id="messageTitle"/>
                <input type="hidden" value="${messageText}" name="messageText" id="messageText"/>
                <input type="hidden" value="${typeIcon}" name="typeIcon" id="typeIcon"/>
            </div>
            
            <center>
                <div class="mybody">
                    <div class="myclass">
                        <a href="#" onclick="rideSharing()">
                            <div class="btN">
                                <div class="btn-imgsec">
                                    <img class="btn-image" src="assets/img/bike-color.png">
                                </div>
                                <div class="btn-text">
                                    <span>Ride Sharing</span>
                                </div>
                            </div>
                        </a>               
                    </div>

                    <div class="myclass">
                        <a href="#" onclick="rentACar()">
                            <div class="btN">
                                <div class="btn-imgsec">
                                    <img class="btn-image" src="assets/img/car-color.png">
                                </div>
                                <div class="btn-text">
                                    <span>Rent A Car</span>
                                </div>
                            </div>
                        </a>               
                    </div>

                    <div class="myclass">
                        <a href="#" onclick="ebusticket()">
                            <div class="btN">
                                <div class="btn-imgsec">
                                    <img class="btn-image" src="assets/img/bus-color.png">
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
        
        <script>
            function rideSharing() {
    //            only For message show
                $(document).ready(function () {
                    document.getElementById("phoneNo").focus();

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
            function rentACar() {
    //            only For message show
                $(document).ready(function () {
                    document.getElementById("phoneNo").focus();

                    var messageTitle = 'Login First';
                    var messageText = 'Need To Login First';
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
                    document.getElementById("phoneNo").focus();

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
                document.getElementById("phoneNo").focus();

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
