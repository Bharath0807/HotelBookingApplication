package com.example.tambola.domain;
import java.util.*;

import com.example.tambola.config.NumberListener;

public class Ticket implements NumberListener {
    private final List<Integer> numbers;
    private final Set<Integer> crossed = new HashSet<>();
    private final String ownerName;

    public Ticket(List<Integer> set, String ownerName) {
        this.numbers = set;
        this.ownerName = ownerName;
    }

    @Override
    public void onNumberAnnounced(int number) {
        if (numbers.contains(number)) {
            crossed.add(number);
        }
    }

    public boolean isNumberCrossed(int number) {
        return crossed.contains(number);
    }

    public boolean hasCrossedAll(List<Integer> required) {
        return crossed.containsAll(required);
    }

	public List<Integer> getNumbers() {
        return numbers;
    }

    public String getOwnerName() {
        return ownerName;
    }
    
    public List<Integer> getTopLine() {
        return new ArrayList<>(numbers).subList(0, numbers.size()/3);
    }

    public List<Integer> getMiddleLine() {
        return new ArrayList<>(numbers).subList(numbers.size()/3, numbers.size()/2);
    }

    public List<Integer> getBottomLine() {
        return new ArrayList<>(numbers).subList(numbers.size()/2, numbers.size());
    }
}
