package com.example.tambola.config.impl;

import com.example.tambola.config.GameRule;
import com.example.tambola.domain.Ticket;

public class FullHouseRule implements GameRule {

    @Override
    public boolean isWinner(Ticket ticket) {
        return ticket.hasCrossedAll(ticket.getNumbers());
    }

    @Override
    public String getName() {
        return "Full House";
    }

    @Override
    public boolean requiresNumberForWin(Ticket ticket, int number) {

        if (!ticket.getNumbers().contains(number)) return false;

        // If this number was not the last missing number, claim invalid
        boolean wasIncompleteBefore =
                ticket.getNumbers().stream()
                        .filter(n -> n != number)
                        .anyMatch(n -> !ticket.isNumberCrossed(n));

        long crossedBefore = ticket.getNumbers().stream()
                .filter(n ->  null!=n && n != number)
                .filter(ticket::isNumberCrossed)
                .count();
        long totalInRow = ticket.getNumbers().stream().filter(n->null!=n).count();
        return crossedBefore == totalInRow - 1 && isWinner(ticket);
    }
}
