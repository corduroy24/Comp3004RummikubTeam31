import junit.framework.TestCase;

public class HumanPlayerTest extends TestCase{
	
	public void  testTurn() { //tests if its the players turn or not 
		
		HumanPlayer humanPlayer = new HumanPlayer ();
		assertTrue(humanPlayer.getIsTurn()); 
		humanPlayer.setIsTurn(false); 
		assertFalse(humanPlayer.getIsTurn()); 
	}
	
	public void testTileTaken() { //tests if the player has a taken a tile or not 
		
		HumanPlayer humanPlayer = new HumanPlayer ();
		assertFalse(humanPlayer.getIsTileTaken()); 
		humanPlayer.setIsTileTaken(true); 
		assertTrue(humanPlayer.getIsTileTaken()); 
	}
	
	public void testTilePlaced() { // tests if a player has placed a tile or not 
		
		HumanPlayer humanPlayer = new HumanPlayer ();
		assertFalse(humanPlayer.getIsTilePlaced()); 
		humanPlayer.setIsTilePlaced(true); 
		assertTrue(humanPlayer.getIsTilePlaced()); 
	}
	
	
	public void testInitialHand() {
		Deck deck = new Deck(); 
		HumanPlayer humanPlayer = new HumanPlayer ();
		int initialSizeOfHand = 14; 
		
		for(int i = 0; i < initialSizeOfHand; i++) {
			humanPlayer.addTileToHand(deck.Draw()); 
		}
		
		assertNotNull(humanPlayer.getPlayerHand());  
		assertEquals(initialSizeOfHand, humanPlayer.sizeOfHand());
	}
	
	public void testPlayedAllTiles() {
		Deck deck = new Deck(); 
		HumanPlayer humanPlayer = new HumanPlayer ();
		int initialSizeOfHand = 14; 
		Tile tileToPlay = new Tile();
		for(int i = 0; i < initialSizeOfHand; i++) {
			humanPlayer.addTileToHand(deck.Draw()); 
		}
		double randomD;
		int randomI; 
		for(int i = initialSizeOfHand; i > 0; i--) {
			randomD = Math.random()*(i - 0); 
			randomI = (int)randomD; 
			tileToPlay = humanPlayer.getPlayerHand().get(randomI);
			humanPlayer.playTileFromHand(tileToPlay); 
		}
		
		assertTrue(humanPlayer.getPlayerHand().isEmpty()); 
		
		
	}
}
