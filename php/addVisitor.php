<?php

error_reporting(0);

include 'dbConfig.php';


$conn = mysqli_connect($servername, $username, $password, $dbname);

$VisitorName = $_POST['visitorName'];
$PhoneNumber = $_POST['phoneNumber'];
$CheckInDate = $_POST['checkInDate'];
$CheckOutDate = $_POST['checkOutDate'];
$No_of_Visitors = $_POST['noOfVisitors'];
$VehicleType = $_POST['vehicleType'];
$PlateNumber = $_POST['plateNumber'];
$License_Number = $_POST['licenseNumber'];
$Parking_Number = $_POST['parkingNumber'];
$ApproveParking = $_POST['approveParking'];
  
$Sql_Query = "INSERT INTO visitor(visitorName, phoneNumber,checkInDate,checkOutDate,
noOfVisitors,vehicleType,plateNumber,licenseNumber,parkingNumber,approveParking)
             VALUES ('$VisitorName', '$PhoneNumber', '$CheckInDate' ,'$CheckOutDate' , 
             '$No_of_Visitors','$VehicleType','$PlateNumber','$License_Number','$Parking_Number','$ApproveParking')";
     
if(mysqli_query($conn,$Sql_Query)){
echo 'Data Submit Successfully';
}
else{
echo 'Try Again';
}
mysqli_close($conn);
?>