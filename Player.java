import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Player {
	ArrayList<Card> seq = new ArrayList<Card>();

	public Player(ArrayList<Card> cards) {

		seq.addAll(cards);

		Collections.sort(seq, new Comparator<Card>() {
			public int compare(Card c1, Card c2) {
				if (c1.getCardNumber() > c2.getCardNumber()) {
					return 1;
				} else if (c1.getCardNumber() < c2.getCardNumber()) {
					return -1;
				}
				return 0;
			}
		});
	}

	public ArrayList<Card> getCards() {
		return seq;
	}
}

