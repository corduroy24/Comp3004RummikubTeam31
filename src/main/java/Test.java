import java.io.IOException;

import junit.framework.TestCase;

public class Test extends TestCase {

	public void testShuffle() throws IOException {  //Tests if shuffle works by comparing first item in array
		boolean different=true;
		Deck Deck = new Deck();	
		Tile[] DeckofTiles = Deck.getDeck();
		Tile T1 = DeckofTiles[0];
		String Color1 = T1.getColor(); 
		int Number1 = T1.getNumber();
		Deck.Shuffle();
		Tile[] DeckofTiles2 = Deck.getDeck();
		Tile T2 = DeckofTiles2[0];
		String Color2 = T2.getColor(); 
		int Number2 = T2.getNumber();
		if ((Number1==Number2)&&(Color1.equals(Color2))) {
			different=false;
		}
		
		assertEquals(different,true);
	}
	
}
