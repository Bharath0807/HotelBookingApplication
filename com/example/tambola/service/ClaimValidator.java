package com.example.tambola.service;
import java.util.List;

import com.example.tambola.config.GameRule;
import com.example.tambola.domain.Claim;
import com.example.tambola.domain.Ticket;

public class ClaimValidator {

    public boolean validateClaim(Claim claim, List<Integer> announcedNumbers) {

        Ticket ticket = claim.getTicket();
        GameRule rule = claim.getRule();
        int last = claim.getLastAnnouncedNumber();

        // Reject if last number was not announced
        if (!announcedNumbers.contains(last)) {
            return false;
        }

        // Does the rule consider this ticket as a winner?
        if (!rule.isWinner(ticket)) {
            return false;
        }

        // Check if LAST number was needed to complete the winning pattern
        if (!rule.requiresNumberForWin(ticket, last)) {
            return false;
        }

        return true;
    }
}
