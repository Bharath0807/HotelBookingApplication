package com.hotel.app.domain;

import java.math.BigDecimal;
import java.util.List;

public class GuestRequest {
	private int premiumRooms;
	private int economyRooms;
	List<BigDecimal> potentialGuests;

	public int getPremiumRooms() {
		return premiumRooms;
	}

	public void setPremiumRooms(int premiumRooms) {
		this.premiumRooms = premiumRooms;
	}

	public int getEconomyRooms() {
		return economyRooms;
	}

	public void setEconomyRooms(int economyRooms) {
		this.economyRooms = economyRooms;
	}

	public List<BigDecimal> getPotentialGuests() {
		return potentialGuests;
	}

	public void setPotentialGuests(List<BigDecimal> potentialGuests) {
		this.potentialGuests = potentialGuests;
	}

}
