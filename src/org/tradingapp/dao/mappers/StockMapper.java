package org.tradingapp.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.tradingapp.dao.Stock;

/**
 * Map a record in the "stocks" table to a {@link Stock} object.
 * 
 * @author Biju
 *
 */
public class StockMapper implements RowMapper<Stock> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
	 * int)
	 */
	@Override
	public Stock mapRow(ResultSet arg0, int arg1) throws SQLException {
		Stock stock = new Stock();

		stock.setCompanyName(arg0.getString("company_name"));
		stock.setCurrentPrice(arg0.getDouble("current_price"));
		stock.setPurchasePrice(arg0.getDouble("purchase_price"));
		stock.setId(arg0.getLong("id"));
		stock.setNetProfit(arg0.getString("net_profit"));
		stock.setUserId(arg0.getLong("user_id"));

		return stock;
	}

}
