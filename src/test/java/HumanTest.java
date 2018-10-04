import junit.framework.TestCase;

public class HumanTest extends TestCase{
	
	public void  testTurn() {
		
		Player humanPlayer = new Player ();
		assertTrue(humanPlayer.getTurn()); 
		humanPlayer.setTurn(False); 
		assertFalse(humanPlayer.getTurn()); 
	}
}
