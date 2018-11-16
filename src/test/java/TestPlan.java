import java.util.ArrayList;
import java.util.Arrays;

import junit.framework.TestCase;

public class TestPlan extends TestCase{
	
	/*public void testGameStart() {
		GameMaster game = new GameMaster(); 
		
		//Req. 1
		game.dealInitialHand();
		
		ArrayList<Player> players = game.getPlayers(); 
		for(int i = 0; i < players.size(); i++ )
			assertEquals(14, players.get(i).getHand().sizeOfHand()); 
				
		for(int i = 0; i < players.size(); i++) {
			for(int j = 0; j < game.getHuman().getHand().sizeOfHand() - 1 ; j++) {
				if(players.get(i).getHand().getTile(j).getColor().equals(players.get(i).getHand().getTile(j+1).getColor())){
					if(players.get(i).getHand().getTile(j).getColor().equals("R")){
						assertTrue(players.get(i).getHand().getTile(j).getNumber() <= players.get(i).getHand().getTile(j+1).getNumber());
					}
					else if(players.get(i).getHand().getTile(j).getColor().equals("B")){
						assertTrue(players.get(i).getHand().getTile(j).getNumber() <= players.get(i).getHand().getTile(j+1).getNumber());
					}
					else if(players.get(i).getHand().getTile(j).getColor().equals("G")){
						assertTrue(players.get(i).getHand().getTile(j).getNumber() <= players.get(i).getHand().getTile(j+1).getNumber());
					}
					else if(players.get(i).getHand().getTile(j).getColor().equals("O")){
						assertTrue(players.get(i).getHand().getTile(j).getNumber() <= players.get(i).getHand().getTile(j+1).getNumber());
					}
				}
			}
		}
	}*/
	

	
	public void test4a1() {
		Player p1 =  new Player("AI1",1,new PlayerStrategy1());
		Player p2 =  new Player("AI1",1,new PlayerStrategy2());
		Player p3 =  new Player("AI1",1,new PlayerStrategy3());
		Tile l[] = {new Tile(1,9), new Tile(2,9), new Tile(3,9),
					new Tile(1,1),new Tile(1,2), new Tile(1,3),
					new Tile(2,4),new Tile(2,5), new Tile(2,6),
			};
		p1.getHand().addTilesToHand(l);
		p2.getHand().addTilesToHand(l);
		p3.getHand().addTilesToHand(l);
		assertTrue (p1.play() == true);
		assertTrue (p2.play() == true);
		assertTrue (p3.play() == true);
		assertTrue(p1.getHand().sizeOfHand() == 0);
		assertTrue(p2.getHand().sizeOfHand() == 0);
		assertTrue(p3.getHand().sizeOfHand() == 0);
		
		
	}
	
