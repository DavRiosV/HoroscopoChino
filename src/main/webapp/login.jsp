<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
		<style>
			<%@ include file="assets/css/estilos.css" %>
		</style>
		<title>Login</title>
	</head>
	<body>
		<%@ include file="menu.jsp" %>
		<section>
		<div class="container py-5 h-100">
			<div class="row d-flex justify-content-center align-items-center h-100">
			<div class="col-12 col-md-8 col-lg-6 col-xl-5">
				<div class="card" style="border-radius: 1rem;">
					<div class="card-body p-3 text-center">
						<form action="/HoroscopoChino/usuario?accion=login" method="post" class="needs-validation" novalidate>
						  <div class="mb-4">
						    <label for="validationCustom01" class="form-label">Usuario</label>
						    <input type="email" class="form-control" id="validationCustom01" name="usuario" required>
						    <div class="invalid-feedback">
						      Debe ingresar un usuario
						    </div>
						  </div>
						  
						  <div class="mb-4">
						    <label for="validationCustom03" class="form-label">Clave</label>
						    <input type="password" class="form-control" id="validationCustom03" name="clave" required>
						    <div class="invalid-feedback">
						      Debe ingresar su clave.
						    </div>
						  </div>
						  <div class="col-12">
						    <button class="btn btn-warning" type="submit">Entrar</button>
						  </div>
						</form>
						<c:if test="${not empty error}">
							<p style="color:red;">${error}</p>
						</c:if>
					</div>
				</div>
			</div>
			</div>
		</div>
		</section>
		<%@ include file="pie.jsp" %>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
		<script>
			// Example starter JavaScript for disabling form submissions if there are invalid fields
			(() => {
			  'use strict'
	
			  // Fetch all the forms we want to apply custom Bootstrap validation styles to
			  const forms = document.querySelectorAll('.needs-validation')
	
			  // Loop over them and prevent submission
			  Array.from(forms).forEach(form => {
			    form.addEventListener('submit', event => {
			      if (!form.checkValidity()) {
			        event.preventDefault()
			        event.stopPropagation()
			      }
	
			      form.classList.add('was-validated')
			    }, false)
			  })
			})()
		</script>
	</body>
</html>