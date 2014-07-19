<?php

$base=$_REQUEST['image'];
$container_location = $_REQUEST['container_location'];
$container_destination = $_REQUEST['container_destination'];
$container_progress = $_REQUEST['container_progress'];
$container_departureDate = $_REQUEST['container_departureDate'];
$container_arrivalDate = $_REQUEST['container_arrivalDate'];
$container_ownerName = $_REQUEST['container_ownerName'];
$container_ownerPhone = $_REQUEST['container_ownerPhone'];
$container_ownerEmail = $_REQUEST['container_ownerEmail'];

$timeStamp = time();

$con=mysqli_connect("woltonguesthouse.com.mysql","woltonguesthous","jerry2006*/","woltonguesthous");
// Check connection
if (mysqli_connect_errno()){
    echo "Failed to connect to MySQL: " . mysqli_connect_error();
}



$results = mysqli_query($con, "INSERT INTO `woltonguesthous`.`container` (`containerID`, `imageDirectory`, `location`, `destination`, `status`, `departureDate`, `arrivalDate`) VALUES (NULL, '"
    .$timeStamp."', '".$container_location."', '".$container_destination."', '".$container_progress."', '".$container_departureDate."', '".$container_arrivalDate."');");


    //$results2 = mysqli_query($con, "");
if(!empty($results)){
   echo "post was successfull";
}
else{
   echo "something went wrong with post";
}

mysqli_close($con);




$binary=base64_decode($base);
header('Content-Type: bitmap; charset=utf-8');
$file = fopen($timeStamp.".jpg", 'wb');
fwrite($file, $binary);
fclose($file);

?>