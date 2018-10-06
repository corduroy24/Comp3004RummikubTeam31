import java.util.ArrayList;

public class Controller {
	
	public static void main(String [ ] args) {
		Deck Deck = new Deck();
		Deck.Shuffle();
		ArrayList<Tile> DeckofTiles = Deck.getDeck();
		
		System.out.println("Starting game");
		Player user = new Player("User", 1);
		Player.hand.drawFirst14(Deck);
		Player.hand.HandReader();
		
		
		
	}
	
}
