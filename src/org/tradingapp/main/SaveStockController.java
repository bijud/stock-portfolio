package org.tradingapp.main;

import java.util.logging.Level;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.tradingapp.dao.Stock;
import org.tradingapp.service.StockService;

import com.sun.istack.internal.logging.Logger;

@Controller
public class SaveStockController {

	@Autowired
	private StockService stockService;

	@RequestMapping(value = "/saveStock", method = RequestMethod.POST)
	public String addStock(@RequestParam(name = "userId") String userId,
			@RequestParam(name = "companyName") String companyName,
			@RequestParam(name = "currentPrice") String currentPrice,
			@RequestParam(name = "purchasePrice") String purchasePrice,
			@RequestParam(name = "netProfit") String netProfit) {
		Stock stock = new Stock();

		stock.setCompanyName(companyName);
		stock.setUserId(Long.valueOf(userId));
		try {
			stock.setCurrentPrice(Double.valueOf(currentPrice));
		} catch (Exception currentPriceException) {
			// ignore, it's ok if value not present.
		}
		try {
			stock.setPurchasePrice(Double.valueOf(purchasePrice));
		} catch (Exception purchasePriceException) {
			Logger.getLogger(SaveStockController.class).log(Level.INFO,
					"Not able to save purchase price:" + purchasePriceException.getMessage());
		}
		stock.setNetProfit(netProfit);

		stockService.saveStock(stock);

		return "index";
	}

	@RequestMapping(value = "/deleteStock", method = RequestMethod.GET)
	public String addStock(@RequestParam(name = "companyName") String companyName) {
		Stock stock = new Stock();
		stock.setCompanyName(companyName);

		stockService.deleteStock(stock);
		
		return "index";
	}

}
