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
		AI2 = new Player("AI2",3, new HumanPlayerStrategy());
		AI3 = new Player("AI3",4, new HumanPlayerStrategy());
		deck = new Deck();
		deck.Shuffle();
		table = new Table();
		GUI = new Ui();
		this.addObserver(human);this.addObserver(AI1);
		this.addObserver(AI2);this.addObserver(AI3);
		this.addObserver(deck);this.addObserver(table);
		this.addObserver(GUI);
	
	}
	
	public void draw_card() {
		human.getHand().drawFirst14(deck);
		AI1.getHand().drawFirst14(deck);
		AI2.getHand().drawFirst14(deck);
		AI3.getHand().drawFirst14(deck);
		setChanged();
		notifyObservers();
	}
	
	
	
	
	

	public static void main(String[] args) {
		GameMaster game = new GameMaster();
		game.draw_card();
	}
}
