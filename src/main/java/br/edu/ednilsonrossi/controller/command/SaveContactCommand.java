package br.edu.ednilsonrossi.controller.command;

import java.io.IOException;

import br.edu.ednilsonrossi.model.dao.ContactDao;
import br.edu.ednilsonrossi.model.dao.ContactDaoFactory;
import br.edu.ednilsonrossi.model.entity.Contact;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SaveContactCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		var name = request.getParameter("textName");
		var fone = request.getParameter("textFone");
		var email = request.getParameter("textEmail");
		
		// ContactDao dao = new ContactDaoFactory(ContactDaoType.JSON).factory();
		ContactDao dao = new ContactDaoFactory().factory();
		
		Contact contact = new Contact(name, fone, email);
		boolean saved = dao.create(contact);
		
		String message;
		if (saved) {
			message = "Contato salvo com sucesso!";
		} else {
			message = "Erro ao salvar contato. Verifique se o e-mail já consta na lista de contatos.";
		}
		
		request.setAttribute("message", message);
		request.setAttribute("saved", saved);
		
		return "contact_form.jsp";
	}

}
