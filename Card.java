public class Card {
	int pip;
	String suit;

	Card(int pip, String suit) {
		this.pip = pip;
		this.suit = suit;
	}

	int getCardNumber() {
		return pip;
	}

	String getCardSuit() {
		return suit;
	}

	public int compareTo(Card c2) {
		return (c2.getCardNumber() - this.getCardNumber());
	}

	public String toString() {
		return String.valueOf(this.pip) + " " + this.suit;
	}
}

