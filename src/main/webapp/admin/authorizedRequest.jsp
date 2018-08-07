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
        <title>Parapar - Authorized Request</title>

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
                <div class="row">
                    <div class="col-md-12">
                        <a style="float: right; font-weight: bold; text-decoration: none;" href="adminDashboard.jsp">&#10006;</a>
                        <h2 class="display-6 text-center ">Authorized Request</h2><hr>
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
                                            <th>Common User Details</th>
                                            <th>Car</th>
                                            <th>Origin</th>
                                            <th>Destination</th>
                                            <th>Fare</th>
                                            <th>Date</th>
                                            <th>Pick up Address</th>
                                            <th>Drop Address</th>
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
        <!-- main content start-->

        <!--footer-->
        <%@include file="footer.jsp" %>
        <%}%>
        <script>
            $(window).on("load", function () {
                $.ajax({
                    type: "POST",
                    url: "../AllAuthorizedRequestServlet",
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
        </script>
    </body>
</html>