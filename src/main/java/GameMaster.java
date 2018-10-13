import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


//enum GameState {Game_Start, P1_Turn, P2_Turn, P3_Turn, P4_Turn, End_Game};

public class GameMaster extends Observable{
    
	private Player human;
	private Player AI1;
	private Player AI2;
	private Player AI3;
	private Deck deck;
	private Table table;
	private Ui GUI;
	
	
	
	public GameMaster() {
		human = new Player("Human",1, new HumanPlayerStrategy());
		AI1 = new Player("AI1",2, new PlayerStrategy1());
		AI2 = new Player("AI2",3, new PlayerStrategy1());
		AI3 = new Player("AI3",4, new PlayerStrategy1());
		deck = new Deck();
		deck.Shuffle();
		table = new Table();
		GUI = new Ui();
		this.addObserver(human);
		this.addObserver(AI1);
		this.addObserver(AI2);
		this.addObserver(AI3);
		this.addObserver(deck);
		this.addObserver(table);
	}
	
	public Deck getDeck() {return deck;}
	
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
	
	public void draw_card() {
		human.getHand().drawFirst14(deck);
		AI1.getHand().drawFirst14(deck);
		AI2.getHand().drawFirst14(deck);
		AI3.getHand().drawFirst14(deck);
		setChanged();
		notifyObservers();
	}
	
	
	
	public void test() {
		human.getHand().addTileToHand(deck.Draw());
		setChanged();
		notifyObservers();
		AI1.getHand().addTileToHand(deck.Draw());
		setChanged();
		notifyObservers();
		
		
	}
	
	public void gamestart() {
		while(human.isWinner() && AI1.isWinner() 
			&& AI2.isWinner() && AI3.isWinner()) {
		
			if(human.play()) {
				table = human.getTable();
				setChanged();
				notifyObservers();
			}
			else {
				deck = human.getDeck();
				setChanged();
				notifyObservers();
			}
			
			if(AI1.play()) {
				table = AI1.getTable();
				setChanged();
				notifyObservers();
			}
			else {
				deck = AI1.getDeck();
				setChanged();
				notifyObservers();
			}
			
			if(AI2.play()) {
				table = AI2.getTable();
				setChanged();
				notifyObservers();
			}
			else {
				deck = AI2.getDeck();
				setChanged();
				notifyObservers();
			}
			
			if(AI3.play()) {
				table = AI3.getTable();
				setChanged();
				notifyObservers();
			}
			else {
				deck = AI3.getDeck();
				setChanged();
				notifyObservers();
			}
			
			
			
			
		}
		
	}
	
	
	
	
	

	public static void main(String[] args) {
		GameMaster game = new GameMaster();
		game.draw_card();
		game.test();
	}
}
