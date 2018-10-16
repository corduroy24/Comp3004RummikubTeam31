import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javafx.application.Application;


//enum GameState {Game_Start, P1_Turn, P2_Turn, P3_Turn, P4_Turn, End_Game};

public class GameMaster extends Observable{
    
	
	private Player human;
	private Player AI1;
	private Player AI2;
	private Player AI3;
	private Deck deck;
	private Table table;
	private Ui GUI;
	
	public GameMaster(){
		//create human, AI1, AI2, AI3, deck table GUI
		human = new Player("Human",1, new HumanPlayerStrategy());
		AI1 = new Player("AI1",2, new PlayerStrategy1());
		AI2 = new Player("AI2",3, new PlayerStrategy2());
		AI3 = new Player("AI3",4, new PlayerStrategy3());
		deck = new Deck();
		deck.Shuffle();
		table = new Table();
		//Add human  and AIs to Observable
		this.addObserver(human);
		this.addObserver(AI1);
		this.addObserver(AI2);
		this.addObserver(AI3);
	}
	
	// main deck
	public Deck getDeck() {return deck;}
	
	//get human and AIs to update data to each player
	public ArrayList<Player> getPlayers(){
		ArrayList<Player> players  = new ArrayList<Player>();
		players.add(human);
		players.add(AI1);
		players.add(AI2);
		players.add(AI3);
		return players;
	}
	
	public Table getTable() {
		return table;
	}
	
	public Player getHuman() {
		return human;
	}
	
	//draw card to human and AI
	public void dealInitialHand() {
		human.getHand().drawFirst14(deck);
		AI1.getHand().drawFirst14(deck);
		AI2.getHand().drawFirst14(deck);
		AI3.getHand().drawFirst14(deck);
		Announcement(human);
	}
	
	public void gamestart() {
		while(human.isWinner() && AI1.isWinner() 
			&& AI2.isWinner() && AI3.isWinner()) {
		
			if(human.play()) {
				table = human.getTable();
				Announcement();
			}
			else {
				deck = human.getDeck();
				Announcement();
			}
			
			if(AI1.play()) {
				table = AI1.getTable();
				Announcement();
			}
			else {
				deck = AI1.getDeck();
				Announcement();
			}
			
			if(AI2.play()) {
				table = AI2.getTable();
				Announcement();
			}
			else {
				deck = AI2.getDeck();
				Announcement();
			}
			
			if(AI3.play()) {
				table = AI3.getTable();
				Announcement();
			}
			else {
				deck = AI3.getDeck();
				Announcement();
		}
	}	
}
	// send notification to observers
	public void Announcement(){
		setChanged();
		notifyObservers();
	}
	public void Announcement(Player human){
		setChanged();
		notifyObservers(human);
	}

	public static void main(String[] args) {
		GameMaster game = new GameMaster();
		game.dealInitialHand();
		
	}
}
