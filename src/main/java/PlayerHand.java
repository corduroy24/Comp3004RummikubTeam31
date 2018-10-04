import java.util.ArrayList;
import java.util.List;


public class PlayerHand {
	
    private List<Tile> playerHand = new ArrayList<Tile>();
    
    public void addTile(Tile newTile ) {
    	playerHand.add(newTile);
    }
    
    public List<Tile> getPlayerHand() {
    	return this.playerHand;
    }
    
    public int sizeOfHand() {
    	return playerHand.size(); 
    }
}
