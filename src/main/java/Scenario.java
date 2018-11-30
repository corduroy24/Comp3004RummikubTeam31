import java.util.ArrayList;

public interface Scenario {
	
	public GameMaster deal(GameMaster game); 
	public GameMaster secondTurn (GameMaster game);
}
	//cannot play
	class Scenario1 implements Scenario{ 
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
			ArrayList<Tile> listTiles = new ArrayList<Tile>(); 
			for(int i = 0; i< t6.length; i++) listTiles.add(t6[i]); 
			
			game.getTable().addTiles(listTiles); 
			
			System.out.println("-------------" +game.getTable().getNumberOfTile());

			game.getAI().getHand().DrawThis(new Tile(1,1), deck);
			game.getAI().getHand().DrawThis(new Tile(2,1), deck);
			game.getAI().getHand().DrawThis(new Tile(3,1), deck);
			game.getAI().getHand().DrawThis(new Tile(1,3), deck);
			game.getAI().getHand().DrawThis(new Tile(1,7), deck);
			game.getAI().getHand().DrawThis(new Tile(4,7), deck);

			game.getAI2().getHand().DrawThis(new Tile(1,4), deck);
			game.getAI2().getHand().DrawThis(new Tile(1,5), deck);
			game.getAI2().getHand().DrawThis(new Tile(1,6), deck);
			game.getAI2().getHand().DrawThis(new Tile(1,5), deck);
			game.getAI2().getHand().DrawThis(new Tile(2,5), deck);
			game.getAI2().getHand().DrawThis(new Tile(3,5), deck);
			
			game.getAI3().getHand().DrawThis(new Tile(2,7), deck);
			game.getAI3().getHand().DrawThis(new Tile(2,8), deck);
			game.getAI3().getHand().DrawThis(new Tile(2,9), deck);
			game.getAI3().getHand().DrawThis(new Tile(2,10), deck);
			game.getAI3().getHand().DrawThis(new Tile(3,5), deck);

			game.getAI4().getHand().DrawThis(new Tile(3,4), deck);
			game.getAI4().getHand().DrawThis(new Tile(4,5), deck);
			game.getAI4().getHand().DrawThis(new Tile(3,6), deck);
			game.getAI4().getHand().DrawThis(new Tile(4,8), deck);
			game.getAI4().getHand().DrawThis(new Tile(2,9), deck);
			
			game.Announcement();
			return game; 
		}
		public GameMaster secondTurn (GameMaster game) {
			
			
			return game; 
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
