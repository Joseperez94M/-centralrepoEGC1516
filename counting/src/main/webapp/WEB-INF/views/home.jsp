<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>

	Subsistema "Recuento y Modificación de Resultados"  
	
</h1>

<P> 
${serverTime} </br>

Subsistema encargado de la realización del recuento y la modificación de los 
resultados de una votación determinada. Para la realización del recuento 
se accederá a la base de datos proporcionada por almacenamiento 
y utilizará los métodos proporcionados por verificación para descifrar
los votos obtenidos </P>

API REST del subsistema: 
<p>* / --> Welcome del proyecto</p>
<p>* /count/predefined --> Test de funcionamiento</p> 
<p>* /count/natural?votationId=* --> Recuento natural</p>
</body>
</html>
