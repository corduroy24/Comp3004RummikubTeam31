import junit.framework.TestCase;

public class PlayerHandTest extends TestCase{
	
	public void testInitialHand() {
		Deck deck = new Deck(); 
		HumanPlayer humanPlayer = new HumanPlayer ();
		int initialSizeOfHand = 14; 
		
		for(int i = 0; i < initialSizeOfHand; i++) {
			humanPlayer.getHand().addTile(deck.Draw()); 
		}
		
		assertNotNull(humanPlayer.getHand());  
		assertEquals(initialSizeOfHand, humanPlayer.getHand().sizeOfHand());
	}

}
