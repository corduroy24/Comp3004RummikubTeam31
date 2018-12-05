import java.util.ArrayList;

import junit.framework.TestCase;

public class TestP1 extends TestCase{

	public void test1() {
		Tile joker = new Tile();
		Tile[] l1 = {joker,new Tile(2,2),new Tile(2,3),new Tile(2,5),new Tile(2,6)}; // 2 3 4 5 6 
		Player p1 = new Player("123",1,new p1());
		p1.getHand().addTilesToHand(l1);
		p1.setCanPlay();
		assertTrue(p1.play() == false);
		assertTrue(joker.getJokerColor().equals("B"));
		assertTrue(joker.getJokerPoint() == 4);
	}
	
	public void test2() {
		Tile joker = new Tile();
		Tile[] l1 = {joker,new Tile(1,2),new Tile(1,3), new Tile(1,2),new Tile(1,3),new Tile(1,4)}; // 2 3 4
		Player p1 = new Player("123",1,new p1());
		p1.getHand().addTilesToHand(l1);
		p1.setCanPlay();
		assertTrue(p1.play() == false);
		assertTrue(joker.getJokerColor().equals("R"));
		assertTrue(joker.getJokerPoint() == 4);
	}
	
	public void test3() {
		Tile joker = new Tile();
		Tile[] l1 = {joker,new Tile(1,2),new Tile(1,3), new Tile(1,10), new Tile(1,11)}; // 2 3 10 11 Play Joker 12
		Player p1 = new Player("123",1,new p1());
		p1.getHand().addTilesToHand(l1);
		p1.setCanPlay();
		assertTrue(p1.play() == true);
		assertTrue(joker.getJokerPoint() == 12);
	}
	
	
	public void test4() {
		Tile joker = new Tile();
		Tile[] l1 = {joker,new Tile(1,2),new Tile(1,3), new Tile(1,5) , new Tile(1,6),new Tile(1,10), new Tile(1,11)}; // 2 3 4 6 10 11 player Joker 4 (after the first initial point) 
		Player p1 = new Player("123",1,new p1());
		p1.getHand().addTilesToHand(l1);
		p1.setIsfirstMeldComplete(true);
		p1.setCanPlay();
		assertTrue(p1.play() == true);
		assertTrue(joker.getJokerColor().equals("R"));
		assertTrue(joker.getJokerPoint() == 4);
	}
	
	public void test5() {
		Tile joker = new Tile();
		Tile[] l1 = {joker,new Tile(1,2),new Tile(1,3), new Tile(1,6),new Tile(1,5),new Tile(1,10), new Tile(1,11)}; // 2 3 5 6 10 11 player Joker 12 (after the first initial point) 
		Player p1 = new Player("123",1,new p1());
		p1.getHand().addTilesToHand(l1);
		p1.setCanPlay();
		assertTrue(p1.play() == true);
		assertTrue(joker.getJokerColor().equals("R"));
		assertTrue(joker.getJokerPoint() == 12);
	}
	
	public void test6() {
		Tile joker = new Tile();
		Tile[] l1 = {joker,new Tile(1,2),new Tile(1,3), new Tile(1,5),new Tile(1,6),new Tile(1,10), new Tile(1,11)}; // 2 3 5 6 10 11 player Joker 5 (after the first initial point) 
		Player p1 = new Player("123",1,new p1());
		p1.getHand().addTilesToHand(l1);
		p1.setIsfirstMeldComplete(true);
		p1.setCanPlay();
		assertTrue(p1.play() == true);
		assertTrue(joker.getJokerColor().equals("R"));
		assertTrue(joker.getJokerPoint() == 4);
	}
	
	public void test7() {
		Tile joker = new Tile();
		Tile joker1 = new Tile();

		Tile[] l1 = {joker,new Tile(1,13), joker1};// use 2 joker to initial the first turn. 
		Player p1 = new Player("123",1,new p1());
		p1.getHand().addTilesToHand(l1);
		p1.setCanPlay();
		assertTrue(p1.play() == true);
		assertTrue(joker.getJokerColor().equals("R"));
		assertTrue(joker.getJokerPoint() == 12);
		assertTrue(joker1.getJokerColor().equals("R"));
		assertTrue(joker1.getJokerPoint() == 11);
		assertTrue(p1.getHand().sizeOfHand() == 0);
	}
	
	public void test8() {
		Tile joker = new Tile();

		Tile[] l1 = {joker,new Tile(1,13), new Tile(2,13),new Tile(3,13)};// use 1 joker to initial the first turn. 
		Player p1 = new Player("123",1,new p1());
		p1.getHand().addTilesToHand(l1);
		p1.setCanPlay();
		assertTrue(p1.play() == true);

		assertTrue(joker.getJokerPoint() == 13);
	}
	
	public void test9() {
		Tile joker = new Tile();

		Tile[] l1 = {joker,new Tile(1,13), new Tile(2,13),
				new Tile(1,9), new Tile(1,10),new Tile(1,12)};
		Player p1 = new Player("123",1,new p1());
		p1.getHand().addTilesToHand(l1);
		p1.setCanPlay();
		assertTrue(p1.play() == true);
		assertTrue(joker.getJokerPoint() == 11);
	}
	
	public void test10() {
		Tile joker = new Tile();

		Tile[] l1 = {joker,new Tile(1,13), new Tile(2,13),
				new Tile(1,6), new Tile(1,7),new Tile(1,9)}; 
		Player p1 = new Player("123",1,new p1());
		p1.getHand().addTilesToHand(l1);
		p1.setCanPlay();
		assertTrue(p1.play() == true);
		assertTrue(joker.getJokerPoint() == 13);
	}
	
	
}
