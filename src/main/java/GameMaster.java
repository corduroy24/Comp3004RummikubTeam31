import java.util.Observable;

enum GameState {Game_Start, End_Turn, End_Game};

public class GameMaster extends Observable{
    
	private GameState state;  
	
	public GameState getGameState() {
		return this.state;
	}
	
	public void setGameState(GameState currState) {		
		this.state = currState;
		setChanged(); 
		notifyObservers(); 
	}
	
	public void endTurn() {
		
	}

}
