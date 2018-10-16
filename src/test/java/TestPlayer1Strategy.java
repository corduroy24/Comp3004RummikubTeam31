import junit.framework.TestCase;

public class TestPlayer1Strategy extends TestCase{
	
	Player player = new Player("Player",999,new PlayerStrategy1());
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
		player.play();
		assertTrue(player.getIsFirstMeldComplete() == true);
		assertTrue(player.getTable().getNumberOfTile() == 3);
		assertTrue(player.isWinner() == false);
		
		
		player = new Player("Player",999,new PlayerStrategy1());
		player.getHand().addTileToHand(tile);
		player.getHand().addTileToHand(tile1);
		player.getHand().addTileToHand(tile2);
		player.play();
		assertTrue(player.getTable().getNumberOfTile() == 3);
		assertTrue(player.isWinner() == true);
		
		player = new Player("Player",999,new PlayerStrategy1());
		player.getHand().addTileToHand(tile);
		player.getHand().addTileToHand(tile1);
		player.getHand().addTileToHand(tile2);
		player.getHand().addTileToHand(t7);
		player.getHand().addTileToHand(t8);
		player.getHand().addTileToHand(t9);
		
		player.play();
		assertTrue(player.getTable().getNumberOfTile() == 6);		
	}
	public void testAfterFirstMove() {
		player = new Player("Player",999,new PlayerStrategy1());
		//1 set 111
		player.getHand().addTileToHand(t7);
		player.getHand().addTileToHand(t8);
		player.getHand().addTileToHand(t9);
		
		player.getHand().addTileToHand(tile3);
		player.getHand().addTileToHand(tile4);
		player.getHand().addTileToHand(tile5);
		
		player.setIsfirstMeldComplete(true);
		player.play();
		assertTrue(player.getTable().getNumberOfTile() == 3);
		assertTrue(player.getHand().sizeOfHand() == 3);
		
		player = new Player("Player",999,new PlayerStrategy1());
		//1 set 111
		player.getHand().addTileToHand(t7);
		player.getHand().addTileToHand(t8);
		player.getHand().addTileToHand(t9);
		// 1 sequences
		player.getHand().addTileToHand(tile);
		player.getHand().addTileToHand(tile1);
		player.getHand().addTileToHand(tile2);
		
		
		
		player.getHand().addTileToHand(tile3);
		player.getHand().addTileToHand(tile4);
		player.getHand().addTileToHand(tile5);
		player.setIsfirstMeldComplete(true);
		player.play();
		assertTrue(player.getTable().getNumberOfTile() == 6);
		assertTrue(player.getHand().sizeOfHand() == 3);

	}
	
}
