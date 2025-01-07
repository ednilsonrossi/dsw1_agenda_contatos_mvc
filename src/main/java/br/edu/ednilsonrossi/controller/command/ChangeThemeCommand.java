package br.edu.ednilsonrossi.controller.command;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ChangeThemeCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String theme = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if("navbarTheme".equals(cookie.getName())) {
					theme = cookie.getValue();
					break;
				}
			}
		} 
		
		if (theme == null || theme.equals("default")) {
			theme = "blue";
		} else {
			theme = "default";
		}
		
		Cookie themeCookie = new Cookie("navbarTheme", theme);
		themeCookie.setMaxAge(60 * 60 * 24 * 7); 	// Expira em 7 dias
		themeCookie.setPath("/");					// Disponível para toda aplicação
		response.addCookie(themeCookie);
		
		return "contact.do?action=logged";
	}
	
}
