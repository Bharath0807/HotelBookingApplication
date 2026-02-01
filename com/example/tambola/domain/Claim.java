package com.example.tambola.domain;

import com.example.tambola.config.GameRule;

public class Claim {
    private final Ticket ticket;
    private final GameRule rule;
    private final int lastAnnouncedNumber;

    public Claim(Ticket ticket, GameRule rule, int lastAnnouncedNumber) {
        this.ticket = ticket;
        this.rule = rule;
        this.lastAnnouncedNumber = lastAnnouncedNumber;
    }

    public Ticket getTicket() { return ticket; }
    public GameRule getRule() { return rule; }
    public int getLastAnnouncedNumber() { return lastAnnouncedNumber; }
}
