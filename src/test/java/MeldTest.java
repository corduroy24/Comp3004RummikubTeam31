import junit.framework.TestCase;

public class MeldTest extends TestCase {

	public void testInitialMeld() {
		
		PlayerHand hand = new PlayerHand ("x"); 
		Tile t1 = new Tile(1,10); 
		Tile t2 = new Tile(1,11); 
		Tile t3 = new Tile(1,12); 
		Tile t4 = new Tile(1,13); 
		
		hand.addTileToHand(t1);
		hand.addTileToHand(t2);
		hand.addTileToHand(t3);
		hand.addTileToHand(t4);

		Meld meld = new Meld (hand.getTiles());
		
		assertTrue(meld.getStatus());
	}
	
	public void testCheckRun() {
		
		PlayerHand hand = new PlayerHand ("x"); 
		Tile t1 = new Tile(1,10); 
		Tile t2 = new Tile(1,11); 
		Tile t3 = new Tile(1,12); 
		Tile t4 = new Tile(1,13); 
		
		hand.addTileToHand(t1);
		hand.addTileToHand(t2);
		hand.addTileToHand(t3);
		hand.addTileToHand(t4);

		Meld meld = new Meld (hand.getTiles());
		
		AssertTrue(meld.checkRun()); 
		
	}
}
