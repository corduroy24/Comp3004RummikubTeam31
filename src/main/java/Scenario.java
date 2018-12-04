import java.util.ArrayList;
import java.util.Arrays;

public interface Scenario {
	public int numTurns = 0; 
	public GameMaster deal(GameMaster game); 
	public GameMaster secondTurn (GameMaster game, int player);
	public GameMaster thirdTurn (GameMaster game, int player);

	public int getNumTurns();
	
}
	class Scenario1 implements Scenario{
		int numTurns = 8; 
		public GameMaster deal (GameMaster game) {
			Deck deck = game.getDeck(); 
			game.addPlayer(1);
			game.addPlayer(2);
			game.addPlayer(3);
			game.addPlayer(4);

			game.getAI().setIsfirstMeldComplete(true);
			game.getAI2().setIsfirstMeldComplete(true);
			game.getAI3().setIsfirstMeldComplete(true);
			game.getAI4().setIsfirstMeldComplete(true);

			Tile t6[] = {new Tile(1,4), new Tile(1,5), new Tile(1,6)};
			Tile t7[] = {new Tile(1,8), new Tile(1,9), new Tile(1,10)};
			
					
			ArrayList<Tile> listTiles1 = new ArrayList<Tile>(); 
			ArrayList<Tile> listTiles2 = new ArrayList<Tile>(); 
			
			listTiles1.addAll(Arrays.asList(t6));
			listTiles2.addAll(Arrays.asList(t7));

			
			game.getTable().addTiles(listTiles1); 
			game.getTable().addTiles(listTiles2); 

			
			game.getAI().getHand().DrawThis(new Tile(1,1), deck);
			game.getAI().getHand().DrawThis(new Tile(2,1), deck);
			game.getAI().getHand().DrawThis(new Tile(3,8), deck);
			game.getAI().getHand().DrawThis(new Tile(1,3), deck);
			game.getAI().getHand().DrawThis(new Tile(1,7), deck);
			game.getAI().getHand().DrawThis(new Tile(4,6), deck);

			game.getAI2().getHand().DrawThis(new Tile(3,6), deck);
			game.getAI2().getHand().DrawThis(new Tile(4,5), deck);
			game.getAI2().getHand().DrawThis(new Tile(2,2), deck);
			game.getAI2().getHand().DrawThis(new Tile(2,3), deck);
			game.getAI2().getHand().DrawThis(new Tile(2,5), deck);
			game.getAI2().getHand().DrawThis(new Tile(3,3), deck);
			
			game.getAI3().getHand().DrawThis(new Tile(2,5), deck);
			game.getAI3().getHand().DrawThis(new Tile(2,6), deck);

			game.getAI4().getHand().DrawThis(new Tile(3,4), deck);
			game.getAI4().getHand().DrawThis(new Tile(4,5), deck);
			game.getAI4().getHand().DrawThis(new Tile(3,6), deck);
			game.getAI4().getHand().DrawThis(new Tile(4,8), deck);
			game.getAI4().getHand().DrawThis(new Tile(2,9), deck);
			game.getAI4().getHand().DrawThis(new Tile(4,10), deck);

			game.Announcement();
			return game; 
		}
		public GameMaster secondTurn (GameMaster game, int player) {
			Deck deck = game.getDeck(); 

			if(player == 1)
				game.getAI().getHand().DrawThis(new Tile(3, 1), deck); //can select next drawn tile to enable completion of meld in hand
			else if (player == 2) 
				game.getAI2().getHand().DrawThis(new Tile(4, 1), deck); //can select next drawn tile to enable complex board reuse
			
			else if(player == 3)
				game.getAI3().getHand().DrawThis(new Tile(1, 11), deck); //can select next drawn tile to enable completion of meld on board

			else if (player == 4)
				game.getAI4().getHand().DrawThis(new Tile(5,14), deck);

			return game; 
		}
		
		public GameMaster thirdTurn (GameMaster game, int player) {
			Deck deck = game.getDeck(); 

			if(player == 1)
				game.getAI().getHand().DrawThis(new Tile(2, 2), deck);
			/*else if (player == 2) 
				game.getAI2().getHand().DrawThis(new Tile(14, 14), deck);
			
			else if(player == 3)
				game.getAI3().getHand().DrawThis(new Tile(2, 7), deck);

			else if (player == 4)
				game.getAI4().getHand().DrawThis(new Tile(4, 5), deck);*/

			return game; 
		}
		public int getNumTurns() {
			return this.numTurns;
		}

		
	}
	
	class Scenario2 implements Scenario{
		int numTurns = 1; 
		public GameMaster deal (GameMaster game) {
			Deck deck = game.getDeck(); 
			
			game.addPlayer(2);
			

		//	game.getAI().setIsfirstMeldComplete(true);
			game.getAI2().setIsfirstMeldComplete(true);
	//		game.getAI3().setIsfirstMeldComplete(true);
//			game.getAI4().setIsfirstMeldComplete(true);
			
			Tile[] l = {new Tile(3,4),new Tile(4,4),
					new Tile(1,5),new Tile(5,14)};
			
			game.getAI2().getHand().addTilesToHand(l);
					
			Tile[] l1 = {new Tile(1,1), new Tile(1,2), new Tile(1,3), new Tile(1,4)};
			Tile[] l2 = { new Tile(1,3),new Tile(2,3), new Tile(3,3),new Tile(4,3)};
			Tile[] l3 = { new Tile(3,3),new Tile(3,4), new Tile(3,5),new Tile(3,6)};
			Tile[] l4 = { new Tile(2,3),new Tile(2,4), new Tile(2,5)};
			Tile[] l5 = { new Tile(1,6),new Tile(2,6), new Tile(3,6)};
			Tile[] l6 = { new Tile(4,1),new Tile(4,2), new Tile(4,3),new Tile(4,4)};
			
			
			
			ArrayList<Tile> a = new ArrayList<Tile>();
			a.addAll(Arrays.asList(l1));
			game.getTable().addTiles(a);
			
			ArrayList<Tile> b = new ArrayList<Tile>();
			b.addAll(Arrays.asList(l2));
			game.getTable().addTiles(b);
			
			ArrayList<Tile> c = new ArrayList<Tile>();
			c.addAll(Arrays.asList(l3));
			game.getTable().addTiles(c);
			
			ArrayList<Tile> d = new ArrayList<Tile>();
			d.addAll(Arrays.asList(l4));
			game.getTable().addTiles(d);
			
			ArrayList<Tile> e = new ArrayList<Tile>();
			e.addAll(Arrays.asList(l5));
			game.getTable().addTiles(e);
			
			ArrayList<Tile> f = new ArrayList<Tile>();
			f.addAll(Arrays.asList(l6));
			game.getTable().addTiles(f);
			
			//System.out.println(game.getAI2().getHand());
			game.Announcement();
			return game; 
		}
		public GameMaster secondTurn (GameMaster game, int player) {
			Deck deck = game.getDeck(); 

			
			return game; 
		}
		
		public GameMaster thirdTurn (GameMaster game, int player) {
			Deck deck = game.getDeck(); 


			return game; 
		}
		public int getNumTurns() {
			return this.numTurns;
		}
		
	}
	
	class Scenario3 implements Scenario{
		int numTurns = 1; 
		public GameMaster deal (GameMaster game) {
			Deck deck = game.getDeck(); 
			
			game.addPlayer(2);
			

		//	game.getAI().setIsfirstMeldComplete(true);
			game.getAI2().setIsfirstMeldComplete(true);
	//		game.getAI3().setIsfirstMeldComplete(true);
//			game.getAI4().setIsfirstMeldComplete(true);

			Tile[] l = {new Tile(1,12),
					new Tile(1,13),new Tile(5,14)};
			
			game.getAI2().getHand().addTilesToHand(l);
			
					
			Tile[] l1 = {new Tile(1,11), new Tile(2,11), new Tile(3,11)};
			Tile[] l2 = { new Tile(4,11),new Tile(4,12), new Tile(4,13)};
			
			
			
			ArrayList<Tile> a = new ArrayList<Tile>();
			a.addAll(Arrays.asList(l1));
			game.getTable().addTiles(a);
			
			ArrayList<Tile> b = new ArrayList<Tile>();
			b.addAll(Arrays.asList(l2));
			game.getTable().addTiles(b);
			
	
			
			//System.out.println(game.getAI2().getHand());
			game.Announcement();
			return game; 
		}
		public GameMaster secondTurn (GameMaster game, int player) {
			Deck deck = game.getDeck(); 

			
			return game; 
		}
		
		public GameMaster thirdTurn (GameMaster game, int player) {
			Deck deck = game.getDeck(); 


			return game; 
		}
		public int getNumTurns() {
			return this.numTurns;
		}
		
	}
	
	class Scenario4 implements Scenario{
		int numTurns = 1; 
		public GameMaster deal (GameMaster game) {
			Deck deck = game.getDeck(); 
			
			game.addPlayer(2);
			

		//	game.getAI().setIsfirstMeldComplete(true);
			game.getAI2().setIsfirstMeldComplete(true);
	//		game.getAI3().setIsfirstMeldComplete(true);
//			game.getAI4().setIsfirstMeldComplete(true);

			Tile[] l = {new Tile(2,10),
					new Tile(1,13),new Tile(3,10)};
			
			game.getAI2().getHand().addTilesToHand(l);
			
					
			Tile[] l1 = {new Tile(1,7), new Tile(1,8), new Tile(1,9),new Tile(1,10),new Tile(1,11),new Tile(1,12)};
			
			
			
			
			ArrayList<Tile> a = new ArrayList<Tile>();
			a.addAll(Arrays.asList(l1));
			game.getTable().addTiles(a);
			
		
			
	
			
			//System.out.println(game.getAI2().getHand());
			game.Announcement();
			return game; 
		}
		public GameMaster secondTurn (GameMaster game, int player) {
			Deck deck = game.getDeck(); 

			
			return game; 
		}
		
		public GameMaster thirdTurn (GameMaster game, int player) {
			Deck deck = game.getDeck(); 


			return game; 
		}
		public int getNumTurns() {
			return this.numTurns;
		}
		
	}	
	
	
	/*	

	
	class dealStrategy4 implements Scenario{
		public void deal (Deck deck, Player p) {
			p.getHand().DrawThis(new Tile(1,2), deck);
			p.getHand().DrawThis(new Tile(1,2), deck);
			p.getHand().DrawThis(new Tile(1,2), deck);
			p.getHand().DrawThis(new Tile(1,2), deck);
			p.getHand().DrawThis(new Tile(1,2), deck);
			p.getHand().DrawThis(new Tile(1,2), deck);
			p.getHand().DrawThis(new Tile(1,2), deck);
			p.getHand().DrawThis(new Tile(1,2), deck);
			p.getHand().DrawThis(new Tile(1,2), deck);
			p.getHand().DrawThis(new Tile(1,2), deck);
			p.getHand().DrawThis(new Tile(1,2), deck);
			p.getHand().DrawThis(new Tile(1,2), deck);
			p.getHand().DrawThis(new Tile(1,2), deck);
			p.getHand().DrawThis(new Tile(1,2), deck);		
			}
	}*/