	public void testP1PlayFirstInitialTurn() {
		GameMaster game = new GameMaster();
		Player p1 = game.getAI();
		
		System.out.println("Test p1 play its initial turn!");
		
		Tile l[] = {new Tile(1,9), new Tile(2,9), new Tile(3,9)};
		Tile l1[] = {new Tile(1,9), new Tile(1,10), new Tile(1,11)};
	
		System.out.println("Test with a run 30 points");
		for(int i =0; i < l1.length; i++) {
			p1.getHand().addTileToHand(l1[i]);
		}
		assertTrue(p1.play() == true);
		System.out.println("Pass\n");

		System.out.println("--------------------------");
		
		game = new GameMaster();
		p1 = game.getAI();

		
		Tile ll1[] = {new Tile(1,11), new Tile(1,12), new Tile(1,13)};
		
		System.out.println("Test with a run to get more than 30 points");
		for(int i =0; i < l1.length; i++) {
			p1.getHand().addTileToHand(ll1[i]);
		}
		assertTrue(p1.play() == true);
		System.out.println("Pass\n");

		System.out.println("--------------------------");
		
		
		game = new GameMaster();
		p1 = game.getAI();

		
		Tile a1[] = {new Tile(1,5), new Tile(1,6), new Tile(1,7)};
		Tile a2[] = {new Tile(1,6), new Tile(1,7), new Tile(1,8)};
		
		System.out.println("Test with several runs to get 30 points");
		for(int i =0; i < l1.length; i++) {
			p1.getHand().addTileToHand(a1[i]);
			p1.getHand().addTileToHand(a2[i]);	
		}
		assertTrue(p1.play() == true);
		System.out.println("Pass\n");

		System.out.println("--------------------------");
		
		game = new GameMaster();
		p1 = game.getAI();

		
		Tile a3[] = {new Tile(1,10), new Tile(1,11), new Tile(1,12)};
		
		System.out.println("Test with several runs to get more than 30 points");
		for(int i =0; i < l1.length; i++) {
			p1.getHand().addTileToHand(a1[i]);
			p1.getHand().addTileToHand(a2[i]);	
			p1.getHand().addTileToHand(a3[i]);	
		}
		assertTrue(p1.play() == true);
		System.out.println("Pass\n");

		System.out.println("--------------------------");
		
		
		game = new GameMaster();
		p1 = game.getAI();
		Tile l4[] = {new Tile(1,10), new Tile(2,10), new Tile(3,10)};
		System.out.println("Test with a set 30 points");
		for(int i =0; i < l4.length; i++) {
			p1.getHand().addTileToHand(l4[i]);
		}
		assertTrue(p1.play() == true);
		System.out.println("Pass\n");

		System.out.println("--------------------------");
		
		
		game = new GameMaster();
		p1 = game.getAI();
		Tile ll4[] = {new Tile(1,12), new Tile(2,12), new Tile(3,12)};
		System.out.println("Test with a set 36 points");
		for(int i =0; i < l4.length; i++) {
			p1.getHand().addTileToHand(ll4[i]);
		}
		assertTrue(p1.play() == true);
		System.out.println("Pass\n");

		System.out.println("--------------------------");
				
		
		
		
		game = new GameMaster();
		p1 = game.getAI();
		Tile k3[] = {new Tile(1,5), new Tile(2,5), new Tile(3,5)};
		Tile k4[] = {new Tile(4,5), new Tile(3,5), new Tile(2,5)};
		
		System.out.println("Test with several sets  to get 30 points");
		for(int i =0; i < k4.length; i++) {
			p1.getHand().addTileToHand(k3[i]);
			p1.getHand().addTileToHand(k4[i]);
		}
		assertTrue(p1.play() == true);
		System.out.println("Pass\n");

		System.out.println("--------------------------");
		
		
		
		
		
		game = new GameMaster();
		p1 = game.getAI();
		Tile k[] = {new Tile(2,3), new Tile(3,3), new Tile(4,3)};
		Tile k1[] = {new Tile(2,9), new Tile(3,9), new Tile(4,9)};
		
		System.out.println("Test with several sets  to get more than 30 points");
		for(int i =0; i < l4.length; i++) {
			p1.getHand().addTileToHand(l4[i]);
			p1.getHand().addTileToHand(k[i]);
			p1.getHand().addTileToHand(k1[i]);
		}
		assertTrue(p1.play() == true);
		System.out.println("Pass\n");

		System.out.println("--------------------------");		
		
		game = new GameMaster();
		p1 = game.getAI();

		Tile l5[] = {new Tile(1,4), new Tile(1,5), new Tile(1,6)};
		Tile l6[] = {new Tile(1,5), new Tile(2,5), new Tile(3,5)};
		System.out.println("Test with mixed runs and sets adding up to 30 points!");
		
		for(int i =0; i < l5.length; i++) {
			p1.getHand().addTileToHand(l5[i]);
			p1.getHand().addTileToHand(l6[i]);
		}
		assertTrue(p1.play() == true);
		System.out.println("Pass\n");

		System.out.println("--------------------------");
		
		game = new GameMaster();
		p1 = game.getAI();

		Tile l7[] = {new Tile(1,6), new Tile(1,7), new Tile(1,8)};
		Tile l8[] = {new Tile(1,6), new Tile(2,6), new Tile(3,6)};
		System.out.println("Test with mixed runs and sets adding up to more than 30 points!");
		
		for(int i =0; i < l5.length; i++) {
			p1.getHand().addTileToHand(l5[i]);
			p1.getHand().addTileToHand(l6[i]);
		}
		assertTrue(p1.play() == true);
		System.out.println("Pass\n");

		System.out.println("--------------------------");	
		
		game = new GameMaster();
		p1 = game.getAI();

		Tile l9[] = {new Tile(1,3), new Tile(1,4), new Tile(1,5), new Tile(1,6), new Tile(1,7),
				new Tile(1,8), new Tile(1,9), new Tile(1,10), new Tile(1,11), new Tile(1,12)};
		Tile l10[] = {new Tile(1,6), new Tile(2,6), new Tile(3,6), new Tile(4,6)};
		System.out.println("Test player wins on first turn");
		
		for(int i =0; i < l9.length; i++) 
			p1.getHand().addTileToHand(l9[i]);
		
		for(int i =0; i < l10.length; i++) 
			p1.getHand().addTileToHand(l10[i]);
		
		assertTrue(p1.play() == true);
		assertTrue(p1.getIsFirstMeldComplete()); 
		assertTrue(p1.isWinner()); 
		System.out.println("Pass\n");

		System.out.println("--------------------------");	
		
		game = new GameMaster();
		p1 = game.getAI(); 
		Tile x1[] = {new Tile(1,9), new Tile(4,9), new Tile(2,3)};
		Tile x2[] = {new Tile(2,9), new Tile(3,10), new Tile(4,7)};
	
		System.out.println("Test p1 cannot play initial meld");

		p1.getHand().addTilesToHand(x1);
		p1.getHand().addTilesToHand(x2);

		assertTrue(p1.play() == false);
		System.out.println("Pass\n");

		System.out.println("--------------------------");
	}
	
