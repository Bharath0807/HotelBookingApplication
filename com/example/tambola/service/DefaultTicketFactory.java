package com.example.tambola.service;
import java.util.*;

import com.example.tambola.config.TicketFactory;
import com.example.tambola.domain.Ticket;

public class DefaultTicketFactory implements TicketFactory {

    @Override
    public Ticket createTicket() {
        Random rand = new Random();

//        while (nums.size() < 15) // Typical tambola count
//            nums.add(rand.nextInt(90) + 1);

        List<Integer> list = new ArrayList<>();

		list.add(4);
		list.add(16);
		list.add(null);
		list.add(null);
		list.add(48);
		list.add(null);
		list.add(63);
		list.add(76);
		list.add(null);

		list.add(7);
		list.add(null);
		list.add(23);
		list.add(38);
		list.add(null);
		list.add(52);
		list.add(null);
		list.add(null);
		list.add(80);

		list.add(9);
		list.add(null);
		list.add(25);
		list.add(null);
		list.add(null);
		list.add(56);
		list.add(64);
		list.add(null);
		list.add(83);
        return new Ticket(list, "Player" + rand.nextInt(1000));
    }
}
