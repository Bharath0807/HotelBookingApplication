package com.hotel.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.app.domain.AllocationResult;
import com.hotel.app.domain.GuestRequest;
import com.hotel.app.service.BookingService;

@RestController
@RequestMapping("/occupancy")
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@PostMapping("")
	public AllocationResult allocate(@RequestBody GuestRequest request) {
		return bookingService.allocateRooms(request.getPremiumRooms(), request.getEconomyRooms(),
				request.getPotentialGuests());
	}
}