	public void testP1AfterGetInitialTurn() {
		GameMaster game = new GameMaster();
		Player p1 = game.getAI();
		p1.setIsfirstMeldComplete(true);
		
		System.out.println("Test p1 play AFTER its initial turn!");
		
		Tile l[] = {new Tile(1,9), new Tile(2,9), new Tile(3,9)};
		Tile l1[] = {new Tile(1,9), new Tile(1,10), new Tile(1,11)};
	
		System.out.println("Test with a run");
		for(int i =0; i < l1.length; i++) {
			p1.getHand().addTileToHand(l1[i]);
		}
		assertTrue(p1.play() == true);
		System.out.println("Pass\n");

		System.out.println("--------------------------");
		
		game = new GameMaster();
		p1 = game.getAI();
		p1.setIsfirstMeldComplete(true);
		
		Tile a1[] = {new Tile(1,6), new Tile(1,7), new Tile(1,8)};
		Tile a2[] = {new Tile(1,2), new Tile(1,3), new Tile(1,4)};
		
		System.out.println("Test with several runs");
		for(int i =0; i < l1.length; i++) {
			p1.getHand().addTileToHand(a1[i]);
			p1.getHand().addTileToHand(a2[i]);	
		}
		assertTrue(p1.play() == true);
		System.out.println("Pass\n");

		System.out.println("--------------------------");
		
		game = new GameMaster();
		p1 = game.getAI();
		p1.setIsfirstMeldComplete(true);
		Tile l4[] = {new Tile(1,10), new Tile(2,10), new Tile(3,10)};
		System.out.println("Test with a set");
		for(int i =0; i < l4.length; i++) {
			p1.getHand().addTileToHand(l4[i]);
		}
		assertTrue(p1.play() == true);
		System.out.println("Pass\n");

		System.out.println("--------------------------");
		
		
		game = new GameMaster();
		p1 = game.getAI();
		p1.setIsfirstMeldComplete(true);
		Tile k3[] = {new Tile(1,5), new Tile(2,5), new Tile(3,5)};
		Tile k4[] = {new Tile(4,5), new Tile(3,5), new Tile(2,5)};
		
		System.out.println("Test with several sets");
		for(int i =0; i < k4.length; i++) {
			p1.getHand().addTileToHand(k3[i]);
			p1.getHand().addTileToHand(k4[i]);
		}
		assertTrue(p1.play() == true);
		System.out.println("Pass\n");

		System.out.println("--------------------------");

		game = new GameMaster();
		p1 = game.getAI();
		p1.setIsfirstMeldComplete(true);
		
		Tile l5[] = {new Tile(1,4), new Tile(1,5), new Tile(1,6)};
		Tile l6[] = {new Tile(1,5), new Tile(2,5), new Tile(3,5)};
		System.out.println("Test with mixed runs and sets");
		
		for(int i =0; i < l5.length; i++) {
			p1.getHand().addTileToHand(l5[i]);
			p1.getHand().addTileToHand(l6[i]);
		}
		assertTrue(p1.play() == true);
		System.out.println("Pass\n");

		System.out.println("--------------------------");
		
		game = new GameMaster();
		p1 = game.getAI();
		p1.setIsfirstMeldComplete(true);
		
		Tile t6[] = {new Tile(1,4), new Tile(1,5), new Tile(1,8)}; 
		ArrayList<Tile> al6 = new ArrayList<Tile>();
		
		Tile x1[] = {new Tile(1,9), new Tile(4,5), new Tile(2,3)};
		Tile x2[] = {new Tile(2,9), new Tile(3,10), new Tile(4,7)};
	
		System.out.println("Test cannot play");
		for(int i =0; i < x1.length; i++) {
			p1.getHand().addTileToHand(x1[i]);
			p1.getHand().addTileToHand(x2[i]);
			al6.add(t6[i]);
		}
		game.getTable().addTiles(al6); 
		
		assertTrue(p1.play() == false);
		System.out.println("Pass\n");

		System.out.println("--------------------------");
		
	}
	
