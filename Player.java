package com.tedyates;

import java.util.Scanner;

public class Player {

	public Scanner scanner = new Scanner(System.in);

	private int balance;
	private String name;
	private int hand;
	private int bet;

	public Player(int balance, String name) {
		this.balance = balance;
		this.name = name;
		this.bet = 0;
		this.hand = 0;
	}

	public Player() {
		this.balance = 0;
		this.name = "Dealer";
		this.bet = 0;
		this.hand = 0;
	}

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
		return hand;
	}

	public void setHand(int hand) {
		this.hand = hand;
	}

	public int getBet() {
		return bet;
	}

	public void setBet(int bet) {
		this.bet = bet;
	}

	public int playBlackjack(Player player, Player dealer) {

		//ask for bet

		System.out.println("You have a remaining balance of " + player.getBalance() + ".\nPlease enter your bet: ");
		player.setBet(scanner.nextInt());

		while (player.getBet() > player.getBalance()) {
			System.out.println("Incorrect bet! must be lesser than or equal to your balance of " + player.getBalance());
			player.setBet(scanner.nextInt());
		}

		System.out.println("Player " + player.getName() + " has wagered " + player.getBet());
		player.setBalance(player.getBalance() - player.getBet());

		//give out cards

		Card player1 = new Card();
		Card player2 = new Card();
		Card dealer1 = new Card();
		Card dealer2 = new Card();
		player1.generateFace();
		player2.generateFace();
		dealer1.generateFace();
		dealer2.generateFace();
		player1.generateSuit();
		player2.generateSuit();
		dealer1.generateSuit();
		dealer2.generateSuit();
		System.out.println("Dealer has a " + dealer1.getFace() + " of " + dealer1.getSuit() + " and a " + dealer2.getFace() + " of " + dealer2.getSuit() + " for a total of " + (dealer1.getValue() + dealer2.getValue()));
		System.out.println("Player has a " + player1.getFace() + " of " + player1.getSuit() + " and a " + player2.getFace() + " of " + player2.getSuit() + " for a total of " + (player1.getValue() + player2.getValue()));

		//calculate values for dealer & player

		dealer.setHand(dealer1.getValue() + dealer2.getValue());
		player.setHand(player1.getValue() + player2.getValue());

		//give dealer cards till they hit 17



		//let player receive cards till they don't want any more



		//determine winner

		if (dealer.getHand() > player.getHand()) {
			player.setBet(player.getBet() * -1);
			System.out.println("You've lost!");
		}  else if (dealer.getHand() == player.getHand()) {
			player.setBet(0);
			System.out.println("You've tied!");
		} else {
			System.out.println("You've won!");
		}

		//return result

		System.out.println("Good game, shall we play another?");
		return player.getBet();
	}

	public static int scanBalance() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome! Please input your desired starting balance:\r");
		return scanner.nextInt();
	}

	public static String scanName() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter your name:\r");
		return scanner.nextLine();

	}

}