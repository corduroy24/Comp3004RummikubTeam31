import java.util.ArrayList;
import java.util.Arrays;

import junit.framework.TestCase;

public class TestP2 extends TestCase{
	
	/*
	*Case of  5 6 7, j 10 11. Win this turn p2 play
	*/
	public void testFirstInitialTurn() {
		Player p = new Player("test",1,new p2());
		Tile joker = new Tile(14,14);
		Tile[] l = {new Tile(1,5),new Tile(1,6),new Tile(1,7),
					new Tile(1,10), new Tile(1,11), joker}; 
		p.getHand().addTilesToHand(l);	
		ArrayList<Tile> a = new ArrayList<Tile>();
		
		Tile[] l1 = {new Tile(1,1),new Tile(1,2),new Tile(1,3)};
		a.addAll(Arrays.asList(l1));
		p.getTable().addTiles(a);
				
		assertTrue(p.play() == true);
		assertTrue(p.isWinner() == true);
	}
	
	/*
	 *case of 6 7, J 10 11
	 *p2 play j 10 11 as the first initial turn
	 */
	public void testFirstInitialTurn1() {
		Player p = new Player("test",1,new p2());
		Tile joker = new Tile(14,14);
		Tile[] l = {new Tile(1,6),new Tile(1,7),
					new Tile(1,10), new Tile(1,11), joker};
		p.getHand().addTilesToHand(l);
		ArrayList<Tile> a = new ArrayList<Tile>();
		
		Tile[] l1 = {new Tile(1,1),new Tile(1,2),new Tile(1,3)};
		a.addAll(Arrays.asList(l1));
		p.getTable().addTiles(a);
				
		assertTrue(p.play() == true);
		assertTrue(p.getHand().sizeOfHand() == 2);
	}

	/*
	 * Case of 5 6 7 , 1 2 joker 10
	 * Fail to play
	 */
	public void testFirstInitialTurn2() {
		Player p = new Player("test",1,new p2());
		Tile joker = new Tile(14,14);
		Tile[] l = {new Tile(1,5),new Tile(1,6),new Tile(1,7),
					new Tile(1,2), new Tile(1,3), joker, new Tile(2,10)};
		p.getHand().addTilesToHand(l);
		ArrayList<Tile> a = new ArrayList<Tile>();
		
		Tile[] l1 = {new Tile(1,1),new Tile(1,2),new Tile(1,3)};
		a.addAll(Arrays.asList(l1));
		p.getTable().addTiles(a);
				
		assertTrue(p.play() == false);
		assertTrue(p.getHand().sizeOfHand() == 7);
	}
	
	/*
	 * Case of 5 6 7 , 10 11 joker
	 * Fail to play the first initial turn
	 */
	public void testFirstInitialTurn3() {
		Player p = new Player("test",1,new p2());
		Tile joker = new Tile(14,14);
		Tile[] l = {new Tile(1,6),new Tile(1,7),
					new Tile(1,10), new Tile(1,11), joker};
		p.getHand().addTilesToHand(l);
		ArrayList<Tile> a = new ArrayList<Tile>();
		
		Tile[] l1 = {new Tile(1,1),new Tile(1,2),new Tile(1,3)};
		a.addAll(Arrays.asList(l1));
		//p.getTable().addTiles(a);
				
		assertTrue(p.play() == false);
		assertTrue(p.getHand().sizeOfHand() == 5);
	}
	/*
	 * Case of 5 6 7: 21 Point
	 *  3 4 joker: more than 9 point
	 *  Play 2 runs for the first initial turn
	 */ 
	public void testFirstInitialTurn4() {
		Player p = new Player("test",1,new p2());
		Tile joker = new Tile(14,14);
		Tile[] l = {new Tile(1,5),new Tile(1,6),new Tile(1,7),
					new Tile(2,3), new Tile(2,4), joker, new Tile(1,1)};
		p.getHand().addTilesToHand(l);
		ArrayList<Tile> a = new ArrayList<Tile>();
		
		Tile[] l1 = {new Tile(1,1),new Tile(1,2),new Tile(1,3)};
		a.addAll(Arrays.asList(l1));
		p.getTable().addTiles(a);
				
		assertTrue(p.play() == true);
		assertTrue(p.getHand().sizeOfHand() == 1);
	}
	
	/*
	 * Use joker as a set to play first initial turn
	 */
	public void testFirstInitialTurn5() {
		Player p = new Player("test",1,new p2());
		Tile joker = new Tile(14,14);
		Tile[] l = {new Tile(1,6),new Tile(1,7),
					new Tile(1,11), new Tile(2,11), joker};
		p.getHand().addTilesToHand(l);
		ArrayList<Tile> a = new ArrayList<Tile>();
		
		Tile[] l1 = {new Tile(1,1),new Tile(1,2),new Tile(1,3)};
		a.addAll(Arrays.asList(l1));
		p.getTable().addTiles(a);
				
		assertTrue(p.play() == true);
		assertTrue(p.getHand().sizeOfHand() == 2);
	}
	
	/*
	 * Use joker as a set to play first initial turn
	 *  5 5 5
	 *  6 6 Joker
	 */
	public void testFirstInitialTurn6() {
		Player p = new Player("test",1,new p2());
		Tile joker = new Tile(14,14);
		Tile[] l = {new Tile(1,6),new Tile(2,6),
					new Tile(1,5), new Tile(2,5),
					new Tile(3,5),joker, new Tile(1,1)};
		p.getHand().addTilesToHand(l);
		ArrayList<Tile> a = new ArrayList<Tile>();
		
		Tile[] l1 = {new Tile(1,1),new Tile(1,2),new Tile(1,3)};
		a.addAll(Arrays.asList(l1));
		p.getTable().addTiles(a);
				
		assertTrue(p.play() == true);
		assertTrue(p.getHand().sizeOfHand() == 1);
	}
	
}
