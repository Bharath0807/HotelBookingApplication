package com.hotel.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.hotel.app.config.impl.EconomyAllocationStrategy;
import com.hotel.app.domain.AllocationResult;
import com.hotel.app.domain.GuestBid;
import com.hotel.app.domain.GuestType;

class EconomyAllocationStrategyTest {

	private final EconomyAllocationStrategy strategy = new EconomyAllocationStrategy();

	@Test
	void shouldAllocateOnlyEconomyGuests() {
		List<GuestBid> economyGuests = List.of(new GuestBid(BigDecimal.valueOf(80), GuestType.ECONOMY),
				new GuestBid(BigDecimal.valueOf(60), GuestType.ECONOMY));

		AllocationResult result = strategy.allocate(List.of(), economyGuests, 0, 1);

		assertEquals(1, result.getUsageEconomy());
		assertEquals(BigDecimal.valueOf(80), result.getRevenueEconomy());
	}
}
