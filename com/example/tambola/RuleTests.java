package com.example.tambola;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.example.tambola.config.TicketFactory;
import com.example.tambola.config.impl.BottomLineRule;
import com.example.tambola.config.impl.EarlyFiveRule;
import com.example.tambola.config.impl.FullHouseRule;
import com.example.tambola.config.impl.TopLineRule;
import com.example.tambola.domain.Claim;
import com.example.tambola.domain.Dealer;
import com.example.tambola.domain.Ticket;
import com.example.tambola.service.ClaimValidator;
import com.example.tambola.service.DefaultTicketFactory;

//4,16,_,_,48,_,63,76,_ 
//7,_,23,38,_,52,_,_,80 
//9,_,25,_,_,56,64,_,83 
public class RuleTests {

	public static void main(String[] args) {
		RuleTests tests = new RuleTests();

		System.out.println("=== Running Rule Tests (No Framework) ===");

		tests.testTopLine1();
//		tests.testMiddleLine();
		tests.testBottomLine();
		tests.testEarlyFive();
		tests.testFullHouse();

		System.out.println("=== Testing Complete ===");
	}

	// -------------------------
	// UTILITY: Create Ticket
	// -------------------------
	private Ticket makeTicket(int start, String owner) {

		return new Ticket(getTickets(), owner);
	}

	private void printResult(String testName, boolean result) {
		System.out.println(testName + ": " + (result ? "PASS" : "FAIL"));
	}

	// -----------------------------------------------------
	// 1. TOP LINE TESTS
	// -----------------------------------------------------
	public void testTopLine() {
		List<Integer> inputTickets = getTickets();
		List<Integer> annoucedNumbers = getAnnouncedNumbers();
		Dealer dealer = new Dealer();
		TicketFactory factory = new DefaultTicketFactory();

		// Generate tickets
		List<Ticket> tickets = new ArrayList<>();
		Ticket t = factory.createTicket();
		tickets.add(t);
		ClaimValidator validator = new ClaimValidator();

		dealer.addListener(t);
		for (int announcedNumber : annoucedNumbers) {
			dealer.announceNumber(announcedNumber);
		}
		Claim claim = new Claim(t, new TopLineRule(), annoucedNumbers.get(annoucedNumbers.size() - 1));

		boolean accepted = validator.validateClaim(claim, dealer.getAnnouncedNumbers());
		if (accepted)
			System.out.println("Claim Accepted");
		else
			System.out.println("Claim Rejected");

		claim = new Claim(t, new TopLineRule(), annoucedNumbers.get(annoucedNumbers.size() - 1));

		accepted = validator.validateClaim(claim, dealer.getAnnouncedNumbers());

		if (accepted)
			System.out.println("Claim Accepted");
		else
			System.out.println("Claim Rejected");

	}

	public static List<Integer> getTickets() {
		String csvFile = "tickets.txt"; // Replace with your CSV file path
		String line;

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			List<String> records = new ArrayList<>();
			while ((line = br.readLine()) != null) {
				records.add(line); // Store each row as a list of strings
			}

			List<Integer> numbers = records.stream().flatMap(row -> Arrays.stream(row.split(",")))
					.map(s -> s.trim().equals("_") ? null : Integer.parseInt(s.trim())) // convert to Integer
					.collect(Collectors.toList());

			System.out.println(numbers);
			return numbers;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	public static List<Integer> getAnnouncedNumbers() {
		String csvFile = "numbers.txt"; // Replace with your CSV file path
		String line;

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			List<String> records = new ArrayList<>();
			while ((line = br.readLine()) != null) {
				records.add(line); // Store each row as a list of strings
			}

			List<Integer> numbers = records.stream().flatMap(row -> Arrays.stream(row.split(",")))
					.map(s -> s.trim().equals("_") ? null : Integer.parseInt(s.trim())) // convert to Integer
					.collect(Collectors.toList());

			System.out.println(numbers);
			return numbers;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	public void testTopLine1() {
		List<Integer> annoucedNumbers = getAnnouncedNumbers();

		Ticket ticket = makeTicket(1, "P1");

		for (int announceNumber : annoucedNumbers) {
			ticket.onNumberAnnounced(announceNumber);
		}

		ClaimValidator validator = new ClaimValidator();

		Claim claim = new Claim(ticket, new TopLineRule(), annoucedNumbers.get(annoucedNumbers.size() - 1));

		boolean accepted = validator.validateClaim(claim, annoucedNumbers);
		if (accepted)
			System.out.println("Claim Accepted");
		else
			System.out.println("Claim Rejected");
	}

	// -----------------------------------------------------
	// 3. BOTTOM LINE TESTS
	// -----------------------------------------------------
	public void testBottomLine() {
		Ticket ticket = makeTicket(1, "P1");
		BottomLineRule rule = new BottomLineRule();

		for (int i = 11; i <= 14; i++)
			ticket.onNumberAnnounced(i);
		boolean beforeWin = rule.isWinner(ticket);

		ticket.onNumberAnnounced(15);
		boolean afterWin = rule.isWinner(ticket);

		boolean requiresCorrect = rule.requiresNumberForWin(ticket, 15);
		boolean requiresWrong = !rule.requiresNumberForWin(ticket, 2);

		printResult("BottomLine - Winner Check Before", !beforeWin);
		printResult("BottomLine - Winner Check After", afterWin);
		printResult("BottomLine - Requires Correct Last Number", requiresCorrect);
		printResult("BottomLine - Rejects Wrong Last Number", requiresWrong);
	}

	// -----------------------------------------------------
	// 4. EARLY FIVE TESTS
	// -----------------------------------------------------
	public void testEarlyFive() {
		Ticket ticket = makeTicket(1, "P1");
		EarlyFiveRule rule = new EarlyFiveRule();

		// Cross 1–4 first → not yet winner
		for (int i = 1; i <= 4; i++)
			ticket.onNumberAnnounced(i);
		boolean beforeWin = rule.isWinner(ticket);

		// Cross 5 → completes early five
		ticket.onNumberAnnounced(5);
		boolean afterWin = rule.isWinner(ticket);

		boolean requiresCorrect = rule.requiresNumberForWin(ticket, 5);
		boolean requiresWrong = !rule.requiresNumberForWin(ticket, 12);

		printResult("EarlyFive - Winner Check Before", !beforeWin);
		printResult("EarlyFive - Winner Check After", afterWin);
		printResult("EarlyFive - Requires Correct Last Number", requiresCorrect);
		printResult("EarlyFive - Rejects Wrong Last Number", requiresWrong);
	}

	// -----------------------------------------------------
	// 5. FULL HOUSE TESTS
	// -----------------------------------------------------
	public void testFullHouse() {
		Ticket ticket = makeTicket(1, "P1");
		FullHouseRule rule = new FullHouseRule();

		// Cross 1–14
		for (int i = 1; i <= 14; i++)
			ticket.onNumberAnnounced(i);
		boolean beforeWin = rule.isWinner(ticket);

		// Final number completes full house
		ticket.onNumberAnnounced(15);
		boolean afterWin = rule.isWinner(ticket);

		boolean requiresCorrect = rule.requiresNumberForWin(ticket, 15);
		boolean requiresWrong = !rule.requiresNumberForWin(ticket, 4);

		printResult("FullHouse - Winner Check Before", !beforeWin);
		printResult("FullHouse - Winner Check After", afterWin);
		printResult("FullHouse - Requires Correct Last Number", requiresCorrect);
		printResult("FullHouse - Rejects Wrong Last Number", requiresWrong);
	}
}
