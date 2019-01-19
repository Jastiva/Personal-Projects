package com.tedyates;

public class Main {

	public static void main(String[] args) {

		/*
		Initialize blackjack object and player objects (player and dealer).
		Use scanBalance method in player class to set the player's balance as an int.
		Use scanName method in player class to set the player's name as a string.
		 */

		Blackjack blackjack = new Blackjack();
		Player player = new Player();
		Player dealer = new Player();
		player.setBalance(Player.scanBalance(player));
		player.setName(Player.scanName());


		/*
		This is the main controller of the program. The whole game runs in a do while loop
		until the player runs out of money. The player's balance is constantly being updated,
		and the actual game is run in this calculation.
		The game is played in the blackjack class in a method called playBlackjack, and the
		player and dealer are passed into the method. This method returns an int that is used
		to modify the players balance. This int will be positive or negative, depending on
		whether they won, lost or tied.
		 */

		do {
			player.setBalance(player.getBalance() + blackjack.playBlackjack(player, dealer));
		} while (player.getBalance() > 0);
		System.out.println("Sorry, you appear to be out of chips!");
	}
}
