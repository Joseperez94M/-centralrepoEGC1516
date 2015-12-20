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
<p>* /count/predefined --> Test de funcionamiento de una votacion</p> 
<p>* /count/referendum?pollId=* --> Recuento natural. Opcionalmente
se le puede a�adir el par�metro cod, dependiendo de la codificacion de
los votos obtenidos. Por ejemplo: 
<br> * /count/referendum?pollId=1&cod=special --> si se esperan recibir los
votos como array de bytes 
<br> * /count/referendum?pollId=2&cod=normal --> si se esperan recibir
los votos como un hash cadena cifrado</p>
</body>
</html>
