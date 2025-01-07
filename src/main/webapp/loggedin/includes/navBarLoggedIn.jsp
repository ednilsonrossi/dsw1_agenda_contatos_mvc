<%@page import="br.edu.ednilsonrossi.model.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
var defaultTheme = true;
var cookies = request.getCookies();
for (Cookie c : cookies) {
	if (c.getName().equals("navbarTheme")) {
		if (c.getValue().equals("blue")) {
			defaultTheme = false;
		}
		break;
	}
}
%>
<%if(defaultTheme){ %>
<nav class="navbar navbar-expand-lg bg-warning">
<%} else { %>
<nav class="navbar navbar-expand-lg bg-info">
<%} %>
	<div class="container-fluid">
		<a class="navbar-brand" href="index.jsp">DWS1</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<li class="nav-item"><a class="nav-link active"
					aria-current="page" href="<%= request.getContextPath() %>/contact.do?action=logged">Home</a></li>
				<li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/contact.do?action=getForm">Novo Contato</a></li>
				<li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/contact.do?action=list">Contatos</a></li>
				<li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/contact.do?action=logoff">Log Off</a></li>
				<li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/contact.do?action=changeTheme">Alterar Tema</a></li>
			</ul>
			<% var user = (User) session.getAttribute("user_id"); %>
			<div class="nav justify-content-end">
				<%= user.getName() %>
			</div>
		
		</div>
	</div>
</nav>