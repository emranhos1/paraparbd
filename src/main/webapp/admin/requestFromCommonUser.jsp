<%-- 
    Document   : activeRentACar
    Created on : Aug 5, 2018, 9:23:34 AM
    Author     : Shahjahan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Parapar - Request From Common User</title>

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
        <section id="requstfrmcomonuser">
            <div class="container">
                <!--message div-->
                <div>
                    <input type="hidden" value="${messageTitle}" name="messageTitle" id="messageTitle"/>
                    <input type="hidden" value="${messageText}" name="messageText" id="messageText"/>
                    <input type="hidden" value="${typeIcon}" name="typeIcon" id="typeIcon"/>
                </div>

                <div class="row">
                    <div class="col-md-12">
                        <a style="float: right; font-weight: bold; text-decoration: none;" href="adminDashboard.jsp">&#10006;</a>
                        <h2 class="display-6 text-center ">Request From Common User</h2><hr>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-12">
                        <div class="table-responsive">
                            <div class="panel-body col-sm-12">
                                <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                        <tr>
                                            <th>SL</th>
                                            <th>Order No (Transaction No)</th>
                                            <th>Common User Details</th>
                                            <th>Car</th>
                                            <th>From - To</th>
                                            <th>Fare</th>
                                            <th>Date</th>
                                            <th>Pick up Address</th>
                                            <th>Drop Address</th>
                                            <th>Authorize</th>
                                        </tr>
                                    </thead>
                                    <tbody id="tableRow">

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!--Specification Dialog for Authorize-->
        <div class="modal fade" id="addSpecCarSend" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Confirm Rent A Car</h5>
                        <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                    </div>
                    <div style="border: none" class="modal-body card card-register mx-auto">

                        <form action="../ConfirmBookingServlet" accept-charset="UTF-8" method="post" role="form" class="form-horizontal">
                            <input  type="hidden" id="rentacarbookingtableid" name="rentacarbookingtableid" class="form-control" value="" readonly/>
                            <input  type="hidden" id="transactionNo" name="transactionNo" class="form-control" value="" readonly/>
                            <input  type="hidden" id="orderNo" name="orderNo" class="form-control" value="" readonly/>
                            <input  type="hidden" id="commonuUserId" name="commonuUserId" class="form-control" value="" readonly/>

                            <div class="form-group row">
                                <label for="rentACarOwner" class="col-4 col-form-label">Rent A Car</label>
                                <div class="form-group col-8">
                                    <select class="form-control req-active" name="rentACarOwner" id="rentACarOwner" required>
                                        <!--<option item="" value="">Select One</option>-->

                                    </select>
                                </div>
                            </div>

                            <div class="form-row">
                                <div class="col-md-12">
                                    <button type="submit" class="btn btn-primary btn-block">Confirm Booking</button>
                                </div>
                            </div>
                        </form>

                    </div>
                </div>
            </div>
        </div>

        <!--Specification Dialog for Booking Payment-->
        <div class="modal fade" id="addSpecBookingPayment" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Send SMS For Booking Payment</h5>
                        <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                    </div>
                    <div style="border: none" class="modal-body1 card card-register mx-auto">

                        <form action="../BookingPaymentServlet" accept-charset="UTF-8" method="post" role="form" class="form-horizontal">
                            <input  type="hidden" id="rentacarbookingtableid" name="rentacarbookingtableid" class="form-control" value="" readonly/>
                            <input  type="hidden" id="commonUserId" name="commonUserId" class="form-control" value="" readonly/>
                            <input  type="hidden" id="totalFare" name="totalFare" class="form-control" value="" readonly/>

                            <div class="form-row">
                                <div class="col-md-12">
                                    <button type="submit" class="btn btn-primary btn-block">Send Payment Request</button>
                                </div>
                            </div>
                        </form>

                    </div>
                </div>
            </div>
        </div>

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
                    url: "../AllRentACarBookingServlet",
                    success: function (data) {
                        $("#tableRow").show();
                        $("#tableRow").html(data);
                        $('#dataTables-example').DataTable({
                            responsive: true,
                            "initComplete": function (settings, json) {
//                                AfterLoadTable();
                            }
                        });
                    }
                });

                $.ajax({
                    type: "POST",
                    url: "../AllRentACarServlet",
                    success: function (data) {
                        $("#rentACarOwner").show();
                        $("#rentACarOwner").append(data);
                    }
                });
            });

            $(document).on("click", ".open-spceDialog-carSend", function () {
                var rentacarbookingtableid = $(this).data('rentacarbookingtableid');
                var transactionno = $(this).data('transactionno');
                var orderno = $(this).data('orderno');
                var commonuserid = $(this).data('commonuserid');

                $(".modal-body #rentacarbookingtableid").val(rentacarbookingtableid);
                $(".modal-body #transactionNo").val(transactionno);
                $(".modal-body #orderNo").val(orderno);
                $(".modal-body #commonuUserId").val(commonuserid);
            });

            $(document).on("click", ".open-spceDialog-bookingPayment", function () {
                var commonUserId = $(this).data('commonuserid');
                var rentacarbookingtableid = $(this).data('rentacarbookingtableid');
                var totalFare = $(this).data('totalfare');

                $(".modal-body1 #rentacarbookingtableid").val(rentacarbookingtableid);
                $(".modal-body1 #commonUserId").val(commonUserId);
                $(".modal-body1 #totalFare").val(totalFare);
            });
        </script>
    </body>
</html>