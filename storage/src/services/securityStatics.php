<?php

function checkAsNotAdminUser($token){
	try{
		$tokenDivido = split(':', $token);
		$nameUser = $tokenDivido[0];
		// Construyendo las URLs para comprobar que el usuario es una administrador
		$urlGetUser = 'http://auth-egc.azurewebsites.net/api/getUser?username='.$nameUser;
		// Cogiendo los datos
		$stringUser = file_get_contents($urlGetUser);
		// Decodificando
		$dataUser = json_decode($stringUser,true);
		// Cogiendo el atributo concreto que necesitamos
		$isAdmin = $dataUser['Is_admin'];
		if($isAdmin == 0){
			return true;
		}else{
			return false;
		}
	}catch(Exception $e){
		return false;
	}
}

function isLoguedAsNotAdmin(){
	$token = $_COOKIE['token'];
	return checkAsNotAdminUser($token);
}

?>
