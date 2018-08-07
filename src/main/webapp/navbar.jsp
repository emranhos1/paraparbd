<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- Navigation -->
<div class="pos-f-t">
    <nav class="navbar navbar-dark bg-custom-green  fixed-top">
        <button class="navbar-toggler navbar-toggler-right " type="button" data-toggle="collapse" data-target="#navbarToggleExternalContent" aria-controls="navbarToggleExternalContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="logo ml-4">
            <a href="index.jsp">
                <img src="<%=request.getContextPath()%>/assets/img/long-logo.png">            
            </a>
            <div class="help">
                <b > Help Line : <a href="tel:09617 111 999" style="color: #fff;">09617 111 999</a></b>
            </div>
        </div>

        <form role="form" role="form" action="LoginServlet" method="post" class="form-inline my-0 mr-sm-0">
            <div class="input-group">
                <input class="form-control mr-sm-1 my-2" name="phoneNo" id="phoneNo" type="text" placeholder="Phone Number" required="true">
                <input class="form-control mr-sm-1 my-2" name="password" id="password" type="password" placeholder="Password" required="true">

                <button class="btn btn-outline-success my-2 mr-sm-1" type="submit">Login</button>

            </div>

            <div class="fpass"> <a style="color: white;" href="<%=request.getContextPath()%>/forgetPassword.jsp">&nbsp;Forget Password</a></div>

        </form>
    </nav>
    <div class="collapse" id="navbarToggleExternalContent">
        <div class="bg-dark p-4">
            <ul class="navbar-nav mr-auto flex-lg-row ">
                <li class="nav-item active">
                    <a class="nav-link" href="index.jsp">Home <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="aboutUs.jsp">About Us</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="contactUs.jsp">Contact Us</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="termsAndCondition.jsp">Terms & Condition</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="commonUserRegistration.jsp">Sign Up</a>
                </li>
            </ul>
        </div>
    </div>
</div>