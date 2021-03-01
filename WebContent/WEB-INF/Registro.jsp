<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html id="list">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="css/bootstrap.min.css" />
</head>
<!-- BARRA DE ICONOS -->
<body>
	<nav class="navbar navbar-expand-sm justify-content-between"
		style="background-color: #cdcdcd;">
		<div class="d-flex justify-content-start">
			<a href="IniciarSesionServlet.do"><img src="img/aventurero.svg"
				width="65" height="65"></a>
		</div>

		<div class="d-flex justify-content-end">

			<a class="btn btn-warning" href="register.html">Registrate</a>
		</div>
	</nav>
	<fieldset>
		<form method="post" action="?" method="post">
			<div class="container py-5">
				<div class="d-flex justify-content-center">
					<div class="col-md-5 ">
						<h2>${CheckType}</h2>
						<div class="form-group">
							<label for="email">Correo electrónico</label> <input name="email"
								type="email" class="form-control"
								placeholder="Escribe tu correo electrónico aquí"
								value="${user.email}" />
						</div>
						<div class="form-group">
							<label for="username">Nombre de usuario</label> <input
								name="username" type="text" class="form-control"
								placeholder="Nombre de usuario" value="${user.username}" />
						</div>
						<div class="form-group">
							<label for="password">Contraseña</label> <input name="password"
								type="password" class="form-control" placeholder="Contraseña"
								value="${user.password}" />
						</div>
						<div class="form-group">
							<input type="submit" value="${Button}"
								class="btn btn-success btn-block" />
							<div>${info}</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</fieldset>
</body>
</html>