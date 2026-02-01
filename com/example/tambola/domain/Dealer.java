package com.example.tambola.domain;
import java.util.*;

import com.example.tambola.config.NumberListener;

public class Dealer {
    private final List<NumberListener> listeners = new ArrayList<>();
    private final List<Integer> announcedNumbers = new ArrayList<>();

    public void addListener(NumberListener listener) {
        listeners.add(listener);
    }

    public void announceNumber(int number) {
        System.out.println("Dealer Announces: " + number);
        announcedNumbers.add(number);

        for (NumberListener listener : listeners) {
            listener.onNumberAnnounced(number);
        }
    }

    public List<Integer> getAnnouncedNumbers() {
        return announcedNumbers;
    }
}