	public void testP2PlayFirstInitialTurn() {
		GameMaster game = new GameMaster();
		Player p1 = game.getAI();
		Player p2 = game.getAI2(); 
				
		System.out.println("TEST P2 PLAY ITS INITAL TURN");

		System.out.println("15b: Test someone has played its 30+ points and p2 can play 30+ poits and thus does");
		
		Tile x1[] = {new Tile(1,9), new Tile(4,5), new Tile(2,3)};
		Tile x2[] = {new Tile(2,9), new Tile(3,10), new Tile(4,7)};
		
		p1.getHand().addTilesToHand(x1);
		p1.getHand().addTilesToHand(x2);

		Tile l5[] = {new Tile(1,4), new Tile(1,8), new Tile(1,6)};
		Tile l6[] = {new Tile(1,5), new Tile(2,5), new Tile(3,5)};
		
		p2.getHand().addTilesToHand(l5);
		p2.getHand().addTilesToHand(l6);
		assertTrue(p1.play() == false);

		assertTrue(p2.play() == false); 
		assertFalse(p2.getIsFirstMeldComplete());
		
		System.out.println("Pass\n");
		System.out.println("--------------------------");
		
		System.out.println("15a: Test someone has played its 30+ points but p2 canrt play 30+ points and draws");

		Tile k3[] = {new Tile(1,5), new Tile(2,5), new Tile(3,5)};
		Tile k4[] = {new Tile(4,5), new Tile(3,5), new Tile(2,5)};		
		Tile l7[] = {new Tile(1,7), new Tile(1,8), new Tile(1,9)};
		Tile l8[] = {new Tile(1,5), new Tile(2,5), new Tile(3,5)};
		
		game = new GameMaster();
		Table table = game.getTable(); 
		ArrayList<ArrayList<Tile>> t = new ArrayList<ArrayList<Tile>>();
		table.setTable(t);

		game.Announcement();
		p1 = game.getAI();
		p2 = game.getAI2(); 
		
		p1.getHand().addTilesToHand(k3);
		p1.getHand().addTilesToHand(k4);
		p2.getHand().addTilesToHand(l7);
		p2.getHand().addTilesToHand(l8);
		
		assertTrue(p1.play()); 
		assertTrue(p2.play()==true);
		assertTrue(p2.getIsFirstMeldComplete());
		assertFalse(p2.isWinner()); 
		
		System.out.println("Pass\n");
		System.out.println("--------------------------");
	}

