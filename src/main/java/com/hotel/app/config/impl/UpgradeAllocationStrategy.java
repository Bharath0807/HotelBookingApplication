package com.hotel.app.config.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import com.hotel.app.config.AllocationStrategy;
import com.hotel.app.domain.AllocationResult;
import com.hotel.app.domain.GuestBid;

@Component("upgradeStrategy")
public class UpgradeAllocationStrategy implements AllocationStrategy {

	@Override
	public AllocationResult allocate(List<GuestBid> premiumGuests, List<GuestBid> economyGuests, int premiumRooms,
			int economyRooms) {

		// this strategy only handles upgrades, so base allocation is zero
		return new AllocationResult(0, 0, BigDecimal.ZERO, BigDecimal.ZERO);
	}

	public AllocationResult upgrade(int freePremiumRooms, List<GuestBid> economyGuests, int economyRooms) {

		int upgrades = Math.min(freePremiumRooms, economyGuests.size() - economyRooms);
		int premiumOccupied = 0;
		int economyOccupied = 0;
		BigDecimal premiumRevenue = BigDecimal.ZERO;
		BigDecimal economyRevenue = BigDecimal.ZERO;

		for (int i = 0; i < upgrades; i++) {
			GuestBid guest = economyGuests.get(i);
			premiumOccupied++;
			premiumRevenue = premiumRevenue.add(guest.getAmount());
			economyOccupied--;
			economyRevenue = economyRevenue.subtract(guest.getAmount());
		}

		return new AllocationResult(premiumOccupied, economyOccupied, premiumRevenue, economyRevenue);
	}
}
