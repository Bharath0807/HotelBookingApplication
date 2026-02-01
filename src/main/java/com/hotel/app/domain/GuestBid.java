package com.hotel.app.domain;

import java.math.BigDecimal;

public class GuestBid {
	private final BigDecimal amount;
	private final GuestType type;

	public GuestBid(BigDecimal amount, GuestType type) {
		this.amount = amount;
		this.type = type;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public GuestType getType() {
		return type;
	}
}
