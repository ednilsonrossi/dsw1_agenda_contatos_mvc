package br.edu.ednilsonrossi.controller.command;

import java.io.IOException;

import br.edu.ednilsonrossi.model.dao.ContactDaoFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SearchContactCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		var name = request.getParameter("textName");
		var dao = new ContactDaoFactory().factory();
		
		var contacts = dao.findByName(name);
		
		if (contacts.isEmpty()) {
			// Contatos não encontrado, apresenta-se a lista completa e mensagem de erro.
			contacts = dao.retrieve();
			request.setAttribute("errorMessage", "Contato '" + name + "' não localizado.");
		}
		
		request.setAttribute("contacts", contacts);
		
		return "contacts.jsp";
	}

}
