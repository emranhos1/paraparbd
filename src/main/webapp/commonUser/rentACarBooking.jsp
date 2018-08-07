<%-- 
    Document   : rentACarBooking
    Created on : Aug 3, 2018, 6:50:06 PM
    Author     : Shahjahan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Rent A Car Booking</title>
        <!--head-->
        <%@include file="head.jsp" %>

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
        <!--<link href="../assets/css/style.css" rel="stylesheet" type="text/css"/>-->
        <link href="../assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="../assets/vendor/timepiker/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%
            if ((session.getAttribute("idUser") == null) || (session.getAttribute("idUser") == "")) {
                response.sendRedirect("../index.jsp");
            } else {%>
        <!--navbar-->
        <%@include file="navbar.jsp" %>

        <!--main content start-->
        <section id="rentacarbooking">
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
                        <h2 class="display-6 text-center ">Rent A Car Booking</h2><hr>
                        <div class="alert alert-warning text-center" role="alert">
                            যদি গাড়ি সিলেক্ট না হয় তবে এই সেবাটি এই জেলার জন্য প্রযোজ্য নয়!!!!
                        </div>
                    </div>
                </div>

                <div class="page-content row mt-3">
                    <div class="main-content">
                        <form id="form" role="form" action="../AddRentACarBookingServlet" method="post" class="form-horizontal">

                            <div class="row col-12">
                                <div class=" col-4" style="float: left">
                                    <label style="text-align: left;" class="control-label"><b>DateTime Picking: </b></label>
                                </div>
                                <div class="col-8 input-append date form_datetime" data-date-format="yyyy-mm-dd HH:mm p" data-link-field="dtp_input1">
                                    <input style="height: 30px" name="travelDate" id="travelDate" type="text" value="" required/>
                                    <span class="add-on" style="height: 30px"><i class="icon-remove"></i></span>
                                    <span class="add-on" style="height: 30px"><i class="icon-th"></i></span>
                                </div>
                                <input type="hidden" id="dtp_input1" value="" /><br/>
                            </div>

                            <!--orign-->
                            <div class="row mt-3">
                                <div class="col-md-12 text-center">
                                    <h4 class="lead"><b>Form:</b></h4>
                                </div>

                                <div class="col-md-4">
                                    <div class="form-group">
                                        <select class="form-control input req-active" name="divisionNameDropDownOrigin" id="divisionNameDropDownOrigin" required onchange="getDistictOrigin(this.value);">
                                            <option item="" value="">Division Name</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="col-md-4">
                                    <div class="form-group">
                                        <select class="form-control input req-active" name="distictNameDropDownOrigin" id="distictNameDropDownOrigin" required onchange="getPoliceStationOrigin(this.value)">
                                            <option value="">District Name</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="col-md-4">
                                    <div class="form-group">
                                        <select class="form-control input req-active" name="policeNameDropDownOrigin" id="policeNameDropDownOrigin" required>
                                            <option value="">Police Station Name</option>
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div class="row mt-3">
                                <div class="col-md-12">
                                    <select class="form-control col-12" name="carCategory" id="carCategory" required>
                                        <option value=''>Select Car</option>
                                    </select>
                                </div>
                            </div>

                            <div class="row mt-3">
                                <div class="col-md-12">
                                    <textarea class="form-control " type="text" value="" name="pickupAddress" id="pickupAddress" placeholder="Pickup Address : blog, road, house no." required></textarea>
                                </div>
                            </div>

                            <!--Destination-->
                            <div class="row mt-3">
                                <div class="col-md-12 text-center">
                                    <h4 class="lead"><b>Destination:</b></h4>
                                </div>

                                <div class="col-md-4">
                                    <div class="form-group">
                                        <select class="form-control input req-active" name="divisionNameDropDownDestination" id="divisionNameDropDownDestination" required onchange="getDistictDestination(this.value);">
                                            <option value="">Division Name</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="col-md-4">
                                    <div class="form-group">
                                        <select class="form-control input req-active" name="distictNameDropDownDestination" id="distictNameDropDownDestination" required onchange="getPoliceStationDestination(this.value)">
                                            <option value="">District Name</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="col-md-4">
                                    <div class="form-group">
                                        <select class="form-control input req-active" name="policeNameDropDownDestination" id="policeNameDropDownDestination" required>
                                            <option value="">Police Station Name</option>
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div class="row mt-3">
                                <div class="col-md-12 ">
                                    <textarea class="form-control" type="text" value="" name="DropAddress" id="DropAddress" placeholder="Drop Address : blog, road, house no." required></textarea>
                                    <label class="registrationFormAlert" id="checkAddress"></label>
                                </div>
                            </div>

                            <div class="row mt-3 result">
                                <div class="col-md-12 text-center">
                                    <h4 class="lead"><b>Total Amount :</b></h4>
                                </div>
                                <div class="col-md-12">
                                    <input class="form-control " id="totalTake" type="text" name="totalTake" placeholder="Total Amount :" readonly required/>
                                </div>
                            </div>

                            <div class="row mt-3">
                                <div class="col-md-12 text-center">
                                    <input type="submit" class="btn btn-success" value="Send Request"/>
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

        <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
        <!--<script src='https://maps.googleapis.com/maps/api/js?key=AIzaSyAd1xMYT1bt99qtFWQEzXiRBvORDWHgPtk&libraries=places'></script>-->
        <script src='https://maps.googleapis.com/maps/api/js?key=AIzaSyBlJ8R75vnO8AjS8fbN3xCc8fK9Vkzkhao&libraries=places'></script>
        <script src="../map/js/dindex.js" type="text/javascript"></script>
        <script src="../assets/vendor/timepiker/jquery-1.8.3.min.js" type="text/javascript"></script>
        <script src="../assets/vendor/timepiker/bootstrap-datetimepicker.js" type="text/javascript"></script>
        <script src="../assets/vendor/timepiker/bootstrap.min.js" type="text/javascript"></script>
        <script src="../assets/vendor/timepiker/bootstrap-datetimepicker.fr.js" type="text/javascript"></script>
        <script type="text/javascript">
                                            $('.form_datetime').datetimepicker({
                                                //language:  'fr',
                                                weekStart: 1,
                                                todayBtn: 1,
                                                autoclose: 1,
                                                todayHighlight: 1,
                                                startView: 2,
                                                forceParse: 0,
                                                showMeridian: 1
                                            });

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
                                                    url: "../AllDivisionName",
                                                    success: function (data) {
                                                        $("#divisionNameDropDownOrigin").show();
                                                        $("#divisionNameDropDownOrigin").append(data);
                                                        $("#divisionNameDropDownDestination").show();
                                                        $("#divisionNameDropDownDestination").append(data);
                                                    }
                                                });
                                            });

                                            function getDistictOrigin(el) {
                                                var divisionNameDropDownOrigin = el;
                                                console.log(divisionNameDropDownOrigin);
                                                $.ajax({
                                                    type: "POST",
                                                    url: "../AllDistictNameOrigin",
                                                    data: 'divisionNameDropDownOrigin=' + divisionNameDropDownOrigin,
                                                    success: function (data) {
                                                        $("#distictNameDropDownOrigin").show();
                                                        $("#distictNameDropDownOrigin").html(data);
                                                    }
                                                });
                                            }

                                            function getDistictDestination(el) {
                                                var divisionNameDropDownDestination = el;
                                                console.log(divisionNameDropDownDestination);
                                                $.ajax({
                                                    type: "POST",
                                                    url: "../AllDistictNameDestination",
                                                    data: 'divisionNameDropDownDestination=' + divisionNameDropDownDestination,
                                                    success: function (data) {
                                                        $("#distictNameDropDownDestination").show();
                                                        $("#distictNameDropDownDestination").html(data);
                                                    }
                                                });
                                            }

                                            function getPoliceStationOrigin(el) {
                                                var districtNameDropDownOrigin = el;
                                                console.log(districtNameDropDownOrigin);
                                                $.ajax({
                                                    type: "POST",
                                                    url: "../AllPoliceStationNameOrigin",
                                                    data: 'districtNameDropDownOrigin=' + districtNameDropDownOrigin,
                                                    success: function (data) {
                                                        $("#policeNameDropDownOrigin").show();
                                                        $("#policeNameDropDownOrigin").html(data);
                                                    }
                                                });
                                                $.ajax({
                                                    type: "POST",
                                                    url: "../AllCarCategory",
                                                    data: 'districtNameDropDownOrigin=' + districtNameDropDownOrigin,
                                                    success: function (data) {
                                                        console.log(data);
                                                        $("#carCategory").show();
                                                        $("#carCategory").html(data);
                                                    }
                                                });
                                            }

                                            function getPoliceStationDestination(el) {
                                                var districtNameDropDownDestination = el;
                                                console.log(districtNameDropDownDestination);
                                                $.ajax({
                                                    type: "POST",
                                                    url: "../AllPoliceStationNameDestination",
                                                    data: 'districtNameDropDownDestination=' + districtNameDropDownDestination,
                                                    success: function (data) {
                                                        $("#policeNameDropDownDestination").show();
                                                        $("#policeNameDropDownDestination").html(data);
                                                    }
                                                });
                                            }
        </script>
    </body>
</html>