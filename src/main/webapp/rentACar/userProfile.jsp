<%-- 
    Document   : userProfile
    Created on : Aug 3, 2018, 7:01:48 PM
    Author     : Shahjahan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile</title>

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
        <!--  maincontent start-->
        <section id="userfrofile">
            <div class="container modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <h2 class="display-6 text-center ">User Profile</h2><hr>
                        <div class="alert alert-warning text-center" role="alert">
                            You Can Not Edit Your Profile For Edit Purpose Contact With Us!
                        </div>
                    </div>
                </div>

                <form id="form" role="form" action="" method="post" class="form-horizontal">
                    <div class="row text-center">
                        <div class="col-md-12">
                            <img id="scanFile" alt="Can't Find Image" height="200px" width="200px"/>
                        </div>
                        <input class="form-control input col-3" type="text" name="rentACarOwnerId" id="rentACarOwnerId" required readonly hidden>
                    </div>

                    <div class="row mt-5">
                        <div class="col-md-6">
                            <div class="form-group">
                                <div class="input-group mb-3 ">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text bg-success text-light" id="basic-addon1"">First Name</span>
                                    </div>
                                    <input class="form-control input" type="text" name="firstName" id="firstName" required readonly>
                                </div>
                            </div>

                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text bg-success text-light" id="basic-addon1"">Last Name</span>
                                    </div>
                                    <input class="form-control input" type="text" name="lastName" id="lastName" required readonly>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row mt-5">
                        <div class="col-md-6">
                            <div class="form-group">
                                <div class="input-group mb-3 ">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text bg-success text-light" id="basic-addon1">Email</span>
                                    </div>
                                    <input class="form-control input" type="text" name="email" id="email" required readonly>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="form-group">
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text bg-success text-light" id="basic-addon1">Phone</span>
                                    </div>
                                    <input class="form-control input" type="text" name="cellNo" id="cellNo" required readonly>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row mt-5">
                        <div class="col-md-6">
                            <div class="form-group">
                                <div class="input-group mb-3 ">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text bg-success text-light" id="basic-addon1"">Division Name</span>
                                    </div>
                                    <input class="form-control input" type="text" name="divisionName" id="divisionName" required readonly>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="form-group">
                                <div class="input-group mb-3 ">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text bg-success text-light" id="basic-addon1"">District Name</span>
                                    </div>
                                    <input class="form-control input" type="text" name="districtName" id="districtName" required readonly>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row mt-5">
                        <div class="col-md-6">
                            <div class="form-group">
                                <div class="input-group mb-3 ">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text bg-success text-light" id="basic-addon1"">Police Station</span>
                                    </div>
                                    <input class="form-control input" type="text" name="policeStationName" id="policeStationName" required readonly>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="form-group">
                                <div class="input-group mb-3 ">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text bg-success text-light" id="basic-addon1"">Address</span>
                                    </div>
                                    <textarea class="form-control input" type="text" name="address" id="address" rows="3" required readonly></textarea>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row mt-5">
                        <div class="col-md-6">
                            <div class="form-group">
                                <div class="input-group mb-3 ">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text bg-success text-light" id="basic-addon1"">Zip Code</span>
                                    </div>
                                    <input class="form-control input" type="text" name="zipCode" id="zipCode" required readonly/>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="form-group">
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text bg-success text-light" id="basic-addon1"">Nid No</span>
                                    </div>
                                    <input class="form-control input" type="text" name="nidNo" id="nidNo" required readonly/>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row mt-5">
                        <div class="col-md-6">
                            <div class="form-group">
                                <div class="input-group mb-3 ">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text bg-success text-light" id="basic-addon1"">Tin No</span>
                                    </div>
                                    <input class="form-control input" type="text" name="tinNo" id="tinNo" required readonly/>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="form-group">
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text bg-success text-light" id="basic-addon1"">Trade license No</span>
                                    </div>
                                    <input class="form-control input" type="text" name="tradeLisenceNo" id="tradeLisenceNo" required readonly/>
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
            setTimeout(function () {
                $('#message').fadeOut('slow');
            }, 2000);

            $(window).on("load", function () {
                $.ajax({
                    type: "POST",
                    url: "../RentACarInfoServlet",
                    success: function (data) {
                        console.log('ajax called');
                        $.each(data, function (index, element) {
                            var rentACarOwnerId = element.rentACarOwnerId;
                            var firstName = element.firstName;
                            var lastName = element.lastName;
                            var email = element.email;
                            var phoneNo = element.phoneNo;
                            var address = element.address;
                            var zipCode = element.zipCode;
                            var divisionName = element.divisionName;
                            var districtName = element.districtName;
                            var policeStationName = element.policeStationName;
                            var nidNo = element.nidNo;
                            var tinNo = element.tinNo;
                            var tradeLisenceNo = element.tradeLisenceNo;
                            var rentDeviceEmiNo = element.rentDeviceEmiNo;
                            var registrationDate = element.registrationDate;
                            var photo = element.photo;

                            $(".modal-body #rentACarOwnerId").val(rentACarOwnerId);
                            $(".modal-body #firstName").val(firstName);
                            $(".modal-body #lastName").val(lastName);
                            $(".modal-body #email").val(email);
                            $(".modal-body #cellNo").val(phoneNo);
                            $(".modal-body #address").val(address);
                            $(".modal-body #zipCode").val(zipCode);
                            $(".modal-body #divisionName").val(divisionName);
                            $(".modal-body #districtName").val(districtName);
                            $(".modal-body #policeStationName").val(policeStationName);
                            $(".modal-body #nidNo").val(nidNo);
                            $(".modal-body #tinNo").val(tinNo);
                            $(".modal-body #tradeLisenceNo").val(tradeLisenceNo);
                            $(".modal-body #rentDeviceEmiNo").val(rentDeviceEmiNo);
                            $(".modal-body #registrationDate").val(registrationDate);
                            $(".modal-body #scanFile").attr('src', '../allImage/' + photo);
                        });
                    }
                });
            });
        </script>
    </body>
</html>