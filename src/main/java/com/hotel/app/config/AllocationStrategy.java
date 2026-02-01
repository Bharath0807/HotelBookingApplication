package com.hotel.app.config;

import java.util.List;

import com.hotel.app.domain.AllocationResult;
import com.hotel.app.domain.GuestBid;

public interface AllocationStrategy {
	AllocationResult allocate(List<GuestBid> premiumGuests, List<GuestBid> economyGuests, int premiumRooms,
			int economyRooms);
}
