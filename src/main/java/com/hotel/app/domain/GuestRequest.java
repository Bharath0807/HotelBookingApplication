package com.hotel.app.domain;

import java.math.BigDecimal;
import java.util.List;

public class GuestRequest {

	private int premiumRooms;

	private int economyRooms;

	private List<BigDecimal> potentialGuests;

	public int getPremiumRooms() {
		return premiumRooms;
	}

	public int getEconomyRooms() {
		return economyRooms;
	}

	public List<BigDecimal> getPotentialGuests() {
		return potentialGuests;
	}

}
