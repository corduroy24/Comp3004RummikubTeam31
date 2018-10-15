import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;


public class PlayerStrategy2 implements PlayerStrategy {

	public boolean playTheGame(Player p) {
		// TODO Auto-generated method stub
		ArrayList<Tile> input = new ArrayList<Tile>();
		
		if(p.getIsFirstMeldComplete()) {
			
		}
		else {
			if(p.getTable().getNumberOfTile() == 0) {
			ArrayList<Tile> hand = new ArrayList<Tile>();
			
			for(int i =0; i < p.getHand().sizeOfHand();i++) {
				hand.add(p.getHand().getTile(i));
			}
			
			Collections.sort(hand, new SortByColor());
			Collections.sort(hand, new SortToFindSequence());
			
			ArrayList<ArrayList<Tile>> sequences = getSequences(hand);
			hand = renew(hand,sequences);
			Collections.sort(hand, new SortbyValue());
			Collections.sort(hand, new SortToFindSet());
			
			ArrayList<ArrayList<Tile>> sets = getSets(hand);
			hand = renew(hand,sets);
			ArrayList<ArrayList<Tile>> output = new ArrayList<ArrayList<Tile>>();

			
			Collections.sort(output, new SortByArrayList());
			if(sets != null)
				output.addAll(sets);
			if(sequences != null)
				output.addAll(sequences);
		
			for(int i =0; i < output.size();i++) {
				for(int u =0; u < output.get(i).size();u++)
					System.out.println(output.get(i).get(u).getColor() + "  " + output.get(i).get(u).getNumber());
			}
			
			int point = 0;
			ArrayList<ArrayList<Tile>> tiles = new ArrayList<ArrayList<Tile>>();
			for(int i = output.size()-1; i > -1 ;i--) {
				for(int u = 0; u < output.get(i).size();u++) {
					point += output.get(i).get(u).getNumber();
				}
				tiles.add(output.get(i));
				if (point >= 30) {
					for(int j = 0; j < tiles.size();j++) {
						p.getTable().addTiles(tiles.get(j));
						for(int n = 0; n < tiles.get(j).size();n++) {
							p.getHand().getTiles().remove(tiles.get(j).get(n));
						}
						p.getHand().HandReader();
						p.setIsfirstMeldComplete(true);
						return true;
					}
				}
			}
			return false;
			}
		}	
		return false;
	}
	
	//sort by tile' value
	class SortByArrayList implements Comparator<ArrayList<Tile>> 
	{ 	
	    public int compare(ArrayList<Tile> a , ArrayList<Tile> b) 
	    { int x = 0;
			int y = 0;
			for(int i =0; i < a.size();i++)
				x += a.get(i).getNumber();
			for(int i =0; i < b.size();i++)
				y += b.get(i).getNumber();

			
			return x - y;
		
	        
	    } 
	}
	
	private ArrayList<Tile>  renew(ArrayList<Tile> t, ArrayList<ArrayList<Tile>> sequences) {
		// TODO Auto-generated method stub
		ArrayList<Tile> output;
		if(sequences != null) {
		for(int i =0; i < sequences.size();i++) {
			for(int u = 0; u < sequences.get(i).size();u++) {
			t.remove(sequences.get(i).get(u));
				}
			}
		}
		output = t;
		return output;
		
	}

	//sort by tile' value
	class SortbyValue implements Comparator<Tile> 
	{ 
	    public int compare(Tile a, Tile b) 
	    { 
	        return a.getNumber() - b.getNumber(); 
	    } 
	}
	
	//List is stored by value, now it will be sorted by color
	class SortToFindSet implements Comparator<Tile> 
	{ 
	    public int compare(Tile a, Tile b) 
	    { 
	    	if(a.getNumber() == b.getNumber()) {
	    		return a.getColor().compareTo(b.getColor());
	    	}
	    	else
	    		return 0; 
	    } 
	}
	
