<%-- 
    Document   : navbar
    Created on : Aug 3, 2018, 6:03:50 PM
    Author     : Shahjahan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- Navigation -->
<div class="pos-f-t">
    <nav class="navbar navbar-dark bg-custom-green  fixed-top">
        <button class="navbar-toggler navbar-toggler-right " type="button" data-toggle="collapse" data-target="#navbarToggleExternalContent" aria-controls="navbarToggleExternalContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="logo ml-4">
            <a href="adminDashboard.jsp">
                <img src="<%=request.getContextPath()%>/assets/img/long-logo.png">            
            </a>
            <div class="help">
                <b > Help Line : <a href="tel:09617 111 999" style="color: #fff;">09617 111 999</a></b>
            </div>
        </div>
    </nav>

    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="collapse" id="navbarToggleExternalContent">
            <div class="p-4">
                <ul class="navbar-nav mr-auto flex-lg-row ">
                    <li class="nav-item active">
                        <a class="nav-link" href="adminDashboard.jsp">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Rent A Car
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">

                            <a class="dropdown-item" href="activeRentACar.jsp">Active Rent A Car</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="inactiveRentACar.jsp">Inactive Rent A Car</a>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Car's
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">

                            <a class="dropdown-item" href="activeCar.jsp">Active Car</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="inActiveCar.jsp">Inactive Car</a>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Common User
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">

                            <a class="dropdown-item" href="requestFromCommonUser.jsp">Request From Common User</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="authorizedRequest.jsp">Authorized Request</a>
                        </div>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="roleAssign.jsp">Role Assign<span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="farePanel.jsp">Fare Panel<span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="dwpRegistration.jsp">DWP Registration<span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Data Insert
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="addBikePackage.jsp">Add Biker Package</a>
                            <a class="dropdown-item" href="AddCarPakege.jsp">Add Car Package</a>                       
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="AddColor.jsp">Add Color</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="AddDivision.jsp">Add Division</a>
                            <a class="dropdown-item" href="AddDistrict.jsp">Add District</a>
                        </div>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="../logout.jsp">Log Out<span class="sr-only">(current)</span></a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</div>