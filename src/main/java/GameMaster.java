import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Observable;


//enum GameState {Game_Start, P1_Turn, P2_Turn, P3_Turn, P4_Turn, End_Game};

public class GameMaster extends Observable{
    
	
	private Player human;
	private Player AI1;
	private Player AI2;
	private Player AI3;
	private Player AI4;
	private Deck deck;
	private Table table;
	
	public GameMaster(){
		//create human, AI1, AI2, AI3, deck table GUI
		human = new Player("Human",1, new HumanPlayerStrategy());
		AI1 = new Player("AI1",2, new p1());
		AI2 = new Player("AI2",3, new PlayerStrategy2());
		AI3 = new Player("AI3",4, new PlayerStrategy3());
		AI4 = new Player("AI4",5, new PlayerStrategy4());

		deck = new Deck();
		deck.Shuffle();
		table = new Table();
		//Add human  and AIs to Observable
		this.addObserver(human);
		this.addObserver(AI1);
		this.addObserver(AI2);
		this.addObserver(AI3);
		this.addObserver(AI4);
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
		players.add(AI4);
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
		return AI2;
	}
	
	public Player getAI3() {
		return AI3;
	}
	public Player getAI4() {
		return AI4;
	}
	
	
	public void AI_play() {
		human.setIsTurn(false);
		AI1.setIsTurn(true);
		System.out.println("AI1 REPORT:  ");
		//AI1.getHand().HandReader();
		System.out.println("Number of tiles are: " + AI1.getHand().sizeOfHand());
		if(AI1.play()) System.out.println("AI 1 decide to play");
		else {
			if(deck.getDeck().size() > 0)
				AI1.getHand().addTileToHand(deck.Draw());
			System.out.println("AI 1 decide not to play");
		}
		
		AI3.getHand().sortTilesByColour();
		AI1.setIsTurn(false);
		AI2.setIsTurn(true);
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
		AI2.getHand().sortTilesByColour();
		AI2.setIsTurn(false);
		AI3.setIsTurn(true);
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
		AI3.getHand().sortTilesByColour();
		AI3.setIsTurn(false);
		human.setIsTurn(true);
		Announcement();
		System.out.println("Number of tiles are: " + AI3.getHand().sizeOfHand() + "\n");
		
		
		
		System.out.println("AI4 REPORT:  ");
		//AI3.getHand().HandReader();
		System.out.println("Number of tiles are: " + AI4.getHand().sizeOfHand());
		if(AI4.play()) System.out.println("AI 4 decide to play");
		else {
			if(deck.getDeck().size() > 0) AI4.getHand().addTileToHand(deck.Draw());
			System.out.println("AI 4 decide not to play");}
		AI4.getHand().sortTilesByColour();
		AI4.setIsTurn(false);
		human.setIsTurn(true);
		Announcement();
		System.out.println("Number of tiles are: " + AI4.getHand().sizeOfHand() + "\n");
		

	}

	//draw card to human and AI
	public int[] dealInitialHand(int[] ai) 
	{
		int[][] turnOrder = new int[ai.length][2];
		Deck turnDeck = new Deck();
		turnDeck.Shuffle();
		
		for(int x=0;x<ai.length;x++)
		{
			if(ai[x]==1)
			{
				//System.out.println("You picked AI 1");
				
				int temp = turnDeck.Draw().getNumber();
				if(temp == 14)
				{
					temp = turnDeck.Draw().getNumber();
					if(temp == 14)
					{
						temp = turnDeck.Draw().getNumber();
						turnOrder[x][0] = temp;
					}
					else
					{
						turnOrder[x][0] = temp;
					}
				}
				else
				{
					turnOrder[x][0] = temp;
				}
				
				turnOrder[x][1] = 1;
				
				AI1.getHand().drawFirst14(deck);
				AI1.getHand().sortTilesByColour();
				AI1.getHand().HandReader();
			}
			else if(ai[x]==2)
			{
				//System.out.println("You picked AI 2");
				
				int temp = turnDeck.Draw().getNumber();
				if(temp == 14)
				{
					temp = turnDeck.Draw().getNumber();
					if(temp == 14)
					{
						temp = turnDeck.Draw().getNumber();
						turnOrder[x][0] = temp;
					}
					else
					{
						turnOrder[x][0] = temp;
					}
				}
				else
				{
					turnOrder[x][0] = temp;
				}
				
				turnOrder[x][1] = 2;
				
				AI2.getHand().drawFirst14(deck);
				AI2.getHand().sortTilesByColour();
				AI2.getHand().HandReader();
			}
			else if(ai[x]==3)
			{
				//System.out.println("You picked AI 3");
				
				int temp = turnDeck.Draw().getNumber();
				if(temp == 14)
				{
					temp = turnDeck.Draw().getNumber();
					if(temp == 14)
					{
						temp = turnDeck.Draw().getNumber();
						turnOrder[x][0] = temp;
					}
					else
					{
						turnOrder[x][0] = temp;
					}
				}
				else
				{
					turnOrder[x][0] = temp;
				}
				
				turnOrder[x][1] = 3;
				
				AI3.getHand().drawFirst14(deck);
				AI3.getHand().sortTilesByColour();
				AI3.getHand().HandReader();
			}
			else if(ai[x]==4)
			{
				//System.out.println("You picked AI 4");
				
				
				int temp = turnDeck.Draw().getNumber();
				if(temp == 14)
				{
					temp = turnDeck.Draw().getNumber();
					if(temp == 14)
					{
						temp = turnDeck.Draw().getNumber();
						turnOrder[x][0] = temp;
					}
					else
					{
						turnOrder[x][0] = temp;
					}
				}
				else
				{
					turnOrder[x][0] = temp;
				}
				
				turnOrder[x][1] = 4;
				
				AI4.getHand().drawFirst14(deck);
				AI4.getHand().sortTilesByColour();
				AI4.getHand().HandReader();
				
			}
		}
		
		turnOrder[ai.length-1][0] = turnDeck.Draw().getNumber();
		turnOrder[ai.length-1][1] = 10;

		human.getHand().drawFirst14(deck);
		human.getHand().sortTilesByColour();
		human.getHand().HandReader();
		human.setIsTurn(true);
		
		boolean doubles = true;
		int xOne = -1;
		int xTwo = -1;
		
		do
		{
			if(xOne != -1)
			{
				turnOrder[xOne][0] = turnDeck.Draw().getNumber();
				turnOrder[xTwo][0] = turnDeck.Draw().getNumber();
			}
			
			Arrays.sort(turnOrder, new Comparator<int[]>() 
			{
			    public int compare(int[] a, int[] b) 
			    {
			        return Integer.compare(b[0], a[0]);
			    }
			});
			
			int findDoubles = turnOrder[0][0];
			
			doubles = false;
			
			for(int x=1;x<turnOrder.length;x++)
			{
				if(findDoubles == turnOrder[x][0])
				{
					doubles = true;
					xOne = x-1;
					xTwo = x;
				}
				else
				{
					findDoubles = turnOrder[x][0];
				}
			}
			
		}while(doubles);
		
		int[] returnOrder = new int[ai.length];
		for(int x=0;x<turnOrder.length;x++)
		{
			returnOrder[x] = turnOrder[x][1];
		}
		

		Announcement();
		
		return returnOrder;
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
