package com.example.tambola.config.impl;
import java.util.List;

import com.example.tambola.config.GameRule;
import com.example.tambola.domain.Ticket;

public class MiddleLineRule implements GameRule {

    @Override
    public boolean isWinner(Ticket ticket) {
        List<Integer> row = ticket.getMiddleLine();
        return row.stream().filter(it->null != it).allMatch(ticket::isNumberCrossed);
    }

    @Override
    public String getName() {
        return "Middle Line";
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

}
