package br.edu.ednilsonrossi.model.dao;

import br.edu.ednilsonrossi.model.entity.User;

public interface UserDao {
	
	boolean insert(User user);
	
	User findByEmail(String email);
	
}