	public void testP2AfterGetInitialTurn() {
		
		System.out.println("TEST P2 PLAY AFTER ITS INITIAL TURN");

		GameMaster game = new GameMaster();
		Player p1 = game.getAI();
		Player p2 = game.getAI2(); 
		Table table = game.getTable(); 
		ArrayList<ArrayList<Tile>> t = new ArrayList<ArrayList<Tile>>();
		table.setTable(t);
	
		Tile k3[] = {new Tile(1,5), new Tile(2,5), new Tile(3,5)};
		Tile k4[] = {new Tile(4,5), new Tile(3,5), new Tile(2,5)};		
		Tile l7[] = {new Tile(1,7), new Tile(1,8), new Tile(1,9)};
	
		System.out.println("16a: Test p2 wins not using the tiles of the table on its last turn");
		game.Announcement();

		p1.setIsfirstMeldComplete(true);
		p2.setIsfirstMeldComplete(true);
		
		p1.getHand().addTilesToHand(k3);
		p1.getHand().addTilesToHand(k4);
		p2.getHand().addTilesToHand(l7);
		
		assertTrue(p1.play() == true);
		assertTrue(p2.play() == true);
		assertTrue(p2.isWinner()); 
		
		System.out.println("Pass\n");
		System.out.println("--------------------------");
		
		System.out.println("16b: Test p2 wins using some tiles of the table on its last turn");
		game = new GameMaster();
		p1 = game.getAI();
		p2 = game.getAI2(); 
		table = game.getTable(); 
		t = new ArrayList<ArrayList<Tile>>();
		table.setTable(t);
		
		Tile k5[] = {new Tile(1,5), new Tile(2,5), new Tile(3,5)};
		Tile k6[] = {new Tile(1,7), new Tile(1,8), new Tile(1,9)};		
		Tile l22[] = {new Tile(1,10), new Tile(4,5), new Tile(1,6)};
	
		game.Announcement();

		p1.setIsfirstMeldComplete(true);
		p2.setIsfirstMeldComplete(true);
		
		p1.getHand().addTilesToHand(k5);
		p1.getHand().addTilesToHand(k6);
		p2.getHand().addTilesToHand(l22);
		
		assertTrue(p1.play() == true);
		assertTrue(p2.play() == true);
		assertTrue(p2.isWinner()); 
		
		System.out.println("Pass\n");
		System.out.println("--------------------------");
		
		System.out.println("17a: it can play one or more tiles that require use of the tiles of the table");
		game = new GameMaster();
		p1 = game.getAI();
		p2 = game.getAI2(); 
		table = game.getTable(); 
		t = new ArrayList<ArrayList<Tile>>();
		table.setTable(t);
		
		Tile k9[] = {new Tile(1,5), new Tile(2,5), new Tile(3,5)};
		Tile k10[] = {new Tile(1,7), new Tile(1,8), new Tile(1,9)};		
		Tile l44[] = {new Tile(1,10), new Tile(4,9), new Tile(1,6)};
		Tile l55[] = {new Tile(3,2), new Tile(4,5), new Tile(4,11)};

		game.Announcement();

		p1.setIsfirstMeldComplete(true);
		p2.setIsfirstMeldComplete(true);
		
		p1.getHand().addTilesToHand(k9);
		p1.getHand().addTilesToHand(k10);
		p2.getHand().addTilesToHand(l44);		
		p2.getHand().addTilesToHand(l55);

		
		assertTrue(p1.play() == true);
		assertTrue(p2.play() == true);
		assertFalse(p2.isWinner()); 
		
		System.out.println("Pass\n");
		System.out.println("--------------------------");
		
		System.out.println("17b: it cannot play at least one tile that requires use of the table and thus draws");
		game = new GameMaster();
		p1 = game.getAI();
		p2 = game.getAI2(); 
		table = game.getTable(); 
		t = new ArrayList<ArrayList<Tile>>();
		table.setTable(t);
		
		Tile k11[] = {new Tile(1,5), new Tile(2,5), new Tile(3,5)};
		Tile k12[] = {new Tile(1,7), new Tile(1,8), new Tile(1,9)};		
		Tile l66[] = {new Tile(1,4), new Tile(4,9), new Tile(1,13)};
		Tile l77[] = {new Tile(3,2), new Tile(4,8), new Tile(4,11)};

		game.Announcement();

		p1.setIsfirstMeldComplete(true);
		p2.setIsfirstMeldComplete(true);
		
		p1.getHand().addTilesToHand(k11);
		p1.getHand().addTilesToHand(k12);
		p2.getHand().addTilesToHand(l66);		
		p2.getHand().addTilesToHand(l77);

		assertTrue(p1.play() == true);
		boolean play = p2.play();
		assertTrue(play == false); 
		assertFalse(p2.isWinner()); 

		int prevSize = p2.getHand().sizeOfHand(); 
		if(play == false) {
    		Tile x = game.getDeck().Draw();
    		p2.getHand().addTileToHand(x);
		}
			
		assertTrue(prevSize != p2.getHand().sizeOfHand()); 
		
		System.out.println("Pass\n");
		System.out.println("--------------------------");
	}
	
	
	public void testP1andP3PlayFirstInitialTurn() {
		GameMaster game = new GameMaster();
		Player p1 = game.getAI();
		Player p3 = game.getAI3(); 
		
		System.out.println("Test p3 and p1 play its initial turn!");
		
		Tile x1[] = {new Tile(1,3), new Tile(1,9), new Tile(1,10), new Tile(1,11)};
		Tile x2[] = {new Tile(1,4), new Tile(3,11), new Tile(3,9), new Tile(3,10)};
		
		p1.getHand().addTilesToHand(x1);
		p3.getHand().addTilesToHand(x2);
		assertTrue(p1.play() == true);
		
		assertTrue(p3.play() == true); 
	}
	
