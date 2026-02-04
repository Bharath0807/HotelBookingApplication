package com.hotel.app.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.app.config.impl.EconomyAllocationStrategy;
import com.hotel.app.config.impl.PremiumAllocationStrategy;
import com.hotel.app.config.impl.UpgradeAllocationStrategy;
import com.hotel.app.domain.AllocationResult;
import com.hotel.app.domain.GuestBid;

@Service
public class BookingService {

	@Autowired
	private GuestClassifier guestClassifier;

	@Autowired
	private PremiumAllocationStrategy premiumStrategy;

	@Autowired
	private EconomyAllocationStrategy economyStrategy;

	@Autowired
	private UpgradeAllocationStrategy upgradeStrategy;

	public AllocationResult allocateRooms(int premiumRooms, int economyRooms, List<BigDecimal> bids) {

		List<GuestBid> premiumGuests = guestClassifier.classifyPremium(bids);
		List<GuestBid> economyGuests = guestClassifier.classifyEconomy(bids);


		AllocationResult premiumResult = premiumStrategy.allocate(premiumGuests, economyGuests, premiumRooms,
				economyRooms);
		AllocationResult economyResult = economyStrategy.allocate(premiumGuests, economyGuests, premiumRooms,
				economyRooms);

		int premiumOccupied = premiumResult.getUsagePremium();
		int economyOccupied = economyResult.getUsageEconomy();

		BigDecimal premiumRevenue = premiumResult.getRevenuePremium();
		BigDecimal economyRevenue = economyResult.getRevenueEconomy();

		// Smart upgrade logic
		int freePremium = premiumRooms - premiumOccupied;
		if (freePremium > 0 && economyGuests.size() > economyRooms) {
			AllocationResult upgradeResult = upgradeStrategy.upgrade(premiumGuests,economyGuests,premiumRooms , economyRooms);

			premiumOccupied = upgradeResult.getUsagePremium();
			economyOccupied = upgradeResult.getUsageEconomy();
			premiumRevenue = premiumRevenue.add(upgradeResult.getRevenuePremium());
			economyRevenue = upgradeResult.getRevenueEconomy();
		}

		return new AllocationResult(premiumOccupied, economyOccupied, premiumRevenue, economyRevenue);
	}

}