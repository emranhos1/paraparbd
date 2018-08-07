<%-- 
    Document   : allCar
    Created on : Aug 3, 2018, 7:00:55 PM
    Author     : Shahjahan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard</title>

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
        <section id="allcar">
            <div class="container">
                <!--message div-->
                <div>
                    <input type="hidden" value="${messageTitle}" name="messageTitle" id="messageTitle"/>
                    <input type="hidden" value="${messageText}" name="messageText" id="messageText"/>
                    <input type="hidden" value="${typeIcon}" name="typeIcon" id="typeIcon"/>
                </div>

                <div class="row">
                    <div class="col-md-12">
                        <h2 class="display-6 text-center ">All Car</h2><hr>
                    </div>
                </div>


                <div class="row">
                    <div class="col-md-4">
                    </div>
                    <div class="col-md-8">
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-12">
                        <div class="table-responsive">
                            <form id="form" role="form" action="" method="post" class="form-horizontal">
                                <div class="panel-body col-sm-12">
                                    <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                        <thead>
                                            <tr>
                                                <th>SL</th>
                                                <th>Car Registration No</th>
                                                <th>Brand Name</th>
                                                <th>Model Name</th>
                                                <th>Color Name</th>
                                                <th>Car Registration Scan Copy</th>
                                                <th></th>
                                            </tr>
                                        </thead>

                                        <tbody id="tableRow">

                                        </tbody>
                                    </table>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!-- Delete Modal-->
        <div class="modal fade" id="addSpecDelete" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Are You Sure You Want To Delete This Car?</h5>
                        <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                    </div>
                    <div style="padding: 10px; border: none;" class="modal-body-delete card card-register mx-auto">


                        <form action="../DeleteCarServlet" accept-charset="UTF-8" method="post" role="form" class="form-horizontal">
                            <input  type="hidden" id="carCategoryId" name="carCategoryId" class="form-control" value=""/>
                            <div class="form-row">
                                <div class="col-md-6">
                                    <button type="submit" class="btn btn-primary btn-block">Delete</button>
                                </div>
                                <div class="col-md-6">
                                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                                </div>
                            </div>
                        </form>

                    </div>
                </div>
            </div>
        </div>

        <!--main content end-->
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
            $(document).on("click", ".open-spceDialog-delete", function () {

                var carCategoryId = $(this).data('carcategoryid');
                console.log(carCategoryId);
                $(".modal-body-delete #carCategoryId").val(carCategoryId);
            });

            $(window).on("load", function () {
                $.ajax({
                    type: "POST",
                    url: "../AllCarServlet",
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