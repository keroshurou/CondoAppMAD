<?php

error_reporting(0);

// Create connection
$conn = mysqli_connect('localhost', 'root', '', 'condoapp');

// an array to display response
$response = array();

// on below line we are checking if the body provided by user contains
// this keys as course name,course description and course duration
if($_POST['visitorName'] && $_POST['phoneNumber']){
	// if above seven parameters are present then we are extracting values
	// from it and storing it in new variables.
	$VisitorName = $_POST['visitorName'];
    $PhoneNumber = $_POST['phoneNumber'];
    $CheckInDate = $_POST['checkInDate'];
    $CheckOutDate = $_POST['checkOutDate'];
    $No_of_Visitors = $_POST['noOfVisitors'];
    $VehicleType = $_POST['vehicleType'];
    $PlateNumber = $_POST['plateNumber'];
    $Licence_Number = $_POST['licenceNumber'];
    $Parking_Number = $_POST['parkingNumber'];
    $ApproveParking = $_POST['approveParking'];

	// after that we are writing an sql query to
	// add this data to our database.
	// on below line make sure to add your table name
	// in previous article we have created our table name
	// as courseDb and add all column headers to it except our id.
	$stmt = $conn->prepare("INSERT INTO visitor(visitorName, phoneNumber,checkInDate,checkOutDate,noOfVisitors,
    vehicleType,plateNumber,licenceNumber,parkingNumber,approveParking) VALUES (?,?,?,?,?,?,?,?,?,?)");
	$stmt->bind_param("sssssssss",$VisitorName,$PhoneNumber,$CheckInDate,$CheckOutDate,$No_of_Visitors,$VehicleType,
    $PlateNumber,$Licence_Number,$Parking_Number,$ApproveParking);

// on below line we are checking if our sql query is executed successfully.
if($stmt->execute() == TRUE){
		// if the script is executed successfully we are
		// passing data to our response object
		// with a success message.
		$response['error'] = false;
		$response['message'] = "Visitor created successfully!";
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
