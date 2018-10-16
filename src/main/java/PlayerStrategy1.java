import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;


public class PlayerStrategy1 implements PlayerStrategy {

	public boolean playTheGame(Player p) {
		// TODO Auto-generated method stub
		if(p.getIsFirstMeldComplete()) {
			ArrayList<Tile> first_hand = new ArrayList<Tile>();
			ArrayList<Tile> second_hand = new ArrayList<Tile>();
			
			//copy player hand to sample hand
			for(int i =0; i < p.getHand().sizeOfHand();i++) {
				first_hand.add(p.getHand().getTile(i));
				second_hand.add(p.getHand().getTile(i));
			}
			// create output 
			ArrayList<ArrayList<Tile>> output;
			ArrayList<ArrayList<Tile>> firstMelds = getFirstOutput(first_hand);
			ArrayList<ArrayList<Tile>> secondMelds = getSecondOutput(second_hand);
			int x = 0,y = 0;
			//check the size of 2 possible output, and get the one whose size is bigger than other.
			for(int i = 0; i < firstMelds.size();i++) {	x += firstMelds.get(i).size();	}
			for(int i = 0; i < secondMelds.size();i++) {y += secondMelds.get(i).size();}
			if(x >= y) output = firstMelds;
			else output = secondMelds;
		
			if (output.size() == 0) return false;
			//add tiles in the table and remove tiles from player hand.
			for(int i = output.size()-1; i > -1 ;i--) {
				p.getTable().addTiles(output.get(i));
				for(int u = 0; u < output.get(i).size();u++) {
					p.getHand().getTiles().remove(output.get(i).get(u));
				}
			}
			//if size ==0, this player is the winner
			if(p.getHand().getTiles().size() == 0) p.setWinner();
			return true;
		}
		else {
			ArrayList<Tile> first_hand = new ArrayList<Tile>();
			ArrayList<Tile> second_hand = new ArrayList<Tile>();
			
			//copy player hand to sample hand
			for(int i =0; i < p.getHand().sizeOfHand();i++) {
				first_hand.add(p.getHand().getTile(i));
				second_hand.add(p.getHand().getTile(i));
			}
			// create output 
			ArrayList<ArrayList<Tile>> output;
			ArrayList<ArrayList<Tile>> firstMelds = getFirstOutput(first_hand);
			ArrayList<ArrayList<Tile>> secondMelds = getSecondOutput(second_hand);
			int x = 0,y = 0;
			//check the size of 2 possible output, and get the one whose size is bigger than other.
			for(int i = 0; i < firstMelds.size();i++) {	x += firstMelds.get(i).size();	}
			for(int i = 0; i < secondMelds.size();i++) {y += secondMelds.get(i).size();}
			if(x >= y) output = firstMelds;
			else output = secondMelds;
			
			if (output.size() == 0) return false;
			int point = 0;
			//add tiles in the table and remove tiles from player hand.
			// use point to sum up tiles' value
			for(int i = output.size()-1; i > -1 ;i--) {
				for(int u = 0; u < output.get(i).size();u++) {
					point += output.get(i).get(u).getNumber();
				}
			}
			// if point >= 30, add tiles to the table and remove them from hand
			if(point >= 30) {
				for(int i = output.size()-1; i > -1 ;i--) {
					p.getTable().addTiles(output.get(i));
					for(int u = 0; u < output.get(i).size();u++) {
						p.getHand().getTiles().remove(output.get(i).get(u));
					}
				}
				//set the fist meld complete
				p.setIsfirstMeldComplete(true);
			}
			//set winner, no tiles left
			if(p.getHand().getTiles().size() == 0) p.setWinner();
			return false;
			
		}	
	}
	
	// Find all sequences of hand then find all sets
	private ArrayList<ArrayList<Tile>> getFirstOutput(ArrayList<Tile> hand){
		//sort hand to find all sequences
		Collections.sort(hand, new SortByColor());
		Collections.sort(hand, new SortToFindSequence());
		//create list to hold sequences
		ArrayList<ArrayList<Tile>> sequences = getSequences(hand);
		hand = renew(hand,sequences); // find all sequences
		
		//sort hand to find all sets
		Collections.sort(hand, new SortbyValue());
		Collections.sort(hand, new SortToFindSet());
		//create list hold sets
		ArrayList<ArrayList<Tile>> sets = getSets(hand);
		hand = renew(hand,sets); // find all sets
		
		// create output 
		ArrayList<ArrayList<Tile>> output = new ArrayList<ArrayList<Tile>>();

		//if have sets add all of it to output
		if(sets != null)
			output.addAll(sets);
		// if have sequences add all of it to output
		if(sequences != null)
			output.addAll(sequences);
		return output;
		
	}
	
	// do the same as getFirstOutput, however, find all the sets first.
	private ArrayList<ArrayList<Tile>> getSecondOutput(ArrayList<Tile> hand){
		
		//sort hand to find all sets
		Collections.sort(hand, new SortbyValue());
		Collections.sort(hand, new SortToFindSet());
		//create list hold sets
		ArrayList<ArrayList<Tile>> sets = getSets(hand);
		hand = renew(hand,sets); // find all sets
		
		//sort hand to find all sequences
		Collections.sort(hand, new SortByColor());
		Collections.sort(hand, new SortToFindSequence());
		//create list to hold sequences
		ArrayList<ArrayList<Tile>> sequences = getSequences(hand);
		hand = renew(hand,sequences); // find all sequences
		
		// create output 
		ArrayList<ArrayList<Tile>> output = new ArrayList<ArrayList<Tile>>();

		//if have sets add all of it to output
		if(sets != null)
			output.addAll(sets);
		// if have sequences add all of it to output
		if(sequences != null)
			output.addAll(sequences);
		return output;
		
	}
	
	
	
	private ArrayList<Tile>  renew (ArrayList<Tile> t, ArrayList<ArrayList<Tile>> sequences) {
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
	
}
