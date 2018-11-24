import junit.framework.TestCase;

public class TestPlayer1Strategy extends TestCase{
	
	Player player = new Player("Player",999,new p1());
	//a run of 33.
	Tile tile = new Tile(1,12);
	Tile tile1 = new Tile(1,11);
	Tile tile2 = new Tile(1,10);

	Tile tile3 = new Tile(2,3);
	Tile tile4 = new Tile(3,5);
	Tile tile5 = new Tile(2,7);
	Tile tile6 = new Tile(2,9);
	
	// set of 3 points
	Tile t7 = new Tile(1,1);
	Tile t8 = new Tile(2,1);
	Tile t9 = new Tile(3,1);
	
	//set of 30 points
	Tile t10 = new Tile(2,10);
	Tile t11 = new Tile(3,10);
	Tile t12 = new Tile(4,10);
	
	
	//run of 24 points
	Tile t13 = new Tile(1,9);
	Tile t14 = new Tile(1,8);
	Tile t15 = new Tile(1,7);
	
	//set of 6 points
	Tile t16 = new Tile(1,2);
	Tile t17 = new Tile(2,2);
	Tile t18 = new Tile(3,2);
		
		
	
	
	public void testFirstMove() {
		// test initial meld. 
		// This case doesn't have any meld => false to play
		
		player.getHand().addTileToHand(tile3);
		player.getHand().addTileToHand(tile4);
		player.getHand().addTileToHand(tile5);
		player.getHand().addTileToHand(tile6);
		player.play();
		assertTrue(player.getIsFirstMeldComplete() == false);
		assertTrue(player.getTable().getNumberOfTile() == 0);
		assertTrue(player.isWinner() == false);	
		
		
		// test player players one meld of 30 points (4a1)--- play a single set(8b)
		player = new Player("player",999,new p1());
		player.getHand().addTileToHand(t10);
		player.getHand().addTileToHand(t11);
		player.getHand().addTileToHand(t12);
		player.play();
		assertTrue(player.getTable().getNumberOfTile() == 3);
		assertTrue(player.getIsFirstMeldComplete() == true);
				
		
		// test player plays one meld for more than 30 points (4a2)--- play a single run (8a)
		player = new Player("Player",999,new p1());
		player.getHand().addTileToHand(tile);
		player.getHand().addTileToHand(tile1);
		player.getHand().addTileToHand(tile2);
		player.play();
		assertTrue(player.getIsFirstMeldComplete() == true);
		assertTrue(player.getTable().getNumberOfTile() == 3);
			
		
		//test player plays several melds 30 points (4b1)
		player = new Player("Player",999,new p1());
		player.getHand().addTileToHand(t13);
		player.getHand().addTileToHand(t14);
		player.getHand().addTileToHand(t15);
		player.getHand().addTileToHand(t16);
		player.getHand().addTileToHand(t17);
		player.getHand().addTileToHand(t18);		
		player.play();
		assertTrue(player.getTable().getNumberOfTile() == 6);	
		
		//test player plays several melds more than 30 points (4b2)--- play a mix of runs and sets (8e)
		player = new Player("Player",999,new p1());
		player.getHand().addTileToHand(tile);
		player.getHand().addTileToHand(tile1);
		player.getHand().addTileToHand(tile2);
		player.getHand().addTileToHand(t7);
		player.getHand().addTileToHand(t8);
		player.getHand().addTileToHand(t9);		
		player.play();
		assertTrue(player.getTable().getNumberOfTile() == 6);	
		assertTrue(player.getIsFirstMeldComplete() == true);
		
		// test player win on the first turn (4c)
		player = new Player("Player",999,new p1());
		player.getHand().addTileToHand(tile);
		player.getHand().addTileToHand(tile1);
		player.getHand().addTileToHand(tile2);
		player.play();
		assertTrue(player.getTable().getNumberOfTile() == 3);
		assertTrue(player.isWinner() == true);
		assertTrue(player.getIsFirstMeldComplete() == true);
		
		//test player play several runs (8c)
		player = new Player("Player",999,new p1());
		player.getHand().addTileToHand(tile);
		player.getHand().addTileToHand(tile1);
		player.getHand().addTileToHand(tile2);
		player.getHand().addTileToHand(t13);
		player.getHand().addTileToHand(t14);
		player.getHand().addTileToHand(t15);		
		player.play();
		assertTrue(player.getTable().getNumberOfTile() == 6);	
		assertTrue(player.getIsFirstMeldComplete() == true);
		
		//test player play several sets (8d)
		player = new Player("Player",999,new p1());
		player.getHand().addTileToHand(t10);
		player.getHand().addTileToHand(t11);
		player.getHand().addTileToHand(t12);
		player.getHand().addTileToHand(t16);
		player.getHand().addTileToHand(t17);
		player.getHand().addTileToHand(t18);		
		player.play();
		assertTrue(player.getTable().getNumberOfTile() == 6);	
		assertTrue(player.getIsFirstMeldComplete() == true);	
	}
	
	public void testAfterFirstMove() {
		player = new Player("Player",999,new p1());
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
		
		player = new Player("Player",999,new p1());
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
