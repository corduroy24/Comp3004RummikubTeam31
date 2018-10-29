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
	
	public Table getTable() 
	{
		return table;
	}
	
	public void setTable(Table newTable) 
	{
		table = newTable;
	}
	
	public Player getHuman() {
		return human;
	}
	
	public Player getAI() {
		return AI1;
	}
	
	public Player getAI2() {
		// TODO Auto-generated method stub
		return AI2;
	}
	
	public Player getAI3() {
		// TODO Auto-generated method stub
		return AI3;
	}
	
	
	public void AI_play() {
		
		System.out.println("AI1 REPORT:  ");
		//AI1.getHand().HandReader();
		System.out.println("Number of tiles are: " + AI1.getHand().sizeOfHand());
		if(AI1.play()) System.out.println("AI 1 decide to play");
		else {
			if(deck.getDeck().size() > 0)
				AI1.getHand().addTileToHand(deck.Draw());
			System.out.println("AI 1 decide not to play");
		}
		Announcement();
		System.out.println("Number of tiles are: " + AI1.getHand().sizeOfHand());
		
		System.out.println("------------------------------");
		
		System.out.println("AI2 REPORT:  ");
		//AI2.getHand().HandReader();
		System.out.println("Number of tiles are: " + AI2.getHand().sizeOfHand());
		if(AI2.play()) System.out.println("AI 2 decide to play");
		else {
			if(deck.getDeck().size() > 0) AI2.getHand().addTileToHand(deck.Draw());
			System.out.println("AI 2 decide not to play");}
		Announcement();
		System.out.println("Number of tiles are: " + AI2.getHand().sizeOfHand());
		
		System.out.println("------------------------------");
		
		
		System.out.println("AI3 REPORT:  ");
		//AI3.getHand().HandReader();
		System.out.println("Number of tiles are: " + AI3.getHand().sizeOfHand());
		if(AI3.play()) System.out.println("AI 3 decide to play");
		else {
			if(deck.getDeck().size() > 0) AI3.getHand().addTileToHand(deck.Draw());
			System.out.println("AI 3 decide not to play");}
		Announcement();
		System.out.println("Number of tiles are: " + AI3.getHand().sizeOfHand() + "\n");
		
	}
	
	
	
	
	//draw card to human and AI
	public void dealInitialHand() {
		human.getHand().drawFirst14(deck);
		human.getHand().sortTilesByColour();
		AI1.getHand().drawFirst14(deck);
		AI2.getHand().drawFirst14(deck);
		AI3.getHand().drawFirst14(deck);
		Announcement();
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

}
