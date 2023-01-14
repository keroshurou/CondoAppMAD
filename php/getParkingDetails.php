<?php
	$hostname = "localhost";
	$username = "root";
	$password = "";
	$database = "condoapp";

	// Connect to the database
	$conn = mysqli_connect('localhost', 'root', '', 'condoapp');
	mysqli_select_db($conn, $database);

	$sql="SELECT parkingNumber FROM `visitor` ";
	$result=mysqli_query($conn,$sql);
	$Visitor=array();

	while($row = mysqli_fetch_assoc($result)){
		
		$index['parkingNumber']=$row['parkingNumber'];
		

		array_push($Visitor, $index);
	}
	echo json_encode($Visitor);

	// Close the connection
	mysqli_close($conn);
?>