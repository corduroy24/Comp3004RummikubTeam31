import java.util.ArrayList;
import junit.framework.TestCase;

public class TestP4 extends TestCase{

	public void test1() {
		Tile joker = new Tile();
		Tile[] l1 = {joker,new Tile(2,2),new Tile(2,3),new Tile(2,5),new Tile(2,6)}; // 2 3 4 5 6 
		Player p4 = new Player("123",1,new p4());
		p4.getHand().addTilesToHand(l1);
		p4.setCanPlay();
		assertTrue(p4.play() == false);
		assertTrue(joker.getJokerColor().equals("B"));
		assertTrue(joker.getJokerPoint() == 4);
	}
	
	public void test2() {
		Tile joker = new Tile();
		Tile[] l1 = {joker,new Tile(1,2),new Tile(1,3), new Tile(1,2),new Tile(1,3),new Tile(1,4)}; // 2 3 4
		Player p4 = new Player("123",1,new p4());
		p4.getHand().addTilesToHand(l1);
		p4.setCanPlay();
		assertTrue(p4.play() == false);
		assertTrue(joker.getJokerColor().equals("R"));
		assertTrue(joker.getJokerPoint() == 4);
	}
	
	public void test3() {
		Tile joker = new Tile();
		Tile[] l1 = {joker,new Tile(1,2),new Tile(1,3), new Tile(1,10), new Tile(1,11)}; // 2 3 10 11 Play Joker 12
		Player p4 = new Player("123",1,new p4());
		 ArrayList<Tile> t = new ArrayList<Tile>();
			 t.add(new Tile(1,2));t.add(new Tile(1,3));t.add(new Tile(1,4));
			 p4.getTable().addTiles(t);
		p4.getHand().addTilesToHand(l1);
		p4.setCanPlay();
		assertTrue(p4.play() == true);
		assertTrue(joker.getJokerPoint() == 12);
	}
	
	
	public void test4() {
		Tile joker = new Tile();
		Tile[] l1 = {joker,new Tile(1,2),new Tile(1,3), new Tile(1,5) , new Tile(1,6),new Tile(1,10), new Tile(1,11)}; // 2 3 4 6 10 11 player Joker 4 (after the first initial point) 
		Player p4 = new Player("123",1,new p4());
		p4.getHand().addTilesToHand(l1);
		p4.setCanPlay();
		p4.setIsfirstMeldComplete(true);
		assertTrue(p4.play() == true);
		assertTrue(joker.getJokerColor().equals("R"));
		assertTrue(joker.getJokerPoint() == 4);
	}
	
	public void test5() {
		Tile joker = new Tile();
		Tile[] l1 = {joker,new Tile(1,2),new Tile(1,3), new Tile(1,6),new Tile(1,5),new Tile(1,10), new Tile(1,11)}; // 2 3 5 6 10 11 player Joker 12 (after the first initial point) 
		Player p4 = new Player("123",1,new p4());
		p4.getHand().addTilesToHand(l1);
		p4.setCanPlay();
		assertTrue(p4.play() == true);
		assertTrue(joker.getJokerColor().equals("R"));
		assertTrue(joker.getJokerPoint() == 12);
	}
	
	public void test6() {
		Tile joker = new Tile();
		Tile[] l1 = {joker,new Tile(1,2),new Tile(1,3), new Tile(1,5),new Tile(1,6),new Tile(1,10), new Tile(1,11)}; // 2 3 5 6 10 11 player Joker 5 (after the first initial point) 
		Player p4 = new Player("123",1,new p4());
		p4.getHand().addTilesToHand(l1);
		p4.setCanPlay();
		p4.setIsfirstMeldComplete(true);
		
		assertTrue(p4.play() == true);
		assertTrue(joker.getJokerColor().equals("R"));
		assertTrue(joker.getJokerPoint() == 4);
	}
	/*
	public void test7() {
		Tile joker = new Tile(14,14);
		Tile joker1 = new Tile(14,14);

		Tile[] l1 = {joker,new Tile(1,13), joker1};// use 2 joker to initial the first turn. 
		Player p4 = new Player("123",1,new p4());
		p4.getHand().addTilesToHand(l1);
		
		assertTrue(p4.play() == true);
		assertTrue(joker1.getJokerColor().equals("R"));
		assertTrue(joker1.getJokerPoint() == 12);
		assertTrue(joker1.getJokerColor().equals("R"));
		assertTrue(joker1.getJokerPoint() == 11);
		assertTrue(p4.getHand().sizeOfHand() == 0);
	}
	*/
	public void test8() {
		Tile joker = new Tile();

		Tile[] l1 = {joker,new Tile(1,13), new Tile(2,13),new Tile(3,13)};// use 1 joker to initial the first turn. 
		Player p4 = new Player("123",1,new p4());
		p4.getHand().addTilesToHand(l1);
		p4.setCanPlay();
		assertTrue(p4.play() == true);

		assertTrue(joker.getJokerPoint() == 13);
	}
	/*
	public void test9() {
		Tile joker = new Tile();

		Tile[] l1 = {joker,new Tile(1,13), new Tile(2,13),
				new Tile(1,9), new Tile(1,10),new Tile(1,12)};
		Player p4 = new Player("123",1,new p4());
		p4.getHand().addTilesToHand(l1);
		
		assertTrue(p4.play() == true);
		assertTrue(joker.getJokerPoint() == 13);
	}
	*/
	public void test10() {
		Tile joker = new Tile();

		Tile[] l1 = {joker,new Tile(1,13), new Tile(2,13),
				new Tile(1,6), new Tile(1,7),new Tile(1,9)}; 
		Player p4 = new Player("123",1,new p4());
		p4.getHand().addTilesToHand(l1);
		p4.setCanPlay();
		assertTrue(p4.play() == true);
		assertTrue(joker.getJokerPoint() == 13);
	}
	
	public void testP4Initial() {  System.out.println(" INITIAL");
	Deck D = new Deck();
	Player p4 = new Player("123",1,new p4());
	p4.setIsfirstMeldComplete(false);
	D=p4.getDeck();
	p4.getHand().DrawThis(new Tile(1,2),D);p4.getHand().DrawThis(new Tile(1,3),D);p4.getHand().DrawThis(new Tile(1,4),D);
	p4.getHand().DrawThis(new Tile(1,8),D);p4.getHand().DrawThis(new Tile(1,5),D);
	p4.getHand().DrawThis(new Tile(2,10),D);p4.getHand().DrawThis(new Tile(3,10),D);p4.getHand().DrawThis(new Tile(4,10),D);
	D=p4.getDeck();
	//player2.getHand().DrawThis(new Tile(1,2),D);player2.getHand().DrawThis(new Tile(1,3),D);player2.getHand().DrawThis(new Tile(1,4),D);
	Player p2 = new Player("123",1,new p2());
	p2.getHand().DrawThis(new Tile(1,9),D);
	p4.getHand().HandReader();
	 ArrayList<Tile> t = new ArrayList<Tile>();
	// t.add(new Tile(1,2));t.add(new Tile(1,3));t.add(new Tile(1,4));
	p4.getTable().addTiles(t);
	p4.setCanPlay();
	p4.play();
	assertTrue(p4.getPlayerHand().sizeOfHand()==5);
	
}
	
}
