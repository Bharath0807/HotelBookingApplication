package com.hotel.app.config.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import com.hotel.app.config.AllocationStrategy;
import com.hotel.app.domain.AllocationResult;
import com.hotel.app.domain.GuestBid;

@Component("premiumStrategy")
public class PremiumAllocationStrategy implements AllocationStrategy {

	@Override
	public AllocationResult allocate(List<GuestBid> premiumGuests, List<GuestBid> economyGuests, int premiumRooms,
			int economyRooms) {

		int occupied = 0;
		BigDecimal revenue = BigDecimal.ZERO;

		for (GuestBid guest : premiumGuests) {
			if (occupied >= premiumRooms)
				break;
			occupied++;
			revenue = revenue.add(guest.getAmount());
		}

		return new AllocationResult(occupied, 0, revenue, BigDecimal.ZERO);
	}
}
