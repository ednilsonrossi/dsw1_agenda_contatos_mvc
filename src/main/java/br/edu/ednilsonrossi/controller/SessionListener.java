package br.edu.ednilsonrossi.controller;

import br.edu.ednilsonrossi.model.entity.User;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {
	private Long count = 0L;
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		HttpSessionListener.super.sessionCreated(se);
		
		count += 1;
		System.out.println("Usuários que acessaram o sistema: " + count);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSessionListener.super.sessionDestroyed(se);
		
		var session = se.getSession();
		var user = (User) session.getAttribute("user_id");
		if (user != null) {
			System.out.println("Usuário " + user.getEmail() + " saiu do sistema.");
		}
	}
}
