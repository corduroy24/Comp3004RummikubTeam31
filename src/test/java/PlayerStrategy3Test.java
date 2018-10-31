import java.util.ArrayList;

import junit.framework.TestCase;

public class PlayerStrategy3Test extends TestCase{

	Player player = new Player("Player",999,new PlayerStrategy3());
	Player player2 = new Player("Player2",998,new PlayerStrategy2());
	Player player3 = new Player("Player2",998,new PlayerStrategy2());
	Player player4 = new Player("Player2",998,new PlayerStrategy2());
	
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

	Player opponent = new Player("Player",999,new PlayerStrategy3());
	
	//opponent.getHand().addTileToHand(tile3);

	
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
		
		
		player = new Player("Player",999,new PlayerStrategy3());
		player.getHand().addTileToHand(tile);
		player.getHand().addTileToHand(tile1);
		player.getHand().addTileToHand(tile2);
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
		
		player.play();
		assertTrue(player.getTable().getNumberOfTile() == 6);		
	}
	
	////////////////FUNCTIONS BELOW ARE RESPONSIBLE OVER WHAT IS AFTER INITIAL 30 POINT MOVE
	
	public void testFirstCase() {          //FOR WHEN strategy patern 2 has to be done
		player = new Player("23",23,new PlayerStrategy3());
		player2 = new Player("33",23,new PlayerStrategy2());
		player3 = new Player("3",23,new PlayerStrategy2());
		player4 = new Player("2",23,new PlayerStrategy2());
		
		ArrayList<Tile> test = new ArrayList<Tile>();
		
		player.setIsfirstMeldComplete(true);
		player2.setIsfirstMeldComplete(true);
		player3.setIsfirstMeldComplete(true);
		player4.setIsfirstMeldComplete(true);
		
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
		
		player2.getHand().addTileToHand(t3);
		player3.getHand().addTileToHand(t3);
		player4.getHand().addTileToHand(t3);
		
		player.getHand().addTileToHand(t11);
		player.getHand().addTileToHand(t10);
		player.getHand().addTileToHand(t9);
		player.getHand().addTileToHand(t3);
		player.getHand().addTileToHand(t5);
		player.getHand().addTileToHand(t6);

		player.setEnimies(player2, player3, player4);
		player.play();

		assertTrue(player.getHand().sizeOfHand() == 5);
		assertTrue(player.getTable().getNumberOfTile() == 4);
		assertTrue(player.isWinner() == false);
		
	}

	public void test2ndCase() {  //FOR WHEN strategy patern 2 DOES NOT have to be done
		
		player = new Player("23",23,new PlayerStrategy3());
		player2 = new Player("33",23,new PlayerStrategy2());
		player3 = new Player("3",23,new PlayerStrategy2());
		player4 = new Player("2",23,new PlayerStrategy2());
		
		ArrayList<Tile> test = new ArrayList<Tile>();
		
		player.setIsfirstMeldComplete(true);
		player2.setIsfirstMeldComplete(true);
		player3.setIsfirstMeldComplete(true);
		player4.setIsfirstMeldComplete(true);
		
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
		
		
		
		
		player2.getHand().addTileToHand(t3);
		player2.getHand().addTileToHand(t4);
		player2.getHand().addTileToHand(t5);
		player2.getHand().addTileToHand(t3);
		player2.getHand().addTileToHand(t4);
		player2.getHand().addTileToHand(t5);
		player2.getHand().addTileToHand(t3);
		player2.getHand().addTileToHand(t4);
		player2.getHand().addTileToHand(t5);
     	player2.getHand().addTileToHand(t9);
		player3.getHand().addTileToHand(t10);
		player2.getHand().addTileToHand(t5);
     	player2.getHand().addTileToHand(t9);
		
		
		player.getHand().addTileToHand(t11);
		player.getHand().addTileToHand(t10);
		player.getHand().addTileToHand(t9);
		player.getHand().addTileToHand(t3);
		
		player.setEnimies(player2, player3, player4);
		player.play();
		
		
		assertTrue(player.getHand().sizeOfHand() == 0);
		assertTrue(player.getTable().getNumberOfTile() == 7);
		assertTrue(player.isWinner() == true);
		
	}
	
}
