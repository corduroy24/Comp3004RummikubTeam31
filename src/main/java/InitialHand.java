
public interface InitialHand {
	
	public void deal(Deck deck, Player p); 
}
	
	class dealStrategy1 implements InitialHand{
		public void deal (Deck deck, Player p) {
			p.getHand().DrawThis(new Tile(1,2), deck);
		}
	}
	
	class dealStrategy2 implements InitialHand{
		public void deal (Deck deck, Player p) {
			p.getHand().DrawThis(new Tile(), deck);
		}
	}
	
	class dealStrategy3 implements InitialHand{
		public void deal (Deck deck, Player p) {
			p.getHand().DrawThis(new Tile(), deck);
		}
	}
	
	class dealStrategy4 implements InitialHand{
		public void deal (Deck deck, Player p) {
			p.getHand().DrawThis(new Tile(), deck);
		}
	}

