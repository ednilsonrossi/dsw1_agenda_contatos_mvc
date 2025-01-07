package br.edu.ednilsonrossi.controller;


import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;

@WebListener
public class SessionAttributeListener implements HttpSessionAttributeListener {

	@Override
	public void attributeAdded(HttpSessionBindingEvent se) {
		HttpSessionAttributeListener.super.attributeAdded(se);
		
		var attrName = se.getName();
		var attrValue = se.getValue();
		
		System.out.println("Atributo adicionado: " + attrName + " = " + attrValue);
	}

}
