import java.util.ArrayList;
import java.util.List;

public class Player {
	//Strategy playerStrategy; 

	
	private boolean isTurn = false;
	private boolean isTileTaken = false;
	private boolean isTilePlaced = false;
	private boolean isFirstMeldComplete = false;

	private List<Tile> playerHand = new ArrayList<Tile>();


	public Player () {
	}
	
	public boolean getIsTurn () { //gets the current status of the players turn  
		return this.isTurn; 
	}
	
	public void setIsTurn(boolean turn) { //sets the turn of the player to true or false 
		this.isTurn  = turn; 
	}
	
	public boolean getIsTileTaken () { //gets the status of a player taking a tile 
		return this.isTileTaken; 
	}
	
	public void setIsTileTaken(boolean tileTaken) { //sets the status of the player taking a tile or not 
		this.isTileTaken  = tileTaken; 
	}
	
	public boolean getIsTilePlaced () { //gets the status of a player placing a tile
		return this.isTilePlaced; 
	}
	
	public void setIsTilePlaced(boolean tilePlaced) {//sets the status of the placing a tile or not 
		this.isTilePlaced  = tilePlaced; 
	}
	
	public boolean getIsFirstMeldComplete () {
		return this.isFirstMeldComplete; 
	}
	
	public void setIsfirstMeldComplete(boolean firstMeldComplete) {
		this.isFirstMeldComplete  = firstMeldComplete; 
	}
	
    public List<Tile> getPlayerHand() {
    	return this.playerHand;
    }
    
    public void addTileToHand(Tile newTile ) {
    	playerHand.add(newTile);
    }

    
    public int sizeOfHand() {
    	return playerHand.size(); 
    }
	
    public void playTileFromHand(Tile tileToRemove) {
    	playerHand.remove(tileToRemove); 
    }

}
