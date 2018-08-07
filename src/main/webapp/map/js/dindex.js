$(function () {

    function calculateDistance(origin, destination) {
//        console.log('calculateDistance');
        var service = new google.maps.DistanceMatrixService();
        service.getDistanceMatrix(
                {
                    origins: [origin],
                    destinations: [destination],
                    travelMode: google.maps.TravelMode.DRIVING,
                    unitSystem: google.maps.UnitSystem.IMPERIAL,
                    avoidHighways: false,
                    avoidTolls: false
                }, callback);
//                console.log(origin);
    }

    function callback(response, status) {
        if (status != google.maps.DistanceMatrixStatus.OK) {
            $('#result').html(err);
        } else {
            var origin = response.originAddresses[0];
            var destination = response.destinationAddresses[0];
            if (response.rows[0].elements[0].status === "ZERO_RESULTS") {
                $('#result').html("Better get on a plane. There are no roads between "
                        + origin + " and " + destination);
            } else {
                var distance = response.rows[0].elements[0].distance;
                var distance_value = distance.value;
                var distance_text = distance.text;
                var miles = distance_text.substring(0, distance_text.length - 3);

                $.ajax({
                    type: "POST",
                    url: "../DistanceServlet",
                    data: 'miles=' + miles,
                    success: function (data) {
                        console.log('ajax called');
                        $.each(data, function (index, element) {
                            var totalTake = element.totalTake;
                            $(".result #totalTake").val('');
                            $(".result #totalTake").val(totalTake);

                        });
                    }
                });
            }
        }
    }

//    $('#distance_form').submit(function (e) {
//        event.preventDefault();
//        var origin = $('#origin').val();
//        var destination = $('#destination').val();
//        var distance_text = calculateDistance(origin, destination);
//    });


//    $("#policeNameDropDownDestination").onchange = (function () {
    document.getElementById('policeNameDropDownDestination').onchange = (function () {

        event.preventDefault();
        var divi = $("#divisionNameDropDownOrigin option:selected").text();
        var dist = $("#distictNameDropDownOrigin option:selected").text();
        var pst = $("#policeNameDropDownOrigin option:selected").text();
        var diviD = $("#divisionNameDropDownDestination option:selected").text();
        var distD = $("#distictNameDropDownDestination option:selected").text();
        var pstD = $("#policeNameDropDownDestination option:selected").text();
        
        
        var origin = pst+', ' + dist+ ', '+divi;
        var destination =  pstD+', ' + distD+ ', '+diviD;
//        var origin = document.getElementById('policeNameDropDownOrigin').value;
//        var destination = document.getElementById('policeNameDropDownDestination').value;
        
        
        
        
//        console.log(origin1);
//        console.log(destination1);
//        var origin = origin1.trim();
//        var destination = destination1.trim();
//        var origin = $('#policeNameDropDownOrigin').val();
//        var destination = $('#policeNameDropDownDestination').val();
        if (origin === '' || origin === null || origin === 'undefined') {
            alert('Can\'t Find origin Thana');
        } else {
            if (destination === '' || destination === null || destination === 'undefined') {
                alert('Can\'t Find destination Thana');
            } else {
                console.log(origin);
                console.log(destination);
                var distance_text = calculateDistance(origin, destination);
            }
        }
    });
});
//==============best for value = 'dhaka'=================