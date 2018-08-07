<%-- 
    Document   : farePanel
    Created on : Aug 5, 2018, 9:31:11 AM
    Author     : Shahjahan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Parapar - All Inactive Rent A Car</title>

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

        <!--main content start-->
        <!-- Page Content -->
        <section id="farepanel">
            <div class="container modal-body">
                <!--message div-->
                <div>
                    <input type="hidden" value="${messageTitle}" name="messageTitle" id="messageTitle"/>
                    <input type="hidden" value="${messageText}" name="messageText" id="messageText"/>
                    <input type="hidden" value="${typeIcon}" name="typeIcon" id="typeIcon"/>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <a style="float: right; font-weight: bold; text-decoration: none;" href="adminDashboard.jsp">&#10006;</a>
                        <h2 class="display-6 text-center text-success"><b>Fare Panel</b></h2><hr>
                    </div>
                </div>

                <form id="form" role="form" action="../UpdateFareRate" method="post" class="form-horizontal">

                    <input type="hidden" name="cuFareRateTableId" id="cuFareRateTableId" class="form-control">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <div class="input-group mb-3 text-center ">
                                    <span class="text-dark" ><b>Per Km Rate</b></span>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-12">
                            <div class="form-group">
                                <div class="input-group mb-3 ">
                                    <input type="text" name="parKmRate" id="parKmRate" class="form-control">
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <div class="input-group mb-3 text-center ">
                                    <span class="text-dark" ><b>Body Lass 200km</b></span>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="form-group">
                                <div class="input-group mb-3 ">
                                    <input type="text" name="bodyLass200km" id="bodyLass200km" class="form-control">
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <div class="input-group mb-3 text-center ">
                                    <span class="text-dark" ><b>Body Big 200km</b></span>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="form-group">
                                <div class="input-group mb-3 ">

                                    <input type="text" name="bodyBig200km" id="bodyBig200km" class="form-control">
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <div class="input-group mb-3 text-center ">
                                    <span class="text-dark" ><b>Driver Cost</b></span>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="form-group">
                                <div class="input-group mb-3 ">
                                    <input type="text" name="driverCost" id="driverCost" class="form-control">
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <div class="input-group mb-3 text-center ">
                                    <span class="text-dark" ><b>Other Cost</b></span>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="form-group">
                                <div class="input-group mb-3 ">
                                    <input type="text" name="otherCost" id="otherCost" class="form-control">
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">                   
                        <div class="col-md-12">
                            <div class="form-group">
                                <div class="input-group mb-3 ml-5 ">
                                    <button type="submit" class="btn btn-success">Save Changes</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
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
                    url: "../AllFareRate",
                    success: function (data) {
                        console.log('ajax called');
                        $.each(data, function (index, element) {
                            var cuFareRateTableId = element.cuFareRateTableId;
                            var parKmRate = element.parKmRate;
                            var bodyLess200km = element.bodyLess200km;
                            var bodyBig200km = element.bodyBig200km;
                            var driverCost = element.driverCost;
                            var otherCost = element.otherCost;

                            $(".modal-body #cuFareRateTableId").val(cuFareRateTableId);
                            $(".modal-body #parKmRate").val(parKmRate);
                            $(".modal-body #bodyLass200km").val(bodyLess200km);
                            $(".modal-body #bodyBig200km").val(bodyBig200km);
                            $(".modal-body #driverCost").val(driverCost);
                            $(".modal-body #otherCost").val(otherCost);
                        });
                    }
                });
            });
        </script>
    </body>
</html>