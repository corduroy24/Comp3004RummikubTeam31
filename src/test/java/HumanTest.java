import junit.framework.TestCase;

public class HumanTest extends TestCase{
	
	public void  testTurn() { //tests if its the players turn or not 
		
		HumanPlayer humanPlayer = new HumanPlayer ();
		assertTrue(humanPlayer.getIsTurn()); 
		humanPlayer.setIsTurn(false); 
		assertFalse(humanPlayer.getIsTurn()); 
	}
	
	public void testTileTaken() {
		
		HumanPlayer humanPlayer = new HumanPlayer ();
		assertTrue(humanPlayer.getIsTileTaken()); 
		humanPlayer.setIsTileTaken(false); 
		assertFalse(humanPlayer.getIsTileTaken()); 
	}
	
	public void testTilePlaced() {
		
		HumanPlayer humanPlayer = new HumanPlayer ();
		assertTrue(humanPlayer.getIsTileTaken()); 
		humanPlayer.setIsTileTaken(false); 
		assertFalse(humanPlayer.getIsTileTaken()); 
	}
}
