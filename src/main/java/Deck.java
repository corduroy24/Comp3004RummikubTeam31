
import java.io.IOException;
import java.util.Random;

public class Deck {

	private Tile DeckofTiles[] = new Tile[106]; // deck that we will be using
	public Deck() { // populates the deck with all 106 tiles
		for (int z = 0; z < 2; z++) {
			for (int i = 1; i < 5; i++) {
				for (int j = 1; j < 14; j++) {
					Tile T = new Tile(); 
					DeckofTiles[(i*j)-1] = T.createTile(i, j);
					
				}
			}
		}
		//DeckofTiles[104] = Tile.addWild();
		//DeckofTiles[105] = Tile.addWild();
	}

	public void Shuffle() { // shuffle function uses trading tiles functionality
		for (int i = 0; i < DeckofTiles.length; i++) {
			int index = (int) (Math.random() * DeckofTiles.length);
			Tile temp = DeckofTiles[i];
			DeckofTiles[i] = DeckofTiles[index];
			DeckofTiles[index] = temp; // trades i and index values

		}
	}

	public Tile[] getDeck() {  //return full Deck of tiles
		return DeckofTiles;
	}
	
	public Tile getTile(int x) {  //get Tile using order of tile in deck;
		return DeckofTiles[x];
	}
	
}
