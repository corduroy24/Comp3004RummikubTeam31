import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;

public class Table{
	private ArrayList<ArrayList<Tile>> table;
	
	public Table() {
		table = new ArrayList<ArrayList<Tile>>();
	}
	public int getNumberOfTile() {
		// TODO Auto-generated method stub
		int u = 0;
		for(int i =0; i < table.size();i++) {
			u += table.get(i).size();
		}
		return u;
	}

	// Add set or sequence on table, return true if success
	// if it is not a set or sequence, it will return false
	public boolean addTiles(ArrayList<Tile> tiles2) {
		// TODO Auto-generated method stub
		if(isSet(tiles2) || isSequence(tiles2))
			return table.add(tiles2);
		return false;
	}
	

	// a is the number of row holding tile.
	// b is the index of Tile in a
	// c is the number of row we want to push tile in
	// d is the number of index we want to put tile in c.
	public boolean moveTile(int a, int b, int c, int d) {
		// TODO Auto-generated method stub
		return false;
	}

	public ArrayList<Tile> getSetOrSequences(int i) {
		// TODO Auto-generated method stub
		return table.get(i);		
	}
	
	public ArrayList<ArrayList<Tile>> getTable(){
		return table;
	}
	
	//check the array is set or not
	public boolean isSet(ArrayList<Tile> t) {
		if (t == null) return false;
		Collections.sort(t, new SortbyValue());
		int NumberOfJoker = 0;
		if (t.get(t.size()-1).getNumber()== 14) NumberOfJoker++;
		if (t.get(t.size()-2).getNumber()== 14) NumberOfJoker++;
		int value = t.get(0).getNumber();
		if(t.size() > 4) return false;
		HashSet<String> set = new HashSet<String>();
		if(t.size()-1-NumberOfJoker == 0) return true;
		
		for(int  u =0; u < t.size()-1-NumberOfJoker;u++) {
			int current, next;
			String current_color = t.get(u).getColor();
			current = t.get(u).getNumber();
			next = t.get(u+1).getNumber();
			if(current != next)
				return false;
			
			if(set.add(current_color) == false) {
				return false;		
			}
		}
		return true;
	}
	//sort tiles by number
	class SortbyValue implements Comparator<Tile> 
	{ 
	    public int compare(Tile a, Tile b) 
	    { 
	        return a.getNumber() - b.getNumber(); 
	    } 
	} 
	//check the array is sequence or not
	public boolean isSequence(ArrayList<Tile> t) {
		if (t == null) return false;
		Collections.sort(t, new SortbyValue());
		int NumberOfJoker = 0;
		if (t.get(t.size()-1).getNumber()== 14) NumberOfJoker++;
		if (t.get(t.size()-2).getNumber()== 14) NumberOfJoker++;
		if(t.size()-1-NumberOfJoker == 0) return true;
		
		String color = t.get(0).getColor();
		
		for(int u =0; u < t.size()-1-NumberOfJoker;u++) {
			int current, next;
			String current_color = t.get(u).getColor();
			current = t.get(u).getNumber();
			next = t.get(u+1).getNumber();
			
			if(next == 14) return true;
			
			if(!current_color.equals(color))
					return false;
			else {
				if(current+1 != next) {
					if(next - current > 2) return false;
					else if((NumberOfJoker > 0) && (next-2 == current))	NumberOfJoker--;
					else return false;
				}
			}
		}
		return true;
	}
	
	public ArrayList<Tile> get(int i ){
		return table.get(i);
	}
	
	public boolean equals(Table t) {
		for(int i =0; i < table.size();i++) {
			for(int u = 0; u < table.get(i).size();u++) {
				if(!table.get(i).get(u).getColor().equals(t.get(i).get(u).getColor()))
					return false;
				if(table.get(i).get(u).getNumber() != t.get(i).get(u).getNumber())
					return false;
				
			}
		}
		return true;
	}
	

}
