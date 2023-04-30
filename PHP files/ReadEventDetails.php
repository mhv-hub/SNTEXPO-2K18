<?php

$con = mysqli_connect("localhost","id3915214_sat_mno","giet2k18sntexpo","id3915214_sntexpo");


$sql = "select * from event_details;";
$sqll = "select * from event_rules;";

$result = mysqli_query($con,$sql);
$resultt = mysqli_query($con,$sqll);

$response = array();


while($row = mysqli_fetch_array($result)){
    $roww = mysqli_fetch_array($resultt);
    array_push($response,array("event_name"=>$row[0],"type"=>$row[1],"last_date"=>$row[2],"venue"=>$row[3],"charge"=>$row[4],"event_date"=>$row[5],"event_time"=>$row[6],"url"=>$row[7],"rules"=>$roww[1],"fac_cord_all"=>$roww[2],"stud_cord_all"=>$roww[3]));
}

echo json_encode(array("server_response"=>$response));;

mysqli_close($con);


?>