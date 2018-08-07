<%-- 
    Document   : pendingBooking
    Created on : Aug 3, 2018, 6:49:39 PM
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

        <!--main content start-->
        <section id="allPendingBooking">
            <div class="container">
                <div>
                    <input type="hidden" value="${messageTitle}" name="messageTitle" id="messageTitle"/>
                    <input type="hidden" value="${messageText}" name="messageText" id="messageText"/>
                    <input type="hidden" value="${typeIcon}" name="typeIcon" id="typeIcon"/>
                </div>

                <div class="row">
                    <div class="col-md-12">
                        <a style="float: right; font-weight: bold; text-decoration: none;" href="commonUserDashboard.jsp">&#10006;</a>
                        <h2 class="display-6 text-center ">All Pending Booking</h2><hr>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-4">

                    </div>
                    <div class="col-md-8">

                    </div>
                </div>

                <div class="table-responsive">
                    <div class="panel-body col-sm-12">
                        <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                            <thead>
                                <tr>
                                    <th>SL</th>
                                    <th>Order No</th>
                                    <th>Messege</th>
                                    <th></th>
                                </tr>
                            </thead>

                            <tbody id="tableRow">

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
                
            <!--Specification Dialog for Transection Code-->
            <div class="modal fade" id="addSpecTransectionCode" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Are You Sure To Inactive This Rent A Car?</h5>
                            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">×</span>
                            </button>
                        </div>
                        <div style="border: none;" class="modal-body card card-register mx-auto">


                            <form action="../UpdateBkashTable" accept-charset="UTF-8" method="post" role="form" class="form-horizontal">
                                <input  type="hidden" id="bkashTableId" name="bkashTableId" class="form-control" value="" readonly/>
                                <div class="form-group">
                                    <div class="form-group col-md-12">
                                        <input  type="text" id="transectionCode" name="transectionCode" class="form-control" value="" required/>  
                                    </div>
                                    <div class="form-group col-md-12">
                                        <button type="submit" class="btn btn-primary btn-block">Send Transection Code</button>
                                    </div>
                                </div>
                            </form>

                        </div>
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
                    url: "../AllPandingBooking",
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
            });

            $(document).on("click", ".open-spceDialog-TransectionCode", function () {
                var bkashTableId = $(this).data('bkashtableid');

                $(".modal-body #bkashTableId").val(bkashTableId);
            });
        </script>
    </body>
</html>