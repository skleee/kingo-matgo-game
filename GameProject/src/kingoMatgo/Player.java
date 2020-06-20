package kingoMatgo;
/***
 * Class for player
 * @author SKLEE
 */

import java.util.ArrayList;

public class Player {
	private ArrayList<Card> hand; // cards in the player's current hand
	private ArrayList<Card> table; // cards in the player's table
	private int numCards; // number of cards in the player's current hand
	
	// Card groups for calculating score
	// Three bird card groups (GoDoRi)
	private Card[] threeBirds = {new Card(Month.FEBRUARY, Picture.ANIMAL), 
			new Card(Month.APRIL, Picture.ANIMAL), new Card(Month.AUGUST, Picture.ANIMAL)};
	// Red ribbon card groups (HongDan)
	private Card[] redRibbon = {new Card(Month.JANUARY, Picture.RIBBON),
			new Card(Month.FEBRUARY, Picture.RIBBON), new Card(Month.MARCH, Picture.RIBBON)};
	// Blue ribbon card groups (CheongDan)
	private Card[] blueRibbon = {new Card(Month.JUNE, Picture.RIBBON),
			new Card(Month.SEPTEMBER, Picture.RIBBON), new Card(Month.OCTOBER, Picture.RIBBON)};
	// Blank ribbon card groups (ChoDan)
	private Card[] blankRibbon = {new Card(Month.APRIL, Picture.RIBBON),
			new Card(Month.MAY, Picture.RIBBON), new Card(Month.JULY, Picture.RIBBON)};
	
	// Constructor
	public Player(Deck deck) {
		super();
		this.hand = new ArrayList<Card>();
		this.table = new ArrayList<Card>();
		
		// Initialization: Get 10 cards each
		for(int i=0; i<10; i++) {
			Card c = deck.getCard();
			this.hand.add(c);
		}
		this.numCards = 10;
	}
	
	// Get hand
	public ArrayList<Card> getHand() {
		return hand;
	}
	
	// Set hand
	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}

	// Get table
	public ArrayList<Card> getTable() {
		return table;
	}
	
	// Get number of cards
	public int getNumCards() {
		return numCards;
	}
	
	// Set number of cards
	public void setNumCards(int numCards) {
		this.numCards = numCards;
	}

	// Add card to hand
	public void addCard(Card card) {
		this.hand.add(card);
	}
	
	// Add card to table
	public void addCardToTable(Card card) {
		this.table.add(card);
	}
	
	// Reset player's state
	public void resetPlayer() {
		this.hand.clear();
		this.table.clear();
		this.numCards = 0;
	}
	
	// Remove card from player's hand
	public void removeCardFromHand(Card card) {
		this.hand.remove(card);
	}
	
	// Get final score
	public int getFinalScore(int score) {
		int tmpScore = score;
		if (MatgoGame.goCnt > 0) {
			tmpScore++;
		}
		return score;
		
	}
	
	// Calculate score
	public int calculateScore() {
		int totalPts = 0;
		int doubleJunkCnt = 0;
		ArrayList<Card> brightCard = new ArrayList<Card>();
		ArrayList<Card> junkCard = new ArrayList<Card>();
		ArrayList<Card> animalCard = new ArrayList<Card>();
		ArrayList<Card> ribbonCard = new ArrayList<Card>();		
		// Sorting in terms of picture
		for (Card c: this.table) {
			if (c.getPicture() == "Bright"){
				brightCard.add(c);
			}
			else if (c.getPicture() == "Junk1" || c.getPicture() == "Junk2" 
					|| c.getPicture() == "DoubleJunk") {
				if (c.getPicture() == "DoubleJunk") {
					doubleJunkCnt++;
				}
				junkCard.add(c);
			}
			else if (c.getPicture() == "Animal"){
				animalCard.add(c);
			}
			else {
				ribbonCard.add(c);
			}
		}	
//		System.out.println(junkCard.size());
//		for (Card c: junkCard) {
//			
//			System.out.println(c);
//		}
		// Bright
		if (brightCard.size() == 3) {		
			Card decB = new Card(Month.DECEMBER, Picture.BRIGHT);
			if (decB.isInList(decB, brightCard) == true) {
				totalPts += 2;
			}
			else {
				totalPts += 3;
			}
			
		}
		else if (brightCard.size() == 4) {
			totalPts += 4;
		}
		else if (brightCard.size() == 5) {
			totalPts += 15;
		}
		
		// Animal
		// Check if 'Three birds(Godori)'
		if (animalCard.size() == 3) {
			boolean flag = true;
			for (Card c: threeBirds) {
				if (c.isInList(c, animalCard) == false) {
					flag = false;
					break;
				}
			}
			if (flag == true) {
				totalPts += 5;				
			}
		}

		if (animalCard.size() >= 5) {
			totalPts += (animalCard.size() - 4);
		}
		
		// Ribbon
		// Red ribbon
		boolean flag = true;
		for (Card c: redRibbon) {
			if (c.isInList(c, ribbonCard) == false) {
				flag = false;
				break;
			}
		}
		if (flag == true) {
			totalPts += 3;				
		}
		// Blue Ribbon
		flag = true;
		for (Card c: blueRibbon) {
			if (c.isInList(c, ribbonCard) == false) {
				flag = false;
				break;
			}
		}
		if (flag == true) {
			totalPts += 3;				
		}
		// Blank ribbon
		flag = true;
		for (Card c: blankRibbon) {
			if (c.isInList(c, ribbonCard) == false) {
				flag = false;
				break;
			}
		}
		if (flag == true) {
			totalPts += 3;				
		}
		
		if (ribbonCard.size() >= 5) {
			totalPts += (ribbonCard.size() - 4);
		}
		
		// JUNK
		if (junkCard.size() +  doubleJunkCnt >= 10) {
			totalPts += (junkCard.size() + doubleJunkCnt - 8);
		}
		return totalPts;
	}

	// Show all cards of hand
	public String showHandCard() {
		String str = "";
		for (Card c: this.hand) {
			str += c.toString() + "\n";
		}
		return str;
	}
	
	@Override
	public String toString() {
		return "Player [hand=" + hand + ", table=" + table + ", numCards=" + numCards + "]";
	}
		
}
