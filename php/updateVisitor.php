<?php
error_reporting(0);

include 'dbConfig.php' ;

// Create connection
$conn = mysqli_connect($servername,$username,$password,$dbname);

$VisitorID= $_POST['visitorID'];
$VisitorName = $_POST['visitorName'];
$PhoneNumber = $_POST['phoneNumber'];
$CheckInDate = $_POST['checkInDate'];
$CheckOutDate = $_POST['checkOutDate'];
$No_of_Visitors = $_POST['noOfVisitors'];
$VehicleType = $_POST['vehicleType'];
$PlateNumber = $_POST['plateNumber'];
$Licence_Number = $_POST['licenceNumber'];
$Parking_Number = $_POST['parkingNumber'];
$ApproveParking = $_POST['parkingNumber'];


$sql = "UPDATE visitor SET visitorName = '$VisitorName',phoneNumber = '$PhoneNumber',checkInDate = '$CheckInDate',checkOutDate= '$CheckOutDate',
        noOfVisitors='$No_of_Visitors',vehicleType='$VehicleType',plateNumber='$PlateNumber',licenceNumber='$Licence_Number',parkingNumber='$Parking_Number',approveParking='$ApproveParking'
        WHERE visitorID= '$VisitorID'";

$result = mysqli_query($conn, $sql);

if($result){
	echo "Data Updated";
}
else{
	echo "Failed";
}
mysqli_close($conn)
?>