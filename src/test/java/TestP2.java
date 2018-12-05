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
		p.setCanPlay();
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
		p.setCanPlay();
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
		p.setCanPlay();
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
		p.setCanPlay();
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
		p.setCanPlay();
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
		p.setCanPlay();
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
		p.setCanPlay();
		assertTrue(p.play() == true);
		assertTrue(p.getHand().sizeOfHand() == 1);
	}
	/*
	public void testUnPlayedTiles() {
		Player p = new Player("test",1,new p2());
		Tile joker = new Tile(14,14);
		Tile[] l = {new Tile(1,6),new Tile(2,6),
					new Tile(1,5), new Tile(2,5),
					new Tile(3,5),joker, new Tile(1,1)};
		p.getHand().addTilesToHand(l);
		ArrayList<Tile> a = new ArrayList<Tile>();
		
		p.setIsfirstMeldComplete(true);
		assertTrue(p.play() == false);
	}
	
	public void testUnPlayedTiles1() {
		Player p = new Player("test",1,new p2());
		Tile joker = new Tile(14,14);
		Tile[] l = {new Tile(1,6),new Tile(2,7),
					new Tile(1,9), new Tile(2,5),
					new Tile(3,5),joker, new Tile(1,1)};
		p.getHand().addTilesToHand(l);
		ArrayList<Tile> a = new ArrayList<Tile>();
		
		p.setIsfirstMeldComplete(true);
		assertTrue(p.play() == false);
	}
	*/
	/*
	 * Player hand (6,6,6) (5,5,1)
	 * Table (1,1,Joker)
	 * Replace joker with 1 and play all the melds (6,6,6) (5,5,Joker)
	 */
	public void testReUseTable() {
		Player p = new Player("test",1,new p2());
		Tile joker = new Tile(14,14);
		Tile[] l = {new Tile(1,6),new Tile(2,6),
					new Tile(3,6), new Tile(2,5),
					new Tile(3,5), new Tile(1,3)};
		
		Tile[] l1 = {joker, new Tile(1,4), new Tile(1,5)};
		p.setCanPlay();
		joker.setJokerPoint(3);
		joker.setJokerColor(1);
		//joker.setJoker(true);
		p.getHand().addTilesToHand(l);
		
		
		ArrayList<Tile> a = new ArrayList<Tile>();
		a.addAll(Arrays.asList(l1));
		p.getTable().addTiles(a);
		
		p.setIsfirstMeldComplete(true);
		assertTrue(p.play() == true); 
		//assertTrue(p.getHand().sizeOfHand() == 0);
		//assertTrue(p.isWinner() == true);
	}
	
	/*
	 * Player hand (6,6,6) (5,5,1)
	 * Table (1,1,1,Joker)
	 * Can't replace => don't play this case
	 */
	public void testReUseTable1() {
		Player p = new Player("test",1,new p2());
		Tile joker = new Tile(14,14);
		Tile[] l = {new Tile(1,6),new Tile(2,6),
					new Tile(3,6), new Tile(2,5),
					new Tile(3,5), new Tile(1,1)};
		p.setCanPlay();
		Tile[] l1 = {joker, new Tile(2,1), new Tile(3,1), new Tile(1,1)};
		joker.setJokerPoint(1);

		p.getHand().addTilesToHand(l);
		
		ArrayList<Tile> a = new ArrayList<Tile>();
		a.addAll(Arrays.asList(l1));
		p.getTable().addTiles(a);
		
		p.setIsfirstMeldComplete(true);
		assertTrue(p.play() == true);
		
		assertTrue(p.getHand().sizeOfHand() == 0);
		assertTrue(p.isWinner() == true);
	}
	
	/*
	 * Player hand (8,8,8) (5,6,1)
	 * Table (1,1,Joker)
	 * Can replace 1 with joker -> using joker to play 5 6 Joker.
	 */
	public void testReUseTable2() {
		Player p = new Player("test",1,new p2());
		Tile joker = new Tile(14,14);
		Tile[] l = {new Tile(1,8),new Tile(2,8),
					new Tile(3,8), new Tile(2,5),
					new Tile(2,6), new Tile(1,1)};
		p.setCanPlay();
		Tile[] l1 = {joker, new Tile(2,1), new Tile(3,1)};
		joker.setJokerPoint(1);

		p.getHand().addTilesToHand(l);
		
		ArrayList<Tile> a = new ArrayList<Tile>();
		a.addAll(Arrays.asList(l1));
		p.getTable().addTiles(a);
		
		p.setIsfirstMeldComplete(true);
		assertTrue(p.play() == true);
		assertTrue(p.getHand().sizeOfHand() == 0);
		assertTrue(p.isWinner() == true);
	}
	/*
	 * Player hand (5,6,7) (5,3,1)
	 * Table (1,1,Joker)	
	 * Can replace 1 with joker -> using joker to play 3 Joker 5. 
	 * (use for scenario - line 63)
	 */
	public void testReUseTable3() {
		Player p = new Player("test",1,new p2());
		Tile joker = new Tile(14,14);
		Tile[] l = {new Tile(1,5),new Tile(1,6),
					new Tile(1,7), new Tile(3,5),
					new Tile(3,3), new Tile(3,1)};
		p.setCanPlay();
		Tile[] l1 = {joker, new Tile(2,1), new Tile(1,1)};
		joker.setJokerPoint(1);

		p.getHand().addTilesToHand(l);

		ArrayList<Tile> a = new ArrayList<Tile>();
		a.addAll(Arrays.asList(l1));
		p.getTable().addTiles(a);
		
		p.setIsfirstMeldComplete(true);
		assertTrue(p.play() == true);
		assertTrue(p.getHand().sizeOfHand() == 0); p.getHand().HandReader();;
		
	}
	public void testReUseTable4() {
		
		Player p = new Player("test",1,new p2());
		Tile joker = new Tile(14,14);
		Tile[] l = {new Tile(1,5),new Tile(1,6),
					new Tile(1,7), new Tile(3,7),
					new Tile(1,3), new Tile(4,8)};
		p.setCanPlay();
		Tile[] l1 = {joker, new Tile(1,2), new Tile(1,1)};
		Tile[] l2 = { new Tile(3,5),new Tile(3,6), new Tile(3,4)};
		
		joker.setJokerPoint(3);
		joker.setJokerColor(1);
		

		p.getHand().addTilesToHand(l);
		p.getHand().HandReader();
		ArrayList<Tile> a = new ArrayList<Tile>();
		a.addAll(Arrays.asList(l1));
		p.getTable().addTiles(a);
		
		ArrayList<Tile> b = new ArrayList<Tile>();
		b.addAll(Arrays.asList(l2));
		p.getTable().addTiles(b);
		
		p.setIsfirstMeldComplete(true);
		assertTrue(p.play() == true);
		p.getHand().HandReader();
		System.out.println("test---------------------");

		assertTrue(p.getHand().sizeOfHand() == 2);

	}
	public void testReUseTable5() {
		Player p = new Player("test",1,new p2());
		Tile joker = new Tile(14,14);
		Tile[] l = {new Tile(1,5),new Tile(1,6),
					new Tile(1,7), new Tile(4,5),
					new Tile(1,3), new Tile(4,8)};
		
		Tile[] l1 = {joker, new Tile(1,3), new Tile(1,4)};
		Tile[] l2 = { new Tile(3,5),new Tile(2,5), new Tile(1,5)};
		p.setCanPlay();
		joker.setJokerPoint(5);
		joker.setJokerColor(1);
		

		p.getHand().addTilesToHand(l);

		ArrayList<Tile> a = new ArrayList<Tile>();
		a.addAll(Arrays.asList(l1));
		p.getTable().addTiles(a);
		
		ArrayList<Tile> b = new ArrayList<Tile>();
		b.addAll(Arrays.asList(l2));
		p.getTable().addTiles(b);
		
		p.setIsfirstMeldComplete(true);
		assertTrue(p.play() == true);
		p.getHand().HandReader();
		assertTrue(p.getHand().sizeOfHand() == 2);
	}
	public void testReUseTable6() {
		Player p = new Player("test",1,new p2());
		Tile joker = new Tile(14,14);
		Tile[] l = {new Tile(1,5),new Tile(1,6),
					new Tile(1,7), new Tile(4,5),
					new Tile(2,9), new Tile(2,8)};
		p.setCanPlay();
		Tile[] l1 = {joker, new Tile(1,3), new Tile(1,4)};
		Tile[] l2 = { new Tile(2,7),new Tile(2,6), new Tile(2,5)};
		
		joker.setJokerPoint(5);
		joker.setJokerColor(1);
		

		p.getHand().addTilesToHand(l);

		ArrayList<Tile> a = new ArrayList<Tile>();
		a.addAll(Arrays.asList(l1));
		p.getTable().addTiles(a);
		
		ArrayList<Tile> b = new ArrayList<Tile>();
		b.addAll(Arrays.asList(l2));
		p.getTable().addTiles(b);
		
		p.setIsfirstMeldComplete(true);
		assertTrue(p.play() == true);
		p.getHand().HandReader();
		assertTrue(p.isWinner() == true);
	}
	
	public void testReUseTable7() {
		Player p = new Player("test",1,new p2());
		Tile joker = new Tile(14,14);
		Tile[] l = {new Tile(3,4),new Tile(4,4),
					new Tile(1,5),joker};
		p.setCanPlay();
		Tile[] l1 = {new Tile(1,1), new Tile(1,2), new Tile(1,3), new Tile(1,4)};
		Tile[] l2 = { new Tile(1,3),new Tile(2,3), new Tile(3,3),new Tile(4,3)};
		Tile[] l3 = { new Tile(3,3),new Tile(3,4), new Tile(3,5),new Tile(3,6)};
		Tile[] l4 = { new Tile(2,3),new Tile(2,4), new Tile(2,5)};
		Tile[] l5 = { new Tile(1,6),new Tile(2,6), new Tile(3,6)};
		Tile[] l6 = { new Tile(4,1),new Tile(4,2), new Tile(4,3),new Tile(4,4)};
		
		

		p.getHand().addTilesToHand(l);
		
		ArrayList<Tile> a = new ArrayList<Tile>();
		a.addAll(Arrays.asList(l1));
		p.getTable().addTiles(a);
		
		ArrayList<Tile> b = new ArrayList<Tile>();
		b.addAll(Arrays.asList(l2));
		p.getTable().addTiles(b);
		
		ArrayList<Tile> c = new ArrayList<Tile>();
		c.addAll(Arrays.asList(l3));
		p.getTable().addTiles(c);
		
		ArrayList<Tile> d = new ArrayList<Tile>();
		d.addAll(Arrays.asList(l4));
		p.getTable().addTiles(d);
		
		ArrayList<Tile> e = new ArrayList<Tile>();
		e.addAll(Arrays.asList(l5));
		p.getTable().addTiles(e);
		
		ArrayList<Tile> f = new ArrayList<Tile>();
		f.addAll(Arrays.asList(l6));
		p.getTable().addTiles(f);
		//System.out.println(p.getTable().getTable().size());
		p.setIsfirstMeldComplete(true);
		assertTrue(p.play() == true);
		p.getHand().HandReader();
		//assertTrue(p.isWinner() == true);
	}
	
}
