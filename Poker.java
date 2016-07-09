import java.util.ArrayList;
import java.util.Collections;

public class Poker {
	Player player1;
	Player player2;
	final String[] suit = { "CLUB", "DIAMOND", "HEART", "SPADE" };
	final ArrayList<Card> pack = new ArrayList<Card>() {
		{
			for (int i = 0; i < 52; ++i) {
				add(new Card(i % 13 + 1, suit[i / 13]));
			}
		}
	};

	public Poker() {
		Collections.shuffle(pack);
		player1 = new Player(new ArrayList<Card>() {
			{
				for (int i = 0; i < 5; i++) {
					add(pack.get(i));
				}
			}
		});
		player2 = new Player(new ArrayList<Card>() {
			{
				for (int i = 5; i < 10; i++) {
					add(pack.get(i));
				}
			}
		});
	}

	public void result() {

		Player winner, loser;

		if (royalflush(player1) || royalflush(player2)) {
			winner = royalflush(player1) ? player1 : player2;
			loser = royalflush(player2) ? player1 : player2;
			System.out.println("Winner is " + winner.getCards()
					+ " royal flush\nloser is  " + loser.getCards());
			return;
		}

		if (straightflush(player1) || straightflush(player2)) {
			winner = straightflush(player1) ? player1 : player2;
			loser = straightflush(player2) ? player1 : player2;
			System.out.println("Winner is " + winner.getCards()
					+ " straight flush\nloser is  " + loser.getCards());
			return;
		}

		if (fourofakind(player1) || fourofakind(player2)) {
			winner = fourofakind(player1) ? player1 : player2;
			loser = fourofakind(player2) ? player1 : player2;
			System.out.println("Winner is " + winner.getCards()
					+ " four of a kind\nloser is  " + loser.getCards());
			return;
		}

		if (fullhouse(player1) || fullhouse(player2)) {
			winner = fullhouse(player1) ? player1 : player2;
			loser = fullhouse(player2) ? player1 : player2;
			System.out.println("Winner is " + winner.getCards()
					+ " full house\nloser is  " + loser.getCards());
			return;
		}

		if (flush(player1) || flush(player2)) {
			winner = flush(player1) ? player1 : player2;
			loser = flush(player2) ? player1 : player2;
			System.out.println("Winner is " + winner.getCards()
					+ " flush\nloser is  " + loser.getCards());
			return;
		}

		if (straight(player1) || straight(player2)) {
			winner = straight(player1) ? player1 : player2;
			loser = straight(player2) ? player1 : player2;
			System.out.println("Winner is " + winner.getCards()
					+ " straight\nloser is  " + loser.getCards());
			return;
		}

		if (twopair(player1) || twopair(player2)) {
			winner = twopair(player1) ? player1 : player2;
			loser = twopair(player2) ? player1 : player2;
			System.out.println("Winner is " + winner.getCards()
					+ " two pair\nloser is  " + loser.getCards());
			return;
		}

		if (onepair(player1) || onepair(player2)) {
			winner = onepair(player1) ? player1 : player2;
			loser = onepair(player2) ? player1 : player2;
			System.out.println("Winner is " + winner.getCards()
					+ " one pair\nloser is  " + loser.getCards());
			return;
		}

		if (highCard(player1) || highCard(player2)) {
			winner = highCard(player1) ? player1 : player2;
			loser = highCard(player2) ? player1 : player2;
			System.out.println("Winner is " + winner.getCards()
					+ " high card\nloser is  " + loser.getCards());
			return;
		}
	}

	public boolean flush(Player hand) {
		for (int i = 0; i < 4; i++) {
			if (!hand.seq.get(i).getCardSuit()
					.equals(hand.seq.get(i + 1).getCardSuit()))
				return false;
		}

		for (int i = 0; i < 4; ++i)
			if (hand.seq.get(i).getCardNumber()
					- hand.seq.get(i).getCardNumber() != 1)
				return true;

		return false;
	}

	public boolean fourofakind(Player hand) {
		int count = 0;
		int i = 0;

		for (i = 0; i < 4; i++) {
			if (hand.seq.get(i).getCardNumber() == hand.seq.get(i + 1)
					.getCardNumber()) {
				count++;
				i++;
			} else
				i++;

			if (count != 4)
				return false;
		}

		return true;
	}

	public boolean straightflush(Player hand) {
		for (int i = 0; i < 4; i++) {
			if (!(hand.seq.get(i).getCardSuit().equals(hand.seq.get(i + 1)
					.getCardSuit()))
					|| !(hand.seq.get(i).getCardNumber()
							- hand.seq.get(i + 1).getCardNumber() != 1))
				return false;
		}

		return true;
	}

	public boolean royalflush(Player hand) {

		for (int i = 0; i < 4; i++) {
			if (!(hand.seq.get(i).getCardSuit().equals(hand.seq.get(i + 1)
					.getCardSuit())))
				return false;
		}

		ArrayList<Card> royalseq = new ArrayList<Card>();

		royalseq.add(new Card(13, hand.seq.get(0).getCardSuit()));
		royalseq.add(new Card(12, hand.seq.get(0).getCardSuit()));
		royalseq.add(new Card(11, hand.seq.get(0).getCardSuit()));
		royalseq.add(new Card(10, hand.seq.get(0).getCardSuit()));
		royalseq.add(new Card(1, hand.seq.get(0).getCardSuit()));

		for (int i = 0; i < 5; ++i)
			if (royalseq.get(i).compareTo(hand.seq.get(i)) != 0)
				return false;

		return true;

	}

	public boolean straight(Player hand) {
		for (int i = 0; i < 4; i++) {
			if (hand.seq.get(i).getCardNumber()
					- hand.seq.get(i + 1).getCardNumber() != 1)
				return false;
		}

		return true;
	}

	public boolean threeofakind(Player hand) {
		int count = 0;
		int i = 0;
		while (i < 3) {
			if (hand.seq.get(i).getCardNumber() == hand.seq.get(i + 1)
					.getCardNumber()
					&& hand.seq.get(i + 1).getCardNumber() == hand.seq.get(
							i + 2).getCardNumber()) {
				count++;
				i++;
			} else
				i++;
		}

		if (count == 3)
			return true;

		return false;
	}

	public boolean twopair(Player hand) {
		int count = 0;
		int i = 0;
		while (i < 4) {
			if (hand.seq.get(i).getCardNumber() == hand.seq.get(i + 1)
					.getCardNumber()) {
				count++;
				i++;
			} else
				i++;
		}

		if (count == 2)
			return true;

		return false;
	}

	public boolean onepair(Player hand) {
		int count = 0;
		int i = 0;
		while (i < 4) {
			if (hand.seq.get(i).getCardNumber() == hand.seq.get(i + 1)
					.getCardNumber()) {
				count++;
				i++;
			} else
				i++;
		}
		if (count == 1)
			return true;

		return false;
	}

	public boolean fullhouse(Player hand) {
		int count = 0;
		int i = 0;
		while (i < 4) {
			if (hand.seq.get(i).getCardNumber() == hand.seq.get(i + 1)
					.getCardNumber()) {
				count++;
				i++;
			} else
				i++;
		}
		if (count == 3)
			return true;

		return false;
	}

	public boolean highCard(Player hand) {

		return true;
	}
}

