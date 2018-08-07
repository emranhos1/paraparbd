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
            
    <div class="collapse" id="navbarToggleExternalContent">
        <div class="bg-dark p-4">
            <ul class="navbar-nav mr-auto flex-lg-row ">
                <li class="nav-item active">
                    <a class="nav-link" href="adminDashboard.jsp">Home <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="allCars.jsp">All Cars</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="userProfile.jsp">Profile</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="../logout.jsp">Log Out</a>
                </li>
            </ul>
        </div>
    </div>
</div>