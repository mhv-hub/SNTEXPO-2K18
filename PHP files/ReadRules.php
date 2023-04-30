<?php

$con = mysqli_connect("localhost","id3915214_sat_mno","giet2k18sntexpo","id3915214_sntexpo");

$event_name = $_POST["ev_nm"];

$sql = "select * from event_rules where event_name = '$event_name'";

$result = mysqli_query($con,$sql);

$response = array();


$row = mysqli_fetch_array($result);

array_push($response,array("rules"=>$row[1],"fac_cord"=>$row[2],"stud_cord"=>$row[3]));

echo json_encode(array("server_response"=>$response));

mysqli_close($con);


?>