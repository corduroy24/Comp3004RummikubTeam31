
import java.io.IOException;
import java.util.Random;

public class Deck {

	private Tile Deck[] = new Tile[106]; // deck that we will be using

	private static final Random generator = new Random();

	public Deck() { // populates the deck with all 106 tiles
		for (int z = 1; z < 3; z++) {
			for (int i = 1; i < 5; i++) {
				for (int j = 1; j < 14; j++) {
					Deck[i * j] = Tile.createTile(i, j);
				}
			}

		}

		Deck[104] = Tile.addWild();
		Deck[105] = Tile.addWild();
	}

	public void Shuffle() { // shuffle function uses trading tiles functionality
		for (int i = 0; i < Deck.length; i++) {
			int index = (int) (Math.random() * Deck.length);
			Tile temp = Deck[i];
			Deck[i] = Deck[index];
			Deck[index] = temp; // trades i and index values

		}
	}

}
