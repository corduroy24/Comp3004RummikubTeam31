import java.util.Observable;

enum GameState {Game_Start, End_Turn, End_Game};

public class GameMaster extends Observable{
    
	private GameState state;  
	int currTurn = 0; 
	
	public GameMaster () {
		this.currTurn = 1;
		this.state = state.Game_Start;
		//setGameState(0)
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