	public void testFirstP() {
		GameMaster game = new GameMaster();
		Player p1 = game.getAI();
		Player p3 = game.getAI3(); 
		
		System.out.println("Test p3 and p1 play its initial turn!");
		
		Tile x1[] = {new Tile(1,3), new Tile(2,3), new Tile(3,3), 
				new Tile(1,9), new Tile(1,10), new Tile(1,11),
				new Tile(4,9)};
		
		Tile x2[] = {new Tile(1,4), new Tile(3,11), new Tile(3,9), new Tile(3,10),
				new Tile(1,13), new Tile(2,13), new Tile(3,13)};
		
		ArrayList<Tile> test = new ArrayList<Tile>();
		
		test.add(new Tile(1,7));test.add(new Tile(1,5));test.add(new Tile(1,6));
		
		p1.getHand().addTilesToHand(x1);
		p3.getHand().addTilesToHand(x2);
		assertTrue(p1.play() == true);
		game.Announcement();
		assertTrue(p3.play() == true); 
		game.Announcement();
		System.out.println("AAAAAAAAAAAAAA");
		p3.getHand().HandReader();
		assertTrue(p3.getHand().sizeOfHand()==4);
		
		p3.getTable().addTiles(test);
		
		assertTrue(p3.play() == true);
		assertTrue(p3.isWinner()==true);
		
		
	}
	
	public void testFirstP3Sub() {
		GameMaster game = new GameMaster();
		Player p1 = game.getAI();
		Player p3 = game.getAI3(); 
		
		System.out.println("Test p3 and p1 play its initial turn!");
		
		Tile x1[] = {new Tile(1,3), new Tile(1,1), new Tile(1,2) 
				};
		
		Tile x2[] = {new Tile(1,4), new Tile(1,5), new Tile(1,6), new Tile(1,7)
				};
		
		ArrayList<Tile> test = new ArrayList<Tile>();
		
		test.add(new Tile(1,3));test.add(new Tile(1,2));test.add(new Tile(1,1));
		
		p1.getHand().addTilesToHand(x1);
		p3.getHand().addTilesToHand(x2);
		p3.getTable().addTiles(test);
		
		p1.play();
		assertTrue(p1.play() == false);
		game.Announcement();
		assertTrue(p3.play() == true); 
		game.Announcement();
		p3.getHand().HandReader();
		assertTrue(p3.getHand().sizeOfHand()==0);
	}
	
