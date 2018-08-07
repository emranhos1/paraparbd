<%-- 
    Document   : commonUserRegistration
    Created on : Aug 2, 2018, 1:00:36 PM
    Author     : Shahjahan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Common User Registration</title>
        <!--head-->
        <%@include file="head.jsp" %>
    </head>
    <body>
        <!--navbar-->
        <%@include file="navbar.jsp" %>

        <section id="commonuserregistration">
            <div class="container">
                <a style="float: right; font-weight: bold; text-decoration: none;" href="index.jsp">&#10006;</a>
                <!--<div class="cross"> <a href="index.jsp">&#10006;</a></div>-->

                <!--message div-->
                <div>
                    <input type="hidden" value="${messageTitle}" name="messageTitle" id="messageTitle"/>
                    <input type="hidden" value="${messageText}" name="messageText" id="messageText"/>
                    <input type="hidden" value="${typeIcon}" name="typeIcon" id="typeIcon"/>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="info-contact text-center">
                            <h2 class="display-6">Common User Sign Up</h2><hr>
                        </div>
                    </div>

                    <form action="CommonUserRegServlet" class="form-horizontal" method="post" accept-charset="utf-8" enctype="multipart/form-data">
                        <div id="company" modal-body>
                            <div class="form-group">
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="inputGroupFileAddon01"><i class="fa fa-camera"></i>
                                        </span>
                                    </div>
                                    <div class="custom-file">
                                        <!--<input type="file" class="custom-file-input" name="commonUserPhoto" id="commonUserPhoto" aria-describedby="commonUserPhoto">-->
                                        <label class="custom-file-label" for="commonUserPhoto">Choose file</label>
                                        <input name="commonUserPhoto" id="commonUserPhoto" type="file" class="img" required/><br>
                                        <span class="" id="checkPhoto"></span>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="input-group mb-3 ">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1"><i class="fa fa-user"></i></span>
                                    </div>
                                    <input type="text" class="form-control" name="firstName" id="firstName" placeholder="First Name" aria-label="Username" aria-describedby="basic-addon1">
                                    <span class="" id="checkFirstName"></span>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1"><i class="fa fa-user"></i></span>
                                    </div>
                                    <input type="text" class="form-control" name="lastName" id="lastName" placeholder="Last Name" aria-label="Username" aria-describedby="basic-addon1">
                                    <span class="" id="checkLastName"></span>
                                </div>
                            </div>


                            <div class="form-group">
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1"><i class="fa fa-check-square"></i></span>
                                    </div>
                                    <select class="custom-select" name="gender" id="gender" required>
                                        <option value="">Gender</option>
                                        <option value="male">Male</option>
                                        <option value="female">Female</option>
                                        <option value="other">Other</option>
                                    </select>
                                    <span class="" id="checkGender"></span>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="emailHelp"><i class="fa fa-envelope"></i></span>
                                    </div>
                                    <input type="email" class="form-control" aria-describedby="emailHelp" name="email" id="email" placeholder="Email">
                                    <span class="" id="checkEmail"></span>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="input-group mb-3 ">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1"><i class="fa fa-phone"></i></span>
                                    </div>                         
                                    <input class="form-control" maxlength="13" required="required" name="cellNo" id="cellNo" placeholder="Phone Number" size="3" title="" type="tel" value="">
                                    <span class="" id="checkCellNo"></span>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="input-group mb-3 ">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1"><i class="fa fa-map-marker"></i></span>
                                    </div>                            
                                    <textarea class="form-control" aria-label="With textarea" name="address" id="address" placeholder="Address" rows="3"></textarea>
                                    <span class="" id="checkAddress"></span>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="input-group mb-3 ">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1"><i class="fa fa-lock"></i></span>
                                    </div>                            
                                    <input type="password" class="form-control" name="pass" id="pass" placeholder="Password">
                                    <span class="" id="checkPassword"></span>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="input-group mb-3 ">
                                    <div class="card-body text-center">
                                        <button type="button" class="btn btn-success input" id="next_btn">Next</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div id="user">
                            <center>
                                <div class="form-group">
                                    <label class="registrationFormAlert" id="message"></label>
                                    <input name="randomCode" id="randomCode" type="text" class="form-control input" placeholder="5 Digit Code" required>
                                    <span>5 digit code was sent to your mobile please submit that code here</span>
                                </div>
                                <div class="div-center">
                                    <button name="button" type="button"  class="btn btn-large btn-primary" id="pre_btn">Back</button>
                                    <input type="submit" name="submit" value="Register"  class="btn btn-success input" /><br>
                                </div>
                            </center>
                        </div>
                        <center><span>Rent a car owner? <a href="rentACarOwnerRegistration.jsp">Registration</a></span></center>
                    </form>
                </div>
            </div>
        </section>

        <!--footer-->
        <%@include file="footer.jsp" %>

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
                    url: "CheckCellNo",
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

            $("#next_btn").click(function () {
                console.log('next_btn Clicked');
                var firstName = $("#firstName").val();
                var lastName = $("#lastName").val();
                var g = document.getElementById("gender");
                var gender = g.options[g.selectedIndex].value;
                var email = $("#email").val();
                var commonUserPhoto = $("#commonUserPhoto").val();
                var address = $("#address").val();
                var password = $("#pass").val();

                if (commonUserPhoto === '' || commonUserPhoto === null || commonUserPhoto === 'undefined') {
                    $("#checkPhoto").html("<font color='red'>Please Insert Photo</font>");
                    document.getElementById("commonUserPhoto").focus();
                } else if (firstName === '' || firstName === null || firstName === 'undefined') {
                    $("#checkFirstName").html("<font color='red'>Please Insert First Name</font>");
                    document.getElementById("firstName").focus();
                } else if (lastName === '' || lastName === null || lastName === 'undefined') {
                    $("#checkLastName").html("<font color='red'>Please Insert Last Name</font>");
                    document.getElementById("lastName").focus();
                } else if (gender === '' || gender === null || gender === 'undefined') {
                    $("#checkGender").html("<font color='red'>Please Select Gender</font>");
                    document.getElementById("gender").focus();
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
                } else if (address === '' || address === null || address === 'undefined') {
                    $("#checkAddress").html("<font color='red'>Please Insert Address</font>");
                    document.getElementById("address").focus();
                } else if (password === '' || password === null || password === 'undefined') {
                    $("#checkPassword").html("<font color='red'>Please Insert Password</font>");
                    document.getElementById("pass").focus();
                } else {
                    var messageTitle = document.getElementById('messageTitle').value = "SMS Send";
                    var messageText = document.getElementById('messageText').value = "1 SMS SEND TO YOUR MOBILE";
                    var typeIcon = document.getElementById('typeIcon').value = "success";
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
                    $("#company").slideUp();
                    $("#user").show();
                    $.ajax({
                        type: "POST",
                        url: "SendSmsServlet",
                        data: 'cellNo=' + cellNo1,
                        success: function (data) {
                            $.each(data, function (index, element) {
                                var message = element.message;
                                $(".modal-body #message").val(message);
                            });
                        }
                    });
                }
            });

            $("#pre_btn").click(function () {
                $("#user").hide();
                $("#company").slideDown();
            });
            var currentTab = 0;

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

            function fixStepIndicator(n) {
                // This function removes the "active" class of all steps...
                var i, x = document.getElementsByClassName("step");
                for (i = 0; i < x.length; i++) {
                    x[i].className = x[i].className.replace(" active", "");
                }
                //... and adds the "active" class on the current step:
                x[n].className += " active";
            }

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

            $("#nextBtn").click(function () {
                console.log('next_btn Clicked');

                var commonUserPhoto = $("#commonUserPhoto").val();


                if (commonUserPhoto === '' || commonUserPhoto === null || commonUserPhoto === 'undefined') {
                    $("#checkPhoto").html("<font color='red'>Please Insert Photo</font>");
                    document.getElementById("commonUserPhoto").focus();
                }
            });

            $(function () {
                $("#commonUserPhoto").on("change", function () {
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
                    $('#commonUserPhoto').trigger('click');
                });
            });
        </script>
    </body>
</html>
