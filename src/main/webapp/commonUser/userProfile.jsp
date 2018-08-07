<%-- 
    Document   : userProfile
    Created on : Aug 3, 2018, 6:50:36 PM
    Author     : Shahjahan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Profile</title>
        <!--head-->
        <%@include file="head.jsp" %>
        
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
        <link href="../assets/css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%
            if ((session.getAttribute("idUser") == null) || (session.getAttribute("idUser") == "")) {
                response.sendRedirect("../index.jsp");
            } else {%>
        <!--navbar-->
        <%@include file="navbar.jsp" %>
        <!--navbar end-->

        <!--main content start-->
        <section id="userfrofile">
            <div class="container">
                <!--message div-->
                <div>
                    <input type="hidden" value="${messageTitle}" name="messageTitle" id="messageTitle"/>
                    <input type="hidden" value="${messageText}" name="messageText" id="messageText"/>
                    <input type="hidden" value="${typeIcon}" name="typeIcon" id="typeIcon"/>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <a style="float: right; font-weight: bold; text-decoration: none;" href="commonUserDashboard.jsp">&#10006;</a>
                        <h2 class="display-6 text-center ">User Profile</h2><hr>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-12 modal-body">
                        <form id="form" role="form" action="../UpdateUserInfoServlet" method="post" class="form-horizontal">
                            <div class="form-group">
                                <div class="input-group mb-3 ">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1"><i class="fa fa-camera"></i></span>
                                    </div>
                                    <center><img id="scanFile" alt="Image Not Found!" style="max-height: 200px; max-width: 200px"/></center>
                                    <input class="form-control col-3" type="text" name="commonUserId" id="commonUserId" required readonly hidden>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="input-group mb-3 ">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1"><i class="fa fa-user"></i></span>
                                    </div>
                                    <!--<input type="text" class="form-control" placeholder="FirstName" aria-label="Username" aria-describedby="basic-addon1">-->
                                    <input class="form-control" type="text" name="firstName" id="firstName" required>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1"><i class="fa fa-user"></i></span>
                                    </div>
                                    <!--<input type="text" class="form-control" placeholder="Last Name" aria-label="Username" aria-describedby="basic-addon1">-->
                                    <input class="form-control" type="text" name="lastName" id="lastName" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="emailHelp"><i class="fa fa-envelope"></i></span>
                                    </div>
                                    <!--<input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">-->
                                    <input class="form-control" type="text" name="email" id="email" required>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="input-group mb-3 ">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1"><i class="fa fa-phone"></i></span>
                                    </div>                            
                                    <!--<input class="form-control" id="inputPhone" maxlength="13" name="phone" required="required" placeholder="Phone Number" size="3" title="" type="tel" value="">-->
                                    <input class="form-control" type="text" name="cellNo" id="cellNo" required readonly>
                                </div>
                            </div>


                            <div class="form-group">
                                <div class="input-group mb-3 ">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1"><i class="fa fa-map-marker"></i></span>
                                    </div>                            
                                    <!--<textarea class="form-control" aria-label="With textarea" placeholder="Address" rows="3"></textarea>-->       
                                    <textarea rows="3" class="form-control" type="text" name="address" id="address" required></textarea>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="input-group mb-3 ">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1"><i class="fa fa-lock"></i></span>
                                    </div>                            
                                    <!--<input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">-->
                                    <input class="form-control" type="password" name="password" id="password" placeholder="Password" required/>
                                </div>
                            </div>

                            <div class="form-group ml-5">
                                <div class="input-group mb-3 ml-5">
                                    <!--<button type="submit" class="btn btn-success">Save Changes</button>-->
                                    <button type="submit" class="btn btn-success">Save Changes</button>
                                </div> 
                            </div>
                        </form>
                    </div>
                </div>
            </div>

        </section>
        <!--main content start-->

        <!--footer-->
        <%@include file="footer.jsp" %>
        <%}%>
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
            $(window).on("load", function () {
                $.ajax({
                    type: "POST",
                    url: "../UserInfoServlet",
                    success: function (data) {
                        console.log('ajax called');
                        $.each(data, function (index, element) {
                            var commonUserId = element.commonUserId;
                            var firstName = element.firstName;
                            var lastName = element.lastName;
                            var email = element.email;
                            var phoneNo = element.phoneNo;
                            var address = element.address;
                            var deviceImeNo = element.deviceImeNo;
                            var deviceIp = element.deviceIp;
                            var createdDate = element.createdDate;
                            var photo = element.photo;

                            $(".modal-body #commonUserId").val(commonUserId);
                            $(".modal-body #firstName").val(firstName);
                            $(".modal-body #lastName").val(lastName);
                            $(".modal-body #email").val(email);
                            $(".modal-body #cellNo").val(phoneNo);
                            $(".modal-body #address").val(address);
                            $(".modal-body #deviceImeNo").val(deviceImeNo);
                            $(".modal-body #deviceIp").val(deviceIp);
                            $(".modal-body #createdDate").val(createdDate);
                            $(".modal-body #scanFile").attr('src', '../allImage/' + photo);
                        });
                    }
                });
            });
        </script>
    </body>
</html>