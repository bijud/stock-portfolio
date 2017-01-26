package org.tradingapp.dao;

/**
 * This class defines the stock object. This is stored in the "stocks" table.
 * 
 * @author Biju
 *
 */
public class Stock {

	private long id;
	private String companyName;
	private double currentPrice;
	private double purchasePrice;
	private String netProfit;
	private long userId;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public double getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}

	public double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public String getNetProfit() {
		return netProfit;
	}

	public void setNetProfit(String netProfit) {
		this.netProfit = netProfit;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Stock [id=" + id + ", companyName=" + companyName + ", currentPrice=" + currentPrice
				+ ", purchasePrice=" + purchasePrice + ", netProfit=" + netProfit + ", userId=" + userId + "]";
	}
}
