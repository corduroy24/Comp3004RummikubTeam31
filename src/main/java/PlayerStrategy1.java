import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PlayerStrategy1 implements PlayerStrategy {

	public boolean playTheGame(Player p) {
		// TODO Auto-generated method stub
		ArrayList<Tile> input = new ArrayList<Tile>();
		
		if(p.getIsFirstMeldComplete()) {
			
		}
		else {
			ArrayList<Tile> value =p.getHand().findRun(p.getHand());
			while(value != null) {
				for(int i =0; i < value.size();i++) {
					p.getHand().removeTile(value.get(i));
				}
				p.setIsfirstMeldComplete(true);
				p.getTable().addTiles(value);
				value = p.getHand().findRun(p.getHand());
			}
			if (p.getHand().sizeOfHand() == 0)
				p.setWinner();
		}
		return false;
		
	}
	
	class SortbyValue implements Comparator<Tile> 
	{ 
	    public int compare(Tile a, Tile b) 
	    { 
	        return a.getNumber() - b.getNumber(); 
	    } 
	}
	
}
