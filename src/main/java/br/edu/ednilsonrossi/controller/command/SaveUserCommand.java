package br.edu.ednilsonrossi.controller.command;

import java.io.IOException;

import br.edu.ednilsonrossi.model.dao.UserDao;
import br.edu.ednilsonrossi.model.dao.UserDaoFactory;
import br.edu.ednilsonrossi.model.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SaveUserCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		var name = request.getParameter("textName");
		var email = request.getParameter("textEmail");
		var passwd = request.getParameter("textPassword");
		
		var dao = new UserDaoFactory().factory();
		var user = new User(name, email, passwd);
		var saved = dao.insert(user);
		
		String message;
		if (saved) 
			message = "Usuário cadastrado com sucesso.";
		else 
			message = "Erro ao cadastrar o usuário.";
		
		request.setAttribute("message", message);
		request.setAttribute("saved", saved);
		
		return "new_user_form.jsp";
	}

}
