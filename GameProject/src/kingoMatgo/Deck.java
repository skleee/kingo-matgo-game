package kingoMatgo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
/**
 * A deck of cards
 * @author SKLEE
 *
 */
public class Deck {
	private ArrayList<Card> cards;
	private int numCards; //The number of cards currently in the deck
	// Constructor
	public Deck(int numDeck) {
		this.numCards = numDeck * 48;
		this.cards = new ArrayList<Card>();
		// Creating all cards
		Month[] months = Month.values();		
		for (Month m : months) {
			/*
			 * DoubleJunk (Bright, Animal, Ribbon, Junk)
			 * 1, 3: (1, 0, 1, 2)
			 * 2, 4, 5, 6, 7, 10: (0, 1, 1, 2)
			 * 8: (1, 1, 0, 2)
			 * 9: (0, 1, 1, 2) or (0, 0, 1, 2, 1) Double junk
			 * 11: (1, 0, 0, 2, 1) Double junk
			 * 12: (1, 1, 1, 1) Double junk
			 * */
			switch (m) {
			case JANUARY:
			case MARCH:
				this.cards.add(new Card(m, Picture.BRIGHT));
				this.cards.add(new Card(m, Picture.RIBBON));
				this.cards.add(new Card(m, Picture.JUNK1));
				this.cards.add(new Card(m, Picture.JUNK2));
				break;
			case FEBRUARY:
			case APRIL:
			case MAY:
			case JUNE:
			case JULY:
			case OCTOBER:
				this.cards.add(new Card(m, Picture.ANIMAL));
				this.cards.add(new Card(m, Picture.RIBBON));
				this.cards.add(new Card(m, Picture.JUNK1));
				this.cards.add(new Card(m, Picture.JUNK2));
				break;
			case AUGUST:
				this.cards.add(new Card(m, Picture.BRIGHT));
				this.cards.add(new Card(m, Picture.ANIMAL));
				this.cards.add(new Card(m, Picture.JUNK1));
				this.cards.add(new Card(m, Picture.JUNK2));
				break;
			case SEPTEMBER:
				this.cards.add(new Card(m, Picture.ANIMAL));
				this.cards.add(new Card(m, Picture.RIBBON));
				this.cards.add(new Card(m, Picture.JUNK1));
				this.cards.add(new Card(m, Picture.JUNK2));
				break;
			case NOVEMBER:
				this.cards.add(new Card(m, Picture.BRIGHT));
				this.cards.add(new Card(m, Picture.JUNK1));
				this.cards.add(new Card(m, Picture.JUNK2));
				this.cards.add(new Card(m, Picture.DOUBLEJUNK));
				break;
			case DECEMBER:
				this.cards.add(new Card(m, Picture.BRIGHT));
				this.cards.add(new Card(m, Picture.ANIMAL));
				this.cards.add(new Card(m, Picture.RIBBON));
				this.cards.add(new Card(m, Picture.DOUBLEJUNK));
				break;
			default:
				break;
			}
		}
	}
	
	// Get number of cards
	public int getNumCards() {
		return numCards;
	}
	
	// Set number of cards
	public void setNumCards(int numCards) {
		this.numCards = numCards;
	}

	// Shuffle all cards
	public void shuffle() {
		Collections.shuffle(this.cards);
	}
	
	// Reset deck
	public void resetDeck() {
		this.numCards = 0;
		this.cards.clear();
	}
	
	// Get Random Card
	public Card getCard() {
		shuffle();
		Card top = this.cards.get(0);
		this.cards.remove(0);
		this.numCards--;
		return top;
	}
	
	// Remove card
	public void removeCard(Card card) {
		this.cards.remove(card);
		this.numCards--;
	}

	@Override
	public String toString() {
		return "Deck [cards=" + cards + ",\n numCards=" + numCards + "]";
	}
	
}
