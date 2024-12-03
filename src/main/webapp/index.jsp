<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page import="com.edutecno.modelo.Usuario" %>
<%
    HttpSession sesion = request.getSession(false);
    Usuario usuario = sesion != null ? (Usuario) sesion.getAttribute("usuario") : null;

    // Decodificar el mensaje para evitar problemas con caracteres especiales
    String mensaje = request.getParameter("mensaje");
    if (mensaje != null) {
        mensaje = URLDecoder.decode(mensaje, "UTF-8");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" 
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        <%@ include file="assets/css/estilos.css" %>
    </style>
    <title>Home</title>
</head>
<body>
<%@ include file="menu.jsp" %>
<main class="container mt-3">
    <div class="welcomeContainer">
        <h1 class="titulo">Bienvenido</h1>
        <h3 class="subTitulo">
            <% 
            if (usuario != null) { 
                out.print(usuario.getNombre() + ", tu animal del zodiaco chino es el: " + usuario.getAnimal()); 
            } else { 
                out.print("Invitado. Regístrate para poder conocer tu signo zodiacal."); 
            %>
                <div class="col-12 mt-3">
                    <a href="registro.jsp" class="btn btn-warning" id="registro">Regístrate Aquí</a>
                </div>
            <% } %>
        </h3>
    </div>
</main>

<!-- Modal de mensaje -->
<c:if test="${not empty mensaje}">
    <div class="modal fade" id="mensajeModal" tabindex="-1" aria-labelledby="mensajeModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="mensajeModalLabel">Mensaje del Sistema</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                </div>
                <div class="modal-body">
                    ${mensaje}
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Cerrar</button>
                </div>
            </div>
        </div>
    </div>
</c:if>

<%@ include file="pie.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script>
    // Mostrar el modal automáticamente
    window.onload = function() {
        const mensajeModal = document.getElementById('mensajeModal');
        if (mensajeModal) {
            const modal = new bootstrap.Modal(mensajeModal);
            modal.show();
            console.log("Modal mostrado correctamente");
        } else {
            console.log("Modal no encontrado");
        }
    };
</script>
</body>
</html>
