package br.edu.ednilsonrossi.controller;

import java.io.IOException;

import br.edu.ednilsonrossi.controller.command.Command;
import br.edu.ednilsonrossi.controller.command.DeleteContactCommand;
import br.edu.ednilsonrossi.controller.command.ErrorCommand;
import br.edu.ednilsonrossi.controller.command.FormContactCommand;
import br.edu.ednilsonrossi.controller.command.ListContactsCommand;
import br.edu.ednilsonrossi.controller.command.LoggedCommand;
import br.edu.ednilsonrossi.controller.command.LogoffCommand;
import br.edu.ednilsonrossi.controller.command.SaveContactCommand;
import br.edu.ednilsonrossi.controller.command.SearchContactCommand;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/contact.do")
public class ContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		Command command;
		
		if ("logged".equals(action) ) {
			command = new LoggedCommand();
		}else if("list".equals(action)) {
			command = new ListContactsCommand();
		} else if ("newContact".equals(action)) {
			command = new SaveContactCommand();
		} else if ("getForm".equals(action)) {
			command = new FormContactCommand();
		}else if ("delete".equals(action)) {
			command = new DeleteContactCommand();
		}else if("searchContact".equals(action)) {
			command = new SearchContactCommand();
		} else if ("logoff".equals(action)) {
			command = new LogoffCommand();
		} else {
			command = new ErrorCommand();
		}
		
		String view = command.execute(request, response);
		var dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

}
