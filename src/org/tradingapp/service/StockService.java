package org.tradingapp.service;

import java.util.ArrayList;
import java.util.List;

import org.tradingapp.dao.Stock;
import org.tradingapp.dao.User;

/**
 * The plan for our service, allowing access to the database. We do not access
 * DAOs or the database from our code, simply call the service. This is the
 * plan, the service implementation will have content.
 * 
 * @author Biju
 *
 */
public interface StockService {

	/**
	 * Get all users. (Hands off to Repository)
	 * 
	 * @return - a list of {@link User} objects.
	 */
	List<User> getUsers();

	/**
	 * Get all the stocks for a specified user id. (Hands off to Repository)
	 * 
	 * @param id
	 *            - The user id to fetch stocks for.
	 * @return - The {@link Stock} list.
	 */
	ArrayList<Stock> getStocksForUserId(long id);

	/**
	 * Save a {@link Stock} object. (Hands off to Repository)
	 * 
	 * @param stock
	 *            - The {@link Stock} to save.
	 * 
	 */
	void saveStock(Stock stock);

	/**
	 * Save a {@link User} object. (Hands off to Repository)
	 * 
	 * @param user
	 *            - The {@link User} to save.
	 */
	void saveUser(User user);

	/**
	 * Delete a {@link Stock} object. (Hands off to Repository)
	 * 
	 * @param stock
	 *            - The {@link Stock} to delete.
	 */
	void deleteStock(Stock stock);
}
