<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>Subsistema "Recuento y Modificación de Resultados"</h1>

	<P>
		Subsistema encargado de la realización del recuento y
		la modificación de los resultados de una votación determinada. Para la
		realización del recuento se accederá a la base de datos proporcionada
		por almacenamiento y utilizará los métodos proporcionados por
		verificación para descifrar los votos obtenidos.
	</P>
	
	<h4>Importante: los tiempos de respuestas pueden ser bastante amplios.<br>
	No es responsabilidad del subsistema de recuento ni de ningún miembro relacionado
	con el subsistema.<br>
	Se trata de un problema sobre el lugar donde esta desplegado el subsistema de
	Verificacion el cual debe ser consultado para el descifrado. <br>
	Aun asi, disculpen las molestias causadas.</h4>
	
	
	<h4>Listado de votaciones existentes:</h4>
	
	<h5>Mostrado sin visualización</h5>
	Votación 1: <a href="https://recuento.herokuapp.com/count/1?cod=special">Votación</a><br>
	
	Votación 2:<a href="https://recuento.herokuapp.com/count/2">Votación</a><br>
	
	<h5>Mostrado con visualización</h5>
	Votación 1: <a href="https://recuento.herokuapp.com/count/1/charts?cod=special">Votación</a><br>
	
	Votación 2:<a href="https://recuento.herokuapp.com/count/2/charts">Votación</a><br>
	
	<h5>Mostrado segmentado</h5>
	Por edad<br>
	Votación 1: <a href="https://recuento.herokuapp.com/count/1/charts?cod=special&segment=age">Votación</a><br>
	
	Votación 2:<a href="https://recuento.herokuapp.com/count/2/charts?segment=age">Votación</a><br>
	
	Por género<br>
	Votación 1: <a href="https://recuento.herokuapp.com/count/1/charts?cod=special&segment=gender">Votación</a><br>
	
	Votación 2:<a href="https://recuento.herokuapp.com/count/2/charts?segment=gender">Votación</a><br>
	Por comunidad autonoma<br>
	Votación 1: <a href="https://recuento.herokuapp.com/count/1/charts?cod=special&segment=aut_com">Votación</a><br>
	
	Votación 2:<a href="https://recuento.herokuapp.com/count/2/charts?segment=aut_com">Votación</a><br>
	
	<h4>API REST del subsistema:</h4>

	<p>*/count/predefined --> Test de funcionamiento de una votación.</p>

	<p>*/count/{idVotacion} --> Recuento de un referéndum.</p>

	<p>
		Se puede añadir el parámetro "cod", dependiendo de la codificación de
		los votos.<br> */count/1?cod=special --> si se esperan recibir
		los votos como array de bytes. <br> */count/2 o
		*/count/2?cod=normal --> si se esperan recibir los votos como un hash
		cadena cifrado.
	</p>
	<p>
		Es posible segmentar el recuento mediante el parámetro "segment" por
		edad, género y comunidad autónoma. <br> */count/2?segment=age -->
		para segmentar el recuento por edad.<br> */count/2?segment=gender
		--> para segmentar el recuento por género.<br>
		*/count/2?segment=aut_com --> para segmentar el recuento por com.
		autónoma.
	</p>

	<p>
		Para visualizar los resultados de una votación por segmento, invocar
		las siguientes rutas: <br> */count/2/charts?segment=age -->
		para segmentar el recuento por edad.<br> */count/2/charts?segment=gender
		--> para segmentar el recuento por género.<br>
		*/count/2/charts?segment=aut_com --> para segmentar el recuento por com.
		autónoma.
	</p>
</body>
</html>
