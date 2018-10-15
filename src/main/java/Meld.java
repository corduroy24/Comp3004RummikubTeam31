import java.util.ArrayList;

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
}
