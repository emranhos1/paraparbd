<%-- 
    Document   : forgetPassword
    Created on : Aug 1, 2018, 12:32:28 PM
    Author     : Shahjahan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Parapar - Forget Password</title>
        <!--head-->
        <%@include file="head.jsp" %>
    </head>
    <body>
        <!--navbar-->
        <%@include file="navbar.jsp" %>
        <div class="forgetpasscontent">
            <a style="float: right; font-weight: bold; color:black; text-decoration: none;" href="index.jsp">&#10006;</a>
            <center>
                <!--message div-->
                <div>
                    <input type="hidden" value="${messageTitle}" name="messageTitle" id="messageTitle"/>
                    <input type="hidden" value="${messageText}" name="messageText" id="messageText"/>
                    <input type="hidden" value="${typeIcon}" name="typeIcon" id="typeIcon"/>
                </div>

                <h2 class="display-6">Forget Password</h2><hr>

                <section id="forgetpassword">
                    <div class="container">
                        <div class="col-md-offset-3 col-md-12">

                            <form action="UpdatePassword" class="form-horizontal modal-body" method="post" accept-charset="utf-8">
                                <div id="company" class="modal-body">
                                    <div class="input-group mb-3 form-group">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text" id="basic-addon1"><i class="fa fa-phone"></i></span>
                                        </div>
                                        <input class="form-control input" type="text" value="" name="cellNo" id="cellNo" placeholder="Insert Your Phone No" required>
                                    </div>
                                    <span class="" id="checkPhoneNo"></span>

                                    <div class="form-group">
                                        <div class="input-group mb-3 ml-5">
                                            <button type="button" class="btn btn-success ml-5 input" id="next_btn">Next</button>
                                        </div>
                                    </div>
                                </div>

                                <div id="user">
                                    <div class="input-group mb-3 form-group">
                                        <label class="registrationFormAlert" id="message"></label>
                                        <input name="loginTableId" id="loginTableId" type="hidden" class="form-control input" required>
                                        <input name="randomCodeDB" id="randomCodeDB" type="hidden" class="form-control input" required>
                                        <div class="input-group-prepend">
                                            <span class="input-group-text" id="basic-addon1"><i class="fa fa-unlock"></i></span>
                                        </div>
                                        <input name="randomCode" id="randomCode" type="text" class="form-control input" placeholder="5 Digit Code" required>
                                        <span class="" id="checkRandomCode"></span>
                                        <span>5 digit code was sent to your mobile please submit that code here</span>
                                    </div>
                                    <div class="card-body text-center">
                                        <button type="button" class="btn btn-success ml-5 input" id="next_btn1">Next</button>
                                    </div>
                                </div>

                                <div id="newPass">
                                    <div class="input-group mb-3 form-group">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text" id="basic-addon1"><i class="fa fa-unlock"></i></span>
                                        </div>
                                        <input class="form-control input" type="password" value="" name="newPass" id="newPass" placeholder="New Password" required>
                                    </div>
                                    <div class="div-center col-12">
                                        <input type="submit" name="submit" value="Update"  class="btn btn-success input" />
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </section>
            </center>
        </div>

        <%@include file="footer.jsp" %>

        <script>
            //            only For message show
            $(document).ready(function () {
                $("#user").hide();
//                document.getElementById("phoneNo").focus();

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

            var cellNo;
            var cellNo1;
            $("#newPass").hide();
            $("#next_btn").click(function () {
                console.log('next_btn Clicked');

                cellNo1 = $("#cellNo").val();

                if (cellNo1 === '' || cellNo1 === null || cellNo1 === 'undefined') {
                    $("#checkPhoneNo").html("<font color='red'>Give Phone No</font>");
                    document.getElementById("cellNo").focus();
                } else {
                    $.ajax({
                        type: "POST",
                        url: "CheckCellNo",
                        data: 'cellNo=' + cellNo1,
                        success: function (data) {
                            console.log('ajax called');
                            $.each(data, function (index, element) {
                                cellNo = element.cellNo;
                                var loginTableId = element.loginTableId;
                                $(".modal-body #loginTableId").val(loginTableId);
                                console.log('cell from db :' + cellNo);
                                console.log('loginTableId db :' + loginTableId);
                                if (cellNo1 !== cellNo) {
                                    $("#checkPhoneNo").html("<font color='red'>This Phone is not Registred Yet <br> Please Try Another</font>");
                                } else if (cellNo === '' || cellNo === null || cellNo === 'undefined') {
                                    $("#checkPhoneNo").html("<font color='red'>This Phone is not Registred Yet <br> Please Try Another</font>");
                                } else {
                                    $("#company").slideUp();
                                    $("#user").show();
//                                    $("#newPass").hide();
                                    $.ajax({
                                        type: "POST",
                                        url: "ResetPassSendSmsServlet",
                                        data: 'cellNo=' + cellNo1,
                                        success: function (data) {
                                            $.each(data, function (index, element) {
                                                var randomcode = element.randomcode;
                                                $(".modal-body #randomCodeDB").val(randomcode);
                                            });
                                        }
                                    });
                                }
                            });
                        }
                    });
                }
            });

            $("#next_btn1").click(function () {
                console.log('next_btn1 Clicked');

                var loginTableId = $("#loginTableId").val();
                var randomCodeDB = $("#randomCodeDB").val();
                var randomCode = $("#randomCode").val();

                if (randomCodeDB !== randomCode) {
                    $("#checkRandomCode").html("<font color='red'>Code Not Match <br> Please Try Another <br></font>");
                } else if (loginTableId === '' || loginTableId === null || loginTableId === 'undefined') {
                    $("#checkRandomCode").html("<font color='red'>Code Not Match <br> Intranal Error Please Reload Page and try again <br></font>");
                } else if (randomCodeDB === '' || randomCodeDB === null || randomCodeDB === 'undefined') {
                    $("#checkRandomCode").html("<font color='red'>Intranal Error Please Reload Page and try again <br></font>");
                } else {

                    $("#user").slideUp();
                    $("#newPass").show();
                }
            });

            $("#pre_btn").click(function () {
                $("#user").hide();
                $("#company").slideDown();
            });
        </script>
    </body>
</html>
