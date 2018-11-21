
public interface InitialHand {
	
	public void deal(Deck deck, Player p); 
	
	public class dealStrategy1 implements InitialHand{
		public void deal (Deck deck, Player p) {
			p.getHand().DrawThis(new Tile(1,2), deck);
		}
	}
	
	public class dealStrategy2 implements InitialHand{
		public void deal (Deck deck, Player p) {
			p.getHand().DrawThis(new Tile(), deck);
		}
	}
	
	public class dealStrategy3 implements InitialHand{
		public void deal (Deck deck, Player p) {
			p.getHand().DrawThis(new Tile(), deck);
		}
	}
	
	public class dealStrategy4 implements InitialHand{
		public void deal (Deck deck, Player p) {
			p.getHand().DrawThis(new Tile(), deck);
		}
	}
	
}
