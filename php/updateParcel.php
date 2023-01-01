<?php
error_reporting(0);

// Create connection
$conn = mysqli_connect('localhost', 'root', '', 'condoapp');

$parcelID = $_POST['parcelID'];
$collectorName = $_POST['collectorName'];
$parcelUnit = $_POST['parcelUnit'];
$expressBrand = $_POST['expressBrand'];
$trackingNumber = $_POST['trackingNumber'];
$deliveredDate = $_POST['deliveredDate'];
$collectStatus = $_POST['collectStatus'];
$collectedDate = $_POST['collectedDate'];

$sql = "UPDATE parcel SET collectorName = '$collectorName',parcelUnit = '$parcelUnit',
parcelUnit = '$parcelUnit',expressBrand = '$expressBrand',trackingNumber = '$trackingNumber',
deliveredDate = '$deliveredDate',collectStatus = '$collectStatus',collectedDate = '$collectedDate' WHERE parcelID = '$parcelID'";

$result = mysqli_query($conn, $sql);

if($result){
	echo "Data Updated";
}
else{
	echo "Failed";
}
mysqli_close($conn)
?>