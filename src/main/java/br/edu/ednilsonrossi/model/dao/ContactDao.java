package br.edu.ednilsonrossi.model.dao;


import java.util.List;

import br.edu.ednilsonrossi.model.entity.Contact;
import br.edu.ednilsonrossi.model.entity.User;

public interface ContactDao {
	
	boolean create(User user, Contact contact);
	
	Contact retrieve(User user, String email);
	
	List<Contact> retrieve(User user);
	
	List<Contact> findByName(User user, String name);
	
	// boolean update(Contact updatedContact, String oldEmail);
	
	boolean delete(User user, Contact contact);
}