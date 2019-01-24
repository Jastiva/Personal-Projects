package com.tedyates;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Player {

////////////////Constructors////////////////////////

	private int balance;
	private String name;
	private int bet;
	private Card[] playerHand;
	private int handCount;

	public Player() {
		this.balance = 0;
		this.name = "Dealer";
		this.bet = 0;
		this.playerHand = new Card [52];
		this.handCount = 0;
	}
////////////////Getters and Setters//////////////////

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHand() {
		int sum = 0;
		int aceCount = 0;
		for (int i = 0; i < this.handCount; i++) {
			Card temp = this.playerHand[i];
			if (temp.getValue() == 11) {
				aceCount++;
			}
			sum += temp.getValue();
		}
		for (int j = 0; j < aceCount; j++) {
			if (sum > 21) {
				sum -= 10;
			}
		}
		return sum;
	}

	public int getBet() {
		return bet;
	}

	public void setBet(int bet) {
		this.bet = bet;
	}

	///////////Additional Methods////////////////////

	public static int scanBalance(Player player) {
		System.out.println("Welcome! Please input your desired starting balance:\r");
		return player.checkInt();
	}

	public static String scanName() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter your name:\r");
		return scanner.nextLine();
	}

	/*
	checkInt method was implemented from a solution I found on stackoverflow. The method creates a Scanner, and then
	implements a do while loop. The do while loop creates the int "guess" at least once as the nextInt of the scanner.
	The try/catch loop continues until InputMismatchException reads false (InputMismatchException is a special library
	I imported for this purpose and reads true if the input != the desired input from Scanner). The loop continues to
	prompt the user to enter an int. As soon as InputMismatchException reads false, the loop closes and the function
	returns the value guess.
	 */

	//credit to https://stackoverflow.com/questions/20693859/how-to-stop-exception-when-type-in-wrong-data-type-using-scanner
	public int checkInt() {
		Scanner scanner = new Scanner(System.in);
		do {
			try {
				int guess = scanner.nextInt();
				if (guess >= 0) {
					return guess;
				}
			} catch (InputMismatchException e) {
			}
			System.out.println("Input must be an integer, no doubles, no floats, no strings");
			scanner.nextLine();
		} while (true);
	}

	public void playerDraw(Deck deck) {
		this.playerHand[this.handCount] = deck.drawCard();
		System.out.println("A " + this.playerHand[this.handCount].getValue() + " has been drawn from the deck");
		this.handCount++;
	}

	public boolean checkBlackjack() {
		return (this.getHand() == 21);
	}

	public void clearCards() {
		this.playerHand =  new Card [52];
		this.handCount = 0;
	}


}
