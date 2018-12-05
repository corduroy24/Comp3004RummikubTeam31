import java.util.ArrayList;
import java.util.Arrays;

import junit.framework.TestCase;

public class TestP3 extends TestCase{
	// can't replace => can't place this time
	public void testReUseTable1() {
		Player p = new Player("test",1,new p3());
		p.setTest(99, 99, 99);
		Tile joker = new Tile(14,14);
		Tile[] l = {new Tile(1,6),new Tile(2,6),
					new Tile(3,6), new Tile(2,5),
					new Tile(3,5), new Tile(1,1)};
		
		Tile[] l1 = {joker, new Tile(2,1), new Tile(3,1), new Tile(1,1)};
		joker.setJokerPoint(1);

		p.getHand().addTilesToHand(l);
		
		ArrayList<Tile> a = new ArrayList<Tile>();
		a.addAll(Arrays.asList(l1));
		p.getTable().addTiles(a);
		
		p.setIsfirstMeldComplete(true);
		p.setCanPlay();
		assertTrue(p.play() == false);
		
		assertTrue(p.getHand().sizeOfHand() == 6);
		assertTrue(p.isWinner() == false);
	}
	
	/*
	 * Player hand (8,8,8) (5,6,1)
	 * Table (1,1,Joker)
	 * Can replace 1 with joker -> using joker to play 5 6 Joker.
	 */
	public void testReUseTable2() {
		Player p = new Player("test",1,new p3());
		p.setTest(99, 99, 99);
		Tile joker = new Tile(14,14);
		Tile[] l = {new Tile(1,8),new Tile(2,8),
					new Tile(3,8), new Tile(2,5),
					new Tile(2,6), new Tile(1,1)};
		
		Tile[] l1 = {joker, new Tile(2,1), new Tile(3,1)};
		joker.setJokerPoint(1);

		p.getHand().addTilesToHand(l);
		
		ArrayList<Tile> a = new ArrayList<Tile>();
		a.addAll(Arrays.asList(l1));
		p.getTable().addTiles(a);
		p.setCanPlay();
		p.setIsfirstMeldComplete(true);
		assertTrue(p.play() == true);
		p.getHand().HandReader();
		System.out.println(p.getTable().getTable());
		assertTrue(p.getHand().sizeOfHand() == 0);
		assertTrue(p.isWinner() == true);
	}
	/*
	 * Player hand (5,6,7) (5,3,1)
	 * Table (1,1,Joker)	
	 * Can replace 1 with joker -> using joker to play 3 Joker 5.
	 */
	public void testReUseTable3() {
		Player p = new Player("test",1,new p3());
		p.setTest(99, 99, 99);
		Tile joker = new Tile(14,14);
		Tile[] l = {new Tile(1,5),new Tile(1,6),
					new Tile(1,7), new Tile(3,5),
					new Tile(3,3), new Tile(3,1)};
		
		Tile[] l1 = {joker, new Tile(2,1), new Tile(1,1)};
		joker.setJokerPoint(1);

		p.getHand().addTilesToHand(l);
		p.setCanPlay();
		ArrayList<Tile> a = new ArrayList<Tile>();
		a.addAll(Arrays.asList(l1));
		p.getTable().addTiles(a);
		
		p.setIsfirstMeldComplete(true);
		assertTrue(p.play() == true);
		p.getHand().HandReader();
		System.out.println(p.getTable().getTable());
		assertTrue(p.getHand().sizeOfHand() == 0);
		
	}
	public void testReUseTable4() {
		Player p = new Player("test",1,new p3());
		p.setTest(99, 99, 99);
		Tile joker = new Tile(14,14);
		Tile[] l = {new Tile(1,5),new Tile(1,6),
					new Tile(1,7), new Tile(3,7),
					new Tile(1,3), new Tile(4,8)};
		
		Tile[] l1 = {joker, new Tile(1,2), new Tile(1,1)};
		Tile[] l2 = { new Tile(3,5),new Tile(3,6), new Tile(3,4)};
		
		joker.setJokerPoint(3);
		joker.setJokerColor(1);
		
		p.setCanPlay();
		p.getHand().addTilesToHand(l);
		ArrayList<Tile> a = new ArrayList<Tile>();
		a.addAll(Arrays.asList(l1));
		p.getTable().AiAddTiles(a);
		
		ArrayList<Tile> b = new ArrayList<Tile>();
		b.addAll(Arrays.asList(l2));
		p.getTable().addTiles(b);
		
		p.setIsfirstMeldComplete(true);
		assertTrue(p.play() == true);
		p.getHand().HandReader();
		System.out.println(p.getTable().getTable());
		assertTrue(p.getHand().sizeOfHand() == 2);
	}
	public void testReUseTable5() {
		Player p = new Player("test",1,new p3());
		p.setTest(99, 99, 99);
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
		p.getTable().AiAddTiles(a);
		
		ArrayList<Tile> b = new ArrayList<Tile>();
		b.addAll(Arrays.asList(l2));
		p.getTable().addTiles(b);
		
		p.setIsfirstMeldComplete(true);
		p.getHand().HandReader();
		System.out.println(p.getTable().getTable());
		assertTrue(p.play() == true);
		p.getHand().HandReader();
		assertTrue(p.getHand().sizeOfHand() == 5);
	}
	public void testReUseTable6() {
		Player p = new Player("test",1,new p3());
		p.setTest(99, 99, 99);
		Tile joker = new Tile(14,14);
		Tile[] l = {new Tile(1,5),new Tile(1,6),
					new Tile(1,7), new Tile(4,5),
					new Tile(2,9), new Tile(2,8)};
		
		Tile[] l1 = {joker, new Tile(1,3), new Tile(1,4)};
		Tile[] l2 = { new Tile(2,7),new Tile(2,6), new Tile(2,5)};
		
		joker.setJokerPoint(5);
		joker.setJokerColor(1);
		
		p.setCanPlay();
		p.getHand().addTilesToHand(l);

		ArrayList<Tile> a = new ArrayList<Tile>();
		a.addAll(Arrays.asList(l1));
		p.getTable().AiAddTiles(a);
		
		ArrayList<Tile> b = new ArrayList<Tile>();
		b.addAll(Arrays.asList(l2));
		p.getTable().addTiles(b);
		
		p.setIsfirstMeldComplete(true);
		assertTrue(p.play() == true);
		assertTrue(p.getHand().sizeOfHand() == 4);
		assertTrue(p.isWinner() == false);
	}
	

}
