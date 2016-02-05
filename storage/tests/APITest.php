<?php

class APITest extends PHPUnit_Framework_TestCase{
	
	// Get votes
	public function testGetVotes(){
		$id_vote = 1;
		$url = 'http://localhost/egc/src/get_votes.php?votation_id=' . $id_vote;
		$string = file_get_contents($url);
		$data = json_decode($string,true);
		$votes = $data["votes"];
		$this->assertEquals(count($votes),2);
	}
	
	// Get votations
	public function testGetVotations(){
		$url = 'http://localhost/egc/src/get_votations.php';
		$string = file_get_contents($url);
		$data = json_decode($string,true);
		$votations = $data["votations"];
		$this->assertEquals(count($votations),1);
	}
	
	// Vote
	public function testVote(){	
		$jsondata['vote'] = 'VotoPruebaTest';
		$jsondata['votation_id'] = "3";
		$url = 'http://localhost/egc/src/vote.php';
		$ch = curl_init();
		curl_setopt($ch, CURLOPT_URL, $url);
		curl_setopt($ch, CURLOPT_POST, 1);
		curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($jsondata));
		curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
		
		$response = curl_exec($ch);
		curl_close($ch);
		
		$this->assertEquals($response,'{"msg":"1"}');
		
	}	
}

?>
