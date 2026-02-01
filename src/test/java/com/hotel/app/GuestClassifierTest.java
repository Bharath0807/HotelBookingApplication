package com.hotel.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.hotel.app.domain.GuestBid;
import com.hotel.app.service.GuestClassifier;

class GuestClassifierTest {

	private final GuestClassifier classifier = new GuestClassifier();

	@Test
	void shouldClassifyPremiumAndEconomyCorrectly() {
		List<BigDecimal> bids = List.of(BigDecimal.valueOf(50), BigDecimal.valueOf(100), BigDecimal.valueOf(150),
				BigDecimal.valueOf(75));

		List<GuestBid> premium = classifier.classifyPremium(bids);
		List<GuestBid> economy = classifier.classifyEconomy(bids);

		assertEquals(2, premium.size());
		assertEquals(2, economy.size());

		assertEquals(BigDecimal.valueOf(150), premium.get(0).getAmount());
		assertEquals(BigDecimal.valueOf(100), premium.get(1).getAmount());

		assertEquals(BigDecimal.valueOf(75), economy.get(0).getAmount());
		assertEquals(BigDecimal.valueOf(50), economy.get(1).getAmount());
	}
}
