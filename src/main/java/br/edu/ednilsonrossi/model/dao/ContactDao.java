package br.edu.ednilsonrossi.model.dao;


import java.util.List;

import br.edu.ednilsonrossi.model.entity.Contact;

public interface ContactDao {
	
	boolean create(Contact contact);
	
	Contact retrieve(String email);
	
	List<Contact> retrieve();
	
	boolean update(Contact updatedContact, String oldEmail);
	
	boolean delete(Contact contact);
}