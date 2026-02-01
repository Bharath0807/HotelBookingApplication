package com.example.tambola;

import com.example.tambola.config.TicketFactory;
import com.example.tambola.config.impl.TopLineRule;
import com.example.tambola.domain.Claim;
import com.example.tambola.domain.Dealer;
import com.example.tambola.domain.Ticket;
import com.example.tambola.service.ClaimValidator;
import com.example.tambola.service.DefaultTicketFactory;

public class TambolaApp {

	public static void main(String[] args) {

		Dealer dealer = new Dealer();
		TicketFactory factory = new DefaultTicketFactory();

		// Generate tickets
		for (int i = 0; i < 1; i++) {
			Ticket ticket = factory.createTicket();
			ClaimValidator validator = new ClaimValidator();

			dealer.addListener(ticket);
			for (int a : RuleTests.getAnnouncedNumbers()) {
				dealer.announceNumber(a);
			}
			Claim claim = new Claim(ticket, new TopLineRule(),
					RuleTests.getAnnouncedNumbers().get(RuleTests.getAnnouncedNumbers().size() - 1));

			boolean accepted = validator.validateClaim(claim, dealer.getAnnouncedNumbers());

			if (accepted)
				System.out.println("Claim Accepted");
			else
				System.out.println("Claim Rejected");

			claim = new Claim(ticket, new TopLineRule(),
					RuleTests.getAnnouncedNumbers().get(RuleTests.getAnnouncedNumbers().size() - 1));

			accepted = validator.validateClaim(claim, dealer.getAnnouncedNumbers());

			if (accepted)
				System.out.println("Claim Accepted");
			else
				System.out.println("Claim Rejected");
		}

		// Create games with different rules
		System.out.println("Round Completed!");
	}
}
