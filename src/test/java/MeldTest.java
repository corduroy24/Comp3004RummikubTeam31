import junit.framework.TestCase;

public class MeldTest extends TestCase {

	public void testInitialMeld() {
		
		PlayerHand hand = new PlayerHand ("x"); 
		hand.addTileToHand(new Tile(1,9));
		hand.addTileToHand(new Tile (1,10));
		hand.addTileToHand(new Tile (1,11));
		hand.addTileToHand(new Tile (1,12));
		

		Meld meld = new Meld (hand.getTiles());
		
		assertTrue(meld.getStatus());
	}
	
	public void testCheckRun() {
		
		//// valid run 
		PlayerHand h1 = new PlayerHand ("x"); 
		
		h1.addTileToHand(new Tile(1,9));
		h1.addTileToHand(new Tile (1,10));
		h1.addTileToHand(new Tile (1,11));
		h1.addTileToHand(new Tile (1,12));

		Meld meld1 = new Meld (h1.getTiles());
		
		assertTrue(meld1.checkRun(meld1.getTiles()));
		
		///// in valid run with different colour 
		PlayerHand h2 = new PlayerHand ("x"); 
		
		h2.addTileToHand(new Tile(1,9));
		h2.addTileToHand(new Tile (1,10));
		h2.addTileToHand(new Tile (1,11));
		h2.addTileToHand(new Tile (1,12));

		Meld meld2 = new Meld (h2.getTiles());
		
		meld2.addTile(new Tile(2,13));
		assertTrue(meld2.checkRun(meld2.getTiles()));
		
		//////invalid run with different number 
		PlayerHand h3 = new PlayerHand ("x"); 
		
		h3.addTileToHand(new Tile(1,9));
		h3.addTileToHand(new Tile (1,10));
		h3.addTileToHand(new Tile (1,11));
		h3.addTileToHand(new Tile (1,12));

		Meld meld3 = new Meld (h2.getTiles());
		
		meld3.addTile(new Tile(1,6));
		assertTrue(meld3.checkRun(meld3.getTiles()));
	}
}
