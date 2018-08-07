<%-- 
    Document   : adminDashboard
    Created on : Aug 3, 2018, 7:06:06 PM
    Author     : Shahjahan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DWP Admin Dashboard</title>
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
        <section id="dwpdashboard">
            <div class="container">
                <div>
                    <input type="hidden" value="${messageTitle}" name="messageTitle" id="messageTitle"/>
                    <input type="hidden" value="${messageText}" name="messageText" id="messageText"/>
                    <input type="hidden" value="${typeIcon}" name="typeIcon" id="typeIcon"/>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <h2 class="display-6 text-center ">DWP Dashboard</h2>
                    </div>
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
                                        <th>Order By</th>
                                        <th>Car Provider</th>
                                        <th>Car</th>
                                        <th>Form</th>
                                        <th>To</th>
                                        <th>Total Fare</th>
                                        <th>Date</th>
                                    </tr>
                                </thead>
                                <tbody id="tableRow">

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
    <!-- /.container -->

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
                url: "../CurrentMonthRentForDWP",
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