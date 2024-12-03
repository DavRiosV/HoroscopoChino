<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page import = "jakarta.servlet.http.HttpSession" %>
<%@ page import="com.edutecno.modelo.Usuario" %>
<%
    HttpSession sesion = request.getSession(false);
    Usuario usuario = sesion != null ? (Usuario) sesion.getAttribute("usuario") : null;

    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String mensaje = request.getParameter("mensaje");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" 
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
		   <%@ include file="assets/css/estilos.css" %>
    </style>
    <title>Opciones de Usuario</title>
</head>
<body>
    <%@ include file="menu.jsp" %>
    <main class="d-flex justify-content-center align-items-center vh-100">
        <div class="card-custom text-center">
            <h2>¿Qué deseas hacer, <%= usuario.getNombre() %>?</h2>
            <div class="btn-container">
                <a href="consulta.jsp" class="btn btn-custom">Conoce tu animal</a>
                <a href="ListarUsuariosServlet" class="btn btn-custom">Buscar Usuarios</a>
                <a href="modificarUsuario.jsp" class="btn btn-custom">Modificar datos</a>
                <form action="EliminarUsuarioServlet" method="post">
   					 <button class="btn btn-danger" type="submit">Eliminar Cuenta</button>
				</form>
            </div>
        </div>
    </main>

    <!-- Modal de Confirmación -->
    <c:if test="${param.mensaje != null}">
        <div class="modal fade" id="mensajeModal" tabindex="-1" aria-labelledby="mensajeModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="mensajeModalLabel">Mensaje</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <p>${param.mensaje}</p>
                    </div>
                    <div class="modal-footer">
                        <button id="redirectButton" class="btn btn-success">Aceptar</button>
                    </div>
                </div>
            </div>
        </div>
    </c:if>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <script>
        // Mostrar el modal automáticamente
        window.onload = function() {
            const mensajeModal = document.getElementById('mensajeModal');
            const redirectButton = document.getElementById('redirectButton');
            if (mensajeModal) {
                const modal = new bootstrap.Modal(mensajeModal);
                modal.show();

                // Redirigir al logout cuando se cierre el modal
                redirectButton.addEventListener('click', function() {
                    window.location.href = 'logout';
                });
            }
        };
    </script>
    <%@ include file="pie.jsp" %>
</body>
</html>
