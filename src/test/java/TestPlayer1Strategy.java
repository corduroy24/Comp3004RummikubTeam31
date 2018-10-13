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
	
	
	public void testFirstMove() {
		player.getHand().addTileToHand(tile3);
		player.getHand().addTileToHand(tile4);
		player.getHand().addTileToHand(tile5);
		player.getHand().addTileToHand(tile6);
		player.getHand().HandReader();
		player.play();
		assertTrue(player.getIsFirstMeldComplete() == false);
		assertTrue(player.getHand().getTiles().size() == 5);
		assertTrue(player.getTable().getNumberOfTile() == 0);
		assertTrue(player.isWinner() == false);	
		
		player.getHand().addTileToHand(tile);
		player.getHand().addTileToHand(tile1);
		player.getHand().addTileToHand(tile2);
		player.getHand().HandReader();
		player.play();
		assertTrue(player.getIsFirstMeldComplete() == true);
		assertTrue(player.getTable().getNumberOfTile() == 3);
		assertTrue(player.isWinner() == false);
		
		
		player = new Player("Player",999,new PlayerStrategy1());
		player.getHand().addTileToHand(tile);
		player.getHand().addTileToHand(tile1);
		player.getHand().addTileToHand(tile2);
		player.getHand().HandReader();
		player.play();
		assertTrue(player.getTable().getNumberOfTile() == 6);
		assertTrue(player.isWinner() == true);
		
		
		
	}

}
