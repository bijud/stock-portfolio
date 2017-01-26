package org.tradingapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tradingapp.dao.Stock;
import org.tradingapp.dao.StockRepository;
import org.tradingapp.dao.User;
import org.tradingapp.dao.UserRepository;

/**
 * The implementation of the stock service. This is the component the front end
 * communicates with for data access.
 * 
 * @author Biju
 *
 */
@Component
public class StockServiceImpl implements StockService {

	/**
	 * Get the {@link StockRepository} object from the Spring context.
	 */
	@Autowired
	private StockRepository stockRepository;

	/**
	 * Get the {@link UserRepository} object from the Spring context.
	 */
	@Autowired
	private UserRepository userRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.tradingapp.service.StockService#getStocksForUserId(long)
	 */
	@Override
	public ArrayList<Stock> getStocksForUserId(long id) {
		List<Stock> stocks = stockRepository.findAll();
		ArrayList<Stock> userStocks = new ArrayList<>();

		for (Stock stock : stocks) {
			if (stock.getUserId() == id) {
				userStocks.add(stock);
			}
		}

		return userStocks;
	}

	/**
	 * Get the users from the database.
	 */
	@Override
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	/**
	 * Test if the stock is already in the database. If it is, don't do
	 * anything. If it is not, insert.
	 */
	@Override
	public void saveStock(Stock stock) {
		List<Stock> stocks = getStocksForUserId(stock.getUserId());
		boolean insert = true;

		for (Stock item : stocks) {
			if (stock.getCompanyName().equals(item.getCompanyName())) {
				insert = false;
			}
		}

		if (insert)
			stockRepository.insert(stock);
		else
			stockRepository.updateStockPrices(stock);
	}

	@Override
	public void saveUser(User user) {
		userRepository.insert(user);
	}

	@Override
	public void deleteStock(Stock stock) {
		stockRepository.deleteStock(stock);
	}
}
