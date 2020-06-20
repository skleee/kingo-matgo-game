package kingoMatgo;

import java.util.ArrayList;
import java.util.Vector;
/**
 * Class for table center
 * @author SKLEE
 */
public class TableCenter {
	private Vector<Card> centerCard;
	// Constructor
	public TableCenter(Deck deck) {
		this.centerCard = new Vector<Card>();
		// Get 8 cards initially
		for(int i=0; i<8; i++) {
			this.centerCard.add(deck.getCard());
		}
	}
	// Get center card
	public Vector<Card> getCenterCard() {
		return centerCard;
	}
	// Set center card
	public void setCenterCard(Vector<Card> centerCard) {
		this.centerCard = centerCard;
	}
	// To string
	@Override
	public String toString() {
		return "TableCenter [centerCard=" + centerCard + "]";
	}	
	// Add card to the center
	public void addCard(Card c) {
		this.centerCard.add(c);
	}	
	// Remove card from the center
	public void removeCard(Card c) {
		this.centerCard.remove(c);
	}	
	// Find matched card in the center with specific card
	public ArrayList<Card> getMatchedCard(Card card){
		ArrayList<Card> matchedCards = new ArrayList<Card>();
		for (Card c: this.centerCard) {
			if (c.getMonth() == card.getMonth()) {
				matchedCards.add(c);
			}
		}
		return matchedCards;
	}		
}
