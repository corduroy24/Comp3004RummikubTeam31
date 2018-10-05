import java.util.ArrayList;

import junit.framework.TestCase;

public class TestTable extends TestCase {
	Table table = new Table();
	ArrayList<Tile> tiles;
	
	// first index 1,2,3,4 respectively Red Blue Green Orange
	// second index is value;
	Tile t1 = new Tile(1,2);
	Tile t2 = new Tile(1,3);
	Tile t3 = new Tile(1,4);
	
	Tile t5 = new Tile(1,1);
	Tile t4 = new Tile(2,1);
	Tile t6=  new Tile(3,1);
	Tile t7 = new Tile(4,1);
	
	Tile joker = new Tile();
	Tile joker1 = new Tile();
	
	
	public void testNumberOfTileOnTheTable() {
		assertTrue(table.getNumberOfTile() == 0);
		tiles = new ArrayList<Tile>();
		tiles.add(t1);
		tiles.add(t2);
		tiles.add(t3);
		table.addTiles(tiles);
		assertTrue(table.getNumberOfTile() == 3);
		tiles = new ArrayList<Tile>();
		tiles.add(t4);
		tiles.add(t5);
		tiles.add(t6);
		tiles.add(t7);
		table.addTiles(tiles);
		assertTrue(table.getNumberOfTile() == 7);
	}
	
	public void testJokerSequence() {
		tiles = new ArrayList<Tile>();
		tiles.add(joker);
		tiles.add(t1);
		tiles.add(t3);
		tiles.add(t2);
		table.addTiles(tiles);
		assertTrue(table.getNumberOfTile() == 4);
	}
	
	public void testJokerSet() {
		tiles = new ArrayList<Tile>();
		tiles.add(joker);
		tiles.add(t4);
		tiles.add(t5);
		tiles.add(t6);
		tiles.add(t7);	
		table.addTiles(tiles);
		assertTrue(table.getNumberOfTile() == 0);
	}
	
	
	public void testAddtingMethodCorrectness() {
		table = new Table();
		tiles = new ArrayList<Tile>();
		tiles.add(t4);
		tiles.add(t5);
		tiles.add(t3);
		assertTrue(table.addTiles(tiles) == false);
		tiles.remove(t3);
		tiles.add(t6);
		assertTrue(table.addTiles(tiles) == true);
	}
	
	public void testMoveCard() {
		assertTrue(table.moveTile(1,0,0,0) ==  true);
		assertTrue(table.getSetOrSequences(0).size()  == 4);
		assertTrue(table.getSetOrSequences(1).size()  == 3);
	}
	
	
}
