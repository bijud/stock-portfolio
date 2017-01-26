package org.tradingapp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.tradingapp.dao.mappers.UserMapper;
import org.tradingapp.main.AppConfig;

/**
 * Database access for the user table.
 * 
 * @author Biju
 *
 */
@Repository
public class UserRepository {

	/**
	 * Our jdbc template that we wired up in the {@link AppConfig} class.
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * Grab all the user records from the database.
	 * 
	 * @return - {@link List} of {@link User} objects from the database.
	 */
	@Transactional(readOnly = true)
	public List<User> findAll() {
		return jdbcTemplate.query("SELECT * FROM trading.user", new UserMapper());
	}

	/**
	 * Insert a user record into the database.
	 * 
	 * @param user
	 *            - {@link User} object we are putting in the database.
	 */
	@Transactional
	public void insert(User user) {
		String insertStatement = "INSERT INTO trading.user" + "(id, username) VALUES (?, ?)";

		jdbcTemplate.update(insertStatement, new Object[] { user.getId(), user.getUsername() });
	}
}
