package com.tedyates;

import java.util.Scanner;

public class Player {

	public Scanner scanner = new Scanner(System.in);

////////////////Constructors////////////////////////

	private int balance;
	private String name;
	private int hand;
	private int bet;
	private Card[] playerHand;
	private int handCount;

	public Player() {
		this.balance = 0;
		this.name = "Dealer";
		this.bet = 0;
		this.hand = 0;
		this.playerHand =  new Card [52];
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
		for (int i = 0; i < this.handCount; i++) {
			Card temp = this.playerHand[i];
			sum += temp.getValue();
		}
		return sum;
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

	public Card[] getPlayerHand() {
		return playerHand;
	}

	public void setPlayerHand(Card[] playerHand) {
		this.playerHand = playerHand;
	}

	public int getHandCount() {
		return handCount;
	}

	public void setHandCount(int handCount) {
		this.handCount = handCount;
	}

	///////////////Big Blackjack Method//////////////

	public int playBlackjack(Player player, Player dealer) {

		//ask for bet

		System.out.println("You have a remaining balance of " + player.getBalance() + ".\nPlease enter your bet: ");
		player.setBet(scanner.nextInt());

		while (player.getBet() > player.getBalance() || player.getBet() < 1) {
			System.out.println("Incorrect bet! must be a positive integer lesser than or equal to your balance of " + player.getBalance());
			System.out.println("Please enter your bet: ");
			player.setBet(scanner.nextInt());
		}

		System.out.println("Player " + player.getName() + " has wagered " + player.getBet());
		player.setBalance(player.getBalance() - player.getBet());

		//give out initial cards

		Deck deck = new Deck();
		deck.shuffle();
		dealer.playerDraw(deck);
		player.playerDraw(deck);
		dealer.playerDraw(deck);
		player.playerDraw(deck);
		System.out.println("Dealer has drawn " + dealer.getHand());
		System.out.println("Player has drawn " + player.getHand());

		//check both players for blackjack

		if (dealer.checkBlackjack()) {
			return dealer.endGame(1, player, dealer);
		}
		if (player.checkBlackjack()) {
			return dealer.endGame(2, player, dealer);
		}

		//let player receive cards till they choose to stand

		System.out.println("Do you want to hit? Press 1 to hit, press 0 to stay");
		int scanInt = scanner.nextInt();
		while (scanInt == 1) {
			player.playerDraw(deck);
			if (player.getHand() > 21) {
				return dealer.endGame(3, player, dealer);
			}
			if (player.getHand() == 21) {
				System.out.println("You've drawn 21!");
				scanInt = 0;
			} else {
				System.out.println("You now have " + player.getHand() + ", do you feel lucky? 1 to hit, 0 to stay");
				scanInt = scanner.nextInt();
			}
		}

		//give dealer cards till they hit 17

		while (dealer.getHand() < 17) {
			dealer.playerDraw(deck);
			System.out.println("Dealer has drawn and now has " + dealer.getHand());
			if (dealer.getHand() > 21) {
				return dealer.endGame(4, player, dealer);
			}
		}

		//determine winner

		if (dealer.getHand() > player.getHand()) {
			return dealer.endGame(5, player, dealer);
		}  else if (dealer.getHand() == player.getHand()) {
			return dealer.endGame(6, player, dealer);
		} else {
			return dealer.endGame(7, player, dealer);
		}
	}

	///////////Additional Methods////////////////////

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

	public void playerDraw(Deck deck) {
		this.playerHand[this.handCount] = deck.drawCard();
		this.handCount++;
	}

	public boolean checkBlackjack() {
		return (this.getHand() == 21);
	}

	public void clearCards() {
		this.playerHand =  new Card [52];
		this.hand = 0;
		this.handCount = 0;
	}

	public int endGame(int result, Player player, Player dealer) {
		switch (result) {
			case 1:
				System.out.println("Dealer has blackjack! You have lost!");
				player.setBet(player.getBet() * -1);
				break;
			case 2:
				System.out.println("You have blackjack! You have won!");
				player.setBet(player.getBet() * 2);
				break;
			case 3:
				System.out.println("You've busted with a total of " + player.getHand() + "!");
				player.setBet(player.getBet() * -1);
				break;
			case 4:
				System.out.println("Dealer has busted with " + dealer.getHand() + ", you have won!");
				break;
			case 5:
				System.out.println("You've lost!");
				player.setBet(player.getBet() * -1);
				break;
			case 6:
				System.out.println("You've tied!");
				player.setBet(0);
				break;
			case 7:
				System.out.println("You've won!");
				break;
		}
		dealer.clearCards();
		player.clearCards();
		return player.getBet();
	}
}
