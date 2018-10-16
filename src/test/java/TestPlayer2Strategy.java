import java.util.ArrayList;

import junit.framework.TestCase;

public class TestPlayer2Strategy extends TestCase{
	
	Player player = new Player("Player",999,new PlayerStrategy2());
	Tile tile = new Tile(1,12);
	Tile tile1 = new Tile(1,11);
	Tile tile2 = new Tile(1,10);
	Tile tile3 = new Tile(2,3);
	Tile tile4 = new Tile(3,5);
	Tile tile5 = new Tile(2,7);
	Tile tile6 = new Tile(2,9);
	
	Tile t7 = new Tile(1,1);
	Tile t8 = new Tile(2,1);
	Tile t9 = new Tile(3,1);
	Tile t10 = new Tile(4,1);
	
	
	
	public void testFirstMove() {
		player.getHand().addTileToHand(tile3);
		player.getHand().addTileToHand(tile4);
		player.getHand().addTileToHand(tile5);
		player.getHand().addTileToHand(tile6);
		player.play();
		assertTrue(player.getIsFirstMeldComplete() == false);
		assertTrue(player.getHand().getTiles().size() == 4);
		assertTrue(player.getTable().getNumberOfTile() == 0);
		assertTrue(player.isWinner() == false);	
		
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
		// set 111
		player.setIsfirstMeldComplete(true);
		test.add(t7);test.add(t8);test.add(t9);
		player.getTable().addTiles(test);
			
		player.getHand().addTileToHand(tile6);
		player.getHand().addTileToHand(t10);
		player.getHand().addTileToHand(tile);
		player.getHand().addTileToHand(tile1);
		player.getHand().addTileToHand(tile2);
		
		player.play();
		assertTrue(player.getTable().getNumberOfTile() == 4);
		assertTrue(player.getHand().sizeOfHand() == 4);
	}
	
}
