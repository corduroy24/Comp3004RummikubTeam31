import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

//support functions
public class Support {

	public boolean one_short(Player p) {
		ArrayList<ArrayList<Tile>> output = new ArrayList<ArrayList<Tile>>();
		// all set and all sequence could be generate from hands and tables
		output = merge(p.getHand().getTiles(),p.getTable());
		// if number of tiles in output = (hand + table) tiles
		// play and set win
		int point  = 0;
		for(int i= 0 ; i < output.size();i++) {
			for(int u =0; u < output.get(i).size();u++) {
				point += output.get(i).get(u).getNumber();
			}
		}
		
		
		if(getSizeOf(output) == p.getHand().sizeOfHand() + p.getTable().getNumberOfTile())
		{
			p.getTable().clean();
			for(int i =0; i < output.size();i++) {
				p.getTable().addTiles(output.get(i));
			}
			System.out.println("Tiles played by " + p.getName());
			p.getHand().HandReader();
			p.getHand().getTiles().clear();
			p.setWinner();
			return true;
		}
		return false;
	}
	
	// merge an list with tiles on the table, then return a 2d array list
	// which contains sets and sequences.
		public ArrayList<ArrayList<Tile>> merge(ArrayList<Tile> TileOnHand, Table table) {
			// TODO Auto-generated method stub
			ArrayList<ArrayList<Tile>> output = new ArrayList<ArrayList<Tile>>();
			ArrayList<ArrayList<Tile>> first_sample = new ArrayList<ArrayList<Tile>>();
			ArrayList<ArrayList<Tile>>  second_sample = new ArrayList<ArrayList<Tile>>();
			
			
			ArrayList<Tile> merge_list = new ArrayList<Tile>(TileOnHand);
			ArrayList<Tile> merge_list1 = new ArrayList<Tile>(TileOnHand);


			for(int i =0; i < table.getTable().size();i++) {
				for(int u =0; u<table.get(i).size();u++) {
					merge_list.add(table.get(i).get(u));
					merge_list1.add(table.get(i).get(u));
					
				}
			}
			
			first_sample = getFirstOutput(merge_list);
			second_sample = getSecondOutput(merge_list1);
			int x = 0,y = 0;
			//check the size of 2 possible output, and get the one whose size is bigger than other.
			x = getSizeOf(first_sample);
			y = getSizeOf(second_sample);
			
			if(x >= y) output = first_sample;
			else output = second_sample;
			
			return output;
		}
	
	// Find all sequences of hand then find all sets
	public ArrayList<ArrayList<Tile>> getFirstOutput(ArrayList<Tile> hand){
		//sort hand to find all sequences
		if(hand == null || hand.size() == 0) return null;
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
	public ArrayList<ArrayList<Tile>> getSecondOutput(ArrayList<Tile> hand){
		if(hand == null || hand.size() == 0) return null;
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
	
	
	//re-arrange number of tiles on t list
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
	private ArrayList<ArrayList<Tile>> getSequences(ArrayList<Tile> hand){
		ArrayList<ArrayList<Tile>> sequences = new ArrayList<ArrayList<Tile>>();
		ArrayList<Tile> check = new ArrayList<Tile>();
		
		if(hand == null || hand.size() == 0) return null;
		
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
	private ArrayList<ArrayList<Tile>> getSets(ArrayList<Tile> hand){
		if(hand == null || hand.size() == 0) return null;
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

	public int getSizeOf(ArrayList<ArrayList<Tile>> sample) {
		// TODO Auto-generated method stub
		int x = 0;
		if(sample == null || sample.size() == 0 ) 
			return 0;
		
		for(int i = 0; i < sample.size();i++) {	x += sample.get(i).size();}
		return x;
	}
}
