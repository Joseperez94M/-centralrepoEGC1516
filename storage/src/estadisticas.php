<?php 

include 'services/securityStatics.php';

$isLoguedAsNotAdmin = false;

if(!$isLoguedAsNotAdmin){
	$isLoguedAsNotAdmin = isLoguedAsNotAdmin();
}
if($isLoguedAsNotAdmin){
	echo "Usuario no administrador.";
	exit();
}
?>

<!DOCTYPE html>
<html lang="es">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Almacenamiento de Votos - Agora US</title>

    <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">

    <!-- Custom Fonts -->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic,900,900italic' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="font-awesome/css/font-awesome.min.css" type="text/css">

    <!-- Plugin CSS -->
    <link rel="stylesheet" href="css/animate.min.css" type="text/css">

    <!-- Custom CSS -->
    <link rel="stylesheet" href="css/creative.css" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body id="page-top">
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		<div id="styleHeadEstadisticas" >
			<ul class="nav navbar-nav navbar-right">
                    <li>
                        <a class="page-scroll" href="../src/index.php">ALMACENAMIENTO DE VOTOS</a>
                    </li>
                </ul>
			</div>
                
        </div>
	<section class="bg-primary" id="stadistics">
		<div class="col-lg-8 col-lg-offset-2 text-center">
			<h2 class="section-heading">Estadísticas</h2>
			<hr class="light">
			<p>Aqu&iacute; podr&aacute; ver el n&uacute;mero de votos totales (Total) almacenados por cada votaci&oacute;n (Votation_id)</p>
		</div>
	<hr/>
	</section>
	
	<table class="table table-hover">
	<tr>
		<td id="titleColumn"><b>Votation_id</b></td>
		<td id="titleColumn"><b>Total</b></td>
	</tr>
	
	<?php
		include 'config.php';
		
		$link = @mysql_connect($servername, $username,$password) or die ("Error al conectar a la base de datos.");
  		@mysql_select_db($dbname, $link) or die ("Error al conectar a la base de datos.");
		
		
		$sql = "SELECT votation_id, COUNT(*) FROM Votes GROUP BY votation_id";
		
		$result = mysql_query($sql);
			
		while($row = mysql_fetch_array($result)){
			echo "<tr><td>" . $row["votation_id"] . "</td>"; 
			echo "<td>" . $row["COUNT(*)"] . "</td></tr>"; 
		}
		
		mysql_free_result($result);
		mysql_close();
	?>	
	
	</table>


    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <!-- Plugin JavaScript -->
    <script src="js/jquery.easing.min.js"></script>
    <script src="js/jquery.fittext.js"></script>
    <script src="js/wow.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="js/creative.js"></script>

</body>

</html>
