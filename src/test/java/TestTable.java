import java.util.ArrayList;

import junit.framework.TestCase;

public class TestTable extends TestCase {
	Table table = new Table();
	ArrayList<Tile> tiles = new ArrayList<Tile>();
	
	Tile t = new Tile();
	// first index 1,2,3,4 respectively Red Blue Green Orange
	// second index is value;
	Tile t1 = new Tile(1,2);
	Tile t2 = new Tile(1,3);
	Tile t3 = new Tile(1,4);
	
	Tile t4 = new Tile(2,1);
	Tile t5 = new Tile(1,1);
	Tile t6=  new Tile(3,1);
	Tile t7 = new Tile(4,1);
	
	public void testNumberOfTileOnTheTable() {
		assertTrue(table.getNumberOfTile() == 0);
		tiles = new ArrayList<Tile>();
		tiles.add(t1);
		tiles.add(t2);
		tiles.add(t3);
		table.addTiles(tiles);
		assertTrue(table.getNumberOfTile() == 3);
	}
	
	public void testAddtingMethodCorrectness() {
		table = new Table();
		tiles = new ArrayList<Tile>();
		tiles.add(t1);
		tiles.add(t2);
		tiles.add(t4);
		assertTrue(table.addTiles(tiles) == false);
		tiles.remove(t4);
		tiles.add(t3);
		assertTrue(table.addTiles(tiles) == true);
	}
	
	public void testMoveCard() {
		assertTrue(table.moveTile(1,0,0,0) ==  true);
		assertTrue(table.getSetOrSequences(0).size()  == 4);
		assertTrue(table.getSetOrSequences(1).size()  == 4);
	}
	
	
}
