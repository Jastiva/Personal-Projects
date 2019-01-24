package com.tedyates;

public class Blackjack {

	public int playBlackjack(Player player, Player dealer) {

		//ask for bet

		/*
		Use user input to set player bet. Remove bet from player balance before play begins to (1) prevent fraud in game
		and (2) more easily apply the change in balance once the game is complete
		 */

		System.out.println("You have a remaining balance of " + player.getBalance() + ".\nPlease enter your bet: ");
		player.setBet(player.checkInt());

		while (player.getBet() > player.getBalance() || player.getBet() < 1) {
			System.out.println("Incorrect bet! must be a positive integer lesser than or equal to your balance of " + player.getBalance());
			System.out.println("Please enter your bet: ");
			player.setBet(player.checkInt());
		}

		System.out.println("Player " + player.getName() + " has wagered " + player.getBet());
		player.setBalance(player.getBalance() - player.getBet());

		//give out initial cards

		/*
		Create a new deck each round to prevent memory problems and possible bugs with card repeats. Shuffle deck and
		draw out 2 cards for each player.
		 */

		Deck deck = new Deck();
		deck.shuffle();
		player.playerDraw(deck);
		dealer.playerDraw(deck);
		player.playerDraw(deck);
		dealer.playerDraw(deck);
		System.out.println("Dealer has drawn " + dealer.getHand());
		System.out.println("Player has drawn " + player.getHand());

		//check both players for blackjack

		if (dealer.checkBlackjack()) {
			return this.endGame(1, player, dealer);
		}
		if (player.checkBlackjack()) {
			return this.endGame(2, player, dealer);
		}

		//let player receive cards till they choose to stand

		System.out.println("Do you want to hit? Press 1 to hit, press 0 to stay, or press 2 to double down");
		int scanInt = player.checkInt();
		while (scanInt > 2){
			System.out.println("Incorrect value! Press 1 to hit, press 0 to stay, or press 2 to double down");
			scanInt = player.checkInt();
		}
		while (scanInt != 0) {

			player.playerDraw(deck);
			if (scanInt == 2) {
				player.setBet(player.getBet() * 2);
				scanInt = 0;
			}
			if (player.getHand() > 21) {
				return this.endGame(3, player, dealer);
			}
			if (player.getHand() == 21) {
				System.out.println("You've drawn 21!");
				scanInt = 0;
			}
			if (scanInt != 0) {
				System.out.println("You now have " + player.getHand() + ", do you feel lucky? 1 to hit, 0 to stay");
				scanInt = player.checkInt();
			}
		}

		//give dealer cards till they hit 17

		while (dealer.getHand() < 17) {
			dealer.playerDraw(deck);
			System.out.println("Dealer has drawn and now has " + dealer.getHand());
			if (dealer.getHand() > 21) {
				return this.endGame(4, player, dealer);
			}
		}

		//determine winner

		if (dealer.getHand() > player.getHand()) {
			return this.endGame(5, player, dealer);
		}  else if (dealer.getHand() == player.getHand()) {
			return this.endGame(6, player, dealer);
		} else {
			return this.endGame(7, player, dealer);
		}
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
