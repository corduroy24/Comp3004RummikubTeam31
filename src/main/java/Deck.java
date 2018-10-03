
import java.util.Random;

public class Deck {

	private Tile DeckofTiles[] = new Tile[104]; // deck that we will be using, no joker functionality
	int count = 0;

	public Deck() { // populates the deck with all 106 tiles
		for (int z = 0; z < 2; z++) {
			for (int i = 1; i < 5; i++) {
				for (int j = 1; j < 14; j++) {
					Tile T = new Tile(i, j);

					DeckofTiles[count] = T;
					count++;

				}
			}
		}
	//	Tile T = new Tile();       THESE ARE JOKERS AND WILL BE USED IN ITERATION 2
	//	DeckofTiles[104] = T;
	//	DeckofTiles[105] = T;
	
	}

	public void Shuffle() { // shuffle function uses trading tiles functionality
		for (int i = 0; i < DeckofTiles.length; i++) {
			int index = (int) (Math.random() * DeckofTiles.length);
			Tile temp = DeckofTiles[i];
			DeckofTiles[i] = DeckofTiles[index];
			DeckofTiles[index] = temp; // trades i and index values

		}
	}

	public Tile[] getDeck() { // return full Deck of tiles
		return DeckofTiles;
	}

	public Tile getTile(int x) { // get Tile using order of tile in deck;
		return DeckofTiles[x];
	}

}
