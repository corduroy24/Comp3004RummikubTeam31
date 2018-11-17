import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;


public class HandleJoker {
	// check the valid color and value of joker for the set
	public boolean isSetWithJoker(ArrayList<Tile> l) {
		if(l.size() < 3 || l.size() > 4) return false;
		Collections.sort(l, new SortSmallestToBiggest());
		int check_point = 0;
		HashSet<String> set = new HashSet<>();
		for(int i =0; i <l.size();i++) {
			if (set.size() == 0) {
				if(l.get(i).isJoker()) {
					set.add(l.get(i).getJokerColor());
					check_point = l.get(i).getJokerPoint();}
				else {
					check_point = l.get(i).getNumber();
					set.add(l.get(i).getColor());
				}
			}
			else if(!l.get(i).isJoker()) {
				if (check_point != l.get(i).getNumber()) return false;
				if(!set.add(l.get(i).getColor()))
					return false;
			}
			else {
				if (check_point != l.get(i).getJokerPoint()) return false;
				if(!set.add(l.get(i).getJokerColor())) 
					return false;
			}
		}
		return true;
	};
	// check the valid color and value of joker for the sequences
	public  boolean isRunWithJoker(ArrayList<Tile> l) {
		if(l.size() < 3) return false;
		Collections.sort(l, new SortSmallestToBiggest());
		String color = "";
		if(l.get(0).isJoker()) color = l.get(0).getJokerColor();
		else color = l.get(0).getColor();
		
		for(int u =0; u < l.size()-1;u++) {
			int current, next;
			if(u == l.size()-1) return true;
			String current_color;
			if(l.get(u).isJoker()) {
				current = l.get(u).getJokerPoint();
				current_color = l.get(u).getJokerColor();
			}
			else {
				current = l.get(u).getNumber();
				current_color = l.get(u).getColor();
			}
			
			if(!color.equals(current_color)) return false;
			
			if (l.get(u+1).isJoker()) next = l.get(u+1).getJokerPoint();
			else next = l.get(u+1).getNumber();			
			
			if(current +1 != next) return false;
		}
		return true;
	};
	
	
	//sort tiles by number
	static class SortSmallestToBiggest implements Comparator<Tile> 
	{  public int compare(Tile a, Tile b) 
	  {	
		int first;
		if(!a.isJoker()) first = a.getNumber();  
		else first = a.getJokerPoint();
		
		int second;
		if(!b.isJoker()) second = b.getNumber();  
		else second = b.getJokerPoint();
		return first - second;
		} 
	}
}
