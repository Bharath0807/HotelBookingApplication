package com.example.tambola.config.impl;

import com.example.tambola.config.GameRule;
import com.example.tambola.domain.Ticket;

public class EarlyFiveRule implements GameRule {

    @Override
    public boolean isWinner(Ticket ticket) {
        return ticket.getNumbers().stream().filter(it->null!=it)
                .filter(ticket::isNumberCrossed)
                .count() >= 5;
    }

    @Override
    public String getName() {
        return "Early Five";
    }

    @Override
    public boolean requiresNumberForWin(Ticket ticket, int number) {

        // If number not even on ticket, can’t complete early five
        if (!ticket.getNumbers().contains(number)) return false;

        // Count BEFORE last number was crossed
        long crossedBefore = ticket.getNumbers().stream()
                .filter(n ->  null!=n && n != number)
                .filter(ticket::isNumberCrossed)
                .count();

        
        // Early five must be *completed* by this number
        long afterCount = crossedBefore + 1;

        return crossedBefore < 5 && afterCount >= 5;
    }
}
