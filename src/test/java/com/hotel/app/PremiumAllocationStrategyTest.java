package com.hotel.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.hotel.app.config.impl.PremiumAllocationStrategy;
import com.hotel.app.domain.AllocationResult;
import com.hotel.app.domain.GuestBid;
import com.hotel.app.domain.GuestType;

class PremiumAllocationStrategyTest {

	private final PremiumAllocationStrategy strategy = new PremiumAllocationStrategy();

	@Test
	void shouldAllocateOnlyPremiumGuests() {
		List<GuestBid> premiumGuests = List.of(new GuestBid(BigDecimal.valueOf(200), GuestType.PREMIUM),
				new GuestBid(BigDecimal.valueOf(150), GuestType.PREMIUM));

		AllocationResult result = strategy.allocate(premiumGuests, List.of(), 1, 0);

		assertEquals(1, result.getUsagePremium());
		assertEquals(BigDecimal.valueOf(200), result.getRevenuePremium());
	}
}
