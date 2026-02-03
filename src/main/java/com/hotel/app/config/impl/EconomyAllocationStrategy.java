package com.hotel.app.config.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.hotel.app.config.AllocationStrategy;
import com.hotel.app.domain.AllocationResult;
import com.hotel.app.domain.GuestBid;

@Component("economyStrategy")
public class EconomyAllocationStrategy implements AllocationStrategy {

	@Override
	public AllocationResult allocate(List<GuestBid> premiumGuests, List<GuestBid> economyGuests, int premiumRooms,
			int economyRooms) {

		int occupied = 0;
		BigDecimal revenue = BigDecimal.ZERO;

		for (GuestBid guest : economyGuests) {
			if (occupied >= economyRooms)
				break;
			occupied++;
			revenue = revenue.add(guest.getAmount());
		}
//		for (int i = 0; i < occupied; i++) {
//			economyGuests.remove(0);
//		}
		return new AllocationResult(0, occupied, BigDecimal.ZERO, revenue);
	}
}
