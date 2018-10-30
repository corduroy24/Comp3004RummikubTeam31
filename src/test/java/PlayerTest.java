import junit.framework.TestCase;

public class PlayerTest extends TestCase{
	
	public void  testTurn() { //tests if its the players turn or not 
		
		Player player = new Player ("X", 1, new HumanPlayerStrategy());
		player.setIsTurn(true); 
		assertTrue(player.getIsTurn()); 
		player.setIsTurn(false); 
		assertFalse(player.getIsTurn()); 
	}
	
	public void testTileTaken() { //tests if the player has a taken a tile or not 
		
		Player player = new Player ("X", 1, new HumanPlayerStrategy());
		assertFalse(player.getIsTileTaken()); 
		player.setIsTileTaken(true); 
		assertTrue(player.getIsTileTaken()); 
	}
	
	public void testTilePlaced() { // tests if a player has placed a tile or not 
		
		Player player = new Player ("X", 1, new HumanPlayerStrategy());
		assertFalse(player.getIsTilePlaced()); 
		player.setIsTilePlaced(true); 
		assertTrue(player.getIsTilePlaced()); 
	}
	
	
	public void testInitialHand() {
		Deck deck = new Deck(); 
		Player player = new Player ("X", 1, new HumanPlayerStrategy());
		int initialSizeOfHand = 14; 
		
		for(int i = 0; i < initialSizeOfHand; i++) {
			player.getPlayerHand().addTileToHand(deck.Draw()); 
		}
		
		assertNotNull(player.getPlayerHand().getTiles());  
		assertEquals(initialSizeOfHand, player.getPlayerHand().sizeOfHand());
	}
	
	public void testPlayedAllTiles() {
		Deck deck = new Deck(); 
		Player player = new Player ("X", 1, new HumanPlayerStrategy());
		int initialSizeOfHand = 14; 
		Tile tileToPlay = new Tile();
		for(int i = 0; i < initialSizeOfHand; i++) {
			player.getPlayerHand().addTileToHand(deck.Draw()); 
		}
		double randomD;
		int randomI; 
		for(int i = initialSizeOfHand; i > 0; i--) {
			randomD = Math.random()*(i - 0); 
			randomI = (int)randomD; 
			tileToPlay = player.getPlayerHand().getTiles().get(randomI);
			player.getPlayerHand().playTileFromHand(tileToPlay); 
		}
		
		assertTrue(player.getPlayerHand().getTiles().isEmpty()); 
	}
	
	public void testIsFirstMeld() {
		Player x = new Player("X", 1, new HumanPlayerStrategy());
		int count=0;
		assertEquals(x.getIsFirstMeldComplete(),false);
		count+=30;
		x.setIsfirstMeldComplete(true);
		assertEquals(x.getIsFirstMeldComplete(),true);
	}
	
	/*public void testFirstMeldComplete {
		
	}*/
}
