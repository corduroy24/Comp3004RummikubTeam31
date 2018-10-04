
public class HumanPlayer {
	
	private boolean isTurn = false;
	
	public HumanPlayer () {
		isTurn = true;
	}
	
	public boolean getTurn () {
		return this.isTurn; 
	}
	
	public void setTurn(boolean turn) {
		this.isTurn  = turn; 
	}

}
