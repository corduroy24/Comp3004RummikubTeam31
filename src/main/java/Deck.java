import java.util.*;

public class Deck{
	ArrayList<Tile> DeckofTiles = new ArrayList<Tile>(); // deck that we will be using, no joker functionality

	int count = 0;

	public Deck() { // populates the deck with all 106 tiles
		for (int z = 0; z < 2; z++) {
			for (int i = 1; i < 5; i++) {
				for (int j = 1; j < 14; j++) {
					Tile T = new Tile(i, j);

					DeckofTiles.add(count,T);
					count++;

				}
			}
		}
		Tile T = new Tile(14, 14);     //  THESE ARE JOKERS AND WILL BE USED IN ITERATION 2
		Tile T1 = new Tile(14,14);
		DeckofTiles.add(T);
	    DeckofTiles.add(T1);
	
	}
	public void add(Tile t) {
		int random = (int) (Math.random()*DeckofTiles.size());
		DeckofTiles.add(random,t);
	}

	
	public void Shuffle() { 
		Collections.shuffle(DeckofTiles);
		}
	

	public ArrayList<Tile> getDeck() { // return full Deck of tiles
		return DeckofTiles;
	}

	public Tile getTile(int x) { // get Tile using order of tile in deck;
		return DeckofTiles.get(x);
	}

	public Tile Draw() {    //Draw a card from the deck then decrease deck size by 1 (also remove card drawn)
		Tile T = DeckofTiles.get(DeckofTiles.size()-1);
		DeckofTiles.remove(DeckofTiles.size()-1);
		DeckofTiles.trimToSize();
		
		return T;
		
	}
	
	public int length() {
		return DeckofTiles.size();
	}
}
