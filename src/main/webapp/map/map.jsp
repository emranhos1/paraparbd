<%-- 
    Document   : map
    Created on : May 23, 2018, 11:13:42 AM
    Author     : PLAYBOY
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" >

    <head>
        <meta charset="UTF-8">
        <title>Google Map</title>

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <div id="panel">
            <b>From:&nbsp;</b>
            <input type="text" id="start" value="" placeholder="From"/>
            <b>To:&nbsp;</b>
            <input type="text" id="end" value="" placeholder="To"/>
            <input type="button" id="submit" value="Go" />
            <div id="route" class="panel" style="padding: 5px;"></div>
        </div>
        <div id="map-canvas">
        </div>
        <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
        <script src='https://maps.googleapis.com/maps/api/js?key=AIzaSyAd1xMYT1bt99qtFWQEzXiRBvORDWHgPtk&libraries=places'></script>
        <script  src="js/index.js"></script>
    </body>
</html>
<!--        <script async defer
                src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDv5UFvcp8qzQsjP7DIfPlxCZr_c6tyHjQ&callback=initMap">
        </script>-->