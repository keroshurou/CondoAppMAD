<?php
error_reporting(0);

// Create connection
$conn = mysqli_connect('localhost', 'root', '', 'condoapp');

$parcelID = $_POST["parcelID"];

$sql = "DELETE FROM parcel WHERE parcelID='$parcelID'";

$result = mysqli_query($conn, $sql);

if($result){
	echo "Data Deleted";
}
else{
	echo "Failed";
}
mysqli_close($conn)
?>