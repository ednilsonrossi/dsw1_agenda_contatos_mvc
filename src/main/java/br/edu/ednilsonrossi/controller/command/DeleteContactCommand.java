package br.edu.ednilsonrossi.controller.command;

import java.io.IOException;

import br.edu.ednilsonrossi.model.dao.ContactDao;
import br.edu.ednilsonrossi.model.dao.ContactDaoFactory;
import br.edu.ednilsonrossi.model.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteContactCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		User user = (User) request.getSession(false).getAttribute("user_id");
		String email = request.getParameter("email");
		
		// ContactDao dao = new ContactDaoFactory(ContactDaoType.JSON).factory();
		ContactDao dao = new ContactDaoFactory().factory();
		
		var contact = dao.retrieve(user, email);
		
		dao.delete(user, contact);
		/*
		 * Não é suficiente devolver a view contacts.jsp, visto que essa view
		 * depende do processo de recuperar dados da lista, então, aqui a view
		 * retornada irá realizar uma chamada ao processo de gerar a lista de 
		 * contados pelo command ListContactsCommand.
		 */
		return "contact.do?action=list";
	}

}