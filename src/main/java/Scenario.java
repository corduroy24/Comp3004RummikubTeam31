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

			
			Tile t6[] = {new Tile(1,4), new Tile(1,5), new Tile(1,8)}; 
			ArrayList<Tile> listTiles = new ArrayList<Tile>(); 
			for(int i = 0; i< t6.length; i++) listTiles.add(t6[i]); 
			
			game.getTable().addTiles(listTiles); 

			game.getAI().getHand().DrawThis(new Tile(1,9), deck);
			game.getAI().getHand().DrawThis(new Tile(4,5), deck);
			game.getAI().getHand().DrawThis(new Tile(2,3), deck);
			game.getAI().getHand().DrawThis(new Tile(2,9), deck);
			game.getAI().getHand().DrawThis(new Tile(3,10), deck);
			game.getAI().getHand().DrawThis(new Tile(4,7), deck);

			game.getAI2().getHand().DrawThis(new Tile(1,4), deck);
			game.getAI2().getHand().DrawThis(new Tile(1,5), deck);
			game.getAI2().getHand().DrawThis(new Tile(1,6), deck);
			game.getAI2().getHand().DrawThis(new Tile(1,5), deck);
			game.getAI2().getHand().DrawThis(new Tile(2,5), deck);
			//game.getAI2().getHand().DrawThis(new Tile(3,5), deck);
			
			game.getAI3().getHand().DrawThis(new Tile(1,4), deck);
			game.getAI3().getHand().DrawThis(new Tile(1,5), deck);
			game.getAI3().getHand().DrawThis(new Tile(1,6), deck);
			game.getAI3().getHand().DrawThis(new Tile(1,5), deck);
			game.getAI3().getHand().DrawThis(new Tile(2,5), deck);

			game.getAI4().getHand().DrawThis(new Tile(3,4), deck);
			game.getAI4().getHand().DrawThis(new Tile(4,5), deck);
			game.getAI4().getHand().DrawThis(new Tile(3,6), deck);
			game.getAI4().getHand().DrawThis(new Tile(4,8), deck);
			game.getAI4().getHand().DrawThis(new Tile(2,9), deck);
			//game.Announcement();
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
