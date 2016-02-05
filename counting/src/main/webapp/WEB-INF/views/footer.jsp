<%@page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="date" class="java.util.Date" />

<footer class="page-footer">
	<div class="container">
		<div class="row">
        	<div class="col l6 s12">
            	<h5 class="white-text">EGC - �gora US</h5>
                <h5 class="white-text">Recuento y modificaci�n de resultados</h5>
                <p class="white-text">Este subsistema realizar� el recuento de una votaci�n determinada. Para llevarlo a cabo tendr� que pedir los votos al subsistema de almacenamiento y deber� lanzar la tarea de recuento sincronizando las diferentes autoridades.</p>
            </div>
        	<div class="col l4 offset-l2 s12">
        		<h5 class="white-text">Miembros</h5>
        		<ul class="white-text">
        			<li>Rafael del R�o Santaella <b>(Coordinador)</b></li>
					<li>V�ctor Jos� Mar�n Aguilar</li>
					<li>Antonio Marcos Pe�a</li>
					<li>Juan Manuel Triguero Pi�ero</li>
					<li>Juan Ram�n R�os Fern�ndez</li>
        		</ul>
			</div>
		</div>
	</div>
    <div class="footer-copyright">
    	<div class="container">
        	� <fmt:formatDate value="${date}" pattern="yyyy" /> Universidad de Sevilla
        	<a class="grey-text text-lighten-4 right" href="https://github.com/rafaeldrs/Recuento-Agora-US" target="blank">Repositorio del c�digo en GitHub</a>
        </div>
	</div>
</footer>
