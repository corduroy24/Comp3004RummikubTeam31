import java.util.Observable;
import java.util.Observer;


enum GameState {Game_Start, P1_Turn, P2_Turn, P3_Turn, P4_Turn, End_Game};

public class GameMaster extends Observable implements Observer {
    
	
	//GameMaster master;  

	Player player_1; // human
	Player player_2; // AI strategy 1 
	Player player_3;  // A1 strategy 2 
	Player player_4;  // A1 strategy 3
	
	private GameState state;  
	int currTurn = 0; 

	Deck deck; 
	
	boolean isWinner = false; 

	
	public GameMaster () {
		this.currTurn = 1;
		this.state = state.Game_Start;
		//setGameState(0)
	}
	
	public void main(String [] args) 
	{
		startGame(); 
	}
	
    public void update(Observable obs, Object x) {
        System.out.println("update(" + obs + "," + x + ");");
     }
	
	public void startGame ()
	{
		//createtable 
		//create each player 
		player_1 = new Player("Player 1 (Human)", 1, new HumanPlayerStrategy()); 
		player_2 = new Player("Player 2 (AI)", 1, new PlayerStrategy1()); 
		//player_3 = new Player("Player 3 (AI)", 1, new PlayerStrategy2()); 
		//player_4 = new Player("Player 4 (AI)", 1, new PlayerStrategy3()); 
		
		
		this.addObserver(player_1);
		this.addObserver(player_2);
		//this.addObserver(player_3);
		//this.addObserver(player_4);
		
		dealTiles(); 
		
		run(); 
		
		

	}
	private void run() {
		// TODO Auto-generated method stub
		setGameState(GameState.P1_Turn); 
		while(isWinner) {
			
			//wait until player 1 clicks endturn button 
			//set the state to P1_turn 

		}

	}

	private void dealTiles() {
		// TODO Auto-generated method stub
		deck = new Deck(); 
		deck.Shuffle();
		
		player_1.getPlayerHand().drawFirst14(deck);
		player_2.getPlayerHand().drawFirst14(deck);
		//player_3.getPlayerHand().drawFirst14(deck);
		//player_4.getPlayerHand().drawFirst14(deck);

	}

	public GameState getGameState() {
		return this.state;
	}
	
	public void setGameState(GameState currState) {		
		this.state = currState;
		setChanged(); 
		notifyObservers(); 
	}
	
	public void endTurn() {
		if(currTurn  == 4)
			this.currTurn  = 1; 
		else 
			currTurn++; 
	}


}
