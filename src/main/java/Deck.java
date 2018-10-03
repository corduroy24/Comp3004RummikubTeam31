import java.util.Random;

public class Deck {
	int length=104;
	Tile DeckofTiles[] = new Tile[length]; // deck that we will be using, no joker functionality

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

	public Tile Draw() {    //Draw a card from the deck then decrease deck size by 1 (also remove card drawn)
		Tile T = DeckofTiles[DeckofTiles.length-1];
		DeckofTiles[DeckofTiles.length-1]=null;
		length=length-1;
		Tile [] DeckofTiles2 = new Tile[length];  //new Deck with right size
		for (int i=0;i<DeckofTiles.length-1;i++) {           //transfer all cards to new Deck
			DeckofTiles2[i]=DeckofTiles[i];;
		}
		DeckofTiles=DeckofTiles2; 
		return T;
		
	}
}
