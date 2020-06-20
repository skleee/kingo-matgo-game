package kingoMatgo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

/***
 * Card class
 * @author SKLEE
 *
 */
public class Card {
	
	// Private fields
	private Picture picture;
	private Month month;
	private boolean isFaceUp;

	/***
	 * Card Constructor
	 * @param month		the month of the card
	 * @param picture	the picture of the card
	 */
	public Card(Month month, Picture picture) {
		this.month = month;
		this.picture = picture;
		isFaceUp = true;
	}
	
	// Get picture
	public String getPicture() {
		return picture.printPicture();
	}
	
	// Get month
	public int getMonth() {
		return month.getMonth();
	}
	
	// Flip card
	public void flipCard() {
		isFaceUp = !isFaceUp;
	}
	
	/**
	 * Check if card is matched to any of cards in the table
	 * @param card
	 * @param table
	 * @return
	 */
	public static boolean isMatched(Card card, TableCenter table) {
		for (Card c: table.getCenterCard()) {
			if (c.getMonth() == card.getMonth()) {
				return true;
			}
		}
		return false;
	}	
	/**
	 * Get all matched card from the list
	 * @param card
	 * @param list
	 * @return
	 */
	public ArrayList<Card> matchCard(Card card, ArrayList<Card> list){
		ArrayList<Card> matchedCards = new ArrayList<Card>();
		for (Card c: list) {
			if (c.getMonth() == card.getMonth()) {
				matchedCards.add(c);
			}
		}
		return matchedCards;
	}
	/**
	 * Check if card is in list
	 * @param c
	 * @param list
	 * @return
	 */
	public boolean isInList(Card c, ArrayList<Card> list) {
		for (Card a: list) {
			if (c == a) {
				return true;
			}
		}
		return false;
	}
	
	// To string
	public String toString() {
		String str = "";
		if (isFaceUp) {
			str += picture.printPicture() + " of " + month.printMonth();			
		}
		else {
			str = "Face down";
		}
		return str;
	}	
}
