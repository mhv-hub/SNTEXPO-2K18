<?php

$con = mysqli_connect("localhost","id3915214_sat_mno","giet2k18sntexpo","id3915214_sntexpo");


$sql = "select * from Notices";

$result = mysqli_query($con,$sql);

$response = array();


while($row = mysqli_fetch_array($result)){

    array_push($response,array("msg"=>$row[1]));
}

echo json_encode(array("server_response"=>$response));;

mysqli_close($con);


?>