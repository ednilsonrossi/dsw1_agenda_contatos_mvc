package br.edu.ednilsonrossi.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import br.edu.ednilsonrossi.model.dao.connection.DatabaseConnection;
import br.edu.ednilsonrossi.model.entity.Contact;
import br.edu.ednilsonrossi.model.entity.User;

class DatabaseContactDao implements ContactDao {

	private static final String CREATE_TABLE = "CREATE TABLE tb_contacts (\n"
			+ "    name VARCHAR(150) NOT NULL,\n"
			+ "    fone VARCHAR(20),\n"
			+ "    email VARCHAR(128) NOT NULL,\n"
			+ "    username VARCHAR(128) NOT NULL,\n"
			+ "    PRIMARY KEY (username, email),\n"
			+ "    FOREIGN KEY (username) REFERENCES tb_user(email) ON DELETE CASCADE\n"
			+ ");";
	private static final String INSERT = "INSERT INTO tb_contacts (name, fone, email, username) VALUES (?, ?, ?, ?)";
	private static final String SELECT_BY_EMAIL = "SELECT * FROM tb_contacts WHERE email = ? AND username = ?";
	private static final String SELECT_BY_NAME = "SELECT * FROM tb_contacts WHERE name LIKE ? AND username = ? ORDER BY name";
	private static final String SELECT_ALL = "SELECT * FROM tb_contacts WHERE username = ? ORDER BY name";
	private static final String UPDATE = "UPDATE tb_contacts SET name = ?, fone = ?, email = ? WHERE email = ?";
	private static final String DELETE = "DELETE FROM tb_contacts WHERE email = ? AND username = ?";

	@Override
	public boolean create(User user, Contact contact) {
		if (contact != null) {
			int rows = -1;
			try ( var connection = DatabaseConnection.getConnection();
				  var preparedStatement = connection.prepareStatement(INSERT)) {

				preparedStatement.setString(1, contact.getName());
				preparedStatement.setString(2, contact.getFone());
				preparedStatement.setString(3, contact.getEmail());
				preparedStatement.setString(4, user.getEmail());
				rows = preparedStatement.executeUpdate();

				if (rows > 0) {
					user.addContact(contact);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return rows > 0;
		}
		return false;
	}

	@Override
	public Contact retrieve(User user, String email) {
		Contact contact = null;
		if (email != null && !email.isEmpty()) {
			try (var connection = DatabaseConnection.getConnection();
				 var preparedStatement = connection.prepareStatement(SELECT_BY_EMAIL)){
				
				preparedStatement.setString(1, email);
				preparedStatement.setString(2, user.getEmail());

				ResultSet result = preparedStatement.executeQuery();
				if (result.next()) {
					contact = new Contact();
					contact.setEmail(result.getString("email"));
					contact.setFone(result.getString("fone"));
					contact.setName(result.getString("name"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return contact;
	}

	@Override
	public List<Contact> retrieve(User user) {
		//List<Contact> contacts = new LinkedList<Contact>();
		user.clearContacts();

		try (var connection = DatabaseConnection.getConnection();
			 var preparedStatement = connection.prepareStatement(SELECT_ALL)){
			
			preparedStatement.setString(1, user.getEmail());
			var result = preparedStatement.executeQuery();

			while (result.next()) {
				var contact = new Contact();
				contact.setEmail(result.getString("email"));
				contact.setFone(result.getString("fone"));
				contact.setName(result.getString("name"));
				user.addContact(contact);
				//contacts.add(contact);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//return contacts;
		return user.getContacts();
	}

	@Override
	public List<Contact> findByName(User user, String name) {
		var contacts = new LinkedList<Contact>();
		if (name != null && !name.isEmpty()) {
			try ( var connection = DatabaseConnection.getConnection();
				  var preparedStatement = connection.prepareStatement(SELECT_BY_NAME)){
				/**
				 * Como o objetivo é usar o LIKE e usar coringas antes de depois do parâmetro,
				 * insere-se os coringas no parâmetro para ser definido no PreparedStatement.
				 */
				name = "%" + name + "%";
				preparedStatement.setString(1, name);
				preparedStatement.setString(2, user.getEmail());
				var result = preparedStatement.executeQuery();

				while (result.next()) {
					var contact = new Contact();
					contact.setEmail(result.getString("email"));
					contact.setFone(result.getString("fone"));
					contact.setName(result.getString("name"));
					contacts.add(contact);
				}
			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
				contacts = new LinkedList<Contact>();
			}
		}
		return contacts;
	}

//	@Override
//	public boolean update(Contact updatedContact, String oldEmail) {
//		if (updatedContact != null && !oldEmail.isEmpty()) {
//			int rows = -1;
//			try ( var connection = DatabaseConnection.getConnection();
//				  var preparedStatement = connection.prepareStatement(UPDATE)){
//				preparedStatement.setString(1, updatedContact.getName());
//				preparedStatement.setString(2, updatedContact.getFone());
//				preparedStatement.setString(3, updatedContact.getEmail());
//				preparedStatement.setString(4, oldEmail);
//
//				rows = preparedStatement.executeUpdate();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			return rows > 0;
//		}
//		return false;
//	}

	@Override
	public boolean delete(User user, Contact contact) {
		if (contact != null) {
			int rows = -1;
			try ( var connection = DatabaseConnection.getConnection();
				  var preparedStatement = connection.prepareStatement(DELETE)) {
				preparedStatement.setString(1, contact.getEmail());
				preparedStatement.setString(2, user.getEmail());

				rows = preparedStatement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return rows > 0;
		}
		return false;
	}

}
