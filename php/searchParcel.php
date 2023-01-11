<?php
error_reporting(0);

// Create connection
$conn = mysqli_connect('localhost', 'root', '', 'condoapp');

$result = array();
$result['item'] = array();
$response = mysqli_query($conn, $select);
$sql = "SELECT *  FROM `parcel` WHERE `collectorName` LIKE \'%a%\';";

if($_POST['collectorName']){
	// if the parameter send from the user id then
	// we will search the item for specific name.
	$collectorName = $_POST['collectorName'];
	   //on below line we are selecting the parcel detail with below name.
	$stmt = $conn->prepare("SELECT * FROM parcel WHERE collectorName = ?");
	$stmt->bind_param("s",$collectorName);
	$result = $stmt->execute();
  // on below line we are checking if our
  // table is having data with specific name.
  if($result == TRUE){
		// if we get the response then we are displaying it below.
		$response['error'] = false;
		$response['message'] = "Retrieval Successful!";
		// on below line we are getting our result.
		$stmt->store_result();
		// on below line we are passing parameters which we want to get.
		$stmt->bind_result($parcelID,$collectorName,$parcelUnit,$expressBrand,$trackingNumber,$deliveredDate,$collectStatus,$collectedDate);
		// on below line we are fetching the data.
		$stmt->fetch();
		// after getting all data we are passing this data in our array.
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
		
	} else{
		// if the id entered by user donot exist then
		// we are displaying the error message
		$response['error'] = true;
		$response['message'] = "Empty";
	}
} else{
	 // if the user donot adds any parameter while making request
	 // then we are displaying the error as insufficient parameters.
	 $response['error'] = true;
	 $response['message'] = "Insufficient Parameters";
}
// at last we are printing
// all the data on below line.
echo json_encode($response);

?>