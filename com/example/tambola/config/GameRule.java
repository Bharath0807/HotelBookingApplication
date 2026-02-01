package com.example.tambola.config;

import com.example.tambola.domain.Ticket;

public interface GameRule {
    boolean isWinner(Ticket ticket);
    boolean requiresNumberForWin(Ticket ticket, int number);
    String getName();
}
