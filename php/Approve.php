<?php
error_reporting(0);

include 'dbConfig.php' ;

// Create connection
$conn = mysqli_connect($servername,$username,$password,$dbname);


$VisitorName = $_POST['visitorName'];
$Parking_Number = $_POST['parkingNumber'];
$ApproveParking = $_POST['approveParking'];


$sql = "UPDATE visitor SET parkingNumber='$Parking_Number',approveParking='$ApproveParking'
        WHERE visitorName= '$VisitorName'";

$result = mysqli_query($conn, $sql);

if($result){
	echo "Data Updated";
}
else{
	echo "Failed";
}
mysqli_close($conn)
?>