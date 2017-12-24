

<script src="C:/Users/Meso/Documents/SoftwareProject2/Website/EmployeeWebApp/o.min.js">

var oHandler = o('http://services.odata.org/V4/(S(ykcab0l02qbs3euppy5louci))/TripPinServiceRW/People');
oHandler.get(function(data) {
    console.log(data); // data of the TripPinService/People endpoint
});

</script>