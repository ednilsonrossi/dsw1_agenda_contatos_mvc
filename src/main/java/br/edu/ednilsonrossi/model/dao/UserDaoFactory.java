package br.edu.ednilsonrossi.model.dao;

public class UserDaoFactory {

	public UserDao factory() {
		return new UserDaoImpl();
	}
}
