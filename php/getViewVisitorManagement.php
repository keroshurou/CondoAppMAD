<?php
	$hostname = "localhost";
	$username = "root";
	$password = "";
	$database = "condoapp";

	// Connect to the database
	$conn = mysqli_connect('localhost', 'username', 'password');
	mysqli_select_db($conn, $database);

	$sql="SELECT * FROM `visitor` ";
	$result=mysqli_query($conn,$sql);
	$Visitor=array();

	while($row = mysqli_fetch_assoc($result)){
		$index['visitorName']=$row['visitorName'];
		$index['phoneNumber']=$row['phoneNumber'];
		$index['checkInDate']=$row['checkInDate'];
		$index['checkOutDate']=$row['checkOutDate'];
		$index['parkingNumber']=$row['parkingNumber'];
		$index['approveParking'] = $row['approveParking'];

		array_push($Visitor, $index);
	}
	echo json_encode($Visitor);

	// Close the connection
	mysqli_close($conn);
?>