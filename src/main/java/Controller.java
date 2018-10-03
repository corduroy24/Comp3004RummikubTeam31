
public class Controller {

	public static void main(String [ ] args) {
		Deck Deck = new Deck();
		Deck.Shuffle();
		Tile[] DeckofTiles = Deck.getDeck();
		
		System.out.println(DeckofTiles[100].getColor());  //examples of how to use getcolour and getnumber functions
		System.out.println(DeckofTiles[8].getNumber());  //take these off, this is only for explaining sake
		
	}
	
}
