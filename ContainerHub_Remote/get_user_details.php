<?php

/*
 * Following code will get single user details
 * A user is identified by user id (uid)
 */

// array for JSON response
$response = array();


// include db connect class
require_once __DIR__ . '/db_connect.php';

// connecting to db
$db = new DB_CONNECT();

// check for post data
if (isset($_GET["email"])) {
    $email = $_GET['email'];

    // get a user from user table
    $result = mysql_query("SELECT *FROM user WHERE email = '".$email."'");

    if (!empty($result)) {
        // check for empty result
        if (mysql_num_rows($result) > 0) {

            $result = mysql_fetch_array($result);

            $user = array();
            $user["uid"] = $result["uid"];
            $user["username"] = $result["username"];
            $user["password"] = $result["password"];
            $user["curcity"] = $result["curcity"];
            $user["email"] = $result["email"];
            // success
            $response["success"] = 1;

            // user node
            $response["user"] = array();

            array_push($response["user"], $user);

            // echoing JSON response
            echo json_encode($response);
        } else {
            // no user found
            $response["success"] = 0;
            $response["message"] = "No user found";

            // echo no users JSON
            echo json_encode($response);
        }
    } else {
        // no user found
        $response["success"] = 0;
        $response["message"] = "No user found";

        // echo no users JSON
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";

    // echoing JSON response
    echo json_encode($response);
}
?>