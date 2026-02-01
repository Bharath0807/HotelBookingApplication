package com.hotel.app.domain;

import java.math.BigDecimal;

public class AllocationResult {

	private int usagePremium;
	private int usageEconomy;
	private BigDecimal revenuePremium;
	private BigDecimal revenueEconomy;

	public AllocationResult(int premiumOccupied, int economyOccupied, BigDecimal revenuePremium,
			BigDecimal revenueEconomy) {
		this.usagePremium = premiumOccupied;
		this.usageEconomy = economyOccupied;
		this.revenuePremium = revenuePremium;
		this.revenueEconomy = revenueEconomy;

	}

	public int getUsagePremium() {
		return usagePremium;
	}

	public void setUsagePremium(int usagePremium) {
		this.usagePremium = usagePremium;
	}

	public int getUsageEconomy() {
		return usageEconomy;
	}

	public void setUsageEconomy(int usageEconomy) {
		this.usageEconomy = usageEconomy;
	}

	public BigDecimal getRevenuePremium() {
		return revenuePremium;
	}

	public void setRevenuePremium(BigDecimal revenuePremium) {
		this.revenuePremium = revenuePremium;
	}

	public BigDecimal getRevenueEconomy() {
		return revenueEconomy;
	}

	public void setRevenueEconomy(BigDecimal revenueEconomy) {
		this.revenueEconomy = revenueEconomy;
	}

}
