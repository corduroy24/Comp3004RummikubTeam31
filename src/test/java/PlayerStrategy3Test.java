import junit.framework.TestCase;

public class PlayerStrategy3Test extends TestCase{

	Player player; 
	Tile tile = new Tile(1,12);
	Tile tile1 = new Tile(1,11);
	Tile tile2 = new Tile(1,10);
	Tile tile3 = new Tile(2,3);
	Tile tile4 = new Tile(3,5);
	Tile tile5 = new Tile(2,7);
	Tile tile6 = new Tile(2,9);
	Tile tile7 = new Tile(4,6);

	
	Tile t7 = new Tile(1,1);
	Tile t8 = new Tile(2,1);
	Tile t9 = new Tile(3,1);
	
	
	Player opponent = new Player("Player",999,new PlayerStrategy3());
	
	//opponent.getHand().addTileToHand(tile3);
	
	public void testFirstMove() {
		player = new Player("Player",999,new PlayerStrategy1());

		player.getHand().addTileToHand(tile3);
		player.getHand().addTileToHand(tile4);
		player.getHand().addTileToHand(tile5);
		player.getHand().addTileToHand(tile6);
		player.getHand().addTileToHand(tile7);

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
		
		player.play(); 
		assertTrue(player.getIsFirstMeldComplete() == true);
		assertTrue(player.getTable().getNumberOfTile() == 3);
		assertTrue(player.isWinner() == false);
		
		player = new Player("Player",999,new PlayerStrategy3());
		player.getHand().addTileToHand(tile);
		player.getHand().addTileToHand(tile1);
		player.getHand().addTileToHand(tile2);
		player.getHand().HandReader();
		player.play();
		assertTrue(player.getTable().getNumberOfTile() == 3);
		assertTrue(player.isWinner() == true);
		
		player = new Player("Player",999,new PlayerStrategy3());
		player.getHand().addTileToHand(tile);
		player.getHand().addTileToHand(tile1);
		player.getHand().addTileToHand(tile2);
		player.getHand().addTileToHand(t7);
		player.getHand().addTileToHand(t8);
		player.getHand().addTileToHand(t9);
		player.getHand().HandReader();
		System.out.println("hello");
		
		player.play();
		assertTrue(player.getTable().getNumberOfTile() == 3);		
	}
	/*public void testAfterFirstMove() {
		player = new Player("Player",999,new PlayerStrategy2());
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
	}*/

}
