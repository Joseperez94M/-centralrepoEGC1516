<?php 

session_start();
//Esto le da un tiempo de vida a la variable token de la cookie nulo. Por lo que basicamente elimina la variable.
setcookie("token",'',1, '/');
header("Location:../index.php");
die();
?>