<?php
error_reporting(0);

// Create connection
$conn = mysqli_connect('localhost', 'root', '', 'condoapp');

$result = array();
$result['item'] = array();
$select = "SELECT `parcelID`,`collectorName`, `parcelUnit`, `expressBrand`, `trackingNumber`, `deliveredDate`, `collectStatus`, `collectedDate` from parcel";
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

/*if(mysqli_query($conn,$Sql_Query)){
	echo 'Data Submit Successfully';
	}
	else{
		echo 'Try Again';
		}
mysqli_close($con);
*/



?>
