import junit.framework.TestCase;

public class TestP1 extends TestCase{

	public void test1() {
		Tile joker = new Tile(14,14);
		Tile[] l1 = {joker,new Tile(2,2),new Tile(2,3),new Tile(2,5),new Tile(2,6)}; // 2 3 4 5 6 
		Player p1 = new Player("123",1,new p1());
		p1.getHand().addTilesToHand(l1);
		p1.play();
		assertTrue(joker.getJokerColor().equals("B"));
		assertTrue(joker.getJokerPoint() == 4);
	}
	
	public void test2() {
		Tile joker = new Tile(14,14);
		Tile[] l1 = {joker,new Tile(1,2),new Tile(1,3)}; // 2 3 4
		Player p1 = new Player("123",1,new p1());
		p1.getHand().addTilesToHand(l1);
		p1.play();
		assertTrue(joker.getJokerColor().equals("R"));
		assertTrue(joker.getJokerPoint() == 4);
	}
	
	public void test3() {
		Tile joker = new Tile(14,14);
		Tile[] l1 = {joker,new Tile(1,2),new Tile(1,3), new Tile(1,10), new Tile(1,11)}; // 2 3 10 11 Play Joker 12
		Player p1 = new Player("123",1,new p1());
		p1.getHand().addTilesToHand(l1);
		p1.play();
		assertTrue(joker.getJokerColor().equals("R"));
		assertTrue(joker.getJokerPoint() == 12);
	}
	
	
	public void test4() {
		Tile joker = new Tile(14,14);
		Tile[] l1 = {joker,new Tile(1,2),new Tile(1,3), new Tile(1,5) , new Tile(1,6),new Tile(1,10), new Tile(1,11)}; // 2 3 4 6 10 11 player Joker 5 (after the first initial point) 
		Player p1 = new Player("123",1,new p1());
		p1.getHand().addTilesToHand(l1);
		p1.setIsfirstMeldComplete(true);
		p1.play();
		assertTrue(joker.getJokerColor().equals("R"));
		assertTrue(joker.getJokerPoint() == 4);
	}
	
	public void test5() {
		Tile joker = new Tile(14,14);
		Tile[] l1 = {joker,new Tile(1,2),new Tile(1,3), new Tile(1,4),new Tile(2,12), new Tile(2,13)}; // 2 3 4 6 10 11 player Joker 5 (after the first initial point) 
		Player p1 = new Player("123",1,new p1());
		p1.getHand().addTilesToHand(l1);

		p1.play();
		System.out.println(joker.getJokerColor());
		System.out.println(joker.getJokerPoint());
		
		assertTrue(joker.getJokerColor().equals("B"));
		assertTrue(joker.getJokerPoint() == 11);
	}
	
	
}
