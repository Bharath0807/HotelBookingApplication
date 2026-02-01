package com.hotel.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hotel.app.domain.AllocationResult;
import com.hotel.app.service.BookingService;

@SpringBootTest
class AllocationServiceIntegrationTest {

	@Autowired
	private BookingService service;

	@Test
	void shouldAllocateCorrectlyWithUpgrade() {
		List<BigDecimal> values = Arrays.asList(BigDecimal.valueOf(23), BigDecimal.valueOf(45), BigDecimal.valueOf(155),
				BigDecimal.valueOf(374), BigDecimal.valueOf(22), BigDecimal.valueOf(99.99), BigDecimal.valueOf(100),
				BigDecimal.valueOf(101), BigDecimal.valueOf(115), BigDecimal.valueOf(209));
		AllocationResult result = service.allocateRooms(3, 3, values);

		assertEquals(3, result.getUsagePremium());
		assertEquals(3, result.getUsageEconomy());
		assertEquals(BigDecimal.valueOf(738), result.getRevenuePremium());
		assertEquals(BigDecimal.valueOf(167.99), result.getRevenueEconomy());

		result = service.allocateRooms(7, 5, values);

		assertEquals(6, result.getUsagePremium());
		assertEquals(4, result.getUsageEconomy());
		assertEquals(BigDecimal.valueOf(1054), result.getRevenuePremium());
		assertEquals(BigDecimal.valueOf(189.99), result.getRevenueEconomy());

		result = service.allocateRooms(2, 7, values);

		assertEquals(2, result.getUsagePremium());
		assertEquals(4, result.getUsageEconomy());
		assertEquals(BigDecimal.valueOf(583), result.getRevenuePremium());
		assertEquals(BigDecimal.valueOf(189.99), result.getRevenueEconomy());

	}

	@Test
	void noUpgradeWhenEconomyRoomsZero() {
		AllocationResult result = service.allocateRooms(2, 0, List.of(BigDecimal.valueOf(50), BigDecimal.valueOf(40)));

		assertEquals(0, result.getUsagePremium());
	}

	@Test
	void noUpgradeWhenEconomyGuestsEqualToRooms() {
		AllocationResult result = service.allocateRooms(2, 2, List.of(BigDecimal.valueOf(90), BigDecimal.valueOf(80)));

		assertEquals(0, result.getUsagePremium());
	}

}
