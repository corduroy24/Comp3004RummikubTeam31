import junit.framework.TestCase;

public class PlayerHandTest extends TestCase{
	
	public void testSizeOfHand() {
		PlayerHand  hand  = new PlayerHand ();
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
		PlayerHand  hand  = new PlayerHand ();
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
		PlayerHand  hand  = new PlayerHand ();
		Deck deck = new Deck(); 
		
		
		Tile tempTile; 
		hand.addTileToHand(tempTile  = deck.Draw());
		hand.addTileToHand(deck.Draw());
		hand.addTileToHand(deck.Draw());

		
		hand.playTileFromHand(tempTile);
		
		assertEquals(2, hand.sizeOfHand()); 
		assertFalse(hand.getHand().contains(tempTile)); 

	}
	
	
	public void testAddTile() {
		PlayerHand  hand  = new PlayerHand ();
		Deck deck = new Deck(); 
		
		
		Tile tempTile; 
		hand.addTileToHand(tempTile  = deck.Draw());
		hand.addTileToHand(deck.Draw());
		hand.addTileToHand(deck.Draw());

				
		assertEquals(3, hand.sizeOfHand()); 
		assertTrue(hand.getHand().contains(tempTile)); 
		assertEquals(tempTile.getNumber(), hand.getHand().get(0).getNumber()); 

	}

	
}
