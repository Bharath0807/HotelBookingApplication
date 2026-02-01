package com.example.tambola.config.impl;
import java.util.List;
import java.util.stream.Collectors;

import com.example.tambola.config.GameRule;
import com.example.tambola.domain.Ticket;

public class TopLineRule implements GameRule {

    @Override
    public boolean isWinner(Ticket ticket) {
    	List<Integer> list = ticket.getTopLine();
        return list.stream().filter(it->null != it)
                .allMatch(ticket::isNumberCrossed);
    }

    @Override
    public boolean requiresNumberForWin(Ticket ticket, int number) {
        List<Integer> row = ticket.getTopLine();

        // If number is NOT in this row, it can't complete the win
        if (!row.contains(number)) return false;

        // Check if BEFORE this number, the row was NOT complete
        long crossedBefore = row.stream()
                .filter(n ->  null!=n && n != number)
                .filter(ticket::isNumberCrossed)
                .count();

        // Total numbers in row
        long totalInRow = row.stream().filter(n->null!=n).count();

        // If crossedBefore is exactly totalInRow - 1, then the last number completes the row
        return crossedBefore == totalInRow - 1 && ticket.isNumberCrossed(number);
    }

    @Override
    public String getName() {
        return "Top Line";
    }
}
