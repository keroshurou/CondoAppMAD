<?php
error_reporting(0);

include 'dbConfig.php' ;

// Create connection
$conn = mysqli_connect($servername,$username,$password,$dbname);

$VisitorName = $_POST["visitorName"];

$sql = "DELETE FROM visitor WHERE visitorName='$VisitorName'";

$result = mysqli_query($conn, $sql);

if($result){
	echo "Data Deleted";
}
else{
	echo "Failed";
}
mysqli_close($conn)
?>