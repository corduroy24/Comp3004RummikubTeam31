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
	
	private Table table;
	private Deck deck;
	private int FirstPlayerPoint;
	private int SecondPlayerPoint;
	private int ThirdPlayerPoint;
	private boolean win;

	
	
	public Player (String s, int id, PlayerStrategy strategy) {
		name=s;
		hand = new PlayerHand(name+"'s Hand"); 
		playerID = id; 
    	this.playerStrategy = strategy; 

	}
	public PlayerHand getHand(){return hand;}
	
    public void update(Observable obs, Object x) {
        GameMaster update = (GameMaster) obs;
        ArrayList<Player> enemies = update.getPlayers();
        enemies.remove(this);
        table = update.getTable();
        deck = update.getDeck();
        
        FirstPlayerPoint = enemies.get(0).getHand().sizeOfHand();
        SecondPlayerPoint = enemies.get(1).getHand().sizeOfHand();
        ThirdPlayerPoint = enemies.get(2).getHand().sizeOfHand();
        
        System.out.println("Here is" + name);
        System.out.println(FirstPlayerPoint + "\n" + SecondPlayerPoint + "\n" + ThirdPlayerPoint );
        
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
    
    public boolean play() {
    	return playerStrategy.playTheGame(table, hand);
    }
    
    public PlayerHand getPlayerHand () {	
    	return this.hand; }
    
    public boolean isWinner() {
    	return win;
    }
    public Table getTable() {return table;}
    public Deck getDeck() {return deck;}
    
    
    
    
}
