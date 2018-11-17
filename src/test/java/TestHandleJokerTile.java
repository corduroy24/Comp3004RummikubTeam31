import static org.junit.Assert.assertTrue;

import junit.framework.TestCase;

public class TestHandleJokerTile extends TestCase {
	HandleJoker handleJoker = new HandleJoker();
	
	Tile joker = new Tile(14,14);	
	Tile joker1 = new Tile(14,14);
	Player p1 = new Player("123",1,new PlayerStrategy1());
	
	public void test1() {
	
	joker1.setJokerColor(4);
	joker1.setJokerPoint(1);
	Tile[] l1 = { joker1,new Tile(4,2),new Tile(4,3)}; // J 2 3
	p1 = new Player("123",1,new PlayerStrategy1());
	p1.getHand().addTilesToHand(l1);
	assertTrue(handleJoker.isRun(p1.getHand().getTiles()));
	}
	
	public void test2() {
	joker1.setJokerColor(1);
	joker1.setJokerPoint(4);
	Tile[] l2 = { joker1,new Tile(1,2),new Tile(1,3)}; // 2 3 J
	p1 = new Player("123",1,new PlayerStrategy1());
	p1.getHand().addTilesToHand(l2);
	assertTrue(handleJoker.isRun(p1.getHand().getTiles()));
	}
	
	public void test3() {
	joker1.setJokerColor(1);
	joker1.setJokerPoint(4);
	Tile[] l3 = { joker1,new Tile(1,2),new Tile(1,3), new Tile(1,5), new Tile(1,6)}; // 2 3 J 5 6
	p1 = new Player("123",1,new PlayerStrategy1());
	p1.getHand().addTilesToHand(l3);
	assertTrue(handleJoker.isRun(p1.getHand().getTiles()));
	}
	
	public void test4() {
	joker1.setJokerColor(1);
	joker1.setJokerPoint(4);
	joker.setJokerColor(1);
	joker.setJokerPoint(3);
	Tile[] l4 = { joker1,joker, new Tile(1,5), new Tile(1,6)}; // J J 5 6
	 p1 = new Player("123",1,new PlayerStrategy1());
	p1.getHand().addTilesToHand(l4);
	assertTrue(handleJoker.isRun(p1.getHand().getTiles()));
	}
	
	public void test5() {
	joker1.setJokerColor(1);
	joker1.setJokerPoint(4);
	joker.setJokerColor(1);
	joker.setJokerPoint(7);
	Tile[] l6 = { joker1,joker, new Tile(1,5), new Tile(1,6)}; // J 5 6 J
	 p1 = new Player("123",1,new PlayerStrategy1());
	p1.getHand().addTilesToHand(l6);
	assertTrue(handleJoker.isRun(p1.getHand().getTiles()));
	}
	
	public void test6() {
	joker1.setJokerColor(1);
	joker1.setJokerPoint(4);
	joker.setJokerColor(1);
	joker.setJokerPoint(6);
	Tile[] l7 = { joker1,new Tile(1,2),new Tile(1,3), new Tile(1,5), new Tile(1,6)}; // J 5 J 7
	 p1 = new Player("123",1,new PlayerStrategy1());
	p1.getHand().addTilesToHand(l7);
	assertTrue(handleJoker.isRun(p1.getHand().getTiles()));
	}
	
	public void test7() {
	joker1.setJokerColor(1);
	joker1.setJokerPoint(7);
	joker.setJokerColor(1);
	joker.setJokerPoint(8);
	Tile[] l5 = { joker1,joker, new Tile(1,5), new Tile(1,6)}; // 5 6 J J
	p1 = new Player("123",1,new PlayerStrategy1());
	p1.getHand().addTilesToHand(l5);
	assertTrue(handleJoker.isRun(p1.getHand().getTiles()));
	}

	public void test8() {
	joker.setJokerColor(4);
	joker.setJokerPoint(5);
	Tile[] l8 = {joker, new Tile(1,5), new Tile(2,5), new Tile(3,5)}; // 5 5 5 J
	p1 = new Player("123",1,new PlayerStrategy1());
	p1.getHand().addTilesToHand(l8);
	assertTrue(handleJoker.isSet(p1.getHand().getTiles()));
	}
	public void test9() {
	joker.setJokerColor(4);
	joker.setJokerPoint(5);
	Tile[] l9 = {joker, new Tile(2,5), new Tile(1,5)}; // 5 5 J
	p1 = new Player("123",1,new PlayerStrategy1());
	p1.getHand().addTilesToHand(l9);
	assertTrue(handleJoker.isSet(p1.getHand().getTiles()));
	}
	public void test10() {
	joker.setJokerColor(4);
	joker.setJokerPoint(5);
	joker.setJokerColor(3);
	joker.setJokerPoint(5);
	
	Tile[] l10 = {joker, new Tile(2,5), new Tile(1,5)}; // 5 5 J J
	p1 = new Player("123",1,new PlayerStrategy1());
	p1.getHand().addTilesToHand(l10);
	assertTrue(handleJoker.isSet(p1.getHand().getTiles()));
	}
}
