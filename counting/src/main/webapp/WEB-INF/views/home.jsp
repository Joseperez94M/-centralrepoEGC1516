<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>

	Subsistema "Recuento y Modificaci�n de Resultados"  
	
</h1>

<P> 
${serverTime} </br>

Subsistema encargado de la realizaci�n del recuento y la modificaci�n de los 
resultados de una votaci�n determinada. Para la realizaci�n del recuento 
se acceder� a la base de datos proporcionada por almacenamiento 
y utilizar� los m�todos proporcionados por verificaci�n para descifrar
los votos obtenidos </P>

API REST del subsistema: 
<p>* / --> Welcome del proyecto</p>
<p>* /count/predefined --> Test de funcionamiento</p> 
<p>* /count/natural?votationId=* --> Recuento natural</p>
</body>
</html>
