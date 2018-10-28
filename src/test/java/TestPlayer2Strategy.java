import java.util.ArrayList;

import junit.framework.TestCase;

public class TestPlayer2Strategy extends TestCase{
	
	Player player = new Player("Player",999,new PlayerStrategy2());
	
	// a meld of 33 points
	Tile tile = new Tile(1,12);
	Tile tile1 = new Tile(1,11);
	Tile tile2 = new Tile(1,10);
	
	
	Tile tile3 = new Tile(2,3);
	Tile tile4 = new Tile(3,5);
	Tile tile5 = new Tile(2,7);
	Tile tile6 = new Tile(2,9);
	
	// a meld of 4 points
	Tile t7 = new Tile(1,1);
	Tile t8 = new Tile(2,1);
	Tile t9 = new Tile(3,1);
	Tile t10 = new Tile(4,1);
	
	
	
	public void testFirstMove() {
		// test the invalid first move
		player.getHand().addTileToHand(tile3);
		player.getHand().addTileToHand(tile4);
		player.getHand().addTileToHand(tile5);
		player.getHand().addTileToHand(tile6);
		player.play();
		assertTrue(player.getIsFirstMeldComplete() == false);
		assertTrue(player.getHand().getTiles().size() == 4);
		assertTrue(player.getTable().getNumberOfTile() == 0);
		assertTrue(player.isWinner() == false);	
		// at a 
		player.getHand().addTileToHand(tile);
		player.getHand().addTileToHand(tile1);
		player.getHand().addTileToHand(tile2);
		ArrayList<Tile> a = new ArrayList<Tile>();
		a.add(tile);
		a.add(tile1);
		a.add(tile2);
		player.getTable().addTiles(a);
		player.play();
		assertTrue(player.getIsFirstMeldComplete() == true);
		assertTrue(player.getTable().getNumberOfTile() == 6);
		
		
		player = new Player("Player",999,new PlayerStrategy2());
		player.getHand().addTileToHand(tile);
		player.getHand().addTileToHand(tile1);
		player.getHand().addTileToHand(tile2);
		player.play();
		assertTrue(player.getTable().getNumberOfTile() == 0);
		assertTrue(player.isWinner() == false);
		
		player = new Player("Player",999,new PlayerStrategy2());
		player.getHand().addTileToHand(tile);
		player.getHand().addTileToHand(tile1);
		player.getHand().addTileToHand(tile2);
		player.getHand().addTileToHand(t7);
		player.getHand().addTileToHand(t8);
		player.getHand().addTileToHand(t9);
		
		player.play();
		assertTrue(player.getTable().getNumberOfTile() == 0);		
	}
	
	public void testAfterFirstMove() {
		player = new Player("Player",999,new PlayerStrategy2());
		ArrayList<Tile> test = new ArrayList<Tile>();
		// set 111
		player.setIsfirstMeldComplete(true);
		test.add(t7);test.add(t8);test.add(t9);
		player.getTable().addTiles(test);
			
		player.getHand().addTileToHand(t10);
		player.getHand().addTileToHand(tile);
		player.getHand().addTileToHand(tile1);
		player.getHand().addTileToHand(tile2);
		
		player.play();
		assertTrue(player.getTable().getNumberOfTile() == 7);
		assertTrue(player.getHand().sizeOfHand() == 0);
		assertTrue(player.isWinner() == true);
		
		player = new Player("Player",999,new PlayerStrategy2());
		test = new ArrayList<Tile>();
		player.setIsfirstMeldComplete(true);
		// set 111
		
		test.add(t7);test.add(t8);test.add(t9);
		player.getTable().addTiles(test);
			
		player.getHand().addTileToHand(tile6);
		player.getHand().addTileToHand(t10); // 1 
		player.getHand().addTileToHand(tile);
		player.getHand().addTileToHand(tile1);
		player.getHand().addTileToHand(tile2);
		
		player.play();
		assertFalse(player.getTable().getNumberOfTile() != 4);
		assertFalse(player.getHand().sizeOfHand() != 4);
	}
	
	
	public void testFirstCase() {
		player = new Player("69",69,new PlayerStrategy2());
		ArrayList<Tile> test = new ArrayList<Tile>();
		player.setIsfirstMeldComplete(true);
		Tile t = new Tile(1,7);
		Tile t1 = new Tile(2,7);
		Tile t2 = new Tile(3,7);
		//set of 5
		test.add(t);test.add(t2);test.add(t1);
		player.getTable().addTiles(test);
		
		Tile t3 = new Tile(3,5);
		Tile t4 = new Tile(3,6);
		Tile t5 = new Tile(3,8);
		Tile t6 = new Tile(3,9);
		player.getHand().addTileToHand(t3);
		player.getHand().addTileToHand(t4);
		player.getHand().addTileToHand(t5);
		player.getHand().addTileToHand(t6);
		//player hand 5,6 8,9
		player.play();
		//player.getHand().HandReader();
		assertTrue(player.getHand().sizeOfHand() == 4);
		assertTrue(player.getTable().getNumberOfTile() == 3);
	}
	
