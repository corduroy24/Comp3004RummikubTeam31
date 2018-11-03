import java.util.ArrayList;

import junit.framework.TestCase;

public class TestPlan extends TestCase{
	
	public void testGameStart() {
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
		
	}
	
	
	
	
}
