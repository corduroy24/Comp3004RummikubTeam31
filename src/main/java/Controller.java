import java.util.ArrayList;

public class Controller {

	public static void main(String [ ] args) {
		Deck Deck = new Deck();
		Deck.Shuffle();
		ArrayList<Tile> DeckofTiles = Deck.getDeck();
		
		System.out.println(DeckofTiles.get(100).getColor());  //examples of how to use getcolour and getnumber functions
		System.out.println(DeckofTiles.get(6).getNumber());  //take these off, this is only for explaining sake
		Tile T = Deck.Draw();  //Test drawing
		System.out.println(T.getColor());        //Print tile drawn
		System.out.println(T.getNumber());  
		System.out.println(Deck.length());   //Test length decrease
		Tile T1 = Deck.Draw();  //Test drawing
		System.out.println(Deck.length());
		
		//System.out.println("Starting game");
		//HumanPlayer user = new HumanPlayer();
		
		
		
	}
	
}
