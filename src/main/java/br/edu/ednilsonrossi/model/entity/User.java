package br.edu.ednilsonrossi.model.entity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class User {

	private String email;
	private String name;
	private String password;
	private List<Contact> contacts;
	
	public User(String name, String email, String password) {
		init(name, email, hashSHA256(password));
	}
	
	public User(String name, String email, String password, boolean fromDatabase) {
		if (fromDatabase) {
			init(name, email, password);
		} else {
			init(name, email, hashSHA256(password));
		}
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = hashSHA256(password);
	}
	
	public void setPasswordHash(String passwordHash) {
		this.password = passwordHash;
	}
	
	public void addContact(Contact contact) {
		contacts.add(new Contact(contact.getName(), contact.getFone(), contact.getEmail()));
	}
	
	public List<Contact> getContacts() {
		return new ArrayList<Contact>(contacts);
	}
	
	public void clearContacts() {
		contacts.clear();
	}
	
	public static boolean autenticate(User userFromSystem, String email, String password) {
		if (userFromSystem != null) {
			return hashSHA256(password).equals(userFromSystem.password) && email.equals(userFromSystem.email);
		}
		return false;
	}
	
	private void init(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
		contacts = new LinkedList<Contact>();
	}
	
	private static String hashSHA256(String input) {
		try {
			var digest = MessageDigest.getInstance("SHA-256");
			
			byte[] hashBytes = digest.digest(input.getBytes());
			
			var sb = new StringBuilder();
			for (byte b : hashBytes) {
				String hex = Integer.toHexString(0xff & b);
				if (hex.length() == 1) {
					sb.append('0');
				}
				sb.append(hex);
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao criptografar.", e);
		}
		
	}
}
