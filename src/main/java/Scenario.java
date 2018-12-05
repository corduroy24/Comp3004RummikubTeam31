import java.util.ArrayList;
import java.util.Arrays;

public interface Scenario {
	public int numTurns = 0; 
	int maxPlayers = 0; 
	int [] turnOrders = new int[maxPlayers]; 
	public GameMaster deal(GameMaster game); 
	public GameMaster secondTurn (GameMaster game, int player);
	public GameMaster thirdTurn (GameMaster game, int player);

	public int [] getTurnOrder(); 
	public int getNumTurns();
	public int getMaxPlayers();

	
}
	class Scenario1 implements Scenario{
		int numTurns = 8; 
		int maxPlayers = 4; 
		int [] turnOrders = new int[maxPlayers]; 
		public GameMaster deal (GameMaster game) {
			
	    	turnOrders[0] = 1;
	    	turnOrders[1] = 2;
	    	turnOrders[2] = 3;
	    	turnOrders[3] = 4;			
	    	Deck deck = game.getDeck(); 
			game.addPlayer(1);
			game.addPlayer(2);
			game.addPlayer(3);
			game.addPlayer(4);

			game.getAI().setIsfirstMeldComplete(true);
			game.getAI2().setIsfirstMeldComplete(true);
			game.getAI3().setIsfirstMeldComplete(true);
			game.getAI4().setIsfirstMeldComplete(true);
			game.getAI().setCanPlay();
			game.getAI2().setCanPlay();
			game.getAI3().setCanPlay();
			game.getAI4().setCanPlay();

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
		public GameMaster thirdTurn (GameMaster game, int player) {return game;}

		
		public int getNumTurns() {
			return this.numTurns;
		}
		
		public int [] getTurnOrder() {
			return this.turnOrders; 
		}
		public int getMaxPlayers() {return this.maxPlayers;}

		
	}
	
	class Scenario2 implements Scenario{
		int numTurns = 1; 
		int maxPlayers = 1; 
		int [] turnOrders = new int[maxPlayers]; 
		public GameMaster deal (GameMaster game) {
			Deck deck = game.getDeck(); 
			
			game.addPlayer(2);

			game.getAI2().setIsfirstMeldComplete(true);
			
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
		
		public GameMaster thirdTurn (GameMaster game, int player) {return game;}

		public int getNumTurns() {
			return this.numTurns;
		}
		public int [] getTurnOrder() {
			return this.turnOrders; 
		}
		public int getMaxPlayers() {return this.maxPlayers;}

		
	}
	//p2 wins with board reuse 
	class Scenario3 implements Scenario{
		int numTurns = 1; 
		int maxPlayers = 1; 
		int [] turnOrders = new int[maxPlayers]; 
		public GameMaster deal (GameMaster game) {
			Deck deck = game.getDeck(); 
			
			game.addPlayer(2);
			
			game.getAI2().setIsfirstMeldComplete(true);

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
		public GameMaster thirdTurn (GameMaster game, int player) {return game;}

		
		public int getNumTurns() {
			return this.numTurns;
		}
		public int [] getTurnOrder() {
			return this.turnOrders; 
		}
		public int getMaxPlayers() {return this.maxPlayers;}

		
	}
	
	class Scenario4 implements Scenario{
		int numTurns = 1; 
		int maxPlayers = 1; 
		int [] turnOrders = new int[maxPlayers]; 
		public GameMaster deal (GameMaster game) {
			Deck deck = game.getDeck(); 
			
			game.addPlayer(2);
			
			game.getAI2().setIsfirstMeldComplete(true);

			Tile[] l = {new Tile(2,10),
					new Tile(1,13),new Tile(3,10)};
			
			game.getAI2().getHand().addTilesToHand(l);
			
					
			Tile[] l1 = {new Tile(1,7), new Tile(1,8), new Tile(1,9),new Tile(1,10),new Tile(1,11),new Tile(1,12)};
			
			
			ArrayList<Tile> a = new ArrayList<Tile>();
			a.addAll(Arrays.asList(l1));
			game.getTable().addTiles(a);
			

			game.Announcement();
			return game; 
		}
		public GameMaster secondTurn (GameMaster game, int player) {
			Deck deck = game.getDeck(); 
			game.getAI().setIsfirstMeldComplete(true);

			
			return game; 
		}
		public GameMaster thirdTurn (GameMaster game, int player) {return game;}

		
		public int getNumTurns() {
			return this.numTurns;
		}
		public int [] getTurnOrder() {
			return this.turnOrders; 
		}
		public int getMaxPlayers() {return this.maxPlayers;}

		
	}	
	
	// p1 can play one meld on its first turn
	//p1 draws on a subsequent turn
	//p1 can play one meld on a subsequent turn *****not working*****---not adding to hand 
	class Scenario5 implements Scenario{
		int numTurns = 3;
		int maxPlayers = 1; 
		int [] turnOrders = new int[maxPlayers]; 
		public GameMaster deal (GameMaster game) {
			turnOrders[0] = 1; 
		//	turnOrders[1] = 2; 

			Deck deck = game.getDeck(); 
			game.addPlayer(1);
			
			Tile a1[] = {new Tile(1,10), new Tile(1,11), new Tile(1,12), new Tile(2,7), new Tile(3,7), new Tile(2,9)};

			
			game.getAI().getHand().addTilesToHand(a1);
						
			game.Announcement();
			return game; 
		}
		
		public GameMaster secondTurn (GameMaster game, int player) {
			Deck deck = game.getDeck(); 
			game.getAI().getHand().DrawThis(new Tile(1,7), deck);

			return game; 
		}
		public GameMaster thirdTurn (GameMaster game, int player) {
			Deck deck = game.getDeck(); 
			game.getAI().getHand().DrawThis(new Tile(1,7), deck);
			return game;
			
		}

		
		public int getNumTurns() {
			return this.numTurns;
		}
		public int [] getTurnOrder() {
			return this.turnOrders; 
		}
		public int getMaxPlayers() {return this.maxPlayers;}

	}	
	
	//p1 can play several melds on its first turn
	class Scenario6 implements Scenario{
		int numTurns = 3;
		int maxPlayers = 1; 
		int [] turnOrders = new int[maxPlayers]; 
		public GameMaster deal (GameMaster game) {
			turnOrders[0] = 1; 
			Deck deck = game.getDeck(); 
			
			game.addPlayer(1);
			
			Tile a1[] = {new Tile(1,10), new Tile(1,11), new Tile(1,12), new Tile(2,7), new Tile(3,7), new Tile(1,7)};
			Tile a2[] = {new Tile(4,5), new Tile(3,5)};
			

			
			game.getAI().getHand().addTilesToHand(a1);
			game.getAI().getHand().addTilesToHand(a2);

						
			game.Announcement();
			return game; 
		}
		
		public GameMaster secondTurn (GameMaster game, int player) {
			Deck deck = game.getDeck(); 
			
			game.getAI().getHand().DrawThis(new Tile(2,5), deck);

			return game; 
		}
		public GameMaster thirdTurn (GameMaster game, int player) {
			Deck deck = game.getDeck(); 
			//System.out.println(game.getAI().getIsFirstMeldComplete());
			return game;
			
		}

		
		public int getNumTurns() {
			return this.numTurns;
		}
		public int [] getTurnOrder() {
			return this.turnOrders; 
		}
		public int getMaxPlayers() {return this.maxPlayers;}

	}	
	
	//p1 draws on its first turn 
	//p1 plays several melds on a subsequent turn
	class Scenario7 implements Scenario{
		int numTurns = 3;
		int maxPlayers = 1; 
		int [] turnOrders = new int[maxPlayers]; 
		public GameMaster deal (GameMaster game) {
			turnOrders[0] = 1; 
			Deck deck = game.getDeck(); 
			
			game.addPlayer(1);
			
			Tile x1[] = {new Tile(1,8), new Tile(4,6), new Tile(2,8)};
			Tile x2[] = {new Tile(2,9), new Tile(3,10), new Tile(4,7)};
			Tile x3[] = {new Tile(1,1), new Tile(1,2), new Tile(1,3)};
			Tile x4[] = {new Tile(2,1), new Tile(3,1), new Tile(4,1)};
			
			game.getAI().getHand().addTilesToHand(x1);
			game.getAI().getHand().addTilesToHand(x2);
			game.getAI().getHand().addTilesToHand(x3);
			game.getAI().getHand().addTilesToHand(x4);
						
			game.Announcement();
			return game; 
		}
		
		public GameMaster secondTurn (GameMaster game, int player) {
			Deck deck = game.getDeck(); 
			game.getAI().setIsfirstMeldComplete(true);
			game.getAI().getHand().DrawThis(new Tile(2,5), deck);

			return game; 
		}
		public GameMaster thirdTurn (GameMaster game, int player) {
			Deck deck = game.getDeck(); 
			//System.out.println(game.getAI().getIsFirstMeldComplete());
			return game;
			
		}

		
		public int getNumTurns() {
			return this.numTurns;
		}
		public int [] getTurnOrder() {
			return this.turnOrders; 
		}
		public int getMaxPlayers() {return this.maxPlayers;}

	}	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// a player has 30+, and p2 plays 30+
	//p2 wins without board reuse 
	class Scenario8 implements Scenario{
		int numTurns = 2;
		int maxPlayers = 2; 
		int [] turnOrders = new int[maxPlayers]; 
		public GameMaster deal (GameMaster game) {
			turnOrders[0] = 1; 
			turnOrders[1] = 2; 

			Deck deck = game.getDeck(); 
			
			game.addPlayer(1);
			game.addPlayer(2);

			Tile x1[] = {new Tile(1,7), new Tile(1,8), new Tile(2,8), new Tile(1,9), new Tile(1,6)};
			game.getAI().setIsfirstMeldComplete(true);
			Tile x2[] = {new Tile(2,11), new Tile(2,12), new Tile(2,13)};

			
			game.getAI().getHand().addTilesToHand(x1);
			game.getAI2().getHand().addTilesToHand(x2);

						
			game.Announcement();
			return game; 
		}
		
		public GameMaster secondTurn (GameMaster game, int player) {
			Deck deck = game.getDeck(); 
			game.getAI().setIsfirstMeldComplete(true);
			game.getAI().getHand().DrawThis(new Tile(2,5), deck);

			return game; 
		}
		public GameMaster thirdTurn (GameMaster game, int player) {
			Deck deck = game.getDeck(); 
			//System.out.println(game.getAI().getIsFirstMeldComplete());
			return game;
			
		}

		
		public int getNumTurns() {
			return this.numTurns;
		}
		public int [] getTurnOrder() {
			return this.turnOrders; 
		}
		public int getMaxPlayers() {return this.maxPlayers;}

	}	
	
	// a player has 30+, but p2 cant play and draws
	class Scenario9 implements Scenario{
		int numTurns = 2;
		int maxPlayers = 2; 
		int [] turnOrders = new int[maxPlayers]; 
		public GameMaster deal (GameMaster game) {
			turnOrders[0] = 1; 
			//turnOrders[1] = 2; 

			Deck deck = game.getDeck(); 
			game.addPlayer(1);
			game.addPlayer(2);
			game.getAI().setIsfirstMeldComplete(true);
			
			Tile x1[] = {new Tile(1,9), new Tile(1,10), new Tile(1,11)};
			Tile x2[] = {new Tile(1,10), new Tile(1,7), new Tile(2,3)};

			game.getAI().getHand().addTilesToHand(x1);
			game.getAI2().getHand().addTilesToHand(x2);

						
			game.Announcement();
			return game; 
		}
		
		public GameMaster secondTurn (GameMaster game, int player) {
			Deck deck = game.getDeck(); 

			return game; 
		}
		public GameMaster thirdTurn (GameMaster game, int player) {
			Deck deck = game.getDeck(); 
			//System.out.println(game.getAI().getIsFirstMeldComplete());
			return game;
			
		}

		public int getNumTurns() {
			return this.numTurns;
		}
		public int [] getTurnOrder() {
			return this.turnOrders; 
		}
		public int getMaxPlayers() {return this.maxPlayers;}

	}	
	
	//p2 plays with board reuse, because cant win
	class Scenario10 implements Scenario{
		int numTurns = 1;
		int maxPlayers = 1; 
		int [] turnOrders = new int[maxPlayers]; 
		public GameMaster deal (GameMaster game) {
			//turnOrders[0] = 1; 
			//turnOrders[1] = 2; 

			Deck deck = game.getDeck(); 
			//game.addPlayer(1);

			game.addPlayer(2);

			Tile a1[] = {new Tile(1,5), new Tile(2,5), new Tile(3,5)};
			Tile a2[] = {new Tile(1,7), new Tile(1,8), new Tile(1,9)};	
			game.getAI().setIsfirstMeldComplete(true);
			game.getAI2().setIsfirstMeldComplete(true);

			
			Tile a3[] = {new Tile(1,10), new Tile(4,9), new Tile(1,6)};
			Tile a4[] = {new Tile(3,2), new Tile(4,5), new Tile(4,11)};
			
			ArrayList<Tile> listTiles1 = new ArrayList<Tile>(); 
			ArrayList<Tile> listTiles2 = new ArrayList<Tile>(); 

			listTiles1.addAll(Arrays.asList(a1));
			listTiles2.addAll(Arrays.asList(a2));

			
			game.getTable().addTiles(listTiles1); 
			game.getTable().addTiles(listTiles2); 

			game.getAI2().getHand().addTilesToHand(a3);
			game.getAI2().getHand().addTilesToHand(a4);
						
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
		public int [] getTurnOrder() {
			return this.turnOrders; 
		}
		public int getMaxPlayers() {return this.maxPlayers;}

	}	
	
	//p2 can't win and has nothing to play thus draws
	class Scenario11 implements Scenario{
		int numTurns = 1;
		int maxPlayers = 1; 
		int [] turnOrders = new int[maxPlayers]; 
		public GameMaster deal (GameMaster game) {
			//turnOrders[0] = 1; 
			//turnOrders[1] = 2; 

			Deck deck = game.getDeck(); 
			//game.addPlayer(1);

			game.addPlayer(2);

			Tile a2[] = {new Tile(1,5), new Tile(2,5), new Tile(3,5)};
			Tile a1[] = {new Tile(1,7), new Tile(1,8), new Tile(1,9)};	
			game.getAI().setIsfirstMeldComplete(true);
			game.getAI2().setIsfirstMeldComplete(true);

			
			Tile a3[] = {new Tile(1,4), new Tile(4,9), new Tile(1,13)};
			Tile a4[] = {new Tile(3,2), new Tile(4,8), new Tile(4,11)};
			
			ArrayList<Tile> listTiles1 = new ArrayList<Tile>(); 
			ArrayList<Tile> listTiles2 = new ArrayList<Tile>(); 

			listTiles1.addAll(Arrays.asList(a1));
			listTiles2.addAll(Arrays.asList(a2));

			
			game.getTable().addTiles(listTiles1); 
			game.getTable().addTiles(listTiles2); 

			game.getAI2().getHand().addTilesToHand(a3);
			game.getAI2().getHand().addTilesToHand(a4);
						
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
		public int [] getTurnOrder() {
			return this.turnOrders; 
		}
		public int getMaxPlayers() {return this.maxPlayers;}

	}	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// p3 plays 30+ points on its first turn
	class Scenario12 implements Scenario{
		int numTurns = 2;
		int maxPlayers = 2; 
		int [] turnOrders = new int[maxPlayers]; 
		public GameMaster deal (GameMaster game) {
			//turnOrders[0] = 1; 
			//turnOrders[1] = 2; 

			Deck deck = game.getDeck(); 
			game.addPlayer(1);
			game.addPlayer(3);
			for(int i = 0; i < game.getPlayers().size(); i++)
				System.out.println(game.getPlayers().get(i).getName());
			Tile x1[] = {new Tile(1,3), new Tile(1,9), new Tile(1,10), new Tile(1,11)};
			Tile x2[] = {new Tile(1,4), new Tile(3,12), new Tile(3,10), new Tile(3,11),
					new Tile(1,4), new Tile(1,6), new Tile(1,7), new Tile(4,5)};

			
			game.getAI().getHand().addTilesToHand(x1);
			game.getAI3().getHand().addTilesToHand(x2);
						
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
		public int [] getTurnOrder() {
			return this.turnOrders; 
		}
		public int getMaxPlayers() {return this.maxPlayers;}

	}	
	
	//   p3 plays 30+ on a subsequent turn
	//   other player has 3 fewer tiles, p3 plays all tiles it can BUT does not reuse the board
	class Scenario13 implements Scenario{
		int numTurns = 2;
		int maxPlayers = 2; 
		int [] turnOrders = new int[maxPlayers]; 
		public GameMaster deal (GameMaster game) {
			//turnOrders[0] = 1; 
			//turnOrders[1] = 2; 

			Deck deck = game.getDeck(); 
			game.addPlayer(1);
			game.addPlayer(3);

			game.getAI().setIsfirstMeldComplete(true);
			game.getAI3().setIsfirstMeldComplete(true);

			Tile x1[] = {new Tile(3,12), new Tile(3,10), new Tile(3,11), new Tile(1,13)};

			Tile x2[] = {new Tile(1,3), new Tile(1,9), new Tile(1,11), new Tile(1,10), new Tile(4,6), new Tile(2,9),
					new Tile(1,5), new Tile(3,4)};
			
			game.getAI().getHand().addTilesToHand(x1);
			game.getAI3().getHand().addTilesToHand(x2);
						
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
		public int [] getTurnOrder() {
			return this.turnOrders; 
		}
		public int getMaxPlayers() {return this.maxPlayers;}

	}	
	
	//   no other player has 3 fewer tiles, p3 plays only tiles requiring board reuse
	//    p3 wins with board reuse
	class Scenario14 implements Scenario{
		int numTurns = 2;
		int maxPlayers = 2; 
		int [] turnOrders = new int[maxPlayers]; 
		public GameMaster deal (GameMaster game) {
			//turnOrders[0] = 1; 
			//turnOrders[1] = 2; 

			Deck deck = game.getDeck(); 
			game.addPlayer(1);
			game.addPlayer(3);

			game.getAI().setIsfirstMeldComplete(true);
			game.getAI3().setIsfirstMeldComplete(true);

			
			Tile x1[] = {new Tile(1,9), new Tile(1,10), new Tile(1,11), new Tile(4,6), new Tile(3,6), new Tile(2,6),
					new Tile(1,5), new Tile(3,4)};
			Tile x2[] = {new Tile(1,12), new Tile(1,13), new Tile(1,6), new Tile(1,8)};
			
			game.getAI().getHand().addTilesToHand(x1);
			game.getAI3().getHand().addTilesToHand(x2);
						
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
		public int [] getTurnOrder() {
			return this.turnOrders; 
		}
		public int getMaxPlayers() {return this.maxPlayers;}

	}	
	
	
	//  no other player has 3 fewer tiles, but p3 can't play and has to draw
	class Scenario15 implements Scenario{
		int numTurns = 2;
		int maxPlayers = 2; 
		int [] turnOrders = new int[maxPlayers]; 
		public GameMaster deal (GameMaster game) {
			//turnOrders[0] = 1; 
			//turnOrders[1] = 2; 

			Deck deck = game.getDeck(); 
			game.addPlayer(1);
			game.addPlayer(3);

			game.getAI().setIsfirstMeldComplete(true);
			game.getAI3().setIsfirstMeldComplete(true);

			
			Tile x1[] = {new Tile(1,9), new Tile(1,10), new Tile(1,11), new Tile(4,6), new Tile(3,6), new Tile(2,6),
					new Tile(1,5), new Tile(3,4)};
			Tile x2[] = {new Tile(1,7), new Tile(2,3), new Tile(4,9), new Tile(3,7)};
			
			game.getAI().getHand().addTilesToHand(x1);
			game.getAI3().getHand().addTilesToHand(x2);
						
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
		public int [] getTurnOrder() {
			return this.turnOrders; 
		}
		public int getMaxPlayers() {return this.maxPlayers;}

	}	
	   //other player has 3 fewer tiles, but p3 has to draw as it can't play a thing
	class Scenario16 implements Scenario{
		int numTurns = 2;
		int maxPlayers = 2; 
		int [] turnOrders = new int[maxPlayers]; 
		public GameMaster deal (GameMaster game) {
			//turnOrders[0] = 1; 
			//turnOrders[1] = 2; 

			Deck deck = game.getDeck(); 
			game.addPlayer(1);
			game.addPlayer(3);

			game.getAI().setIsfirstMeldComplete(true);
			game.getAI3().setIsfirstMeldComplete(true);

			
			Tile x1[] = {new Tile(3,12), new Tile(3,10), new Tile(3,11), new Tile(1,13)};
			Tile x2[] = {new Tile(2,4), new Tile(1,9), new Tile(1,4), new Tile(1,10), new Tile(4,6), new Tile(2,9),
					new Tile(1,5), new Tile(3,7)};
			
			game.getAI().getHand().addTilesToHand(x1);
			game.getAI3().getHand().addTilesToHand(x2);
						
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
		public int [] getTurnOrder() {
			return this.turnOrders; 
		}
		public int getMaxPlayers() {return this.maxPlayers;}

	}	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//play joker by putting on the board as part of a meld from hand
	class Scenario17 implements Scenario{
		int numTurns = 1;
		int maxPlayers = 1; 
		int [] turnOrders = new int[maxPlayers]; 
		public GameMaster deal (GameMaster game) {
			//turnOrders[0] = 2; 

			Deck deck = game.getDeck(); 
			game.addPlayer(2);

		//	game.getAI2().setIsfirstMeldComplete(true);

			
			Tile joker = new Tile(14,14);
			Tile[] x = {new Tile(1,6),new Tile(1,7),
						new Tile(1,10), new Tile(1,11), joker};
			game.getAI2().getHand().addTilesToHand(x);
			ArrayList<Tile> a = new ArrayList<Tile>();
			
			Tile[] x1 = {new Tile(1,1),new Tile(1,2),new Tile(1,3)};
			a.addAll(Arrays.asList(x1));
			game.getTable().addTiles(a);
			
						
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

		public int getNumTurns() {return this.numTurns;}
		public int [] getTurnOrder() {return this.turnOrders; }
		public int getMaxPlayers() {return this.maxPlayers;}

	}	
	
	
	//show valid substitution of a joker played on the table (where joker is a specific card)	
	//Issue: Meld of {joker, (3,5), (3,3)} does not display
	class Scenario18 implements Scenario{
		int numTurns = 1;
		int maxPlayers = 1; 
		int [] turnOrders = new int[maxPlayers]; 
		public GameMaster deal (GameMaster game) {
			//turnOrders[0] = 2; 

			Deck deck = game.getDeck(); 
			game.addPlayer(2);

			game.getAI2().setIsfirstMeldComplete(true);

			Tile joker = new Tile(14,14);
			Tile[] l = {new Tile(1,5),new Tile(1,6),
						new Tile(1,7), new Tile(3,5),
						new Tile(3,3), new Tile(3,1)};
			
			Tile[] l1 = {joker, new Tile(2,1), new Tile(1,1)};
			joker.setJokerPoint(1);
			joker.setJokerColor(3);

			game.getAI2().getHand().addTilesToHand(l);

			ArrayList<Tile> a = new ArrayList<Tile>();
			a.addAll(Arrays.asList(l1));
			game.getTable().addTiles(a);
						
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

		public int getNumTurns() {return this.numTurns;}
		public int [] getTurnOrder() {return this.turnOrders; }
		public int getMaxPlayers() {return this.maxPlayers;}

	}	
	
	//show valid substitution of a joker played on the table (where joker could be one of 2 cards)		
	//Issue: adds 2 jokers to the game 
	class Scenario19 implements Scenario{
			int numTurns = 1;
			int maxPlayers = 1; 
			int [] turnOrders = new int[maxPlayers]; 
			public GameMaster deal (GameMaster game) {
				//turnOrders[0] = 2; 

				Deck deck = game.getDeck(); 
				game.addPlayer(2);

				game.getAI2().setIsfirstMeldComplete(true);

				Tile joker = new Tile(14,14);
				Tile[] l = {new Tile(1,5),new Tile(1,6),
							new Tile(1,7), new Tile(3,5),
							new Tile(3,4), new Tile(1,1)};
				
				Tile[] l1 = {joker, new Tile(2,1), new Tile(1,1)};
				
				joker.setJokerPoint(1);
				joker.setJokerColor(1);

				game.getAI2().getHand().addTilesToHand(l);

				ArrayList<Tile> a = new ArrayList<Tile>();
				a.addAll(Arrays.asList(l1));
				game.getTable().addTiles(a);
							
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

			public int getNumTurns() {return this.numTurns;}
			public int [] getTurnOrder() {return this.turnOrders; }
			public int getMaxPlayers() {return this.maxPlayers;}

		}	
	
	//show valid substitution of a joker played on the table (where joker could be one of 2 cards)		
	//Issue: says AI4 played, but doesnt display meld to table 
	class Scenario20 implements Scenario{
			int numTurns = 1;
			int maxPlayers = 1; 
			int [] turnOrders = new int[maxPlayers]; 
			public GameMaster deal (GameMaster game) {
				//turnOrders[0] = 2; 

				Deck deck = game.getDeck(); 
				
				game.addPlayer(4);

				game.getAI4().setIsfirstMeldComplete(true);

				Tile[] l = {new Tile(3,2),new Tile(2,2),
							new Tile(1,9), new Tile(1,2),
							new Tile(1,7),new Tile(1,8),new Tile(1,11),new Tile(1,9)};
				
				Tile[] l1 = {new Tile(2,2), new Tile(1,2), new Tile(3,2)};

				game.getAI4().getHand().addTilesToHand(l);

				ArrayList<Tile> a = new ArrayList<Tile>();
				a.addAll(Arrays.asList(l1));
				game.getTable().addTiles(a);
							
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

			public int getNumTurns() {return this.numTurns;}
			public int [] getTurnOrder() {return this.turnOrders; }
			public int getMaxPlayers() {return this.maxPlayers;}

		}	
	
	class Scenario21 implements Scenario{
		int numTurns = 1;
		int maxPlayers = 1; 
		int [] turnOrders = new int[maxPlayers]; 
		public GameMaster deal (GameMaster game) {
			//turnOrders[0] = 2; 

			Deck deck = game.getDeck(); 
			
			game.addPlayer(4);

			game.getAI4().setIsfirstMeldComplete(true);

			Tile[] l = {new Tile(1,1),new Tile(1,2),
						new Tile(1,9), new Tile(1,3),
						new Tile(1,7),new Tile(1,8),new Tile(1,11)};
			
			Tile[] l1 = {new Tile(1,2), new Tile(2,2), new Tile(3,2)};

			game.getAI4().getHand().addTilesToHand(l);

			ArrayList<Tile> a = new ArrayList<Tile>();
			a.addAll(Arrays.asList(l1));
			game.getTable().addTiles(a);
						
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

		public int getNumTurns() {return this.numTurns;}
		public int [] getTurnOrder() {return this.turnOrders; }
		public int getMaxPlayers() {return this.maxPlayers;}

	}	
	
	class Scenario22 implements Scenario{
		int numTurns = 1;
		int maxPlayers = 1; 
		int [] turnOrders = new int[maxPlayers]; 
		public GameMaster deal (GameMaster game) {
			//turnOrders[0] = 2; 

			Deck deck = game.getDeck(); 
			
			game.addPlayer(2);

			game.getAI2().setIsfirstMeldComplete(true);

			Tile[] l = {new Tile(1,4),new Tile(2,4),
						new Tile(3,4)};
			Tile[] l2 = { new Tile(1,5),
					new Tile(1,6),new Tile(1,7)};
			
			Tile[] l1 = {new Tile(1,4), new Tile(4,4), new Tile(1,8)};

			game.getAI2().getHand().addTilesToHand(l1);

			ArrayList<Tile> a = new ArrayList<Tile>();
			a.addAll(Arrays.asList(l));
			game.getTable().addTiles(a);
			
			ArrayList<Tile> b = new ArrayList<Tile>();
			a.addAll(Arrays.asList(l2));
			game.getTable().addTiles(b);
						
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

		public int getNumTurns() {return this.numTurns;}
		public int [] getTurnOrder() {return this.turnOrders; }
		public int getMaxPlayers() {return this.maxPlayers;}

	}	
	
	class Scenario24 implements Scenario{
		int numTurns = 1;
		int maxPlayers = 1; 
		int [] turnOrders = new int[maxPlayers]; 
		public GameMaster deal (GameMaster game) {
			//turnOrders[0] = 2; 

			Deck deck = game.getDeck(); 
			
			game.addPlayer(2);

			game.getAI2().setIsfirstMeldComplete(true);

			Tile[] l = {new Tile(2,2),new Tile(3,2),
						new Tile(1,9), new Tile(3,3),
						new Tile(1,7),new Tile(1,8),new Tile(1,11)};
			
			Tile[] l1 = {new Tile(1,2), new Tile(2,2), new Tile(3,2), new Tile(4,2)};

			game.getAI2().getHand().addTilesToHand(l);

			ArrayList<Tile> a = new ArrayList<Tile>();
			a.addAll(Arrays.asList(l1));
			game.getTable().addTiles(a);
						
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

		public int getNumTurns() {return this.numTurns;}
		public int [] getTurnOrder() {return this.turnOrders; }
		public int getMaxPlayers() {return this.maxPlayers;}

	}	
	
	class Scenario25 implements Scenario{
		int numTurns = 1;
		int maxPlayers = 1; 
		int [] turnOrders = new int[maxPlayers]; 
		public GameMaster deal (GameMaster game) {
			//turnOrders[0] = 2; 

			Deck deck = game.getDeck(); 
			
			game.addPlayer(2);

			game.getAI2().setIsfirstMeldComplete(true);

			Tile[] l = {new Tile(2,5),new Tile(2,6),
						new Tile(1,9), new Tile(3,3),
						new Tile(1,7),new Tile(1,8),new Tile(1,11)};
			
			Tile[] l1 = {new Tile(2,4), new Tile(2,5), new Tile(2,6), new Tile(2,7)};

			game.getAI2().getHand().addTilesToHand(l);

			ArrayList<Tile> a = new ArrayList<Tile>();
			a.addAll(Arrays.asList(l1));
			game.getTable().addTiles(a);
						
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

		public int getNumTurns() {return this.numTurns;}
		public int [] getTurnOrder() {return this.turnOrders; }
		public int getMaxPlayers() {return this.maxPlayers;}

	}	
	
	
	
	
	