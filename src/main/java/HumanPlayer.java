
public class HumanPlayer {
	
	private boolean isTurn = false;
	private boolean isTileTaken = false;
	private boolean isTilePlaced = false;

	
	public HumanPlayer () {
		isTurn = true;
	}
	
	public boolean getIsTurn () {
		return this.isTurn; 
	}
	
	public void setIsTurn(boolean turn) {
		this.isTurn  = turn; 
	}
	
	public boolean getIsTileTaken () {
		return this.isTurn; 
	}
	
	public void setIsTileTaken(boolean turn) {
		this.isTurn  = turn; 
	}
	
}
