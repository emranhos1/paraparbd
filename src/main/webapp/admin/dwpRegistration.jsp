<%-- 
    Document   : rentACarOwnerRegistration
    Created on : Aug 3, 2018, 11:20:09 AM
    Author     : Shahjahan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DWP owner Registration</title>
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

        <section id="dwpownerregistration">
            <div class="container">
                <!--message div-->
                <div>
                    <input type="hidden" value="${messageTitle}" name="messageTitle" id="messageTitle"/>
                    <input type="hidden" value="${messageText}" name="messageText" id="messageText"/>
                    <input type="hidden" value="${typeIcon}" name="typeIcon" id="typeIcon"/>
                </div>

                <div class="row">
                    <div class="col-lg-12">
                        <div class="info-contact text-center">
                            <a style="float: right; font-weight: bold; text-decoration: none;" href="adminDashboard.jsp">&#10006;</a>
                            <h2 class="display-6">DWP owner Registration</h2><hr>
                        </div>
                    </div>

                    <form id="form" role="form" action="../DWPRegServlet" method="post" class="form-horizontal"  enctype="multipart/form-data">
                        <div id="company" modal-body>
                            <div class="form-group">
                                <div class="input-group mb-3 col-12  input-group-sm input-group-lg ">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="inputGroupFileAddon01"><i class="fa fa-camera"></i>
                                        </span>
                                    </div>
                                    <div class="custom-file">
                                        <input name="ownerPhoto" id="ownerPhoto" type="file" name="image" class="img" />
                                        <span class="" id="checkPhoto"></span>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="input-group mb-3  col-12 input-group-sm input-group-lg ">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1"><i class="fa fa-user"></i></span>
                                    </div>
                                    <input class="form-control input" type="text" value="" name="firstName" id="firstName" placeholder="First Name" placeholder="" required>
                                    <span class="" id="checkFirstName"></span>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="input-group mb-3  col-12 input-group-sm input-group-lg">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1"><i class="fa fa-user"></i></span>
                                    </div>
                                    <input class="form-control input" type="text" value="" name="lastName" id="lastName" placeholder="Last Name" required>
                                    <span class="" id="checkLastName"></span>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="input-group mb-3  col-12 input-group-sm input-group-lg">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1"><i class="fa fa-check-square"></i></span>
                                    </div>
                                    <select class="form-control input" name="gender" id="gender" placeholder="" required>
                                        <option value="">Gender</option>
                                        <option value="male">Male</option>
                                        <option value="female">Female</option>
                                        <option value="other">Other</option>
                                    </select>
                                    <span class="" id="checkGender"></span>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="input-group mb-3  col-12 input-group-sm input-group-lg">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="emailHelp"><i class="fa fa-envelope"></i></span>
                                    </div>
                                    <input class="form-control input" type="email" value="" name="email" id="email" placeholder="Email" required>
                                    <span class="" id="checkEmail"></span>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="input-group mb-3  col-12 input-group-sm input-group-lg ">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1"><i class="fa fa-phone"></i></span>
                                    </div>                            
                                    <input class="form-control input" type="text" value="" name="cellNo" id="cellNo" placeholder="Phone Number" required>
                                    <span class="" id="checkCellNo"></span>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="input-group mb-3  col-12 input-group-sm input-group-lg">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1"><i class="fa fa-check-square"></i></span>
                                    </div>
                                    <select class="form-control input req-active" name="divisionNameDropDown" id="divisionNameDropDown" required onchange="getDistict(this.value);">
                                        <option item="" value="">Division Name</option>
                                    </select>
                                    <span class="" id="checkDivisionNameDropDown"></span>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="input-group mb-3  col-12 input-group-sm input-group-lg">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1"><i class="fa fa-check-square"></i></span>
                                    </div>
                                    <select class="form-control input req-active" name="distictNameDropDown" id="distictNameDropDown" placeholder="" required onchange="getPoliceStation(this.value)">
                                        <option value="">District Name</option>
                                    </select>
                                    <span class="" id="checkDistictNameDropDown"></span>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="input-group mb-3 col-12 input-group-sm input-group-lg">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1"><i class="fa fa-check-square"></i></span>
                                    </div>
                                    <select class="form-control input req-active" name="policeNameDropDown" id="policeNameDropDown" placeholder="" required>
                                        <option value="">Police Station Name</option>
                                    </select>
                                    <span class="" id="checkPoliceNameDropDown"></span>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="input-group mb-3  col-12 input-group-sm input-group-lg ">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1"><i class="fa fa-map-marker"></i></span>
                                    </div>                            
                                    <textarea class="form-control input" type="text" value="" name="address" id="address" placeholder="Address" required rows="3"></textarea>
                                    <span class="" id="checkAddress"></span>   
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="input-group mb-3  col-12 input-group-sm input-group-lg ">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1"><i class="fa fa-map-marker"></i></span>
                                    </div>                            
                                    <input class="form-control input" type="text" value="" name="zipCode" id="zipCode" placeholder="Zip Code" required/>
                                    <span class="" id="checkZipCode"></span>
                                </div>
                            </div>

                            <div class="card-body text-center">
                                <button type="button" class="btn btn-success input" id="next_btn">Next</button>
                            </div>

                        </div>



                        <div id="user">
                            <div class="form-group">
                                <div class="input-group mb-3  col-12 input-group-sm input-group-lg ">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1"><i class="fa fa-user"></i></span>
                                    </div>                            
                                    <input class="form-control input" type="text" value="" name="nidNo" id="nidNo" placeholder="NID No" required/>
                                    <span class="" id="checkNidNo"></span>
                                </div>
                            </div>

                            <div class="input-group mb-3  col-12 input-group-sm input-group-lg ">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="inputGroupFileAddon01"><i class="fa fa-camera"></i>

                                </div>
                                <div class="custom-file">
                                    <label class="label">Choose NID Photo</label>
                                    <input style="border-top-left-radius: 0px; border-top-right-radius: 0px;" class="form-control input" type="file" accept="image/*" value="" name="nidPhoto" id="nidPhoto" title = "Choose Owner Photo" required/>
                                    <span class="" id="checkNidPhoto"></span>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="input-group mb-3  col-12 input-group-sm input-group-lg ">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1"><i class="fa fa-user"></i></span>
                                    </div>                            
                                    <input class="form-control input" type="text" value="" name="tinNo" id="tinNo" placeholder="TIN No" required/>
                                    <span class="" id="checkTinNo"></span>
                                </div>
                            </div>

                            <div class="input-group mb-3  col-12 input-group-sm input-group-lg ">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="inputGroupFileAddon01"><i class="fa fa-camera"></i>

                                </div>
                                <div class="custom-file">                                
                                    <label class="label">Choose Tin Photo</label>
                                    <input style="border-top-left-radius: 0px; border-top-right-radius: 0px;" class="form-control input" type="file" accept="image/*" value="" name="tinPhoto" id="tinPhoto" title = "Choose Owner Photo" required/>
                                    <span class="" id="checkTinPhoto"></span>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="input-group mb-3  col-12 input-group-sm input-group-lg ">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1"><i class="fa fa-user"></i></span>
                                    </div>                            
                                    <input class="form-control input" type="text" value="" name="tradeLicenseNo" id="tradeLicenseNo" placeholder="Trade License  No" required/>
                                    <span class="" id="checkTradeLicenseNo"></span>
                                </div>
                            </div>

                            <div class="input-group mb-3  col-12 input-group-sm input-group-lg ">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="inputGroupFileAddon01"><i class="fa fa-camera"></i>
                                </div>
                                <div class="custom-file">                                
                                    <label class="label">Choose Trade License Photo</label>
                                    <input style="border-top-left-radius: 0px; border-top-right-radius: 0px;" class="form-control input" type="file" accept="image/*" value="" name="tradeLicensePhoto" id="tradeLicensePhoto" title = "Choose Owner Photo" required/>
                                    <span class="" id="checkTradeLicensePhoto"></span>
                                </div>
                            </div>

                            <div class="form-group col-12 input-group-sm input-group-lg">
                                <div class="input-group mb-3 ">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1"><i class="fa fa-lock"></i></span>
                                    </div>                            
                                    <input class="form-control input" type="password" value="" name="pass" id="pass" placeholder="Password" required/>
                                    <span class="" id="checkPass"></span>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="input-group mb-3 ml-5">

                                    <button name="pre_btn" type="button"  class="btn btn-large btn-primary" id="pre_btn">Back</button>
                                    <button type="submit" class="btn btn-success" id="reg_btn">Registration</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>


        </section>
        <!--footer-->
        <%@include file="footer.jsp" %>
        <%}%>
        <script type="text/javascript">

