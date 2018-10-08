import java.util.ArrayList;

import junit.framework.TestCase;

public class PlayerHandTest extends TestCase{
	
	public void testSizeOfHand() {
		PlayerHand  hand  = new PlayerHand ("X");
		Deck deck = new Deck(); 
				
		Tile tempTile; 
		hand.addTileToHand(tempTile  = deck.Draw());
		hand.addTileToHand(deck.Draw());
		hand.addTileToHand(deck.Draw());

		assertEquals(3, hand.sizeOfHand()); 
		
		
		hand.playTileFromHand(tempTile);
		
		assertEquals(2, hand.sizeOfHand()); 

	}
	
	public void testEmptyfHand() {
		PlayerHand  hand  = new PlayerHand ("X");
		Deck deck = new Deck(); 
				
		Tile tempTile_1, tempTile_2, tempTile_3; 
		hand.addTileToHand(tempTile_1 = deck.Draw());
		hand.addTileToHand(tempTile_2 = deck.Draw());
		hand.addTileToHand(tempTile_3 = deck.Draw());
		
		
		hand.playTileFromHand(tempTile_1);
		hand.playTileFromHand(tempTile_2);
		hand.playTileFromHand(tempTile_3);

		assertTrue(hand.isEmpty());
	}
	
	public void testPlay() {
		PlayerHand  hand  = new PlayerHand ("X");
		Deck deck = new Deck(); 
		
		
		Tile tempTile; 
		hand.addTileToHand(tempTile  = deck.Draw());
		hand.addTileToHand(deck.Draw());
		hand.addTileToHand(deck.Draw());

		
		hand.playTileFromHand(tempTile);
		
		assertEquals(2, hand.sizeOfHand()); 
		assertFalse(hand.getTiles().contains(tempTile)); 

	}
	
	
	public void testAddTile() {
		PlayerHand  hand  = new PlayerHand ("X");
		Deck deck = new Deck(); 
		
		
		Tile tempTile; 
		hand.addTileToHand(tempTile  = deck.Draw());
		hand.addTileToHand(deck.Draw());
		hand.addTileToHand(deck.Draw());

				
		assertEquals(3, hand.sizeOfHand()); 
		assertTrue(hand.getTiles().contains(tempTile)); 
		assertEquals(tempTile.getNumber(), hand.getTiles().get(0).getNumber()); 

	}

	public void testadd14() {
		Deck deck = new Deck();
		PlayerHand  hand  = new PlayerHand ("X");
		hand.drawFirst14(deck);
		assertEquals(hand.sizeOfHand(),14);
	}
	
	
	public void testSortByNumber() {
		Deck deck = new Deck();
		deck.Shuffle();
		PlayerHand  hand  = new PlayerHand ("X");
		hand.drawFirst14(deck);
		
		//hand.HandReader();
		hand.sortTilesByNumber();
		for(int i = 0; i < hand.sizeOfHand()-1; i++) 
			assertTrue((hand.getTiles().get(i).getNumber() <= hand.getTiles().get(i+1).getNumber()));
		
	}
	
	public void testSortByColour () {
		Deck deck = new Deck();
		deck.Shuffle();
		PlayerHand  hand  = new PlayerHand ("X");
		hand.drawFirst14(deck);
		
		
		hand.HandReader();
		ArrayList <PlayerHand> hands = hand.seperateTilesByColour();
		
		//for(int i = 0; i < hands.sizeOfHand()-1; i++) 
			//assertTrue((hands.get(i).getTiles().get<= hand.getTiles().get(i+1).getNumber()));
	}
	
}
