import java.util.ArrayList;
import java.util.List;

public class PlayerHand {

	private List<Tile> hand = new ArrayList<Tile>();

	
    public List<Tile> getHand() {
    	return this.hand;
    }
    
    public void addTileToHand(Tile newTile ) {
    	hand.add(newTile);
    }

    
    public int sizeOfHand() {
    	return hand.size(); 
    }
	
    public void playTileFromHand(Tile tileToPlay) {
    //	this.playerStrategy.playTiles(hand, tileToPlay);
    	hand.remove(tileToPlay); 

    }
    
    public boolean isEmpty() {
    	return hand.isEmpty();
    }
    
}
