<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "condoapp";

 
$conn = new mysqli($servername, $username, $password, $dbname);
 

 $response = array();
 if($_POST['visitorName']){
     // if the parameter send from the user id then
     // we will search the item for specific id.
     $VisitorName = $_POST['visitorName'];
        //on below line we are selecting the course detail with below id.
     $stmt = $conn->prepare("SELECT visitorID,visitorName,phoneNumber,checkInDate,checkOutDate,noOfVisitors,vehicleType,plateNumber,licenceNumber FROM visitor WHERE visitorName = ?");
     $stmt->bind_param("s",$VisitorName);
     $result = $stmt->execute();
   // on below line we are checking if our
   // table is having data with specific id.
   if($result == TRUE){
         // if we get the response then we are displaying it below.
         $response['error'] = false;
         $response['message'] = "Retrieval Successful!";
         // on below line we are getting our result.
         $stmt->store_result();
         // on below line we are passing parameters which we want to get.
         $stmt->bind_result($VisitorId,$VisitorName,$PhoneNumber, $CheckInDate ,$CheckOutDate , $No_of_Visitors,$VehicleType,$PlateNumber,$Licence_Number);
         // on below line we are fetching the data.
         $stmt->fetch();
         // after getting all data we are passing this data in our array.
         $response['visitorID'] = $VisitorId;
         $response['visitorName'] = $VisitorName;
         $response['phoneNumber'] = $PhoneNumber;
         $response['checkInDate'] = $CheckInDate;
         $response['checkOutDate'] = $CheckOutDate;
         $response['noOfVisitors'] = $No_of_Visitors;
         $response['vehicleType'] = $VehicleType;
         $response['plateNumber'] = $PlateNumber;
         $response['licenceNumber'] = $Licence_Number;
         
         
     } else{
         // if the id entered by user donot exist then
         // we are displaying the error message
         $response['error'] = true;
         $response['message'] = "Incorrect id";
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