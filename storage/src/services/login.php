<?php

/**
 * Funcion que a traves de un token, nos creará una cookie de session si el token es correcto
 */
function loginService($token){
	try{		
		if(checkToken($token) && checkAdminUser($token)){
			setcookie("token", $token, time()+604800, '/'); //cookie 7 dias
			return true;
		}else{
			return false;
		}
	}catch(Exception $e){
		return false;
	}
}

function loginServiceAsNotAdmin($token){
	try{		
		if(checkToken($token) && checkAsNotAdminUser($token)){
			setcookie("token", $token, time()+604800, '/'); //cookie 7 dias
			return true;
		}else{
			return false;
		}
	}catch(Exception $e){
		return false;
	}
}

//Comprueba que el token es un token correcto
function checkToken($token){
	try{
		// Construyendo las URLs para corroborar token
		$url = 'http://auth-egc.azurewebsites.net/api/checkToken?token='.$token;
		// Cogiendo los datos
		$string = file_get_contents($url);
		// Decodificando
		$data = json_decode($string,true);
		// Cogiendo el atributo concreto que necesitamos
		$valido = $data['valid'];
		
		if($valido == 1){
			return true;
		}else{
			return false;
		}
		
	}catch(Exception $e){
		return false;
	}
}

//Comprueba que el user del token es un administrador
function checkAdminUser($token){
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
		if($isAdmin == 1){
			return true;
		}else{
			return false;
		}
	}catch(Exception $e){
		return false;
	}
}

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

//Funciones que miran si el usuario logueado es un administrador o no. Para ello revisa el token de la cookie
function isLoguedAsAdmin(){
	if(isset($_COOKIE['token'])){
		$token = $_COOKIE['token'];
		return checkToken($token) && checkAdminUser($token);
	}else{
		return false;
	}
}

function isLoguedAsNotAdmin(){
	if(isset($_COOKIE['token'])){
		$token = $_COOKIE['token'];
		return checkToken($token) && checkAsNotAdminUser($token);
	}else{
		return false;
	}
}
?>
