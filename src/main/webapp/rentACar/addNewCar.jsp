<%-- 
    Document   : addNewCar
    Created on : Aug 3, 2018, 7:00:35 PM
    Author     : Shahjahan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add New Car</title>

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
        <section id="addnewcar">
            <div class="container">
                <div>
                    <input type="hidden" value="${messageTitle}" name="messageTitle" id="messageTitle"/>
                    <input type="hidden" value="${messageText}" name="messageText" id="messageText"/>
                    <input type="hidden" value="${typeIcon}" name="typeIcon" id="typeIcon"/>
                </div>

                <div class="row">
                    <div class="col-md-12">
                        <a style="float: right; font-weight: bold; text-decoration: none;" href="rentACarDashboard.jsp">&#10006;</a>
                        <h2 class="display-6 text-center ">Add New Car</h2><hr>
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
                        <form id="form" role="form" action="../AddRentACarServlet" method="post" class="form-horizontal" enctype="multipart/form-data">
                            <div class="form-group">
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1"><i class="fa fa-car"></i></span>
                                    </div>
                                    <select class="form-control input req-active" name="brandNameDropDown" id="brandNameDropDown" required onchange="getModel(this.value);">
                                        <option item="" value="">Car Brand Name</option>

                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1"><i class="fa fa-car"></i></span>
                                    </div>
                                    <select class="form-control input req-active" name="ModelNameDropDown" id="ModelNameDropDown" required>
                                        <option item="" value="">Car Model Name</option>

                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1"><i class="fa fa-car"></i></span>
                                    </div>
                                    <select class="form-control input req-active" name="colorDropDown" id="colorDropDown" required>
                                        <option item="" value="">Color Name</option>

                                    </select>
                                </div>
                            </div>


                            <div class="form-group">
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="emailHelp"><i class="fa fa-car"></i></span>
                                    </div>
                                    <input class="form-control input" type="text" value="" name="carRegNo" id="carRegNo" placeholder="Car Registration No" required>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="emailHelp"><i class="fa fa-car"></i></span>
                                    </div>
                                    <input class="form-control input" type="text" value="" name="texTokenNo" id="texTokenNo" placeholder="Tax Token No" required>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="emailHelp"><i class="fa fa-car"></i></span>
                                    </div>
                                    <input class="form-control input" type="text" value="" name="carFitnessNo" id="carFitnessNo" placeholder="Car Fitness Number" required>
                                </div>
                            </div>

                            <div class="form-group text-center">
                                <div class="input-group mb-3 alert alert-success  ">
                                    Choose Car Registration Scan Copy
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group mb-3">

                                    <input name="carRegScanCopy" id="carRegScanCopy" type="file" class="img" required/><br>
                                    <span class="" id="checkPhoto"></span>
                                </div>
                            </div>


                            <div class="form-group">
                                <div class="input-group mb-3 ml-5">
                                    <button type="submit" class="btn btn-success">Add</button>
                                    <button type="reset" class="btn btn-danger">Reset</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
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

            $(window).on("load", function () {
                $.ajax({
                    type: "POST",
                    url: "../AllCarBrandName",
                    success: function (data) {
                        $("#brandNameDropDown").show();
                        $("#brandNameDropDown").append(data);
                    }
                });
                $.ajax({
                    type: "POST",
                    url: "../AllColorName",
                    success: function (data) {
                        $("#colorDropDown").show();
                        $("#colorDropDown").append(data);
                    }
                });
                $.ajax({
                    type: "POST",
                    url: "../AllCarCategoryServlet",
                    success: function (data) {
                        $("#carCategoryTable").show();
                        $("#carCategoryTable").html(data);
                        $('#carCategoryTableId').DataTable({
                            responsive: true,
                            "initComplete": function (settings, json) {
                                AfterLoadTable();
                            }
                        });
                    }
                });
            });

            function getModel(el) {
                var brandNameDropDown = el;
                console.log(brandNameDropDown);
                $.ajax({
                    type: "POST",
                    url: "../AllModelName",
                    data: 'brandNameDropDown=' + brandNameDropDown,
                    success: function (data) {
                        $("#ModelNameDropDown").show();
                        $("#ModelNameDropDown").html(data);
                    }
                });
            }

//            for image load
            var currentTab = 0;
            function previewFile() {
                var preview = document.querySelector('#img');
                var file = document.querySelector('input[type=file]').files[0];
                var reader = new FileReader();

                reader.addEventListener("load", function () {
                    preview.src = reader.result;
                }, false);

                if (file) {
                    reader.readAsDataURL(file);
                }
            }
            $(function () {
                $("#carRegScanCopy").on("change", function () {
                    var files = !!this.files ? this.files : [];
                    if (!files.length || !window.FileReader)
                        return; // no file selected, or no FileReader support

                    if (/^image/.test(files[0].type)) { // only image file
                        var reader = new FileReader(); // instance of the FileReader
                        reader.readAsDataURL(files[0]); // read the local file

                        reader.onloadend = function () { // set image data as background of div
                            $("#imagePreview").css("background-image", "url(" + this.result + ")");
                        }
                    }
                });
                $('#imagePreview').click(function () {
                    $('#carRegScanCopy').trigger('click');

                    console.log('clicked');
                });
            });

            function fixStepIndicator(n) {
                // This function removes the "active" class of all steps...
                var i, x = document.getElementsByClassName("step");
                for (i = 0; i < x.length; i++) {
                    x[i].className = x[i].className.replace(" active", "");
                }
                //... and adds the "active" class on the current step:
                x[n].className += " active";
            }
            function validateForm() {
                // This function deals with validation of the form fields
                var x, y, i, valid = true;
                x = document.getElementsByClassName("tab");
                y = x[currentTab].getElementsByTagName("input");
                // A loop that checks every input field in the current tab:
                for (i = 0; i < y.length; i++) {
                    // If a field is empty...
                    if (y[i].value == "") {
                        // add an "invalid" class to the field:
                        y[i].className += " invalid";
                        // and set the current valid status to false
                        valid = false;
                    }
                }
                // If the valid status is true, mark the step as finished and valid:
                if (valid) {
                    document.getElementsByClassName("step")[currentTab].className += " finish";
                }
                return valid; // return the valid status
            }
            function nextPrev(n) {
                // This function will figure out which tab to display
                var x = document.getElementsByClassName("tab");
                // Exit the function if any field in the current tab is invalid:
                if (n == 1 && !validateForm())
                    return false;
                // Hide the current tab:
                x[currentTab].style.display = "none";
                // Increase or decrease the current tab by 1:
                currentTab = currentTab + n;
                // if you have reached the end of the form...
                if (currentTab >= x.length) {
                    // ... the form gets submitted:
                    document.getElementById("regForm").submit();
                    return false;
                }
                // Otherwise, display the correct tab:
                showTab(currentTab);
            }
        </script>
    </body>
</html>


