import java.util.ArrayList;
import java.util.Collections;

public class Meld {
	
	private boolean status; 
	
	private ArrayList<Tile> meld = new ArrayList<Tile>(); 
	
	public Meld (ArrayList<Tile> t) {

		addTiles(t); 
		
		//could add a check meld method here, if we werent asuuming validation of melds
		this.status = true; 
	}
	
	public void addTiles(ArrayList<Tile> t) {
		// TODO Auto-generated method stub
		meld.addAll(t); 
	}
	
	public void addTile(Tile t) {
		// TODO Auto-generated method stub
		meld.add(t); 
	}

	public boolean getStatus() {
		
		return this.status; 
	}
	
	public void removeTile(Tile t) {
		meld.remove(t); 
	}

	public void removeTiles (ArrayList<Tile> t) {
		meld.removeAll(t); 
	}
	
	public boolean checkRun(ArrayList<Tile> t) {
		Tile currTile; 
		
		if(t.size() < 3) return false;  
		
		String colour = t.get(0).getColor(); 
		int prevNum = t.get(0).getNumber(); 
		
		Collections.sort(t, new SortByX());
		
		for(int i = 1; i < t.size(); i++) {
			currTile = t.get(i); 
			
			if(!(colour.equals(currTile.getColor()))) return false;
			
			if(currTile.getNumber() == prevNum + 1) prevNum++; 
			else return false; 
		}
		
		return true; 
	}
	
	public boolean checkSet(ArrayList<Tile> t) {
		
		Tile currTile; 
		ArrayList<String> usedColours = new ArrayList<String>(); 
		
		if(t.size() != 3 & t.size() != 4) return false;  
		
		int setNum = t.get(0).getNumber(); 
		usedColours.add(t.get(0).getColor()); 
		
		for(int i = 1; i < t.size(); i++) {
			currTile = t.get(i); 
			
			if(currTile.getNumber() != setNum) return false;
			
			
			if(usedColours.contains(currTile.getColor())) return false; 
			
			
			usedColours.add(currTile.getColor()); 
		}
		return true; 
		
		
	}
	
	public ArrayList<Tile> getTiles() {
		return this.meld; 
	}
}
