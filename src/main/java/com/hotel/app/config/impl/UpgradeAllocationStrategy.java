package com.hotel.app.config.impl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.hotel.app.config.AllocationStrategy;
import com.hotel.app.domain.AllocationResult;
import com.hotel.app.domain.GuestBid;

@Component("upgradeStrategy")
public class UpgradeAllocationStrategy implements AllocationStrategy {

	@Override
	public AllocationResult allocate(List<GuestBid> premiumGuests, List<GuestBid> economyGuests, int premiumRooms, int economyRooms) {
		// This strategy only handles upgrades, so base allocation is zero
		return new AllocationResult(0, 0, BigDecimal.ZERO, BigDecimal.ZERO);
	}


	public AllocationResult upgrade(List<GuestBid> premiumGuests, List<GuestBid> economyGuests, int premiumRooms, int economyRooms) {
		// Sort guests by bid descending
		int premiumAllocated = Math.min(premiumRooms, premiumGuests.size());
		BigDecimal premiumRevenue = premiumGuests.stream()
				.limit(premiumAllocated)
				.map(GuestBid::getAmount)
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		int freePremiumRooms = premiumRooms - premiumAllocated;

		// Upgrade top economy guests to premium if there are free premium rooms
		int upgrades = Math.min(freePremiumRooms, economyGuests.size());
		BigDecimal upgradeRevenue = economyGuests.stream()
				.limit(upgrades)
				.map(GuestBid::getAmount)
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		// The next economy guests fill economy rooms
		int economyAllocated = Math.min(economyRooms, economyGuests.size() - upgrades);
		BigDecimal economyRevenue = economyGuests.stream()
				.skip(upgrades)
				.limit(economyAllocated)
				.map(GuestBid::getAmount)
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		return new AllocationResult(
				premiumAllocated + upgrades,
				economyAllocated,
				upgradeRevenue,
				economyRevenue
		);
	}
}