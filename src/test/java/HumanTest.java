import junit.framework.TestCase;

public class HumanTest extends TestCase{
	
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
}
