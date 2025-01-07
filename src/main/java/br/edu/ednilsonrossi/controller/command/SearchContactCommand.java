package br.edu.ednilsonrossi.controller.command;

import java.io.IOException;

import br.edu.ednilsonrossi.model.dao.ContactDaoFactory;
import br.edu.ednilsonrossi.model.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SearchContactCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		var user = (User) request.getSession(false).getAttribute("user_id");
		var name = request.getParameter("textName");
		var dao = new ContactDaoFactory().factory();
		
		var contacts = dao.findByName(user, name);
		
		if (contacts.isEmpty()) {
			// Contatos não encontrado, apresenta-se a lista completa e mensagem de erro.
			contacts = dao.retrieve(user);
			request.setAttribute("errorMessage", "Contato '" + name + "' não localizado.");
		}
		
		request.setAttribute("contacts", contacts);
		
		return "/loggedin/contacts.jsp";
	}

}
