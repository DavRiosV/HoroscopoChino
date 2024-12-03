<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page import = "jakarta.servlet.http.HttpSession" %>
<header>
	<nav class="navbar navbar-expand-lg" >
	  <div class="container-fluid">
	    <a class="navbar-brand" href="#">Horoscopo</a>
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="collapse navbar-collapse" id="navbarSupportedContent">
	      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
	        <li class="nav-item">
	          <a class="nav-link active" aria-current="page" href="index.jsp">Inicio</a>
	        </li>
	        <c:if test="${empty sessionScope.usuario}">
		        <li class="nav-item">
		          <a class="nav-link" href="login.jsp">Login</a>
		        </li>
	        </c:if>
	        <c:if test="${not empty sessionScope.usuario }">
		        <li class="nav-item">
		          <a class="nav-link" href="usuario.jsp">Usuario</a>
		        </li>
	        </c:if>
   	        <c:if test="${not empty sessionScope.usuario }">	        
		        <li class="nav-item">
		          <a class="nav-link" href="/HoroscopoChino/consulta.jsp">Horoscopo</a>
		        </li>
	        </c:if>
	        <c:if test="${not empty sessionScope.usuario }">	        
		        <li class="nav-item">
		          <a class="nav-link" href="/HoroscopoChino/logout">Logout</a>
		        </li>
	        </c:if>
	      </ul>
	    </div>
	  </div>
	</nav>
</header>