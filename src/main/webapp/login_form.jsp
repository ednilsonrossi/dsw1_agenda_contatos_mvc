<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="includes/head.html" />
<body>
	<jsp:include page="includes/navBar.jsp" />

	<main class="container-sm flex-grow-1  justify-content-center">
		<h1 style="text-align: center; margin: 30px;">Log In</h1>

		<%
		String msg = (String) request.getAttribute("message");
		if (msg != null ) {
			out.println("<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\">");
			out.println(msg);
			out.println("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button></div>");
		}
		%>

		<form class="bg-white p-4 rounded-3 shadow" action="front.do?action=login" method="post">

			<div class="mb-3">
				<label for="email" class="form-label">E-mail</label> 
				<input type="email" class="form-control" id="email" name="textEmail"
					placeholder="Digite o e-mail do contato." required="required" value="ednilson@email.com">
			</div>
			<div class="mb-3">
				<label for="password" class="form-label">Senha</label> 
				<input
					type="password" class="form-control" id="password" name="textPassword"
					placeholder="Digite a senha segura." required="required" value="123">
			</div>

			<button type="submit" class="btn btn-warning"
				style="text-align: center;">Entrar</button>
				
		</form>
	</main>

	<jsp:include page="includes/scripts.html" />
</body>
</html>