package org.tradingapp.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.tradingapp.dao.User;

/**
 * A class to map a record in the user table to a {@link User} object.
 * 
 * @author Biju
 *
 *         This is automatically called by the JDBC template. As the JDBC
 *         template iterates over the resultset it creates an object for each
 *         record.
 *
 */
public class UserMapper implements RowMapper<User> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
	 * int)
	 */
	@Override
	public User mapRow(ResultSet arg0, int arg1) throws SQLException {
		User user = new User();
		user.setUsername(arg0.getString("username"));
		user.setId(arg0.getLong("id"));

		return user;
	}

}
