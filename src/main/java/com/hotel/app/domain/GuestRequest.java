package com.hotel.app.domain;

import java.math.BigDecimal;
import java.util.List;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class GuestRequest {

	@NotNull(message = "Premium rooms count must be provided")
	@PositiveOrZero(message = "Premium rooms must be zero or greater")
	private Integer premiumRooms;

	@NotNull(message = "Economy rooms count must be provided")
	@PositiveOrZero(message = "Economy rooms must be zero or greater")
	private Integer economyRooms;

	@NotEmpty(message = "Guest bids list cannot be empty")
	@NotEmpty(message = "Guest bids list cannot be empty")
	private List<@NotNull(message = "Bid value cannot be null") @DecimalMin(value = "0.0", inclusive = false, message = "Bid value must be greater than zero") BigDecimal> potentialGuests;

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
