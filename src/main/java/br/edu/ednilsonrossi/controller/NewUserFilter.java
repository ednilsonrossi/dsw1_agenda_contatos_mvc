package br.edu.ednilsonrossi.controller;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

@WebFilter(urlPatterns = "/front.do")
public class NewUserFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest requestHttp = (HttpServletRequest) request;
		var action = requestHttp.getParameter("action");
		if (action.equals("newUser")) {
			var name = requestHttp.getParameter("textName");
			System.out.println("Tentativa de cadastro de novo usuário: " + name);
		}
		chain.doFilter(request, response);
	}

}
