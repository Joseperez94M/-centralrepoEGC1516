<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>Subsistema "Recuento y Modificaci�n de Resultados"</h1>

	<P>
		Subsistema encargado de la realizaci�n del recuento y
		la modificaci�n de los resultados de una votaci�n determinada. Para la
		realizaci�n del recuento se acceder� a la base de datos proporcionada
		por almacenamiento y utilizar� los m�todos proporcionados por
		verificaci�n para descifrar los votos obtenidos.
	</P>
	
	<h4>Importante: los tiempos de respuestas pueden ser bastante amplios.<br>
	No es responsabilidad del subsistema de recuento ni de ning�n miembro relacionado
	con el subsistema.<br>
	Se trata de un problema sobre el lugar donde esta desplegado el subsistema de
	Verificacion el cual debe ser consultado para el descifrado. <br>
	Aun asi, disculpen las molestias causadas.</h4>
	
	
	<h4>Listado de votaciones existentes:</h4>
	
	<h5>Mostrado sin visualizaci�n</h5>
	Votaci�n 1: <a href="https://recuento.herokuapp.com/count/1?cod=special">Votaci�n</a><br>
	
	Votaci�n 2:<a href="https://recuento.herokuapp.com/count/2">Votaci�n</a><br>
	
	<h5>Mostrado con visualizaci�n</h5>
	Votaci�n 1: <a href="https://recuento.herokuapp.com/count/1/charts?cod=special">Votaci�n</a><br>
	
	Votaci�n 2:<a href="https://recuento.herokuapp.com/count/2/charts">Votaci�n</a><br>
	
	<h5>Mostrado segmentado</h5>
	Por edad<br>
	Votaci�n 1: <a href="https://recuento.herokuapp.com/count/1/charts?cod=special&segment=age">Votaci�n</a><br>
	
	Votaci�n 2:<a href="https://recuento.herokuapp.com/count/2/charts?segment=age">Votaci�n</a><br>
	
	Por g�nero<br>
	Votaci�n 1: <a href="https://recuento.herokuapp.com/count/1/charts?cod=special&segment=gender">Votaci�n</a><br>
	
	Votaci�n 2:<a href="https://recuento.herokuapp.com/count/2/charts?segment=gender">Votaci�n</a><br>
	Por comunidad autonoma<br>
	Votaci�n 1: <a href="https://recuento.herokuapp.com/count/1/charts?cod=special&segment=aut_com">Votaci�n</a><br>
	
	Votaci�n 2:<a href="https://recuento.herokuapp.com/count/2/charts?segment=aut_com">Votaci�n</a><br>
	
	<h4>API REST del subsistema:</h4>

	<p>*/count/predefined --> Test de funcionamiento de una votaci�n.</p>

	<p>*/count/{idVotacion} --> Recuento de un refer�ndum.</p>

	<p>
		Se puede a�adir el par�metro "cod", dependiendo de la codificaci�n de
		los votos.<br> */count/1?cod=special --> si se esperan recibir
		los votos como array de bytes. <br> */count/2 o
		*/count/2?cod=normal --> si se esperan recibir los votos como un hash
		cadena cifrado.
	</p>
	<p>
		Es posible segmentar el recuento mediante el par�metro "segment" por
		edad, g�nero y comunidad aut�noma. <br> */count/2?segment=age -->
		para segmentar el recuento por edad.<br> */count/2?segment=gender
		--> para segmentar el recuento por g�nero.<br>
		*/count/2?segment=aut_com --> para segmentar el recuento por com.
		aut�noma.
	</p>

	<p>
		Para visualizar los resultados de una votaci�n por segmento, invocar
		las siguientes rutas: <br> */count/2/charts?segment=age -->
		para segmentar el recuento por edad.<br> */count/2/charts?segment=gender
		--> para segmentar el recuento por g�nero.<br>
		*/count/2/charts?segment=aut_com --> para segmentar el recuento por com.
		aut�noma.
	</p>
</body>
</html>
