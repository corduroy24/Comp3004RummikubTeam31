import junit.framework.TestCase;

public class HumanTest extends TestCase{
	
	public void  testTurn() {
		
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
}
