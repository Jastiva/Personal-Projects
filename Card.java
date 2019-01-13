package com.tedyates;

import java.util.Random;

public class Card {
	Random rand = new Random();
	private int value;
	private String  face;
	private String suit;

	public Card(String face, String suit) {
		this.value = 0;
		this.face = "Null";
		this.suit = suit;
	}

	public Card() {
		this.value = 0;
		this.face = "Null";
		this.suit = null;
	}

	public Card(String face) {
		this.value = 0;
		this.face = "Null";
		this.suit = null;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = "Null";
	}

	public String getSuit() {
		return suit;
	}

	public void setSuit(String suit) {
		this.suit = suit;
	}

	public String generateFace() {
		Random rand = new Random();
		int face =  rand.nextInt(12) + 1;
		if (face > 9 && face < 13) {
			this.setValue(10);
		} else {
			this.setValue(face);
		}
		switch (face) {
			case 1:
				this.face = "Ace";
				break;
			case 2:
				this.face = "Two";
				break;
			case 3:
				this.face = "Three";
				break;
			case 4:
				this.face = "Four";
				break;
			case 5:
				this.face = "Five";
				break;
			case 6:
				this.face = "Six";
				break;
			case 7:
				this.face = "Seven";
				break;
			case 8:
				this.face = "Eight";
				break;
			case 9:
				this.face = "Nine";
				break;
			case 10:
				this.face = "Ten";
				break;
			case 11:
				this.face = "Jack";
				break;
			case 12:
				this.face = "Queen";
				break;
			case 13:
				this.face = "King";
				break;
		}
		return this.face;
	}

	public String generateSuit() {
		int temp = rand.nextInt(3) + 1;
		switch (temp) {
			case 1: {
				this.setSuit("Spades");
				break;
			}
			case 2: {
				this.setSuit("Clubs");
				break;
			}
			case 3: {
				this.setSuit("Hearts");
				break;
			}
			case 4: {
				this.setSuit("Diamonds");
				break;
			}
			default: {
				this.setSuit("Error");
				break;
			}
		} return this.getSuit();
	}

}