//            only For message show
            $(document).ready(function () {
                console.log('document ready');
                $("#user").hide();
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

            var cellNo;
            var cellNo1;
            $('#cellNo').keyup(function () {
                cellNo1 = $(this).val();
                $.ajax({
                    type: "POST",
                    url: "../CheckCellNo",
                    data: 'cellNo=' + cellNo1,
                    success: function (data) {
                        console.log('ajax called');
                        $.each(data, function (index, element) {

                            cellNo = element.cellNo;
                            console.log('cell from db :' + cellNo);
                            if (cellNo1 === '' || cellNo1 === null || cellNo1 === 'undefined') {
                                $("#checkCellNo").html("<font color='red'>Give Phone No</font>");
                                document.getElementById("cellNo").focus();
                            } else if (cellNo1 === cellNo) {
                                $("#checkCellNo").html("<font color='red'>This Phone Already Used <br> Please Try Another</font>");
                            } else if (cellNo === '' || cellNo === null || cellNo === 'undefined') {
                                $("#checkCellNo").html("<font color='red'>Phone No is ok</font>");
                            }
                        });
                    }
                });
            });

            $(window).on("load", function () {
                $.ajax({
                    type: "POST",
                    url: "../AllDivisionName",
                    success: function (data) {
                        $("#divisionNameDropDown").show();
                        $("#divisionNameDropDown").append(data);
                    }
                });
            });

            function getDistict(el) {
                var divisionNameDropDown = el;
//                console.log(divisionNameDropDown);
                $.ajax({
                    type: "POST",
                    url: "../AllDistictName",
                    data: 'divisionNameDropDown=' + divisionNameDropDown,
                    success: function (data) {
                        $("#distictNameDropDown").show();
                        $("#distictNameDropDown").html(data);
                    }
                });
            }

            function getPoliceStation(el) {
                var districtNameDropDown = el;
                console.log(districtNameDropDown);
                $.ajax({
                    type: "POST",
                    url: "../AllPoliceStationNameForRegestration",
                    data: 'districtNameDropDown=' + districtNameDropDown,
                    success: function (data) {
                        $("#policeNameDropDown").show();
                        $("#policeNameDropDown").html(data);
                    }
                });
            }

            $("#next_btn").click(function () {
                console.log('next_btn Clicked');
                var ownerPhoto = $("#ownerPhoto").val();
                var firstName = $("#firstName").val();
                var lastName = $("#lastName").val();
                var email = $("#email").val();
                var gend = document.getElementById("gender");
                var gender = gend.options[gend.selectedIndex].value;
                var divi = document.getElementById("divisionNameDropDown");
                var divisionNameDropDown = divi.options[divi.selectedIndex].value;
                var dist = document.getElementById("distictNameDropDown");
                var distictNameDropDown = dist.options[dist.selectedIndex].value;
                var pst = document.getElementById("policeNameDropDown");
                var policeNameDropDown = pst.options[pst.selectedIndex].value;
                var address = $("#address").val();
                var zipCode = $("#zipCode").val();
                console.log(divisionNameDropDown);
                console.log(distictNameDropDown);
                console.log(policeNameDropDown);
                if (ownerPhoto === '' || ownerPhoto === null || ownerPhoto === 'undefined') {
                    $("#checkPhoto").html("<font color='red'>Please Insert Photo</font>");
                    document.getElementById("ownerPhoto").focus();
                } else if (firstName === '' || firstName === null || firstName === 'undefined') {
                    $("#checkFirstName").html("<font color='red'>Please Insert First Name</font>");
                    document.getElementById("firstName").focus();
                } else if (lastName === '' || lastName === null || lastName === 'undefined') {
                    $("#checkLastName").html("<font color='red'>Please Insert Last Name</font>");
                    document.getElementById("lastName").focus();
                } else if (email === '' || email === null || email === 'undefined') {
                    $("#checkEmail").html("<font color='red'>Please Insert Email</font>");
                    document.getElementById("email").focus();
                } else if (cellNo1 === '' || cellNo1 === null || cellNo1 === 'undefined') {
                    $("#checkCellNo").html("<font color='red'>Please Insert Phone No</font>");
                    document.getElementById("cellNo").focus();
                } else if (cellNo1 === cellNo) {
                    console.log('front :' + cellNo1);
                    console.log('db :' + cellNo);
                    document.getElementById("cellNo").value = "";
                    $("#checkCellNo").html("<font color='red'>Please Insert Phone No</font>");
                    document.getElementById("cellNo").focus();
                } else if (gender === '' || gender === null || gender === 'undefined') {
                    $("#checkGender").html("<font color='red'>Please Select Gender</font>");
                    document.getElementById("gender").focus();
                } else if (divisionNameDropDown === '' || divisionNameDropDown === null || divisionNameDropDown === 'undefined') {
                    $("#checkDivisionNameDropDown").html("<font color='red'>Please Select Division</font>");
                    document.getElementById("divisionNameDropDown").focus();
                } else if (distictNameDropDown === '' || distictNameDropDown === null || distictNameDropDown === 'undefined') {
                    $("#checkDistictNameDropDown").html("<font color='red'>Please Select Division</font>");
                    document.getElementById("distictNameDropDown").focus();
                } else if (policeNameDropDown === '' || policeNameDropDown === null || policeNameDropDown === 'undefined') {
                    $("#checkPoliceNameDropDown").html("<font color='red'>Please Select Police Station</font>");
                    document.getElementById("policeNameDropDown").focus();
                } else if (address === '' || address === null || address === 'undefined') {
                    $("#checkAddress").html("<font color='red'>Please Insert Address</font>");
                    document.getElementById("address").focus();
                } else if (zipCode === '' || zipCode === null || zipCode === 'undefined') {
                    $("#checkZipCode").html("<font color='red'>Please Insert Zip Code</font>");
                    document.getElementById("zipCode").focus();
                } else {
                    $("#company").slideUp();
                    $("#user").show();
                }
            });

            $("#pre_btn").click(function () {
                $("#user").hide();
                $("#company").slideDown();
            });

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
                $("#ownerPhoto").on("change", function () {
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
                    $('#ownerPhoto').trigger('click');

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
