package br.edu.ednilsonrossi.controller.command;

import java.io.IOException;
import java.util.List;

import br.edu.ednilsonrossi.model.dao.ContactDao;
import br.edu.ednilsonrossi.model.dao.ContactDaoFactory;
import br.edu.ednilsonrossi.model.dao.ContactDaoFactory.ContactDaoType;
import br.edu.ednilsonrossi.model.entity.Contact;
import br.edu.ednilsonrossi.model.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ListContactsCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		var user = (User) request.getSession(false).getAttribute("user_id");
		
		// ContactDao dao = new ContactDaoFactory(ContactDaoType.JSON).factory();
		ContactDao dao = new ContactDaoFactory().factory();
		
		List<Contact> contacts = dao.retrieve(user);
		request.setAttribute("contacts", contacts);
		
		return "/loggedin/contacts.jsp";
	}

}
