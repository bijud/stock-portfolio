package org.tradingapp.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tradingapp.dao.Stock;
import org.tradingapp.service.StockService;

/**
 * This will need to be refactored when adding more users to the system.
 * 
 * @author Biju
 *
 */
@RestController
public class StockRestController {

	@Autowired
	private StockService StockService;

	@RequestMapping("/getStocks")
	public List<Stock> getStocks() {
		long id = 1; // only 1 user in this case.

		return StockService.getStocksForUserId(id);
	}
}
