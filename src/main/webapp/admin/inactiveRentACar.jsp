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

        <!-- Page Content -->
        <section id="activerentacar">
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
                        <h2 class="display-6 text-center ">All Inactive Rent A Car</h2><hr>
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
                                            <th>Name</th>
                                            <th>Email</th>
                                            <th>Phone No</th>
                                            <th>Division Name</th>
                                            <th>District Name</th>
                                            <th>Police Station Name</th>
                                            <th></th>
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
                
            <!--Specification Dialog for Authorize-->
            <div class="modal fade" id="addSpecActive" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Are You Sure To Active This Rent A Car?</h5>
                            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">×</span>
                            </button>
                        </div>
                        <div style="border: none;" class="modal-body card card-register mx-auto">

                            <form action="../ActiveRentACarServlet" accept-charset="UTF-8" method="post" role="form" class="form-horizontal">
                                <input  type="hidden" id="allUserId" name="allUserId" class="form-control" value="" readonly/>
                                <div class="form-row">
                                    <div class="col-md-12">
                                        <button type="submit" class="btn btn-primary btn-block">Active</button>
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
            setTimeout(function () {
                $('#message').fadeOut('fast');
            }, 2000);
            $(window).on("load", function () {
                $.ajax({
                    type: "POST",
                    url: "../AllInactiveRentACarServlet",
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

            $(document).on("click", ".open-spceDialog-active", function () {
                var alluserid = $(this).data('alluserid');

                $(".modal-body #allUserId").val(alluserid);
            });
        </script>
    </body>
</html>