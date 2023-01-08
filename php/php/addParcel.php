<?php

error_reporting(0);

// Create connection
$conn = mysqli_connect('localhost', 'root', '', 'condoapp');

// an array to display response
$response = array();

// on below line we are checking if the body provided by user contains
// this keys as course name,course description and course duration
if($_POST['collectorName'] && $_POST['parcelUnit'] && $_POST['expressBrand']
&& $_POST['trackingNumber'] && $_POST['deliveredDate']){
	// if above seven parameters are present then we are extracting values
	// from it and storing it in new variables.
	$collectorName = $_POST['collectorName'];
	$parcelUnit = $_POST['parcelUnit'];
	$expressBrand = $_POST['expressBrand'];
	$trackingNumber = $_POST['trackingNumber'];
	$deliveredDate = $_POST['deliveredDate'];
	$collectStatus = $_POST['collectStatus'];
	$collectedDate = $_POST['collectedDate'];

	// after that we are writing an sql query to
	// add this data to our database.
	// on below line make sure to add your table name
	// in previous article we have created our table name
	// as courseDb and add all column headers to it except our id.
	$stmt = $conn->prepare("INSERT INTO `parcel`(`collectorName`, `parcelUnit`,
		`expressBrand`, `trackingNumber`, `deliveredDate`, `collectStatus`,
		`collectedDate`) VALUES (?,?,?,?,?,?,?,?)");
	$stmt->bind_param("sssssss",$collectorName,$parcelUnit,$expressBrand,
	$trackingNumber,$deliveredDate,$collectStatus,$collectedDate);

// on below line we are checking if our sql query is executed successfully.
if($stmt->execute() == TRUE){
		// if the script is executed successfully we are
		// passing data to our response object
		// with a success message.
		$response['error'] = false;
		$response['message'] = "parcel created successfully!";
	} else{
		// if we get any error we are passing error to our object.
		$response['error'] = true;
		$response['message'] = "failed\n ".$conn->error;
	}
} else{
	// this method is called when user
	// do not enter sufficient parameters.
	$response['error'] = true;
	$response['message'] = "Insufficient parameters";
}
// at last we are printing our response which we get.
echo json_encode($response);
?>
