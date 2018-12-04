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
	
/*	class dealStrategy2 implements Scenario{
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
	}
	
	class dealStrategy3 implements Scenario{
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
	}
	
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
