import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;

//support functions
public class Support {

	
	public boolean playLastTile(Player p) {
		ArrayList<Tile> output = new ArrayList<Tile>();
		boolean done=false;
		int count=0;
		int c=0;//System.out.println(p.getTable().getTable().size());
		for (int i=0;i<p.getTable().getTable().size();i++) {
			
			output = p.getTable().getTable().get(i);
			output.add(p.getHand().getTile(0));
			for (int j=0;j<output.size();j++) {  
					count=getSequences(output).get(j).size();
			if (count==output.size()) {
			c=i;
			done=true;
			if (done==true) {
				p.getHand().removeTile(p.getHand().getTile(0));
				p.getTable().getTable().remove(c);
				p.getTable().addTiles(output);
				return true;
			}
			}
		}		
			for (int j=0;j<output.size();j++) {
				count=getSets(output,false).get(j).size();
				if (count==output.size()) {
				c=i;
				done=true;
				if (done==true) {
					p.getHand().removeTile(p.getHand().getTile(0));
					p.getTable().getTable().remove(c);
					p.getTable().addTiles(output);
					return true;
				}
				}
			}
			
		}
		
		return true;
	}
	
	public boolean one_short(Player p) {
		ArrayList<ArrayList<Tile>> output = new ArrayList<ArrayList<Tile>>();
		output = merge(p.getHand().getTiles(),p.getTable());
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
		ArrayList<ArrayList<Tile>> sets = getSets(hand,false);
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
		ArrayList<ArrayList<Tile>> sets = getSets(hand,false);
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
	public ArrayList<Tile>  renew (ArrayList<Tile> t, ArrayList<ArrayList<Tile>> sequences) {
		// TODO Auto-generated method stub
		ArrayList<Tile> output;
		if(sequences != null) {
		for(int i =0; i < sequences.size();i++) {
			for(int u = 0; u < sequences.get(i).size();u++) {
				if(sequences.get(i).get(u).isJoker()) {
					myloop: for(int k =0; k < t.size();k++) {
						if(t.get(k).isJoker() && t.get(k).getJokerColor().equals(sequences.get(i).get(u).getJokerColor())
								&& t.get(k).getJokerPoint() == sequences.get(i).get(u).getJokerPoint()) {
							t.remove(k);
							break myloop;
						}
					}
						
				}
				else
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
	
	
	public boolean containsJoker(ArrayList<Tile> T) {
		for (int i=0;i<T.size();i++) {
			if (T.get(i).isJoker()==true){
				return true;
			}
		}
		return false;
	}
	
	public Tile getJoker(ArrayList<Tile> T) {
		for (int i=0;i<T.size();i++) {
			if (T.get(i).isJoker()==true){
				return T.get(i);
			}
		}
		return null;
	}
	
	public ArrayList<Tile> getJInBetween(ArrayList<Tile> x) {
		ArrayList<Tile> newList = new ArrayList<Tile>();
		ArrayList<Tile> temp = new ArrayList<>();
		Tile T = new Tile();
		int sum = 0 ;
		int tempsum;
		for (int i=0; i<x.size()-1;i++) {
			temp.clear();
			tempsum=0;
			if ((x.get(i).getColor()==x.get(i+1).getColor())&&(x.get(i+1).getNumber()-x.get(i).getNumber()==2)) {

				temp.add(x.get(i));			
				T.setNumber(x.get(i+1).getNumber()-1);
				T.setColor(x.get(i).getColor());
				temp.add(T);
				temp.add(x.get(i+1));
				tempsum=temp.get(0).getNumber()+temp.get(1).getNumber()+temp.get(2).getNumber();
				if (tempsum>sum) {
					sum=tempsum; 
					newList=temp;
				}
				
				
			}
			
		}
		
		return newList;
	}
	
	public ArrayList<Tile> getJokerSequences(ArrayList<Tile> x){  //arrange and place jokers with sequences
		ArrayList<Tile> inBet = new ArrayList<Tile>();
		ArrayList<Tile> newList = new ArrayList<Tile>();
		ArrayList<Tile> temp = new ArrayList<>();
		int sumB=0;
		inBet=getJInBetween(x); 
		for (int i=0;i<inBet.size();i++) {
			sumB+=inBet.get(i).getNumber();
		}
		
		Tile T = new Tile();
		int sum = 0 ;
		int tempsum;
		for (int i=0; i<x.size()-1;i++) {
			temp.clear();
			tempsum=0;
			if ((x.get(i).getColor()==x.get(i+1).getColor())&&(x.get(i+1).getNumber()-x.get(i).getNumber()==1) 
					) {
				if (x.get(i+1).getNumber()==13) {
					T.setNumber(x.get(i).getNumber()-1); 
					T.setColor(x.get(i).getColor());
					temp.add(T);
					temp.add(x.get(i));			
					temp.add(x.get(i+1));
					
				}
				
				else {
				temp.add(x.get(i));			
				temp.add(x.get(i+1));
				T.setNumber(x.get(i+1).getNumber()+1); 
				T.setColor(x.get(i).getColor());
				temp.add(T);
				}
				
				tempsum=temp.get(0).getNumber()+temp.get(1).getNumber()+temp.get(2).getNumber();
				if (tempsum>sum) {
					sum=tempsum; 
					newList=temp;
				}	
			}	
		}

		if (sumB>sum) {
			newList=inBet;
		}
		
		return newList;
	}
	
	public ArrayList<Tile> getJokerSets(ArrayList<Tile> x){  //arrange and place jokers with sets
		
		ArrayList<Tile> newList = new ArrayList<Tile>();
		newList=x;
		Tile T = new Tile();
		
		Tile T1 = new Tile();
		T1.setJoker(false);
		Tile T2 = new Tile();
		T2.setJoker(false);
		Tile T3 = new Tile();
		T3.setJoker(false);
		
		T.setJokerPoint(x.get(0).getNumber());
		T1.setColor(x.get(0).getColor());
		T2.setColor(x.get(1).getColor());
		
		if (newList.size()>2) {
		T3.setColor(x.get(2).getColor());	
		}
		
		if (newList.size()==4) {
			return x;
		}
		
		if (newList.size()==3) {
		if (T1.getColor() != "R" && T2.getColor() !="R" && T3.getColor()!="R"){
			T.setJokerColor("R");
			newList.add(T);
		}
			else if (T1.getColor() != "B" && T2.getColor() !="B" && T3.getColor()!="B") {
				T.setJokerColor("B");
				newList.add(T);
		}
			else if (T1.getColor() != "G" && T2.getColor() !="G" && T3.getColor()!="G") {
				T.setJokerColor("G");
				newList.add(T);
		}
			else if (T1.getColor() != "O" && T2.getColor() !="O" && T3.getColor()!="O") {
				T.setJokerColor("O");
				newList.add(T);
		}
		
		}
		
		if (newList.size()==2) {
		if (T1.getColor() != "R" && T2.getColor() !="R" ) {
			T.setJokerColor("R");
			newList.add(T);
		}
			else if (T1.getColor() != "B" && T2.getColor() !="B") {
				T.setJokerColor("B");
				newList.add(T);
		}
			else if (T1.getColor() != "G" && T2.getColor() !="G" ) {
				T.setJokerColor("G");
				newList.add(T);
		}
			else if (T1.getColor() != "O" && T2.getColor() !="O" ) {
				T.setJokerColor("O");
				newList.add(T);
		}
		
		
		}
		
		return newList;
	}
	
	
	//Function get all the sequences in the List sorted by color, then sorted by value (this list is sorted 2 times)
	public ArrayList<ArrayList<Tile>> getSequences(ArrayList<Tile> hand){
		
		ArrayList<Tile> t = new ArrayList<Tile>(hand);
		Collections.sort(t, new SortbyValue());
		Collections.sort(t, new SortToFindSet());
		//create list hold sets
		ArrayList<ArrayList<Tile>> sets = getSets(t,false);
		
		ArrayList<ArrayList<Tile>> sequences = new ArrayList<ArrayList<Tile>>();
		ArrayList<Tile> check = new ArrayList<Tile>();
		ArrayList<Tile> check1 = new ArrayList<Tile>();
		
		if(hand == null || hand.size() == 0) return null;
		
		String color = hand.get(0).getColor();
		for(int i =0; i < hand.size();i++) {
			if(color.equals(hand.get(i).getColor())) {
				if(check.size() == 0) {
					check.add(hand.get(i));
				}
				else {
					if(check.size() >= 3 && check1.size() >= 2) {
						if (Integer.compare(hand.get(i).getNumber()-1, check1.get(check1.size()-1).getNumber()) == 0)
							check1.add(hand.get(i));
					}
					else if(Integer.compare(hand.get(i).getNumber()-1, check.get(check.size()-1).getNumber()) == 0)
						check.add(hand.get(i));
					else if (Integer.compare(hand.get(i).getNumber(), check.get(check.size()-1).getNumber()) == 0)
						check1.add(hand.get(i));
					else {
						if(check.size() > 2) sequences.add(check);
						if(check1.size() > 2) sequences.add(check1);
						
						check1 = new ArrayList<Tile>();
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
				if(check1.size() > 2) sequences.add(check1);
				check1 = new ArrayList<Tile>();
			}
		}
		if(check.size()>2)sequences.add(check);
		if(check1.size()>2)sequences.add(check1);
		
		for(int i =0; i < sequences.size();i++) {
			if(sequences.get(i).size() == 4) {
				myloop: for(int u =0; u < sets.size();u++) {
					if(sets.get(u).contains(sequences.get(i).get(0))) {
						sequences.get(i).remove(0);
						break myloop;
					}
					else if(sets.get(u).contains(sequences.get(i).get(3))) {
						sequences.get(i).remove(3);
						break myloop;
					}
				}
			}
			else if (sequences.get(i).size() == 5) {
				myloop: for(int u =0; u < sets.size();u++) {
						
					if(sets.get(u).contains(sequences.get(i).get(1))) {
						sequences.get(i).remove(1);
						sequences.get(i).remove(0);
						break myloop;
					}
					else if(sets.get(u).contains(sequences.get(i).get(3))) {
						sequences.get(i).remove(4);
						sequences.get(i).remove(3);
						break myloop;
					}
					
					else if(sets.get(u).contains(sequences.get(i).get(0))) {
						if(sets.get(u).contains(sequences.get(i).get(4)))
							sequences.get(i).remove(4);
						sequences.get(i).remove(0);
						break myloop;
					}
					else if(sets.get(u).contains(sequences.get(i).get(4))) {
						sequences.get(i).remove(4);
						break myloop;
						}
				}
			}
			
		}
		
		return sequences;
	}
	//Function get all the sets in the List sorted by value, then sorted by color (this list is sorted 2 times)
	public ArrayList<ArrayList<Tile>> getSets(ArrayList<Tile> hand, boolean x){
		
		if(hand == null || hand.size() == 0) return null;
		ArrayList<ArrayList<Tile>> sets = new ArrayList<ArrayList<Tile>>();
		ArrayList<Tile> check = new ArrayList<Tile>();
		HashSet<String> string = new HashSet<String>();
		if(x==true) {
		Collections.sort(hand, new SortByColor());
		}
		int value = hand.get(0).getNumber();
		
		
		ArrayList<Tile> check1 = new ArrayList<Tile>();
		HashSet<String> string1 = new HashSet<String>();
		
		for(int i =0; i < hand.size();i++) {
			if(value  == hand.get(i).getNumber()) {
				if(check.size() == 0) {
					check.add(hand.get(i));
					string.add(hand.get(i).getColor());
				}
				else {
					if(string.size() >= 3 && string1.size() >= 2) {
						if (string1.add(hand.get(i).getColor())) {
							check1.add(hand.get(i));
							string1.add(hand.get(i).getColor());
						}
					}
					else if(string.add(hand.get(i).getColor())) {
						check.add(hand.get(i));
						string.add(hand.get(i).getColor());
					}
					else if (string1.add(hand.get(i).getColor())) {
						check1.add(hand.get(i));
						string1.add(hand.get(i).getColor());
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
				
				check1 = new ArrayList<Tile>();
				string1 = new HashSet<String>();
				
				
			}
		}
		if(check.size()>2) sets.add(check);
		if(check1.size() > 2) sets.add(check1);
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
	
	public double getProbability(Tile t, Table table, ArrayList<Tile> Deck, int p1,int p2, int p3) {
		
		double p = 0.0; 
		int count=1; 
		String color = ""; 
		int num=0; 
		
		for(int j =0; j < table.getTable().size();j++) {
			for (int i=0;i<table.getTable().get(j).size();i++) {
				color =  table.getTable().get(j).get(i).getColor(); 
				num = table.getTable().get(j).get(i).getNumber(); 
				
				if ((t.getColor().equals(color))&&(t.getNumber()==num))  count++;  	
			}
		}
		if (count==2)  return 0; 
		
		//calculate probability
		p= (count*1000/(Deck.size()+p1+p2+p3));
		
		return p; 
	}
	
}
