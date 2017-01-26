package org.tradingapp.rest;

/**
 * This is the rest endpoint to get stock information.
 * 
 * @author Biju
 *
 */
public class StockRest {

	private final String stockId;
	private final String companyName;
	private final String userName;

	public StockRest(String stockId, String companyName, String userName) {
		this.stockId = stockId;
		this.companyName = companyName;
		this.userName = userName;
	}

	public String getStockId() {
		return stockId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getUserName() {
		return userName;
	}

}