	public void testPlayAllTilesP3() {
		GameMaster game = new GameMaster();
		Player p1 = game.getAI();
		Player p3 = game.getAI3(); 
		
		System.out.println("Testing if all tiles get played on first turn!");
		
		Tile x1[] = {new Tile(1,3), new Tile(1,1), new Tile(1,2) 
				};
		
		Tile x2[] = {new Tile(1,4), new Tile(1,5), new Tile(1,6), new Tile(1,7)
				};
		
		ArrayList<Tile> test = new ArrayList<Tile>();
		
		test.add(new Tile(1,3));test.add(new Tile(1,2));test.add(new Tile(1,1));
		
		p1.getHand().addTilesToHand(x1);
		p3.getHand().addTilesToHand(x2);
		p3.getTable().addTiles(test);
		
		p1.play();
		assertTrue(p1.play() == false);
		game.Announcement();
		assertTrue(p3.play() == true); 
		game.Announcement();
		p3.getHand().HandReader();
		assertTrue(p3.getHand().sizeOfHand()==0);
		assertTrue(p3.isWinner()==true);
	}
	
	public void testwinP3() {
		GameMaster game = new GameMaster();
		Player p1 = game.getAI();
		Player p3 = game.getAI3(); 
		
		System.out.println("Test p3 winning");
		
		Tile x1[] = {new Tile(1,3), new Tile(1,1), new Tile(1,2), new Tile(4,4),new Tile(2,5) 
				};
		
		Tile x2[] = {new Tile(3,4), new Tile(3,5), new Tile(3,6), new Tile(3,7)
				};
		
		ArrayList<Tile> test = new ArrayList<Tile>();
		
		test.add(new Tile(1,3));test.add(new Tile(1,2));test.add(new Tile(1,1));
		
		p1.getHand().addTilesToHand(x1);
		p3.getHand().addTilesToHand(x2);
		p3.getTable().addTiles(test);
		
		p1.play();
		assertTrue(p1.play() == false);
		game.Announcement();
		assertTrue(p3.play() == true); 
		game.Announcement();
		p3.getHand().HandReader();
		assertTrue(p3.getHand().sizeOfHand()==0);
		assertTrue(p3.isWinner()==true);
	}
	
	public void testP3Draw() {  
		
		GameMaster game = new GameMaster();
		Player p1 = game.getAI();
		Player p3 = game.getAI3(); 
		
			ArrayList<Tile> test = new ArrayList<Tile>();
			
			Tile x1[] = {new Tile(1,3), new Tile(1,1), new Tile(1,2) 
			};

			Tile x2[] = {new Tile(3,7),new Tile(2,3), new Tile(4,4)
			};
			p1.getHand().addTilesToHand(x1);
			p3.getHand().addTilesToHand(x2);
			p1.play();
			p3.play();
			if (p3.play()==false) {
				p3.getHand().addTileToHand(game.getDeck().Draw());
			}
			System.out.println("WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW");
			assertEquals(p3.getHand().sizeOfHand(),4);

			
			
		}
	
public void testP3Draw2() {  //FOR WHEN strategy pattern 2 DOES NOT have to be done
	
	GameMaster game = new GameMaster();
	Player p1 = game.getAI();
	Player p3 = game.getAI3(); 
	
		ArrayList<Tile> test = new ArrayList<Tile>();
		
		Tile x1[] = {new Tile(1,3), new Tile(1,1), new Tile(1,2) 
		};

		Tile x2[] = {new Tile(3,4), new Tile(3,5), new Tile(3,6), new Tile(3,7),new Tile(2,3)
		};
		p1.getHand().addTilesToHand(x1);
		p3.getHand().addTilesToHand(x2);
		p1.play();
		p3.play();
		if (p3.play()==false) {
			p3.getHand().addTileToHand(game.getDeck().Draw());
		}
		System.out.println("WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW");
		assertEquals(p3.getHand().sizeOfHand(),6);

		
		
	}
	
}
