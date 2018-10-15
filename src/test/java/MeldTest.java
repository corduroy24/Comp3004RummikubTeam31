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
		
		///// valid run via adding tile
		PlayerHand h5 = new PlayerHand ("x"); 
		
		h5.addTileToHand(new Tile(1,9));
		h5.addTileToHand(new Tile (1,10));
		h5.addTileToHand(new Tile (1,11));
		h5.addTileToHand(new Tile (1,12));

		Meld meld5 = new Meld (h5.getTiles());
		
		meld5.addTile(new Tile(1,13));
		assertTrue(meld5.checkRun(meld5.getTiles()));
		
		///// in valid run with different colour 
		PlayerHand h2 = new PlayerHand ("x"); 
		
		h2.addTileToHand(new Tile(1,9));
		h2.addTileToHand(new Tile (1,10));
		h2.addTileToHand(new Tile (1,11));
		h2.addTileToHand(new Tile (1,12));

		Meld meld2 = new Meld (h2.getTiles());
		
		meld2.addTile(new Tile(2,13));
		assertFalse(meld2.checkRun(meld2.getTiles()));
		
		//////valid run with different number 
		PlayerHand h3 = new PlayerHand ("x"); 
		h3.addTileToHand(new Tile(1,9));
		h3.addTileToHand(new Tile (1,10));
		h3.addTileToHand(new Tile (1,11));
		h3.addTileToHand(new Tile (1,12));

		Meld meld3 = new Meld (h2.getTiles());
		
		meld3.addTile(new Tile(1,6));
		assertFalse(meld3.checkRun(meld3.getTiles()));
		
		//////invalid run via size 
		PlayerHand h4 = new PlayerHand ("x"); 
		Tile tempTile_1; 
		Tile tempTile_2; 

		h4.addTileToHand(new Tile(1,9));
		h4.addTileToHand(new Tile (1,10));
		h4.addTileToHand(tempTile_1 = new Tile (1,11));
		h4.addTileToHand(tempTile_2 = new Tile (1,12));

		Meld meld4 = new Meld (h4.getTiles());
		
		meld4.removeTile(tempTile_1);
		meld4.removeTile(tempTile_1);

		assertFalse(meld4.checkRun(meld4.getTiles()));
	}
	
	public void testCheckSet() {
		//// valid set 
		PlayerHand h1 = new PlayerHand ("x"); 
		
		h1.addTileToHand(new Tile(1,9));
		h1.addTileToHand(new Tile (2,9));
		h1.addTileToHand(new Tile (3,9));
		h1.addTileToHand(new Tile (4,9));

		Meld meld1 = new Meld (h1.getTiles());
		
		assertTrue(meld1.checkSet(meld1.getTiles()));
		
		//// valid set via adding new tile
		PlayerHand h2 = new PlayerHand ("x"); 
		
		h2.addTileToHand(new Tile(1,10));
		h2.addTileToHand(new Tile (2,10));
		h2.addTileToHand(new Tile (3,10));

		Meld meld2 = new Meld (h2.getTiles());
		meld2.addTile(new Tile (4,10));
		assertTrue(meld2.checkSet(meld2.getTiles()));
		
		//// invalid set via size 
		Tile temp_1; 
		PlayerHand h3 = new PlayerHand ("x"); 
		
		h3.addTileToHand(new Tile(1,10));
		h3.addTileToHand(new Tile (2,10));
		h3.addTileToHand(temp_1 = new Tile (3,10));

		Meld meld3 = new Meld (h3.getTiles());
		meld3.removeTile(temp_1);
		assertFalse(meld3.checkSet(meld3.getTiles()));
		
		//// invalid set via colour
		PlayerHand h4 = new PlayerHand ("x"); 
		
		h4.addTileToHand(new Tile(1,10));
		h4.addTileToHand(new Tile (2,10));
		h4.addTileToHand(new Tile (3,10));

		Meld meld4 = new Meld (h4.getTiles());
		meld4.addTile(new Tile (3,10));
		assertFalse(meld3.checkSet(meld4.getTiles()));
		
		//// invalid set via number
		PlayerHand h5 = new PlayerHand ("x"); 
		
		h5.addTileToHand(new Tile(1,10));
		h5.addTileToHand(new Tile (2,10));
		h5.addTileToHand(new Tile (3,10));

		Meld meld5 = new Meld (h5.getTiles());
		meld5.addTile(new Tile (4,11));
		assertFalse(meld3.checkSet(meld5.getTiles()));
	}
}
