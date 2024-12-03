<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="com.edutecno.modelo.Usuario" %>
<%
    HttpSession sesion = request.getSession(false);
    Usuario usuario = (Usuario) (sesion != null ? sesion.getAttribute("usuario") : null);
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" 
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        <%@ include file="assets/css/estilos.css" %>
    </style>
    <title>Consulta tu Horóscopo Chino</title>
</head>
<body>
    <%@ include file="menu.jsp" %>
    <section>
        <div class="container py-5 h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                    <div class="card" style="border-radius: 1rem;">
                        <div class="card-body p-3 text-center">
                            <h2 class="mb-4">Consulta tu Horóscopo Chino</h2>
                            <c:choose>
                                <c:when test="${usuario != null}">
                                    <p class="mb-4">Bienvenido, <strong>${usuario.nombre}</strong></p>
                                </c:when>
                                <c:otherwise>
                                    <p class="mb-4">Bienvenido, <strong>Invitado</strong></p>
                                </c:otherwise>
                            </c:choose>
                            <form action="ConsultarHoroscopo" method="post" class="needs-validation" novalidate>
                                <div class="mb-4">
                                    <label for="fechaNacimiento" class="form-label">Ingresa tu fecha de Nacimiento</label>
                                    <input type="date" class="form-control" id="fechaNacimiento" name="fechaNacimiento" required>
                                    <div class="invalid-feedback">
                                        Debes ingresar tu fecha de nacimiento.
                                    </div>
                                </div>
                                <button class="btn btn-warning w-100" type="submit">Consultar</button>
                            </form>
                            <c:if test="${not empty animal}">
                                <div class="mt-4">
                                    <h3>Tu signo del horóscopo chino es:</h3>
                                    <p class="fs-4 text-success"><strong>${animal}</strong></p>
                                </div>
                            </c:if>
                            <c:if test="${not empty error}">
                                <div class="alert alert-danger mt-4" role="alert">
                                    ${error}
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <%@ include file="pie.jsp" %>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <script>
        (() => {
            'use strict';

            const forms = document.querySelectorAll('.needs-validation');

            Array.from(forms).forEach(form => {
                form.addEventListener('submit', event => {
                    if (!form.checkValidity()) {
                        event.preventDefault();
                        event.stopPropagation();
                    }

                    form.classList.add('was-validated');
                }, false);
            });
        })();
    </script>
</body>
</html>
