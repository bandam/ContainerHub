<?php

//array for JSON response
$response = array();

$con=mysqli_connect("woltonguesthouse.com.mysql","woltonguesthous","jerry2006*/","woltonguesthous");
// Check connection
if (mysqli_connect_errno()){
	echo "Failed to connect to MySQL: " . mysqli_connect_error();
}

// get all containers from the container table
$result = mysqli_query($con,"SELECT * FROM `container`");

if(!empty($result)){

	if(mysqli_num_rows($result)> 0){
		
		$containers = array();

		while ($row = mysqli_fetch_array($result)) {
			$container  = array();
		//$container["containerID"] = $row["containerID"];
			$container["imageDirectory"] = $row["imageDirectory"];
			$container["location"] = $row["location"];
			$container["destination"] = $row["destination"];
			$container["status"] = $row["status"];
			$container["departureDate"] = $row["departureDate"];
			$container["arrivalDate"] = $row["arrivalDate"];

			array_push($containers, $container);
		}

		

		//user node
		$response["containers"] = array();

		array_push($response["containers"], $containers);

		$response["success"] = 1;

		echo json_encode($response);
	}
	else{
		$response["success"] = 0;
		$response["message"] = "No container found";

		echo json_encode($response, JSON_FORCE_OBJECT);
	}
}

else{
	$response["success"] = 0;
	$response["message"] = "No container found(results array empty)";

	echo json_encode($response);
}

mysql_close($con);
?>