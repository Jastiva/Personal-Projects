package com.tedyates;

public class Main {

	public static void main(String[] args) {

		//initialize players
		Player player = new Player();
		player.setBalance(Player.scanBalance());
		player.setName(Player.scanName());
		Player dealer = new Player();

		//play blackjack
		do {
			player.setBalance(player.getBalance() + player.playBlackjack(player, dealer));
		} while (player.getBalance() > 0);
		System.out.println("Sorry, you appear to be out of chips!");
	}
}
