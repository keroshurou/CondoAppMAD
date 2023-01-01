<?php
header('Content-Type: application/json');
$data = array();
$con = mysqli_connect('localhost', 'root', '', 'condoapp');

if($con) {
  $sql = "select * from parcel";
  $result = mysqli_query($con, $sql);
  if(mysqli_num_rows($result) != 0) {
    $i = 0;
    while ($row = mysqli_fetch_assoc($result)){
      $data[$i] = $row;
      $i++;
    }
    echo json_encode($data, JSON_PRETTY_PRINT);
  }
}
else echo "Failed to connect to database";
?>
