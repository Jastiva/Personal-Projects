package com.tedyates;

public class Card {
	private int value;
	private int faceInt;
	private int suitInt;
	private String face;
	private String suit;

	public Card(int faceInt, int suitInt) {
		this.faceInt = faceInt;
		this.suitInt = suitInt;
		this.value = 0;
		this.face = this.generateFace(this.faceInt);
		this.suit = this.generateSuit(this.suitInt);
	}

	public Card() {
		this.faceInt = 0;
		this.suitInt = 0;
		this.value = 0;
		this.face = "null";
		this.suit = "null";
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

	public String generateFace(int faceInt) {

		if (faceInt > 10 && faceInt < 14) {
			this.setValue(10);
		} else if (faceInt == 1) {
			this.setValue(11);
		} else {
			this.setValue(faceInt);
		}
		switch (faceInt) {
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

	public String generateSuit(int suitInt) {

		switch (suitInt) {
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
