
public class Controller {

	public static void main(String [ ] args) {
		Deck Deck = new Deck();
		Deck.Shuffle();
		Tile[] DeckofTiles = Deck.getDeck();
		
		System.out.println(DeckofTiles[100].getColor());  
		System.out.println(DeckofTiles[8].getNumber());  
		
	}
	
}
