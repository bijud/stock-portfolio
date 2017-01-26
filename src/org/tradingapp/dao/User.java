package org.tradingapp.dao;

/**
 * This class defines the user object. This is stored in the user table.
 * 
 * @author Biju
 *
 */
public class User {

	private long id;
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + "]";
	}

}
