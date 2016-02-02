<?php

include 'config.php';

try{

	header("Content-Type:application/json");
	
	$conn = new mysqli($servername, $username, $password, $dbname);
	
	$sql = "SELECT distinct votation_id FROM Votes ";
	$result = $conn->query($sql);
	
	$votations = array();
	if ($result->num_rows > 0) {
	    while($row = $result->fetch_assoc()) {
	        $votations[] = $row["votation_id"];
	    }
	}
	
	echo json_encode(array("votations"=>$votations, "msg" => 1));
	$conn->close();
}catch(Exception $e){
	echo json_encode(array("msg"=>0));
}
die();

?>
