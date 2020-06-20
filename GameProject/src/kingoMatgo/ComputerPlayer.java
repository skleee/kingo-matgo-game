package kingoMatgo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

/**
 * Class for Computer Player
 * Subclass of Player
 * @author SKLEE
 *
 */
public class ComputerPlayer extends Player {

	// Constructor
	public ComputerPlayer(Deck deck) {
		super(deck);
	}
	
	// Put the selected card on the center
	public Card playCard(TableCenter center) {
		int num = super.getNumCards();
		super.setNumCards(num--);
		
		// Card that match to some card in the table
		Card target;
		ArrayList<Card> tmp = new ArrayList<Card>();
		for (Card c: this.getHand()) {
			if (Card.isMatched(c, center) == true) {
				tmp.add(c);
			}
		}
		// If matched card exists, choose one of them
		if (tmp.size() > 0) {
			Collections.shuffle(tmp);
			target = tmp.get(0);	
		}
		// If no match card, choose any of them
		else {
			target = this.getHand().get(0);
		}	
		
		// Remoce card from hand
		this.removeCardFromHand(target);
		
		return target;
	}

}
