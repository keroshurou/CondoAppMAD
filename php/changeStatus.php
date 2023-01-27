<?php
error_reporting(0);

// Create connection
$conn = mysqli_connect('localhost', 'root', '', 'condoapp');

$parcelID = $_POST['parcelID'];
$collectStatus = $_POST['collectStatus'];
$collectedDate = $_POST['collectedDate'];


$sql = "UPDATE parcel SET collectStatus='$collectStatus',collectedDate='$collectedDate'
        WHERE parcel.parcelID= '$parcelID'";

$result = mysqli_query($conn, $sql);

if($result){
	echo "Data Updated";
}
else{
	echo "Failed";
}
mysqli_close($conn)
?>