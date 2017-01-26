package org.tradingapp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.tradingapp.dao.mappers.StockMapper;
import org.tradingapp.main.AppConfig;

/**
 * Implement a spring repository for access to the database for stock objects.
 * If we were building this more, we would probably want to select stocks by
 * user, otherwise the current methods would be inadequate. Returning all
 * results from the database to manually pull out one user is not smart.
 * 
 * @author Biju
 *
 */
@Repository
public class StockRepository {

	/**
	 * Our jdbc template that we wired up in the {@link AppConfig} class.
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * Grab all the stock records from the database.
	 * 
	 * @return - {@link List} of {@link Stock} objects from the database.
	 */
	@Transactional(readOnly = true)
	public List<Stock> findAll() {
		return jdbcTemplate.query("SELECT * FROM stocks", new StockMapper());
	}

	/**
	 * Grab all the stock records from the database.
	 * 
	 * @return - {@link List} of {@link Stock} objects from the database.
	 */
	@Transactional(readOnly = true)
	public Stock findOne(String companyName) {
		String query = "SELECT * FROM stocks WHERE company_name = ?";

		return jdbcTemplate.queryForObject(query, new Object[] { companyName }, new StockMapper());
	}

	/**
	 * Insert a stock record into the database.
	 * 
	 * @param stock
	 *            - {@link Stock} object we are putting in the database.
	 */
	@Transactional
	public void insert(Stock stock) {
		String insertStatement = "INSERT INTO stocks "
				+ "(id, company_name, current_price, purchase_price, net_profit, user_id) VALUES (?, ?, ?, ?, ?, ?)";

		jdbcTemplate.update(insertStatement, new Object[] { stock.getId(), stock.getCompanyName(),
				stock.getCurrentPrice(), stock.getPurchasePrice(), stock.getNetProfit(), stock.getUserId() });
	}

	/**
	 * Delete a stock from the stocks table based on the stock id.
	 * 
	 * @param stock
	 *            - The stock to delete.
	 */
	@Transactional
	public void deleteStock(Stock stock) {
		String deleteStatement = "DELETE FROM stocks WHERE company_name = ?";

		jdbcTemplate.update(deleteStatement, new Object[] { stock.getCompanyName() });
	}

	/**
	 * If looking at a stock that is already in the database we want to make
	 * sure the prices are up to date, lets go ahead and update those values.
	 * 
	 * @param stock
	 *            - The stock we want to update prices on.
	 */
	public void updateStockPrices(Stock stock) {
		String updateStatement = "UPDATE stocks SET current_price = ?, purchase_price = ?, net_profit = ? WHERE id = ?";

		stock.setId(findOne(stock.getCompanyName()).getId());

		jdbcTemplate.update(updateStatement, new Object[] { stock.getCurrentPrice(), stock.getPurchasePrice(),
				stock.getNetProfit(), stock.getId() });
	}
}