	public void testSecondCase() {
		player = new Player("69",69,new PlayerStrategy2());
		ArrayList<Tile> test = new ArrayList<Tile>();
		player.setIsfirstMeldComplete(true);
		Tile t = new Tile(1,7);
		Tile t1 = new Tile(2,7);
		Tile t2 = new Tile(3,7);
		//set of 7
		test.add(t);test.add(t2);test.add(t1);
		player.getTable().addTiles(test);
		
		Tile t3 = new Tile(1,5);
		Tile t4 = new Tile(1,6);
		
		Tile t5 = new Tile(2,5);
		Tile t6 = new Tile(2,6);
		
		Tile t7 = new Tile(3,8);
		Tile t8= new Tile(3,9);
		
		Tile t9 = new Tile(4,10);
		Tile t10 = new Tile(4,11);
		Tile t11 = new Tile(4,12);
		
		Tile t12 = new Tile(2,12);
		player.getHand().addTileToHand(t3);
		player.getHand().addTileToHand(t4);
		player.getHand().addTileToHand(t5);
		player.getHand().addTileToHand(t6);
		player.getHand().addTileToHand(t7);
		player.getHand().addTileToHand(t8);
		player.getHand().addTileToHand(t9);
		player.getHand().addTileToHand(t10);
		player.getHand().addTileToHand(t11);
		player.getHand().addTileToHand(t12);
		
		
		//player hand 5,6 8,9
		player.play();
		player.getHand().HandReader();
		assertTrue(player.getHand().sizeOfHand() == 4);
		assertTrue(player.getTable().getNumberOfTile() == 9);
		assertTrue(player.isWinner() == false);
	}
	
	
	public void testThirdCase() {
		player = new Player("69",69,new PlayerStrategy2());
		ArrayList<Tile> test = new ArrayList<Tile>();
		player.setIsfirstMeldComplete(true);
		Tile t = new Tile(1,7);
		Tile t1 = new Tile(2,7);
		Tile t2 = new Tile(3,7);
		//set of 7
		test.add(t);test.add(t2);test.add(t1);
		player.getTable().addTiles(test);
		
		Tile t3 = new Tile(4,7);
		
		Tile t4 = new Tile(1,6);
		Tile t5 = new Tile(1,5);
		
		Tile t6 = new Tile(2,6);
		Tile t8 = new Tile(3,9);
		Tile t7 = new Tile(3,8);
		
		Tile t9 = new Tile(4,10);
		Tile t10 = new Tile(4,11);
		Tile t11 = new Tile(4,12);
		
		
		
		
		player.getHand().addTileToHand(t3);
		player.getHand().addTileToHand(t4);
		player.getHand().addTileToHand(t5);
		
		player.getHand().addTileToHand(t6);
		player.getHand().addTileToHand(t7);
		player.getHand().addTileToHand(t8);
		
		player.getHand().addTileToHand(t9);
		player.getHand().addTileToHand(t10);
		player.getHand().addTileToHand(t11);
		
		player.play();
		
		assertTrue(player.getHand().sizeOfHand() == 6);
		assertTrue(player.getTable().getNumberOfTile() == 6);
		assertTrue(player.isWinner() == false);
	}
	
	
	
	
	
}
