import junit.framework.TestCase;

public class HumanTest extends TestCase{
	
	public void  testTurn() {
		
		HumanPlayer humanPlayer = new HumanPlayer ();
		assertTrue(humanPlayer.getTurn()); 
		humanPlayer.setTurn(false); 
		assertFalse(humanPlayer.getTurn()); 
	}
}
