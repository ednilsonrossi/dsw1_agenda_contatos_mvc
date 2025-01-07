package br.edu.ednilsonrossi.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import br.edu.ednilsonrossi.controller.command.Command;
import br.edu.ednilsonrossi.controller.command.FormLoginCommand;
import br.edu.ednilsonrossi.controller.command.FormUserCommand;
import br.edu.ednilsonrossi.controller.command.LoginCommand;
import br.edu.ednilsonrossi.controller.command.SaveUserCommand;


@WebServlet("/front.do")
public class FrontServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Command command = null;
		String action = request.getParameter("action");
		
		if ("getUserForm".equals(action)) {
			command = new FormUserCommand();
		} else if ("newUser".equals(action)) {
			command = new SaveUserCommand();
		} else if ("getLoginForm".equals(action)) {
			command = new FormLoginCommand();
		} else if ("login".equals(action)) {
			command = new LoginCommand();
		}
		
		String view = command.execute(request, response);
		var dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
}
