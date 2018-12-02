import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PlayerHand {

	String name = "";

	PlayerHand(String x) {
		// super();
		name = x;
	}

	private ArrayList<Tile> hand = new ArrayList<Tile>();

	public void clear() {
		hand.clear();
	}
	
	public boolean containJoker() {
		for(int i =0; i <hand.size();i++) {
			if(hand.get(i).isJoker()) return true;
		}
		
		return false;
	}
	
	public void DrawThis(Tile x, Deck y) {
		for (int i=0;i<y.DeckofTiles.size();i++) {
			if ((x.getColor()==y.DeckofTiles.get(i).getColor())&&(x.getNumber()==y.DeckofTiles.get(i).getNumber())) {
				Tile T = y.DeckofTiles.get(i);
				y.DeckofTiles.remove(i);
				y.DeckofTiles.trimToSize();
				hand.add(T);
				break;
			}
		}
		
	}
	
	public void drawFirst14(Deck x) {
		for (int i = 0; i < 14; i++) {
			Tile T= x.Draw();
			if(containJoker() && T.isJoker()) {
				x.add(T);
				i--;
			}
			else hand.add(T);
		}
	}
	
	public void setPlayerHand(ArrayList<Tile> t ) {
		hand = new ArrayList<Tile>();
		for(int i =0; i < t.size();i++) hand.add(t.get(i));
	}
	

	public boolean hasJoker() {
		for (int i=0;i<sizeOfHand();i++) {
			if (getTile(i).isJoker()==true){
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Tile> getTiles() {
		return this.hand;
	}

	public Tile getTile(int index) {
		if(index<=this.hand.size()-1)
		{
			return this.hand.get(index);
		}
		else
		{
			System.out.println("getTile returned null");
			return null;
		}
	}

	public void addTileToHand(Tile newTile) {
		hand.add(newTile);
	}
	
	public void addTilesToHand(Tile[] newTiles) {
		hand.addAll(Arrays.asList(newTiles));
	}
	
	public void addTilesToHand(ArrayList<Tile> newTiles) {
		hand.addAll(newTiles);
	}
	public int sizeOfHand() {
		return hand.size();
	}

	public void playTileFromHand(Tile tileToPlay) {
		// this.playerStrategy.playTiles(hand, tileToPlay);
		hand.remove(tileToPlay);
		hand.trimToSize();

	}

	public boolean isEmpty() {
		return hand.isEmpty();
	}

	public void HandReader() {
		//sortTilesByColour();
		ArrayList<Tile> sample = hand;
		//Collections.sort(sample, new SortByX());
		String output = "";
		
		
		if (isEmpty()) {
			System.out.println("Player: " + name + " has no tiles");
		} else {
			System.out.println("Displaying Player: " + name + "'s tiles");
			for (int i = 0; i < sample.size(); i++) {
				String Color = "";
				if (sample.get(i).getColor().equals("O")) {
					Color = "Orange";
				}
				if (sample.get(i).getColor().equals("B")) {
					Color = "Blue";
				}
				if (sample.get(i).getColor().equals("G")) {
					Color = "Green";
				}
				if (sample.get(i).getColor().equals("R")) {
					Color = "Red";
				}
				if (sample.get(i).getColor().equals("J")) {
					Color = "Joker";
				}
				output += Color + " " + sample.get(i).getNumber() + "; ";
			}
		}
		System.out.println(output+"\n");
	}

	public void sortTilesByNumber() {// sorts tiles from least to greatest
		ArrayList<Tile> sortedHand = this.hand;

		Collections.sort(sortedHand, new SortByX());

	}

	// inspired from
	// https://github.com/Ratchette/Rummikub/blob/master/src/rummikub/Hand.java
	public void sortTilesByColour() {
		ArrayList<PlayerHand> handsByColour = new ArrayList<PlayerHand>();

		ArrayList<Tile> tempHand = new ArrayList<Tile>();
		handsByColour = seperateByColour();

		// sorts the hand by number then adds to hand
		for (int i = 0; i < 5; i++) {
			handsByColour.get(i).sortTilesByNumber();
			tempHand.addAll(handsByColour.get(i).getTiles());
		}

		this.hand = tempHand;
	}

	// inspired from:
	// https://github.com/Ratchette/Rummikub/blob/master/src/rummikub/Hand.java
	public ArrayList<PlayerHand> seperateByColour() {
		ArrayList<PlayerHand> handsByColour = new ArrayList<PlayerHand>();
		Tile currTile;

		for (int i = 0; i < 5; i++)
			handsByColour.add(new PlayerHand("" + i));

		// seperates the hand by colour
		for (int i = 0; i < this.sizeOfHand(); i++) {
			currTile = this.getTile(i);

			if (currTile.getColor() == "R")
				handsByColour.get(0).addTileToHand(currTile);

			else if (currTile.getColor() == "B")
				handsByColour.get(1).addTileToHand(currTile);

			else if (currTile.getColor() == "G")
				handsByColour.get(2).addTileToHand(currTile);

			else if (currTile.getColor() == "O")
				handsByColour.get(3).addTileToHand(currTile);
			
			else if (currTile.getColor() == "J")
				handsByColour.get(4).addTileToHand(currTile);
		}
		return handsByColour;
	}

	/*
	 * public ArrayList<List> getInitialMeld(){ int score = 0; int largestScore = 0;
	 * 
	 * ArrayList<Tile> currPossibility;
	 * 
	 * currPossibility = this.hand. for(int i = 0; i < 5; i++) {
	 * 
	 * } return null; }
	 */

	private void removeDoubles() {
		// TODO Auto-generated method stub
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		ArrayList<Tile> x = new ArrayList<Tile>();
		x.addAll(this.hand);
		this.hand.clear();
		this.hand.addAll(x);

		tiles = this.hand;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////
	//take out PlayerHand x as a parameter 
	public boolean runFound(PlayerHand x) { // THIS CHECKS IF HAND HAS ANY POSSIBILITIES FOR RUNS

		if (x.hand.isEmpty()) {
			return false;
		}
		x.removeDoubles();
		ArrayList<Tile> y = new ArrayList<Tile>();
		y.add(x.getTile(0));
		int count = 1;
		for (int i = 1; i < x.sizeOfHand(); i++) {
			if ((x.getTile(i).getNumber()) - (y.get(count - 1).getNumber()) == 1) {
				y.add(x.getTile(i));
				count++;
				if ((count == 3) && (x.sizeOfHand() - 1 == i)) {
					return true;
				}
			} else if (y.size() > 2) {
				return true;
			} else if ((i == x.sizeOfHand() - 1) && (y.size() > 2)) {
				return true;
			} else if ((i == x.sizeOfHand() - 1) && (y.size() < 3)) {
				return false;
			} else if (i < x.sizeOfHand() - 1) {
				y.clear();
				count = 1;

				y.add(x.getTile(i));

			}
		}
		return false;
	}

	public ArrayList<Tile> findRun(PlayerHand x) { // Gives you the best run it can detect. (FOR AI)
		ArrayList<PlayerHand> colourSep = x.seperateByColour();

		ArrayList<Tile>[] y = new ArrayList[4];
		for (int i = 0; i < 4; i++) {
			y[i] = new ArrayList<Tile>();
		}
		int count5 = 0;
		for (int i = 0; i < 4; i++) {
			colourSep.get(i).sortTilesByNumber();
			colourSep.get(i).removeDoubles();
			if ((!colourSep.get(i).hand.isEmpty()) && (runFound(colourSep.get(i)) == false)) {
				count5++;
			}
		}
		if (count5 == 4) {
			// System.out.println("NO GROUPS FOUND");
		} else {
			for (int z = 0; z < 4; z++) {
				while (colourSep.get(z).hand.isEmpty()) {
					z++;
					// System.out.println(z); was testing something, ignore this
					if (z > 3) {
						if ((y[0].size() >= y[1].size()) && (y[0].size() >= y[2].size())
								&& (y[0].size() >= y[3].size())) {
							return y[0];
						} else if ((y[1].size() >= y[2].size()) && (y[1].size() >= y[3].size())) {
							return y[1];
						} else if (y[2].size() > y[3].size()) {
							return y[2];
						} else {
							return y[3];
						}
					}
				}
				y[z].add(colourSep.get(z).getTile(0));
				int count = 1;
				for (int i = 1; i < colourSep.get(z).sizeOfHand(); i++) { // System.out.println(y.get(z).get(0));
					if ((colourSep.get(z).getTile(i).getNumber()) - (y[z].get(count - 1).getNumber()) == 1) {
						y[z].add(colourSep.get(z).getTile(i));
						count++;

					}

					else if (i < colourSep.get(z).sizeOfHand() - 1) {
						y[z].clear();
						count = 1;

						y[z].add(colourSep.get(z).getTile(i));
					}
				}

			}

		}
		if ((y[0].size() >= y[1].size()) && (y[0].size() >= y[2].size()) && (y[0].size() >= y[3].size())) {
			return y[0];
		} else if ((y[1].size() >= y[2].size()) && (y[1].size() >= y[3].size())) {
			return y[1];
		} else if (y[2].size() > y[3].size()) {
			return y[2];
		} else {
			return y[3];
		}
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	public boolean foundSet(PlayerHand x) { // returns true if hand has possibility for any sets
		ArrayList<PlayerHand> colourSep = x.seperateByColour();
		
		for (int i = 0; i < 4; i++) {
			colourSep.get(i).sortTilesByNumber();
			colourSep.get(i).removeDoubles();

		}
		int j=0;
		for (int i = 0; i < 4; i++,j++) {
			int count = 0;
			for (j=j ; j < 4; j++) {
				for (int z = 0; z < colourSep.get(j).sizeOfHand(); z++) {
					for (int y = 0; y < colourSep.get(i).sizeOfHand(); y++) {
						if (colourSep.get(i).getTile(y).getNumber() == colourSep.get(j).getTile(z).getNumber()) {
							count++;
							if (count > 2) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	public ArrayList<Tile> findSet() { // Gives you the best set it can detect. (FOR AI)
		ArrayList<Tile> largestRun = null; 

    	ArrayList<PlayerHand> handsByNumber; 
    	ArrayList<ArrayList<Tile>> sets = new ArrayList<ArrayList<Tile>>(); 
		ArrayList<Tile> currSet;
		
    	handsByNumber  = seperateByNumber(); 
    	
    	for(PlayerHand temp : handsByNumber) {//temp is the currSet
    		currSet  = new ArrayList<Tile>(); 
    		temp.removeDoubles();
    		
    		if(temp.sizeOfHand() > 2)
    			currSet.addAll(temp.getTiles()); 
    			sets.add(currSet); 
    	}

    	if(sets.size()>0)
    		largestRun = sets.get(0); 
	    for(int i = 1; i < sets.size(); i++) {
	    	
	    	if(getMeldScore(sets.get(i)) >= getMeldScore(largestRun)) {
	    		largestRun = sets.get(i); 
	    	}
	   	}
 
    	    	
    	if(largestRun != null ) {//&& getMeldScore(largestRun) >= 30) {

    		return largestRun; 
    	}

		return null;
	}

	private int getMeldScore(ArrayList<Tile> tiles){
	   	int count = 0; 
	   	for(int i = 0; i < tiles.size(); i++) {
	   		count += tiles.get(i).getNumber(); 
	   	}
	    	//System.out.println("Count "+ count);
	    return count; 	
	}

	private ArrayList<PlayerHand> seperateByNumber() {
    	ArrayList<PlayerHand> handsByNumber = new ArrayList<PlayerHand>();

		this.sortTilesByNumber();
    	//used constant for max tile number 
    	for(int i = 0; i < 14; i++)
    		handsByNumber.add(new PlayerHand(""+i)); 

    	for(int i = 0; i < this.sizeOfHand(); i++)
    		handsByNumber.get(this.getTile(i).getNumber()).addTileToHand(this.getTile(i));
    	
    	return handsByNumber; 
    }

	

	public boolean hasPossibilities(PlayerHand x) {

		return false;
	}

	public void removeTile(Tile tile) 
	{
		for(int x=0; x<hand.size();x++)
		{
			if(tile.getColor().equals(hand.get(x).getColor()) && tile.getNumber() == hand.get(x).getNumber())
			{
				hand.remove(x);
			}
		}
	}

}

class SortByX implements Comparator<Tile> {
	// Used for sorting in ascending order of
	// tile number
	public int compare(Tile a, Tile b) {

		return a.getNumber() - b.getNumber();
	}
}
