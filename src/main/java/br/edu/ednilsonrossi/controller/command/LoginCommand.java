package br.edu.ednilsonrossi.controller.command;

import java.io.IOException;

import br.edu.ednilsonrossi.model.dao.UserDaoFactory;
import br.edu.ednilsonrossi.model.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		var email = request.getParameter("textEmail");
		var passwd = request.getParameter("textPassword");
		
		var dao = new UserDaoFactory().factory();
		var user = dao.findByEmail(email);
		
		var autorized = User.autenticate(user, email, passwd);
		
		String view;
		
		if (autorized) {
			var session = request.getSession(true);
			session.setAttribute("user_id", user);
			session.setMaxInactiveInterval(24 * 60 * 60);
			view = "contact.do?action=logged";
		} else {
			request.setAttribute("message", "Usuário ou senha inválido.");
			view = "front.do?action=getLoginForm";
		}
		
		return view;
	}

}