	//sort by tile' color
	class SortByColor implements Comparator<Tile> 
	{ 
	    public int compare(Tile a, Tile b) 
	    { 
	        return a.getColor().compareTo(b.getColor()); 
	    } 
	}
	//list sorted by color, not it will be sorted by value (find sequence)
	class SortToFindSequence implements Comparator<Tile> 
	{ 
	    public int compare(Tile a, Tile b) 
	    { 
	    	if(a.getColor().equals(b.getColor())) {
	    		return a.getNumber() - b.getNumber();
	    	}
	    	else
	    		return 0; 
	    } 
	}
	//Function get all the sequences in the List sorted by color, then sorted by value (this list is sorted 2 times)
	public ArrayList<ArrayList<Tile>> getSequences(ArrayList<Tile> hand){
		ArrayList<ArrayList<Tile>> sequences = new ArrayList<ArrayList<Tile>>();
		ArrayList<Tile> check = new ArrayList<Tile>();
		
		String color = hand.get(0).getColor();
		for(int i =0; i < hand.size();i++) {
			if(color.equals(hand.get(i).getColor())) {
				if(check.size() == 0) {
					check.add(hand.get(i));
				}
				else {
					if(Integer.compare(hand.get(i).getNumber()-1, check.get(check.size()-1).getNumber()) == 0)
						check.add(hand.get(i));
					else {
						if(check.size() > 2) sequences.add(check);
						check = new ArrayList<Tile>();
						check.add(hand.get(i));
					}
				}
			}
			else {
				color = hand.get(i).getColor();
				if(check.size() > 2) sequences.add(check);
				check = new ArrayList<Tile>();
				check.add(hand.get(i));
			}
		}
		if(check.size()>2)sequences.add(check);
		return sequences;
	}
	//Function get all the sets in the List sorted by value, then sorted by color (this list is sorted 2 times)
	public ArrayList<ArrayList<Tile>> getSets(ArrayList<Tile> hand){
		if(hand.size() == 0) return null;
		ArrayList<ArrayList<Tile>> sets = new ArrayList<ArrayList<Tile>>();
		ArrayList<Tile> check = new ArrayList<Tile>();
		int value = hand.get(0).getNumber();
		HashSet<String> string = new HashSet<String>();
		for(int i =0; i < hand.size();i++) {
			if(value  == hand.get(i).getNumber()) {
				if(check.size() == 0) {
					check.add(hand.get(i));
					string.add(hand.get(i).getColor());
				}
				else {
					if(string.add(hand.get(i).getColor())) {
						check.add(hand.get(i));
						string.add(hand.get(i).getColor());
					}
					else {
					}
				}
			}
			else {
				if(check.size() > 2) sets.add(check);
				check = new ArrayList<Tile>();
				string = new HashSet<String>();
				value = hand.get(i).getNumber();
				check.add(hand.get(i));
				string.add(hand.get(i).getColor());
			}
		}
		if(check.size()>2)
			sets.add(check);
		return sets;
	}
	
	public static void main(String[] args) {
		Player player = new Player("bill",123, new PlayerStrategy2());
		Deck deck = new Deck();
		deck.Shuffle();
		Tile tile = new Tile(1,12);
		Tile tile1 = new Tile(1,11);
		Tile tile2 = new Tile(1,10);
		Tile tile3 = new Tile(2,3);
		Tile tile4 = new Tile(3,5);
		Tile tile5 = new Tile(2,7);
		Tile tile6 = new Tile(1,7);
		Tile tile10 = new Tile(3,7);
		
		Tile t7 = new Tile(1,1);
		Tile t8 = new Tile(2,1);
		Tile t9 = new Tile(3,1);
		
		Tile t11 = new Tile(4,1);
		Tile t12 = new Tile(4,2);
		Tile t13 = new Tile(4,3);
		Tile t14 = new Tile(4,4);
		player.getPlayerHand().addTileToHand(t11);
		player.getPlayerHand().addTileToHand(t12);
		player.getPlayerHand().addTileToHand(t13);
		player.getPlayerHand().addTileToHand(t14);
		
		player.getPlayerHand().addTileToHand(tile);
		player.getPlayerHand().addTileToHand(tile1);
		player.getPlayerHand().addTileToHand(tile2);
		player.getPlayerHand().addTileToHand(tile3);
		player.getPlayerHand().addTileToHand(tile4);
		player.getPlayerHand().addTileToHand(tile5);
		player.getPlayerHand().addTileToHand(tile6);
		player.getPlayerHand().addTileToHand(tile10);
		
		player.getPlayerHand().addTileToHand(t7);
		player.getPlayerHand().addTileToHand(t8);
		//player.getPlayerHand().addTileToHand(t9);
		player.play();
		
	}
	
}
