<?php
error_reporting(0);

// Create connection
$conn = mysqli_connect('localhost', 'root', '', 'condoapp');

$result = array();
$result['item'] = array();
$select = "SELECT * FROM `parcel` WHERE `collectStatus` LIKE 'Available'";
$select2 = "SELECT * FROM `parcel` WHERE `parcelUnit` LIKE '18' AND `collectStatus` LIKE 'Available'";
$response = mysqli_query($conn, $select);

while ($row = mysqli_fetch_array($response)){
	
	$index['parcelID'] = $row['0'];
	$index['collectorName'] = $row['1'];
	$index['parcelUnit'] = $row['2'];
	$index['expressBrand'] = $row['3'];
  	$index['trackingNumber'] = $row['4'];
  	$index['deliveredDate'] = $row['5'];
  	$index['collectStatus'] = $row['6'];
  	$index['collectedDate'] = $row['7'];
	
	array_push($result['item'], $index);
}

	$result["success"] = "1";
	echo json_encode($result, JSON_PRETTY_PRINT);
	mysql_close($conn);

?>