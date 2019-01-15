package com.tedyates;

import java.util.Random;

public class Deck {

	private Card[] arrayOfCards = new Card[52];
	public int cardcount = 0;

	public Deck() {
		int count = 0;
		this.cardcount = 0;
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 4; j++) {
				arrayOfCards[count++] = new Card(i, j);
			}
		}
	}

	//credit to https://codereview.stackexchange.com/questions/1382/texas-holdem-in-java?rq=1 for this method
	public void shuffle() {
		Random random = new Random();
		int deckLength = arrayOfCards.length;
		for (int i = 0; i < deckLength; i++) {
			int change = i + random.nextInt(deckLength - i);
			Card temp = arrayOfCards[i];
			arrayOfCards[i] = arrayOfCards[change];
			arrayOfCards[change] = temp;
		}
	}

	public Card drawCard() {
		this.cardcount++;
		return arrayOfCards[cardcount-1];
	}
}
