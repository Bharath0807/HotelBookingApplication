package com.hotel.app.service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import com.hotel.app.domain.GuestBid;
import com.hotel.app.domain.GuestType;
import org.springframework.util.ObjectUtils;

@Component
public class GuestClassifier {

	private static final BigDecimal PREMIUM_THRESHOLD = BigDecimal.valueOf(100);

	public List<GuestBid> classifyPremium(List<BigDecimal> bids) {
		return bids.stream().filter(bid -> !ObjectUtils.isEmpty(bid) && bid.intValue()>0 && bid.compareTo(PREMIUM_THRESHOLD) >= 0).sorted(Comparator.reverseOrder())
				.map(bid -> new GuestBid(bid, GuestType.PREMIUM)).collect(Collectors.toList());
	}

	public List<GuestBid> classifyEconomy(List<BigDecimal> bids) {
		return bids.stream().filter(bid ->!ObjectUtils.isEmpty(bid) && bid.compareTo(BigDecimal.ZERO)>0 && bid.compareTo(PREMIUM_THRESHOLD) < 0).sorted(Comparator.reverseOrder())
				.map(bid -> new GuestBid(bid, GuestType.ECONOMY)).collect(Collectors.toList());
	}
}
