import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

public class PlayerStrategy3 {	
	public boolean playTheGame(Player p) {
		// TODO Auto-generated method stub
		if(p.getIsFirstMeldComplete()) {
			if(isCondition(p)) {
			
			}
			else return false; 

		}
		else {
			ArrayList<Tile> hand = p.getHand().getTiles();
			Collections.sort(hand, new SortByColor());
			Collections.sort(hand, new SortToFindSequence());
			ArrayList<ArrayList<Tile>> sequences = getSequences(hand);
			hand = renew(hand,sequences);
			Collections.sort(hand, new SortbyValue());
			Collections.sort(hand, new SortToFindSet());
			ArrayList<ArrayList<Tile>> sets = getSets(hand);
			int point = 0;
			point = getPoint(sequences,sets);
					
			
			p.getHand().HandReader();
			for(int i =0; i < sequences.size();i++) {
				for(int u = 0; u < sequences.get(i).size();u++) {
					System.out.println(sequences.get(i).get(u).getColor() + "  " + sequences.get(i).get(u).getNumber());
				}
			}
			System.out.println("--------------------");
			for(int i =0; i < sets.size();i++) {
				for(int u = 0; u < sets.get(i).size();u++) {
					System.out.println(sets.get(i).get(u).getColor() + "  " + sets.get(i).get(u).getNumber());
				}
			}
			
			
			
		}				
		return false;
	}
	
	private ArrayList<Tile>  renew(ArrayList<Tile> hand, ArrayList<ArrayList<Tile>> sequences) {
		// TODO Auto-generated method stub
		ArrayList<Tile> output;
		if(sequences != null) {
		for(int i =0; i < sequences.size();i++) {
			for(int u = 0; u < sequences.get(i).size();u++) {
			hand.remove(sequences.get(i).get(u));
				}
			}
		}
		output = hand;
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
	
	public int getPoint(ArrayList<ArrayList<Tile>> a, ArrayList<ArrayList<Tile>> b) {
		ArrayList<Integer> sum = new ArrayList<Integer>();
		// sum size = a.size() + b.size()
		int point = 0;
		if(a != null && a.size() != 0) {
			for(int i =0; i < a.size();i++) {
				point = 0;
				for(int u = 0; u < a.get(i).size();u++) {
					point += a.get(i).get(u).getNumber();
				}
				sum.add(point);
			}
		}
		point = 0;
		if(b != null && b.size() != 0) {
			for(int i =0; i < b.size();i++) {
				for(int u = 0; u < b.get(i).size();u++) {
					point += b.get(i).get(u).getNumber();
				}
			}
			
		}
		
		
		return point;
	}
	
	//firstInitial30
	
	public boolean isCondition(Player p) {
		int sizeOfPlayerHand = p.getHand().sizeOfHand(); 
		
		if(sizeOfPlayerHand - 3 > p.getFirstPlayerHand()) return false; 
		else if (sizeOfPlayerHand - 3 > p.getSecondPlayerHand()) return false; 
		else if (sizeOfPlayerHand - 3 > p.getThirdPlayerHand()) return false; 
		
		return true; 
	}
	
	

	public static void main(String[] args) {
		Player player = new Player("name",123, new PlayerStrategy2());
		Deck deck = new Deck();
		deck.Shuffle();
		player.getHand().drawFirst14(deck);
		player.play();
		
	}
}
