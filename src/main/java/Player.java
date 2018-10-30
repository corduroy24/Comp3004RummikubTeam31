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
	private String report = "";
	
	
	public String return_report() {
		return report;
	}
	
	public void set_report(String a) {
		report = a;
	}

	
	
	public Player (String s, int id, PlayerStrategy strategy) {
		name=s;
		hand = new PlayerHand(name+"'s Hand"); 
		playerID = id; 
    	this.playerStrategy = strategy; 
    	deck = new Deck();
    	table = new Table();

	}
	
	
	public PlayerHand getHand(){return hand;}
	
	public void setHandTest()
	{
		PlayerHand temp = new PlayerHand("test");
		
		Tile tile1 = new Tile(1, 11);
		Tile tile2 = new Tile(2, 11);
		Tile tile3 = new Tile(3, 11);
		Tile tile4 = new Tile(4, 11);
		
		Tile tile5 = new Tile(1, 1);
		Tile tile6 = new Tile(1, 2);
		
		Tile tile7 = new Tile(1, 4);
		Tile tile8 = new Tile(1, 5);
		Tile tile9 = new Tile(1, 6);
		Tile tile10 = new Tile(1, 7);
		Tile tile11 = new Tile(1, 8);
		
		Tile tile12 = new Tile(2, 1);
		Tile tile13 = new Tile(2, 4);
		Tile tile14 = new Tile(2, 5);
		
		temp.addTileToHand(tile1);
		temp.addTileToHand(tile2);
		temp.addTileToHand(tile3);
		temp.addTileToHand(tile4);
	
		temp.addTileToHand(tile5);
		temp.addTileToHand(tile6);
		
		temp.addTileToHand(tile7);
		temp.addTileToHand(tile8);
		temp.addTileToHand(tile9);
		temp.addTileToHand(tile10);
		temp.addTileToHand(tile11);
		
		temp.addTileToHand(tile12);
		temp.addTileToHand(tile13);
		temp.addTileToHand(tile14);
		
		hand = temp;
	}
	
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
    
    public boolean isEligibleforP3() {
    	if (((getPlayerHand().sizeOfHand())>=(firstPlayerPoint+2)&&(getPlayerHand().sizeOfHand()+2>=(secondPlayerPoint))
    			&&(getPlayerHand().sizeOfHand()+2)>=(thirdPlayerPoint))) {
    		return true;
    	}
    	else {
    	return false;
    	}
    }
    
    public void setEnimies(Player p1, Player p2, Player p3) { //for strategy 3 test purposes 
    	 firstPlayerPoint = p1.getHand().sizeOfHand();
         secondPlayerPoint = p2.getHand().sizeOfHand();
         thirdPlayerPoint = p3.getHand().sizeOfHand();
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
