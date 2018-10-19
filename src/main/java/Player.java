import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Player implements Observer{
	private PlayerStrategy playerStrategy; 
	private String name;
	private PlayerHand hand; 
	
	private boolean isTurn = false;
	private boolean isTileTaken = false;
	private boolean isTilePlaced = false;
	private boolean isFirstMeldComplete = false;
	private int playerID = 0; 
	
	private Table table; // player can see table itself
	private Deck deck; // player can see deck itself
	private int firstPlayerPoint; // Number of tiles on hand of first player
	private int secondPlayerPoint;// Number of tiles on hand of second player
	private int thirdPlayerPoint;// Number of tiles on hand of third player
	private boolean win; // define winner

	
	
	public Player (String s, int id, PlayerStrategy strategy) {
		name=s;
		hand = new PlayerHand(name+"'s Hand"); 
		playerID = id; 
    	this.playerStrategy = strategy; 
    	deck = new Deck();
    	table = new Table();

	}
	
	
	public PlayerHand getHand(){return hand;}
	
    public void update(Observable obs, Object x) {
        GameMaster update = (GameMaster) obs;
        ArrayList<Player> enemies = update.getPlayers();
       // avoid duplicate information
        enemies.remove(this);
        // update table and deck to decide what to play
        table = update.getTable();
        deck = update.getDeck();
        
        firstPlayerPoint = enemies.get(0).getHand().sizeOfHand();
        secondPlayerPoint = enemies.get(1).getHand().sizeOfHand();
        thirdPlayerPoint = enemies.get(2).getHand().sizeOfHand();
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
	

    public PlayerStrategy getPlayerStrategy() {
    	return this.playerStrategy; 
    }
    //play function, which take table and its hand to decide how to play for this turn
    // it return true false, so that GM can recognize update from deck or table to notify to others
    public boolean play() {
    	return playerStrategy.playTheGame(this);
    }
    
    public PlayerHand getPlayerHand () {	
    	return this.hand; }
    
    public boolean isWinner() {
    	return win;
    }
    public Table getTable() {return table;}
    public Deck getDeck() {return deck;}
    
	public int getFirstPlayerHand() {
		// TODO Auto-generated method stub
		return firstPlayerPoint;
	}
	public int getSecondPlayerHand() {
		// TODO Auto-generated method stub
		return secondPlayerPoint;
	}
	public int getThirdPlayerHand() {
		// TODO Auto-generated method stub
		return thirdPlayerPoint;
	}
    
	public void setWinner() {
		win = true;
	}
    
    
    
}
